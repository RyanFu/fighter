package cn.com.uangel.adsys.ctrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.MSBook;
import cn.com.uangel.adsys.entity.MSHanatourPushInfo;
import cn.com.uangel.adsys.entity.MSObject;
import cn.com.uangel.adsys.service.TestService;
import cn.com.uangel.adsys.test.Student;
import cn.com.uangel.adsys.util.AjaxUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestConctroller {
	TestService ts = new TestService();

	public String testMethod(HttpServletRequest request, HttpServletResponse response) {

		AD ad = new AD();
		ad.setName(request.getParameter("name"));
		ADArs adars = new ADArs();
		adars.setArs_name(request.getParameter("arsName"));
		adars.setAd_id(1);

		ts.addAdAndArs(ad, adars);

		return "2:index.jsp";
	}

	public String testAjax(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		System.out.println(username);
		if ("admin".equals(username)) {
			AjaxUtils.sendJSON(response, "true");
		} else {
			AjaxUtils.sendJSON(response, "false");
		}
		return "3:ajax";
	}

	public String testAjaxTable(HttpServletRequest request, HttpServletResponse response) {
		List<Student> list = new ArrayList<Student>();
		Student s1 = new Student(1, "张三", 22);
		Student s2 = new Student(2, "李四", 23);
		Student s3 = new Student(3, "王五", 24);
		Student s4 = new Student(4, "贾六", 25);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		AjaxUtils.sendJSON(response, list);

		return "3:ajax";
	}

	public String testAddAd(HttpServletRequest request, HttpServletResponse response) {
		AD ad = new AD();
		ad.setName(request.getParameter("name"));
		ad.setType(request.getParameter("type"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(request.getParameter("startDate"));
			Date endDate = sdf.parse(request.getParameter("endDate"));
			ad.setStart_date(startDate);
			ad.setEnd_date(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// ad.setMax_pay_perday(Integer.parseInt(request.getParameter(
		// "maxPayPerDay")));

		// 存储广告所选平台
		String[] appPlatform = request.getParameterValues("appPlatform");
		char[] temp = "00".toCharArray();
		for (int i = 0; appPlatform != null && i < appPlatform.length; i++) {
			if ("1".equals(appPlatform[i])) {
				temp[0] = '1';
			} else if ("2".equals(appPlatform[i])) {
				temp[1] = '1';
			}
		}
		String apf = String.valueOf(temp);
		ad.setApp_platform(apf);

		ad.setState("未完成");
		ad.setMem_id(1);

		ts.addAd(ad);
		return "1:index.jsp";
	}

	public String testAndroid(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getContextPath();
		System.out.println("hello");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		try {
			response.getOutputStream().write((basePath + "file/ad/logo.png").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testSend(HttpServletRequest request, HttpServletResponse response) {
		return "1:ctrl/test.test";
	}

	public String test(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getRemoteAddr() + ":" + request.getServerPort() + "/";
		if (path != null && path.contains("adsys")) {
			basePath += path.substring(1) + "/";
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(basePath);
		return "";
	}

	public String testMSBook(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testMSBook....");
		MSObject obj = new MSObject();
		List<MSBook> bookList = ts.getAllMSBooks(0, 0, "ms_book");
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testMSBookLevel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testMSBookLevel....");
		MSObject obj = new MSObject();
		List<MSBook> bookList = ts.getAllMSBooks(0, 0, "ms_book_level");
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testMagazineChurch(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testMagazineChurch....");
		MSObject obj = new MSObject();
		List<MSBook> bookList = ts.getAllMSBooks(0, 0, "magazine_church");
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}
	
	public String testMagazineArtist(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testMagazineArtist....");
		MSObject obj = new MSObject();
		List<MSBook> bookList = ts.getAllMSBooks(0, 0, "magazine_artist");
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testMagazineKoreaCulture(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testMagazineKoreaCulture....");
		MSObject obj = new MSObject();
		List<MSBook> bookList = ts.getAllMSBooks(0, 0, "magazine_church");
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testMagazineAudioAlbum(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testMagazineAudioAlbum....");
		String code = request.getParameter("serialcode");

		MSObject obj = new MSObject();
		MSBook book = ts.getMSBooksBySerialCode(0, 0, "magazine_audio_album", code);
		List<MSBook> bookList = new ArrayList<MSBook>();
		if (book != null) {
			bookList.add(book);
		}
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testHanatour(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testHanatour....");
		String code = request.getParameter("serialcode");

		MSObject obj = new MSObject();
		MSBook book = ts.getMSBooksBySerialCode(0, 0, "magazine_hanatour", code);
		List<MSBook> bookList = new ArrayList<MSBook>();
		if (book != null) {
			bookList.add(book);
		}
		obj.setCount(bookList.size());
		obj.setBooks(bookList);
		obj.setLastDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		// String objStr = AjaxUtils.jsonString(obj);
		String objStr = getStringToJson(obj);
		System.out.println(objStr);

		try {
			response.getWriter().print(objStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String testHanatourAddTokenInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testHanatourAddTokenInfo....");
		String token = request.getParameter("token");
		String deviceType = request.getParameter("deviceType");

		if (!ts.hanatourTokenIsExist(token)) {
			ts.addTokenInfo(token, deviceType);
		}

		return "3:ajax";
	}
	
	public String testChurchAddTokenInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testChurchAddTokenInfo....");
		String token = request.getParameter("token");
		String deviceType = request.getParameter("deviceType");

		if (!ts.churchTokenIsExist(token)) {
			ts.addChurchTokenInfo(token, deviceType);
		}

		return "3:ajax";
	}

	public String testHanatourPushNotice(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testHanatourPushNotice....");
		String count = request.getParameter("count");
		String msg = request.getParameter("msg");
		System.out.println(msg);
		
		if (count != null) {
			int newMagazineCount = Integer.parseInt(count);
			List<MSHanatourPushInfo> tokenInfos = ts.getAllTokenInfo();
			System.out.println("size: " + tokenInfos.size());
			
			PayLoad payLoad = new PayLoad();
			try {
				payLoad.addAlert(msg);
				payLoad.addBadge(newMagazineCount);
				payLoad.addSound("default");

				PushNotificationManager pushManager = PushNotificationManager.getInstance();
				
				// Connect to APNs
				String host = "gateway.sandbox.push.apple.com";
				int port = 2195;
				String certificatePath = "/root/HanatourDev.p12";
//				String certificatePath = "C:\\HanatourDev.p12";
				String certificatePassword = "hanatourUaNgEl(4";
				pushManager.initializeConnection(host, port, certificatePath, certificatePassword,
						SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
				
				for (MSHanatourPushInfo pushInfo : tokenInfos) {
					System.out.println(pushInfo.getToken());
					// Send Push
					pushManager.addDevice(pushInfo.getDevice_type(), pushInfo.getToken());
					Device client = pushManager.getDevice(pushInfo.getDevice_type());
					pushManager.sendNotification(client, payLoad);
					pushManager.removeDevice(pushInfo.getDevice_type());
				}
				
				pushManager.stopConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "2:jsp/test/hanatourpushsuccess.jsp";
	}
	
	public String testChurchPushNotice(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in testChurchPushNotice....");
		String count = request.getParameter("count");
		String msg = request.getParameter("msg");
		
		if (count != null) {
			int newMagazineCount = Integer.parseInt(count);
			List<MSHanatourPushInfo> tokenInfos = ts.getAllTokenInfoByTableName("magazine_church_push");
			System.out.println("size: " + tokenInfos.size());
			
			PayLoad payLoad = new PayLoad();
			try {
				payLoad.addAlert(msg);
				payLoad.addBadge(newMagazineCount);
				payLoad.addSound("default");

				PushNotificationManager pushManager = PushNotificationManager.getInstance();
				
				// Connect to APNs
				String host = "gateway.sandbox.push.apple.com";
				int port = 2195;
				String certificatePath = "/root/church.p12";
//				String certificatePath = "C:\\church.p12";
				String certificatePassword = "UaNgEl67717556";
				pushManager.initializeConnection(host, port, certificatePath, certificatePassword,
						SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
				
				for (MSHanatourPushInfo pushInfo : tokenInfos) {
					System.out.println(pushInfo.getToken());
					// Send Push
					pushManager.addDevice(pushInfo.getDevice_type(), pushInfo.getToken());
					Device client = pushManager.getDevice(pushInfo.getDevice_type());
					pushManager.sendNotification(client, payLoad);
					pushManager.removeDevice(pushInfo.getDevice_type());
				}
				
				pushManager.stopConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "2:jsp/test/hanatourpushsuccess.jsp";
	}

	public static String getStringToJson(MSObject bookObj) {
		Type type = null;
		Gson gson = new Gson();
		if (bookObj != null) {
			type = new TypeToken<MSObject>() {
			}.getType();
		}
		String beanListToJson = gson.toJson(bookObj, type);

		return beanListToJson;
	}

	/**
	 * 文件下载
	 */
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取下载文件路径
			String path = request.getSession().getServletContext().getRealPath("magazines") + File.separator
					+ request.getParameter("file");
			// 编码
			path = URLEncoder.encode(path, "ISO-8859-1");
			// 解码
			path = URLDecoder.decode(path, "UTF-8");
			File file = new File(path);
			// 文件名
			String fileName = file.getName();
			// 扩展名
			@SuppressWarnings("unused")
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
			// 输入流
			InputStream inStream = new FileInputStream(path);
			InputStream in = new BufferedInputStream(inStream);
			byte[] bs = new byte[in.available()];
			in.read(bs);
			in.close();
			// 清空response
			response.reset();
			// 设置response的Header
			// 使浏览器弹出下载对话框
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			// 输出流
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(bs);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
		}
		return "3:ajax";
	}
}
