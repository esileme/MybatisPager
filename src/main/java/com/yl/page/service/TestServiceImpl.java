package com.yl.page.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.page.mapper.ItemMapper;
import com.yl.page.mapper.OrderMapper;

@Service
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private OrderMapper orderMapper;

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

	public List<Map<String, Object>> getOrdersByUserId(String userId) {

		PageHelper.startPage(1, 2);
		List<Map<String, Object>> list = orderMapper.getOrdersByUserId(userId);
		return list;

	}

	public List<Map<String, Object>> getAnotherOrdersByUserId(String userId) {
		PageHelper.startPage(1, 2);
		List<Map<String, Object>> orderMaps = orderMapper.getAnotherOrdersByUserId(userId);
		for (Map<String, Object> map : orderMaps) {
			String orderId = (String) map.get("orderId");
			List<Map<String, Object>> items = orderMapper.getItems(orderId);
			map.put("items", items);
		}
		PageInfo pageInfor = new PageInfo(orderMaps);
		System.out.println(pageInfor.getPageNum());
		System.out.println(pageInfor.getTotal());

		return orderMaps;
	}

}
