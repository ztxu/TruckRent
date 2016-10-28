package edu.xmu.software.truckrent.module;

/**
 * Created by DELL on 2016/10/21.
 */

public class ChatRecord {
    private long id;
    private String chattingFre;//聊天对象
    private int imagUrl;//聊天对象头像
    private String lastRecord;
    private String lastTime;

    public ChatRecord() {
    }

    public ChatRecord(long id, String chattingFre, int imagUrl, String lastRecord, String lastTime) {
        this.id = id;
        this.chattingFre = chattingFre;
        this.imagUrl = imagUrl;
        this.lastRecord = lastRecord;
        this.lastTime = lastTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChattingFre() {
        return chattingFre;
    }

    public void setChattingFre(String chattingFre) {
        this.chattingFre = chattingFre;
    }

    public int getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(int imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getLastRecord() {
        return lastRecord;
    }

    public void setLastRecord(String lastRecord) {
        this.lastRecord = lastRecord;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
