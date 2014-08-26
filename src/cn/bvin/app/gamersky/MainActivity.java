package cn.bvin.app.gamersky;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import cn.bvin.app.gamersky.manager.RequestManager;
import cn.bvin.app.gamersky.models.PageCursor;
import cn.bvin.app.gamersky.models.TopArticles;
import cn.bvin.app.gamersky.net.GsonRequest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private String url;
	private PageCursor params;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		doPost();
	}
	
	private void request() {
		url = "appapi.gamersky.com"+"/v1/articletop";
		url = "http://www.bejson.com/";
		StringRequest s =new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				
			}}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	private void doPost() {
		url = "http://appapi.gamersky.com"+"/v1/articletop";
		params = new PageCursor(String.valueOf(1), String.valueOf(20));
		ErrorListener errorListener =  new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.e("TopArticles-onErrorResponse",arg0.getLocalizedMessage());
			}};
		Listener<TopArticles> listener = new Listener<TopArticles>() {

			@Override
			public void onResponse(TopArticles arg0) {
				Log.e("TopArticles-onResponse", arg0.result.length+"条");
			}};
		GsonRequest<TopArticles> request = new GsonRequest<TopArticles>(url, TopArticles.class,listener ,errorListener);
		request.setJsonParam(params);
		RequestManager.addRequest(request, request.getDebugeUrl());
	}
}
