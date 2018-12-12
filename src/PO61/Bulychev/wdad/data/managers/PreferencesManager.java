package PO61.Bulychev.wdad.data.managers;

import PO61.Bulychev.wdad.utils.PreferencesManagerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class PreferencesManager {
    private static PreferencesManager ourInstance = new PreferencesManager();

    private String xmlpath = "src/PO61/Bulychev/wdad/resources/configuration/appconfig.xml";
    private XPathFactory xPathFactory;
    private Document document;
    private List<String> keys;

    public PreferencesManager() {
        loadxml(xmlpath);
        xPathFactory = XPathFactory.newInstance();
        keys = new ArrayList<>();
        keys.add(PreferencesManagerConstants.CREATE_REGISTRY);
        keys.add(PreferencesManagerConstants.REGISTRY_ADDRESS);
        keys.add(PreferencesManagerConstants.REGISTRY_PORT);
        keys.add(PreferencesManagerConstants.POLICY_PATH);
        keys.add(PreferencesManagerConstants.USE_CODEBASE_ONLY);
        keys.add(PreferencesManagerConstants.CLASS_PROVIDER);
    }

    public static PreferencesManager getInstance() {
        return ourInstance;
    }


    public void setProperty(String key, String value) {
        getNode(key).setTextContent(value);
        savexml();
    }

    public String getProperty(String key) {
        return getNode(key).getTextContent();
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        for (String key: keys) {
            properties.setProperty(key, getProperty(key));
        }
        return properties;
    }

    public void setProperties(Properties properties) {
        Set<String> strings = properties.stringPropertyNames();
        for (String key: strings) {
            setProperty(key, properties.getProperty(key));
        }
    }

    public void addBindedObject(String name, String className) {
        Element element = document.createElement("bindedobject");
        element.setAttribute("name",name);
        element.setAttribute("class", className);
        document.getElementsByTagName("server").item(0).appendChild(element);
        savexml();
    }

    public void removeBindedObject(String name) {
        String xpathkey = "appconfig/rmi/server/bindedobject";
        XPath xpath = xPathFactory.newXPath();
        XPathExpression expr = null;
        NodeList nodelist = null;
        try {
            expr = xpath.compile(xpathkey);
            nodelist = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
            Element element;
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
               if ( element.getAttribute("name").equals(name)) {
                   element.getParentNode().removeChild(element);
                   savexml();
                   return;
               }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private void savexml() {
        try {
            //document.get
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "appconfig.dtd");
            Result output = new StreamResult(new File(xmlpath));
            Source input = new DOMSource(document);
            transformer.transform(input, output);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void loadxml(String path) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(path);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private Node getNode(String key) {
        String xpathkey = key.replace('.', '/');
        XPath xpath = xPathFactory.newXPath();
        XPathExpression expr = null;
        Node node = null;
        try {
            expr = xpath.compile(xpathkey);
            node = (Node) expr.evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        if (node == null) {
            throw new RuntimeException("key is not exist");
        }
        return node;
    }




    @Deprecated
    public String getCreateRegistryProperty() {
        return getProperty("createregistry");
    }

    @Deprecated
    public void setCreateRegistryProperty(String val) {
        setProperty("createregistry", val);
    }

    @Deprecated
    public String getRegistryAddressProperty() {
        return getProperty("registryaddress");
    }

    @Deprecated
    public void setRegistryAddressProperty(String val) {
        setProperty("registryaddress", val);
    }

    @Deprecated
    public String getRegistryPortProperty() {
        return getProperty("registryport");
    }

    @Deprecated
    public void setRegistryPortProperty(String val) {
        setProperty("registryport", val);
    }

    @Deprecated
    public String getPolicyPathProperty() {
        return getProperty("policypath");
    }

    @Deprecated
    public void setPolicyPathProperty(String val) {
        setProperty("policypath", val);
    }

    @Deprecated
    public String getUseCodeBaseOnlyProperty() {
        return getProperty("usecodebaseonly");
    }

    @Deprecated
    public void setUseCodeBaseOnlyProperty(String val) {
        setProperty("usecodebaseonly", val);

    }

    @Deprecated
    public String getClassProviderProperty() {
        return getProperty("classprovider");
    }

    @Deprecated
    public void setClassProviderProperty(String val) {
        setProperty("classprovider", val);
    }
}
