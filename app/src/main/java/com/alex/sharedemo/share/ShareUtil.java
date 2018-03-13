package com.alex.sharedemo.share;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.io.File;
import java.util.List;
/*
 *  @文件名:   ShareUtil
 *  @创建者:   xuerui
 *  @创建时间:  2017/10/16 17:55
 *  @描述：    微信分享工具类
 */

public class ShareUtil {



	/**
	 * 调用系统分享的方法
	 * @param context
	 * @param file
	 */
	public static void systemShare(Context context,File file){
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		share.putExtra("Kdescription", "");
		Uri uri = Uri.fromFile(file);
		share.setType("image/*");
		share.putExtra(Intent.EXTRA_STREAM, uri);
		context.startActivity(Intent.createChooser(share, "分享"));
	}

	/**
	 *  系统分享图片到微信朋友圈
	 * @param context
	 * @param file
	 */
	public static void shareImageToWXCircle(Context context, File file) {
		boolean found = false;
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		share.setType("image/*");
		share.putExtra("Kdescription", "");
		Uri uri = Uri.fromFile(file);

		List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(share, PackageManager.MATCH_DEFAULT_ONLY);
		if (!resInfo.isEmpty()) {
			for (ResolveInfo info : resInfo) {
				if (info.activityInfo.name.contains("com.tencent.mm.ui.tools.ShareToTimeLineUI")) {
					share.putExtra(Intent.EXTRA_STREAM, uri);
					share.setPackage(info.activityInfo.packageName);
					share.setClassName(info.activityInfo.packageName, info.activityInfo.name);
					found = true;
					String packageName = info.activityInfo.packageName;
					context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
					break;
				}
			}
			if (!found) return;
			context.startActivity(share);
		}
	}

	/**
	 *  系统分享图片到微信好友
	 */
	public static void shareImageToAll(Context context, File file) {
		boolean found = false;
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		share.setType("image/*");
		Uri uri = Uri.fromFile(file);

		List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(share, PackageManager.MATCH_DEFAULT_ONLY);
		if (!resInfo.isEmpty()) {
			for (ResolveInfo info : resInfo) {
				if (info.activityInfo.name.contains("com.tencent.mm.ui.tools.ShareImgUI")) {
					share.putExtra(Intent.EXTRA_STREAM, uri);
					//share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, getUriListForImages());
					//share.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(selectFile.get(0)));
					share.setPackage(info.activityInfo.packageName);
					share.setClassName(info.activityInfo.packageName, info.activityInfo.name);
					found = true;
					String packageName = info.activityInfo.packageName;
					context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
					break;
				}
			}
			if (!found) return;
			context.startActivity(share);
		}
	}

	/**
	 * 分享有礼
	 */
	public static void shareGift(Context context) {
		ShareWeb shareWeb = new ShareWeb("http://a.app.qq.com/o/simple.jsp?pkgname=com.dolphin.insuranceAgent");
		String title = "保险精英都在用";
		shareWeb.setTitle(title);
		String des = "大咖推荐，客户多，签单快";
		shareWeb.setDescription(des);
		new ShareAction(context).setPlatform(SHARE_MEDIA.WECHAT).withMedia(shareWeb).setCallback(new ShareListener()).share();
	}


}
