package com.magic.magicprojectbaseapp.mvp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by sdyy on 2017/11/15.
 */
@Entity
public class FileInfo {
    @Id
    private Long id;
    //文件地址
    private String fileUrl;
    //md5标识
    private String fileMd5;
    //上传的文件名称
    private String fileName;
    //状态
    private int fileState;
    //文件大小
    private long fileLength;
    //文件进度
    private long fileProgress;


    //上传的用户名
    private String userName;
    //上传的用户id
    private long userId;
    //上传的文件类型
    private String fileStyle;
    //创建时间
    private String dateTime;

    public FileInfo(int i, String absolutePath, String name, long length, int i1, String fileMD5String) {

    }


    @Generated(hash = 341126374)
    public FileInfo(Long id, String fileUrl, String fileMd5, String fileName, int fileState, long fileLength,
                    long fileProgress, String userName, long userId, String fileStyle, String dateTime) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.fileMd5 = fileMd5;
        this.fileName = fileName;
        this.fileState = fileState;
        this.fileLength = fileLength;
        this.fileProgress = fileProgress;
        this.userName = userName;
        this.userId = userId;
        this.fileStyle = fileStyle;
        this.dateTime = dateTime;
    }


    @Generated(hash = 1367591352)
    public FileInfo() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileState() {
        return fileState;
    }

    public void setFileState(int fileState) {
        this.fileState = fileState;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public long getFileProgress() {
        return fileProgress;
    }

    public void setFileProgress(long fileProgress) {
        this.fileProgress = fileProgress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFileStyle() {
        return fileStyle;
    }

    public void setFileStyle(String fileStyle) {
        this.fileStyle = fileStyle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
