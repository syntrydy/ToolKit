package cm.it.secret.gasmyr.toolkit.database;

import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.GroupMember;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public interface IGroupPersonService {

    public void add(Long groupId, Long personId);
    public GroupMember delete(Long groupId, Long personId);
}
