package cn.com.uangel.lockscreen;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
/**
 * 这一项目涉及到service的运用，也涉及到server的运用
 * 整体也是widget应用
 * @author Fighter
 *
 */
@SuppressLint("NewApi")
public class MainActivity extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onReceive");
		System.out.println("getAction:   " + intent.getAction());
		if (intent.getAction() == null) {
			System.out.println("want intent to UpdateService");
			Intent localIntent = new Intent(context, UpdateService.class);
			context.startService(localIntent);
		}

		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		System.out.println("onUpdate");
		System.out.println("123456");
		Intent localIntent = new Intent(context,UpdateService.class);
		context.startService(localIntent);
		
	}

	

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

}
