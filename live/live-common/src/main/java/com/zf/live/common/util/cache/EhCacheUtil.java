package com.zf.live.common.util.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.zf.live.common.assertx.ZFAssert;

/**
 * EHCache工具类
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月15日 上午1:15:46
 */
public class EhCacheUtil {
	
	private static Map<String, EhCacheUtil> cacheManagerMap = new HashMap<String, EhCacheUtil>();
	
	public static EhCacheUtil getInstance(String configurationFileName){
		synchronized (EhCacheUtil.class) {
			if(cacheManagerMap.containsKey(configurationFileName)){
				return cacheManagerMap.get(configurationFileName);
			}else{
				CacheManager cacheManager = CacheManager.create(EhCacheUtil.class.getClassLoader()
						.getResource(configurationFileName)) ;
				EhCacheUtil ehCacheUtil = new EhCacheUtil(cacheManager , configurationFileName);
				cacheManagerMap.put(configurationFileName, ehCacheUtil);
				return ehCacheUtil ;
			}
		}
	}
	
	private String configurationFileName ;
	
	private  CacheManager cacheManager; 
	
	private EhCacheUtil(CacheManager cacheManager , String configurationFileName){
		this.cacheManager = cacheManager ;
		this.configurationFileName = configurationFileName ;
	}
	
	/**
	 * 存对象到EHCache缓存中
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void put(String cacheName , Object key , Object value){
		ZFAssert.notNull(cacheName, "cacheName不允许为空");
		ZFAssert.notNull(key, "key不允许为空");
		ZFAssert.notBlank(value, "value不允许为空"); 
		Cache cache = cacheManager.getCache(cacheName);
		ZFAssert.notBlank(cache, "cacheName:" + cacheName + "未找到，请到"+ configurationFileName +"中定义");
		Element element = new Element(key, value) ;
		cache.put(element); 
	}
	
	
	/**
	 * 从EHCache缓存中取对象
	 * @param cacheName
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName , Object key , Class<T> t){
		ZFAssert.notNull(cacheName, "cacheName不允许为空");
		ZFAssert.notNull(key, "key不允许为空");
		Cache cache = cacheManager.getCache(cacheName);
		ZFAssert.notBlank(cache, "cacheName:" + cacheName + "未找到，请到"+ configurationFileName +"中定义");
		Element element = cache.get(key);
		if(element == null){
			return null ;
		}
		return (T)element.getObjectValue();
	}
	
	/**
	 * 获取所有的Key
	 * @param cacheName
	 * @return
	 */
	public List<?> getAllKeys(String cacheName){
		ZFAssert.notNull(cacheName, "cacheName不允许为空");
		Cache cache = cacheManager.getCache(cacheName);
		ZFAssert.notBlank(cache, "cacheName:" + cacheName + "未找到，请到"+ configurationFileName +"中定义");
		return cache.getKeys();
	}
	
	/**
	 * 根据key移除指定的对象
	 * @param cacheName
	 * @param key
	 */
	public boolean remove(String cacheName , Object key){
		ZFAssert.notNull(cacheName, "cacheName不允许为空");
		ZFAssert.notNull(key, "key不允许为空");
		Cache cache = cacheManager.getCache(cacheName);
		ZFAssert.notBlank(cache, "cacheName:" + cacheName + "未找到，请到"+ configurationFileName +"中定义");
		return cache.remove(key);
	}
	
	public static void main(String[] args) {
		
		EhCacheUtil ehCacheUtil = EhCacheUtil.getInstance("ehcache.xml");
		
		ehCacheUtil.put("categoryRecommendTopVideoCache", "aa", "123");
		
		String value = ehCacheUtil.get("categoryRecommendTopVideoCache", "aa", String.class) ;
		
		System.out.println(value);
	}
	
}
