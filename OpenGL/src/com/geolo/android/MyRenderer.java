package com.geolo.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import com.geolo.android.polygon.MyCube;
import com.geolo.android.polygon.Polygon;

public class MyRenderer implements Renderer {

	private Polygon mPolygon ;//�����
	private Context mContext;

	public MyRenderer(Context context){
		this.mContext = context;
		mPolygon = new MyCube(mContext);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//		GL_PERSPECTIVE_CORRECTION_HINTʱ����ָ����ɫ����������Ĳ�ֵ����.
//		GL_FASTESTΪʹ���ٶ�����ģʽ.
//		GL_NICESTΪʹ��������õ�ģʽ.
//		����һ��GL_DONT_CAREΪ�������豸������.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
//		glClear��������OPENGL,��������ͨ��glClearʹ�ú죬�̣����Լ�AFAֵ�������ɫ�������ģ�
//		���Ҷ�����һ���ڣ�0��1��֮���ֵ����ʵ������յ�ǰ��������ɫ��
		gl.glClearColor(0, 0, 0, 1);
//		http://wei19870601.blog.163.com/blog/static/135936648201221311314371/
		gl.glEnable(GL10.GL_DEPTH_TEST);	
		gl.glClearDepthf(1.0f);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glCullFace(GL10.GL_BACK);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		float radio = (float)width/height;
		
//			glViewport(GLint x,GLint y,GLsizei width,GLsizei height)Ϊ�亯��ԭ�͡�
//		����X��Y��������������Ϊ��λ��ָ�����ӿڵ����½�λ�á�
//		����width��height����������ʾ����ӿھ��εĿ�Ⱥ͸߶ȣ����ݴ��ڵ�ʵʱ�仯�ػ洰�ڡ�
		gl.glViewport(0, 0, width, height);
		/**
		 * glMatrixMode - ָ����һ�������ǵ�ǰ����
		 * 
		 * mode ָ����һ�������ջ����һ�����������Ŀ��,��ѡֵ:
		 * GL_MODELVIEW��GL_PROJECTION��GL_TEXTURE. ���� ˵�� ���� glMatrixMode���õ�ǰ����ģʽ:
		 * ����GL_MODELVIEW,��ģ���Ӿ������ջӦ�����ľ������. ���� GL_PROJECTION,��ͶӰ����Ӧ�����ľ������.
		 * ����GL_TEXTURE,����������ջӦ�����ľ������. ���� ��glLoadIdentity()һͬʹ��
		 * ����glLoadIdentity():�ú����Ĺ��������õ�ǰָ���ľ���Ϊ��λ����
		 * ������glLoadIdentity()֮������Ϊ����������͸��ͼ
		 * ��glMatrixMode(GL_MODELVIEW)���õ�ǰ����Ϊģ����ͼ����ģ����ͼ���󴢴����й��������Ϣ��
		 */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-radio, radio, -1, 1, 1, 100);
		mPolygon.initData(gl);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);
		mPolygon.draw(gl);
	}

}
