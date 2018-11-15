package PO61.Bulychev.wdad.data.managers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

    private void loadxml(String path) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(path);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
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

}
