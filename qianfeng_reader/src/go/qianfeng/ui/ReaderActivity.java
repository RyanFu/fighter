package go.qianfeng.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/************************************************************************
 *	项目名字	:仿ireader书架 
 * @author  angelの慧
 * @version 2012-10-08
　*
************************************************************************/
public class ReaderActivity extends Activity {

    private ShelfAdapter mAdapter;
    private Button shelf_image_button;
    private ListView shelf_list;
    private Button button_1 , button_2 , button_3;

    @ Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.shelf );
        init ();
        mAdapter = new ShelfAdapter ();//new adapter对象才能用
        //将 shelf_list添加到adapter中
        shelf_list.setAdapter ( mAdapter );
    }

    private void init () {
    	//根据ID找到相应控件
        shelf_image_button = ( Button ) findViewById ( R.id.shelf_image_button );
        shelf_list = ( ListView ) findViewById ( R.id.shelf_list );
    }
    //ShelfAdapter继承BaseAdapter
    public class ShelfAdapter extends BaseAdapter {
    	//假设数据
        int[ ] size = new int[ 10 ];
        //构造函数
        public ShelfAdapter () {

        }

        @ Override
        public int getCount () {

            if ( size.length > 3 ) {
                return size.length;
            } else {
                return 3;
            }

        }

        @ Override
        public Object getItem ( int position ) {

            return size[ position ];
        }

        @ Override
        public long getItemId ( int position ) {

            return position;
        }

        @ Override
        //得到新的视图并对其进行自定义外观
        public View getView ( int position , View convertView , ViewGroup parent ) {
           //// 生成布局适配器，引用外部XML文件
        	LayoutInflater layout_inflater = ( LayoutInflater ) ReaderActivity.this.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            //生成新的view
        	View layout = layout_inflater.inflate ( R.layout.shelf_list_item , null );
            //根据button ID找到对应按钮控件
            Button button_1 , button_2 , button_3;
            button_1 = ( Button ) layout.findViewById ( R.id.button_1 );
            button_2 = ( Button ) layout.findViewById ( R.id.button_2 );
            button_3 = ( Button ) layout.findViewById ( R.id.button_3 );
            //对按钮设置监听
            button_1.setOnClickListener ( new ButtonOnClick () );
            button_2.setOnClickListener ( new ButtonOnClick () );
            button_3.setOnClickListener ( new ButtonOnClick () );
            return layout;
        }
    };
    //按钮吐司事件监听
    public class ButtonOnClick implements OnClickListener {
        @ Override
        public void onClick ( View v ) {
            switch ( v.getId () ) {
                case R.id.button_1 ://吐司button1
                    Toast.makeText ( ReaderActivity.this , "button1" , Toast.LENGTH_SHORT ).show ();
                    break;
                case R.id.button_2 ://吐司button2
                    Toast.makeText ( ReaderActivity.this , "button2" , Toast.LENGTH_SHORT ).show ();
                    break;
                case R.id.button_3 ://吐司button3
                    Toast.makeText ( ReaderActivity.this , "button3" , Toast.LENGTH_SHORT ).show ();
                    break;
            }
        }
    }
}