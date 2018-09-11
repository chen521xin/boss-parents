/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description 
 * @data 2018年5月23日下午8:28:11
 * @author chen.xinxin
 * @version v1.0
 * @since v1.0
 *
 **/
@Component("exportExcel")
public class ExcelCache  implements InitializingBean{
	private Logger logger = Logger.getLogger(ExcelCache.class);
	private volatile Map<String,List<String>> colument=new HashMap<>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void afterPropertiesSet(){
			try {
				SAXReader sax=new SAXReader();
				Document doc = sax.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("excel/ExportExcel.xml"));
				Element rootEl=doc.getRootElement();
				Iterator iter = rootEl.elementIterator();		
				List<String> list=null;
				while(iter.hasNext()){
					list=new ArrayList<String>();
					Element el=(Element) iter.next();			
					List<Element> elList=el.elements();
					if(elList==null||elList.size()<=0){
						continue;
					}
					for(Element e:elList){
						list.add(e.getText());
					}			
					if(!colument.containsValue(el.attributeValue("id"))){
						colument.put(el.attributeValue("id"), list);
					}
					
				}
			} catch (DocumentException e1) {
				e1.printStackTrace();
				logger.error("读取excel导出列配置xml报错");
			}
		
			System.out.println(colument);

	}
	
	public List<String> getById(String key){
		if(StringUtils.isEmpty(key)){
			return null;
		}
		if(colument == null || colument.size() == 0){
			afterPropertiesSet();
		}
		return colument.get(key);
	}

}
