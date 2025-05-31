package org.leocoder.web.controlelr;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.leocoder.mall.common.convention.exception.ClientException;
import org.leocoder.mall.common.convention.result.Result;
import org.leocoder.mall.common.convention.result.Results;
import org.leocoder.mall.common.enums.HttpCodeEnum;
import org.leocoder.mall.service.ILoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author leocoder
 * @description 登录服务
 * @date 2025-05-31 13:58
 */
@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/login/")
public class LoginController {

    @Resource
    private ILoginService loginService;

    /**
     * 生成微信扫码登录二维码ticket
     *
     * @return Result<String>
     */
    @GetMapping("/weixin_qrcode_ticket")
    public Result<String> weixinQrCodeTicket() {
        try {
            String qrCodeTicket = loginService.createQrCodeTicket();
            log.info("生成微信扫码登录 ticket:{}", qrCodeTicket);
            return Results.success(qrCodeTicket);
        } catch (Exception e) {
            log.error("生成微信扫码登录 ticket 失败", e);
            throw new ClientException(HttpCodeEnum.Ticket_Generate_Error);
        }
    }


    /**
     * 轮训校验扫码登录结果
     *
     * @param ticket 扫码登录 ticket
     * @return Result<String>
     */
    @GetMapping("/check_login")
    public Result<String> checkLogin(@RequestParam String ticket) {
        try {
            String openidToken = loginService.checkLogin(ticket);
            log.info("扫码检测登录结果 ticket:{} openidToken:{}", ticket, openidToken);
            if (StringUtils.isNotBlank(openidToken)) {
                return Results.success(openidToken);
            } else {
                throw new ClientException(HttpCodeEnum.Not_Login);
            }
        } catch (Exception e) {
            log.error("扫码检测登录结果失败 ticket:{}", ticket, e);
            throw new ClientException(HttpCodeEnum.Login_Check_Error);
        }
    }

}
