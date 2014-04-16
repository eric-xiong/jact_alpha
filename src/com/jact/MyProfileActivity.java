package com.jact;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MyProfileActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void rewardsOnClick(View view){
    	String val="currentuser";//HACK:Dummy value, send to next activity.
    	//if(val != "") {
	        //生成一个Intent对象 
	        Intent intent=new Intent(); 
	        //设置传递的参数 
	        intent.putExtra("val", val); 
	        //从Activity IntentTest跳转到Activity IntentTest01 
	        intent.setClass(MyProfileActivity.this,RewardsActivity.class); 
	        //启动intent的Activity 
	        MyProfileActivity.this.startActivity(intent);
    	//} else { //wrong username or pwd
    	//	Toast.makeText(getApplicationContext(), "need val to next Rewards.",
    	//		     Toast.LENGTH_SHORT).show();
    	//}
    }

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_profile, menu);
		return true;
	}*/

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
			View rootView = inflater.inflate(R.layout.fragment_my_profile,
					container, false);
			return rootView;
		}
	}

}
