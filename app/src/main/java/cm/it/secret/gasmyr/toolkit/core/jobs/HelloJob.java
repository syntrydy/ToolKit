package cm.it.secret.gasmyr.toolkit.core.jobs;

import android.os.Handler;
import android.os.Looper;

import com.evernote.android.job.JobRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cm.it.secret.gasmyr.toolkit.app.ToolkitConstants;
import cm.it.secret.gasmyr.toolkit.core.Group;
import cm.it.secret.gasmyr.toolkit.core.PersonContact;
import cm.it.secret.gasmyr.toolkit.util.DateUtils;
import cm.it.secret.gasmyr.toolkit.util.PhoneUtils;
import cm.it.secret.gasmyr.toolkit.util.Utils;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class HelloJob extends ToolKitJob {
    static final String TAG = "toolkit_send_greeting_sms";

    @Override
    protected Result onRunJob(Params params) {
        if (DateUtils.isTimeForHelloDay()) {
            notifyMeBefore("GASMYR GASMYR OPÉRATION TANGO DECLENCHER");
            sendGoodMorningSmsToOrangeGroup();
        }
        if (DateUtils.isTimeForHelloNight()) {
            notifyMeBefore("GASMYR  GASMYR  L' OPERATION  TANGO  DEUX VIENT DE COMMENCER.");
            sendHelloNightToOrangeGroup();
        }
        return Result.SUCCESS;
    }


    private void sendGoodMorningSmsToOrangeGroup() {
        Set<PersonContact> personContacts = new HashSet<>();
        if (getSharedPreferences().getBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_DAY_IS_ENABLE, false)) {
            List<Group> groups = getDaoSession().getGroupDao().loadAll();
            for (Group group : groups) {
                if (group.getName().equalsIgnoreCase(ToolkitConstants.TOOLKIT_GROUP_ORANGE)) {
                    personContacts = new HashSet<>(group.getPeoples());
                    break;
                }
            }
            for (final PersonContact person : personContacts) {
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PhoneUtils.sendSmsSilent(person.getPhone(),
                                "Bonjour " + PhoneUtils.getContactName(getContext(), person.getPhone()) + " J'espère que vous allez bien. Bonne journée. ", getContext());
                    }
                }, 5000);
            }
        }
    }

    private void sendHelloNightToOrangeGroup() {
        Set<PersonContact> personContacts = new HashSet<>();
        if (getSharedPreferences().getBoolean(ToolkitConstants.TOOLKIT_ORANGE_HELLO_NIGHT_IS_ENABLE, false)) {
            List<Group> groups = getDaoSession().getGroupDao().loadAll();
            for (Group group : groups) {
                if (group.getName().equalsIgnoreCase(ToolkitConstants.TOOLKIT_GROUP_ORANGE)) {
                    personContacts = new HashSet<>(group.getPeoples());
                    break;
                }
            }

            for (final PersonContact person : personContacts) {
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PhoneUtils.sendSmsSilent(person.getPhone(), "Bonsoir " + PhoneUtils.getContactName(getContext(), person.getPhone()) + ", " +
                                "Le weekend est terminé, je vous souhaite de passer une semaine paisible. Merci! ", getContext());
                    }
                }, 5000);

            }
        }
    }

    public static int schedulePeriodic() {
        int id = new JobRequest.Builder(HelloJob.TAG)
                .setExact(TimeUnit.MINUTES.toMillis(15))
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build()
                .schedule();
        return id;
    }

    private void notifyMeBefore(String message) {
        Utils.startSpeakerService(getContext(), message);
    }

    @Override
    protected void saveId() {
    }

    @Override
    protected void retrieveId() {
    }


}
