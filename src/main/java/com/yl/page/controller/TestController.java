package com.yl.page.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yl.page.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping("/item.action")
	@ResponseBody
	public List<Map<String, Object>> getItems(Integer page, Integer pageSize) {

		// PageInfo page = testService.getItems();
		// 参数：页码；当前页的数量
		List<Map<String, Object>> list = testService.getItems(page, pageSize);
		return list;
	}

}
