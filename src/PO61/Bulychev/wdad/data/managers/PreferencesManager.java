package PO61.Bulychev.wdad.data.managers;

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
import java.util.HashMap;
import java.util.Map;

public class PreferencesManager {
    private static PreferencesManager ourInstance = new PreferencesManager();

    private String xmlpath = "src/PO61/Bulychev/wdad/resources/configuration/appconfig.xml";
    private XPathFactory xPathFactory;
    private Document document;
    //private Map<String, String> propertyToXPath;

    private PreferencesManager() {
        loadxml(xmlpath);
        xPathFactory = XPathFactory.newInstance();
//        propertyToXPath = new HashMap<>();
//        propertyToXPath.put("createregistry", "/appconfig/rmi/server/registry/createregistry/text()");
//        propertyToXPath.put("registryaddress", "/appconfig/rmi/server/registry/registryaddress/text()");
//        propertyToXPath.put("registryport", "/appconfig/rmi/server/registry/registryport/text()");
//        propertyToXPath.put("policypath", "/appconfig/rmi/client/policypath/text()");
//        propertyToXPath.put("usecodebaseonly", "/appconfig/rmi/client/usecodebaseonly/text()");
//        propertyToXPath.put("classprovider", "/appconfig/rmi/classprovider/text()");
    }

    public static PreferencesManager getInstance() {
        return ourInstance;
    }


    public void setProperty(String property, String value) {
        getNode(property).setTextContent(value);
        savexml();
    }

    public String getProperty(String property) {

        //String xpathProperty = propertyToXPath.get(property);
        XPath xpath = xPathFactory.newXPath();
        String result = "";
        XPathExpression expr = null;
        try {
            //expr = xpath.compile(xpathProperty);
            expr = xpath.compile(property);
            result = (String) expr.evaluate(document, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
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

    private Node getNode(String property) {
        NodeList appconfig = document.getElementsByTagName(property);
        return appconfig.item(0);
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
