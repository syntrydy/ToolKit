package cm.it.secret.gasmyr.toolkit.core;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class GreetingPerson {
    private String name;
    private String phoneNumber;

    public GreetingPerson(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
