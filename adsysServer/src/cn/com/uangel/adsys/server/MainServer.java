package cn.com.uangel.adsys.server;

import java.net.ServerSocket;
import java.net.Socket;

import cn.com.uangel.adsys.server.thread.SolveRequest;
import cn.com.uangel.adsys.util.Log;

public class MainServer {

	private static DataManager dataManager;
	private static ServerSocket ss;

	public static void main(String[] args) {
		Log.info("服务器已开启！");
		dataManager = new DataManager();
		dataManager.init();

		run();
	}

	public static void run() {
		try {
			ss = new ServerSocket(2022);

			while (true) {
				Socket s = ss.accept();
				try {
					new SolveRequest(s, dataManager);
				} catch (Exception e) {
					try {
						s.close();
					} catch (Exception e1) {
						Log.error(e1, "Close socket failed.");
					}
				}
			}
		} catch (Exception e) {
			Log.error(e);
			dataManager.saveDataWhenException();
		} finally {
			try {
				ss.close();
			} catch (Exception e) {
				Log.error(e, "ServerSocket closed failed.");
			}
		}
	}

}
