package com.zf.live.web.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code",""));
		System.out.println(oauth.authorize("code",""));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try{
			AccessToken accessToken = oauth.getAccessTokenByCode(code) ;
			System.out.println(accessToken);
			
			Users um = new Users();
			um.setToken(accessToken.getAccessToken());
			User user = um.showUserById(accessToken.getUid());
			System.out.println("user:" + user);
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
	}
}
