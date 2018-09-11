/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.util;




import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.boss.core.db.Details;
import com.boss.utils.DateFormatUtils;
import com.boss.utils.enums.DateFormatModel;


/**
 * xml工具，com.models.InsurancePolicy对象转org.w3c.dom.Document
 * @author Administrator
 *
 */
public class XmlTools 
{
	
	
	/**
	 * 
	 * @param policy 需要输出的policy com.models.InsurancePolicy对象
	 * @return org.w3c.dom.Document
	 * @throws ParserConfigurationException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws UnsupportedEncodingException 
	 */
	public static Document objectToXmlDocument(Details details) throws ParserConfigurationException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.setXmlVersion("1.0");
        document.setXmlStandalone(true);
        
      
        Element root = document.createElement("Packet");//创建一级根节点
        document.appendChild(root);

        Element head = document.createElement("Head");
        Element body = document.createElement("Body");
        root.appendChild(head);
        root.appendChild(body);
        
        head.setTextContent("picc.stif.cargo.tb");
        
        
        Element CargoDetail = document.createElement("CargoDetail");
        Element WtDetail = document.createElement("WtDetail");
        Element CyDetail = document.createElement("CyDetail");
        Element MainInfo = document.createElement("MainInfo");
        
        body.appendChild(CargoDetail);
        body.appendChild(WtDetail);
        body.appendChild(CyDetail);
        body.appendChild(MainInfo);
        
        //CargoDetail 发货记录信息 start
        Element MainID = document.createElement("MainID");
        MainID.setTextContent(details.getTransDealNo());
        
        Element Itemdetail = document.createElement("Itemdetail");
        Itemdetail.setTextContent(details.getGoodsName());
      
        Element Quantity = document.createElement("Quantity");
        Quantity.setTextContent(details.getGoodsTotalNum());
        
        Element Weight = document.createElement("Weight");
        Weight.setTextContent(details.getGoodsWeightNum());
        
        Element Spec = document.createElement("Spec");
        //Spec.setTextContent(details.get);//空
        
        Element Conveyance = document.createElement("Conveyance");
        Conveyance.setTextContent("国内公路");
        
        Element CarrybillNo = document.createElement("CarrybillNo");
        //CarrybillNo.setTextContent("国内公路");
        
        Element StartTime = document.createElement("StartTime");
        StartTime.setTextContent(MathDbTools.convertDate2String(DateFormatUtils.DateFormat(details.getStartDate()), DateFormatModel.md4_24));
        
        
        Element StartsiteName = document.createElement("StartsiteName");
        StartsiteName.setTextContent(details.getFazhan());
        
        Element ViasiteName = document.createElement("ViasiteName");
        
        Element EndsiteName = document.createElement("EndsiteName");
        EndsiteName.setTextContent(details.getGoodsTo());
        
        
        Element Currency = document.createElement("Currency");
        Currency.setTextContent("CNY");
        
        Element Sumamount = document.createElement("Sumamount");
        Double be=Double.valueOf(details.getSelectMoney())*10000;
        Sumamount.setTextContent(MathDbTools.convertToDouble(be, 2, BigDecimal.ROUND_HALF_UP).doubleValue()+"");
        
        
        Element Rate = document.createElement("Rate");
        String xh=details.getRbXishu();
        Double xishu=xh==null?0:"".equals(xh)?0:Double.parseDouble(xh);
        Rate.setTextContent(MathDbTools.convertToDouble(xishu, 2, BigDecimal.ROUND_HALF_UP).doubleValue()+"");
        
        
        Element Sumpremium = document.createElement("Sumpremium");
        double bf=xishu/10000*be;
        Sumpremium.setTextContent(MathDbTools.convertToDouble(bf, 2, BigDecimal.ROUND_HALF_UP).doubleValue()+"");
          
        
        Element BuyTime = document.createElement("BuyTime");
        BuyTime.setTextContent(MathDbTools.convertDate2String(DateFormatUtils.DateFormat(details.getTdate()), DateFormatModel.md4_24));
        
        
        CargoDetail.appendChild(MainID);
        CargoDetail.appendChild(Itemdetail);
        CargoDetail.appendChild(Quantity);
        CargoDetail.appendChild(Weight);
        CargoDetail.appendChild(Spec);
        CargoDetail.appendChild(Conveyance);
        CargoDetail.appendChild(CarrybillNo);
        CargoDetail.appendChild(StartTime);
        CargoDetail.appendChild(StartsiteName);
        CargoDetail.appendChild(ViasiteName);
        CargoDetail.appendChild(EndsiteName);
        CargoDetail.appendChild(Currency);
        CargoDetail.appendChild(Sumamount);
        CargoDetail.appendChild(Rate);
        CargoDetail.appendChild(Sumpremium);
        CargoDetail.appendChild(BuyTime);
        //CargoDetail 发货记录信息 end
        
        //委托方信息（WtDetail） start
        Element AppwtID = document.createElement("AppwtID");
        AppwtID.setTextContent(details.getGoodsOwner());
        
        Element AppwtName = document.createElement("AppwtName");
        AppwtName.setTextContent(details.getGoodsOwner());
        
        Element AppwtTel = document.createElement("AppwtTel");
        
        Element AppwtPhone = document.createElement("AppwtPhone");
        AppwtPhone.setTextContent(details.getGoodsToMobile());
        
        Element AppwtAddr = document.createElement("AppwtAddr");
        AppwtAddr.setTextContent("无地址无地址");
        
        WtDetail.appendChild(AppwtID);
        WtDetail.appendChild(AppwtName);
        WtDetail.appendChild(AppwtTel);
        WtDetail.appendChild(AppwtPhone);
        WtDetail.appendChild(AppwtAddr);
        //委托方信息（WtDetail） end
        
        //承运方信息（CyDetail） start
        Element AppcyID = document.createElement("AppcyID");
        AppcyID.setTextContent(details.getCustName());
        
        Element AppcyName = document.createElement("AppcyName");
        AppcyName.setTextContent(details.getCustName());
        
        Element LicenseNo = document.createElement("LicenseNo");
        LicenseNo.setTextContent(details.getVehicleNo()+" 挂 "+details.getVehicleType());
        
        Element VinNo = document.createElement("VinNo");
        
        Element EngineNo = document.createElement("EngineNo");
        
        Element BrandName = document.createElement("BrandName");
        
        Element Owner = document.createElement("Owner");
        Owner.setTextContent("无数据无数据");//(details.getDriver());
        
        Element DriverName = document.createElement("DriverName");
        DriverName.setTextContent(details.getDriver());
        
        Element DriverID = document.createElement("DriverID");
        DriverID.setTextContent(details.getDriverLicense());
        
        Element AppcyTel = document.createElement("AppcyTel");
        
        Element AppcyPhone = document.createElement("AppcyPhone");
        AppcyPhone.setTextContent("13911112222");
        
        Element AppcyAddr = document.createElement("AppcyAddr");
        
        CyDetail.appendChild(AppcyID);
        CyDetail.appendChild(AppcyName);
        CyDetail.appendChild(LicenseNo);
        CyDetail.appendChild(VinNo);
        CyDetail.appendChild(EngineNo);
        CyDetail.appendChild(BrandName);
        CyDetail.appendChild(Owner);
        CyDetail.appendChild(DriverName);
        CyDetail.appendChild(DriverID);
        CyDetail.appendChild(AppcyTel);
        CyDetail.appendChild(AppcyPhone);
        CyDetail.appendChild(AppcyAddr);
        //承运方信息（CyDetail） end
        
        //校验信息（MainInfo） start
        String md5Msg=MainID.getTextContent()+AppwtID.getTextContent()+AppcyID.getTextContent()+"PICCTEST1234";
        Element Md5Value = document.createElement("Md5Value");
        String md5Code=MD5Utils.getMd5Value(md5Msg,null);
        Md5Value.setTextContent(md5Code);
        
        MainInfo.appendChild(Md5Value);
        
        return document;
	}
	
	/**
	 * 根据XML的dom对象，获取指定tag名下的指定索引的node的textcontent
	 * @param dom xmldom对象
	 * @param tagName 要获取的tag名称
	 * @param index 索引
	 * @return
	 */
	public static String getTextContentByTagAndIndex(Document dom,String tagName,int index)
	{
		String testContent="";
		NodeList  nodList=dom.getElementsByTagName(tagName);
		if(nodList.getLength()!=0)
		{
			testContent=nodList.item(index).getTextContent();
		}
		return testContent;
	}
	
	/**
	 * 根据XML的dom对象，获取指定tag名下的指定索引的node并设置其textcontent
	 * @param dom 要设置的dom对象
	 * @param tagName 要设置的tag名称
	 * @param tagTextContent 要设置的tag的text
	 * @param index 要设置的Tag的索引
	 */
	public static void setTextContentByTagAndIndex(Document dom,String tagName,String tagTextContent,int index)
	{
		NodeList  nodList=dom.getElementsByTagName(tagName);
		if(nodList.getLength()!=0)
		{
			nodList.item(index).setTextContent(tagTextContent);
		}
	}
	
	
	
	/**
	 * XML的Document对象输出到.xml文件
	 * @param xmlDocument 需要输出的xml对象
	 * @param encoding 输出字符集，即：gbk、ISO8859、GB2312等
	 * @param fileFullName 文件全路径，包括文件名称。如：d:/test.xml
	 * @throws TransformerException
	 */
	public static void outputXmlToFile(Document xmlDocument,String encoding,String fileFullName) throws TransformerException
	{
		//输出源
		DOMSource ds=new DOMSource(xmlDocument);
        
        TransformerFactory tsf=TransformerFactory.newInstance();
        Transformer ts= tsf.newTransformer();
        //输出字符集
        ts.setOutputProperty("encoding",encoding);
        
        //输出类
        StreamResult sr=new StreamResult(new File(fileFullName));
        
        ts.transform(ds,sr);
	}
	
	
	
	/**
	 * 发送xml数据和接收xml数据，并把接收的xml转换成字符串
	 * @param urlStr 发送地址
	 * @param xmlInputStreamReader 要发送的xml的java.io.InputStreamReader，对象，可用toStringFromXmlDoc方法
	 * @return 返回xml报文
	 * @throws IOException
	 */
	public static String sendAndReceive(String urlStr,InputStreamReader xmlInputStreamReader) throws IOException
	{
		//"http://localhost:9001/ecargo/xmlDeal! md5InterfaceAction.action"
		URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        BufferedReader b = new BufferedReader(xmlInputStreamReader);
        
        //connection.getOutputStream()会隐藏式的提交
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                connection.getOutputStream()));
        String strTmp = "";
        while ((strTmp = b.readLine()) != null) {
            bw.write(strTmp);
            bw.write("\r\n");
        }
        //提交完成
        bw.flush();
        bw.close(); 

        
        //此处接收
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbData = new StringBuffer(45);
        strTmp = "";
        while ((strTmp = br.readLine()) != null) {
            sbData.append(strTmp);
            sbData.append("\r\n");
        }
        br.close();
        
        return sbData.toString();
	}
	
	/**
	 * 发送xml数据和接收xml数据，并把接收的xml转换成字符串
	 * @param urlStr 发送地址
	 * @param xmlInputStreamReader 要发送的xml的java.io.InputStreamReader，对象，可用toStringFromXmlDoc方法
	 * @return 返回xml报文
	 * @throws IOException
	 */
	public static String sendAndReceiveSSL(String urlStr,InputStreamReader xmlInputStreamReader,String charSet) throws IOException
	{
		//"http://localhost:9001/ecargo/xmlDeal! md5InterfaceAction.action"
		URL url = new URL(urlStr);
		
		try {  
	        SslUtils.ignoreSsl();  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } 
		
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        BufferedReader b = new BufferedReader(xmlInputStreamReader);
        
        //connection.getOutputStream()会隐藏式的提交
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                connection.getOutputStream(),charSet));
        String strTmp = "";
        while ((strTmp = b.readLine()) != null) {
            bw.write(strTmp);
            bw.write("\r\n");
        }
        //提交完成
        bw.flush();
        bw.close(); 

        
        //此处接收
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbData = new StringBuffer(45);
        strTmp = "";
        while ((strTmp = br.readLine()) != null) {
            sbData.append(strTmp);
            sbData.append("\r\n");
        }
        br.close();
        return sbData.toString();
	}
	
	/**
	 * 
	 * @param xmlStr 把xml的字符串转换成document
	 * @return 对象org.w3c.dom.Document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document strToDocument(String xmlStr) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        InputStream is= new BufferedInputStream(new ByteArrayInputStream(xmlStr.getBytes()));
        
        Document document = builder.parse(is);
        
        is.close();
        
        return document;
	}

    /**
     * 把dom文件转换为xml字符串
     * @param document 对象org.w3c.dom.document
     * @param encoding 字符集，gbk,utf,iso-8859-1,gb2312等
     * @return
     */
    public static String toStringFromXmlDoc(Document document,String encoding) 
    {  
        String result = null;  

        if (document != null) 
        {  

            StringWriter strWtr = new StringWriter();  
            StreamResult strResult = new StreamResult(strWtr);  
            TransformerFactory tfac = TransformerFactory.newInstance();  
            try 
            {  

                javax.xml.transform.Transformer t = tfac.newTransformer();  

                t.setOutputProperty(OutputKeys.ENCODING, encoding);  
                t.setOutputProperty(OutputKeys.INDENT, "yes");  
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,  

                // text  
                t.setOutputProperty(  

                        "{http://xml.apache.org/xslt}indent-amount", "4");  

                t.transform(new DOMSource(document.getDocumentElement()),strResult);  
            } 
            catch (Exception e) 
            {  
                System.err.println("XML.toString(Document): " + e);  
            }  
            result = strResult.getWriter().toString();  
            try 
            {  
                strWtr.close();  
            } 
            catch (IOException e) 
            {  
                e.printStackTrace();  
            }
        }
        return result;  
    } 
    
    /**
     * 将String类型的xml转换成指定字符集的java.io.InputStreamReader对象
     * @param xmlStr 转换成String类型的xml
     * @param encoding 字符集，gbk,utf,iso-8859-1,gb2312等
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static InputStreamReader xmlStringToInputStreamReader(String xmlStr,String encoding) throws UnsupportedEncodingException
    {
    	InputStreamReader isr=null;
    	ByteArrayInputStream bais=new ByteArrayInputStream(xmlStr.getBytes(encoding));
    	InputStream is=new BufferedInputStream(bais);
    	isr=new InputStreamReader(is, encoding);
    	return isr;
    }
}
