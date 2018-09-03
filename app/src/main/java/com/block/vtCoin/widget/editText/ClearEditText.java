package com.block.vtCoin.widget.editText;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

import com.block.vtCoin.R;

/**
 * 带清除按钮的edittext
 */
public class ClearEditText extends EditText implements OnTouchListener, OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {
	private static final String TAG = "ClearEditText";

	public  enum Location {
		LEFT(0), RIGHT(2);

		final int idx;

		 Location(int idx) {
			this.idx = idx;
		}
	}

	public interface Listener {
		void didClearText();
	}

	public ClearEditText(Context context) {
		super(context);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	/**
	 * null disables the icon
	 */
	public void setIconLocation(Location loc) {
		this.loc = loc;
		initIcon();
	}

	@Override
	public void setOnTouchListener(OnTouchListener l) {
		this.l = l;
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener f) {
		this.f = f;
	}

	private Location loc = Location.RIGHT;

	private Drawable xD;
	private Listener listener;

	private OnTouchListener l;
	private OnFocusChangeListener f;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (getDisplayedDrawable() != null) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			int left = (loc == Location.LEFT) ? 0 : getWidth() - getPaddingRight() - xD.getIntrinsicWidth();
			int right = (loc == Location.LEFT) ? getPaddingLeft() + xD.getIntrinsicWidth() : getWidth();
//			boolean tappedX = x >= left && x <= right && y >= 0 && y <= (getBottom() - getTop());
			boolean tappedX = x >= left-50 && x <= right+50 && y >= 0 && y <= (getBottom() - getTop());
//			Log.i(TAG, " x="+x+"   y="+y+"\nleft="+left+"   right="+right+"\ngetbottom="+getBottom()+"   getTop="+getTop());
			if (tappedX) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					setText("");
					if (listener != null) {
						listener.didClearText();
					}
				}
				return true;
			}
		}
		if (l != null) {
			return l.onTouch(v, event);
		}
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			setClearIconVisible(isNotEmpty(getText()));
		} else {
			setClearIconVisible(false);
		}
		if (f != null) {
			f.onFocusChange(v, hasFocus);
		}
	}

	@Override
	public void onTextChanged(EditText view, String text) {
		if (isFocused()) {
			setClearIconVisible(isNotEmpty(text));
		}
	}

	@Override
	public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
		super.setCompoundDrawables(left, top, right, bottom);
		initIcon();
	}

	private void init() {
		super.setOnTouchListener(this);
		super.setOnFocusChangeListener(this);
		addTextChangedListener(new TextWatcherAdapter(this, this));
		initIcon();
		setClearIconVisible(false);
	}

	private void initIcon() {
		xD = null;
		if (loc != null) {
			xD = getCompoundDrawables()[loc.idx];
		}
		if (xD == null) {
			xD = getResources().getDrawable(R.mipmap.close);
		}
		xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
		int min = getPaddingTop() + xD.getIntrinsicHeight() + getPaddingBottom();
		if (getSuggestedMinimumHeight() < min) {
			setMinimumHeight(min);
		}
	}

	private Drawable getDisplayedDrawable() {
		return (loc != null) ? getCompoundDrawables()[loc.idx] : null;
	}

	protected void setClearIconVisible(boolean visible) {
		Drawable[] cd = getCompoundDrawables();
		Drawable displayed = getDisplayedDrawable();
		boolean wasVisible = (displayed != null);
		if (visible != wasVisible) {
			Drawable x = visible ? xD : null;
			super.setCompoundDrawables((loc == Location.LEFT) ? x : cd[0], cd[1], (loc == Location.RIGHT) ? x : cd[2],
					cd[3]);
		}
	}
	public static boolean isNotEmpty(CharSequence str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(CharSequence str) {
		return str == null || str.length() == 0;
	}
}
