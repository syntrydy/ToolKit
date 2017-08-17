package cm.it.secret.gasmyr.toolkit.database;

import java.util.List;

import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public interface IGroupService {

    public Group getGroudById(Long id);

    public List<Group> getAllGroups();

    public boolean deleteGroup(Group group);

    public boolean deleteGroup(Long id);

    public void updateGroup(Group group);

    public void addGroud(Group group);
}
