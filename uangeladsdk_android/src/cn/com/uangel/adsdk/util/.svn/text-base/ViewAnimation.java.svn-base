package cn.com.uangel.adsdk.util;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ViewAnimation extends Animation {
	Camera m_camera;

	public ViewAnimation() {
//		super();
		m_camera = new Camera();
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		// TODO Auto-generated method stub
		super.initialize(width, height, parentWidth, parentHeight);
		setDuration(2500);
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		// TODO Auto-generated method stub
		// super.applyTransformation(interpolatedTime, t);
		final Matrix matrix = t.getMatrix();

		m_camera.save();
		m_camera.translate(0.0f, 0.0f, 0.0f);
		m_camera.rotateY(360 * interpolatedTime);
		m_camera.getMatrix(matrix);

		
		m_camera.restore();
		// matrix.setScale(interpolatedTime, interpolatedTime);
		matrix.preTranslate(-240, -100);
		matrix.postTranslate(240, 100);

		
	}

}
