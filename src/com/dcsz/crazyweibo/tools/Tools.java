package com.dcsz.crazyweibo.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.dcsz.crazyweibo.R;

public class Tools {
	
	
	public static boolean connNetwork(Context context){
		ConnectivityManager manager =  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(!manager.equals(null)){
			NetworkInfo[] info = manager.getAllNetworkInfo();
			if(info!=null){
				for (int i = 0; i < info.length; i++) {
					if(info[i].isConnected()&&info[i].getState()==NetworkInfo.State.CONNECTED){
						Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
						return true;
				}
			}
				}else{
				Toast.makeText(context, "无网络信号", Toast.LENGTH_SHORT).show();
				return false;
			}
		}else{
			Toast.makeText(context, R.string.null_networkmanager, 2000).show();
			return false;
		}
		return true;
	}

}
