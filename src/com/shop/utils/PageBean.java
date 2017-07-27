package com.shop.utils;

import java.util.List;

public class PageBean<T> {
	private int page;	//��ǰҳ��
	private int totalCount;	//�ܼ�¼��
	private int totalPage;	//��ҳ��
	private int eachPageCount;	//ÿҳ��ʾ�ļ�¼��
	private List<T> list;	//ÿҳ��ʾ���ݵļ���
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getEachPageCount() {
		return eachPageCount;
	}
	public void setEachPageCount(int eachPageCount) {
		this.eachPageCount = eachPageCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
