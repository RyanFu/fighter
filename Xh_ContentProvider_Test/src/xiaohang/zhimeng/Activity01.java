package xiaohang.zhimeng;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class Activity01 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*插入数据*/
        ContentValues values = new ContentValues();
        values.put(NotePad.Notes.TITLE, "title1");
        values.put(NotePad.Notes.NOTE, "NOTENOTE1");
        getContentResolver().insert(NotePad.Notes.CONTENT_URI, values);
        
        values.clear();
        values.put(NotePad.Notes.TITLE, "title2");
        values.put(NotePad.Notes.NOTE, "NOTENOTE2");
        getContentResolver().insert(NotePad.Notes.CONTENT_URI, values);
        //显示
        displayNote();
    }
    
    private void displayNote(){
    	String columns[] = new String[] { NotePad.Notes._ID,
    									  NotePad.Notes.TITLE,
    									  NotePad.Notes.NOTE,
    									  NotePad.Notes.CREATEDDATE,
    									  NotePad.Notes.MODIFIEDDATE};
    	
    	Uri myUri = NotePad.Notes.CONTENT_URI;
    	Cursor cur = managedQuery(myUri, columns, null, null, null);
    	if (cur.moveToFirst()) {
			String id = null;
			String titile = null;
			do {
				id = cur.getString(cur.getColumnIndex(NotePad.Notes._ID));
				titile = cur.getString(cur.getColumnIndex(NotePad.Notes.TITLE));
				Toast toast = Toast.makeText(this, "TITILE:"+id + "\t" + "NOTE:" + titile, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 40);
				toast.show();
			} while (cur.moveToNext());
		}
    }
}