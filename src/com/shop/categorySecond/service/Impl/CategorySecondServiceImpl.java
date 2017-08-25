package com.shop.categorySecond.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.categorySecond.dao.CategorySecondDao;
import com.shop.categorySecond.entity.CategorySecond;
import com.shop.categorySecond.service.CategorySecondService;
import com.shop.utils.PageBean;

@Component("categorySecondService")
@Transactional
public class CategorySecondServiceImpl implements CategorySecondService{
	
	@Resource(name="categorySecondDao")
	private CategorySecondDao categorySecondDao;

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ��¼��
		int limit = 10;
		pageBean.setEachPageCount(limit);
		//�����ܼ�¼��
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿҳ��ʾ���ݼ���
		int begin = (page - 1)*limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public CategorySecond findByCsid(Integer cs_id) {
		return categorySecondDao.findByCsid(cs_id);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
}
