package cn.rookiex.xml;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author rookiex
 *
 */
public class XML {
	
	/**
	 * 初始化方法,初始xml配置
	 */
	public void init(){
		SAXReader reader = new SAXReader();       
		Document   document = null;
		System.out.println(this.getClass().getResource(""));
	    try {
	    	document = reader.read(new File("src/cn/rookiex/config/aiTree.xml"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    
	    Element root = document.getRootElement();  
	    System.out.println(root.getName());
	    
	    Iterator<Element> elementIterator = root.elementIterator();
	    while(elementIterator.hasNext()){
	    	Element next = elementIterator.next();
	    	listNode(next);
	    }
	    
	}
	
	public void listNode(Element node){	    
		
	    Iterator<Element> elementIterator = node.elementIterator();
		 while(elementIterator.hasNext()){
		    	Element next = elementIterator.next();
		    	listNode(next);
		    }
	}
	
	public static void main(String[] args){
		XML xml = new XML();
		xml.init();
	}
}
