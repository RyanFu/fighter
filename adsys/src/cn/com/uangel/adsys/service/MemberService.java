package cn.com.uangel.adsys.service;

import cn.com.uangel.adsys.entity.Member;

public interface MemberService {
	public void add(Member mem);

	public Member getById(int id,String code);
	
	public Member getById(int id);
	
	public Member getByEmail(String email);
	
	public void modify(Member mem);
	
	public boolean initPassword(String id,String code,String reinitNewPw);
	
	public boolean modify(Member mem,String password);
	
	public boolean memberIsExist(String email);
	
	public Member getByEmailAndPassword(String email, String password);
	
	public boolean financialIsExist(int id);
}
