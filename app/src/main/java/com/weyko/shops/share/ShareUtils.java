package com.weyko.shops.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.util.ShowUtil;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
 * shareSDK分享工具类
 */
public class ShareUtils implements PlatformActionListener {
	private static final int MSG_USERID_FOUND = 1;
	private ShareStatusListener listener;
	boolean mShowToast = true;
	private String sharePlat;
	public interface ShareStatusListener {
		void shareSuccess(String sharePlat);
	}
	public void setShareStatusListener (ShareStatusListener l) {
		this.listener = l;
	}

	public ShareUtils() {
	}

	/**
	 * QQ空间、QQ好友
	 * @param sharePlat
	 * @param title
	 * @param content
	 * @param url
	 */
	public void shareQqOrZone(String sharePlat, String title, String content, String picUrL, String url) {
		Platform qzone = ShareSDK.getPlatform(sharePlat);
		Platform.ShareParams sp = new Platform.ShareParams();
		sp.setTitle(title);
		sp.setTitleUrl(url); // 标题的超链接
		sp.setText(content);
		sp.setImageUrl(picUrL);
		// sp.setSite("发布分享的网站名称");
		// sp.setSiteUrl("www.baidu.com");
		qzone.setPlatformActionListener(this); // 设置分享事件回调
		qzone.share(sp);
		this.sharePlat=sharePlat;
	}
	/**
	 * 微信好友
	 * @param sharePlat 类型
	 * @param title 标题
	 * @param content 内容
	 * @param imageDatas Bitmap图片
	 * @param url 链接地址
	 */
	public void shareWeChatFriends(String sharePlat, String title,
								   String content, Bitmap imageDatas, String url) {
		Platform plat = ShareSDK.getPlatform(sharePlat);
		Platform.ShareParams sp = getShareParams(title, content, url);
		sp.setImageData(imageDatas);
		plat.setPlatformActionListener(this);
		plat.share(sp);
		this.sharePlat=sharePlat;
	}

	/**
	 * 微信朋友圈
	 * @param sharePlat 类型
	 * @param title 标题
	 * @param picUrL 图片
	 * @param url 链接地址
	 */
	public void shareWechatMoments(String sharePlat, String title, String content, String picUrL, String url) {
		Platform plat = ShareSDK.getPlatform(sharePlat);
		Platform.ShareParams sp = getShareParams(title, content, url);
		sp.setImageUrl(picUrL);
		plat.setPlatformActionListener(this);
		plat.share(sp);
		this.sharePlat=sharePlat;
	}

	/**
	 * 微信朋友圈
	 * @param sharePlat 类型
	 * @param title 标题
	 * @param imageData Bitmap图片
	 * @param url 链接地址
	 */
	public void shareWechatMoments(String sharePlat, String title, String content, Bitmap imageData, String url) {
		Platform plat = ShareSDK.getPlatform(sharePlat);
		Platform.ShareParams sp = getShareParams(title, content, url);
		sp.setImageData(imageData);
		plat.setPlatformActionListener(this);
		plat.share(sp);
		this.sharePlat=sharePlat;
	}
	/**
	 * 短信分享
	 * @param sharePlat
	 * @param title
	 * @param text
	 */
	public void shareMsg(String sharePlat, String title, String text,String imageUrl,String address) {
		Platform plat = ShareSDK.getPlatform(sharePlat);
		Platform.ShareParams sp = new Platform.ShareParams();
		sp.setTitle(title);
		sp.setAddress(address);
		sp.setText(text);
		sp.setImageUrl(imageUrl);
		plat.setPlatformActionListener(this);
		plat.share(sp);
		this.sharePlat=sharePlat;
	}
	/**
	 * 调起系统发短信功能
	 * @param message
	 */
	public void doSendSMSTo(FragmentActivity activity,String message){
		//"smsto:xxx" xxx是可以指定联系人的
		Uri smsToUri = Uri.parse("smsto:");
		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
		//"sms_body"必须一样，smsbody是发送短信内容content
		intent.putExtra("sms_body", message);
		activity.startActivity(intent);
	}
	/**
	 * 设置数据
	 * @param title 标题
	 * @param content 内容
	 * @param url 链接地址
	 * @return ShareParams
	 */
	private Platform.ShareParams getShareParams(String title, String content, String url) {
		Platform.ShareParams sp = new Platform.ShareParams();
		sp.setTitle(title);
		sp.setText(content);
		sp.setShareType(Platform.SHARE_WEBPAGE);
		sp.setUrl(url);
		return sp;
	}

	public void showToast(boolean show){
		this.mShowToast = show;
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what){
				case 0:
					if (mShowToast){
						ShowUtil.showToast(BaseApplication.getInstance(), BaseApplication.getInstance().getString(R.string.share_completed));
					}
					if (listener != null) {
						listener.shareSuccess(sharePlat);
					}
					break;
				case 1:
					ShowUtil.showToast(BaseApplication.getInstance(), BaseApplication.getInstance().getString(R.string.share_canceled));
					break;
				case 2:
					ShowUtil.showToast(BaseApplication.getInstance(), BaseApplication.getInstance().getString(R.string.share_failed));
					break;
			}
		}
	};
	@Override
	public void onComplete(Platform plat, int action,
						   HashMap<String, Object> res) {
		handler.sendEmptyMessage(0);
//		ShowUtil.showToast(context, context.getString(R.string.share_completed));
	}

	@Override
	public void onCancel(Platform plat, int action) {
		ShowUtil.d("weyko","Platform-----------action="+action);
		handler.sendEmptyMessage(1);
//		ShowUtil.showToast(context, context.getString(R.string.share_canceled));
	}

	@Override
	public void onError(Platform plat, int action, Throwable t) {
		t.printStackTrace();
		handler.sendEmptyMessage(2);
//		ShowUtil.showToast(context, context.getString(R.string.share_failed));
	}
}
