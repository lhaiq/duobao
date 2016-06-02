package com.hengsu.duobao.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by haiquanli on 15/11/21.
 */
public class ChangePassVO {

    @NotEmpty
    @Size(min = 11,max = 11)
    private String phone;
    @NotEmpty
    private String validateCode;
    @NotEmpty
    private String password;

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getValidateCode() {
        return validateCode;
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
}
