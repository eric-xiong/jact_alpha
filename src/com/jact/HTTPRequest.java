package com.jact;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HTTPRequest {

	public static String executePost(String strUrl, String json) {
		String result = null;
		BufferedReader reader = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost();
			//request.setURI(new URI("http://staging.squadup.com/api/v1/users/session"));
			request.setURI(new URI(strUrl));
			
			//This is solution one: POST with raw parameters
			//List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			//postParameters.add(new BasicNameValuePair("token", "alexzhou"));
			
			//UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
			//		postParameters);
			//request.setEntity(formEntity);

			//This is solution two: POST with JSON string
			//String json="{\"username\": \"testusername\",\"password\":\"sadfjlk43$sd2\"}";
			//String json="{\"password\":\"123456\",\"email\":\"testuser@squadup.com\"}";
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			request.setEntity(s);
			

			HttpResponse response = client.execute(request);
			reader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));

			StringBuffer strBuffer = new StringBuffer("");
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuffer.append(line);
			}
			result = strBuffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
    
    public static String executeHttpPost() {
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL("http://staging.squadup.com/api/v1/users/session");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "utf-8");
			DataOutputStream dop = new DataOutputStream(
					connection.getOutputStream());
			dop.writeBytes("token=alexzhou");
			dop.flush();
			dop.close();

			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
			result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}
    
    /*public static JSONObject post(String url,JSONObject json){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			
			HttpResponse res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
				HttpEntity entity = res.getEntity();
				String charset = EntityUtils.getContentCharSet(entity);
				response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(),charset)));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}*/

}
