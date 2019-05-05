package com.ecpss.templet.infrastructure.rptimpl.base;

import com.ecpss.templet.domain.model.BaseEntityDomain;
import com.ecpss.templet.infrastructure.rptimpl.hibernate.BaseRptImpl;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.annotation.Resource;

/**
 * 仓储默认基类
 * 
 * @author sunxy 2015年9月28日 下午2:38:16	
 * @since 1.0
 */
public class BeautifulBaseRptImpl<Domain extends BaseEntityDomain> extends BaseRptImpl<Domain> {
	
	@Override
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        super.setHibernateTemplate(hibernateTemplate);
    }
	
}
