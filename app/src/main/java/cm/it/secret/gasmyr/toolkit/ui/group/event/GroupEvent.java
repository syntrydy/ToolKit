package cm.it.secret.gasmyr.toolkit.ui.group.event;

import cm.it.secret.gasmyr.toolkit.core.Group;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

public class GroupEvent {

    int code;
    Long groupId;
    private Group group;

    public GroupEvent(int code, Long groupId) {
        this.code = code;
        this.groupId = groupId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupEvent() {
    }
}
