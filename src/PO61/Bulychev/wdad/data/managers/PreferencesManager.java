package PO61.Bulychev.wdad.data.managers;

import java.io.File;
import java.nio.file.Paths;

public class PreferencesManager {
    private static PreferencesManager ourInstance = new PreferencesManager();

    private String configpath = "PO61/Bulychev/wdad/resources/configuration/appconfig.xml";

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {
    }


}
