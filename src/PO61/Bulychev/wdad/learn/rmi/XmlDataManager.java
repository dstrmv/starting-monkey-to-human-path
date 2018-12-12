package PO61.Bulychev.wdad.learn.rmi;

import PO61.Bulychev.wdad.learn.xml.Officiant;
import PO61.Bulychev.wdad.learn.xml.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface XmlDataManager extends Remote {

    double earningsTotal(Officiant officiant, LocalDate date) throws RemoteException;

    void removeDay (LocalDate date) throws RemoteException;

    void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws RemoteException;

    List<Order> getOrders(LocalDate date) throws RemoteException;

    LocalDate lastOfficiantWorkDate(Officiant officiant) throws RemoteException;
}
