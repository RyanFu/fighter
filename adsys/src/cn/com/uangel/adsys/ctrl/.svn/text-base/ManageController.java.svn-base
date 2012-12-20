package cn.com.uangel.adsys.ctrl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.AdCheckOtherInfo;
import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalEdit;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeEdit;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.service.AppService;
import cn.com.uangel.adsys.service.impl.AdCheckServiceImpl;
import cn.com.uangel.adsys.service.impl.AppServiceImpl;
import cn.com.uangel.adsys.service.impl.ManageServiceImpl;
import cn.com.uangel.adsys.util.AjaxUtils;


public class ManageController {
	ManageServiceImpl msi = new ManageServiceImpl();
	AdCheckServiceImpl asi=new AdCheckServiceImpl();
	AppService apps=new AppServiceImpl();

	/** 1:待审 2：未通过 3: 已成功 */
	public String rechargeInfoShow(HttpServletRequest request, HttpServletResponse response) {

		String state = 1 + "";
		List<RechargeInfo> rechList = msi.getRechargesByCondition(state, null, null, null);
		String rechListString = AjaxUtils.jsonString(rechList);
		request.setAttribute("rechListString", rechListString);
		request.setAttribute("rechList", rechList);
		return "1:jsp/manage/rechargeinfo.jsp";

	}

	/** 1:待审 2：未通过 3: 已成功 */
	public String rechargeInfoSearch(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String ord_number = request.getParameter("ord_number");

		String state = request.getParameter("state");
		List<RechargeInfo> rechList = msi.getRechargesByCondition(state, startDate, endDate, ord_number);
		String rechListString = AjaxUtils.jsonString(rechList);
		request.setAttribute("rechListString", rechListString);
		request.setAttribute("rechList", rechList);
		request.setAttribute("state", state);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		return "1:jsp/manage/rechargeinfo.jsp";
	}

	public String rechargeInfoEdit(HttpServletRequest request, HttpServletResponse response) {
		int rechId = Integer.parseInt(request.getParameter("rechId"));
		List<RechargeEdit> rechEditList = msi.getEditRechargeById(rechId);
		if (rechEditList != null) {
			request.setAttribute("email", rechEditList.get(0).getEmail());
			request.setAttribute("name", rechEditList.get(0).getName());
			request.setAttribute("ord_number", rechEditList.get(0).getOrd_number());
			request.setAttribute("recharge_count", rechEditList.get(0).getRecharge_count());
			request.setAttribute("real_count", rechEditList.get(0).getReal_count());
			request.setAttribute("state", rechEditList.get(0).getState());
			request.setAttribute("recharge_time", rechEditList.get(0).getRecharge_time());
			request.setAttribute("bank", rechEditList.get(0).getBank_num());
			request.setAttribute("rechId", rechId);
		}
		return "1:jsp/manage/rechargeedit.jsp";
	}

	public String rechargeInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		int rechId = Integer.parseInt(request.getParameter("rechIdEdit"));
		Double real_count = Double.parseDouble(request.getParameter("real_count"));
		String state = request.getParameter("state");
		RechargeInfo rech = new RechargeInfo();
		rech.setId(rechId);
		rech.setReal_count(real_count);
		rech.setState(state);
		msi.modifyRechargeEdit(rech);
		return "2:ctrl/rechargeInfoShow.man";
	}

	public String paypalInfoShow(HttpServletRequest request, HttpServletResponse response) {

		String state = 1 + "";
		List<PaypalInfo> paypalList = msi.getPaypalByCondition(state, null, null, null);
		String paypalListString = AjaxUtils.jsonString(paypalList);
		request.setAttribute("paypalListString", paypalListString);
		return "1:jsp/manage/cashoutinfo.jsp";
	}

	/** 1:待审 2：未通过 3: 已成功 */
	public String paypalInfoSearch(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String bod_number = request.getParameter("bod_number");
		String state = request.getParameter("state");

		List<PaypalInfo> payList = msi.getPaypalByCondition(state, startDate, endDate, bod_number);
		String paypalListString = AjaxUtils.jsonString(payList);
		request.setAttribute("paypalListString", paypalListString);
		request.setAttribute("payList", payList);
		request.setAttribute("state", state);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		return "1:jsp/manage/cashoutinfo.jsp";
	}

	/*
	 * 提现记录检查页面
	 */
	public String paypalInfoEdit(HttpServletRequest request, HttpServletResponse response) {
		int rechId = Integer.parseInt(request.getParameter("rechId"));
		List<PaypalEdit> payEditList = msi.getEditPaypalById(rechId);
		if (payEditList != null) {
			request.setAttribute("email", payEditList.get(0).getEmail());
			request.setAttribute("name", payEditList.get(0).getName());
			request.setAttribute("account_attr", payEditList.get(0).getAccount_attr());
			request.setAttribute("invoice_able", payEditList.get(0).getInvoice_able());
			request.setAttribute("bod_number", payEditList.get(0).getBod_number());
			request.setAttribute("apply_amount", payEditList.get(0).getApply_amount());// 申请的金额
			request.setAttribute("allow_amount", payEditList.get(0).getAllow_amount());// 允许的实际金额
			request.setAttribute("income_balance", payEditList.get(0).getIncome_balance());// 会员账户余额
			request.setAttribute("apply_time", payEditList.get(0).getApply_time());
			request.setAttribute("account_number", payEditList.get(0).getAccount_number());
			request.setAttribute("paypalId", rechId);
		}
		return "1:jsp/manage/cashoutedit.jsp";
	}
/**
 * 提现记录更新页面
 * @param request
 * @param response
 * @return
 */
	public String paypalInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		int paypalId = Integer.parseInt(request.getParameter("paypalIdEdit"));
		Double allow_amount = Double.parseDouble(request.getParameter("allow_amount"));
		PaypalInfo pay = new PaypalInfo();
		pay.setId(paypalId);
		pay.setAllow_amount(allow_amount);
		pay.setPay_date(new java.util.Date());
		pay.setState(3 + "");
		
		msi.modifyPaypalInfo(pay);

		return "2:ctrl/paypalInfoShow.man";
	}

	public String paypalInfoDelete(HttpServletRequest request, HttpServletResponse response) {
		int paypalId=Integer.parseInt(request.getParameter("paypalId"));
		msi.removePaypalInfoById(paypalId);
		return "2:ctrl/paypalInfoShow.man";
	}
	
	public String rechargeAccountInfoShow(HttpServletRequest request, HttpServletResponse response) {
		List<RechargeAccount> rechAcc = msi.getRechargeAccount();
		if (rechAcc != null) {
			String rechAccListString = AjaxUtils.jsonString(rechAcc);
			request.setAttribute("rechAccListString", rechAccListString);
		}
		return "1:jsp/manage/managebankinfoshow.jsp";
	}

	public String rechargeAccountInfoSave(HttpServletRequest request, HttpServletResponse response) {
		String bank_name = request.getParameter("bank_name");
		String bank_num = request.getParameter("bank_num");
		String open_name = request.getParameter("open_name");
		String bank_address = request.getParameter("bank_address");
		RechargeAccount rechAcc = new RechargeAccount();
		rechAcc.setBank_name(bank_name);
		rechAcc.setBank_num(bank_num);
		rechAcc.setOpen_name(open_name);
		rechAcc.setBank_address(bank_address);
		msi.addRechAcc(rechAcc);

		return "2:ctrl/rechargeAccountInfoShow.man";
	}

	public String rechargeAccountInfoDelete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("rechId"));
		msi.removeRechAccBy(id);
		return "2:ctrl/rechargeAccountInfoShow.man";
	}

	public String rechargeAccountInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("rechAccId"));
		String bank_name = request.getParameter("bank_name");
		String bank_num = request.getParameter("bank_num");
		String open_name = request.getParameter("open_name");
		String bank_address = request.getParameter("bank_address");
		RechargeAccount rechAcc = new RechargeAccount();
		rechAcc.setId(id);
		rechAcc.setBank_name(bank_name);
		rechAcc.setBank_address(bank_address);
		rechAcc.setBank_num(bank_num);
		rechAcc.setOpen_name(open_name);
		msi.modifyRechAcc(rechAcc);
		return "2:ctrl/rechargeAccountInfoShow.man";
	}

	public String rechargeAccountInfoEdit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("rechId"));
		RechargeAccount rechAcc = msi.getRechAcc(id);
		if (rechAcc != null) {
			request.setAttribute("bank_name", rechAcc.getBank_name());
			request.setAttribute("bank_num", rechAcc.getBank_num());
			request.setAttribute("bank_address", rechAcc.getBank_address());
			request.setAttribute("open_name", rechAcc.getOpen_name());
			request.setAttribute("rechId", id);
		}
		return "1:jsp/manage/managebankinfoedit.jsp";
	}
	
	public String adTypePriceShow(HttpServletRequest request, HttpServletResponse response) {
		String type=request.getParameter("type");
		if(type==null){
			type="CPC";
		}
		AdTypePrice adtype=msi.getAdTypePriceByIsNew(type);
		if(adtype!=null){
			String ad_type=adtype.getAd_type();
			Double cur_money=adtype.getCur_money();
			Date change_time =adtype.getChange_time();
			int id=adtype.getId();
			request.setAttribute("ad_type", ad_type);
			request.setAttribute("cur_money", cur_money);
			request.setAttribute("change_time", change_time);
			request.setAttribute("id", id);
		}
		return "1:jsp/manage/adtypeprice.jsp";
	}
	
	public String adTypePriceUpdate(HttpServletRequest request, HttpServletResponse response) {
		String reqId=request.getParameter("adtypeid");
		String ad_type=request.getParameter("ad_type");
		Double cur_money=Double.parseDouble(request.getParameter("cur_money"));
		AdTypePrice adtype=new AdTypePrice();
		int id = 0;
		if(reqId!=null){
			id=Integer.parseInt(reqId);
			adtype.setId(id);
		}//id不存在就插入语句
			
			
			adtype.setAd_type(ad_type);
			adtype.setCur_money(cur_money);
			adtype.setChange_time(new java.util.Date());
			adtype.setIs_newest(1+"");
			msi.modifyAdTypePrice(adtype,ad_type);
		
		return "2:ctrl/adTypePriceShow.man";
	}
	/**
	 * 广告审批显示列表
	 * @param request
	 * @param response
	 * @return
	 */
	
	public String adCheckShow(HttpServletRequest request, HttpServletResponse response) {
		
		List<AD> adList = asi.getAdByState("待审批");
		String adListString = AjaxUtils.jsonString(adList);
		request.setAttribute("adListString", adListString);
		return "1:jsp/adcheck/adcheckshow.jsp";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 广告编辑页面数据
	 */

	/**
	 * 获取广告投放方式信息
	 */
	public String getAdPutWays(HttpServletRequest request, HttpServletResponse response) {
		int adId = Integer.parseInt(request.getParameter("adId"));
		
		List<ADShowType> showTypes = asi.getADShowTypesByADID(adId);
		
		
		request.setAttribute("adId", adId);
		request.setAttribute("showTypes", showTypes);
		return "1:jsp/adcheck/selectshowtype.jsp";
	}
	
	public String adCheckEdit(HttpServletRequest request, HttpServletResponse response) {
		int adId = Integer.parseInt(request.getParameter("adId"));
		AD ad = asi.getAdById(adId);
		Member mem = (Member) request.getSession().getAttribute("member");
		if (!ad.getMem_id().equals(mem.getId())) {
			try {
				request.setAttribute("error", "请选择您的广告！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("editedAD", ad);
//		int id=Integer.parseInt(request.getParameter("adId"));
//		
//		AD ad=asi.getAdById(id);
//		request.setAttribute("name", ad.getName());//广告名称
//		request.setAttribute("type",ad.getType());
//		request.setAttribute("show_type",ad.getShow_type());
//		request.setAttribute("show_src",ad.getShow_src());
//		request.setAttribute("img_word_type",ad.getImg_word_type());
//		request.setAttribute("title",ad.getTitle());
//		request.setAttribute("img_path",ad.getImg_path());
//		request.setAttribute("pay_mode",ad.getPay_mode());
//		request.setAttribute("state", ad.getState());
//		request.setAttribute("adId", id);
		return "1:jsp/adcheck/adcheckedit.jsp";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 广告验证更新
	 */

	public String adCheckUpdate(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("adId"));
		String state=request.getParameter("adpass");
		AD ad=new AD();
		ad.setId(id);
		ad.setState(state);
		asi.modifyAd(ad);
		
		return "2:ctrl/adCheckShow.man";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 待审批的应用list信息
	 */
	public String appCheckInfoShow(HttpServletRequest request, HttpServletResponse response){
		List<App> listApp=apps.getAppByState("待审批");
		String appListString = AjaxUtils.jsonString(listApp);
		request.setAttribute("appListString", appListString);
		return "1:jsp/appcheck/appcheckshow.jsp";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 编辑页面显示的待验证信息
	 */
	public String appCheckEdit(HttpServletRequest request, HttpServletResponse response){
		String appId=request.getParameter("appId");
		int id=0;
		if(appId!=null){
			id=Integer.parseInt(appId);
		}
		App app=apps.getAppById(id);
		String app_name=app.getApp_name();
		String app_type=app.getApp_type();
		String pid=app.getPid();
		Date create_time=app.getCreate_time();
		String apk_url=app.getApk_url();
		String app_platform=app.getApp_platform();
		
		request.setAttribute("appid", id+"");
		request.setAttribute("app_name", app_name);
		request.setAttribute("app_type", app_type);
		request.setAttribute("pid", pid);
		request.setAttribute("create_time", create_time);
		request.setAttribute("apk_url", apk_url);
		request.setAttribute("app_platform", app_platform);
		
		return "1:jsp/appcheck/appcheckedit.jsp";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 下一步后页面显示的信息
	 */
	public String appCheckEditNext(HttpServletRequest request, HttpServletResponse response){
		String appid=request.getParameter("appid");
		int id=0;
		if(appid!=null){
			id=Integer.parseInt(appid);
			
		}
		App app=apps.getAppById(id);
		String app_name=app.getApp_name();
		String packageName=app.getPakage_name();
		request.setAttribute("id", id+"");
		request.setAttribute("app_name", app_name);
		request.setAttribute("pakage_name", packageName);
		
		return "1:jsp/appcheck/appcheckeditnext.jsp";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 应用信息确认完毕更新
	 */
	public String appCheckUpdate(HttpServletRequest request, HttpServletResponse response){
		String id=request.getParameter("id");
		String[] userCrowd=request.getParameterValues("userCrowd");
		String gender =request.getParameter("gender");
		String app_state=request.getParameter("app_state");
		int time = 0;
		for (int i = 0; i < userCrowd.length; i++) {
			if ("1".equals(userCrowd[i])) {
				time ^= 8;
			} else if ("2".equals(userCrowd[i])) {
				time ^= 4;
			} else if ("3".equals(userCrowd[i])) {
				time ^= 2;
			} else if ("4".equals(userCrowd[i])) {
				time ^= 1;
			}
		}
		App app=new App();
		app.setId(Integer.parseInt(id));
		app.setApp_crowd(time+"");
		app.setApp_gender(gender);
		app.setApp_state(app_state);
		apps.modifyAppByAppID(app);
		request.setAttribute("id", id);
		request.setAttribute("time", time);
		request.setAttribute("gender", gender);
		
		return "2:ctrl/appCheckInfoShow.man";
	}
	/** *******************************下为零碎**************************************** **/
	
	/**
	 * 
	 */
	public String appUpload(HttpServletRequest request, HttpServletResponse response){
		System.out.println("request.getParame: "+request.getParameter("appurl"));
		try{
  			//获取下载文件路径
//			String path=request.getSession().getServletContext().getRealPath("files"+File.separator+"app")+File.separator+request.getParameter("appurl");
			String path="http://ad.uangel.com.cn/files/app/"+request.getParameter("appurl");
			//编码
			path=URLEncoder.encode(path, "ISO-8859-1");
			//解码
			path=URLDecoder.decode(path, "UTF-8");
			File file=new File(path);
			//文件名
			String fileName=file.getName();
			//扩展名
//			String ext=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toUpperCase();
			//输入流
			InputStream inStream=new FileInputStream(path);
			InputStream  in=new BufferedInputStream(inStream);
			byte []bs=new byte[in.available()];
			in.read(bs);
			in.close();
			// 清空response
			response.reset();
			// 设置response的Header
			//使浏览器弹出下载对话框
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			//输出流
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(bs);
			toClient.flush();
			toClient.close(); 
		} catch (Exception e) {}

		return "";
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 广告账户余额
	 */
	public String adBalance(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("rechAdId"));
		Member mem=msi.getAdBalanceByRechId(id);
		AjaxUtils.sendJSON(response, mem.getAd_balance());
		return "3:ajax";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 广告审批页面的其它信息
	 */
	
	public String adCheckOtherInfo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("adId"));
		AdCheckOtherInfo aco=asi.getAdCheckOtherInfo(id);
		System.out.println(aco.getAdBalance()+"0000000000000");
		String s1 = aco.getAdName();
		String s2 = aco.getAdType();
		Double s3 = aco.getAdBalance();
		AjaxUtils.sendJSON(response, s1+"‖"+s2+"‖"+s3);
		return "3:ajax";
	}
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @return 收入账户余额
	 */
	public String incomeBalance(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("paypalIdEdit"));
		Member mem=msi.getIncomeBalanceByPaypalId(id);
		AjaxUtils.sendJSON(response, mem.getIncome_balance());
		return "3:ajax";
	}
	
	public String rechargeInfoDelete(HttpServletRequest request, HttpServletResponse response) {
		int rechId=Integer.parseInt(request.getParameter("rechId"));
		msi.removeRechargeInfoById(rechId);
		return "2:ctrl/rechargeInfoShow.man";
	}
	
	/**
	 * 登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String loginEmail = request.getParameter("loginEmail");
		String loginPassword = request.getParameter("loginPassword");
		String loginauthCode=request.getParameter("authCode").toUpperCase();
		Member member = msi.getByEmailAndPassword(loginEmail, loginPassword);
		HttpSession session = request.getSession(true);

		String authCode=(String)request.getSession().getAttribute("validateCode");
		
		
		if (member == null) {
			request.setAttribute("loginFailed", "您输入的用户名或密码错误！");
			return "1:jsp/manage/login.jsp";
		} else if(!member.getState().equals("manager")){
			request.setAttribute("loginFailed", "您不是管理员");
			return "1:jsp/manage/login.jsp";
		}else if(!loginauthCode.equals(authCode)){
			request.setAttribute("loginAuthCode", "验证码错误！");
			return "1:jsp/manage/login.jsp";
		}else if(member.getState().equals("manager")){
			session.setAttribute("member", member);
		}
		return "2:index.jsp";
	}
	
	/**
	 * 退出
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "2:index.jsp";
	}
	
	/**
	 * 
	 * @param request 
	 * @param response
	 * @return  验证码图片
	 */

	public String authCode(HttpServletRequest request, HttpServletResponse response) {
		
		init();

		try {
			serviceCode(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		  

		return "3:ajax";
	}
	
	
	 // 验证码图片的宽度。
	private int width = 60;
	// 验证码图片的高度。
	private int height = 20;
	// 验证码字符个数
	private int codeCount = 4;

	private int x = 0;
	// 字体高度
	private int fontHeight;
	private int codeY;
    
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
   'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
   'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    
    /**
	 * 初始化验证图片属性
	 */
   
	 public void init() {
		// // 从web.xml中获取初始信息
		// // 宽度
		// public String strWidth=200+"";
		// // 高度
		// String strHeight=this.getInitParameter("height");
		// // 字符个数
		// String strCodeCount=this.getInitParameter("codeCount");
		//	  
		// // 将配置的信息转换成数值
		try {

			if (75 + "" != null && 75 + "".length() != 0) {
				width = Integer.parseInt(75 + "");
			}
			if (30 + "" != null && 30 + "".length() != 0) {
				height = Integer.parseInt(20 + "");
			}
			if (4 + "" != null && 4 + "".length() != 0) {
				codeCount = Integer.parseInt(4 + "");
			}
		} catch (NumberFormatException e) {

		}

		x = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;

	}
	protected void serviceCode(HttpServletRequest req, HttpServletResponse resp) throws java.io.IOException {

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 创建一个随机数生成器类
		Random random = new Random();

		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// 设置字体。
		g.setFont(font);

		// 画边框。
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(Color.BLACK);
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
		session.setAttribute("validateCode", randomCode.toString());

		
		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。

		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
}
