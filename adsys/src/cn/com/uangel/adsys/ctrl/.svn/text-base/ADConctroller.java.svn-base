package cn.com.uangel.adsys.ctrl;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.AdCheckOtherInfo;
import cn.com.uangel.adsys.entity.AdStatInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.StatisticsByCrowd;
import cn.com.uangel.adsys.entity.StatisticsByGeography;
import cn.com.uangel.adsys.entity.StatisticsByTimeInterval;
import cn.com.uangel.adsys.service.ADService;
import cn.com.uangel.adsys.service.impl.ADServiceImpl;
import cn.com.uangel.adsys.util.AjaxUtils;

public class ADConctroller {
	ADService as = new ADServiceImpl();

	/**
	 * 保存广告的基本信息
	 */
	public String saveAdBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		AD ad = new AD();
		ad.setName(request.getParameter("name"));
		ad.setType(request.getParameter("type"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(request.getParameter("startDate"));
			Date endDate = sdf.parse(request.getParameter("endDate"));
			ad.setStart_date(startDate);
			ad.setEnd_date(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String maxPay = request.getParameter("maxPayPerDay");
		if (!"".equals(maxPay)) {
			ad.setMax_pay_perday(Double.parseDouble(maxPay));
		}

		// 存储广告所选平台
		String[] appPlatform = request.getParameterValues("appPlatform");
		char[] temp = "00".toCharArray();
		for (int i = 0; appPlatform != null && i < appPlatform.length; i++) {
			if ("1".equals(appPlatform[i])) {
				temp[0] = '1';
			} else if ("2".equals(appPlatform[i])) {
				temp[1] = '1';
			}
		}
		String apf = String.valueOf(temp);
		ad.setApp_platform(apf);

		ad.setState("未完成");
		ad.setMem_id(((Member) request.getSession().getAttribute("member")).getId());

		as.addAdWithBacicInfo(ad);

		// request.setAttribute("adId", ad.getId());
		request.getSession().setAttribute("currentAd", ad);
		return "2:jsp/ad/selectputways.jsp";
	}

	/**
	 * 保存广告投放方向
	 */
	public String saveAdPutWays(HttpServletRequest request, HttpServletResponse response) {
		int adId = ((AD) request.getSession().getAttribute("currentAd")).getId();
		String[] ars = request.getParameterValues("ars");
		String[] phoneTypes = request.getParameterValues("phoneType");
		String[] provinces = request.getParameterValues("ck_province");
		String zoneType = request.getParameter("zoneType");
		// 如果是局部选择，令zoneCode=-1
		if ("局部选择".equals(zoneType)) {
			String lng = request.getParameter("lng");
			String lat = request.getParameter("lat");
			String radius = request.getParameter("radius");
			provinces = new String[] { "-1", lng, lat, radius };
		}
		String[] userCrowd = request.getParameterValues("userCrowd");
		String gender = request.getParameter("gender");
		String[] showtime = request.getParameterValues("showtime");
		as.modifyAdPutWays(adId, ars, phoneTypes, provinces, userCrowd, gender, showtime);
		List<ADShowType> showTypes = as.getADShowTypesByADID(adId);
		request.getSession().setAttribute("showTypes", showTypes);
		return "2:jsp/ad/selectshowtype.jsp";
	}

	/**
	 * 保存广告投放方向
	 */
	public String saveAdShowType(HttpServletRequest request, HttpServletResponse response) {
		AD currentAd = (AD) request.getSession().getAttribute("currentAd");

		List<ADShowType> adShowTypes = null;
		if (currentAd != null) {
			String[] showTypes = request.getParameterValues("showType");
			adShowTypes = new ArrayList<ADShowType>();
			for (int i = 0; i < showTypes.length; i++) {
				if ("条幅型".equals(showTypes[i])) {
					String showType = request.getParameter("clickshowtype");
					String showSrc = request.getParameter("showSrc");
					if ("发送短信".equals(showType) || "发送邮件".equals(showType) || "播放音乐".equals(showType)) {
						String msgContent = request.getParameter("msgContent");
						showSrc += "¿" + msgContent;
					}
					if ("显示GoogleMap地址".equals(showType)) {
						String lat = request.getParameter("lat");
						String lng = request.getParameter("lng");
						showSrc += ":" + lat + ":" + lng;
					}
					String imgWordType = request.getParameter("imgWordType");
					String title = null;
					String imgPath = null;
					if ("仅广告文字".equals(imgWordType)) {
						title = request.getParameter("title1");
					} else if ("仅显示图片".equals(imgWordType)) {
						imgPath = request.getParameter("imgPath2");
					} else if ("显示文字＋图片".equals(imgWordType)) {
						title = request.getParameter("title3");
						imgPath = request.getParameter("imgPath3");
					}
					String payMode = request.getParameter("paymode");
					String price = request.getParameter("price");

					// TODO 以下注释字段也移动到adShowType表中，ad表不用保存了
					// currentAd.setShow_type(showType);
					// currentAd.setShow_src(showSrc);
					// currentAd.setTitle(title);
					// currentAd.setImg_path(imgPath);
					// currentAd.setPay_mode(payMode);
					// currentAd.setImg_word_type(imgWordType);

					ADShowType ast = new ADShowType();
					ast.setAd_id(currentAd.getId());
					ast.setShow_type_name(showTypes[i] + ":" + imgWordType);
					ast.setTitle(title);
					ast.setAd_info_url(imgPath);
					ast.setClick_effect(showType);
					ast.setClick_url(showSrc);
					ast.setPay_mode(payMode);
					ast.setPrice(Double.parseDouble(price));

					adShowTypes.add(ast);

					// 判断用户是否修改了图片
					List<ADShowType> oldShowTypes = as.getADShowTypesByADID(currentAd.getId());
					boolean isEdited = true;
					File oldImg = null;
					String path = request.getSession().getServletContext().getRealPath("/files/ad");
					for (ADShowType oast : oldShowTypes) {
						String showTypeName = oast.getShow_type_name().split(":")[0];
						if ("条幅型".endsWith(showTypeName) && imgPath != null) {
							if (imgPath.equals(oast.getAd_info_url())) {
								isEdited = false;
							} else {
								oldImg = new File(path, oast.getAd_info_url());
							}
						}
					}

					// 如果修改，则将广告图片存起立
					if (isEdited) {
						if (!"仅广告文字".equals(imgWordType)) {
							createDir(path, request);
							FileItem fileItem = (FileItem) request.getSession().getAttribute("currentAdImg");
							File mkr = new File(path, imgPath);
							try {
								if (mkr.createNewFile()) {
									fileItem.write(mkr);
									request.getSession().removeAttribute("currentAdImg");
									// 删除之前旧的图片
									if (oldImg != null) {
										oldImg.delete();
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								throw new RuntimeException(e);
							}
						}
					}
				} else if ("插屏型".equals(showTypes[i])) {
					String contenttype = request.getParameter("contenttype");
					String price = request.getParameter("price2");
					String imgPath = request.getParameter("uploadInsertPath");
					String payMode = request.getParameter("paymode_Insert");

					ADShowType ast = new ADShowType();
					ast.setAd_id(currentAd.getId());
					ast.setShow_type_name(showTypes[i] + ":" + contenttype);
					ast.setAd_info_url(imgPath);
					ast.setPay_mode(payMode);
					ast.setPrice(Double.parseDouble(price));

					adShowTypes.add(ast);

					// 判断用户是否修改了图片
					List<ADShowType> oldShowTypes = as.getADShowTypesByADID(currentAd.getId());
					boolean isEdited = true;
					File oldImg = null;
					String path = request.getSession().getServletContext().getRealPath("/files/fullad");
					for (ADShowType oast : oldShowTypes) {
						String showTypeName = oast.getShow_type_name().split(":")[0];
						if ("插屏型".endsWith(showTypeName) && imgPath != null) {
							if (imgPath.equals(oast.getAd_info_url())) {
								isEdited = false;
							} else {
								oldImg = new File(path, oast.getAd_info_url());
							}
						}
					}

					// 如果修改，则保存上传的全屏图片或视频文件
					if (isEdited) {
						createDir(path, request);
						FileItem fileItem = (FileItem) request.getSession().getAttribute("currentInsertFile");
						File mkr = new File(path, imgPath);
						try {
							if (mkr.createNewFile()) {
								fileItem.write(mkr);
								request.getSession().removeAttribute("currentInsertFile");
								// 删除之前旧的图片
								if (oldImg != null) {
									oldImg.delete();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}

				} else if ("悬浮型".equals(showTypes[i])) {
					String showType = request.getParameter("clickshowtype2");
					String showSrc = request.getParameter("showSrc2");
					if ("发送短信".equals(showType) || "发送邮件".equals(showType) || "播放音乐".equals(showType)) {
						String msgContent = request.getParameter("msgContent2");
						showSrc += "¿" + msgContent;
					}
					if ("显示GoogleMap地址".equals(showType)) {
						String lat = request.getParameter("lat2");
						String lng = request.getParameter("lng2");
						showSrc += ":" + lat + ":" + lng;
					}
					String imgWordType = request.getParameter("imgWordType2");
					String title = null;
					String imgPath = null;
					if ("仅广告文字".equals(imgWordType)) {

					} else if ("仅显示图片".equals(imgWordType)) {
						imgPath = request.getParameter("imgPath22");
					} else if ("显示文字＋图片".equals(imgWordType)) {
						title = request.getParameter("title32");
						imgPath = request.getParameter("imgPath32");
					}
					String payMode = request.getParameter("paymodeFloat");
					String price = request.getParameter("price3");
					String addetail = request.getParameter("addetail");

					ADShowType ast = new ADShowType();
					ast.setAd_id(currentAd.getId());
					ast.setShow_type_name(showTypes[i] + ":" + imgWordType);
					ast.setTitle(title);
					ast.setAd_info_url(imgPath);
					ast.setClick_effect(showType);
					ast.setClick_url(showSrc);
					ast.setPay_mode(payMode);
					ast.setPrice(Double.parseDouble(price));
					ast.setAd_detail(addetail);

					adShowTypes.add(ast);

					// 判断用户是否修改了图片
					List<ADShowType> oldShowTypes = as.getADShowTypesByADID(currentAd.getId());
					boolean isEdited = true;
					File oldImg = null;
					String path = request.getSession().getServletContext().getRealPath("/files/ad");
					for (ADShowType oast : oldShowTypes) {
						String showTypeName = oast.getShow_type_name().split(":")[0];
						if ("悬浮型".endsWith(showTypeName) && imgPath != null) {
							if (imgPath.equals(oast.getAd_info_url())) {
								isEdited = false;
							} else {
								oldImg = new File(path, oast.getAd_info_url());
							}
						}
					}

					// 如果修改，则将广告图片存起立
					if (isEdited) {
						if (!"仅广告文字".equals(imgWordType)) {
							createDir(path, request);
							FileItem fileItem = (FileItem) request.getSession().getAttribute("currentFloatFile");
							System.out.println(path + " : " + imgPath);
							File mkr = new File(path, imgPath);
							try {
								if (mkr.createNewFile()) {
									fileItem.write(mkr);
									request.getSession().removeAttribute("currentAdImg");
									// 删除之前旧的图片
									if (oldImg != null) {
										oldImg.delete();
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
			currentAd.setShowTypes(adShowTypes);
			currentAd.setState("待审批");

			as.modify(currentAd);
			request.getSession().removeAttribute("putwayInfo");
			request.getSession().removeAttribute("showTypes");
			request.getSession().removeAttribute("currentAd");
		} else {
			return "2:jsp/ad/saveadinfo.jsp";
		}

		return "2:ctrl/adManage.ad";// 进广告管理
	}

	/**
	 * 验证上传的图片（仅显示图片）
	 */
	@SuppressWarnings("unchecked")
	public String uploadImage2(HttpServletRequest request, HttpServletResponse response) {
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
					BufferedImage image = ImageIO.read(fileItem.getInputStream());
					int width = image.getWidth();
					int height = image.getHeight();
					if (width != 320 || height != 50) {
						AjaxUtils.sendJSON(response, "no尺寸只能是320*50");
					} else {
						int pointIndex = fileFullName.lastIndexOf('.');
						int startIndex = fileFullName.lastIndexOf('\\') + 1;
						String fileName;
						if (startIndex != -1) {
							fileName = fileFullName.substring(startIndex, pointIndex) + System.currentTimeMillis();
						} else {
							fileName = fileFullName.substring(0, pointIndex) + System.currentTimeMillis();
						}
						String postfix = fileFullName.substring(pointIndex, fileFullName.length());
						String type = (String) request.getAttribute("type");
						if ("float".equals(type)) {
							request.getSession().setAttribute("currentFloatFile", fileItem);
						} else {
							request.getSession().setAttribute("currentAdImg", fileItem);
						}
						AjaxUtils.sendJSON(response, "ok" + fileName + postfix);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	/**
	 * 验证上传的图片（显示图片+文字）
	 */
	@SuppressWarnings("unchecked")
	public String uploadImage3(HttpServletRequest request, HttpServletResponse response) {
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
					BufferedImage image = ImageIO.read(fileItem.getInputStream());
					int width = image.getWidth();
					int height = image.getHeight();
					if (width != 38 || height != 38) {
						AjaxUtils.sendJSON(response, "no尺寸只能是38*38");
					} else {
						int pointIndex = fileFullName.lastIndexOf('.');
						int startIndex = fileFullName.lastIndexOf('\\') + 1;
						String fileName;
						if (startIndex != -1) {
							fileName = fileFullName.substring(startIndex - 1, pointIndex) + System.currentTimeMillis();
						} else {
							fileName = fileFullName.substring(0, pointIndex) + System.currentTimeMillis();
						}
						String postfix = fileFullName.substring(pointIndex, fileFullName.length());
						String type = (String) request.getAttribute("type");
						if ("float".equals(type)) {
							request.getSession().setAttribute("currentFloatFile", fileItem);
						} else {
							request.getSession().setAttribute("currentAdImg", fileItem);
						}
						AjaxUtils.sendJSON(response, "ok" + fileName + postfix);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	/**
	 * 验证上传的图片或视频（插屏型）
	 */
	@SuppressWarnings("unchecked")
	public String uploadInsert(HttpServletRequest request, HttpServletResponse response) {
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
					String type = (String) request.getAttribute("type");
					if ("image".equals(type)) {
						String fileFullName = fileItem.getName();
						BufferedImage image = ImageIO.read(fileItem.getInputStream());
						int width = image.getWidth();
						int height = image.getHeight();
						if (width != 480 || height != 800) {
							AjaxUtils.sendJSON(response, "no尺寸只能是480*800");
						} else {
							int pointIndex = fileFullName.lastIndexOf('.');
							int startIndex = fileFullName.lastIndexOf('\\') + 1;
							String fileName;
							if (startIndex != -1) {
								fileName = fileFullName.substring(startIndex, pointIndex) + System.currentTimeMillis();
							} else {
								fileName = fileFullName.substring(0, pointIndex) + System.currentTimeMillis();
							}
							String postfix = fileFullName.substring(pointIndex, fileFullName.length());
							request.getSession().setAttribute("currentInsertFile", fileItem);
							AjaxUtils.sendJSON(response, "ok" + fileName + postfix);
						}
					} else if ("view".equals(type)) {
						// TODO 暂时不做验证
						String fileFullName = fileItem.getName();
						int pointIndex = fileFullName.lastIndexOf('.');
						int startIndex = fileFullName.lastIndexOf('\\') + 1;
						String fileName;
						if (startIndex != -1) {
							fileName = fileFullName.substring(startIndex, pointIndex) + System.currentTimeMillis();
						} else {
							fileName = fileFullName.substring(0, pointIndex) + System.currentTimeMillis();
						}
						String postfix = fileFullName.substring(pointIndex, fileFullName.length());
						request.getSession().setAttribute("currentInsertFile", fileItem);
						AjaxUtils.sendJSON(response, "ok" + fileName + postfix);
					} else {
						AjaxUtils.sendJSON(response, "no很抱歉，系统暂时无法上传，请与客服联系！");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "3:ajax";
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

	public String adManage(HttpServletRequest request, HttpServletResponse response) {
		AD con = new AD();
		con.setMem_id(((Member) request.getSession().getAttribute("member")).getId());
		con.setState("可用");
		List<AD> adList = as.getADsByCondition(con);
		String adListString = AjaxUtils.jsonString(adList);
		request.setAttribute("adListString", adListString);
		request.setAttribute("searchState", "可用");
		// request.setAttribute("adList", adList);
		return "1:jsp/ad/showad.jsp";
	}

	public String adManageByCondition(HttpServletRequest request, HttpServletResponse response) {
		AD con = new AD();
		con.setMem_id(((Member) request.getSession().getAttribute("member")).getId());
		String state = request.getParameter("state");
		con.setState(state);
		List<AD> adList = as.getADsByCondition(con);
		request.setAttribute("searchState", state);
		request.setAttribute("adList", adList);
		String adListString = AjaxUtils.jsonString(adList);
		request.setAttribute("adListString", adListString);
		return "1:jsp/ad/showad.jsp";
	}

	/**
	 * 
	 * @param request
	 *            点击查看按钮
	 * @param response
	 * @return 返回广告信息
	 */
	public String getAdPutWays(HttpServletRequest request, HttpServletResponse response) {
		int adId = Integer.parseInt(request.getParameter("adId"));

		List<ADShowType> showTypes = as.getADShowTypesByADID(adId);

		request.setAttribute("adId", adId);
		request.setAttribute("showTypes", showTypes);
		return "1:jsp/ad/checkshowtype.jsp";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return 广告审批页面的其它信息
	 */

	public String adCheckOtherInfo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("adId"));
		AdCheckOtherInfo aco = as.getAdCheckOtherInfo(id);
		String s1 = aco.getAdName();
		String s2 = aco.getAdType();
		Double s3 = aco.getAdBalance();
		AjaxUtils.sendJSON(response, s1 + "‖" + s2 + "‖" + s3);
		return "3:ajax";
	}

	public String editAD(HttpServletRequest request, HttpServletResponse response) {
		int adId = Integer.parseInt(request.getParameter("adId"));
		AD ad = as.getAD(adId);
		Member mem = (Member) request.getSession().getAttribute("member");
		if (!ad.getMem_id().equals(mem.getId())) {
			try {
				request.setAttribute("error", "请选择您的广告！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("editedAD", ad);
		return "1:jsp/ad/saveadinfo.jsp";
	}

	public String saveEditAD(HttpServletRequest request, HttpServletResponse response) {
		AD newAd = new AD();
		newAd.setId(Integer.parseInt(request.getParameter("adId")));
		newAd.setName(request.getParameter("name"));
		newAd.setType(request.getParameter("type"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(request.getParameter("startDate"));
			Date endDate = sdf.parse(request.getParameter("endDate"));
			newAd.setStart_date(startDate);
			newAd.setEnd_date(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String maxPay = request.getParameter("maxPayPerDay");
		if (!"".equals(maxPay)) {
			newAd.setMax_pay_perday(Double.parseDouble(maxPay));
		}

		// 存储广告所选平台
		String[] appPlatform = request.getParameterValues("appPlatform");
		char[] temp = "00".toCharArray();
		for (int i = 0; appPlatform != null && i < appPlatform.length; i++) {
			if ("1".equals(appPlatform[i])) {
				temp[0] = '1';
			} else if ("2".equals(appPlatform[i])) {
				temp[1] = '1';
			}
		}
		String apf = String.valueOf(temp);
		newAd.setApp_platform(apf);

		newAd.setState("未完成");

		AD currentAD = as.modifyAdBacicInfo(newAd);

		// request.setAttribute("adId", ad.getId());
		request.getSession().setAttribute("currentAd", currentAD);
		List<String[]> putwayInfo = as.getPutwayInfosById(currentAD.getId());
		request.getSession().setAttribute("putwayInfo", putwayInfo);
		return "2:jsp/ad/selectputways.jsp";
	}

	/**
	 * 设置统计条件
	 */
	public String setStatCondition(HttpServletRequest request, HttpServletResponse response) {

		AD condition = new AD();
		condition.setMem_id(((Member) request.getSession().getAttribute("member")).getId());
		condition.setState("可用");// 应该是可用
		List<AD> adList = as.getADsByCondition(condition);
		request.setAttribute("adList", adList);
		return "1:jsp/ad/setstatcondition.jsp";
	}

	/**
	 * 显示按时间统计结果
	 */
	public String showStatResult(HttpServletRequest request, HttpServletResponse response) {

		String[] adIds = request.getParameterValues("ads");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String statWay = request.getParameter("statWay");
		// String gatherWay = request.getParameter("gatherWay");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int[] intAdIds = new int[adIds.length];
		for (int i = 0; i < intAdIds.length; i++) {
			intAdIds[i] = Integer.parseInt(adIds[i]);
		}

		List<AdStatInfo> statList = null;
		try {
			statList = as.getStatInfo(intAdIds, startDate.equals("") ? null : sdf.parse(startDate),
					endDate.equals("") ? null : sdf.parse(endDate), statWay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String appStatStr = AjaxUtils.jsonString(statList);
		request.setAttribute("adStatStr", appStatStr);
		return "1:jsp/ad/showstatinfo.jsp";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return 按用户群统计结果
	 */
	public String adStatisticsByCrowd(HttpServletRequest request, HttpServletResponse response) {
		String[] adIds = request.getParameterValues("ads");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String[] userCrowd = request.getParameterValues("userCrowd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int[] intAdIds = new int[adIds.length];
		for (int i = 0; i < intAdIds.length; i++) {
			intAdIds[i] = Integer.parseInt(adIds[i]);
		}
		List<StatisticsByCrowd> listStatisticsByCrowd = null;
		try {
			listStatisticsByCrowd = as.getStatisticsByCrowd(intAdIds, startDate.equals("") ? null : sdf
					.parse(startDate), endDate.equals("") ? null : sdf.parse(endDate), userCrowd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String result = AjaxUtils.jsonString(listStatisticsByCrowd);
		request.setAttribute("adStatisticsByCrowd", result);
		return "1:jsp/ad/showgeocrowdinfo.jsp";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return 按地理统计结果
	 */
	public String adStatisticsByGeography(HttpServletRequest request, HttpServletResponse response) {
		String[] adIds = request.getParameterValues("ads");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String[] ck_province = request.getParameterValues("ck_province");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int[] intAdIds = new int[adIds.length];
		for (int i = 0; i < intAdIds.length; i++) {
			intAdIds[i] = Integer.parseInt(adIds[i]);
		}
		List<StatisticsByGeography> listStatisticsByGeography = null;
		try {
			listStatisticsByGeography = as.getStatisticsByGeography(intAdIds, startDate.equals("") ? null : sdf
					.parse(startDate), endDate.equals("") ? null : sdf.parse(endDate), ck_province);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String result = AjaxUtils.jsonString(listStatisticsByGeography);
		request.setAttribute("adStatisticsByGeography", result);
		return "1:jsp/ad/showgeographyinfo.jsp";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return 按时段统计结果
	 */
	public String adStatisticsByTimeInterval(HttpServletRequest request, HttpServletResponse response) {
		String[] adIds = request.getParameterValues("ads");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String[] showtime = request.getParameterValues("showtime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int[] intAdIds = new int[adIds.length];
		for (int i = 0; i < intAdIds.length; i++) {
			intAdIds[i] = Integer.parseInt(adIds[i]);
		}
		List<StatisticsByTimeInterval> listStatisticsByTimeInterval = null;
		try {
			listStatisticsByTimeInterval = as.getStatisticsByTimeInterval(intAdIds, startDate.equals("") ? null : sdf
					.parse(startDate), endDate.equals("") ? null : sdf.parse(endDate), showtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String result = AjaxUtils.jsonString(listStatisticsByTimeInterval);
		request.setAttribute("adStatisticsByTimeInterval", result);
		return "1:jsp/ad/showtimeintervalinfo.jsp";
	}

	/**
	 * 接收手机端的广告请求，选择合适的广告传回手机端显示
	 */
	public String doPhoneRequest(HttpServletRequest request, HttpServletResponse response) {
		String imei = request.getParameter("imei");
		String appID = request.getParameter("appid");
		String ars = request.getParameter("ars");
		// String ars = "中国移动";

		System.out.println(imei + ", " + appID + ", " + ars);

		String objStr = as.getOKAD(appID, ars, imei);

		try {
			response.getOutputStream().write(objStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "3:ajax";
	}

	/**
	 * 用户点击广告后在ClickInfo中插入一条数据
	 */
	public String doADClick(HttpServletRequest request, HttpServletResponse response) {
		String imei = request.getParameter("imei");
		String adID = request.getParameter("adid");
		String appID = request.getParameter("appid");

		as.addClickInfo(Integer.parseInt(adID), appID, imei);
		System.out.println(imei + ", " + appID + ", " + adID);

		return "3:ajax";
	}

	/**
	 * 更改广告状态
	 */
	public String doChangeADsState(HttpServletRequest request, HttpServletResponse response) {
		String adIDs = request.getParameter("ads");
		String newState = request.getParameter("newState");
		String[] ids = adIDs.split(",");
		int[] intIDs = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			intIDs[i] = Integer.parseInt(ids[i]);
		}
		as.modifyADsState(intIDs, newState);
		return "3:ajax";
	}

	/**
	 * 判断应用可否被删除
	 */
	public String testAppIDCouldDelete(HttpServletRequest request, HttpServletResponse response) {
		String appID = request.getParameter("appid");
		boolean flag = as.testAppIDCouldDelete(appID);
		AjaxUtils.sendJSON(response, flag);
		return "3:ajax";
	}

	/**
	 * 判断广告账户余额是否小于0.5
	 */
	public String testADBalance(HttpServletRequest request, HttpServletResponse response) {
		int memID = ((Member) request.getSession().getAttribute("member")).getId();
		boolean flag = as.testADBalance(memID);
		AjaxUtils.sendJSON(response, flag);
		return "3:ajax";
	}

	/**
	 * 获取当前的广告展示次数
	 */
	public String getCurShowCount(HttpServletRequest request, HttpServletResponse response) {
		int showCount = as.getCurShowCount();
		AjaxUtils.sendJSON(response, showCount);
		return "3:ajax";
	}

	/**
	 * 文件下载
	 */
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取下载文件路径
			String path = request.getSession().getServletContext().getRealPath("files") + File.separator
					+ request.getParameter("file");
			// 编码
			path = URLEncoder.encode(path, "ISO-8859-1");
			// 解码
			path = URLDecoder.decode(path, "UTF-8");
			File file = new File(path);
			// 文件名
			String fileName = file.getName();
			// 扩展名
			@SuppressWarnings("unused")
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
			// 输入流
			InputStream inStream = new FileInputStream(path);
			InputStream in = new BufferedInputStream(inStream);
			byte[] bs = new byte[in.available()];
			in.read(bs);
			in.close();
			// 清空response
			response.reset();
			// 设置response的Header
			// 使浏览器弹出下载对话框
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			// 输出流
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(bs);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
		}
		return "3:ajax";
	}
}
