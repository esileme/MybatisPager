package com.yl.page.intercpter;

import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts(

@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }))
public class CameHumpIntercpter implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		List<Object> values = (List<Object>) invocation.proceed();
		for (Object object : values) {

			if (object instanceof Map) {
				processMap((Map) object);

			} else {
				break;
			}
		}
		return values;
	}

	public Object plugin(Object target) {

		return Plugin.wrap(target, this);
	}

	// 获取配置参数
	public void setProperties(Properties properties) {

	}

	private void processMap(Map<String, Object> map) {
		Set<String> keySet = new HashSet<String>(map.keySet());
		for (String key : keySet) {

			if ((key.charAt(0) >= 'A' && key.charAt(0) <= 'Z') || key.indexOf("_") >= 0) {

				Object value = map.get(key);
				map.remove(key);
				map.put(undelineToNomall(key), value);
			}
		}

	}

	public String undelineToNomall(String inputString) {
		StringBuilder buder = new StringBuilder();
		boolean nextUpperCase = false;
		for (int i = 0; i < inputString.length(); i++) {

			char c = inputString.charAt(i);
			if (c == '_') {
				if (buder.length() > 0) {
					nextUpperCase = true;
				}

			} else {

				if (nextUpperCase) {
					buder.append(Character.toUpperCase(c));

					nextUpperCase = false;
				} else {
					buder.append(Character.toLowerCase(c));
				}

			}

		}

		return buder.toString();
	}

}
