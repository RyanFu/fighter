package cn.com.uangel.adsdk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
@SuppressWarnings("unused")
public class Orientation{

	public static String orientationByStation(Context context) {
		JSONObject jsonObject1 = null;
		String cityname = "";
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
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

			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					15000);
			post.setEntity(se);

			HttpResponse resp = null;
			try {
				resp = client.execute(post);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (resp != null && resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = resp.getEntity();

				BufferedReader br = new BufferedReader(new InputStreamReader(
						entity.getContent()));
				StringBuffer sb = new StringBuffer();
				String result = br.readLine();

				while (result != null) {

					sb.append(result);
					result = br.readLine();
				}
				JSONObject jsonObject = new JSONObject(sb.toString());

				jsonObject1 = new JSONObject(jsonObject.getString("location"));

				double latitude = Double.parseDouble(jsonObject1
						.getString("latitude"));
				double longitude = Double.parseDouble(jsonObject1
						.getString("longitude"));

				cityname = getAddressByLatLng(latitude, longitude);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cityname;
	}


	public static String getAddressByLatLng(double lat, double lng) {
		String address = null;
		JSONObject jsonObject = geocodeAddr(lat, lng);
		try {
			JSONArray placemarks = jsonObject.getJSONArray("Placemark");
			JSONObject place = placemarks.getJSONObject(0);

			JSONObject AddressDetails = place.getJSONObject("AddressDetails");
			JSONObject Country = (JSONObject) AddressDetails.get("Country");
			
			JSONObject AdministrativeArea = (JSONObject) Country.get("AdministrativeArea");//省
			address = AdministrativeArea.toString();
			
			JSONObject Locality = (JSONObject) AdministrativeArea.get("Locality");//城市
			
			JSONObject DependentLocality = (JSONObject) Locality.get("DependentLocality");//区县
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	private static JSONObject geocodeAddr(double lat, double lng) {
		String urlString = "http://ditu.google.com/maps/geo?q=+" + lat + ","
				+ lng + "&output=json&oe=utf8&hl=zh-CN&sensor=false";
		JSONObject jsonObject = new JSONObject();
		/*
		 * String urlString =
		 * "http:maps.google.com/maps/api/geocode/json?latlng="
		 * +lat+","+lng+"&language=zh_CN&sensor=false";
		 */
		StringBuilder sTotalString = new StringBuilder();
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(15000);
			if (conn.getResponseCode() == 200) {
				InputStream urlStream = conn.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(urlStream));
				String sCurrentLine = "";
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString.append(sCurrentLine);
				}
				bufferedReader.close();
				conn.disconnect(); 
				jsonObject = new JSONObject(sTotalString.toString());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return jsonObject;
	}

	private static void getAddressByQQ() {
		try {
			URL url = new URL("http://fw.qq.com/ipaddress");
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "GBK"));
			String line = null;
			while ((line = reader.readLine()) != null)
				System.out.println(line);
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
