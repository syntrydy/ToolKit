package cm.it.secret.gasmyr.toolkit.service;

import android.util.Log;

import java.util.List;

import cm.it.secret.gasmyr.toolkit.core.DaoSession;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;
import cm.it.secret.gasmyr.toolkit.database.IGroupService;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public class GroupService implements IGroupService {

    private DaoSession daoSession;

    public GroupService(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Group getGroudById(Long id) {
        return daoSession.getGroupDao().loadByRowId(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return daoSession.getGroupDao().loadAll();
    }

    @Override
    public boolean deleteGroup(Group group) {
        try{
            daoSession.getGroupDao().delete(group);
            return true;
        }
        catch (Exception e){
            Log.e(GroupService.class.getName(),e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteGroup(Long id) {
        try{
            daoSession.getGroupDao().deleteByKey(id);
            return true;
        }
        catch (Exception e){
            Log.e(GroupService.class.getName(),e.getMessage());
            return false;
        }
    }

    @Override
    public void updateGroup(Group group) {
        daoSession.getGroupDao().update(group);

    }

    @Override
    public void addGroud(Group group) {
        daoSession.getGroupDao().insert(group);
    }
}
