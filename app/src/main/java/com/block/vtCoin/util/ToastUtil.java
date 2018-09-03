package com.block.vtCoin.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.block.vtCoin.R;
import com.block.vtCoin.widget.text_view.ToastTextView;

/**
 * 封装Toast
 */
public class ToastUtil {
	private static ToastUtil mToastUtil;
	private Toast mToast;
	private TextView textView;

	private ToastUtil() {
	}

	// 唯一实例
	public static ToastUtil getInstance() {
		if (mToastUtil == null) {
			mToastUtil = new ToastUtil();
		}
		return mToastUtil;
	}

	/**
	 * 系统的toast
	 */
	public void showMessage(Context context, String message, int duration) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context.getApplicationContext(), message, duration);
		mToast.show();
	}

	/**
	 * 系统的toast
	 */
	public void showMessage(Context context, int resId, int duration) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context.getApplicationContext(), resId, duration);
		mToast.show();
	}

	public void showTopMessage(Context context, String message) {
		float topHeight = context.getResources().getDimension(R.dimen.sw_height_1);
		showMyMessage(context.getApplicationContext(), message, (int)(topHeight + ScreenUtils.dip2px(10)));
	}


	/**
	 * @param context 要用applayCation的context,不然会出现内存泄漏
	 * @param message
	 */
	public void showBottomMessage(Context context, String message) {
		showMyMessage(context.getApplicationContext(), message, ScreenUtils.dip2px(450));
	}
	
	public void showCenterMessage(Context context, String message) {
		showMyMessage(context.getApplicationContext(), message, context.getResources().getDimensionPixelSize(R.dimen.tiptext_bottom_margin));
	}

	/**
	 * 自定义的toast
	 */
	private void showMyMessage(Context context, String message, int top) {
		if (context instanceof Activity && ((Activity)context).isFinishing()) {
			return;
		}
		View view = View.inflate(context, R.layout.toast, null);
		textView = (ToastTextView) view.findViewById(R.id.message);
		textView.setText(message);
		
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = new Toast(context);
		mToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, top);
		mToast.setView(view);
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.show();
	}
}
