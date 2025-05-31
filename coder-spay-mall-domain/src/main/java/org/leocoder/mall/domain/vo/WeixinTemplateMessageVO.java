package org.leocoder.mall.domain.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-30 22:03
 * @description : WeixinTemplateMessageVO
 */
public class WeixinTemplateMessageVO {

    private String touser;
    private String template_id;
    private String url;
    private Map<String, Map<String, String>> data = new HashMap<>();

    public WeixinTemplateMessageVO(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }

    // 实例方法put
    public void put(TemplateKey key, String value) {
        Map<String, String> item = new HashMap<>();
        item.put("value", value);
        item.put("color", "#173177");
        data.put(key.getCode(), item);
    }

    // 静态方法put
    public static void put(Map<String, Map<String, String>> data, TemplateKey key, String value, String color) {
        Map<String, String> item = new HashMap<>();
        item.put("value", value);
        item.put("color", color);
        data.put(key.getCode(), item);
    }
    public static void put(Map<String, Map<String, String>> data, TemplateKey key, String value) {
        put(data, key, value, "#173177");
    }


    // 枚举：模板消息字段，全部补全！
    public enum TemplateKey {
        userName("userName", "登录用户"),
        loginTime("loginTime", "登录时间"),
        loginIp("loginIp", "登录IP"),
        loginLocation("loginLocation", "登录地点"),
        remark("remark", "备注");
        // 可扩展更多字段

        private String code;
        private String desc;

        TemplateKey(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    // Getter & Setter
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }
}
