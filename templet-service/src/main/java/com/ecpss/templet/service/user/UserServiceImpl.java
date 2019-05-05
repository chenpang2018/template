package com.ecpss.templet.service.user;

import com.ecpss.templet.domain.user.TempletUser;
import com.ecpss.templet.service.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenpang on 2018/5/16 20:43.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void put(String userNo, String userName) {
        TempletUser templetUser = new TempletUser(userNo, userName);

        userRepository.put(templetUser);
    }
    @Override
    public TempletUser getUserByUserNo(String userNo) {
        return userRepository.getUserByUserNo(userNo);
    }
    @Override
    public TempletUser getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }


    @Override
    public List<TempletUser> getUserList() {
        return userRepository.getUserList();
    }
}
