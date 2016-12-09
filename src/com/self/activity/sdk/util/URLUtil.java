package com.self.activity.sdk.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import com.self.activity.sdk.bean.PageBean;

public class URLUtil {
	public static synchronized String toUrl(Object obj,PageBean page){
		String url="?1=1";
		if(obj instanceof Map){
			@SuppressWarnings("unchecked")
			Map<String,Object> map=(Map<String,Object>)obj;
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				Object object = map.get(key);
				if(object!=null){
					url+="&"+key+"="+object;
				}
			}
		}else{
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object object = field.get(obj);
					if(object!=null){
						url+="&"+field.getName()+"="+object;
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					continue;
				}finally {
					field.setAccessible(false);
				}
			}
		}
		if(page!=null){
			if(page.getNumber()!=null){
				url+="&number="+page.getNumber();
			}
			if(page.getSize()!=null){
				url+="&size="+page.getSize();
			}
			if(page.getOffset()!=null){
				url+="&offset="+page.getOffset();
			}
			if(page.getTotalElements()!=null){
				url+="&totalElements="+page.getTotalElements();
			}
			if(page.getTotalPages()!=null){
				url+="&totalPages="+page.getTotalPages();
			}
		}
		return url;
	}
}
