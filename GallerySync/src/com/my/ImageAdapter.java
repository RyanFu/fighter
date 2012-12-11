package com.my;

import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	public static final BaseAdapter Adapter = null;
	private List<String> imageUrls; // ͼƬ��ַlist
	private Context context;
	int mGalleryItemBackground;

	public ImageAdapter(List<String> imageUrls, Context context) {
		this.imageUrls = imageUrls;
		this.context = context;
		// /*
		// * ʹ����res/values/attrs.xml�е�<declare-styleable>���� ��Gallery����.
		// */
		TypedArray a = context.obtainStyledAttributes(R.styleable.Gallery1);
		/* ȡ��Gallery���Ե�Index id */
		mGalleryItemBackground = a.getResourceId(
				R.styleable.Gallery1_android_galleryItemBackground, 0);
		/* �ö����styleable�����ܹ�����ʹ�� */
		a.recycle();
	}

	public int getCount() {
		return imageUrls.size();
	}

	public Object getItem(int position) {
		return imageUrls.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Bitmap image;
		ImageView view = new ImageView(context);
		image = MyActivity.imagesCache.get(imageUrls.get(position));
		// �ӻ����ж�ȡͼƬ
		if (image == null) {
			image = MyActivity.imagesCache.get("background_non_load");
		}
		// ��������ͼƬ����Դ��ַ
		view.setImageBitmap(image);
		view.setScaleType(ImageView.ScaleType.FIT_XY);
		view.setLayoutParams(new Gallery.LayoutParams(240, 320));
		view.setBackgroundResource(mGalleryItemBackground);
		/* ����Gallery����ͼ */
		return view;
	}
}
