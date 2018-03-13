package com.alex.sharedemo.share;

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
