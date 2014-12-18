package com.zf.live.common.hessian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.zf.live.common.ZFSpringPropertyConfigure;

/**
 * 代理Hessain客户端
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月15日 上午12:16:54
 */
public class ZFHessianProxyFactory extends HessianProxyFactoryBean {
    
    @Autowired
    private ZFSpringPropertyConfigure propertyConfigure ;
    
    /**
     * Remote Service URL Key
     */
    private final String REMOTE_SERVICE_URL_KEY = "rmiservice.url" ;
    
    private String serviceName ;
    
    public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
	String baseUrl = propertyConfigure.getProperties(REMOTE_SERVICE_URL_KEY) ;
	if(baseUrl == null){
	    throw new IllegalArgumentException("properties key{"+ REMOTE_SERVICE_URL_KEY + "}未设置");
	}
	super.setServiceUrl(baseUrl + serviceName);
    }

    public String getServiceName() {
        return serviceName;
    }
    
    
}
