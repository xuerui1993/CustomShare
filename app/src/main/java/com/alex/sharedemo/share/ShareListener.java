package com.alex.sharedemo.share;

import android.widget.Toast;

public class ShareListener  implements ShareInterface {

	private int type;
	private String shareId;
	private int forwardType;

	public ShareListener() {

	}

	public ShareListener(int type, String id, int forwardType) {
		this.type = type;
		this.shareId = id;
		this.forwardType = forwardType;
	}

	/**
	 * @descrption 分享开始的回调
	 * @param platform 平台类型
	 */
	public void onStart(SHARE_MEDIA platform) {

	}

	/**
	 * @descrption 分享成功的回调
	 * @param platform 平台类型
	 */
	public void onResult(SHARE_MEDIA platform) {
		Toast.makeText(UiUtil.getContext(),"分享成功了",Toast.LENGTH_SHORT).show();
		if (type != 0) {

		}

	}

	/**
	 * @descrption 分享失败的回调
	 * @param platform 平台类型
	 * @param t 错误原因
	 */
	public void onError(SHARE_MEDIA platform, Throwable t) {
		Toast.makeText(UiUtil.getContext(),"分享失败",Toast.LENGTH_SHORT).show();
	}

	/**
	 * @descrption 分享取消的回调
	 * @param platform 平台类型
	 */
	public void onCancel(SHARE_MEDIA platform) {
		Toast.makeText(UiUtil.getContext(),"分享取消了",Toast.LENGTH_SHORT).show();
		if (type != 0) {

		}
	}
}
