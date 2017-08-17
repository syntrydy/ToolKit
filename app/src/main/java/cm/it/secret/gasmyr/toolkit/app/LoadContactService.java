package cm.it.secret.gasmyr.toolkit.app;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import cm.it.secret.gasmyr.toolkit.core.GreetingPerson;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;
import cm.it.secret.gasmyr.toolkit.service.GroupPersonServiceService;
import cm.it.secret.gasmyr.toolkit.service.GroupService;
import cm.it.secret.gasmyr.toolkit.service.PersonContactService;
import cm.it.secret.gasmyr.toolkit.util.PhoneUtils;

/**
 * Created by gasmyr.mougang on 6/5/17.
 */

public class LoadContactService extends IntentService {

    ToolKitApp toolKitApp;
    private GroupService groupService;
    private PersonContactService contactService;
    private GroupPersonServiceService groupPersonServiceService;

    public LoadContactService(String name) {
        super(name);
    }

    public LoadContactService() {
        super("LoadContactService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        init();
        loadContactIntoDatabase();
        setupDefaultGroupMembers();
    }

    private void loadContactIntoDatabase() {
        for (GreetingPerson person : PhoneUtils.getAllContact(getApplicationContext())) {
            contactService.addPerson(new PersonContact(person.getName(), person.getPhoneNumber()));
        }
    }

    private void setupDefaultGroupMembers() {
        for (Group group : groupService.getAllGroups()) {
            if (group.getName().equalsIgnoreCase(ToolkitConstants.TOOLKIT_GROUP_ORANGE)) {
                for (PersonContact person : contactService.getAllPerson()) {
                    if (PhoneUtils.isOrange(person.getPhone())) {
                        groupPersonServiceService.add(group.getId(), person.getId());
                    }
                }
            }
            if (group.getName().equalsIgnoreCase(ToolkitConstants.TOOLKIT_GROUP_MTN)) {
                for (PersonContact person : contactService.getAllPerson()) {
                    if (PhoneUtils.isMtn(person.getPhone())) {
                        groupPersonServiceService.add(group.getId(), person.getId());
                    }
                }
            }

        }
    }

    private void init() {
        toolKitApp = (ToolKitApp) getApplication();
        contactService = new PersonContactService(toolKitApp.getDaoSession());
        groupService = new GroupService(toolKitApp.getDaoSession());
        groupPersonServiceService = new GroupPersonServiceService(toolKitApp.getDaoSession());
    }
}
