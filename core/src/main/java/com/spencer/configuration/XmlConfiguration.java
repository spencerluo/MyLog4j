package com.spencer.configuration;

import com.spencer.plugins.PluginType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlConfiguration extends BaseConfiguration {
    private Element rootElement;

    public XmlConfiguration(InputStream inputStream){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            rootElement = document.getDocumentElement();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }

    public void setup(){
        convertToConfigNode(rootElement, rootNode);
        System.out.println("over");
    }

    private void convertToConfigNode(Element element, ConfigNode configNode) {
        processAttributes(element, configNode);
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element) {
                Element child = (Element)item;
                String name = getType(child);
                PluginType plugin = pluginManager.getPlugin(name);
                ConfigNode childNode = new ConfigNode(configNode, name, plugin);
                convertToConfigNode(child, childNode);
                if (plugin != null) {
                    configNode.addChildNode(childNode);
                }
            }
        }
    }

    private void processAttributes(Element element, ConfigNode configNode) {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i <attributes.getLength(); i++) {
            Node node = attributes.item(i);
            if (node instanceof Attr){
                Attr attr = (Attr)node;
                configNode.addAttr(attr.getName(), attr.getValue());
            }
        }
    }

    private String getType(Element child) {
        String type = child.getAttribute("type");
        String tagName = child.getTagName();
        return type.isEmpty()?tagName: type;
    }

    public static void main(String[] args) {
        InputStream inputStream = XmlConfiguration.class.getResourceAsStream("/log4j.xml");
        XmlConfiguration xmlConfiguration = new XmlConfiguration(inputStream);
        xmlConfiguration.start();

    }
}
