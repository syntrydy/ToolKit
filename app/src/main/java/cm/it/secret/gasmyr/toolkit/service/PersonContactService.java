package cm.it.secret.gasmyr.toolkit.service;

import android.util.Log;

import java.util.List;

import cm.it.secret.gasmyr.toolkit.core.DaoSession;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;
import cm.it.secret.gasmyr.toolkit.database.IPersonContactService;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public class PersonContactService implements IPersonContactService{

    private DaoSession daoSession;

    public PersonContactService(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public PersonContact getPersonById(Long id) {
        return daoSession.getPersonContactDao().loadByRowId(id);
    }

    @Override
    public List<PersonContact> getAllPerson() {
        return daoSession.getPersonContactDao().loadAll();
    }

    @Override
    public boolean deletePerson(PersonContact personContact) {
        try{
            daoSession.getPersonContactDao().delete(personContact);
            return true;
        }
        catch (Exception e){
            Log.e(PersonContactService.class.getName(),e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePerson(Long id) {
        try{
            daoSession.getPersonContactDao().deleteByKey(id);
            return true;
        }
        catch (Exception e){
            Log.e(PersonContactService.class.getName(),e.getMessage());
            return false;
        }
    }

    @Override
    public void updatePerson(PersonContact personContact) {
        daoSession.getPersonContactDao().update(personContact);
    }

    @Override
    public void addPerson(PersonContact personContact) {
        daoSession.getPersonContactDao().insert(personContact);
    }
}
