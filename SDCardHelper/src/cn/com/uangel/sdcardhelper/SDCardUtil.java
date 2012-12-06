package cn.com.uangel.sdcardhelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.Toast;

/**
 * SDCard 帮助类 所有有关sdcard的操作
 * @author Fighter
 *
 */
/************************************************************************************************/

public class SDCardUtil extends Activity {

	/**
	 * 
	 * @return SDCard allSpare (MB)
	 */
	public static long getTotalSDCardSize() {
		long allSpare = 0;
		if (isSDCardReady()) {
			String sdcard = Environment.getExternalStorageDirectory().getPath();
			StatFs statFs = new StatFs(sdcard);
			long blockSize = statFs.getBlockSize();
			long allBlocks = statFs.getBlockCount();
			allSpare = (allBlocks * blockSize) / (1024 * 1024);
		}
		return allSpare;
	}

	/**
	 * 
	 * @return SDCard avaiableSpare (MB)
	 */
	public static long getAvaiableSDCardSize() {
		long avaiableSpare = 0;
		if (isSDCardReady()) {
			String sdcard = Environment.getExternalStorageDirectory().getPath();
			StatFs statFs = new StatFs(sdcard);
			long blockSize = statFs.getBlockSize();
			long availableBlocks = statFs.getAvailableBlocks();
			avaiableSpare = (availableBlocks * blockSize) / (1024 * 1024);
		}
		return avaiableSpare;
	}

	/**
	 * 
	 * @return 是否有SDCard
	 */
	public static boolean isSDCardReady() {
		String STATE = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(STATE);
	}

	/**
	 * 
	 * @return 返回sd卡根目录
	 */
	public static String getSDRootPath() {
		File SDdir = null;
		if (isSDCardReady()) {
			SDdir = Environment.getExternalStorageDirectory();
		}
		if (SDdir != null) {
			return SDdir.toString();
		} else {
			return null;
		}
	}

	/**
	 * 创建目录 或者文件夹
	 * 
	 * @param fileFoder
	 * @param fileName
	 * @return 如果创建了文件 则返回文件
	 */
	public static File createSDCardDir(String fileFoder, String fileName) {
		String newPath = null;
		File file = new File(fileFoder + fileName);
		if (isSDCardReady()) {
			Log.d("sd", "未找到sd卡");
		} else {
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			newPath = getSDRootPath() + fileFoder;// newPath在程序中要声明
			File path1 = new File(newPath);
			if (!path1.exists()) {
				// 若不存在，创建目录，可以在应用启动的时候创建
				path1.mkdirs();
			}
			if (file.exists() == false) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * copy assets sources to SDCard
	 * 
	 * @param assetDir
	 *            root dir ---""
	 * @param dir
	 *            SDCard dir
	 */
	public void CopyAssets(String assetDir, String dir) // by King
	{
		Log.v("CopyAssets", "assetDir:" + assetDir);
		Log.v("CopyAssets", "dir:" + dir);
		String[] files;
		try {
			files = this.getResources().getAssets().list(assetDir);
		} catch (IOException e1) {
			return;
		}
		File mWorkingPath = new File(dir);
		// if this directory does not exists, make one.
		if (!mWorkingPath.exists()) {
			if (!mWorkingPath.mkdirs()) {
				Log.e("sd", "cannot create directory.");
			}
		}
		for (int i = 0; i < files.length; i++) {
			try {
				String fileName = files[i];

				if (fileName.compareTo("images") == 0
						|| fileName.compareTo("sounds") == 0
						|| fileName.compareTo("webkit") == 0) {
					continue;
				}
				// we make sure file name not contains '.' to be a folder.
				if (!fileName.contains(".")) {
					if (0 == assetDir.length()) {
						CopyAssets(fileName, dir + fileName + "/");
					} else {
						CopyAssets(assetDir + "/" + fileName, dir + fileName
								+ "/");
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
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.v("sd", "zzzzzzzzzzzzzzzz");
	}

	/**
	 * 有关sd卡文件的遍历
	 */
	private List<String> mSongs;
	private List<String> mSongspath;

	public void upDataSongs() {

		if (isSDCardReady()) {
			Toast.makeText(this, "没有 sdcard", Toast.LENGTH_SHORT).show();

		} else {
			mSongs = new ArrayList<String>();
			mSongspath = new ArrayList<String>();
			TraversalFile(getSDRootPath());
			// songsDialog();
		}
	}

	public void TraversalFile(String path) {

		String paths = path + "/";
		File file = new File(paths);
		File[] filelist = file.listFiles();

		if (filelist != null) {
			for (int i = 0; i < filelist.length; i++) {
				if (filelist != null) {

					File g = new File(filelist[i].getPath());

					if (g.isDirectory()) {
						TraversalFile(g.getPath());

					} else if (g.getName().endsWith(".mp3")) {
						mSongs.add(g.getName());
						mSongspath.add(g.getPath());
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param newPath
	 *            图片保存路径
	 * @param bitName
	 * @param d
	 * @param percent
	 * @throws IOException
	 */
	public void saveMyBitmap(String newPath, String bitName, Drawable d,
			int percent) throws IOException {

		Bitmap bmp = drawable2Bitmap(d);// 这里的drawable2Bitmap方法是我把ImageView中
										// 的drawable转化成bitmap，当然实验的时候可以自己创建bitmap
		newPath = getSDRootPath() + newPath;
		File f = new File(newPath + bitName + ".jpg");
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bmp.compress(Bitmap.CompressFormat.JPEG, percent, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 附加drawable2Bitmap方法
	static Bitmap drawable2Bitmap(Drawable d) {
		int width = d.getIntrinsicWidth();
		int height = d.getIntrinsicHeight();
		Bitmap.Config config = d.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0, 0, width, height);
		d.draw(canvas);
		return bitmap;
	}
}
