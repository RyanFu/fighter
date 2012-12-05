package cn.com.uangel.mycontenprovider;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor c=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(c.moveToFirst()){
        	System.out.println("ddddddddddddddddd:   "+ c.getCount());
        	do {
        		String name=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        		Log.d("fff",name);
        	}while (c.moveToNext());
        }
        c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
