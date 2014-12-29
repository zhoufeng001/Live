package com.zf.live.web.app.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(HelloService.class);


	public HelloService() {
		log.info("HelloService......");
	}

	public String sayHello(){
		return "hello";
	}

}
