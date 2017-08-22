package cn.rookiex.xml;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.rookiex.ai.Selector;
import cn.rookiex.ai.Sequence;
import cn.rookiex.aiTree.AIContext;
import cn.rookiex.aiTree.AIExcutor;
import cn.rookiex.aiTree.AILeaf;
import cn.rookiex.aiTree.Node;

/**
 * @author rookiex
 *
 */
public class XML2AI {

	/**
	 * 初始化方法,初始xml配置
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		SAXReader reader = new SAXReader();
		Document document = null;
		System.out.println(this.getClass().getResource(""));
		try {
			document = reader.read(new File("src/cn/rookiex/config/aiTree.xml"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = document.getRootElement();		
		
		Node AI = listNode(root);


		AI.excute(null);
	}

	public Node listNode(Element node) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Node aiNode = Xml2Node(node);
		
		Iterator<Element> elementIterator = node.elementIterator();
		while (elementIterator.hasNext()) {
			Element next = elementIterator.next();
			aiNode.addSon(listNode(next));
		}
		return aiNode;
	}

	private Node Xml2Node(Element node) throws ClassNotFoundException, InstantiationException, 
	IllegalAccessException{
		StringBuffer className = new StringBuffer(64);
		className.append("cn.rookiex.ai.");
		//className.append("AI");
		className.append(node.attribute("class").getValue());
		return createNodeByName(className.toString());
	}
	
//	@SuppressWarnings("unused")
//	private Node createNodeByName(String name) {
//		if(name == "Selector"){
//			return new Selector();
//		}else if(name == "Sequence"){
//			return new Sequence();
//		}
//		return null;
//	}

	private Node createNodeByName (String nodeName) 
			throws ClassNotFoundException, 
			InstantiationException, 
			IllegalAccessException {
		if (nodeName == null || nodeName.trim().equals("")) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		Class<? extends Node> node = 
				(Class<? extends Node>) Class.forName(nodeName);
		
		return node.newInstance();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		XML2AI xml = new XML2AI();
		xml.init();
		
	}
}
