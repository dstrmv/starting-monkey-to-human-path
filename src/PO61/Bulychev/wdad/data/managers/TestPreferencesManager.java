package PO61.Bulychev.wdad.data.managers;

public class TestPreferencesManager {
    public static void main(String[] args) {
        PreferencesManager manager = PreferencesManager.getInstance();
        System.out.println(manager.getCreateRegistryProperty());
        System.out.println(manager.getRegistryAddressProperty());
        System.out.println(manager.getRegistryPortProperty());
        System.out.println(manager.getPolicyPathProperty());
        System.out.println(manager.getUseCodeBaseOnlyProperty());
        System.out.println(manager.getClassProviderProperty());
    }
}
