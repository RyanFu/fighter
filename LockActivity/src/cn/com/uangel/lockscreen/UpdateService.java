package cn.com.uangel.lockscreen;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateService extends IntentService {

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub

		System.out.println("service is start");
		super.onStart(intent, startId);
	}

	public UpdateService() {
		super("UpdateService");
		// TODO Auto-generated constructor stub
	}

	public static final String RE_ENABLE = "reenable";
	public static final String STATE = "state";
	public static final String TITLE = "Lock";

	
	public void onHandleIntent(Intent paramIntent) {
		System.out.println("UpdateService::onHandleIntent");
		Log.d("ToggleWidget", "UpdateService::onHandleIntent");
		ComponentName localComponentName = new ComponentName(this,
				MainActivity.class);
		AppWidgetManager localAppWidgetManager = AppWidgetManager
				.getInstance(this);
		RemoteViews localRemoteViews = buildUpdate(this);
		localAppWidgetManager.updateAppWidget(localComponentName,
				localRemoteViews);
	}
	private RemoteViews buildUpdate(Context context) {
		Log.d("ToggleWidget", "UpdateService::buildUpdate");
		System.out.println("111111111111111111");
		String str = context.getPackageName();
		RemoteViews localRemoteViews = new RemoteViews(str,
				R.layout.activity_main);
		Intent localIntent = new Intent(context,
				LockActivity.class);
		PendingIntent localPendingIntent = PendingIntent.getActivity(
				context, 0, localIntent, 0);
		localRemoteViews.setOnClickPendingIntent(R.id.lock_screen, localPendingIntent);
		localRemoteViews.setOnClickPendingIntent(R.id.relativeLayout, localPendingIntent);
		
		return localRemoteViews;
	}

	
}