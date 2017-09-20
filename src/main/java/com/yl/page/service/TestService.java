package com.yl.page.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface TestService {

	//PageInfo<Map<String, Object>> getItems();
	List<Map<String, Object>> getItems(Integer page,Integer pageSize);
}
