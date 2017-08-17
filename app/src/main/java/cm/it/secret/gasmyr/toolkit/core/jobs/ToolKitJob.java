package cm.it.secret.gasmyr.toolkit.core.jobs;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;

import org.greenrobot.greendao.database.Database;

import java.util.Random;

import cm.it.secret.gasmyr.toolkit.MainActivity;
import cm.it.secret.gasmyr.toolkit.R;
import cm.it.secret.gasmyr.toolkit.app.ToolkitConstants;
import cm.it.secret.gasmyr.toolkit.core.DaoMaster;
import cm.it.secret.gasmyr.toolkit.core.DaoSession;
import es.dmoral.toasty.Toasty;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public abstract class ToolKitJob extends Job {
    public static final boolean ENCRYPTED = false;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int id;

    abstract protected Result onRunJob(Params params);

    abstract protected void saveId();

    abstract protected void retrieveId();

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(), MainActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentTitle("TookKitApp")
                .setContentText("Simple Notification.")
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowWhen(true)
                .setColor(Color.RED)
                .setLocalOnly(true)
                .build();
        NotificationManagerCompat.from(getContext())
                .notify(new Random().nextInt(), notification);
    }

    public DaoSession getDaoSession() {
        JobManager.create(getContext()).addJobCreator(new ToolKitJobCreator());
        Toasty.Config.getInstance().apply();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), ENCRYPTED ? "toolkit-db-encrypted" : "toolkit-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    public SharedPreferences getSharedPreferences() {
        sharedPreferences = getContext().getSharedPreferences(ToolkitConstants.TOOLKIT_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        editor = sharedPreferences.edit();
        return editor;
    }
}
