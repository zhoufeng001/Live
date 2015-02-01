package com.zf.live.web.app.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月1日 下午10:58:55
 */
public class FreemarkerUtil {

	private final static BeansWrapper wrapper = BeansWrapper.getDefaultInstance();  
	private final static TemplateHashModel staticModels = wrapper.getStaticModels();
	
	public static TemplateHashModel useStaticPacker(Class<?> clazz) {  
	    try {  
	        return (TemplateHashModel) staticModels.get(clazz.getName());  
	    } catch (TemplateModelException e) {  
	        throw new RuntimeException(e);  
	    }  
	};  
	
}
