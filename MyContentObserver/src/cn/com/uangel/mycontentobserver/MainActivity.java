package cn.com.uangel.mycontentobserver;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvAirplane;
	private EditText etSmsoutbox;

	// Message 类型值
	private static final int MSG_AIRPLANE = 1;
	private static final int MSG_OUTBOXCONTENT = 2;

	private AirplaneContentObserver airplaneCO;
	private SMSContentObserver smsContentObserver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvAirplane = (TextView) findViewById(R.id.button1);
		etSmsoutbox = (EditText) findViewById(R.id.menu_settings);

		// 创建两个对象
		airplaneCO = new AirplaneContentObserver(this, mHandler);
		smsContentObserver = new SMSContentObserver(this, mHandler);
		
		//注册内容观察者
		registerContentObservers() ;
	}

	private void registerContentObservers() {
		// 通过调用getUriFor 方法获得 system表里的"飞行模式"所在行的Uri
		Uri airplaneUri = Settings.System.getUriFor(Settings.System.AIRPLANE_MODE_ON);
		// 注册内容观察者
		getContentResolver().registerContentObserver(airplaneUri, false, airplaneCO);
		// ”表“内容观察者 ，通过测试我发现只能监听此Uri -----> content://sms
		// 监听不到其他的Uri 比如说 content://sms/outbox
		Uri smsUri = Uri.parse("content://sms");
		getContentResolver().registerContentObserver(smsUri, true,smsContentObserver);
	}

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			
			System.out.println("---mHanlder----");
			switch (msg.what) {
			case MSG_AIRPLANE:
				int isAirplaneOpen = (Integer) msg.obj;
				if (isAirplaneOpen != 0)
					tvAirplane.setText("飞行模式已打开");
				else if (isAirplaneOpen == 0)
					tvAirplane.setText("飞行模式已关闭");
				break;
			case MSG_OUTBOXCONTENT:
				String outbox = (String) msg.obj;
				etSmsoutbox.setText(outbox);
				break;
			default:
				break;
			}
		}
	};
}
