package com.magic.magicprojectbaseapp.mvp.entity;

/**
 */
public class LoginBean {

    private String errorCode;//返回码
    private String msg;//返回信息
    private String usercode;//用户id
    private String username;//用户名
    private String orgcode;//机构编码
    private String orgname;//机构名称
    private String password;//登录密码
    private String time;//当前时间  同步系统时间
    private String ifrecord;//是否开启录音权限 y是，n否
    private String apkversion;//版本号
    private String roleid;//用户角色

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIfrecord() {
        return ifrecord;
    }

    public void setIfrecord(String ifrecord) {
        this.ifrecord = ifrecord;
    }

    public String getApkversion() {
        return apkversion;
    }

    public void setApkversion(String apkversion) {
        this.apkversion = apkversion;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}
