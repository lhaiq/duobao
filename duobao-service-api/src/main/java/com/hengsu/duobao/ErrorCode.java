package com.hengsu.duobao;

import com.hkntv.pylon.core.exception.BusinessException;

/**
 * Created by haiquanli on 15/11/19.
 */
public enum ErrorCode {

    //AUTH

    ADMIN_PASS_ERROR("1001", "旧密码错误"),
    ADMIN_NAME_EXISTED("1001", "用户名已存在"),
    ADMIN_LOGIN_ERROR("1001", "密码错误"),
    NO_PERMISSION("1001", "没有权限访问该接口"),
    SYSTEM_INTERNAL_ERROR("1000", "内部错误"),
    AUTH_CODE_TIME_OUT("1001", "验证码超时"),
    AUTH_CODE_ERROR("1002", "验证码不正确"),
    AUTH_TOKEN_INVALID("1003", "AUTH TOKEN非法"),
    AUTH_TOKEN_MUST("1004", "AUTH TOKEN 必须填"),
    REGISTER_PHONE_EXISTED("1005", "该手机已经注册过"),
    USER_NOT_EXISTED("1006", "用户不存在"),
    AID_USER_NOT_EXISTED("1006", "推广id非法"),



    OUT_OF_PERIODS("1007", "超出设定期数"),
    CANNOT_UNSHELVE("1007", "正在抢购的商品不能下架"),
    CANNOT_UPDATE("1007", "正在抢购的商品不能修改"),
    CANNOT_SHELVE("1007", "不能重复上架"),
    SHOPPING_HAVE_FINISHED("1007", "该商品已经抢完"),
    SHOPPING_NOT_CERTIFIED("1007", "实名用户才能抢购"),
    //商品
    BALANCE_NOT_ENOUGH("1013", "虚拟币不足"),
    GOODS_NOT_ENOUGH("1013", "库存不足"),
    SHOP_FINISHED("1013", "已卖完"),
    NUM_INVALID("1013", "购买数量非法"),
    ORDER_STATUS_INVALID("1013", "该订单已支付或超时"),
    MONEY_NOT_ENOUGH("1013", "付款不足"),

    PHONE_NOT_REGISTER("1009", "该手机号尚未注册"),

    JSON_FORMATTED("1010", "JSON格式错误"),
    FIELD_MUST("1011", "字段必须填"),
    PARAMETER_MUST("1012", "字段必须填"),
    IMAGE_NOT_EXISTED("1013", "图片不存在"),



    UPLOAD_IMAGE_ERROR("1014", "上传图片错误"),
    GET_IMAGE_ERROR("1014", "获取图片错误"),
    JSON_FORMAT_ERROR("1016", "json格式错误"),



    ;

    private String code;
    private String errorMsg;

    private ErrorCode(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static void throwBusinessException(ErrorCode errorCode) {
        throw new BusinessException(errorCode.errorMsg, errorCode.code);
    }

    public String getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static void main(String[] args) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            System.out.println(errorCode.getCode() + "\t" + errorCode.getErrorMsg());
        }
    }
}
