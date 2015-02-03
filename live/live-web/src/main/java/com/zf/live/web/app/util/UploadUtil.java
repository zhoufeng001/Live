package com.zf.live.web.app.util;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zf.live.common.ZFSpringPropertyConfigure;
import com.zf.live.common.ftp.FtpUtil;
import com.zf.live.common.util.UUID;
import com.zf.live.web.WebConst;

/**
 * 
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2015年2月4日 上午2:36:12
 */
@Component("uploadUtil")
public class UploadUtil {
	
	@Autowired
	private ZFSpringPropertyConfigure propertyConfigure ;

	/**
	 * 上传用户头像
	 * @param img
	 * @return 返回保存图片地址
	 */
	public String uploadUserPhoto(InputStream img){
		String url = propertyConfigure.getProperties("ftp.url");
		String port = propertyConfigure.getProperties("ftp.port");
		String username = propertyConfigure.getProperties("ftp.username");
		String password = propertyConfigure.getProperties("ftp.password");
		String basePath = propertyConfigure.getProperties("ftp.basepath");
		String fileName = UUID.newUUID() + ".jpg";
		FtpUtil.uploadFile(url, Integer.parseInt(port), username, password, basePath, 
				WebConst.userPhotoDir, fileName, img) ;
		return WebConst.userPhotoDir + "/" + fileName ;
	}
	
}
