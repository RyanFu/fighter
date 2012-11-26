package com.geolo.android.polygon;

import javax.microedition.khronos.opengles.GL10;

public interface Polygon {
	public void initData(GL10 gl);
	public void draw(GL10 gl);
}
