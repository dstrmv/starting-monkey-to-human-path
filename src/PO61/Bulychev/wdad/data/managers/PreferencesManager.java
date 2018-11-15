package PO61.Bulychev.wdad.data.managers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.nio.file.Paths;

public class PreferencesManager {
    private static PreferencesManager ourInstance = new PreferencesManager();

    private String xmlpath = "src/PO61/Bulychev/wdad/resources/configuration/appconfig.xml";
    Document document;
    private static final String XPATH_CREATEREGISTRY_EXPR = "/appconfig/rmi/server/registry/createregistry/text()";
    private static final String XPATH_REGISTRYADDRESS_EXPR = "/appconfig/rmi/server/registry/registryaddress/text()";
    private static final String XPATH_REGISTRYPORT_EXPR = "/appconfig/rmi/server/registry/registryport/text()";
    private static final String XPATH_CLIENTPOLICYPATH_EXPR = "/appconfig/rmi/client/policypath/text()";
    private static final String XPATH_CLIENTUSECODEBASE_EXPR = "/appconfig/rmi/client/usecodebaseonly/text()";
    private static final String XPATH_CLASSPROVIDER_EXPR = "/appconfig/rmi/classprovider/text()";

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {
        loadxml(xmlpath);
    }

    public String getCreateRegistryProperty() {
        return getProperty(XPATH_CREATEREGISTRY_EXPR);
    }

    public String getRegistryAddressProperty() {
        return getProperty(XPATH_REGISTRYADDRESS_EXPR);
    }

    public String getRegistryPortProperty() {
        return getProperty(XPATH_REGISTRYPORT_EXPR);
    }

    public String getPolicyPathProperty() {
        return getProperty(XPATH_CLIENTPOLICYPATH_EXPR);
    }

    public String getUseCodeBaseOnlyProperty() {
        return getProperty(XPATH_CLIENTUSECODEBASE_EXPR);
    }

    public String getClassProviderProperty() {
        return getProperty(XPATH_CLASSPROVIDER_EXPR);
    }

    public void setCreateRegistryProperty(String val) {
        NodeList appconfig = document.getElementsByTagName("createregistry");
        appconfig.item(0).setTextContent(val);
        savexml();
    }

    public void setRegistryAddressProperty(String val) {
        NodeList appconfig = document.getElementsByTagName("registryaddress");
        appconfig.item(0).setTextContent(val);
        savexml();
    }

    public void setRegistryPortProperty(String val) {
        NodeList appconfig = document.getElementsByTagName("registryport");
        appconfig.item(0).setTextContent(val);
        savexml();

    }

    public void setPolicyPathProperty(String val) {
        NodeList appconfig = document.getElementsByTagName("policypath");
        appconfig.item(0).setTextContent(val);
        savexml();
    }

    public void setUseCodeBaseOnlyProperty(String val) {
        NodeList appconfig = document.getElementsByTagName("uusecodebaseonly");
        appconfig.item(0).setTextContent(val);
        savexml();

    }

    public void setClassProviderProperty(String val) {
        NodeList appconfig = document.getElementsByTagName("classprovider");
        appconfig.item(0).setTextContent(val);
        savexml();
    }

    private String getProperty(String xpathProperty) {

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        String result = "";
        XPathExpression expr = null;
        try {
            expr = xpath.compile(xpathProperty);
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

}
