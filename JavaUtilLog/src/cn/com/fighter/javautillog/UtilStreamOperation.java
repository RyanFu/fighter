package cn.com.fighter.javautillog;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UtilStreamOperation {
	/**
	 * 采用流将一个文件移动到其他目录下
	 * @param thisFile
	 * @param anotherFile
	 */
	public void copyFileToAnother(String thisFile,String anotherFile){
		try {
			DataOutputStream datOut=null;
			DataInputStream datIn = new DataInputStream(new FileInputStream(
					thisFile));
			File file = new File(thisFile);
			if(file.exists()){
				byte[]bytes = new byte[8*1024];//
				datOut = new DataOutputStream(new FileOutputStream(
						anotherFile));
				
				int readByte = 0;
				while ((readByte = datIn.read(bytes)) != -1) {
					datOut.write(bytes, 0, readByte);
				}
			}
			datOut.flush();
			datOut.close();
			datIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
