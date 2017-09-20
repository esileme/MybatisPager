package com.yl.page.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.page.mapper.ItemMapper;

@Service
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private ItemMapper itemMapper;

	/*
	 * public PageInfo<Map<String, Object>> getItems() { List<Map<String,
	 * Object>> list = itemMapper.getItems(); return new
	 * PageInfo<Map<String,Object>>(list); }
	 */

	public List<Map<String, Object>> getItems(Integer page, Integer pageSize) {

		PageHelper.startPage(page, pageSize);
		List<Map<String, Object>> list = itemMapper.getItems();
		PageInfo<Map<String, Object>> pageInfor = new PageInfo<Map<String, Object>>(list);
		System.out.println(pageInfor.getPageNum());
		System.out.println(pageInfor.getTotal());
		System.out.println(pageInfor.getPages());

		return list;
	}

}
