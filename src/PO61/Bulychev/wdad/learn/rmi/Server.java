package PO61.Bulychev.wdad.learn.rmi;

import PO61.Bulychev.wdad.data.managers.PreferencesManager;
import PO61.Bulychev.wdad.utils.PreferencesManagerConstants;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class Server {

    private static final String BINDED_OBJECT_NAME = "XmlDataManager";


    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        int port = 1099;
        Registry registry = null;
        PreferencesManager manager = PreferencesManager.getInstance();
        try {
            manager.removeBindedObject(BINDED_OBJECT_NAME);
        } catch (NullPointerException e) {
            System.out.print(""); // no binded object
        }

        Properties props = manager.getProperties();
        String codebaseUrl = props.getProperty(PreferencesManagerConstants.CLASS_PROVIDER);
        String usecodebaseonly = props.getProperty(PreferencesManagerConstants.USE_CODEBASE_ONLY);
        String securityPolicyPath = props.getProperty(PreferencesManagerConstants.POLICY_PATH);
        usecodebaseonly = usecodebaseonly.equals("yes") ? "true" : "false";

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodeBaseOnly", usecodebaseonly);
        System.setProperty("java.security.policy", securityPolicyPath);
        System.setSecurityManager(new SecurityManager());

        if (props.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("no")) {
            String registryAddress = props.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
            String registryPort = props.getProperty(PreferencesManagerConstants.REGISTRY_PORT);
            port = Integer.parseInt(registryPort);
            registry = LocateRegistry.getRegistry(registryAddress, port);
        } else if (props.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("yes")) {
            registry = LocateRegistry.createRegistry(port);
        }

        XmlDataManagerImpl dataManager = new XmlDataManagerImpl();
        Remote stub = UnicastRemoteObject.exportObject(dataManager, 0);
        registry.bind(BINDED_OBJECT_NAME, stub);
        manager.addBindedObject(BINDED_OBJECT_NAME, dataManager.getClass().getCanonicalName());
        System.out.println(manager.getAttribute(PreferencesManagerConstants.BINDED_OBJECT, "name"));
        System.out.println(manager.getAttribute(PreferencesManagerConstants.BINDED_OBJECT, "class"));
        manager.setProperty(PreferencesManagerConstants.REGISTRY_ADDRESS, "localhost");
        manager.setProperty(PreferencesManagerConstants.REGISTRY_PORT, Integer.toString(port));

        //props = manager.getProperties();
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }

    }
}
