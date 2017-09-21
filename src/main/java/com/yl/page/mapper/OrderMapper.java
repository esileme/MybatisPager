package com.yl.page.mapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;


public interface OrderMapper {

	List<Map<String, Object>> getOrdersByUserId(@Param("userId") String userId);
	
	List<Map<String, Object>> getAnotherOrdersByUserId(String userId);
	
	List<Map<String, Object>> getItems(@Param("orderId") String orderId);
	
	
}
