package com.zf.live.service.impl.room.filter;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zf.live.client.vo.room.Audience;

/**
 * 游客过滤器
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年1月24日 上午1:17:25
 */
@Component("touristFilter")
public class TouristFilter implements AudienceFilter{

	@Override 
	public void filter(List<Audience> audiences) {
		if(audiences == null || audiences.size() <= 0){
			return  ;
		}
		Iterator<Audience> it = audiences.iterator();
		while(it.hasNext()){
			Audience audience = it.next();
			if(audience.isTourist()){
				it.remove(); 
			}
		}
	}

}
