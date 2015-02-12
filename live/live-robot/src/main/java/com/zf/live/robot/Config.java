package com.zf.live.robot;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

public class Config {

	static final Logger log = org.slf4j.LoggerFactory.getLogger(Config.class);

	private Properties properties ;

	private static Config instance ;

	private Config(){};

	public static Config getInstance(){
		synchronized(Config.class){ 
			if(instance == null){
				instance = new Config() ;
				instance.loadProperties();
			}
		}
		return instance ;
	}

	private void loadProperties(){
		properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream("/app-env.properties"));
		} catch (IOException e) {
			log.error(e.getMessage() , e);
			return ;
		}
	}
	
	public String getProperty(String key){
		if(properties == null){
			return null ;
		}
		return properties.getProperty(key) ;
	}

}
