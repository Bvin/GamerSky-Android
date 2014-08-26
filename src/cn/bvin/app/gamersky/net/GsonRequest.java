package cn.bvin.app.gamersky.net;

import java.io.UnsupportedEncodingException;
import cn.bvin.app.gamersky.config.Config;
import cn.bvin.app.gamersky.utils.StringUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T>{

	private final Gson gson = new Gson();
	private final Class<T> clazz;
	private final Listener<T> listener;
	private Object jsonParam;//序列化成json字符串参数
	
	public GsonRequest(int method, String url, Class<T> clazz,Listener<T> listener,ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
        this.listener = listener;
	}


	/**
	 * 
	 * @Title:GsonRequest
	 * @Description:默认POST请求
	 * @param url 请求地址
	 * @param clazz 返回Json类
	 * @param listener
	 * @param errorListener
	 */
	public GsonRequest(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        this(Method.POST,url, clazz,listener,errorListener);
	}


	@Override
	public byte[] getBody() throws AuthFailureError {
		if (getMethod()==Method.POST&&jsonParam!=null) {
			try {
				return gson.toJson(jsonParam).getBytes(Config.DEFAULT_CHATSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return gson.toJson(jsonParam).getBytes();
			}
		}
		return super.getBody();
	}



	
	@Override
	protected void deliverResponse(T arg0) {
		listener.onResponse(arg0);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse arg0) {
		try {
            String json = new String(
            		arg0.data, HttpHeaderParser.parseCharset(arg0.headers));
            return Response.success(
                    gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(arg0));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
	}

	public Object getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(Object jsonParam) {
		this.jsonParam = jsonParam;
	}
	
	/** 获取如GET方式下带参数的url */
	public String getDebugeUrl() {
		if (getMethod()==Method.POST&&jsonParam!=null) {
			return getUrl()+"?"+StringUtils.formatJson2Map(gson.toJson(jsonParam));
		}else {
			return getUrl();
		}
	}
	
}
