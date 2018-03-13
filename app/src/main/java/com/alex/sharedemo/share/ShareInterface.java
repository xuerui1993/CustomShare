package com.alex.sharedemo.share;

/*
 *  @项目名：  DIAgent 
 *  @包名：    com.dolphin.insuranceAgent.share
 *  @文件名:   ShareInterface
 *  @创建者:   xuerui
 *  @创建时间:  2018/3/13 11:35
 *  @描述：    分享回调接口
 */

public interface ShareInterface  {
	void onStart(SHARE_MEDIA platform);  //分享开始

	void onResult(SHARE_MEDIA platform);  //分享成功后的结果

	void onError(SHARE_MEDIA platform, Throwable t);  //分享错误

	void onCancel(SHARE_MEDIA platform);  //分享取消
}
