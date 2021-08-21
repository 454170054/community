package com.yoona.community.Exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001, "问题不存在，换个问题看看？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或者评论进行回复"),
    NOT_LOGIN(2003, "用户未登录请先登录"),
    SYS_ERROR(2004, "服务器冒烟了，请稍后再试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟你这是读别人的通知呢"),
    NOTIFICATION_NOT_FOUND(2009, "消息走丢了"),
    ;
    private final String message;
    private final Integer code;


    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
