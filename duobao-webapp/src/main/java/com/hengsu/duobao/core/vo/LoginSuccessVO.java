package com.hengsu.duobao.core.vo;

/**
 * Created by haiquanli on 15/11/19.
 */
public class LoginSuccessVO {

    private UserVO user;

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public UserVO getUser() {
        return user;
    }
}
