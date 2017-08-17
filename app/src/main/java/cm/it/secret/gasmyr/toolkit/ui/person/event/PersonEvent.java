package cm.it.secret.gasmyr.toolkit.ui.person.event;

/**
 * Created by gasmyr.mougang on 6/4/17.
 */

public class PersonEvent {
    int code;
    Long personId;

    public PersonEvent(int code, Long personId) {
        this.code = code;
        this.personId = personId;
    }

    public int getCode() {
        return code;
    }

    public Long getPersonId() {
        return personId;
    }
}
