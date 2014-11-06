package com.enphoneh.appleemoji;

import enphone.FileHelper.FileHelper;
import enphone.clicklistenner.ButtonClick;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {
 
	protected Button mSwitchtoApple;
	protected Button mSwitchtoAndroid;
	protected FileHelper mFileHelper;
	private InterstitialAd mInterstitial;
	private AdView adView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSwitchtoApple =  (Button) findViewById(R.id.btn_switchtoapple);
		mSwitchtoAndroid = (Button) findViewById(R.id.btn_switchtoandroid);
		mSwitchtoApple.setOnClickListener(new ButtonClick(this));
		mSwitchtoAndroid.setOnClickListener(new  ButtonClick(this));
		 // 制作插页式广告。
		mInterstitial = new InterstitialAd(this);
		mInterstitial.setAdUnitId("ca-app-pub-8795122230307245/4774273416");
	    // 创建广告请求。
	    AdRequest adRequest = new AdRequest.Builder().build();
	    // 开始加载插页式广告。
	    mInterstitial.loadAd(adRequest);
	    
	 // 创建adView。
	    adView = new AdView(this);
	    adView.setAdUnitId("ca-app-pub-8795122230307245/3157939410");
	    adView.setAdSize(AdSize.BANNER);

	    // 查询LinearLayout，假设其已指定
	    // 属性android:id="@+id/mainLayout"。
	    LinearLayout layout = (LinearLayout)findViewById(R.id.admobe);

	    // 在其中添加adView。
	    layout.addView(adView);

	    // 启动一般性请求。
	    AdRequest adRequestbanner = new AdRequest.Builder().build();

	    // 在adView中加载广告请求。
	    adView.loadAd(adRequestbanner);
	}
	
	 @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	        	displayInterstitial();
	        	finish();
	        }  	          
	        return false;          
	    }
	 // 在您准备好展示插页式广告时调用displayInterstitial()。
	  public void displayInterstitial() {
	    if (mInterstitial.isLoaded()) {
	    	mInterstitial.show();
	    }
	  }
	  @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		  adView.pause();
		super.onPause();
	}
	  @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		  adView.destroy();
		super.onDestroy();
	}
	  @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adView.resume();
	}
}
