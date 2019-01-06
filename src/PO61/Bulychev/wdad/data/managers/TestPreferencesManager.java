package PO61.Bulychev.wdad.data.managers;

public class TestPreferencesManager {
    public static void main(String[] args) {
        PreferencesManager manager = PreferencesManager.getInstance();
        //System.out.println(manager.getCreateRegistryProperty());
        System.out.println(manager.getProperty("appconfig.rmi.server.registry.createregistry"));
        manager.setProperty("appconfig.rmi.server.registry.createregistry", "yes");
        System.out.println(manager.getProperty("appconfig.rmi.server.registry.createregistry"));
        // manager.addBindedObject("object", "className");
        manager.removeBindedObject("object");
        //System.out.println(manager.getRegistryAddressProperty());
        //System.out.println(manager.getRegistryPortProperty());
        //System.out.println(manager.getPolicyPathProperty());
        //System.out.println(manager.getUseCodeBaseOnlyProperty());
        //System.out.println(manager.getClassProviderProperty());
        //manager.setCreateRegistryProperty("no");
        //manager.setRegistryPortProperty("1234");
        //manager.setPolicyPathProperty("client2.policy");
        //System.out.println(manager.getCreateRegistryProperty());
    }
}
