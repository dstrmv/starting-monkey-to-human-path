package PO61.Bulychev.wdad.learn.rmi;

import PO61.Bulychev.wdad.data.managers.PreferencesManager;
import PO61.Bulychev.wdad.utils.PreferencesManagerConstants;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Client {
    public static void main(String[] args) throws RemoteException {
        PreferencesManager manager = PreferencesManager.getInstance();

        Properties props = manager.getProperties();
        String registryAddress = props.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
        int registryPort = Integer.parseInt(props.getProperty(PreferencesManagerConstants.REGISTRY_PORT));

        Registry registry = LocateRegistry.getRegistry(registryAddress, registryPort);
        Remote stub = registry.lookup()
       // registry.lookup();
    }
}
