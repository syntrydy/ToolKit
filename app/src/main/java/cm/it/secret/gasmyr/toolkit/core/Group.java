package cm.it.secret.gasmyr.toolkit.core;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.DaoException;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */

@Entity(indexes = {
        @Index(value = "name, date DESC", unique = true)
})
public class Group {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Index(unique = true)
    private String name;
    private String description;
    private Date date;
    private String helloDayMessage;
    private String helloNightMessage;
    @ToMany
    @JoinEntity(
            entity = GroupMember.class,
            sourceProperty = "groupId",
            targetProperty = "personId"
    )
    private List<PersonContact> peoples;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1591306109)
    private transient GroupDao myDao;


    @Generated(hash = 145008907)
    public Group(Long id, @NotNull String name, String description, Date date,
                 String helloDayMessage, String helloNightMessage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.helloDayMessage = helloDayMessage;
        this.helloNightMessage = helloNightMessage;
    }

    public Group(
            String helloDayMessage, String helloNightMessage) {
        this.helloDayMessage = helloDayMessage;
        this.helloNightMessage = helloNightMessage;
    }

    public Group(@NotNull String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Generated(hash = 117982048)
    public Group() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1121069605)
    public List<PersonContact> getPeoples() {
        if (peoples == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonContactDao targetDao = daoSession.getPersonContactDao();
            List<PersonContact> peoplesNew = targetDao._queryGroup_Peoples(id);
            synchronized (this) {
                if (peoples == null) {
                    peoples = peoplesNew;
                }
            }
        }
        return peoples;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1540104093)
    public synchronized void resetPeoples() {
        peoples = null;
    }

    public synchronized boolean removePeople(PersonContact personContact) {
        return peoples.remove(personContact);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String getHelloDayMessage() {
        return this.helloDayMessage;
    }

    public void setHelloDayMessage(String helloDayMessage) {
        this.helloDayMessage = helloDayMessage;
    }

    public String getHelloNightMessage() {
        return this.helloNightMessage;
    }

    public void setHelloNightMessage(String helloNightMessage) {
        this.helloNightMessage = helloNightMessage;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1333602095)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGroupDao() : null;
    }
}
