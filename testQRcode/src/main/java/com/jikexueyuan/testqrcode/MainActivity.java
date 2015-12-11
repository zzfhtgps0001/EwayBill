package com.jikexueyuan.testqrcode;

import junit.framework.Test;

import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
	private Button scanButton;
	private TextView text;
	private EditText input;
	private Button genButton;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scanButton = (Button) findViewById(R.id.scan);
		text = (TextView) findViewById(R.id.text);
		scanButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(MainActivity.this, "你写可以扫描条形码或者二维码", Toast.LENGTH_SHORT).show();
				Intent  startScan = new Intent(MainActivity.this, CaptureActivity.class);
//				startActivity(startScan);
				startActivityForResult(startScan, 0);
			}
		});
		
		input = (EditText) findViewById(R.id.input);
		genButton = (Button) findViewById(R.id.gen);
		img = (ImageView) findViewById(R.id.img);
		genButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String in = input.getText().toString();
				if(in.equals("")){
					Toast.makeText(MainActivity.this, "请输入文本", Toast.LENGTH_SHORT).show();
				}else {
					try {
						
						Bitmap qrcode = EncodingHandler.createQRCode(in, 400);
						img.setImageBitmap(qrcode);
					} catch (WriterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			String result = data.getExtras().getString("result");
			text.setText(result);
		}
	}
}
