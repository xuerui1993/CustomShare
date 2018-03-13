package com.alex.sharedemo.share;

/*
 *  @文件名:   ShareImage
 *  @创建者:   xuerui
 *  @创建时间:  2017/12/25 11:12
 *  @描述：    分享网页类型
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class ShareWeb extends ShareObject {
	public String webpageUrl;  //图片地址
	public String title;  //标题
	public String description;  //描述
	public String thumb;  //缩略图地址
	public Bitmap thumbImg;  //缩略图Bitmap

	public ShareWeb(String url) {
		webpageUrl = url;
		mShareType = WEB_TYPE;
	}

	void setThumbImg(int res) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.outHeight = 250;
		options.outWidth = 250;
		thumbImg = BitmapFactory.decodeResource(UiUtil.getContext().getResources(),res,options);
	}

	public void setThumbImg(File file) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.outHeight = 250;
		options.outWidth = 250;
		thumbImg = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
	}

	public String getWebpageUrl() {
		return webpageUrl;
	}

	public void setWebpageUrl(String webpageUrl) {
		this.webpageUrl = webpageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Bitmap getThumbImg() {
		return thumbImg;
	}
}
