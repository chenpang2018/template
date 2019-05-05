package com.ecpss.templet.infrastructure.rptimpl.hibernate;

import com.ecpss.templet.domain.model.BaseEntityDomain;
import com.ecpss.templet.domain.util.ClassUtils;
import com.ecpss.templet.domain.util.DBLockMode;
import com.ecpss.templet.domain.util.Pager;
import com.ecpss.templet.infrastructure.rptimpl.base.BaseRpt;
import com.ecpss.templet.infrastructure.util.PagerUtil;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lindongcheng
 */
@SuppressWarnings("rawtypes")
public class BaseRptImpl<Domain extends BaseEntityDomain> implements BaseRpt<Domain> {

    private HibernateTemplate hibernateTemplate;

    private Class domainClass;

    @Override
    public Domain getById(Long id) {
        return getById(id, DBLockMode.NONE);
    }

    @SuppressWarnings("unchecked")
	@Override
    public Domain getById(Long id, DBLockMode dbLockMode) {
        if (id == null) {
            return null;
        }

        LockMode lockMode = convert(dbLockMode);
        return (Domain) hibernateTemplate.get(getDomainClass(), id, lockMode);
    }

    public static LockMode convert(DBLockMode lockMode) {
        switch (lockMode) {
            case NONE:
                return LockMode.NONE;
            case WRITE:
                return LockMode.PESSIMISTIC_WRITE;
            case WRITE_NOWAIT:
                return LockMode.UPGRADE_NOWAIT;
            case READ_ONLY:
                return LockMode.READ;
            default:
                throw new RuntimeException("预期之外的数据库锁类型[" + lockMode + "]");
        }
    }

    @Override
    public void saveOrUpdate(Object domain) {
        hibernateTemplate.saveOrUpdate(domain);
    }



    private Class getDomainClass() {
        if (domainClass == null) {
            domainClass = ClassUtils.getGenericType(getClass(), 0);
        }
        return domainClass;
    }

    /**
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * @param hibernateTemplate the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void saveOrUpdateList(List<Domain> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        for (Domain domain : list) {
            saveOrUpdate(domain);
        }
    }

    @Override
    public List<Domain> getList(final Long[] ids) {
        return getList(ids, DBLockMode.NONE);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	@Override
    public List<Domain> getList(final Long[] ids, final DBLockMode lockMode) {

        return (List<Domain>) getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from " + getDomainClassName() + " where id in (:ids)");
                query.setParameterList("ids", ids);
                query.setLockMode("this", convert(lockMode));
                return query.list();
            }
        });
    }

    protected String getDomainClassName() {
        return getDomainClass().getName();
    }


    @Override
    public List<Object> getListBySql(String sql, Map keyAndParm) {
        return getHibernateTemplate().execute((HibernateCallback<List>) session -> {
            Query query = session.createSQLQuery(sql);
            query = setParamList(query, keyAndParm); //filter query
            //parms.keySet().stream().forEach(k -> query.setParameter(k, parms.get(k)));
            return query.list();
        });
    }

    @Override
    public <T> T getById(Class<T> cla, Long id) {
        return hibernateTemplate.get(cla, id);
    }


    /**
     * filter query的setParamList
     *
     * @param query
     * @param map
     * @return
     */
    public Query setParamList(Query query, Map<String, Object> map) {
        if (map != null) {
            Set<String> keySet = map.keySet();
            for (String string : keySet) {
                Object obj = map.get(string);
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (obj instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    query.setParameterList(string, (Object[]) obj);
                } else {
                    query.setParameter(string, obj);
                }
            }
        }
        return query;
    }

    public Object uniqueResult(String hql, Object... values) {
        return hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                return query.uniqueResult();
            }
        });
    }

    /**
     * 普通分页
     *
     * @param hql
     * @param keyAndParm
     * @param pager
     * @return
     */
    public Pager getPager(String hql, Map keyAndParm, Pager pager) {

        return getPagerWithResultTransformer(hql, keyAndParm, pager, null);
    }
    /**
     * 带ResultTransformer的分页
     *
     * @param hql
     * @param keyAndParm
     * @param pager
     * @param resultTransformer
     * @return
     */
    public Pager getPagerWithResultTransformer(String hql, Map keyAndParm, Pager pager, ResultTransformer resultTransformer) {
        String countHql = PagerUtil.getTotalNumHQL(hql);
        pager.setTotal(Integer.valueOf(getPageTotal(countHql, keyAndParm)));
        List records = getByHqlAndParmAndTransformer(hql, keyAndParm,
                pager.getBeginCountIndex(), pager.getLimit(), resultTransformer);
        pager.setList(records);
        pager.setPages(pager.getTotal() / pager.getLimit() + (pager.getTotal() % pager.getLimit() == 0 ? 0 : 1));
        return pager;
    }
    /**
     * 获取分页total
     *
     * @param countHql
     * @param keyAndParm
     * @return
     */
    public String getPageTotal(String countHql, Map<String, Object> keyAndParm) {
        return getHibernateTemplate().execute(session -> {
            Query query = session.createQuery(countHql);
            query = setParamList(query, keyAndParm); //filter query
            //keyAndParm.keySet().stream().forEach(k -> query.setParameter(k, keyAndParm.get(k)));
            return query.uniqueResult().toString();
        });
    }
    /**
     * 带ResultTransformer的分页list
     *
     * @param hql
     * @param parms
     * @param begin
     * @param max
     * @param transformer
     * @return
     */
    public List getByHqlAndParmAndTransformer(String hql, Map<String, Object> parms, int begin, int max, ResultTransformer transformer) {
        return getHibernateTemplate().execute((HibernateCallback<List>) session -> {
            Query query = session.createQuery(hql);
            query = setParamList(query, parms); //filter query
            //parms.keySet().stream().forEach(k -> query.setParameter(k, parms.get(k)));
            query.setMaxResults(max);  //查询多少条
            query.setFirstResult(begin); //开始记录
            if (transformer != null) {
                query.setResultTransformer(transformer);
            }
            return query.list();
        });
    }
}
