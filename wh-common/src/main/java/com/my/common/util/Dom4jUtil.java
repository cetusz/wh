package com.my.common.util;

import  java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import  java.io.InputStream;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import  org.dom4j.Document;
import  org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import  org.dom4j.io.DOMReader;
import  org.dom4j.io.DOMWriter;
import org.dom4j.io.OutputFormat;
import  org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dom4jUtil {
	final static Logger logger = LoggerFactory.getLogger(Dom4jUtil.class);
	
	public static Document loadDomByPath(String path){
		Document dom = null;
		try {
			SAXReader reader = new SAXReader();
			InputStream is=new FileInputStream(path);
			dom= reader.read(is);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dom;
	}
	
	public static Document loadDomByClassPath(String path){
		Document dom = null;
		try {
			SAXReader reader = new SAXReader();
			InputStream is=Dom4jUtil.class.getResourceAsStream(path);
			dom= reader.read(is);	
		} catch (Exception e) {
			logger.warn("找不到路径为"+path+"的配置文件");
		}
		return dom;
	}
	
	public static org.w3c.dom.Document toW3CDocument(Document d4doc)   
    {   
        DOMWriter d4Writer = new DOMWriter();   
        try  
        {   
            return d4Writer.write(d4doc);   
        }   
        catch(DocumentException e)   
        {   
            e.printStackTrace();  
        }   
        return null;
    }
	
	
	public static Document fromW3CDocument(org.w3c.dom.Document doc) {
		if (doc == null) {
			return null;
		}
		DOMReader xmlReader = new DOMReader();
		return xmlReader.read(doc);
	}
	
	public static Document fromString(String xmlStr) {// Str是传入的一段XML内容的字符串
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);// DocumentHelper.parseText(str)这个方法将传入的XML字符串转换处理后返回一个Document对象
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	
	public static Document getXMLfromBlob(Blob blob) {//read XML from Blob field
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			if (blob != null) {
				InputStream stream=blob.getBinaryStream();
				document=reader.read(stream);
			}
		} catch (DocumentException de) {
			de.printStackTrace();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;

	}
	
	/** 
     * 写入xml文件地址 
     * @param document 所属要写入的内容 
     * @param outFile 文件存放的地址 
     */  
    public static void writeDocument(Document document, String outFile){  
        try{  
            //读取文件  
            FileWriter fileWriter = new FileWriter(outFile);  
            //设置文件编码  
            OutputFormat xmlFormat = new OutputFormat();  
            xmlFormat.setEncoding("UTF-8");  
            //创建写文件方法  
            XMLWriter xmlWriter = new XMLWriter(fileWriter,xmlFormat);  
            //写入文件  
            xmlWriter.write(document);  
            //关闭  
            xmlWriter.close();  
        }catch(IOException e){  
            System.out.println("文件没有找到");  
            e.printStackTrace();  
        }  
    } 
    
    
    
    /**
	 * 分析出XML Document对象
	 * 
	 * @param xmldocumentstr
	 * @return
	 */
	public static Document parse(String xmldocumentstr){
		try {
			SAXReader reader = new SAXReader();
			StringReader str_reader = new StringReader(xmldocumentstr);
			//Document document = reader.read(xmldocumentstr);
			Document document = DocumentHelper.parseText(xmldocumentstr);
			str_reader.close();
			return document;
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
			//throw new RuntimeException("分析XML字符串失败");
		}
	}
	
	
	/**
	 * 分析出XML Document对象
	 * 
	 * @param xmldocumentstr
	 * @return
	 */
	public static Document parseFile(String filepath){
		try {
			SAXReader reader = new SAXReader();
			FileReader file_reader = new FileReader(filepath);
			Document document = reader.read(file_reader);
			file_reader.close();
			return document;
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	
	/**
	 * 分析出XML Document对象 输入XML片段
	 * 
	 * @param xmldocumentstr
	 *        输入的XML片段，允许为空
	 * @return
	 */
	public static Document parseFragment(String xmldocumentstr){
		try {
			if (xmldocumentstr != null) {
				return DocumentHelper.parseText(xmldocumentstr);
			}
			else {
				return DocumentHelper.createDocument();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("分析XML字符串失败", null);
		}
	}
	
	/**
	 * 根据标签名获取子元素，可以取多个元素
	 * 
	 * @param element
	 *        父元素
	 * @param tagName
	 *        标签名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getSubElementByTagName(Element element, String tagName) {
		// 不指定标签名称则返回null
		if (tagName == null || "".equals(tagName.trim())) {
			return null;
		}

		List nodes = element.elements(new QName(tagName, element.getNamespace()));
		return null == nodes ? new ArrayList() : nodes;
	}
	
	/**
	 * 根据标签名获取子元素，适合只有一个子元素的情况
	 * 
	 * @param element
	 *        父元素
	 * @param tagName
	 *        标签名
	 */
	@SuppressWarnings("unchecked")
	public static Element getSingleElementByTagName(Element element, String tagName) {
		// 不指定标签名称则返回null
		if (tagName == null || "".equals(tagName.trim())) {
			return null;
		}

		List nodes = element.elements(new QName(tagName, element.getNamespace()));

		if (nodes == null || nodes.isEmpty()) {
			return null;
		}
		else {
			return (Element) nodes.get(0);
		}
	}
	
	/**
	 * 根据父元素和子标签名获取该子标签的内容，如果没有则返回空字符串，适合只有一个子元素的情况
	 * 
	 * @param element
	 *        父元素
	 * @param tagName
	 *        标签名
	 */
	public static String getContentByTagName(Element element, String tagName) {
		// 不指定标签名称则返回null
		if (tagName == null || "".equals(tagName.trim())) {
			return null;
		}
		return getSingleElementByTagName(element, tagName).getText();
	}

	/**
	 * 获取该标签的内容，如果没有则返回空字符串
	 * 
	 * @param element
	 *        该元素
	 */
	public static String getContent(Element element) {
		return element.getText();
	}
	
	/**
	 * 获取标签里对应属性的值
	 * 
	 * @param element
	 *        该标签
	 * @param attName
	 *        属性名
	 * @return 该属性的值
	 */
	public static String getValueByAttName(Element element, String attName) {
		// 不指定属性名称则返回null
		if (attName == null || "".equals(attName.trim())) {
			return null;
		}
		return element.valueOf("@" + attName);
	}
	
	/**
	 * 修改标签里对应属性的值，如果原来就有该属性则覆盖，如果原来没有该属性则添加
	 * 
	 * @since May 19, 2008
	 * @param element
	 *        该标签
	 * @param attName
	 *        属性名
	 * @param attName
	 *        属性值
	 */
	public static void setValueByAttName(Element element, String attName, String attValue) {
		// 不指定属性名称则返回null
		if (attName == null || "".equals(attName.trim())) {
			return;
		}

		attValue = attValue == null ? "" : attValue;
		Attribute attribute = element.attribute(attName);// 获取属性对象
		if (attribute != null) {
			attribute.setValue(attValue);
		}
		else {
			element.addAttribute(attName, attValue);// 构造新属性
		}
	}

	/**
	 * 功能：获取指定父节点其下的所有直接子节点。和getSubElementByTagName不同之处在于其不指定儿子的名字
	 * 
	 * @param element
	 * @return
	 */
	public static List getSubElementByParent(Element element) {
		return element.elements();
	}

	/**
	 * 功能：获取指定父节点下的所有子节点中符合条件的第一个节点（条件:指定属性为指定值）
	 * <p>
	 * 
	 * @param element
	 *        父节点
	 * @param attName
	 *        属性名称
	 * @param attValue
	 *        属性值
	 * @return 如果找不到合适的则返回null
	 * @throws IFormException
	 */
	public static Element getSubElementByValue(Element element, String attName,String attValue){
		// 不指定属性名称则返回null
		if (attName == null || "".equals(attName.trim())) {
			return null;
		}
		// 不指定属性值也返回null，允许指定属性值为空字符串
		if (attValue == null) {
			return null;
		}

		// 遍历所有子节点，发现符合条件的立即返回
		for (Iterator itr = getSubElementByParent(element).iterator(); itr.hasNext();) {
			Element subEle = (Element) itr.next();
			if (attValue.equals(getValueByAttName(subEle, attName))) {
				return subEle;
			}
		}
		return null;
	}
}
