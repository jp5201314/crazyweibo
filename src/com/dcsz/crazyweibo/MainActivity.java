package com.dcsz.crazyweibo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dcsz.crazyweibo.tools.Tools;

public class MainActivity extends Activity {
	ImageView image;
	RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image = new ImageView(this);
		image.setBackgroundResource(R.drawable.sina);
		rl = (RelativeLayout) this.findViewById(R.id.ll_re);
		rl.addView(image);
	}

	@Override
    protected void onResume() {
    	
    	super.onResume();
		// TODO Auto-generated method stub
		boolean flag = false;
    	if(flag!=Tools.connNetwork(MainActivity.this)){
    		if(MainActivity.this.isFristEnter()){
    			AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 0.9f);
    			alphaAnimation.setDuration(5000);
    	    	alphaAnimation.start();
    	    	image.setAnimation(alphaAnimation);
    	    	alphaAnimation.setAnimationListener(new AnimationListener() {
    				
    				@Override
    				public void onAnimationStart(Animation animation) {
    					// TODO Auto-generated method stub
    					
    				}
    				
    				@Override
    				public void onAnimationRepeat(Animation animation) {
    					// TODO Auto-generated method stub
    					
    				}
    				
    				@Override
    				public void onAnimationEnd(Animation animation) {
    					startActivity(new Intent(MainActivity.this,OauthActivity.class));
    	    			
    	    			MainActivity.this.finish();
    				}
    	    	});
    	    	
    			
    		}else{
    			startActivity(new Intent(MainActivity.this,HomeActivity.class));
    			MainActivity.this.finish();
    		}
    	}else{
    		// 没网络就弹出dialog来设置网络
    		new AlertDialog.Builder(MainActivity.this)
    				.setIcon(R.drawable.ic_com_sina_weibo_sdk_logo)
    				.setTitle(R.string.connectNetwork)
    				.setMessage(R.string.connectNetworkHint)
    				.setPositiveButton(R.string.network_Dialog_Positive_Button,
    						new OnClickListener() {
    							@Override
    							public void onClick(DialogInterface dialog,
    									int which) {

    								// 跳转到系统设置网络的界面
    								Intent intent = new Intent(
    										android.provider.Settings.ACTION_WIRELESS_SETTINGS);
    								startActivity(intent);
    							}
    						}).create().show();
	}
    	
    }
	
	private boolean isFristEnter(){
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		if(preferences.getString("key", "null")=="first"){
			return false;
		}
		Editor editor = preferences.edit();
		editor.putString("key", "first");
		editor.commit();
		return true;
	}
}
