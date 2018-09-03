package com.block.vtCoin.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.block.vtCoin.R;

/**
 * 自定义加载数据对话框
 * 
 * 
 */
public class LoadDialog extends Dialog {

	public LoadDialog(Context context) {
		super(context);
		init();
	}

	@SuppressWarnings("deprecation")
	private void init() {
		// TODO Auto-generated method stub
		Window window = this.getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.setBackgroundDrawable(new BitmapDrawable());
		setContentView(R.layout.load_dialog);
	}

	/**
	 * 设置加载标题　通过字符串
	 * 
	 * @param loadMsg
	 */
	public void setText(String loadMsg) {
		(findViewById(R.id.load_lable)).setVisibility(View.VISIBLE);
		((TextView) findViewById(R.id.load_lable)).setText(loadMsg);
	}

	public void setNoText(){
		( findViewById(R.id.load_lable)).setVisibility(View.GONE);
	}

	public void setText(int loadMsg) {
		((TextView) findViewById(R.id.load_lable)).setText(loadMsg);
	}
}
