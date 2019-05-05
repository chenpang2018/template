package com.ecpss.templet.domain.user;

import com.ecpss.templet.domain.model.BaseEntityDomain;

/**
 * Created by chenpang on 2018/5/15 21:11.
 */
public class TempletUser extends BaseEntityDomain{
    private String userNo;
    private String userName;

    public TempletUser() {
    }

    public TempletUser(String userNo, String userName) {
        this.userNo = userNo;
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public String getUserName() {
        return userName;
    }
}
