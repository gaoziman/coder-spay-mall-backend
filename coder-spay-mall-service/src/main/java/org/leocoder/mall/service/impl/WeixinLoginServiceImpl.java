package org.leocoder.mall.service.impl;

import com.google.common.cache.Cache;
import org.leocoder.mall.domain.req.WeixinQrCodeReq;
import org.leocoder.mall.domain.resp.WeixinQrCodeRes;
import org.leocoder.mall.domain.resp.WeixinTokenRes;
import org.leocoder.mall.domain.vo.WeixinTemplateMessageVO;
import org.leocoder.mall.service.ILoginService;
import org.leocoder.mall.service.weixin.IWeixinApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 程序员Leo
 * @description 微信服务
 * @date 2025-05-31 13:46
 */
@Service
public class WeixinLoginServiceImpl implements ILoginService {

    @Value("${weixin.config.app-id}")
    private String appid;

    @Value("${weixin.config.app-secret}")
    private String appSecret;

    @Value("${weixin.config.template_id}")
    private String template_id;

    @Resource
    private Cache<String, String> weixinAccessToken;

    @Resource
    private IWeixinApiService weixinApiService;

    @Resource
    private Cache<String, String> openidToken;


    /**
     * 生成登录码
     *
     * @return 登录码凭证ticket
     * @throws IOException IO异常
     */
    @Override
    public String createQrCodeTicket() throws IOException {
        // 1. 获取 accessToken
        String accessToken = weixinAccessToken.getIfPresent(appid);
        if (null == accessToken) {
            Call<WeixinTokenRes> call = weixinApiService.getToken("client_credential", appid, appSecret);
            WeixinTokenRes weixinTokenRes = call.execute().body();
            assert weixinTokenRes != null;
            accessToken = weixinTokenRes.getAccess_token();
            weixinAccessToken.put(appid, accessToken);
        }

        // 2. 生成 ticket
        WeixinQrCodeReq weixinQrCodeReq = WeixinQrCodeReq.builder()
                .expire_seconds(2592000)
                .action_name(WeixinQrCodeReq.ActionNameTypeVO.QR_SCENE.getCode())
                .action_info(WeixinQrCodeReq.ActionInfo.builder()
                        .scene(WeixinQrCodeReq.ActionInfo.Scene.builder()
                                .scene_id(100601)
                                .build())
                        .build())
                .build();

        Call<WeixinQrCodeRes> call = weixinApiService.createQrCode(accessToken, weixinQrCodeReq);
        WeixinQrCodeRes weixinQrCodeRes = call.execute().body();
        assert null != weixinQrCodeRes;
        return weixinQrCodeRes.getTicket();
    }

    /**
     * 检查登录状态
     *
     * @param ticket 登录码凭证ticket
     * @return openid
     */
    @Override
    public String checkLogin(String ticket) {
        return openidToken.getIfPresent(ticket);
    }


    /**
     * 保存登录状态
     *
     * @param ticket        登录码凭证ticket
     * @param openid        openid
     * @param loginTime     登录时间
     * @param loginIp       登录IP
     * @param loginLocation 登录地点
     * @throws IOException IO异常
     */
    @Override
    public void saveLoginState(String ticket, String openid, String loginTime, String loginIp, String loginLocation) throws IOException {
        openidToken.put(ticket, openid);

        // 1. 获取 accessToken 【实际业务场景，按需处理下异常】
        String accessToken = weixinAccessToken.getIfPresent(appid);
        if (null == accessToken) {
            Call<WeixinTokenRes> call = weixinApiService.getToken("client_credential", appid, appSecret);
            WeixinTokenRes weixinTokenRes = call.execute().body();
            assert weixinTokenRes != null;
            accessToken = weixinTokenRes.getAccess_token();
            weixinAccessToken.put(appid, accessToken);
        }

        // 2.发送模板消息
        Map<String, Map<String, String>> data = new HashMap<>();
        WeixinTemplateMessageVO.put(data, WeixinTemplateMessageVO.TemplateKey.userName, openid);
        WeixinTemplateMessageVO.put(data, WeixinTemplateMessageVO.TemplateKey.loginTime, loginTime);
        WeixinTemplateMessageVO.put(data, WeixinTemplateMessageVO.TemplateKey.loginIp, loginIp);
        WeixinTemplateMessageVO.put(data, WeixinTemplateMessageVO.TemplateKey.loginLocation, loginLocation);
        WeixinTemplateMessageVO.put(data, WeixinTemplateMessageVO.TemplateKey.remark, "如有疑问请联系客服。", "#00b386");


        WeixinTemplateMessageVO templateMessageDTO = new WeixinTemplateMessageVO(openid, template_id);
        templateMessageDTO.setUrl("https://leocoder.cn");
        templateMessageDTO.setData(data);

        Call<Void> call = weixinApiService.sendMessage(accessToken, templateMessageDTO);
        call.execute();

    }

}
