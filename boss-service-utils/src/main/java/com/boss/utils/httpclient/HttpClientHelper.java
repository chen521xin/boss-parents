package com.boss.utils.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;






public class HttpClientHelper {

	public static HttpClient buildClient() {
		return HttpClientBuilder.create().build();
	}
	
	public static HttpClient buildClient(CookieStore cookieStore) {
		return HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
	}

	
	public static HttpResponse doGet(HttpClient client, String uri) throws ClientProtocolException, IOException {

		if (null == uri || "".equals(uri)) {
			throw new NullPointerException("uri地址为空");
		}
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = client.execute(httpGet);
		return response;
	}

	public static HttpResponse doGet(HttpClient client, URI uri) throws ClientProtocolException, IOException {

		if (null == uri) {
			throw new NullPointerException("uri地址为空");
		}
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = client.execute(httpGet);
		return response;
	}
	

	
	
	/**
	 * 
	 * @param client 一个org.apache.http.client.HttpClient类型参数，可包含 cookie信息
	 * @param uri 一个String类型的请求地址
	 * @param requestParams 一个Map<String, String>类型的请求参数
	 * @param charSet 一个 String类型参数，如果为null则默认为：UTF-8
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static HttpResponse doPost(HttpClient client, String uri, Map<String, String> requestParams,  String charSet)
			throws ClientProtocolException, IOException, URISyntaxException {
		UrlEncodedFormEntity entity = null;

		if (null == uri || "".equals(uri)) {
			throw new NullPointerException("uri地址为空");
		}

		// 判断是否为空，为空则默认为Consts.UTF_8
		charSet=charSet==null||"".equals(charSet.trim())?"utf-8":charSet;

		// 配置请求参数
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<String> keys = requestParams.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			formparams.add(new BasicNameValuePair(key, requestParams.get(key)));
		}

		// 请求体
		entity = new UrlEncodedFormEntity(formparams, charSet);

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		return response;
	}

	/**
	 * 发送一个post请求，并返回一个 CloseableHttpResponse 对象
	 * 
	 * @param uri
	 *            一个URI对象，包含了请求地址和请求的hearder中的信息,如果为空就会调用this.uri如是this.
	 *            uri仍为空则抛NullPointException
	 * @param requestParams
	 *            一个map类型的参数列表
	 * @param charset
	 *            一个 java.nio.charset.Charset类型参数，如果为null则默认为：Consts.UTF_8
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse doPost(HttpClient client, URI uri, Map<String, String> requestParams, String charSet)
			throws ClientProtocolException, IOException {
		if (uri == null) {
			throw new NullPointerException("请求地址不能为空");
		}

		UrlEncodedFormEntity entity = null;

		// 判断是否为空，为空则默认为Consts.UTF-8
		charSet=charSet==null||"".equals(charSet)?"utf-8":charSet;

		// 配置请求参数
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<String> keys = requestParams.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			formparams.add(new BasicNameValuePair(key, requestParams.get(key)));
		}

		// 请求体
		entity = new UrlEncodedFormEntity(formparams, charSet);

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		return response;
	}

	/**
	 * 根据id从html获取值，若id不存在 则返回null;charset:编码;返回id对应的值
	 * 
	 * @param html
	 * @param charset
	 * @return
	 */
	public static String getValueById(String html, String id, String charset) {
		String value = null;
		Document doc = Jsoup.parse(html, charset);
		Element content = doc.getElementById(id);
		if (null != content) {
			value = content.val();
		}
		return value;
	}

	/**
	 * 根据id从html获取Text，若id不存在 则返回null;charset:编码;返回id对应的值
	 * 
	 * @param html
	 * @param charset
	 * @return
	 */
	public static String getTextById(String html, String id, String charset) {
		String value = null;
		Document doc = Jsoup.parse(html, charset);
		Element content = doc.getElementById(id);
		if (null != content) {
			value = content.text();
		}
		return value;
	}

	/***
	 * 根据ID获取元素
	 * @param html
	 * @param id
	 * @param charset
	 * @return
	 */
	public static Element getElementById(String html, String id, String charset) {
		Document doc = Jsoup.parse(html, charset);
		Element elm = doc.getElementById(id);
		return elm;
	}
	
	
	/**
	 * 根据TAG和索引获取元素
	 * @param html 
	 * @param tagName
	 * @param charset
	 * @return
	 */
	public static Element getElement4Html(String html, String tagName, String charset,int index) {
		Document doc = Jsoup.parse(html, charset);
		Elements elms = doc.getElementsByTag(tagName);
		return elms.get(index);
	}
	
	/**
	 * 根据TAG和索引获取元素
	 * @param html 
	 * @param tagName
	 * @param charset
	 * @return
	 */
	public static Elements getElements4Html(String html, String tagName, String charset) {
		Document doc = Jsoup.parse(html, charset);
		Elements elms = doc.getElementsByTag(tagName);
		return elms;
	}
	
	
	
	/**
	 * 从response对象中读取图片内容，写到指定文件中
	 * 
	 * @param response
	 * @param imgFullPath
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static void writeImageFileFromResponse(byte[] imgByteArray, String imgFullPath)
			throws UnsupportedOperationException, IOException {
		File fo = new File(imgFullPath);
		FileOutputStream fout = new FileOutputStream(fo);
		fout.write(imgByteArray);
		fout.close();
	}
	
	/**
	 * 返回html
	 * @param response 
	 * @param charSet 如果为null则为utf-8
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getEntityHtml4Response(HttpResponse response,String charSet) throws ParseException, IOException
	{
		charSet=charSet==null||"".equals(charSet)?"utf-8":charSet;
		return EntityUtils.toString(response.getEntity(), charSet);
	}

}
