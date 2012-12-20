package cn.com.uangel.adsys.dao;

import java.util.List;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;

public interface AdCheckDao {
	
	public List<AD> selectAdToShow(String state);
	
	public AD selectAdById(int id);
	
	public void updateAdsByMem_id(int mem_id);
	
	public void updateAd(AD ad);
	
	/** 查询广告对应的展现方式 */
	public List<ADShowType> selectADShowTypesByADID(int adId);

}
