package com.hengsu.duobao.core.vo;

/**
 * Created by haiquanli on 16/5/31.
 */
public class UpdatePassVO {

    private String oldPass;
    private String newPass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }
}
