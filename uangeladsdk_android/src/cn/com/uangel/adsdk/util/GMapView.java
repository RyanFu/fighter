//package cn.com.uangel.adsdk.util;
//
//import android.content.Context;
//import android.view.Gravity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnLongClickListener;
//import android.widget.ZoomControls;
//
//import com.google.android.maps.MapView;
//
//public class GMapView extends MapView implements OnLongClickListener {
//	private static final int FILL = ViewGroup.LayoutParams.FILL_PARENT;
//
//	@SuppressWarnings("deprecation")
//	public GMapView(Context context, String apiKey) {
//		super(context, apiKey);
//
//		setClickable(true);
//		setLongClickable(true);
//		setOnLongClickListener(this);
//
//		// ZoomControls settings.
//		ZoomControls zoomControls = (ZoomControls) getZoomControls();
//		zoomControls.setLayoutParams(new ViewGroup.LayoutParams(FILL, FILL));
//		zoomControls.setGravity(Gravity.BOTTOM + Gravity.CENTER_HORIZONTAL);
//		addView(zoomControls);
//	}
//
//	@Override
//	public boolean onLongClick(View v) {
//		displayZoomControls(true);
//		return false;
//	}
//
//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		onTouchEvent(ev);
//		return false;
//	}
//
//}
