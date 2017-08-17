package cm.it.secret.gasmyr.toolkit.service;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cm.it.secret.gasmyr.toolkit.core.DaoSession;
import cm.it.secret.gasmyr.toolkit.core.GroupMember;
import cm.it.secret.gasmyr.toolkit.core.GroupMemberDao;
import cm.it.secret.gasmyr.toolkit.database.IGroupPersonService;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public class GroupPersonServiceService implements IGroupPersonService {


    private DaoSession daoSession;

    public GroupPersonServiceService(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public void add(Long groupId, Long personId) {
        daoSession.getGroupMemberDao().insert(new GroupMember(groupId, personId));
    }

    @Override
    public GroupMember  delete(Long groupId, Long personId) {
        QueryBuilder<GroupMember> queryBuilder = daoSession.getGroupMemberDao().queryBuilder();
        queryBuilder.where(GroupMemberDao.Properties.GroupId.eq(groupId),GroupMemberDao.Properties.PersonId.eq(personId)).limit(1).build();
        GroupMember groupMember = queryBuilder.list().get(0);
        if(groupMember!=null){
            daoSession.getGroupMemberDao().delete(new GroupMember(groupMember.getId(),groupId, personId));
            return groupMember;
        }else {
            return null;
        }
    }
}
