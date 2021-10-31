import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Green;
import model.Plant;
import model.Root;

public class Parser {
	
	private static final String TAG_NAME_MAIN = "name";
	private static final String TAG_PLANT = "plants";
	private static final String TAG_TREE = "tree";
	private static final String TAG_BUSH = "bush";
	private static final String TAG_ELEMENT = "element";
	private static final String TAG_NAME = "name";
	private static final String TAG_AGE = "age";
	private static final String TAG_HEIGHT = "height";
	private static final String TAG_STATUS = "status";

	
	public Root parse() {
		Root root = new Root();
		Plant plant = new Plant();

		Document doc;
		try {
			doc = buildDocument();
		}	catch(Exception e) {
			System.out.println("Open parsing error " + e.toString());
			return null;
		}
		
		
		Node rootNode = doc.getFirstChild();
		NodeList rootChilds = rootNode.getChildNodes();
		
		String mainName = null;
		Node plantNode = null;
		
		for(int i = 0; i < rootChilds.getLength(); i++) {
			if(rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			switch (rootChilds.item(i).getNodeName()) {
				case TAG_NAME_MAIN:
					mainName = rootChilds.item(i).getTextContent();
					break;
				case TAG_PLANT:
					plantNode = rootChilds.item(i);
					break;
			}
		}
		
		if(plantNode == null) {
			return null;
		}
		NodeList plantChilds = plantNode.getChildNodes();
		Node treeNode = null;
		Node bushNode = null;
		for(int i = 0; i < plantChilds.getLength(); i++) {
			if(plantChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			switch (plantChilds.item(i).getNodeName()) {
				case TAG_TREE: {
					treeNode = plantChilds.item(i);
					break;
				}
				case TAG_BUSH: {
					bushNode = plantChilds.item(i);
					break;
				}
			}
		}
		List<Green> treeList = parsGreen(treeNode);
		List<Green> bushList = parsGreen(bushNode);
		plant.setTree(treeList);
		plant.setBush(bushList);
		
		root.setName(mainName);
		root.setPlant(plant);
		
//		root.getPeople().stream().filter(people -> {
//			return people.getAge() == 20;
//		}).forEach(people -> {
//			System.out.println("People " + people.toString());
//		});;
		
		return root;
	}
	
	
	private Document buildDocument() throws Exception{
		File file = new File("text.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		return dbf.newDocumentBuilder().parse(file);
	}
	
	private List<Green> parsGreen(Node greenNode) {
		List<Green> greenList = new ArrayList<>();
		NodeList greenChilds = greenNode.getChildNodes();
		for(int i = 0; i < greenChilds.getLength(); i++) {
			if(greenChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			if(!greenChilds.item(i).getNodeName().equals(TAG_ELEMENT)) {
				continue;
			}
			
			Green green = parsElement(greenChilds.item(i));			
			greenList.add(green);
		}
		return greenList;
	}
	
	private Green parsElement(Node elementNode) {
		String name = "";
		int age = 0;
		String status = "";
		int height = 0;
		
		NodeList elementChilds = elementNode.getChildNodes();
		for(int j = 0; j < elementChilds.getLength(); j++) {
			if(elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			switch (elementChilds.item(j).getNodeName()) {
				case TAG_NAME: 
					name = elementChilds.item(j).getTextContent();
					break;
				case TAG_AGE:
					age = Integer.valueOf(elementChilds.item(j).getTextContent());
					break;
				case TAG_STATUS:
					status = elementChilds.item(j).getTextContent();
//					System.out.println(status);
					break;
				case TAG_HEIGHT:
					height = Integer.valueOf(elementChilds.item(j).getTextContent());
					break;
			}
		}

		return new Green(name, age, status, height);
	}
}
