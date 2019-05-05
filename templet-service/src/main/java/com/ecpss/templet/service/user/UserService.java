package com.ecpss.templet.service.user;

import com.ecpss.templet.domain.user.TempletUser;

import java.util.List;

/**
 * Created by chenpang on 2018/5/16 20:41.
 */
public interface UserService {
    public void put(String userNo, String userName);

    public TempletUser getUserByUserNo(String userNo);

    public TempletUser getUserByUserName(String userName);

    public List<TempletUser> getUserList();
}
