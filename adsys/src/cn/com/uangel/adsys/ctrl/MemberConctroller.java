package cn.com.uangel.adsys.ctrl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.service.impl.MemberServiceImpl;
import cn.com.uangel.adsys.util.AjaxUtils;

public class MemberConctroller {

	MemberServiceImpl msi = new MemberServiceImpl();

	/**
	 * 当用户注册时，若注册邮箱已存在，报错；若不存在，则保存用户所填信息，并设置用户的状态为“2”（已注册，未激活），然后向用户注册邮箱中发送邮件
	 */
	public String memberInfoSave(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String cellphone = request.getParameter("cellphone");
		String com_name = request.getParameter("com_name");
		String[] appPlatform = request.getParameterValues("memAccountType");
		char[] temp = "00".toCharArray();
		for (int i = 0; appPlatform != null && i < appPlatform.length; i++) {
			if ("1".equals(appPlatform[i])) {
				temp[0] = '1';
			} else if ("2".equals(appPlatform[i])) {
				temp[1] = '1';
			}
		}
		String apf = String.valueOf(temp);
		Member mem = new Member();
		mem.setAccount_type(apf);
		mem.setEmail(email);
		mem.setPassword(password);
		mem.setRegist_time(new java.util.Date());
		mem.setName(name);
		mem.setCellphone(cellphone);
		mem.setCom_name(com_name);

		msi.add(mem);

		request.setAttribute("email", email);
		return "1:jsp/member/registerInfo.jsp";
	}

	/**
	 * ajax方法，接触注册邮箱是否存在
	 */
	public String testMemberExist(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		
		boolean resultRegister = msi.memberIsExist(email);
		if (resultRegister) {
			AjaxUtils.sendJSON(response, "true");
		} else {
			AjaxUtils.sendJSON(response, "false");
		}
		return "3:ajax";

	}

	/**
	 * 在邮箱中点击激活连接到这个方法，设置用户的状态为“1”（已激活），若已经激活，重定向到登录页面
	 */
	public String activation(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String code = request.getParameter("ser_num");
		Member mem = msi.getById(id, code);

		if(mem==null){
			request.setAttribute("activateState", "请复制到地址栏重新激活");
		}
		if (mem.getState().equals("1")) {
			request.setAttribute("activateState", "激活成功");
		} else {
			request.setAttribute("activateState", "请复制到地址栏重新激活");
		}
		return "1:jsp/member/registerSuccess.jsp";

	}

	/**
	 * 获取当前登录会员的基本信息和详细信息
	 */
	public String getMemberInfo(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		int id=mem.getId();
		msi.getById(id);
		return "";
	}

	/**
	 * 更新会员的基本信息和详细信息
	 */
	public String updateMemberInfo(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem!=null){
			int id = mem.getId();
			String name = request.getParameter("name");
			String telephone = request.getParameter("telephone");
			String cellphone = request.getParameter("cellphone");
			String qq = request.getParameter("qq");
			String com_name = request.getParameter("com_name");
			String com_homepage = request.getParameter("com_homepage");
			String com_address = request.getParameter("com_address");
			String zipcode = request.getParameter("zipcode");
			String[] appPlatform = request.getParameterValues("memAccountType");
			char[] temp = "00".toCharArray();
			for (int i = 0; appPlatform != null && i < appPlatform.length; i++) {
				if ("1".equals(appPlatform[i])) {
					temp[0] = '1';
				} else if ("2".equals(appPlatform[i])) {
					temp[1] = '1';
				}
			}
			String apf = String.valueOf(temp);
			mem.setId(id);
			mem.setName(name);
			mem.setTelephone(telephone);
			mem.setCellphone(cellphone);
			mem.setAccount_type(apf);
			mem.setQq(qq);
			mem.setCom_name(com_name);
			mem.setCom_homepage(com_homepage);
			mem.setCom_address(com_address);
			mem.setZipcode(zipcode);
			msi.modify(mem);
			return "2:ctrl/info.mem";
		}
		
		return "1:jsp/member/login.jsp";
	}

	/**
	 * 更新会员的财务信息
	 */
	public String updateMemberFinancialInfo(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem!=null){
			String[] memPlatform = request.getParameterValues("account_attr");
			char[] temp = "00".toCharArray();
			for (int i = 0; memPlatform != null && i < memPlatform.length; i++) {
				if ("1".equals(memPlatform[i])) {
					temp[0] = '1';
				} else if ("2".equals(memPlatform[i])) {
					temp[1] = '1';
				}
			}
			String account_attr = String.valueOf(temp);
			String invoice_able = request.getParameter("invoice_able");

			String[] memPlatform2 = request.getParameterValues("account_attr");
			char[] temp2 = "00".toCharArray();
			for (int i = 0; memPlatform2 != null && i < memPlatform2.length; i++) {
				if ("1".equals(memPlatform2[i])) {
					temp2[0] = '1';
				} else if ("2".equals(memPlatform2[i])) {
					temp2[1] = '1';
				}
			}
			String get_money_mode = String.valueOf(temp);

			String open_bank = request.getParameter("open_bank");
			String account_number = request.getParameter("account_number");
			String open_name = request.getParameter("open_name");
			String address_bank = request.getParameter("address_bank");
			String zipcode_bank = request.getParameter("zipcode_bank");

			mem.setAccount_attr(account_attr);
			mem.setInvoice_able(invoice_able);
			mem.setGet_money_mode(get_money_mode);
			mem.setOpen_bank(open_bank);
			mem.setAccount_number(account_number);
			mem.setOpen_name(open_name);
			mem.setAddress_bank(address_bank);
			mem.setZipcode_bank(zipcode_bank);
			msi.modify(mem);
			return "2:ctrl/info.mem";
		}
		
		return "1:jsp/member/login.jsp";
	}

	/**
	 * 修改密码
	 */
	public String changePassword(HttpServletRequest request, HttpServletResponse response) {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member != null) {
			String newPassword = request.getParameter("newPassword");
			member.setPassword(newPassword);
			msi.modify(member);
			
			Member memberInfo=msi.getById(member.getId());
			request.setAttribute("nameMem", memberInfo.getName());
			request.setAttribute("ad_balance", memberInfo.getAd_balance());
			request.setAttribute("income_balance", memberInfo.getIncome_balance());
			return "1:jsp/member/info.jsp";
		}
		return "1:jsp/member/changePassword.jsp";
	}

	/**
	 * 忘记密码，通过邮箱找回密码。
	 */
	public String forgetPasswordAndSendEmail(HttpServletRequest request, HttpServletResponse response) {
		String reinitEmail=request.getParameter("reinitEmail");
		Member mem=msi.getByEmail(reinitEmail);
		if(mem!=null){
			request.setAttribute("loginFailed","重置密码的连接已发送到您的邮箱，请打开连接。");
			return "1:jsp/member/login.jsp";
		}
		request.setAttribute("inexistence","您不是我们的会员，不能为您发送邮件");
		return "1:jsp/member/reInitPassword.jsp";
	}

	/**
	 * 重置密码
	 */
	public String reInitPassword(HttpServletRequest request, HttpServletResponse response) {
        String id= request.getParameter("idInit1");
        String ser_num=request.getParameter("setNumInit1");
        String reinitNewPw=request.getParameter("reinitNewPw");
        boolean result =msi.initPassword(id, ser_num, reinitNewPw);
        if(result){
        	return "1:jsp/member/reInitLogin.jsp";
        }
		return "";
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
			return "1:jsp/member/login.jsp";
		} else if(!member.getState().equals("1")){
			request.setAttribute("loginFailed", "请激活您邮箱的注册链接！");
			return "1:jsp/member/login.jsp";
		}else if(!loginauthCode.equals(authCode)){
			request.setAttribute("loginAuthCode", "验证码错误！");
			return "1:jsp/member/login.jsp";
		}else if(member.getState().equals("1")){
			session.setAttribute("member", member);
		}
		return "2:ctrl/info.mem";
	}
	
	/**
	 * 首页登录
	 */
	public String loginWithoutCode(HttpServletRequest request, HttpServletResponse response) {
		String loginEmail = request.getParameter("loginEmail");
		String loginPassword = request.getParameter("loginPassword");
		Member member = msi.getByEmailAndPassword(loginEmail, loginPassword);
		HttpSession session = request.getSession(true);

		if (member == null) {
			AjaxUtils.sendJSON(response, "您输入的用户名或密码错误！");
			return "2:jsp/member/login.jsp";
		} else if(!member.getState().equals("1")){
			AjaxUtils.sendJSON(response, "请激活您邮箱的注册链接！");
			return "2:jsp/member/login.jsp";
		}else if(member.getState().equals("1")){
			session.setAttribute("member", member);
			AjaxUtils.sendJSON(response, "ok");
		}
		return "3:ajax";
	}
	
	/**
	 * 个人中心
	 */
	public String info(HttpServletRequest request, HttpServletResponse response) {
		Member mem = (Member) request.getSession().getAttribute("member");

		if (mem != null) {
			Member member=msi.getById(mem.getId());
			request.setAttribute("accountType", member.getAccount_type());
			request.setAttribute("nameMem", member.getName());
			request.setAttribute("ad_balance", member.getAd_balance());
			request.setAttribute("income_balance", member.getIncome_balance());
			return "1:jsp/member/info.jsp";
		}
		return "1:jsp/member/login.jsp";
	}

	/**
	 * 退出
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "2:index.jsp";
	}

	protected Timestamp getSQLTimestamp(java.util.Date d) {
		if (d == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(d);
		return Timestamp.valueOf(dateStr);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String isTestBankInfo(HttpServletRequest request, HttpServletResponse response) {
		int id =Integer.parseInt(request.getParameter("id")) ;
		boolean resultFinal = msi.financialIsExist(id);
		if (resultFinal) {
			AjaxUtils.sendJSON(response, "true");
		} else {
			AjaxUtils.sendJSON(response, "false");
		}
		return "3:ajax";
	}

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
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 广告账户余额
	 */
	public String adBalance(HttpServletRequest request, HttpServletResponse response) {
		Member mem = (Member) request.getSession().getAttribute("member");
		if (mem != null) {
			Member member=msi.getById(mem.getId());
			
			AjaxUtils.sendJSON(response, member.getAd_balance());
		}
		return "3:ajax";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return 收入账户余额
	 */
	public String incomeBalance(HttpServletRequest request, HttpServletResponse response) {
		Member mem = (Member) request.getSession().getAttribute("member");
		if (mem != null) {
			Member member=msi.getById(mem.getId());
			AjaxUtils.sendJSON(response, member.getIncome_balance());
		}
		return "3:ajax";
	}
}
