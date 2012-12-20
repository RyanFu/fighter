package cn.com.uangel.adsys.dao;

import java.util.Vector;

import cn.com.uangel.adsys.entity.Member;

public interface MemberDao {
	/** 添加会员 */
	public void insert(Member mem);

	/** 更新会员 */
	public void update(Member mem);

	/** 通过id查 */
	public Member selectById(int id);

	/** 通过email查 */
	public Member selectByEmail(String email);

	/** 登录时用 */
	public Member selectByEmailAndPassword(String email, String password);

	/** 检测邮箱地址是否已注册 */
	public boolean emailIsRegist(String email);

	public void deleteById(int id);

	public void deleteByIds(int[] ids);

	public Vector<Member> selectAllMembers();

	/** 更新前一天用户的广告支出和程序应用收入 */
	public void updateMoney(Vector<Member> mems);

}
