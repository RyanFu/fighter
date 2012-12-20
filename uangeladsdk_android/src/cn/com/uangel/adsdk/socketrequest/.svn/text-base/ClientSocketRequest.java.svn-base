package cn.com.uangel.adsdk.socketrequest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import cn.com.uangel.adsdk.entity.Advertisement;
import cn.com.uangel.adsdk.util.CommunalData;
import cn.com.uangel.adsdk.util.Util;

/**
 * 
 * @author dev 手机端通过socket向服务器发送请求传递参数
 */
public class ClientSocketRequest {

	private Socket socket = null;

	private String serverIP = "202.152.184.94";

	public static ClientSocketRequest m_Client_Socket_Request = null;

	private DataInputStream in;
	private DataOutputStream out;

	private Vector<Advertisement> ads;

	/**
	 * 
	 * @return 返回连接socket对象
	 */
	public static ClientSocketRequest getIntence() {
		if (m_Client_Socket_Request == null) {
			m_Client_Socket_Request = new ClientSocketRequest();
		}
		return m_Client_Socket_Request;
	}

	private void writeUTF(String utfStr) throws IOException {
		out.writeUTF(utfStr);
		out.flush();
	}

	private String readUTF() throws IOException {
		return in.readUTF();
	}

	public synchronized Vector<Advertisement> queryServerData(Context context, int adType) throws Exception {
		if (CommunalData.city_code.equals("")) {
			Util.initAll(context);
		}

		// 暂时不考虑net_state的问题了，因为没有网络会接受不到数据，效果是一样的
		// if (!CommunalData.net_state) {
		// return null;
		// }
		socket = new Socket(serverIP, 2022);
		in = new DataInputStream(new BufferedInputStream(socket.getInputStream(), 8 * 1024));
		out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 8 * 1024));
		writeUTF("RequestAD");
		out.flush();

		readUTF(); // "OK"

		String netType = "WIFI".equals(CommunalData.net_type) ? "1" : "0";
		out.writeUTF(CommunalData.AppId + "‖" + CommunalData.Imei + "‖" + CommunalData.service_prv + "‖"
				+ CommunalData.phone_type + "‖" + netType + "‖" + adType + "‖" + CommunalData.packagename + "‖"
				+ CommunalData.city_code + "‖" + CommunalData.longitude + "‖" + CommunalData.latitude);
		// System.out.println(CommunalData.phone_type);
		// System.out.println("netType: " + CommunalData.net_type);
		out.flush();

		String okADsStr = in.readUTF(); // 满足条件的广告字符串
		if ("No ADs".equals(okADsStr)) {
			Log.e("UangelAD", "No ADs");
			return null;
		}
		String[] okADs = okADsStr.split("︴");
		// System.out.println(okADsStr);
		ads = new Vector<Advertisement>();
		for (String adStr : okADs) {
			System.out.println("adStr: " + adStr);
			String[] okAD = adStr.split("‖");
			Advertisement ad = new Advertisement();
			ad.setShowTypeID(okAD[0]);
			ad.setAdID(okAD[1]);
			ad.setAdTitle(okAD[3]);
			ad.setDetail(okAD[6]);
			String titleOrImg = okAD[2].split(":")[1];
			int toi = 0;
			if ("显示文字＋图片".equals(titleOrImg)) {
				toi = 0;
			} else if ("仅显示图片".equals(titleOrImg)) {
				toi = 1;
			} else if ("全屏图片".equals(titleOrImg)) {
				toi = 2;
			} else if ("视频动画".equals(titleOrImg)) {
				toi = 3;
			}
			ad.setTitleOrImg(toi);

			if (okAD[5] != null && !okAD[5].equals("")) {
				int click_result = 0;
				if (okAD[5].equals("手机网络")) {
					click_result = 1;
					ad.setWeb_url(okAD[7]);
				} else if (okAD[5].equals("Android程序")) {
					click_result = 2;
					ad.setWeb_url(okAD[7]);
				} else if (okAD[5].equals("OPhone程序")) {
					click_result = 2;
					ad.setWeb_url(okAD[7]);
				} else if (okAD[5].equals("点击通话")) {
					click_result = 3;
					ad.setTel(okAD[7]);
				} else if (okAD[5].equals("发送短信")) {
					click_result = 4;
					String[] msgInfo = okAD[7].split("¿");
					ad.setTel(msgInfo[0]);
					ad.setSms(msgInfo[1]);
				} else if (okAD[5].equals("发送邮件")) {
					click_result = 5;
					String[] emailInfo = okAD[7].split("¿");
					ad.setEmail(new String[] { emailInfo[0] });
					ad.setEmail_title("安捷乐广告");
					ad.setEmail_content(emailInfo[1]);
				} else if (okAD[5].equals("显示GoogleMap地址")) {
					String[] addressInfo = okAD[7].split(":");
					click_result = 6;
					ad.setLon(Double.parseDouble(addressInfo[2]));
					ad.setLat(Double.parseDouble(addressInfo[1]));
					ad.setWeb_url(addressInfo[0]);
				} else if (okAD[5].equals("视频动画")) {
					click_result = 7;
				} else if (okAD[5].equals("播放音乐")) {
					click_result = 8;
					ad.setMp3_url(okAD[7]);
				} else if (okAD[5].equals("全屏图片")) {
					click_result = 9;
					ad.setClickFullImg(Drawable.createFromStream(in, null));
				}
				ad.setClick_result(click_result);
			}

			switch (adType) {
			case 1:// 条幅型
			case 3:// 悬浮型
				ad.setImages(Drawable.createFromStream(in, null));
				break;
			case 2:// 插屏型
				switch (toi) {
				case 2:// 全屏图片
					ad.setImages(Drawable.createFromStream(in, null));
					break;
				case 3:// 视频动画
					// ad.setImages(Drawable.createFromStream(in, null));
					break;
				}
			}

			ads.add(ad);
		}

		socket.close();
		return ads;
	}

	public synchronized void sendClickInfo(Advertisement ad) {
		if (ad == null)
			return;
		try {
			socket = new Socket(serverIP, 2022);
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream(), 8 * 1024));
			out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 8 * 1024));
			writeUTF("ClickAD");
			writeUTF(CommunalData.AppId + "‖" + ad.getAdID() + "‖" + CommunalData.Imei + "‖" + CommunalData.city_code
					+ "‖" + ad.getShowTypeID());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
