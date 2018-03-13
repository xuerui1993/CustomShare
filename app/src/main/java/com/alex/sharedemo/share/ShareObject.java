package com.alex.sharedemo.share;

/*
 *  @项目名：  DIAgent
 *  @包名：    com.dolphin.insuranceAgent.utils
 *  @文件名:   ShareObject
 *  @创建者:   xuerui
 *  @创建时间:  2017/12/25 11:08
 *  @描述：    分享类型基类
 */

abstract class ShareObject {
	static final int IMAGE_TYPE = 1;  //图片
	static final int WEB_TYPE = 2;  //网页
	int mShareType;  //分享类型
}
