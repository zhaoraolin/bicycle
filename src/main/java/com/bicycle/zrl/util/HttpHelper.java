package com.bicycle.zrl.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

/**
* @ClassName: HttpHelp
* @Description: TODO(HTTP相关工具方法)
* @author zrl
* @date 2018年4月28日 下午2:51:38
* 
*/
public final class HttpHelper {
  
  private HttpHelper() {
  }
  /**解析查询字符串为 map */
  public static Map<String, Object> paramRequest(HttpServletRequest request) {
    Map<String, Object> map = new HashMap<>();
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
     String name = (String) names.nextElement();
     String value = request.getParameter(name);
     if (value.startsWith("[")) {
     List<String> arrValue = JSON.parseArray(value, String.class);
      map.put(name, arrValue);
    }
     map.put(name, value);
   }
    return map;
  }
  
  public static Map<String,Object> convertMap(Map<String,String> map){
    HashMap<String, Object> result = new HashMap<>();
    result.putAll(map);
    return result;
    
  }
}
