package com.zf.live.web.app.util;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.util.Base64Utils;

/**
 * Des加密工厂
 * @author is_zhoufeng@163.com , QQ:243970446
 * 2014年12月26日 上午1:25:37
 */
public class DesSecureFactory {

	private final static String DES = "DES";

	/**
	 * 根据密钥key获取一个加密工具类
	 * @param key 密钥
	 * @return
	 */
	public static DesSecure newInstance(String key){
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(dks);

			SecureRandom sr = new SecureRandom();
			Cipher encrypt = Cipher.getInstance(DES);
			encrypt.init(Cipher.ENCRYPT_MODE, securekey, sr);

			Cipher decrypt = Cipher.getInstance(DES);
			// 用密钥初始化Cipher对象
			decrypt.init(Cipher.DECRYPT_MODE, securekey, sr);

			return new DesSecure(encrypt , decrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}


	public static class DesSecure{

		private Cipher encrypt ;
		private Cipher decrypt ;

		public DesSecure(Cipher encrypt, Cipher decrypt) {
			this.encrypt = encrypt;
			this.decrypt = decrypt;
		}

		/**
		 * 解密
		 * @param source
		 * @return
		 */
		public String decrypt(String source){
			byte[] bytes = null;
			try {
				byte[] sourceBytes = Base64Utils.decodeFromString(source);
				bytes = decrypt.doFinal(sourceBytes);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
				return null ;
			}
			return new String(bytes); 
		}

		/**
		 * 加密
		 * @param source
		 * @return
		 */
		public String encrypt(String source){
			byte[] result = null;
			try {
				result = encrypt.doFinal(source.getBytes());
				return Base64Utils.encodeToString(result);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			return null ;
		}

	}

}
