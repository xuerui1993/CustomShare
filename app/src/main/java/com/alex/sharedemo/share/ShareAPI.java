package com.alex.sharedemo.share;

/*
 *  @项目名：  DIAgent 
 *  @包名：    com.dolphin.insuranceAgent.utils
 *  @文件名:   ShareAPI
 *  @创建者:   xuerui
 *  @创建时间:  2017/12/25 10:33
 *  @描述：    TODO
 */

import android.graphics.Bitmap;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class ShareAPI {
	private final IWXAPI mWxApi;
	private static ShareAPI mInstance = null;
	private ShareListener mShareListener;
	private AuthListener mAuthListener;

	private ShareAPI() {
		mWxApi = WXAPIFactory.createWXAPI(UiUtil.getContext(), "Your WX_APP_ID");
	}

	public static ShareAPI getInstance() {
		if (mInstance == null) {
			mInstance = new ShareAPI();
		}
		return mInstance;
	}

	public ShareListener getShareListener() {
		return mShareListener;
	}

	public AuthListener getAuthListener() {
		return mAuthListener;
	}

	/**
	 * 下载缩略图图片
	 */
	private Bitmap getThumbBitmap(String url) {
		Bitmap bitmap = null;
		try {
			bitmap = Glide.with(UiUtil.getContext()).load(url).asBitmap().fitCenter()
				.into(250, 250).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	void doShare(final ShareAction shareAction, ShareListener listener) {
		mShareListener = listener;
		if (!mWxApi.isWXAppInstalled()) {
			Toast.makeText(UiUtil.getContext(),"您还未安装微信客户端",Toast.LENGTH_SHORT).show();
			return;
		}
		int shareType = shareAction.mShareObject.mShareType;
		if (shareType == ShareObject.IMAGE_TYPE) {
			//分享图片
			ShareImage shareImage = (ShareImage) shareAction.mShareObject;
			WXImageObject imageObject = new WXImageObject(shareImage.bitmap);
			WXMediaMessage msg = new WXMediaMessage();
			msg.mediaObject = imageObject;
			SendMessageToWX.Req req = new SendMessageToWX.Req();
			req.transaction = "img" + System.currentTimeMillis();
			req.message = msg;
			req.scene = shareAction.mPlatform == SHARE_MEDIA.WEIXIN_CIRCLE ?
				SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
			mWxApi.sendReq(req);
			if (mShareListener!=null){
				mShareListener.onStart(shareAction.mPlatform);
			}
		} else if (shareType == ShareObject.WEB_TYPE) {
			//分享网页
				new Thread(){
					@Override
					public void run() {
						WXWebpageObject webpage = new WXWebpageObject();
						ShareWeb shareWeb = (ShareWeb) shareAction.mShareObject;
						WXMediaMessage msg = new WXMediaMessage(webpage);
						if (shareWeb.thumbImg!=null){
							msg.setThumbImage(shareWeb.thumbImg);
						}else {
							String thumb = shareWeb.thumb;
							if (thumb != null) {
								Bitmap bitmap = getThumbBitmap(thumb);
								msg.setThumbImage(bitmap);
							}
						}
						//设置缩略图
						webpage.webpageUrl = shareWeb.webpageUrl;
						msg.title = shareWeb.title;
						msg.description = shareWeb.description;
						SendMessageToWX.Req req = new SendMessageToWX.Req();
						req.transaction = "webpage" + System.currentTimeMillis();
						req.message = msg;
						req.scene = shareAction.mPlatform == SHARE_MEDIA.WEIXIN_CIRCLE ?
							SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
						mWxApi.sendReq(req);
						if (mShareListener!=null){
							mShareListener.onStart(shareAction.mPlatform);
						}
					}
				}.start();



		}
	}

	public void getPlatformInfo(SHARE_MEDIA platform, AuthListener authListener) {
		mAuthListener = authListener;
		// send oauth request
		SendAuth.Req req = new SendAuth.Req();
		req.scope = "snsapi_userinfo";
		req.state = "if_consultant";
		mWxApi.sendReq(req);
	}
}
