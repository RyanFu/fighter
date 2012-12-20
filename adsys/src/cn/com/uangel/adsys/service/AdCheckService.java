package cn.com.uangel.adsys.service;

import java.util.List;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.AdCheckOtherInfo;

public interface AdCheckService {
	
	public List<AD> getAdByState(String state);
	
	public AD getAdById(int id);
	
	public void modifyAd(AD ad);
	
	public List<ADShowType> getADShowTypesByADID(int adID);
	
	public AdCheckOtherInfo getAdCheckOtherInfo(int id);

}
