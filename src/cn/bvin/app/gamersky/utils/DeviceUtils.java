package cn.bvin.app.gamersky.utils;

import cn.bvin.app.gamersky.AppContext;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public class DeviceUtils {

	/**
	 * Gets the screen size.
	 * 返回一个宽高数组
	 * @param ctx the ctx
	 * @return the screen size
	 */
	public static int[] getScreenSize(Context ctx){
		int[] size = new int[2];
		DisplayMetrics  dm = ctx.getResources().getDisplayMetrics();
		size[0] = dm.widthPixels;
		size[1] = dm.heightPixels;
		return size;
	}
	
	/**
	 * 检查是否存在sd卡
	 * @param mContext
	 * @return
	 */
	public static boolean isSDCardExist(Context mContext){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  //判断是否存在SD卡
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断SD卡还有多少空间
	 */
	public static long getSDFreeSize() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		long availableBlocks=stat.getAvailableBlocks();//可用存储块数量
		long size=stat.getBlockSize();//存储块大小：字节为单位
		long availableSize=availableBlocks*size;///可用大小
		return availableSize;
	}
	
	public static String getDeviceIMEI(){
      TelephonyManager tm = (TelephonyManager) AppContext.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
      if (!TextUtils.isEmpty(tm.getDeviceId())) {
          return tm.getDeviceId();
      }
      return "此设备无IMEI";
  }
}
