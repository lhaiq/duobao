package com.hengsu.duobao.core.model;

/**
 * Created by haiquanli on 16/4/13.
 */
public class AuthModel {

    public static final int ROLE_ALL = 0;
    public static final int ROLE_USER = 1;
    public static final int ROLE_ADMIN = 2;
    public static final int ROLE_SUPER_ADMIN = 3;


    private Long id;
    private Integer role;

    public AuthModel(Long id, Integer role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Integer getRole() {
        return role;
    }

}
