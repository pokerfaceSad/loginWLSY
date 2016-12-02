package pokerface.Sad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class LoginWLSY {
	private static HttpResponse response = null;  
	private static CloseableHttpClient closeableHttpClient = null; 
	public static void main(String args[]) {  
		if (postLogin())   
		{
			   System.out.println("登录成功");
			   String redirectLocation = getRedirectLocation(); 
//			   getText("http://wlsy.xidian.edu.cn"+redirectLocation);
			   String html = getText("http://wlsy.xidian.edu.cn/phyews/student/select.aspx");
//			   System.out.println(html);
//			   PatternUtil.getSelected(html);
			   //获取实验信息并封装为Experiment类对象流输出
			   JsoupUtil.getMsg(html);
		} 
		else{
			System.out.println("登录失败");
		}
	   }

	private static boolean postLogin() {
		   HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
	       closeableHttpClient = httpClientBuilder.build();  
	       
	       HttpPost httpPost = new HttpPost("http://wlsy.xidian.edu.cn/PhyEws/default.aspx?ReturnUrl=%2fPhyEws%2fstudent%2fstudent.aspx");  
	       List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	       Properties pro = new Properties();
	       try {
			pro.load(new FileInputStream("login.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	       formparams.add(new BasicNameValuePair("__EVENTARGUMENT", pro.getProperty("__EVENTARGUMENT")));  
	       formparams.add(new BasicNameValuePair("__EVENTTARGET", pro.getProperty("__EVENTTARGET")));  
	       formparams.add(new BasicNameValuePair("__VIEWSTATE", pro.getProperty("__VIEWSTATE")));  
	       formparams.add(new BasicNameValuePair("__EVENTVALIDATION", pro.getProperty("__EVENTVALIDATION")));  
	       formparams.add(new BasicNameValuePair("login1$btnLogin.y", pro.getProperty("login1$btnLogin.y")));  
	       formparams.add(new BasicNameValuePair("login1$btnLogin.x", pro.getProperty("login1$btnLogin.x")));  
	       formparams.add(new BasicNameValuePair("login1$StuPassword", pro.getProperty("login1$StuPassword")));  
	       formparams.add(new BasicNameValuePair("login1$StuLoginID", pro.getProperty("login1$StuLoginID")));  
	       formparams.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", pro.getProperty("__VIEWSTATEGENERATOR")));  
	       formparams.add(new BasicNameValuePair("login1$UserRole", pro.getProperty("login1$UserRole")));  
	  
	       UrlEncodedFormEntity entity = null;  
	       try {  
	           entity = new UrlEncodedFormEntity(formparams, "UTF-8");  
	           httpPost.setEntity(entity);  
	  
	           response = closeableHttpClient.execute(httpPost);  
	           
	           //getEntity()  
	           HttpEntity httpEntity = response.getEntity();  
	           if(response.getStatusLine().getStatusCode()==302)
	        	   return true;
	           else
	        	   return false;
	       } catch (Exception e) {  
	           e.printStackTrace();  
	           return false;
	       }finally{
	    	   httpPost.abort();
	       }
	}  
    private static String getRedirectLocation() {  
        Header locationHeader = response.getFirstHeader("Location");  
        if (locationHeader == null) {  
            return null;  
        }  
        return locationHeader.getValue();  
    }  
    public static String getText(String URL){
    	ResponseHandler<String> responseHandler = new BasicResponseHandler();  
    	String responseBody = ""; 
        RequestConfig requestConfig = RequestConfig.custom()  
        	    .setConnectionRequestTimeout(1000).setConnectTimeout(1000)  
        	    .setSocketTimeout(1000).build();  
        HttpGet httpGet = new HttpGet(URL);  
        httpGet.setConfig(requestConfig);    
       
        HttpEntity httpEntity = null;
        
//        System.out.println(httpGet.getRequestLine());  
        try {  
            responseBody= closeableHttpClient.execute(httpGet, responseHandler);  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {  
            	entity.getContent();
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            httpGet.abort();  
        }  
    	return responseBody;
    }
    
}