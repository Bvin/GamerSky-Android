package cn.bvin.app.gamersky.manager;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import cn.bvin.app.gamersky.AppContext;
import cn.bvin.app.gamersky.net.OkHttpStack;
import cn.bvin.app.gamersky.utils.DeviceUtils;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.NoCache;

public class RequestManager {

    //最大SD卡缓存大小
    public static final int MAX_DISC_CACHE_SIZE =  10 * 1024 * 1024;
  
	public static RequestQueue mRequestQueue = newRequestQueue();
	
	private RequestManager() {
	}
	
	private static RequestQueue newRequestQueue() {
		RequestQueue requestQueue ;
		Network network = new BasicNetwork(getStack());
		requestQueue = new RequestQueue(getCache(), network);
		requestQueue.start();
		return requestQueue;
	}

	public static void addRequest(Request request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		mRequestQueue.add(request);
	}
	
	public static void clearRequests(Object tag){
		mRequestQueue.cancelAll(tag);
	}
	
	
	/**
	 * Gets the stack.
	 * 
	 * @return the stack
	 */
	private static HttpStack getStack(){
		HttpStack stack = new OkHttpStack();
		return stack;
	}
	
	/**
	 * Gets the cache.
	 * 获取缓存，有SD卡就用DiskBasedCache缓存，没有不缓存
	 * @param context the context
	 * @return the cache
	 */
	private static Cache getCache(){
		Cache cache = new NoCache();
		if(DeviceUtils.isSDCardExist(AppContext.getInstance().getApplicationContext())){//存在SD卡
			File cacheFile = getExternalCacheDir(AppContext.getInstance().getApplicationContext());
			if(cacheFile!=null){//SD可以创建文件
				if(DeviceUtils.getSDFreeSize()>MAX_DISC_CACHE_SIZE){
					cache = new DiskBasedCache(cacheFile,MAX_DISC_CACHE_SIZE);
				}
			}
		}
		return cache;
	}
	
	public static File getExternalCacheDir(final Context context) {

      // Before Froyo we need to construct the external cache dir ourselves
      final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
      File cacheFile = null;
      cacheFile = new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
      return cacheFile;
  }
	
}
