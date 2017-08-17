package cm.it.secret.gasmyr.toolkit.core;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

@Entity
public class GroupMember {
    @Id
    private Long id;
    private Long groupId;
    private Long personId;
    @Generated(hash = 137264532)
    public GroupMember(Long id, Long groupId, Long personId) {
        this.id = id;
        this.groupId = groupId;
        this.personId = personId;
    }

    public GroupMember(Long groupId, Long personId) {
        this.groupId = groupId;
        this.personId = personId;
    }
    @Generated(hash = 1668463032)
    public GroupMember() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGroupId() {
        return this.groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public Long getPersonId() {
        return this.personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
