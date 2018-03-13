package com.alex.sharedemo.share;

/*
 *  @项目名：  DIAgent 
 *  @包名：    com.dolphin.insuranceAgent.utils
 *  @文件名:   ShareAction
 *  @创建者:   xuerui
 *  @创建时间:  2017/12/22 17:07
 *  @描述：    TODO
 */

import android.content.Context;

public class ShareAction {
	SHARE_MEDIA mPlatform ;
	Context mContext;
	private ShareListener mListener;
	ShareObject mShareObject;

	public ShareAction(Context context) {
			mContext = context.getApplicationContext();
	}

	public ShareAction setPlatform(SHARE_MEDIA platform) {
		this.mPlatform = platform;
		return this;
	}

	public ShareAction withMedia(ShareObject shareObject) {
		mShareObject = shareObject;
		return this;
	}

	public ShareAction setCallback(ShareListener listener) {
		mListener = listener;
		return this;
	}

	public void share() {
		ShareAPI.getInstance().doShare(this,mListener);
	}
}
