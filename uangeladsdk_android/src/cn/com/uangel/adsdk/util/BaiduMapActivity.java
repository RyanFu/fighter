package cn.com.uangel.adsdk.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.Projection;

public class BaiduMapActivity extends MapActivity {

	private double mLat = 39.897957; // point1纬度
	private double mLon = 116.475904; // point1经度
	private String addressName = ""; // 地址名称

	BMapManager mBMapMan = null;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		MapView mv = new MapView(this);
		mBMapMan = new BMapManager(this);
		mBMapMan.init(getMapApiKey(this), new MKGeneralListener() {
			@Override
			public void onGetNetworkState(int arg0) {

			}

			@Override
			public void onGetPermissionState(int arg0) {

			}
		});
		super.initMapActivity(mBMapMan);
		mBMapMan.start();
		Intent intent = this.getIntent();
		mLon = intent.getDoubleExtra("lon", 116.475904);
		mLat = intent.getDoubleExtra("lat", 39.897957);
		addressName = intent.getStringExtra("addressName");
		GeoPoint p = new GeoPoint((int) (mLat * 1E6), (int) (mLon * 1E6));
		MapController mc = mv.getController();
		mc.setCenter(p);
		mc.setZoom(14);

		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(this.getAssets().open("logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable logo = new BitmapDrawable(bitmap);

		mv.getOverlays().add(new OverItemT(logo, this));

		setContentView(mv);
	}

	private static String getMapApiKey(Context context) {
		ApplicationInfo localPackageManager;
		String result = "";
		try {
			localPackageManager = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
			result = localPackageManager.metaData.getString("MAP_API_KEY");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	protected void onDestroy() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	class OverItemT extends ItemizedOverlay<OverlayItem> {

		private List<OverlayItem> mGeoList = new ArrayList<OverlayItem>();
		private Drawable marker;

		public OverItemT(Drawable marker, Context context) {
			super(boundCenterBottom(marker));

			this.marker = marker;

			// 用给定的经纬度构造GeoPoint，单位是微度 (度 * 1E6)
			GeoPoint p1 = new GeoPoint((int) (mLat * 1E6), (int) (mLon * 1E6));

			// 构造OverlayItem的三个参数依次为：item的位置，标题文本，文字片段
			mGeoList.add(new OverlayItem(p1, addressName, ""));
			populate(); // createItem(int)方法构造item。一旦有了数据，在调用其它方法前，首先调用这个方法
		}

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {

			// Projection接口用于屏幕像素坐标和经纬度坐标之间的变换
			Projection projection = mapView.getProjection();
			for (int index = size() - 1; index >= 0; index--) { // 遍历mGeoList
				OverlayItem overLayItem = getItem(index); // 得到给定索引的item

				String title = overLayItem.getTitle();
				// 把经纬度变换到相对于MapView左上角的屏幕像素坐标
				Point point = projection.toPixels(overLayItem.getPoint(), null);

				// 可在此处添加您的绘制代码
				Paint paintText = new Paint();
				paintText.setColor(Color.RED);
				paintText.setTextSize(18);
				paintText.setShadowLayer(10, 5, 5, Color.BLACK);
				canvas.drawText(title, point.x - title.length() * 9, point.y + 20, paintText); // 绘制文本
			}

			super.draw(canvas, mapView, shadow);
			// 调整一个drawable边界，使得（0，0）是这个drawable底部最后一行中心的一个像素
			boundCenterBottom(marker);
		}

		@Override
		protected OverlayItem createItem(int i) {
			return mGeoList.get(i);
		}

		@Override
		public int size() {
			return mGeoList.size();
		}
	}

}