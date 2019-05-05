package com.ecpss.templet.infrastructure.rptimpl.base;


import com.ecpss.templet.domain.model.BaseEntityDomain;
import com.ecpss.templet.domain.util.DBLockMode;
import com.ecpss.templet.domain.util.Pager;
import org.hibernate.transform.ResultTransformer;

import java.util.List;
import java.util.Map;


/**
 * @author lindongcheng
 */
public interface BaseRpt<Domain extends BaseEntityDomain> {

    Domain getById(Long id);

    Domain getById(Long id, DBLockMode lockMode);

    <T> T getById(Class<T> cla, Long id);

    List<Domain> getList(Long[] ids);

    List<Domain> getList(Long[] ids, DBLockMode lockMode);

    void saveOrUpdate(Object domain);

    void saveOrUpdateList(List<Domain> domain);

    List<Object> getListBySql(String sql, Map keyAndParm);

    public Object uniqueResult(String hql, Object... values);

    public Pager getPager(String hql, Map keyAndParm, Pager pager);

    public Pager getPagerWithResultTransformer(String hql, Map keyAndParm, Pager pager, ResultTransformer resultTransformer);

    public String getPageTotal(String countHql, Map<String, Object> keyAndParm);

    public List getByHqlAndParmAndTransformer(String hql, Map<String, Object> parms, int begin, int max, ResultTransformer transformer);
}