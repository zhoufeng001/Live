package com.zf.live.common.assertx;

import com.zf.live.client.exception.ValidateException;
import com.zf.live.common.util.ZFReflectionUtils;


/**
 * 校验工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月23日 下午5:43:59
 */
public abstract class ZFAssert {

	/**
	 * 判断target对象是否为空,如果不为空，则抛出异常
	 * @param target
	 * @param message
	 */
	public static void isNull(Object target , String message){
		if(target != null){
			if(message == null){
				message = "[校验失败] 对象必须为空" ;
			}
			throw new ValidateException(message);
		}
	}

	
	/**
	 * 判断target对象是否不为空，如果对象为空，则抛出异常
	 * @param target
	 * @param message
	 */
	public static void notNull(Object target , String message){
		if(target == null){
			if(message == null){
				message = "[校验失败] 对象不能为空";
			}
			throw new ValidateException(message); 
		}
	}
	
	/**
	 * 校验target对象的属性field的值是否不为空，如果为空则抛出异常
	 * @param target
	 * @param field
	 * @param message
	 */
	public static void fieldNotNull(Object target, String field , String message) {
		Object value = ZFReflectionUtils.getFiledValue(target, field) ;
		if(value == null){
			if(message == null){
				message = "[校验失败] 字段" + field + "的值不能为空";
			}
			throw new ValidateException(message) ;
		}
	}
	
	/**
	 * 校验target对象的field字段的值是否为空，如果不为空则抛出异常
	 * @param target
	 * @param field
	 * @param message
	 */
	public static void fieldIsNull(Object target , String field , String message){
		Object value = ZFReflectionUtils.getFiledValue(target, field);
		if(value != null){
			if(message == null){
				message = "[校验失败] 字段" + field + "的值必须为空";
			}
			throw new ValidateException(message) ;
		}
	}
	
	
	/**
	 * 判断指定对象是否是数字类型，如果不是数字类型就会抛出异常
	 * @param target
	 * @param message
	 */
	public static void isInteger(Object target , String message){
		if(target == null){
			return ;
		}
		try {
			Integer.valueOf(target.toString());
		} catch (NumberFormatException e) {
			if(message == null){
				message = "对象必须为数字格式" ;
			}
			throw new ValidateException(message);
		}
	}
	
	/**
	 * 判断target对象的field字段是否是数字类型，如果不是数字类型就抛出异常
	 * @param target
	 * @param field
	 * @param message
	 */
	public static void fieldIsInteger(Object target , String field ,String message){
		Object value = ZFReflectionUtils.getFiledValue(target, field); 
		if(value == null){
			return ;
		}
		try {
			Integer.valueOf(value.toString());
		} catch (NumberFormatException e) {
			if(message == null){
				message = "[校验失败] 字段" + field + "不是数字格式"; 
			}
			throw new ValidateException(message);
		}
	}
	
	
	/**
	 * 判断target对象（toString）的值是否是regex格式的，如果不是，那么就抛出异常
	 * @param target
	 * @param regexp
	 * @param message
	 */
	public static void isRegex(Object target , String regex , String message){
		if(target == null){
			return ;
		}
		if(!target.toString().matches(regex)){
			if(message == null){
				message = "[校验失败] 对象不能为空" ;
			}
			throw new ValidateException(message);
		}
	}
	
	/**
	 * 判断targe对象的field字段(toStirng)的值是regex格式，如果不是，那么就抛出异常
	 * @param target
	 * @param param
	 * @param regex
	 * @param message
	 */
	public static void fieldIsRegex(Object target , String field , String regex , String message){
		Object value = ZFReflectionUtils.getFiledValue(target, field) ;
		if(value == null){
			return ;
		}
		if(!value.toString().matches(regex)){
			if(message == null){
				message = "[校验失败] 字段" + field + "不能为空";
			}
			throw new ValidateException(message);
		}
	}
	
	
	/**
	 * 校验target是否与value相等，如果不相等，则抛出异常
	 * @param target
	 * @param value
	 * @param message
	 */
	public static void equals(Object target , Object equalsValue , String message){
		if(target == null){
			return ;
		}
		if(!target.equals(equalsValue)){
			if(message == null){
				message = "对象的值不符合要求";
			}
			throw new ValidateException(message);
		}
	}
	
	/**
	 * 校验target对象的field字段是否与给定的value相等，如果不想等，则抛出异常
	 * @param target
	 * @param value
	 * @param field
	 * @param message
	 */
	public static void fieldEquals(Object target , Object equalsValue , String field , String message){
		Object value = ZFReflectionUtils.getFiledValue(target, field) ;
		if(value == null){
			return ;
		}
		if(!value.equals(equalsValue)){
			if(message == null){
				message = "[校验失败] 字段" + field + "的值不符合要求" ;
			}
			throw new ValidateException(message);
		}
	}
	
	/**
	 * 校验target的(toString)值是否不为空字符串，如果为空字符串，则抛出异常
	 * @param target
	 * @param message
	 */
	public static void notBlank(Object target , String message){
		if(message == null){
			message = "[校验失败] 对象不能为空值" ;
		}
		if(target == null){
			throw new ValidateException(message);
		}
		if(target.toString().trim().equals("")){
			throw new ValidateException(message);
		}
	}
	
	/**
	 * 校验target对象的field字段的(toString)值是否为空字符串，如果为空字符串，则抛出异常
	 * @param target
	 * @param field
	 * @param message
	 */
	public static void fieldNotBlank(Object target ,String field , String message){
		if(message == null){
			message = "[校验失败] 字段" + field + "不能为控制";
		}
		Object value = ZFReflectionUtils.getFiledValue(target, field) ;
		if(value == null){
			throw new ValidateException(message);
		}
		if(value.toString().trim().equals(message)){
			throw new ValidateException(message);
		}
	}

}
