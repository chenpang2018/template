package com.ecpss.templet.infrastructure.rptimpl.user;

import com.ecpss.templet.domain.user.TempletUser;
import com.ecpss.templet.infrastructure.rptimpl.base.BeautifulBaseRptImpl;
import com.ecpss.templet.service.user.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenpang on 2018/5/16 21:00.
 */
@Transactional
@Repository
public class UserRptImpl extends BeautifulBaseRptImpl<TempletUser> implements UserRepository {
    @Override
    public void put(TempletUser templetUser) {
        saveOrUpdate(templetUser);
    }
    @Override
    public TempletUser getUserByUserNo(String userNo) {
        String hql = "from TempletUser u where u.userNo = ?";
        return (TempletUser) getHibernateTemplate().find(hql, userNo).stream().findAny().orElse(null);
    }
    @Override
    public TempletUser getUserByUserName(String userName) {
        String hql = "from TempletUser u where u.userName = ?";
        return (TempletUser) getHibernateTemplate().find(hql, userName).stream().findAny().orElse(null);
    }


    @Override
    public List<TempletUser> getUserList() {
        String hql = "from TempletUser u order by u.createTime desc ";
        return (List<TempletUser>) getHibernateTemplate().find(hql);
    }

}
