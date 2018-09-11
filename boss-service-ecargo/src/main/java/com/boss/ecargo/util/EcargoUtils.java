/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boss.core.db.EcargoInfo;
import com.boss.core.db.SysConfig;
import com.boss.core.pojo.EcargoMsg;
import com.boss.core.pojo.EcargoProjo;
import com.boss.ecargo.service.SysConfigService;
import com.boss.utils.MathDbTools;
import com.boss.utils.enums.DateFormatModel;
import com.boss.utils.exceptions.IdentCardException;
import com.boss.utils.finalclases.AppContents;
import com.boss.utils.httpclient.HttpClientHelper;
import com.boss.utils.httpclient.JsExecuteUtil;
import com.boss.utils.image.ImageHelper;

@Service
public class EcargoUtils {

	
	public static Map<String,Object> result=new HashMap<String,Object>();
	
	/**
	 * 系统参数服务
	 */
	@Autowired
	private  SysConfigService sysConfigService;

	/**
	 * 
	 * @return 返回是否成功
	 */
	public static boolean postPolicy() {

		return true;
	}

	/**
	 * ecargo登录功能
	 * 
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public  boolean loginUser() throws ClientProtocolException, IOException, URISyntaxException {

		String imgCode = "xxyy";

		CookieStore cookieStore = new BasicCookieStore();
		HttpClient client = HttpClientHelper.buildClient(cookieStore);

		String loginBzType = "ECARGO_LOGIN";
		
		String loginUri="http://www.epicc.com.cn/ecargo/login.jsp";
		
		HttpResponse  loginResponse=HttpClientHelper.doGet(client, loginUri);
		
		String charSet="GBK";
		String logHtml=HttpClientHelper.getEntityHtml4Response(loginResponse, charSet);
		String ssid=HttpClientHelper.getValueById(logHtml, "Ssid", charSet);
		
		cookieStore.getCookies().get(0);
		BasicClientCookie cookie=new BasicClientCookie(AppContents.ECARGO_COOKIE_SID,ssid);
		cookieStore.addCookie(cookie);
		cookie.setVersion(0);  
        cookie.setDomain("www.epicc.com.cn");   //设置范围
        cookie.setPath("/");
		
		// 第三方参数
		Map<String, SysConfig> loginPams = sysConfigService.findByBusinessType(loginBzType);
		
		// 需要识别验证码
		if (AppContents.ECARGO_NEED_CHECK_IMG_YES
				.equals(loginPams.get(AppContents.ECARGO_LOGIN_NEED_VALIDE_IMG).getPamCodeValue())) {
			String uri = loginPams.get(AppContents.ECARGO_LOGIN_IMG_CODE_URI).getPamCodeValue();// "http://www.epicc.com.cn/ecargo/kaptcha.jpg";

			CloseableHttpResponse response = (CloseableHttpResponse) HttpClientHelper.doGet(client, uri);
			byte[] result = ImageHelper.getByteArray4Response(response);
			// ImageHelper.writeImgeData2File(result, "d:/work/img.png");

			String imgBase64 = ImageHelper.getBASE64CODE4Img(result);
			/*
			 * String user = "awfidx"; String pass = "52624135aw"; String
			 * codetype = "1004"; String softid = "895809"; String curi =
			 * "http://upload.chaojiying.net/Upload/Processing.php";
			 */
			String codBusisType = "CJY";
			// 第三方参数
			Map<String, SysConfig> cjyPams = sysConfigService.findByBusinessType(codBusisType);

			String user = cjyPams.get(AppContents.CJY_USER).getPamCodeValue();
			String pass = cjyPams.get(AppContents.CJY_PASS).getPamCodeValue();
			String codetype = cjyPams.get(AppContents.CJY_CODE_TYPE).getPamCodeValue();
			String softid = cjyPams.get(AppContents.CJY_SOFT_ID).getPamCodeValue();
			String curi = cjyPams.get(AppContents.CJY_CURI).getPamCodeValue();
			imgCode = ImageHelper.getImageCode(user, pass, codetype, softid, curi, imgBase64);
		}

		// 登录用户名
		String ecargoUserName = loginPams.get(AppContents.ECARGO_LOGIN_USER).getPamCodeValue();// "Hzbskj";
		// 登录密码
		String ecargoPwd = loginPams.get(AppContents.ECARGO_LOGIN_PWD).getPamCodeValue();// "YEF@0115";

		// 获取编码后的用户名，密码，psw
		List<String> dd = new ArrayList<String>();
		dd.add(ecargoUserName);
		dd.add(ecargoPwd);
		// 调用JS方法处理用户名密码参数
		
		
		/*String fileNmae="security.js";
		File fl=new File(fileNmae);
		if(fl.exists())
		{
			fl.createNewFile();
		}
		
		FileWriter fw=new FileWriter(fl);
		
		BufferedReader bufferReader = null;
		InputStream ins = EcargoUtils.class.getResourceAsStream("security.js");
		bufferReader = new BufferedReader(new InputStreamReader(ins));
		String line=null;
		while((line=bufferReader.readLine())!=null)
		{
			fw.write(line);
			fw.write("\n");
		}
		
		bufferReader.close();
		fw.flush();
		fw.close();
		
		String fulPath="/"+fl.getPath();*/
		String fulPath="/security.js";
		String loginMsg = (String)JsExecuteUtil.executeFunction(fulPath, "enceypt", dd);
		String[] msg = loginMsg.split("-");

		/*String loginUri = loginPams.get(AppContents.ECARGO_LOGIN_URI).getPamCodeValue();

		HttpResponse loginResponse = HttpClientHelper.doGet(client, loginUri);

		String charSet = loginPams.get(AppContents.ECARGO_LOGIN_CAHARSET).getPamCodeValue();
		String ssid = HttpClientHelper.getValueById(loginResponse.getEntity().toString(), AppContents.ECARGO_LOGIN_SSID, charSet);
*/
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("imgCode", imgCode);
		// 匹配验证码 不是必须(验证码不匹配好像也可登录)
		HttpResponse checkcodeHtmlResponse = HttpClientHelper.doPost(client,
				"http://www.epicc.com.cn/ecargo/checkImage!checkImage.action", paramsMap, "GBK");
		String checkcodeHtml = HttpClientHelper.getEntityHtml4Response(checkcodeHtmlResponse, charSet);

		//System.out.println(checkcodeHtml);
		if ("imgError" == checkcodeHtml.trim()) {
			System.out.println("验证码错误");
			
		}

		if (msg.length > 2) {
			paramsMap.put("ssid", ssid);
			paramsMap.put("extno", msg[0]);// MUserCode
			paramsMap.put("pwd", msg[1]);// MPassword
			paramsMap.put("psw", msg[2]);
		}

		// 登录
		HttpResponse sucessHtmlResponse = HttpClientHelper.doPost(client, "http://www.epicc.com.cn/ecargo/login.action",
				paramsMap, "GBK");
		String successHtml = HttpClientHelper.getEntityHtml4Response(sucessHtmlResponse, "GBK");
		//System.out.println(successHtml);

		// 判断登录是否成功（不成功则返回登录页面）
		String failureLogin = HttpClientHelper.getTextById(successHtml, "ErrorMessage", "GBK");
		if (failureLogin == null) {
			System.out.println("登录成功");
			List<Cookie> cookies=cookieStore.getCookies();
			//因为没做定时，所以每次重新登录清空cookie
			EcargoUtils.result.clear();
			for(Cookie cok:cookies)
			{
				EcargoUtils.result.put(cok.getName(), cok);
			}
			return true;
		}

		System.out.println("登录失败！" + failureLogin);

		return false;
	}

	/**
	 * ecargo 投保方法
	 * 
	 * @param ecargoInfo
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws IdentCardException 
	 * @throws ParseException 
	 */
	public  EcargoProjo sendEcargoInfo(EcargoInfo ecargoInfo) throws ClientProtocolException, IOException, URISyntaxException, IdentCardException, ParseException {
		// 构造信息返回类
		EcargoMsg ecargoMsg = new EcargoMsg(true, "");
		EcargoProjo epj = new EcargoProjo(ecargoMsg, ecargoInfo);

		this.loginUser();//执行登录功能

		// 构造请求cookie
		CookieStore cs = new BasicCookieStore();

		Collection<Object> rs=EcargoUtils.result.values();
		for(Object o:rs)
		{
			if(o instanceof Cookie)
			{
				cs.addCookie((Cookie)o);
			}
		}

		//构造请求体
		HttpClient initClient=HttpClientHelper.buildClient(cs);
		
		
		
		//HttpClientHelper.doPost(client, uri, requestParams, charSet);
		String busType="ECARGO_INSURE";
		
		Map<String, SysConfig> initParams=sysConfigService.findByBusinessType(busType);
		String saveUri=initParams.get(AppContents.ECARGO_INSURE_SAVE_URI).getPamCodeValue();
		String charSet=initParams.get(AppContents.ECARGO_INSURE_CHARSET).getPamCodeValue();
		String initUri=initParams.get(AppContents.ECARGO_INSURE_INIT_URI).getPamCodeValue();
		
		HttpResponse initResponse=HttpClientHelper.doGet(initClient, initUri);
		String initResult=HttpClientHelper.getEntityHtml4Response(initResponse, charSet);
		//System.out.println(initResult);
		Map<String,String> insurePam=new HashMap<String,String>();
		//投保参数初始化开始《《《《《《《《《《《《《《《《《《
				insurePam.put("holderName", ecargoInfo.getHolderName());//投保人"投保人A");
				insurePam.put("holderAddr", ecargoInfo.getHolderAddr());//投保人地址"投保人A——ADDR");
				insurePam.put("recognizeeAddr", ecargoInfo.getRecognizeeAddr());//被保险人地址"BBADDR");
				
				String cardType="其他";//"身份证";
				/*String cardId=ecargoInfo.getRecognizeeIdenty();//"***0219***40***";
				List<String> checkId = new ArrayList<String>();
				checkId.add(cardId);//证件号码
				checkId.add(cardType);//证件类型
*/				//验证证件 

				/*String fileNmae="IDCard.js";
				File fl=new File(fileNmae);
				if(fl.exists())
				{
					fl.createNewFile();
				}
				
				FileWriter fw=new FileWriter(fl);
				
				BufferedReader bufferReader = null;
				InputStream ins = EcargoUtils.class.getResourceAsStream("IDCard.js");
				bufferReader = new BufferedReader(new InputStreamReader(ins));
				String line=null;
				while((line=bufferReader.readLine())!=null)
				{
					fw.write(line);
					fw.write("\n");
				}
				
				bufferReader.close();
				fw.flush();
				fw.close();
				
				String fulPath="/"+fl.getPath();*/
				//String fulPath="/IDCard.js";
				//Boolean checkResult=(Boolean)JsExecuteUtil.executeFunction(fulPath, "identyCheck", checkId);
				//System.out.println(checkResult);//IdCardExceptionIdCardException
				
				//身份主下验证失败
				/*if(!checkResult)
				{
					throw new BizException(new BizCode(44444, "身份证认证不合法！"));
				}*/
				
				insurePam.put("documentType", cardType);//被保险人证件类型
				insurePam.put("recognizeeIdenty", ecargoInfo.getRecognizeeIdenty());//被保险人证件号码（其他）cardId);//
				insurePam.put("recognizeePhone", ecargoInfo.getRecognizeePhone());//被保险人电话"15902883191");//
				insurePam.put("recognizeeOrg", ecargoInfo.getRecognizeeOrg());//组织机构"");//
				insurePam.put("recognizeeName", ecargoInfo.getRecognizeeName());//被保险人"BB");//
				insurePam.put("invNo", ecargoInfo.getInvoiceNumber());//发票号/运单号"no3303");//
				insurePam.put("goodsName", ecargoInfo.getItemdetail());//货物名称"shuiguo");//
				insurePam.put("weights", ecargoInfo.getWeight());//货物重量"133");//
				String goodsType=ecargoInfo.getType();//"804";//货物类型
				insurePam.put("goodsTypeID",goodsType );//ecargoInfo.getType());//货物类型
				//String pkg=ecargoInfo.getPackages();
				//pkg=null==pkg?"":pkg.trim();
				String pack=ecargoInfo.getPackages();//包装词"箱";//
				insurePam.put("pack", pack);//ecargoInfo.getPackages());//包装词
				insurePam.put("quantity",ecargoInfo.getQuantity()+pack);//货物数量 "311"+"箱");//
				
				
				
				
				insurePam.put("transportTypeID", "5");//运输方式，不可选择，默认公路
				insurePam.put("transport", ecargoInfo.getLicenseNo());//运输工具名称(车牌号)"沪F111（必填）");//
				insurePam.put("transportNo","");//航(班)次/航龄
				insurePam.put("fromLoc", ecargoInfo.getStartsiteName());//起运地"Adi");//
				insurePam.put("viaLoc", ecargoInfo.getViasiteName());//中转地"Bdi");//
				insurePam.put("toLoc",ecargoInfo.getEndsiteName());//目的地 "Cdi");//
				
				Date dt=MathDbTools.convertString2Date(ecargoInfo.getStartTime(), DateFormatModel.md4_24);//("2018-09-08 18:18:18", DateFormatModel.md4_24);//
				
				Date know=new Date();
				
				//如果起运时间小于，也主不是早于投保时间，也就是需要先投保再起运，就把走去时间取当前系统时间再延时5分钟
				if(dt.getTime()<know.getTime())
				{
					dt=	MathDbTools.timePlusAmount(new Date(), 5, Calendar.MINUTE);
				}
				
				Calendar cnd = Calendar.getInstance();
				cnd.setTime(dt);
				//Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
				int year = cnd.get(Calendar.YEAR);//年
				int month = cnd.get(Calendar.MONTH)+1;//月
				int date = cnd.get(Calendar.DATE);//日
				int hour = cnd.get(Calendar.HOUR_OF_DAY);//时
				//int minute = c.get(Calendar.MINUTE);//分
				//int second = c.get(Calendar.SECOND);//秒
				String smonth=month<10?"0"+month:month+"";
				String sdate=date<10?"0"+date:date+"";
				String shour=hour<10?"0"+hour:hour+"";
				
				String qiyunDateStr=year+"-"+smonth+"-"+sdate;
				//System.out.println(qiyunDateStr);
				
				
				insurePam.put("departureDate", qiyunDateStr);//起运时间--日期
				insurePam.put("departureTime", shour);//起运时间--时间
				
				//根据保险类型，判断主险条款
				String itype=ecargoInfo.getInsuranceType();//"1";//
				if(AppContents.ECARGO_INSURE_TYPE_MULTIPLE.equals(itype))
				{
					insurePam.put("glausesID", "5");//主险条款,国内水路、陆路货运综合险
				}
				else if(AppContents.ECARGO_INSURE_TYPE_BASE.equals(itype))
				{
					insurePam.put("glausesID", "6");//主险条款,国内水路、陆路货运基本险
				}
				else
				{
					insurePam.put("glausesID", "5");//主险条款,默认：国内水路、陆路货运综合险
				}
				
				double baoe=Double.parseDouble(ecargoInfo.getSumamount());
				double fielv=Double.parseDouble(ecargoInfo.getEcRatio());//0.13;
				insurePam.put("additive", "");//附加险条款内容,默认空
				insurePam.put("insuredAmount", baoe+"");//ecargoInfo.getSumamount());//保险金额
				insurePam.put("ratio", fielv+"");//ecargoInfo.getRatio()+"");//费率
				insurePam.put("premium", baoe*fielv/1000+"");//ecargoInfo.getPremium());//保费
				insurePam.put("deductible", "");//绝对免赔率
				
				Calendar qcnd = Calendar.getInstance();
				qcnd.setTime(know);
				//Calendar qc = Calendar.getInstance();//可以对每个时间域单独修改
				int qyear = qcnd.get(Calendar.YEAR);//年
				int qmonth = qcnd.get(Calendar.MONTH)+1;//月
				int qdate = qcnd.get(Calendar.DATE);//日
				int qhour = qcnd.get(Calendar.HOUR_OF_DAY);//时
				//int qminute = qcnd.get(Calendar.MINUTE);//分
				//int qsecond = qcnd.get(Calendar.SECOND);//秒
				String qsmonth=qmonth<10?"0"+qmonth:qmonth+"";
				String qsdate=qdate<10?"0"+qdate:qdate+"";
				String qshour=qhour<10?"0"+qhour:qhour+"";
				
				insurePam.put("effDate", qyear+"-"+qsmonth+"-"+qsdate);//签单日期
				
				insurePam.put("effTime", qshour);//签单时间
				//特别约定 
				insurePam.put("remark", "1、单票最高保险金额200万元。每次保险事故绝对免赔额1000元，绝对免赔率（损失金额）10 %，两者以高者为准。火灾加扣免赔率10%。玻璃等易碎品绝对免赔额5000元或免赔率20%，两者以高者为准。2、每次保险事故保险人承担的最高赔偿限额为200万元；本投保单确定的保险期限内，保险人承担的累计最高赔偿限额为400万元。3、有以下情形的，出险后损失由被保险人承担：（1）承运车辆驾驶员无有效驾驶证；（2）承运车辆（含牵引车和挂车）无有效的行驶证或号牌，或未按规定检验或检验不合格的。4、易碎品仅承担火灾爆炸、交通意外事故。5、不承担盗窃或提货不着造成的损失。");
				insurePam.put("endTypeID", "40");//结算方式,默认：逐单结
				insurePam.put("postalModeId", "60");//保单投递方式，默认：自取
				insurePam.put("invHead", "");//发票抬头
				insurePam.put("extUsrNo", "");//自定义查询编码
				insurePam.put("shipCName", "");//船名(中名)
				insurePam.put("shipEName", "");//船名(英名)
				insurePam.put("fleetNo", "");//船队编号
				insurePam.put("itemNo", "0");//标的序号
				insurePam.put("stepHull", "无船级");//船级，默认：无船级
				insurePam.put("shipFlag", "");//船旗，默认：空
				insurePam.put("associate", "");//保赔协会
				
				String makeYearMonth=HttpClientHelper.getValueById(initResult, "MakeYearMonth", charSet);
				
				insurePam.put("makeYearMonth", makeYearMonth);//建造年
				insurePam.put("countryCode", "");//国家代号
				insurePam.put("makeFactory", "");//制造厂家
				insurePam.put("templateDesc", "");//请对模版进行描述
				insurePam.put("consigneeInfo", "");//收件人信息
				insurePam.put("neijian", "");//内件
				insurePam.put("contactPerson", "");//投递收件人
				insurePam.put("contactTel", "");//联系电话
				insurePam.put("postalCode", "");//邮编
				insurePam.put("postalAddr", "");//投递地址
				
				String glaId=insurePam.get("glausesID");
				if("5".equals(glaId))
				{
					insurePam.put("glauses", "国内水路、陆路货运综合险");//主险条款文字
					
				}
				else if("6".equals(glaId))
				{
					insurePam.put("glauses", "国内水路、陆路货运基本险");//主险条款文字
				}
				
				insurePam.put("packQty",ecargoInfo.getWeight());//货物重量 "133");//
				
				String way=ecargoInfo.getDisputeFunction();//"仲裁委员会";//解决争议方式，
				
				insurePam.put("way", way);//解决争议方式
				
				insurePam.put("ifPackage", pack);//包装
				insurePam.put("contractPerson", "");//空无意义 
				insurePam.put("contractTel", "");//空无意义 
				insurePam.put("postalCode", "");//邮编
				insurePam.put("postalAddr", "");//投递地址
				//重复insurePam.put("ratio", "");//费率 -------------与之前冲途，可能有问题
				insurePam.put("additiveNo", "");
				insurePam.put("invRefNo", "");
				insurePam.put("policyNo", "");
				insurePam.put("policyNoLong", "");//投保人
				//重复insurePam.put("insuranceID", "");
				insurePam.put("endCurrencyID", "1");//币种，人民币
				insurePam.put("currencyID", "1");//币种，人民币
				insurePam.put("policyNoRemark", "");//备注信息
				insurePam.put("HChangeNo", "");
				insurePam.put("extUsrNo", "");//自定义查询编码
				insurePam.put("mianID", "");
				insurePam.put("mianDesc", "");
				insurePam.put("contractNo", "");
				insurePam.put("insuranceID", "1");//投保币种
				insurePam.put("inDelayApplay", "");
				
				String agreenumStartDate=HttpClientHelper.getValueById(initResult, "agreenumStartDate", charSet);
				insurePam.put("agreenumStartDate", agreenumStartDate);//协议日期，从initHTML中获取
				
				String policyNoHead=HttpClientHelper.getValueById(initResult, "policyNoHead", charSet);
				
				insurePam.put("policyNoHead", policyNoHead);//投保单号头
				insurePam.put("myfileFileName", "");//投保人
				
				
				//投保参数初始化结束《《《《《《《《《《《《《《《《《《
				//String saveURI="http://www.epicc.com.cn/ecargo/insure!saveInInsure.action";
				HttpClient saveClinet = HttpClientHelper.buildClient(cs);
				HttpResponse saveResponse=HttpClientHelper.doPost(saveClinet, saveUri, insurePam, charSet);
				String saveResult=HttpClientHelper.getEntityHtml4Response(saveResponse, charSet);
				System.out.println(saveResult);
				
				//获取保单 查询连接
				Element a=HttpClientHelper.getElement4Html(saveResult, "a", charSet, 0);
				String auri=a.attr("href");
				
				//用超链接去查询保单信息
				HttpClient aClinet = HttpClientHelper.buildClient(cs);
				HttpResponse aResponse=HttpClientHelper.doGet(aClinet,  auri);
				String aResult=HttpClientHelper.getEntityHtml4Response(aResponse, charSet);
				System.out.println(aResult);
				
				//获取保单信息右则DIV
				String rightId="right";
				Element rightDiv=HttpClientHelper.getElementById(aResult, rightId, charSet);
				Elements tds=HttpClientHelper.getElements4Html(rightDiv.outerHtml(), "td", charSet);
				
				Element status=tds.get(4);//投保状态
				Element appno=tds.get(6);//投保单号
				//投保单状态
				String applyStatus=status.ownText().trim();
				//投保单号码
				String applyPolicyNo=appno.ownText().trim();
				//保单号码
				String policyNo=null;
				//如果为已生效，就是投保自动核保通过，有保单号，就需要设置保单号
				if("已生效".equals(applyStatus))
				{
					Element pono=tds.get(8);//投保单号
					policyNo=pono.ownText().trim();
				}
				
			if(null!=applyPolicyNo || !"".equals(applyPolicyNo))
			{
				
				ecargoInfo.setApplyPolicyNo(applyPolicyNo);
				ecargoInfo.setPolicyNo(policyNo);
				ecargoInfo.setApplyStatus(applyStatus);
				
				ecargoMsg.setApplyNo(applyPolicyNo);
				ecargoMsg.setApplyStatus(applyStatus);
				ecargoMsg.setPolicyNo(policyNo);
				ecargoMsg.setErrMsg("投保成功！投保单编号为："+applyPolicyNo +"，保单编号为："+policyNo+"，投保单状态为："+applyStatus);
				
			}
			else
			{
				ecargoMsg.setErrMsg("投保失败！ecargo投保接口有问题！");
				ecargoMsg.setResult(false);
			}
			
		
		return epj;

	}
}
