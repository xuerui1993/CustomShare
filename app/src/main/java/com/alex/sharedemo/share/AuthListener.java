package com.alex.sharedemo.share;

/*
 *  @文件名:   AuthListener
 *  @创建者:   xuerui
 *  @创建时间:  2017/12/26 11:02
 *  @描述：    授权回调监接口
 */

import java.util.Map;

public interface AuthListener {
	int ACTION_AUTHORIZE = 0;
	int ACTION_DELETE = 1;
	int ACTION_GET_PROFILE = 2;

	void onStart(SHARE_MEDIA platform);

	void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data);

	void onError(SHARE_MEDIA platform, int action, Throwable t);

	void onCancel(SHARE_MEDIA platform, int action);
}
