package cn.com.uangel.adsdk.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;

/**
 * @author azrael
 *  全屏广告，可能是图片也可能是视频
 */
public class AdView_FullScreen extends RelativeLayout {
	
	
	public AdView_FullScreen(Context context) {
		super(context);
		
		
	}
	public AdView_FullScreen (Context context,boolean paramBoolean){
		this(context);
		
		
	}
	@Override
	public void setBackgroundDrawable(Drawable d) {
		// TODO Auto-generated method stub
		super.setBackgroundDrawable(d);
	}

	
	
}
