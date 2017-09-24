package com.hjw.home.weichat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.hjw.home.weichat.menu.Button;
import com.hjw.home.weichat.menu.ClickButton;
import com.hjw.home.weichat.menu.Menu;
import com.hjw.home.weichat.menu.ViewButton;

import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSONObject;

public class WeixinUtil {
	private final static String  APPID="wx2530db8b49887c00";
	
	private final static String APPSECRET="02fe308f61fbfeb4308c14c6b1aa8796";
	
	private String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private String CREATE_MENU="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public String doGet(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        JSONObject jsonobject=null;
        String result=null;
        
        	try {
				String requesturl=this.ACCESS_TOKEN_URL.replace("APPID", APPID);
				requesturl=requesturl.replace("APPSECRET", APPSECRET);
				//创建Get请求，
				HttpGet httpGet = new HttpGet(requesturl);
				//执行Get请求，
				response = httpClient.execute(httpGet);
				//得到响应体
				HttpEntity entity = response.getEntity();
				//获取响应内容
				responseContent  = EntityUtils.toString(entity,"UTF-8");
				//转换为map
				jsonobject=JSONObject.fromObject(responseContent);
				//转换为map
				AccessToken accesstoken=(AccessToken) JSONObject.toBean(jsonobject, AccessToken.class);
				result=accesstoken.getAccess_token();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        return result;
		
	}
	
	public String dopost(String url,String outstr){
		CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        JSONObject jsonobject=null;
        String result=null;
        
        	try {
        		
				//创建Get请求，
				HttpPost post=new HttpPost(url);
				post.setEntity(new StringEntity(outstr, "UTF-8"));
				//执行Get请求，
				response = httpClient.execute(post);
				//得到响应体
				HttpEntity entity = response.getEntity();
				//获取响应内容
				responseContent  = EntityUtils.toString(entity,"UTF-8");
				//转换为map
				jsonobject=JSONObject.fromObject(responseContent);
				AccessToken accesstoken=(AccessToken) JSONObject.toBean(jsonobject, AccessToken.class);
				result=accesstoken.getAccess_token();

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        return result;
		
	}
	
	public String initmenu(){
		Button button1=new Button();
		button1.setName("菜单1");
		ClickButton button11=new ClickButton();
		button11.setName("菜单11");
		button11.setType("click");
		button11.setKey("cd1111");
		ClickButton button12=new ClickButton();
		button12.setName("菜单12");
		button12.setType("click");
		button12.setKey("cd1212");
		button1.setSub_button(new Button[]{button11,button12});
		
		ClickButton button2=new ClickButton();
		button2.setName("菜单2");
		button2.setType("click");
		button2.setKey("cd222");
		
		ViewButton button3=new ViewButton();
		button3.setType("view");
		button3.setUrl("https://www.baidu.com/");
		button3.setName("菜单3");
		
		Menu menu=new Menu();
		menu.setButton(new Button[]{button1,button2,button3});
		String menue=JSONObject.fromObject(menu).toString();
		return menue;
		
	}
	public static void main(String[] args) {
		WeixinUtil weixinUtil=new WeixinUtil();
		String token="bf1SqK7tZ85naGdsHw2hWW0LyTSLptISDEcsZ3Eb9ELrFFWQ3ONgxvR1lCJ9LJ3cJWXqduxY4YxPDRSbG7exvZr7uU6Q-z5SscxCBVzYN0H-0vCxcT9FUzXa2iKeIjXCJZPhAHAEUK";
		String createmenue=weixinUtil.CREATE_MENU.replace("ACCESS_TOKEN", token);
		System.out.println(weixinUtil.dopost(createmenue, weixinUtil.initmenu()));
		
		
	}
	
			
			

}
