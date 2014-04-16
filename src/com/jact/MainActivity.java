package com.jact;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;  
import android.widget.TextView;
import android.content.Intent;
import android.widget.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

public class MainActivity extends ActionBarActivity {
	TextView Tv1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        //Tv1 = (TextView)findViewById(R.id.textViewMain);
        //String strTmp="testing";  
        //Tv1.setText(strTmp);  
        
        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText1);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        
        Button Btnlogin = (Button)findViewById(R.id.login);  
 
        /*Btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });*/

        
    }

    
    
    
    private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			//HACK:we have the msg here:
			msg.obj = "{\"status\": 1,\"message\": \"Success\",\"uid\": 4,\"session-token\": \"w2341083$jsadlf;223\"}";
			
			if(msg == null || msg.obj == null) {
				Log.d("JACT","handleMessage get null message.");
				return;
			}
			
			Log.d("JACT", "handle message:" + msg.obj.toString()); 
			Toast.makeText(getApplicationContext(), msg.obj.toString(),
				     Toast.LENGTH_SHORT).show();
			
			//DEMO: how to create JSON
			/*JSONObject jrewards_response = new JSONObject();
			try {
				JSONArray jrewards = new  JSONArray();
				JSONObject rewarda = new JSONObject();
				JSONObject rewarda_content = new JSONObject();
				rewarda_content.put("reward-thumbnail-url", "http://adb.com/defga");
				rewarda_content.put("description", "reward a description");
				rewarda_content.put("title", "reward a title");
				rewarda.put("reward-a", rewarda_content);
				jrewards.put(rewarda);
				JSONObject rewardb = new JSONObject();
				JSONObject rewardb_content = new JSONObject();
				rewardb_content.put("reward-thumbnail-url", "http://adb.com/defgb");
				rewardb_content.put("description", "reward b description");
				rewardb_content.put("title", "reward b title");
				rewardb.put("reward-b", rewarda_content);
				jrewards.put(rewardb);
				JSONObject rewardc = new JSONObject();
				JSONObject rewardc_content = new JSONObject();
				rewardc_content.put("reward-thumbnail-url", "http://adb.com/defgc");
				rewardc_content.put("description", "reward c description");
				rewardc_content.put("title", "reward c title");
				rewardc.put("reward-c", rewarda_content);
				jrewards.put(rewardc);
				jrewards_response.put("rewards", jrewards);
				
				jrewards_response.put("status", 1);
				jrewards_response.put("uid", 3);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d("JACT", jrewards_response.toString());

			//DEMO:parse String to JSON
			String strLoginResponse="{\"username\": \"testusername\",\"password\":\"sadfjlk43$sd2\"}";
			
			try {
				JSONTokener jsonParser = new JSONTokener(strLoginResponse);
				JSONObject jlogin = (JSONObject) jsonParser.nextValue();
				Log.d("JACT", jlogin.toString()); 
				Log.d("JACT","username:"+jlogin.getString("username"));
				Log.d("JACT","password:"+jlogin.getString("password"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

			//DEMO: MD5
				Log.d("JACT","MD5:" + MD5Util.getMD5Str("password"));
			*/
			
			try {
				JSONTokener jsonParser = new JSONTokener(msg.obj.toString());
				JSONObject jlogin = (JSONObject) jsonParser.nextValue();
				Log.d("JACT", jlogin.toString()); 
				Log.d("JACT","status:"+jlogin.getInt("status"));
				
				switch (msg.what) {
					case 0:
						if(jlogin.getInt("status") == 1) { //login success.
							//生成一个Intent对象 
					        Intent intent=new Intent(); 
					        //设置传递的参数 
					        intent.putExtra("uid", jlogin.getInt("uid")); 
					        //从Activity IntentTest跳转到Activity IntentTest01 
					        intent.setClass(MainActivity.this,MyProfileActivity.class); 
					        //启动intent的Activity 
					        MainActivity.this.startActivity(intent);
						} else {
							Toast.makeText(getApplicationContext(),
									"login failed." + jlogin.getString("message"),
				    			     Toast.LENGTH_SHORT).show();
						}
						
						break;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			
		};
	};
	
    public void loginOnClick(View view){  
    	/*EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
    	EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        //String message = editText.getText().toString(); 
        //Tv1.setText(message); 
    	
    	String valUserName=editTextUserName.getText().toString(); 
    	String valPassword=editTextPassword.getText().toString();
    	
    	/*if(valUserName.equals("admin") && valPassword.equals("123")){
	        //生成一个Intent对象 
	        Intent intent=new Intent(); 
	        //设置传递的参数 
	        intent.putExtra("val", valUserName); 
	        //从Activity IntentTest跳转到Activity IntentTest01 
	        intent.setClass(MainActivity.this,MyProfileActivity.class); 
	        //启动intent的Activity 
	        MainActivity.this.startActivity(intent);
    	} else { //wrong username or pwd
    		Toast.makeText(getApplicationContext(), "Username or password wrong.please input admin/123.",
    			     Toast.LENGTH_SHORT).show();
    	}*/
    	
    	
    	
    	new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				//List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				//postParameters.add(new BasicNameValuePair("token", "alexzhou"));
				
				//String url = "http://staging.squadup.com/api/v1/users/session";
				String url = "http://api.jact.clients.inzen.com.cn/user/login";
				
				//String json="{\"password\":\"123456\",\"email\":\"testuser@squadup.com\"}";
				
				EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		    	EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		        //String message = editText.getText().toString(); 
		        //Tv1.setText(message); 
		    	
		    	String valUserName=editTextUserName.getText().toString(); 
		    	String valPassword=editTextPassword.getText().toString();
				String json="{\"password\":\""+MD5Util.getMD5Str(valPassword)+"\",\"username\":\""
						+valUserName+"\"}";
				Log.d("JACT", "json:"+ json);
				
				String strResult = HTTPRequest.executePost(url, json);
				Log.d("JACT","receive data:" + strResult);
				//handler.sendEmptyMessage(0);
				Message msg = new Message();
				msg.obj = strResult;
				msg.what = 0;
				handler.sendMessage(msg);
			}
		}.start();
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
