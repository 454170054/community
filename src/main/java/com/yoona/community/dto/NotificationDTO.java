package com.yoona.community.dto;


public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private Integer type;
    private String typeName;

    public Long getOuterId() {
        return outerId;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getNotifier() {
        return notifier;
    }

    public void setNotifier(Long notifier) {
        this.notifier = notifier;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName;
    }

    public String getOuterTitle() {
        return outerTitle;
    }

    public void setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle;
    }

}
