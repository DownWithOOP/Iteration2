package utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cduica on 2/26/17.
 */
public class MapXMLParser {

    private Document doc;

    public void loadDocument(String path) throws Exception {
        File xmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
    }

    public ArrayList<HashMap<String, String>> parseDocument(){
        ArrayList<HashMap<String, String>> tileList = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName("Tile");
        for(int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                HashMap<String, String> temp = new HashMap<>();
                temp.put("Terrain", element.getElementsByTagName("Terrain").item(0).getTextContent());
                temp.put("Energy", element.getElementsByTagName("Energy").item(0).getTextContent());
                temp.put("Ore", element.getElementsByTagName("Ore").item(0).getTextContent());
                temp.put("Food", element.getElementsByTagName("Food").item(0).getTextContent());
                temp.put("Decal", element.getElementsByTagName("Decal").item(0).getTextContent());
                temp.put("Item", element.getElementsByTagName("Item").item(0).getTextContent());
                temp.put("AreaEffect", element.getElementsByTagName("AreaEffect").item(0).getTextContent());
                tileList.add(temp);
            }
        }
        return tileList;
    }

    public String getMapAttribute(String attrName){
        Node node = doc.getElementsByTagName("Map").item(0);
        return  ((Element) node).getAttribute(attrName);
    }

}
