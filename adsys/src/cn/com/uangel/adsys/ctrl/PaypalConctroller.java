package cn.com.uangel.adsys.ctrl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.service.impl.MemberServiceImpl;
import cn.com.uangel.adsys.service.impl.PaypalServiceImpl;
import cn.com.uangel.adsys.util.AjaxUtils;

public class PaypalConctroller {
	PaypalServiceImpl psi=new PaypalServiceImpl();
	MemberServiceImpl msi=new MemberServiceImpl();
	public String paypalInfoSave(HttpServletRequest request, HttpServletResponse response) {
		
		return "1:jsp/member/registerInfo.jsp";
	}
	/** state :1 为待审 ；2 为审核*/
	public String rechargeInfoSave(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem==null){
			return "1:jsp/member/login.jsp";
		}
		String[] bankPayValue=request.getParameterValues("bankPayValue");
		
		
		String rechargeMoney=request.getParameter("rechargeMoney");
		Double money=Double.parseDouble(rechargeMoney); 
		RechargeInfo rech=new RechargeInfo();
		rech.setBank_num(bankPayValue[0]);
		rech.setState("1");
		rech.setMem_id(mem.getId());
		rech.setRecharge_time(new java.util.Date());
		rech.setRecharge_count(money);
		psi.addRechargeInfo(rech);
		return "2:ctrl/rechargeInfoShow.pay";
	}
	public String commisionInfoSave(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	/** state :1 为待审 ；2 为未通过 3 已成功*/
	public String PaypalInfoSave(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		
		
//		Member member=psi.getById(mem.getId());
//		System.out.println(member.getAccount_number()+"member.getAccount_number()");
//		if("".equals(member.getAccount_number())||member.getAccount_number()==null){
//			request.setAttribute("bankIsNoHere", "0");//0代表会员银行账号没有填写
//			return "1:ctrl/paypalShow.pay";
//		}
		String cashout=request.getParameter("cashout");
		Double cashoutManey=Double.parseDouble(cashout);
		PaypalInfo paypal=new PaypalInfo();
		paypal.setMem_id(mem.getId());
		paypal.setApply_amount(cashoutManey);
		paypal.setApply_time(new java.util.Date());
		paypal.setState("1");
		psi.addPaypalInfo(paypal);
		return "2:ctrl/paypalInfoShow.pay";
	}
	/** state :1 为待审 ；2 为未通过 3 已成功*/
	public String paypalInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		int cashoutId=Integer.parseInt(request.getParameter("paypalIdEdit"));
		Double rechCount=Double.parseDouble(request.getParameter("cashoutUpdate"));
		PaypalInfo paypal=new PaypalInfo();
		paypal.setId(cashoutId);
		paypal.setApply_amount(rechCount);
		psi.modifyPaypalInfo(paypal);
		return "2:ctrl/paypalInfoShow.pay";
	}
	public String rechargeInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		int rechId=Integer.parseInt(request.getParameter("rechIdEdit"));
		Double rechCount=Double.parseDouble(request.getParameter("rechUpdate"));
		RechargeInfo rech=new RechargeInfo();
		rech.setId(rechId);
		rech.setRecharge_count(rechCount);
		psi.modifyRechargeInfo(rech);
		
		
		return "2:ctrl/rechargeInfoShow.pay";
	}
	public String commisionInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	
	public String paypalInfoDelete(HttpServletRequest request, HttpServletResponse response) {
		int paypalId=Integer.parseInt(request.getParameter("paypalId"));
		psi.removePaypalInfoById(paypalId);
		return "2:ctrl/paypalInfoShow.pay";
	}
	public String rechargeInfoDelete(HttpServletRequest request, HttpServletResponse response) {
		int rechId=Integer.parseInt(request.getParameter("rechId"));
		psi.removeRechargeInfoById(rechId);
		return "2:ctrl/rechargeInfoShow.pay";
	}
	public String commisionInfoDelete(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	public String rechargeInfoEdit(HttpServletRequest request, HttpServletResponse response) {
		int rechId=Integer.parseInt(request.getParameter("rechId"));
		RechargeInfo rech=psi.getRechargeById(rechId);
		if(rech!=null){
			String ord_number = rech.getOrd_number();
			Double rechCount=rech.getRecharge_count();
			SimpleDateFormat df=new SimpleDateFormat();
			String rechTime=df.format(rech.getRecharge_time());
			String bankNumber=rech.getBank_num();
			request.setAttribute("ord_number", ord_number);
			request.setAttribute("rechTime", rechTime);
			request.setAttribute("rechCount", rechCount);
			request.setAttribute("bankNumber", bankNumber);
			request.setAttribute("rechId", rech.getId());
		}
		return "1:jsp/paypal/rechargeedit.jsp";
	}
	
	public String paypalInfoEdit(HttpServletRequest request, HttpServletResponse response) {
		int paypalId=Integer.parseInt(request.getParameter("paypalId"));
		
		Member mem =(Member) request.getSession().getAttribute("member");
		PaypalInfo paypal=psi.getPaypalById(paypalId);
		
		if(paypal!=null){
			String bod_number = paypal.getBod_number();
			Double apply_amount=paypal.getApply_amount();
			SimpleDateFormat df=new SimpleDateFormat();
			String applyTime=df.format(paypal.getApply_time());
			String account_number=mem.getAccount_number();
			request.setAttribute("bod_number", bod_number);
			request.setAttribute("applyTime", applyTime);
			request.setAttribute("apply_amount", apply_amount);
			request.setAttribute("account_number", account_number);
			request.setAttribute("paypalId", paypal.getId());
		}
		
		
		return "1:jsp/paypal/cashoutedit.jsp";
	}
	

	public String rechargeShow(HttpServletRequest request, HttpServletResponse response) {
		
		return "";
	}
	public String rechargeInfoShow(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem!=null){
			int memId=mem.getId();
			List<RechargeInfo> rechList=psi.getRechargesByMemId(memId);
			String rechListString =AjaxUtils.jsonString(rechList);
			request.setAttribute("rechListString", rechListString);
			request.setAttribute("rechList", rechList);
			return "1:jsp/paypal/rechargeinfo.jsp";
		}
		return "1:jsp/member/login.jsp";
	}
	

	public String paypalShow(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem!=null){
			Member member=msi.getById(mem.getId());
			String email=member.getEmail();
			String account_number=member.getAccount_number();
			Double income_balance=member.getIncome_balance();
			request.setAttribute("email", email);
			request.setAttribute("account_number", account_number);//银行账号
			request.setAttribute("income_balance", income_balance);//账户余额
			
			return "1:jsp/paypal/cashout.jsp";
		}
		return "1:jsp/member/login.jsp";
	}
	
	public String paypalInfoShow(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem!=null){
			int memId=mem.getId();
			List<PaypalInfo> paypalList=psi.getPaypalByMemId(memId);
			String paypalListString =AjaxUtils.jsonString(paypalList);
			request.setAttribute("paypalListString", paypalListString);
			request.setAttribute("paypalList", paypalList);
			return "1:jsp/paypal/cashoutinfo.jsp";
		}
		return "1:jsp/member/login.jsp";
	}
	
	public String commisionInfoShow(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		if(mem!=null){
			int memId=mem.getId();
			List<CommisionInfo> commList=psi.getCommisionByMemId(memId);
			String commListString=AjaxUtils.jsonString(commList);
			request.setAttribute("commListString", commListString);
			request.setAttribute("commList", commList);
			return "1:jsp/paypal/commisioninfo.jsp";
		}
		return "1:jsp/member/login.jsp";
	}
	
	
	public String commisionInfoSearch(HttpServletRequest request, HttpServletResponse response) {
		Member mem =(Member) request.getSession().getAttribute("member");
		String startTime=request.getParameter("startDate");
		String endTime=request.getParameter("endDate");
		String commision_num=request.getParameter("commision_num");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(mem!=null){
			int memId=mem.getId();
			CommisionInfo comm=new CommisionInfo();
			try {
					comm.setStart_time(startTime.equals("") ? null:sdf.parse(startTime));
					comm.setEnd_time(endTime.equals("") ? null:sdf.parse(endTime));
				    comm.setCommision_num(commision_num.equals("")?null:commision_num);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<CommisionInfo> commList=psi.getCommisionByMemIdAndDate(memId, comm);
			String commListString=AjaxUtils.jsonString(commList);
			
			request.setAttribute("commListString", commListString);
			request.setAttribute("commList", commList);
			return "1:jsp/paypal/commisioninfo.jsp";
		}
		return "1:jsp/member/login.jsp";
	}
	
	/**
	 * 
	 * @param request
	 *                充值页面的银行信息
	 * @param response
	 * @return
	 */
	public String rechInfoShow(HttpServletRequest request, HttpServletResponse response) {
			
			List<RechargeAccount> sccountList=psi.getAllRechargeAccount();
//			String accountListString=AjaxUtils.jsonString(sccountList);
//			request.setAttribute("accountListString", accountListString);
			
			request.setAttribute("sccountList", sccountList);
		return "1:jsp/paypal/recharge.jsp";
	}

	public String isBankHere(HttpServletRequest request, HttpServletResponse response){
		Member mem =(Member) request.getSession().getAttribute("member");
		if (mem != null) {
			Member memis=msi.getById(mem.getId());
			AjaxUtils.sendJSON(response, memis.getAccount_number());
		}
		return "3:ajax";
	}

}
