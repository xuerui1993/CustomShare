package com.alex.sharedemo.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class ShareImage extends ShareObject {
	public String title;  //标题
	public String description;  //描述
	public String url;  //图片地址
	public Bitmap bitmap;  //图片Bitmap

	public ShareImage(Bitmap bitmap) {
		this.bitmap = bitmap;
		mShareType = IMAGE_TYPE;
	}

	public ShareImage(File file) {
		this.bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
		mShareType = IMAGE_TYPE;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
