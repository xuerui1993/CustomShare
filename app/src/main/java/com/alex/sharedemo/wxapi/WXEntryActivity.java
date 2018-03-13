package com.alex.sharedemo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.alex.sharedemo.share.SHARE_MEDIA;
import com.alex.sharedemo.share.ShareAPI;
import com.alex.sharedemo.share.ShareListener;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		api = WXAPIFactory.createWXAPI(this, "Your WX_APP_ID");
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {

	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp instanceof SendAuth.Resp) {
			//微信授权登录
			SendAuth.Resp res = (SendAuth.Resp) resp;
			if ("if_consultant".equals(res.state)) {
				String code = res.code;
				//TODO:获取微信授权信息
			}
		} else {
			//微信分享
			ShareListener listener = ShareAPI.getInstance().getShareListener();
			if (listener != null) {
				if (resp.errCode == 0) {
					//成功
					listener.onResult(SHARE_MEDIA.WECHAT);
				} else if (resp.errCode == -2) {
					//取消
					listener.onCancel(SHARE_MEDIA.WECHAT);
				} else if (resp.errCode == -1) {
					//失败
					listener.onError(SHARE_MEDIA.WECHAT, new Exception());
				}
			}
		}
		finish();
	}
}
