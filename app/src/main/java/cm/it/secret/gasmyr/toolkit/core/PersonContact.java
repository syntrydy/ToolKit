package cm.it.secret.gasmyr.toolkit.core;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gasmyr.mougang on 6/3/17.
 */
@Entity(indexes = {})
public class PersonContact {

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @Generated(hash = 557590507)
    public PersonContact(Long id, @NotNull String name, @NotNull String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public PersonContact(@NotNull String name, @NotNull String phone) {
        this.name = name;
        this.phone = phone;
    }
    @Generated(hash = 601771127)
    public PersonContact() {
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
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
