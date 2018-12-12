package PO61.Bulychev.wdad.learn.rmi;

import PO61.Bulychev.wdad.data.managers.PreferencesManager;
import PO61.Bulychev.wdad.utils.PreferencesManagerConstants;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Server {

    private static final String BINDED_OBJECT_NAME = "XmlDataManager";


    public static void main(String[] args) {
        Registry registry = null;
        PreferencesManager manager = new PreferencesManager();
        Properties props = manager.getProperties();
        if (props.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("no")) {
            String registryAdress = props.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
            String registryPort = props.getProperty(PreferencesManagerConstants.REGISTRY_PORT);
            try {
                registry = LocateRegistry.getRegistry(registryAdress, Integer.parseInt(registryPort));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (props.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("yes")) {
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }



    }
}
