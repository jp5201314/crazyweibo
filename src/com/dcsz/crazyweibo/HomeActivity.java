package com.dcsz.crazyweibo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView text = new TextView(this);
		text.setText("欢迎来到主界面");
		setContentView(text);
	}
}
