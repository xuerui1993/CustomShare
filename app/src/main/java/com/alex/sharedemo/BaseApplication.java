package com.alex.sharedemo;

import android.app.Application;

import com.alex.sharedemo.share.UiUtil;


public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		UiUtil.init(this);
	}


}
