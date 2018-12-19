package com.example.vzvision;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import com.todayin.cctv.R;

public class DeviceActivity extends Activity {
	
	private EditText deviceNameEdit = null;
	private EditText deviceIpEdit = null;
	private EditText devicePortEdit = null;
	private EditText userNameEdit = null;
	private EditText userPasswordEdit = null;
	private Button OkButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device);
		
		deviceNameEdit = (EditText) findViewById(R.id.editText_deviceName);
		deviceIpEdit = (EditText) findViewById(R.id.editText_deviceIp);
		devicePortEdit = (EditText) findViewById(R.id.editText_devicePort);
		userNameEdit = (EditText) findViewById(R.id.editText_deviceUserName);
		userPasswordEdit = (EditText) findViewById(R.id.editText_deviceUserpassword);
		OkButton         = (Button) findViewById(R.id.button_WlistEdit_Ok);
		OkButton.setOnClickListener(clickListener);
		
		Intent intent = getIntent();
		 
		Bundle bundle = intent.getExtras();
		
		String devicename = bundle.getString(CameraActivity.deviceNameLabel);
		String deviceip = bundle.getString(CameraActivity.deviceIpLabel);
		int deviceport = bundle.getInt(CameraActivity.devicePortLabel);
		String userName = bundle.getString(CameraActivity.UserNameLabel);
		String userPassword = bundle.getString(CameraActivity.UserPasswordLabel);
		
		deviceNameEdit.setText(devicename);
		deviceIpEdit.setText(deviceip);
		devicePortEdit.setText(Integer.toString( deviceport));
		userNameEdit.setText(userName);
		userPasswordEdit.setText(userPassword);
		
		 
	}
	
//	@Override
//    public void finish() {
//		
//	  
//        super.finish();
//    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.device, menu);
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
	
	 private View.OnClickListener clickListener =  new View.OnClickListener(){
			@Override
			public void onClick(View view)
			{
				   Intent intent = new Intent( DeviceActivity.this,CameraActivity.class);
				     
				   intent.putExtra(CameraActivity.deviceNameLabel,deviceNameEdit.getText().toString());
				   intent.putExtra(CameraActivity.deviceIpLabel, deviceIpEdit.getText().toString());
				   intent.putExtra(CameraActivity.devicePortLabel, devicePortEdit.getText().toString());
				   intent.putExtra(CameraActivity.UserNameLabel, userNameEdit.getText().toString());
				   intent.putExtra(CameraActivity.UserPasswordLabel, userPasswordEdit.getText().toString());
			          
					setResult(RESULT_OK, intent);
				
				finish();
			}
	 };
}
