package cn.com.uangel.adsys.service.impl;

import java.sql.SQLException;

import cn.com.uangel.adsys.dao.MemberDao;
import cn.com.uangel.adsys.dao.impl.MemberDaoImpl;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.service.MemberService;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.EmailCheck;

public class MemberServiceImpl implements MemberService {
	Member member = null;

	@Override
	public boolean memberIsExist(String email) {
		try {
			// Service 方法开始
			ConnectionProvider.beginTransaction();
			
			MemberDaoImpl mdi = new MemberDaoImpl();
			boolean isExist = mdi.emailIsRegist(email);
			if (isExist) {
				return true;
			}
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public void add(Member mem) {
		try {
			// Service 方法开始
			ConnectionProvider.beginTransaction();
			MemberDaoImpl mdi = new MemberDaoImpl();
			mdi.insert(mem);// 插入数据
			EmailCheck emailCheck = new EmailCheck();
			String code = emailCheck.checkCode(mem.getId());// 得到注册验证码字符串
			mem.setSer_num(code);
			mdi.update(mem);// 更新注册验证码
			emailCheck.emailCheck(mem.getEmail(), mem.getId(), code, "ctrl/activation.mem");// 发送邮件
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}

	}

	@Override
	public Member getByEmailAndPassword(String email, String password) {
		Member mem=null;
		try {
			// Service 方法开始
			ConnectionProvider.beginTransaction();
			MemberDaoImpl mdi = new MemberDaoImpl();
			member = mdi.selectByEmailAndPassword(email, password);
			if (member != null && member.getEmail().equals(email) && member.getPassword().equals(password)) {
				mem = member;
			}
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}

		return mem;
	}

	@Override
	public void modify(Member mem) {
		try {
			ConnectionProvider.beginTransaction();
			MemberDaoImpl mdi = new MemberDaoImpl();
			mdi.update(mem);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}

	/*
	 * 修改密码的modify 返回值为true： 返回值为false：填写的
	 */
	@Override
	public boolean modify(Member mem, String password) {
		try {
			ConnectionProvider.beginTransaction();
			MemberDaoImpl mdi = new MemberDaoImpl();
			int id = mem.getId();
			Member member = mdi.selectById(id);
			
			if (member.getPassword().equals(password)) {
				mdi.update(mem);
				return true;
			}

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public Member getById(int id, String code) {
		try {
			ConnectionProvider.beginTransaction();
			MemberDaoImpl memDaoI = new MemberDaoImpl();
			member = memDaoI.selectById(id);
			
			if (member.getSer_num().equals(code)) {
				member.setState("1");
				memDaoI.update(member);
			}
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return member;
	}

	@Override
	public Member getById(int id) {
		try {
			ConnectionProvider.beginTransaction();
			MemberDaoImpl memDaoI = new MemberDaoImpl();
			member = memDaoI.selectById(id);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return member;
	}

	@Override
	public Member getByEmail(String email) {
		try {
			ConnectionProvider.beginTransaction();
			MemberDaoImpl memDaoI = new MemberDaoImpl();
			member = memDaoI.selectByEmail(email);
			if (member == null) {
				
				return null;
			} else {
				
				EmailCheck emailCheck = new EmailCheck();
				emailCheck.emailCheck(member.getEmail(), member.getId(), member.getSer_num(),
						"jsp/member/reInitNewPass.jsp");// 发送邮件
			}
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return member;
	}

	@Override
	public boolean initPassword(String id, String code, String reinitNewPw) {
		try {
			
			ConnectionProvider.beginTransaction();
			MemberDaoImpl memDaoI = new MemberDaoImpl();
			int idInt = Integer.parseInt(id);
			Member mem = memDaoI.selectById(idInt);
			if (code.equals(mem.getSer_num())) {

				mem.setPassword(reinitNewPw);
				
				memDaoI.update(mem);
			}
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
			return true;
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			return false;
		}
	}

	@Override
	public boolean financialIsExist(int id) {
		boolean result = false;
		try {
			ConnectionProvider.beginTransaction();

			MemberDao mem=new MemberDaoImpl();
			Member member=mem.selectById(id);
			if(member.getAccount_number()!=null){
				result=true;
			}else {
				result=false;
			}
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
		}
		return result;
	}
}
