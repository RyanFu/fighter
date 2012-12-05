package cn.com.uangel.copyassetstosd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String sd=Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
        CopyAssets("",sd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * 
     * @param assetDir root dir ---""
     * @param dir SDCard dir
     */
    private void CopyAssets(String assetDir, String dir)  //by yj
	{
		Log.v("CopyAssets","assetDir:"+assetDir);
		Log.v("CopyAssets","dir:"+dir);
		  String[] files;
		  try 
		  {
			  files = this.getResources().getAssets().list(assetDir);
		  } 
		  catch (IOException e1) 
		  {
			  return;
		  }
		  File mWorkingPath = new File(dir);
		  // if this directory does not exists, make one.
		  if (!mWorkingPath.exists()) 
		  {
			  if (!mWorkingPath.mkdirs()) 
			  {
				  	Log.e("--CopyAssets--", "cannot create directory.");
			  }
		  }
		  for (int i = 0; i < files.length; i++) 
		  {
			  try 
			  {
				  String fileName = files[i];
				  
				  if(fileName.compareTo("images") == 0 || fileName.compareTo("sounds") == 0 || fileName.compareTo("webkit") == 0)    
				  {    
					  continue;    
			      }
				  // we make sure file name not contains '.' to be a folder.
				  if (!fileName.contains(".")) 
				  {
					  if (0 == assetDir.length()) 
					  {
						  CopyAssets(fileName, dir + fileName + "/");
					  } 
					  else 
					  {
						  CopyAssets(assetDir + "/" + fileName, dir + fileName + "/");
					  }
					  continue;
				  }
				  File outFile = new File(mWorkingPath, fileName);
				  if (outFile.exists())
					  outFile.delete();
				  InputStream in = null;
				  if (0 != assetDir.length())
					  in = getAssets().open(assetDir + "/" + fileName);
				  else
					  in = getAssets().open(fileName);
				  OutputStream out = new FileOutputStream(outFile);
				  // Transfer bytes from in to out
				  byte[] buf = new byte[1024];
				  int len;
				  while ((len = in.read(buf)) > 0) 
				  {
					  out.write(buf, 0, len);
				  }
				  in.close();
				  out.close();
			  } 
			  catch (FileNotFoundException e) 
			  {
				  e.printStackTrace();
			  } 
			  catch (IOException e) 
			  {
				  e.printStackTrace();
			  }
		  }
		  Log.v("CopyAssets","zzzzzzzzzzzzzzzz");
	}
}
