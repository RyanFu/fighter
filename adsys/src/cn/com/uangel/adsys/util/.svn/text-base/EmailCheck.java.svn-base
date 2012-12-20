package cn.com.uangel.adsys.util;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailCheck {
	StringBuffer code;
	public EmailCheck() {
		
	}

	public void emailCheck(String to_email,int id,String code,String mem) {
		try {
			String to_mail = to_email;
			String to_title = "title";
			String to_content = "http://ad.uangel.com.cn/"+mem+"?id="+id+"&ser_num="+code;
			// --------建立邮件会话--------
			Properties props = new Properties();// 也可用Properties props =
			// System.getProperties();
			props.put("mail.smtp.host", "smtp.126.com"); // 存储发送邮件服务器的信息
			props.put("mail.smtp.auth", "true"); // 同时通过验证
			Session s = Session.getInstance(props); // 根据属性新建一个邮件会话
			s.setDebug(true);
			// ----由邮件会话新建一个消息对象----
			MimeMessage message = new MimeMessage(s); // 由邮件会话新建一个消息对象

			// --------设置邮件--------
			InternetAddress from = new InternetAddress("fighter576133402@126.com");
			message.setFrom(from); // 设置发件人
			InternetAddress to = new InternetAddress(to_mail);
			message.setRecipient(Message.RecipientType.TO, to);
			// 设置收件人,并设置其接收类型为TO
			message.setSubject(to_title, "UTF-8"); // 设置主题
			message.setText(to_content, "UTF-8"); // 设置信件内容
			message.setSentDate(new Date()); // 设置发信时间
			// --------发送邮件--------
			message.saveChanges(); // 存储邮件信息
			Transport transport = s.getTransport("smtp");
			// --以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址;第二个参数为用户名;第3个参数为密码
			transport.connect("smtp.126.com", "fighter576133402@126.com",
					"576133402wj");
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**获得邮箱验证码*/
	public String checkCode(int memberId){
		code=new StringBuffer();
		java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("MMddyyyy");
		code.append(date.format(new Date(System.currentTimeMillis())));
		String ch[]={"0","1","2","3","4","5","6","7","8","9"};
		Random r=new Random();
		for(int i=0;i<6;i++) {
		code.append(ch[r.nextInt(9)]);
		}
		String zeroAppend=""+memberId;
		for(int j=1;j<=(12-zeroAppend.length());j++){
			code.append("0");
		}
		code.append(memberId);
		
		for(int k=0;k<3;k++) {
			code.append(ch[r.nextInt(9)]);
		}
		String returncode=code.toString();
		String encrypt=StringUtil.getRegisterCode(returncode);
		return encrypt;
	}
	
}
