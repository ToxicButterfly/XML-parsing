import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.thoughtworks.xstream.XStream;

import model.Green;
import model.Root;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, FileNotFoundException, TransformerException {
		
		Parser parser = new Parser();
		Root root = parser.parse();
		System.out.println(root.toString());

//		Сажаем все непосаженные растения.
		planting(root);
		int count = counting(root);
		
//		Тут должен был найти сумму высот всех деревьев, но мне не хватило знаний по stream
//		int sum = root.getPlant().getBush().stream().f
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		Element roott = document.createElement("root");
		Element col = document.createElement("count");		
		Element height = document.createElement("total-height");
		document.appendChild(roott);
		col.setTextContent(Integer.toString(count));
		height.setTextContent("?");
		roott.appendChild(col);
		roott.appendChild(height);
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("temp.xml")));
	}   
	
	
	
	
	
	
	public static void planting(Root root) {
		root.getPlant().getBush().stream().filter(green -> green.getStatus().equals("Not planted")).forEach(green -> {
			green.planting();
		});
		root.getPlant().getTree().stream().filter(green -> green.getStatus().equals("Not planted")).forEach(green -> {
			green.planting();
		});
	}
	public static int counting(Root root) {
		int count = 0;
		count = (int) root.getPlant().getBush().stream().count();
		count += (int) root.getPlant().getTree().stream().count();
		return count;
	}
}
