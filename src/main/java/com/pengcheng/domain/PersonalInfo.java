package com.pengcheng.domain;

//个人信息
public class PersonalInfo {

    public String nickname;
    public String username;
    public String type;     //普通用户、月卡会员、季卡会员、年卡会员
    public Integer bannedCount;     //被封次数
    public Integer watchCount;      //剩余查看次数
    public String vipExpireTime;    //会员到期时间

    public String getVipExpireTime() {
        return vipExpireTime;
    }

    public void setVipExpireTime(String vipExpireTime) {
        this.vipExpireTime = vipExpireTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBannedCount() {
        return bannedCount;
    }

    public void setBannedCount(Integer bannedCount) {
        this.bannedCount = bannedCount;
    }

    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }
}
