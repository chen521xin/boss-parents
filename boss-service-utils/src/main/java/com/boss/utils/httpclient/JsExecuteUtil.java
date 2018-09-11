package com.boss.utils.httpclient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsExecuteUtil {
	
	/**
	 * jsPath:js文件地址；function：方法名称；param：参数
	 * @param jspath
	 * @param functionName
	 * @param param
	 * @return
	 */
	public static Object executeFunction(String jspath, String functionName, List<String> param) {
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine se=sem.getEngineByExtension("js");
		Object value = null;
		try {
			se.eval(new FileReader(jspath));
			String script="eval(\""+ functionName + getParamString(param) + "\")";
			value= se.eval(script);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		
		//System.out.println(getParamString(param));
		return value;
	}
	
	public static Object executeFunction(BufferedReader reader, String functionName, List<String> param) throws IOException {
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine se=sem.getEngineByExtension("js");
		Object value = null;
			
			try {
				
				StringBuffer sbf=new StringBuffer();
				String tmp=null;
				while((tmp=reader.readLine())!=null)
				{
					sbf.append(tmp);
				}
				
				se.eval(sbf.toString());
				String script="eval(\""+ functionName + getParamString(param) + "\")";
				value= se.eval(script);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		//System.out.println(getParamString(param));
		return value;
	}
	
	/**
	 * 通过输入param得到要求的参数格式
	 * @param param
	 * @return
	 */
	public static String getParamString(List<String> param) {
		Iterator<String> it = param.iterator();
        if (! it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append("(\'");
        for (;;) {
            String e = it.next();
            sb.append(e);
            if (! it.hasNext())
            {
            	System.out.println(sb.toString());
            	return sb.append("\')").toString();
            }
            
            sb.append("\',").append(" \'");
        }
	}

}
