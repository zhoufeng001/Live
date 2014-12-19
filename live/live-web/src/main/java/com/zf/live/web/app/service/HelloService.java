package com.zf.live.web.app.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	public HelloService() {
		System.out.println("HelloService......");
	}
	
	public String sayHello(){
		return "hello";
	}

}
