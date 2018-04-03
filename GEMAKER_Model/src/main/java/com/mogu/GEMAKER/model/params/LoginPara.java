package com.mogu.GEMAKER.model.params;

import javax.validation.constraints.Pattern;

/**
 * Created by chang on 2017/6/19.
 */
public class LoginPara extends BaseParas {
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9])|(17[6-8]))\\d{8}$",message = "手机号码格式不匹配。")
    private String tel;

    private String yzm;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }
}
