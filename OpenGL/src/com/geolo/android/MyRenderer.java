package com.geolo.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import com.geolo.android.polygon.MyCube;
import com.geolo.android.polygon.Polygon;

public class MyRenderer implements Renderer {

	private Polygon mPolygon ;//多边形
	private Context mContext;

	public MyRenderer(Context context){
		this.mContext = context;
		mPolygon = new MyCube(mContext);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//		GL_PERSPECTIVE_CORRECTION_HINT时，是指定颜色和纹理坐标的插值质量.
//		GL_FASTEST为使用速度最快的模式.
//		GL_NICEST为使用质量最好的模式.
//		还有一个GL_DONT_CARE为由驱动设备来决定.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
//		glClear函数来自OPENGL,其中它是通过glClear使用红，绿，蓝以及AFA值来清除颜色缓冲区的，
//		并且都被归一化在（0，1）之间的值，其实就是清空当前的所有颜色。
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
		
//			glViewport(GLint x,GLint y,GLsizei width,GLsizei height)为其函数原型。
//		　　X，Y————以像素为单位，指定了视口的左下角位置。
//		　　width，height————表示这个视口矩形的宽度和高度，根据窗口的实时变化重绘窗口。
		gl.glViewport(0, 0, width, height);
		/**
		 * glMatrixMode - 指定哪一个矩阵是当前矩阵
		 * 
		 * mode 指定哪一个矩阵堆栈是下一个矩阵操作的目标,可选值:
		 * GL_MODELVIEW、GL_PROJECTION、GL_TEXTURE. 　　 说明 　　 glMatrixMode设置当前矩阵模式:
		 * 　　GL_MODELVIEW,对模型视景矩阵堆栈应用随后的矩阵操作. 　　 GL_PROJECTION,对投影矩阵应用随后的矩阵操作.
		 * 　　GL_TEXTURE,对纹理矩阵堆栈应用随后的矩阵操作. 　　 与glLoadIdentity()一同使用
		 * 　　glLoadIdentity():该函数的功能是重置当前指定的矩阵为单位矩阵。
		 * 　　在glLoadIdentity()之后我们为场景设置了透视图
		 * 。glMatrixMode(GL_MODELVIEW)设置当前矩阵为模型视图矩阵，模型视图矩阵储存了有关物体的信息。
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
