package PO61.Bulychev.wdad.learn.rmi;

import PO61.Bulychev.wdad.data.managers.DataManager;
import PO61.Bulychev.wdad.data.managers.PreferencesManager;
import PO61.Bulychev.wdad.learn.xml.Officiant;
import PO61.Bulychev.wdad.utils.PreferencesManagerConstants;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Properties;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        PreferencesManager manager = PreferencesManager.getInstance();
        Properties props = manager.getProperties();

        String codebaseUrl = props.getProperty(PreferencesManagerConstants.CLASS_PROVIDER);
        String usecodebaseonly = props.getProperty(PreferencesManagerConstants.USE_CODEBASE_ONLY);
        String securityPolicyPath = props.getProperty(PreferencesManagerConstants.POLICY_PATH);
        usecodebaseonly = usecodebaseonly.equals("yes") ? "true" : "false";

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodeBaseOnly", usecodebaseonly);
        System.setProperty("java.security.policy", securityPolicyPath);
        System.setSecurityManager(new SecurityManager());

        String registryAddress = props.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
        int registryPort = Integer.parseInt(props.getProperty(PreferencesManagerConstants.REGISTRY_PORT));

        String nameAttr = manager.getAttribute(PreferencesManagerConstants.BINDED_OBJECT, "name");
        String classAttr = manager.getAttribute(PreferencesManagerConstants.BINDED_OBJECT, "class");

        Registry registry = LocateRegistry.getRegistry(registryAddress, registryPort);
        DataManager datamanager = (DataManager) registry.lookup(nameAttr);

        datamanager.changeOfficiantName(new Officiant("sidor", "sidorov"), new Officiant("ivan", "ivanov"));
        System.out.println(datamanager.earningsTotal(new Officiant("ivan", "ivanov"), LocalDate.of(2010, 1, 1)));
        System.out.println(datamanager.lastOfficiantWorkDate(new Officiant("ivan", "ivanov")));
        datamanager.getOrders(LocalDate.of(2010, 1, 1)).forEach(System.out::println);
       // registry.lookup();
    }
}
