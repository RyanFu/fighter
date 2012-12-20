package cn.com.uangel.adsdk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

public class Util {
	/**
	 * 读取AndroidManifest AppId
	 */
	public static void getAppId(Context context) {
		ApplicationInfo localPackageManager;
		try {
			localPackageManager = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
			CommunalData.AppId = localPackageManager.metaData.getString("Uangel_APPID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getPhoneInfo(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		if (tm.getSubscriberId() != null) {
			if (tm.getSubscriberId().startsWith("46000") || tm.getSubscriberId().startsWith("46002")) {// 移动
				CommunalData.service_prv = "中国移动";
			} else if (tm.getSubscriberId().startsWith("46001")) {// 联通
				CommunalData.service_prv = "中国联通";
			} else if (tm.getSubscriberId().startsWith("46003")) {// 电信
				CommunalData.service_prv = "中国电信";
			}
		}
		CommunalData.Imei = tm.getDeviceId();
		CommunalData.phone_type = Build.MANUFACTURER;
	}

	public static void getCityCode(String cityname) {
		if (0 == CommunalData.city_code.length()) {
			for (int i = 0; i < CommunalData.province.length; i++) {
				if (cityname.startsWith(CommunalData.province[i])) {
					CommunalData.city_code = CommunalData.provinceCode[i];
					return;
				}
			}
		}
		CommunalData.city_code = "000000";// 其他地区
	}

	public static void getProvinceCode(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (loc != null) {
			parseLocaction(context, loc.getLatitude(), loc.getLongitude());
		} else {
			// 基站定位
			if (!getLocationByGsm(context)) {
				CommunalData.city_code = "000000";// 其他地区
			}
		}
	}

	public static synchronized void parseLocaction(Context context, double lat, double lon) {
		try {
			CommunalData.longitude = lon;
			CommunalData.latitude = lat;
			
			String province = null;
			List<Address> addList = null;
			Geocoder ge = new Geocoder(context);
			boolean happenException = false;
			try {
				addList = ge.getFromLocation(lat, lon, 1);
				if (addList != null && addList.size() > 0) {
					for (int i = 0; i < addList.size(); i++) {
						Address ad = addList.get(i);
						province = ad.getLocality();
					}
				}
			} catch (IOException e) {
				Log.w("UangelAD", e.getMessage());
				happenException = true;
			}
			if (happenException) { // 备用方案，较慢
				String jsonStr = Orientation.getAddressByLatLng(lat, lon);
				// String jsonStr =
				// Orientation.getAddressByLatLng(34.46,113.40);
				JSONObject json = new JSONObject(jsonStr);
				province = json.getString("AdministrativeAreaName");
			}
			getCityCode(province);
		} catch (Exception e) {
			Log.w("UangelAD", e);
			CommunalData.city_code = "000000";// 其他地区
		}
	}

	// 通过基站获取Province Code
	private static boolean getLocationByGsm(Context context) {
		boolean succ = false;
		JSONObject jsonObject1 = null;
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			int mcc = 0, mnc = 0, cid = 0, lac = 0;
			GsmCellLocation gcl = (GsmCellLocation) tm.getCellLocation();

			cid = gcl.getCid();
			lac = gcl.getLac();

			mcc = Integer.valueOf(tm.getNetworkOperator().substring(0, 3));
			mnc = Integer.valueOf(tm.getNetworkOperator().substring(3, 5));

			JSONObject holder = new JSONObject();
			holder.put("version", "1.1.0");
			holder.put("host", "maps.google.com");
			if (mcc == 460) {
				holder.put("address_language", "zh_CN");
			} else {
				holder.put("address_language", "en_US");
			}
			holder.put("request_address", true);

			JSONArray array = new JSONArray();
			JSONObject data = new JSONObject();
			data.put("cell_id", cid); // 25070
			data.put("location_area_code", lac);// 4474
			data.put("mobile_country_code", mcc);// 460
			data.put("mobile_network_code", mnc);// 0
			array.put(data);
			holder.put("cell_towers", array);

			DefaultHttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost("http://www.google.com/loc/json");

			StringEntity se = new StringEntity(holder.toString());

			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
			post.setEntity(se);

			HttpResponse resp = null;
			try {
				resp = client.execute(post);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = resp.getEntity();

				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
				StringBuffer sb = new StringBuffer();
				String result = br.readLine();

				while (result != null) {

					sb.append(result);
					result = br.readLine();
				}
				JSONObject jsonObject = new JSONObject(sb.toString());

				jsonObject1 = new JSONObject(jsonObject.getString("location"));

				Double latitude = Double.parseDouble(jsonObject1.getString("latitude"));
				Double longitude = Double.parseDouble(jsonObject1.getString("longitude"));
				
				CommunalData.longitude = longitude;
				CommunalData.latitude = latitude;

				parseLocaction(context, latitude, longitude);

				succ = true;
			}
		} catch (Exception e) {
			succ = false;
			e.printStackTrace();
		}
		return succ;
	}

	public static void getPackageName(Context context) {
		CommunalData.packagename = context.getPackageName();
	}

	public static void getNetType(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			if (networkInfo.getTypeName().contains("WIFI")) {
				CommunalData.net_type = "WIFI";
				CommunalData.net_state = true;// TODO 遗留问题 不确定
			} else if (networkInfo.getTypeName().contains("mobile")) {
				TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				mTelephonyMgr.listen(new PhoneStateListener() {
					@Override
					public void onDataConnectionStateChanged(int state) {
						switch (state) {
						case TelephonyManager.DATA_DISCONNECTED:// 未连接上
						case TelephonyManager.DATA_CONNECTING:// 正在连接
						case TelephonyManager.DATA_SUSPENDED:// 挂起
							CommunalData.net_state = false;
							CommunalData.net_type = "3G";
							break;
						case TelephonyManager.DATA_CONNECTED:// 已连接
							CommunalData.net_state = true;
							CommunalData.net_type = "3G";
							break;
						}
					}

				}, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
			} else {
				CommunalData.net_state = false;
				CommunalData.net_type = "UNKNOW";
			}

		}
	}

	public static void getAllPublicInfo(Context context) {
		if (0 == CommunalData.service_prv.length()) {
			getPhoneInfo(context);// 服务商 机器号 手机型号
		}
		if (0 == CommunalData.AppId.length()) {
			getAppId(context);// 应用ID
		}
		if (0 == CommunalData.packagename.length()) {
			getPackageName(context);// 应用包名
		}

	}

	public static void initAll(Context context) {
		getAppId(context);
		getPhoneInfo(context);
		getPackageName(context);
		getNetType(context);
		getProvinceCode(context);
	}

}
