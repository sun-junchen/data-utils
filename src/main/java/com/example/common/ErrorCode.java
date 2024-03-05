package com.example.common;

/**
 * 自定义错误码
 *
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(400, "请求参数错误"),
    NOT_LOGIN_ERROR(400, "未登录"),
    NO_AUTH_ERROR(403, "无权限"),
    NOT_FOUND_ERROR(400, "请求数据不存在"),
    FORBIDDEN_ERROR(403, "禁止访问"),
    SYSTEM_ERROR(500, "系统内部异常"),
    OPERATION_ERROR(500, "操作失败"),
    API_REQUEST_ERROR(500, "接口调用失败"),
    CAST_OBJECT_ERROR(400, "转对象失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
