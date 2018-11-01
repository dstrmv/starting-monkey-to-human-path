package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Officiant {

    @XmlAttribute(name = "firstname")
    private String firstName;
    @XmlAttribute(name = "secondname")
    private String secondName;

    public Officiant() {
    }

    public Officiant(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "officiant " + firstName + " " + secondName;
    }
}
