package com.geolo.android.polygon;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.geolo.android.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class MyCube implements Polygon{

	private float vertexs[][] = {
			new float[]{//top
					-1,1,-1,
					-1,1,1,
					1,1,1,
					1,1,-1,
			},
			new float[]{//right
					1,1,1,
					1,-1,1,
					1,-1,-1,
					1,1,-1,
			},
			new float[]{//bottom
					-1,-1,-1,
					-1,-1,1,
					1,-1,1,
					1,-1,-1,
			},
			new float[]{//left
					-1,1,1,
					-1,-1,1,
					-1,-1,-1,
					-1,1,-1,
			},
			new float[]{//front
					-1,1,1,
					-1,-1,1,
					1,-1,1,
					1,1,1
			},
			new float[]{//back
					-1,1,-1,
					-1,-1,-1,
					1,-1,-1,
					1,1,-1
			},
	};

	private float texturecoods[][] = {
			new float[]{ //top
					0.0f,0.0f,
					1.0f,0.0f,
					1.0f,1.0f,
					0.1f,1.0f,
			},
			new float[]{ //right
					0.0f,0.0f,
					1.0f,0.0f,
					1.0f,1.0f,
					0.1f,1.0f,
			},
			new float[]{ //bottom
					0.0f,0.0f,
					1.0f,0.0f,
					1.0f,1.0f,
					0.1f,1.0f,
			},
			new float[]{ //left
					0.0f,0.0f,
					1.0f,0.0f,
					1.0f,1.0f,
					0.1f,1.0f,
			},
			new float[]{ //front
					0.0f,0.0f,
					1.0f,0.0f,
					1.0f,1.0f,
					0.1f,1.0f,
			},
			new float[]{ //back
					0.0f,0.0f,
					1.0f,0.0f,
					1.0f,1.0f,
					0.1f,1.0f,
			},
	};

	private float normals[][] = {
			new float[]{//top
					0.0f,1.0f,0.0f,//对应到每个顶点的法线
					0.0f,1.0f,0.0f,
					0.0f,1.0f,0.0f,
					0.0f,1.0f,0.0f,
			},
			new float[]{//right
					1.0f,0.0f,0.0f,
					1.0f,0.0f,0.0f,
					1.0f,0.0f,0.0f,
					1.0f,0.0f,0.0f,
			},
			new float[]{//bottom
					0.0f,-1.0f,0.0f,
					0.0f,-1.0f,0.0f,
					0.0f,-1.0f,0.0f,
					0.0f,-1.0f,0.0f,
			},
			new float[]{//left
					-1.0f,0.0f,0.0f,
					-1.0f,0.0f,0.0f,
					-1.0f,0.0f,0.0f,
					-1.0f,0.0f,0.0f,
			},
			new float[]{//front
					0.0f,0.0f,1.0f,
					0.0f,0.0f,1.0f,
					0.0f,0.0f,1.0f,
					0.0f,0.0f,1.0f,
					0.0f,0.0f,1.0f,
			},
			new float[]{//back
					0.0f,0.0f,-1.0f,
					0.0f,0.0f,-1.0f,
					0.0f,0.0f,-1.0f,
					0.0f,0.0f,-1.0f,
			},
	};

	private FloatBuffer vertexBuffers[] = new FloatBuffer[vertexs.length];
	private FloatBuffer textureBuffers[] = new FloatBuffer[texturecoods.length];
	private FloatBuffer normalBuffers[] = new FloatBuffer[normals.length];
	private float angle = 0;
	private Bitmap mBitmap1,mBitmap2,mBitmap3,mBitmap4,mBitmap5,mBitmap6;
	private IntBuffer intBuffer;

	public MyCube(Context context){
		mBitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.side1);
		mBitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.side1);
		mBitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.side1);
		mBitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.side1);
		mBitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.side1);
		mBitmap6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.side1);
		/////vertex
		for(int i=0; i < vertexBuffers.length;i++){
			vertexBuffers[i] = FloatBuffer.wrap(vertexs[i]);
		}
		/////texture
		for(int i=0; i < textureBuffers.length;i++){
			textureBuffers[i] = FloatBuffer.wrap(texturecoods[i]);
		}
		/////normal
		for(int i=0; i < normalBuffers.length;i++){
			normalBuffers[i] = FloatBuffer.wrap(normals[i]);
		}
	}

	public void setupTexture(GL10 gl){
		gl.glEnable(GL10.GL_TEXTURE_2D);
		intBuffer = IntBuffer.allocate(6);
		gl.glGenTextures(6, intBuffer);
		//////texture01
		gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(0));
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap1, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		
		//////texture02
		gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(1));
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap2, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		//////texture03
		gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(2));
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap3, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		//////texture04
		gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(3));
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap4, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		//////texture05
		gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(4));
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap5, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		//////texture06
		gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(5));
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap6, 0);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);

		mBitmap1.recycle();
		mBitmap2.recycle();
		mBitmap3.recycle();
		mBitmap4.recycle();
		mBitmap5.recycle();
		mBitmap6.recycle();
	}

	@Override
	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -3.0f);
		gl.glRotatef(angle,1.0f, 1.0f, 0.5f);
		gl.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
		for(int i=0; i<6;i++){//正方体有6个面
			gl.glBindTexture(GL10.GL_TEXTURE_2D, intBuffer.get(i));
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffers[i]);	
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffers[i]);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffers[i]);
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		}
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		angle += 1.5f;
	}

	@Override
	public void initData(GL10 gl) {
		setupTexture(gl);
	}

}
