package com.hengsu.duobao.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by haiquanli on 15/11/19.
 */
public class RegisterVO {

    @NotEmpty
    @Size(min = 11, max = 11)
    private String phone;
    @NotEmpty
    private String password;
    @NotEmpty
    private String validateCode;

    private String aid;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
