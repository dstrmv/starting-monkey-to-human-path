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
        Properties props = manager.getProperties();
        if (props.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("no")) {
            String registryAddress = props.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
            String registryPort = props.getProperty(PreferencesManagerConstants.REGISTRY_PORT);
            port = Integer.parseInt(registryPort);
            registry = LocateRegistry.getRegistry(registryAddress, port);
        } else if (props.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("yes")) {
            registry = LocateRegistry.createRegistry(port);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            manager.removeBindedObject(BINDED_OBJECT_NAME);
        }));

        XmlDataManagerImpl dataManager = new XmlDataManagerImpl();
        Remote stub = UnicastRemoteObject.exportObject(dataManager, 0);
        registry.bind(BINDED_OBJECT_NAME, stub);
        manager.addBindedObject(BINDED_OBJECT_NAME, dataManager.getClass().getCanonicalName());
        manager.setProperty(PreferencesManagerConstants.REGISTRY_ADDRESS, "localhost");
        manager.setProperty(PreferencesManagerConstants.REGISTRY_PORT, Integer.toString(port));
        //props = manager.getProperties();
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }

    }
}
