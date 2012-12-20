package cn.com.uangel.adsdk.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.com.uangel.adsdk.entity.Advertisement;
import cn.com.uangel.adsdk.socketrequest.ClientSocketRequest;
import cn.com.uangel.adsdk.util.ClickListner;

public class AdView_Implicit_Dialog extends Dialog implements OnClickListener {

	private Context context;
	private LinearLayout outContainer = null;
	private Advertisement myAd = null;
	private Button downloadBut = null;
	private Button cancel = null;
	private ImageView image = null; // 中图片控件
	private TextView adTitle = null;// 图片加文字广告标题

	private final int contentWidth = 440;// 详细信息宽度
	private final int contentHeight = 260;// 详细信息高度
	private final int topDialog = 80;// 顶部title高度
	private int textColor = 0;

	public AdView_Implicit_Dialog(Context context) {
		super(context);
		this.context = context;
	}

	public AdView_Implicit_Dialog(Context context, Advertisement ad, int textColor) {
		this(context);
		this.context = context;
		this.myAd = ad;
		this.textColor = textColor;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (myAd != null) {
			LinearLayout main = init();
			this.setContentView(main);
		}

	}

	/**
	 * 
	 * @return dialog 整体布局视图
	 */
	private LinearLayout init() {
		// 最外层布局
		outContainer = new LinearLayout(context);
		outContainer.setOrientation(LinearLayout.VERTICAL);
		outContainer.setBackgroundColor(Color.WHITE);
		outContainer.setGravity(Gravity.CENTER_VERTICAL | Gravity.CLIP_HORIZONTAL);

		// 顶部dialog标题
		LinearLayout.LayoutParams llbtn = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, topDialog);
		Button titleButton = new Button(context);
		titleButton.setLayoutParams(llbtn);
		titleButton.setText("精品应用推荐");

		// 图片或者 图片+文字 广告布局
		LinearLayout titleAD = titleChoise();

		// 顶部分割线
		LinearLayout.LayoutParams line = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1);
		line.setMargins(10, 0, 10, 0);
		TextView lineText = new TextView(context);
		lineText.setBackgroundColor(Color.BLACK);
		lineText.setLayoutParams(line);

		// “详细介绍” 文字
		LinearLayout.LayoutParams detailTextp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView detailText = new TextView(context);
		detailText.setLayoutParams(detailTextp);
		detailText.setTextColor(Color.BLACK);
		detailText.setPadding(20, 10, 0, 10);
		detailText.setTextSize(20);
		detailText.setText("详细介绍：");

		// 详细信息滚动
		FrameLayout.LayoutParams frLayoutParams = new FrameLayout.LayoutParams(contentWidth, contentHeight);
		ScrollView scrollView = new ScrollView(context);
		scrollView.setLayoutParams(frLayoutParams);

		// 广告的详细信息
		LinearLayout.LayoutParams contentTextp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView contentText = new TextView(context);
		contentText.setLayoutParams(contentTextp);
		contentText.setTextColor(Color.BLACK);
		contentText.setPadding(20, 10, 20, 10);
		contentText.setText(myAd.getDetail());

		scrollView.addView(contentText);
		// 设置底部按钮的布局
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setGravity(Gravity.CENTER);
		linearLayout.setPadding(0, 20, 0, 0);

		// 底部按钮比重
		LinearLayout.LayoutParams btnWidth = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 1);

		downloadBut = new Button(context);
		downloadBut.setOnClickListener(this);
		downloadBut.setText(choiseBtnText(myAd));

		downloadBut.setLayoutParams(btnWidth);
		cancel = new Button(context);
		cancel.setOnClickListener(this);
		cancel.setText("取消");
		cancel.setLayoutParams(btnWidth);

		linearLayout.addView(downloadBut);
		linearLayout.addView(cancel);

		outContainer.addView(titleButton);
		outContainer.addView(titleAD);
		outContainer.addView(lineText);
		outContainer.addView(detailText);
		outContainer.addView(scrollView);
		outContainer.addView(linearLayout);
		return outContainer;

	}

	private LinearLayout titleChoise() {
		LinearLayout outLayout = new LinearLayout(context);
		LinearLayout.LayoutParams abs = new LinearLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 70);
		outLayout.setLayoutParams(abs);
		switch (myAd.getTitleOrImg()) {
		case 0:

			image = new ImageView(context);
			image.setBackgroundDrawable(myAd.getImages());
			RelativeLayout.LayoutParams lp_logo = new RelativeLayout.LayoutParams(46, 46);
			lp_logo.setMargins(6, 6, 2, 0);
			lp_logo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

			adTitle = new TextView(context);
			String title = myAd.getAdTitle();
			adTitle.setText(title);
			if (title.length() >= 15) {
				adTitle.setTextSize(14);
			} else {
				adTitle.setTextSize(17);
			}
			adTitle.setTextColor(textColor);
			RelativeLayout.LayoutParams lp_text = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			lp_text.setMargins(80, 11, 0, 0);
			lp_text.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

			RelativeLayout reLayout = new RelativeLayout(context);
			reLayout.addView(image, lp_logo);
			reLayout.addView(adTitle, lp_text);
			outLayout.addView(reLayout);
			break;

		case 1:

			LinearLayout.LayoutParams lp_text2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT);
			lp_text2.setMargins(0, 0, 0, 0);
			outLayout.setOrientation(LinearLayout.VERTICAL);
			image = new ImageView(context);
			image.setBackgroundDrawable(myAd.getImages());
			outLayout.addView(image, lp_text2);

			break;
		case 2:
			adTitle = new TextView(context);
			adTitle.setText(myAd.getAdTitle());
			if (myAd.getAdTitle().length() >= 15) {
				adTitle.setTextSize(14);
			} else {
				adTitle.setTextSize(17);
			}
			adTitle.setTextColor(textColor);
			adTitle.setPadding(20, 20, 0, 0);
			RelativeLayout.LayoutParams lp_text1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			lp_text1.setMargins(6, 11, 0, 0);
			lp_text1.addRule(RelativeLayout.CENTER_HORIZONTAL);
			outLayout.addView(adTitle, lp_text1);
			break;

		}

		return outLayout;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == downloadBut) {
			ClickListner.onClick(context, myAd);
			ClientSocketRequest.getIntence().sendClickInfo(myAd);
		} else if (v == cancel) {
			this.dismiss();
		}
	}

	private String choiseBtnText(Advertisement ad) {
		String string = null;
		switch (ad.getClick_result()) {
		case 1:// 浏览网页
			string = "查看网页";
			break;
		case 2:// 下载应用
			string = "下载";
			break;
		case 3:// 打电话
			string = "拨打电话";
			break;
		case 4:// 发信息
			string = "发送短信";
			break;
		case 5:// 发邮件
			string = "发送邮件";
			break;
		case 6:// 地图
			string = "查看地图";
			break;
		case 7:// 视频
			string = "播放视频";
			break;
		case 8:// 音乐
			string = "播放音乐";
			break;
		case 9:// 显示 全屏图片
			string = "查看";
			break;
		default:
			string = "测试一下";
		}
		return string;
	}

}
