package com.shop.categorySecond.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.shop.categorySecond.dao.CategorySecondDao;
import com.shop.categorySecond.entity.CategorySecond;
import com.shop.utils.PageHibernateCallback;

@Component("categorySecondDao")
@SuppressWarnings("unchecked")
public class CategorySecondDaoImpl implements CategorySecondDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by cs_id desc";
		List<CategorySecond> list = hibernateTemplate
				.execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
