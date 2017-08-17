package cm.it.secret.gasmyr.toolkit.database;

import java.util.List;

import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public interface IPersonContactService {

    public PersonContact getPersonById(Long id);

    public List<PersonContact> getAllPerson();

    public boolean deletePerson(PersonContact personContact);

    public boolean deletePerson(Long id);

    public void updatePerson(PersonContact personContact);

    public void addPerson(PersonContact personContact);
}
