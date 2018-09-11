package com.boss.utils.image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.boss.utils.httpclient.HttpClientHelper;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class ImageHelper {

	public static String getBASE64CODE4Img(String imgPath)
	{
		InputStream in = null;  
        byte[] data = null;  
        // 读取图片字节数组  
        try {  
            in = new FileInputStream(imgPath);  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        // 对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        // 返回Base64编码过的字节数组字符串  
        return encoder.encode(data);
	}

	/**
	 * 把二进制的图片数据转换成转换 成BASE64字符串
	 * 
	 * @param data
	 *            byte[]类型的图片数据
	 * @return
	 */
	public static String getBASE64CODE4Img(byte[] data) {
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		// 返回Base64编码过的字节数组字符串
		return encoder.encode(data);
	}

	public static boolean generateImage4BASE64Code(String imgStr, String fileFullPath) {
		if (imgStr == null) // 图像数据为空
		{
			throw new NullPointerException("图片字符为空");
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(fileFullPath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 字符串MD5加密
	 * 
	 * @param s
	 *            原始字符串
	 * @return 加密后字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通用POST方法
	 * 
	 * @param url
	 *            请求URL
	 * @param param
	 *            请求参数，如：username=test&password=1
	 * @return response
	 * @throws IOException
	 */
	public static String httpRequestData(String url, String param) throws IOException {
		URL u;
		HttpURLConnection con = null;
		OutputStreamWriter osw;
		StringBuffer buffer = new StringBuffer();

		u = new URL(url);
		con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(param);
		osw.flush();
		osw.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * 核心上传函数
	 * 
	 * @param param
	 *            请求参数，如：username=test&password=1
	 * @param data
	 *            图片二进制流
	 * @return response
	 * @throws IOException
	 */
	public static String httpPostImage(String param, byte[] data) throws IOException {
		long time = (new Date()).getTime();
		URL u = null;
		HttpURLConnection con = null;
		String boundary = "----------" + MD5(String.valueOf(time));
		String boundarybytesString = "\r\n--" + boundary + "\r\n";
		OutputStream out = null;

		u = new URL("http://upload.net/Upload/Processing.php");

		con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		// con.setReadTimeout(60000);
		con.setConnectTimeout(60000);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setUseCaches(true);
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		out = con.getOutputStream();

		for (String paramValue : param.split("[&]")) {
			out.write(boundarybytesString.getBytes("UTF-8"));
			String paramString = "Content-Disposition: form-data; name=\"" + paramValue.split("[=]")[0] + "\"\r\n\r\n"
					+ paramValue.split("[=]")[1];
			out.write(paramString.getBytes("UTF-8"));
		}
		out.write(boundarybytesString.getBytes("UTF-8"));

		String paramString = "Content-Disposition: form-data; name=\"userfile\"; filename=\"" + "chaojiying_java.gif"
				+ "\"\r\nContent-Type: application/octet-stream\r\n\r\n";
		out.write(paramString.getBytes("UTF-8"));

		out.write(data);

		String tailer = "\r\n--" + boundary + "--\r\n";
		out.write(tailer.getBytes("UTF-8"));

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null) {
			buffer.append(temp);
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * 识别图片_按图片文件路径
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param softid
	 *            软件ID
	 * @param codetype
	 *            图片类型
	 * @param len_min
	 *            最小位数
	 * @param filePath
	 *            图片文件路径
	 * @return
	 * @throws IOException
	 */
	public static String postPic(String username, String password, String softid, String codetype, String len_min,
			String filePath) {
		String result = "";
		String param = String.format("user=%s&pass=%s&softid=%s&codetype=%s&len_min=%s", username, password, softid,
				codetype, len_min);
		try {
			File f = new File(filePath);
			if (null != f) {
				int size = (int) f.length();
				byte[] data = new byte[size];
				FileInputStream fis = new FileInputStream(f);
				fis.read(data, 0, size);
				if (null != fis)
					fis.close();

				if (data.length > 0)
					result = httpPostImage(param, data);
			}
		} catch (Exception e) {
			result = "未知问题";
		}

		return result;
	}

	/**
	 * 识别图片_按图片二进制流
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param softid
	 *            软件ID
	 * @param codetype
	 *            图片类型
	 * @param len_min
	 *            最小位数
	 * @param byteArr
	 *            图片二进制数据流
	 * @return
	 * @throws IOException
	 */
	public static String postPic(String username, String password, String softid, String codetype, String len_min,
			byte[] byteArr) {
		String result = "";
		String param = String.format("user=%s&pass=%s&softid=%s&codetype=%s&len_min=%s", username, password, softid,
				codetype, len_min);
		try {
			result = httpPostImage(param, byteArr);
		} catch (Exception e) {
			result = "未知问题";
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取tyte数组，从response中获取
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public static byte[] getByteArray4Response(HttpResponse response) throws IOException   {
		HttpEntity entity = response.getEntity();
		byte[] tmp = EntityUtils.toByteArray(entity);
		return tmp;

	}

	
	
	
	
	/**
	 * url="http://upload.chaojiying.net/Upload/Processing.php";
	 * @param user 用户名。默认：awfidx
	 * @param pass 密码。默认：52624135aw
	 * @param codetype 图片验证码类型。默认：1004
	 * @param softid 图片验证码类型。默认：895809
	 * @param uri 解码地址URI。默认：http://upload.chaojiying.net/Upload/Processing.php
	 * @param imgBase64 调用ImageHelper.getBASE64CODE4Img获取图片的64
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws Exception
	 */
	public static String getImageCode(String user,String pass,String codetype,String softid,String uri,String imgBase64) throws ClientProtocolException, IOException, URISyntaxException  {
		//String url = "http://upload.chaojiying.net/Upload/Processing.php";// "http://5610086.cn/pcm/loginAction!login.action";
		HttpClient client = HttpClientHelper.buildClient();

		Map<String, String> pams = new HashMap<String, String>();
		pams.put("user", user);
		pams.put("pass", pass);
		pams.put("codetype", codetype);
		pams.put("softid", softid);
		pams.put("file_base64", imgBase64);
		/*pams.put("user", "awfidx");
		pams.put("pass", "52624135aw");
		pams.put("codetype", "1004");
		pams.put("softid", "895809");
		pams.put("file_base64", imgBase64);*/

		CloseableHttpResponse response = (CloseableHttpResponse) HttpClientHelper.doPost(client,uri,pams, "UTF-8");

		
		InputStream ins = response.getEntity().getContent();
		BufferedReader bis = new BufferedReader(new InputStreamReader(ins));
		// Byte[] bt=new Byte[2048];
		StringBuffer sbf = new StringBuffer();
		String line = null;
		while ((line = bis.readLine()) != null) {
			sbf.append(line);
		}

		System.out.println(sbf.toString());
		String res = sbf.toString();
		JSONObject js = JSONObject.fromObject(res);
		return js.get("pic_str").toString();

	}
	

	/**
	 * 把图片数据写入指定文件
	 * @param result 图片二进制数据
	 * @param fileFullPath 存储路径
	 * @throws IOException
	 */
	public static void writeImgeData2File(byte[] result,String fileFullPath) throws IOException
	{
		File file=new File(fileFullPath);
		FileOutputStream fo=new FileOutputStream(file);
		fo.write(result);
		fo.close();
	}

}
