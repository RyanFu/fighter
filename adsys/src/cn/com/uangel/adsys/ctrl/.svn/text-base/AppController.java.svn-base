package cn.com.uangel.adsys.ctrl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.AppGroundStatInfo;
import cn.com.uangel.adsys.entity.AppStatInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.service.AppService;
import cn.com.uangel.adsys.service.impl.AppServiceImpl;
import cn.com.uangel.adsys.util.AjaxUtils;
import cn.com.uangel.adsys.util.StringUtil;

public class AppController {
	AppService as = new AppServiceImpl();

	/**
	 * 保存程序的基本信息
	 */
	public String saveAppBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		App app = new App();
		String appName = request.getParameter("name");
		String appType = request.getParameter("type");
		Date nowDate = new Date();
		String date = nowDate.getTime() + "";
		String appPlatform = request.getParameter("appPlatform");
		String ranStr = StringUtil.getRandomString();
		app.setApp_name(appName);
		app.setApp_type(appType);
		app.setCreate_time(nowDate);

		app.setApp_platform(appPlatform);

		String appId = StringUtil.getAppIDStr(date + appType + appPlatform + StringUtil.getMemberIDString(1) + ranStr);
		app.setPid(appId);

		app.setMem_id(((Member) request.getSession().getAttribute("member")).getId());

		app.setApp_state("待审批");
		
//		if(isFlush!=null && isFlush){
//			as.addAppWithBacicInfo(app);
//		}
		request.getSession().setAttribute("currentApp", app);
		return "2:jsp/app/okinfo.jsp";
	}

	/**
	 * 显示当前登录用户的所有程序
	 */
	public String showApps(HttpServletRequest request, HttpServletResponse response) {

		List<App> appList = as.getAppsByMemId(((Member) request.getSession().getAttribute("member")).getId(), null);
		String appListStr = AjaxUtils.jsonString(appList);
		request.setAttribute("appListStr", appListStr);
		return "1:jsp/app/showapp.jsp";
	}

	/**
	 * 设置统计条件
	 */
	public String setStatCondition(HttpServletRequest request, HttpServletResponse response) {

		List<App> appList = as.getAppsByMemId(((Member) request.getSession().getAttribute("member")).getId(), "通过");
		request.setAttribute("appList", appList);
		return "1:jsp/app/setstatcondition.jsp";
	}

	/**
	 * 显示统计结果
	 */
	public String showStatResult(HttpServletRequest request, HttpServletResponse response) {

		String[] appIds = request.getParameterValues("apps");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String statWay = request.getParameter("statWay");
		// String gatherWay = request.getParameter("gatherWay");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<AppStatInfo> statList = null;
		try {
			statList = as.getStatInfo(appIds, startDate.equals("") ? null : sdf.parse(startDate),
					endDate.equals("") ? null : sdf.parse(endDate), statWay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String appStatStr = AjaxUtils.jsonString(statList);
		request.setAttribute("appStatStr", appStatStr);
		return "1:jsp/app/showstatinfo.jsp";
	}

	/**
	 * 显示统计结果
	 */
	public String showGroundStatResult(HttpServletRequest request, HttpServletResponse response) {

		String[] appIds = request.getParameterValues("apps");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String statWay = request.getParameter("statWay");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<AppGroundStatInfo> groundStatList = null;
		try {
			groundStatList = as.getGroundStatInfo(appIds, startDate.equals("") ? null : sdf.parse(startDate), endDate
					.equals("") ? null : sdf.parse(endDate), statWay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String appStatStr = AjaxUtils.jsonString(groundStatList);
		request.setAttribute("appStatStr", appStatStr);
		return "1:jsp/app/showgroundstatinfo.jsp";
	}

	public String deleteAppByAppID(HttpServletRequest request, HttpServletResponse response) {
		String appID = request.getParameter("appID");
		as.removeByAppID(appID);
		return "2:ctrl/showApps.app";
	}

	@SuppressWarnings("unchecked")
	public String uploadApk(HttpServletRequest request, HttpServletResponse response) {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload up = new ServletFileUpload(factory);
			List<FileItem> ls = up.parseRequest(request);

			for (FileItem fileItem : ls) {
				if (fileItem.isFormField()) {
					String FieldName = fileItem.getFieldName();

					// getName()返回的是文件名字 普通域没有文件 返回NULL
					// String Name = fileItem.getName();
					String content = fileItem.getString("utf-8");
					request.setAttribute(FieldName, content);
				} else {
					String fileFullName = fileItem.getName();
					// 得到所有上传的文件
					Iterator fileItr = ls.iterator();
					// 循环处理所有文件
					while (fileItr.hasNext()) {

						// 得到当前文件
						fileItem = (FileItem) fileItr.next();
						// 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
						/*
						 * if (fileItem == null || fileItem.isFormField()) {
						 * continue; }
						 */
					}
					int pointIndex = fileFullName.lastIndexOf('.');
					int startIndex = fileFullName.lastIndexOf('\\')+1;
					String fileName;
					if (startIndex != -1) {
						fileName = fileFullName.substring(startIndex, pointIndex) + System.currentTimeMillis();
					} else {
						fileName = fileFullName.substring(0, pointIndex) + System.currentTimeMillis();
					}
					String postfix = fileFullName.substring(pointIndex, fileFullName.length());
					request.getSession().setAttribute("currentAppFile", fileItem);
					AjaxUtils.sendJSON(response, "ok" + fileName + postfix);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	public String checkPackageNameExist(HttpServletRequest request, HttpServletResponse response) {
		String packageName = request.getParameter("pakage_name");
		String pid = request.getParameter("pid");
		boolean resultApkPackage = as.isPackageNameExist(packageName,pid);
		if (resultApkPackage) {
			AjaxUtils.sendJSON(response, "true");
		} else {
			AjaxUtils.sendJSON(response, "false");
		}
		return "3:ajax";

	}

	public String saveAppApkInfo(HttpServletRequest request, HttpServletResponse response) {
		App appBasic=(App)request.getSession().getAttribute("currentApp");
		String apkname = request.getParameter("apkname");
		String pakage_name = request.getParameter("pakage_name");
		if (appBasic!=null) {
			appBasic.setApk_url(apkname);
			appBasic.setPakage_name(pakage_name);
			appBasic.setApp_state("待审批");
			as.addAppWithBacicInfo(appBasic);
			request.getSession().removeAttribute("currentApp");
			// 保存
			String path = request.getSession().getServletContext().getRealPath("/files/app");
			createDir(path, request);
			FileItem fileItem = (FileItem) request.getSession().getAttribute("currentAppFile");
			File mkr = new File(path, apkname);
			try {
				if (mkr.createNewFile()) {
					fileItem.write(mkr);
					request.getSession().removeAttribute("currentAppFile");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}// end
			}
			
		

		return "2:jsp/app/apkuploaduccess.jsp";
	}

	/**
	 * 点击编辑按钮后进入
	 * @param request
	 * @param response
	 * @return
	 */
	public String editAppInfo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("ID"));
		App app = as.getAppByIdAndChangeState(id);

		request.setAttribute("Id", app.getId());
		request.setAttribute("name", app.getApp_name());
		request.setAttribute("AppType", app.getApp_type());
		request.setAttribute("AppPlatform", app.getApp_platform());
		return "1:jsp/app/saveappinfo.jsp";
	}
	/**
	 * 点击查看按钮后进入
	 * @param request
	 * @param response
	 * @return
	 */
	public String checkAppInfo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("ID"));
		App app = as.getAppById(id);
		request.setAttribute("pid", app.getPid());
		request.setAttribute("name", app.getApp_name());
		request.setAttribute("appType", app.getApp_type());
		request.setAttribute("appPlatform", app.getApp_platform());
		request.setAttribute("creatTime", app.getCreate_time());
		return "1:jsp/app/checkAppInfo.jsp";
	}
	
	public String updateBasicAppInfo(HttpServletRequest request, HttpServletResponse response) {
		
		AppService appService=new AppServiceImpl();
		int id = Integer.parseInt(request.getParameter("Id"));
		App app = as.getAppByIdAndChangeState(id);

		
		String appName = request.getParameter("name");
		String appType = request.getParameter("type");
		String appPlatform = request.getParameter("appPlatform");
		
		app.setApp_name(appName);
		app.setApp_type(appType);
		app.setApp_platform(appPlatform);
		
		appService.modifyApp(app);
		request.setAttribute("id", app.getId());
		request.getSession().setAttribute("currentApp", app);
		
		return "1:jsp/app/okinfo.jsp";
	}
	/*
	 * 编辑okinfo 页面确定  -更新packageName
	 */
	public String updateOkInfo(HttpServletRequest request, HttpServletResponse response) {
		App appBasic=(App)request.getSession().getAttribute("currentApp");
		String apkname = request.getParameter("apkname");
		String pakage_name = request.getParameter("pakage_name");
		if (appBasic!=null) {
			appBasic.setApk_url(apkname);
			appBasic.setPakage_name(pakage_name);
			appBasic.setApp_state("待审批");
			
			// 保存
			boolean isEdited= true;
			App isEditApp=as.getAppById(appBasic.getId());
			if(apkname.equals(isEditApp.getApk_url())){
				isEdited= false;
			}
			if(isEdited){
				String path = request.getSession().getServletContext().getRealPath("/files/app");
				createDir(path, request);
				FileItem fileItem = (FileItem) request.getSession().getAttribute("currentAppFile");
				File mkr = new File(path, apkname);
				File deleteOldApk = new File(path, isEditApp.getApk_url());
				deleteOldApk.delete();
				try {
					if (mkr.createNewFile()) {
						fileItem.write(mkr);
						request.getSession().removeAttribute("currentAppFile");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}// end
			}
			as.modifyApp(appBasic);
			request.getSession().removeAttribute("currentApp");
			
		}

		return "2:jsp/app/apkuploaduccess.jsp";
	}

	private void createDir(String path, HttpServletRequest request) {
		File p = new File(request.getSession().getServletContext().getRealPath("/files"));
		if (!p.exists()) {
			p.mkdir();
		}
		p = new File(path);
		if (!p.exists()) {
			p.mkdir();
		}
	}

}
