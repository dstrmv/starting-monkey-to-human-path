package PO61.Bulychev.wdad.data.managers;

import PO61.Bulychev.wdad.learn.xml.Officiant;
import PO61.Bulychev.wdad.learn.xml.Order;
import PO61.Bulychev.wdad.learn.xml.XmlTask;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public class RmiDataManager implements DataManager {

    XmlTask xmlTask;

    public RmiDataManager() {
        xmlTask = new XmlTask("src/PO61/Bulychev/wdad/learn/xml/restFirstCorrect.xml");
    }

    @Override
    public double earningsTotal(Officiant officiant, LocalDate date) throws RemoteException {
        return xmlTask.earningsTotal(officiant, date);
    }

    @Override
    public void removeDay(LocalDate date) throws RemoteException {
        xmlTask.removeDay(date);
    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws RemoteException {
        xmlTask.changeOfficiantName(oldOfficiant, newOfficiant);
    }

    @Override
    public List<Order> getOrders(LocalDate date) throws RemoteException {
        return xmlTask.getOrders(date);
    }

    @Override
    public LocalDate lastOfficiantWorkDate(Officiant officiant) throws RemoteException {
        return xmlTask.lastOfficiantWorkDate(officiant);
    }
}
