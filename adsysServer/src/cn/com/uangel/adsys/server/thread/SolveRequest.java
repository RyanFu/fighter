package cn.com.uangel.adsys.server.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import cn.com.uangel.adsys.server.DataManager;
import cn.com.uangel.adsys.util.Log;

/**
 * 处理请求广告的线程
 * 
 * @author Tree
 * 
 */
public class SolveRequest extends Thread {
	private Socket socket;
	private DataManager dataManager;
	private DataInputStream in;
	private DataOutputStream out;
	// private String adImgFolder = "/root/ad.uangel.com.cn/files/ad/";
	private String adImgFolder = "D:\\Program Files\\apache-tomcat-6.0.14\\webapps\\adsys\\files\\ad\\";
	private String adFullADFolder = "D:\\Program Files\\apache-tomcat-6.0.14\\webapps\\adsys\\files\\fullad\\";

	public SolveRequest(Socket socket, DataManager dataManager) throws IOException {
		this.socket = socket;
		this.dataManager = dataManager;
		in = new DataInputStream(new BufferedInputStream(this.socket.getInputStream(), 8 * 1024));
		out = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream(), 8 * 1024));
		start();
	}

	private void writeUTF(String utfStr) throws IOException {
		out.writeUTF(utfStr);
		out.flush();
	}

	private String readUTF() throws IOException {
		return in.readUTF();
	}

	private void writeByte(String filePath) throws IOException {
		DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));

		int bufferSize = 8192;
		byte[] buf = new byte[bufferSize];
		int read = 0;
		int size = 0;
		while ((read = fis.read(buf)) != -1) {
			out.write(buf, 0, read);
			size += read;
		}
		Log.debug(size);
		out.flush();

		fis.close();
	}

	@Override
	public void run() {
		try {
			long start = System.currentTimeMillis();

			String what = in.readUTF();
			if (what != null) {
				writeUTF("ok"); // 直接读两次时间会很慢，有时间查查为什么
			}
			Log.debug("Do what: " + what);
			if ("RequestAD".equals(what)) {
				String requestMsg = readUTF();
				Log.debug("requestMsg: " + requestMsg);
				String returnMsg = dataManager.parseRequestMessage(requestMsg);
				// Log.debug("returnMsg: " + returnMsg);
				writeUTF(returnMsg);
				if (!"No ADs".equals(returnMsg)) {
					String[] adInfos = requestMsg.split("‖");
					int typeCode = Integer.parseInt(adInfos[5]);
					Log.debug("typeCode: " + typeCode);
					switch (typeCode) {
					case 1: // 条幅型，请求1条
						String[] adMsgs = returnMsg.split("‖");
						if ("全屏图片".equals(adMsgs[5])) {
							writeByte(adFullADFolder + adMsgs[7]);
						}
						writeByte(adImgFolder + adMsgs[4]);
						break;
					case 2:// 插屏型，请求一条
						// 没测试
						String[] fullMsgs = returnMsg.split("‖");
						Log.debug("path: " + adFullADFolder + fullMsgs[4]);
						writeByte(adFullADFolder + fullMsgs[4]);
						break;
					case 3:// 悬浮型，请求N条
						String[] adsStr = returnMsg.split("︴");
						for (String adStr : adsStr) {
							String[] noName = adStr.split("‖");
							writeByte(adImgFolder + noName[4]);
						}
						Log.debug("imgCount: " + adsStr.length);
						break;
					}
				}
			} else if ("ShowAD".equals(what)) {

			} else if ("ClickAD".equals(what)) {
				String clickMsg = readUTF();
				String returnMsg = dataManager.parseClickMessage(clickMsg);
				writeUTF(returnMsg);
			}
			Log.debug("Solving time: " + (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			Log.error(e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
}
