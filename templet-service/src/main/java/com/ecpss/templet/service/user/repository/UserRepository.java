package com.ecpss.templet.service.user.repository;

import com.ecpss.templet.domain.user.TempletUser;

import java.util.List;

/**
 * Created by chenpang on 2018/5/16 20:45.
 */
public interface UserRepository {
    public void put(TempletUser templetUser);

    public TempletUser getUserByUserNo(String userNo);

    public TempletUser getUserByUserName(String userName);

    public List<TempletUser> getUserList();
}
