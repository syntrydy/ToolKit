package cm.it.secret.gasmyr.toolkit.app;

import android.app.Application;
import android.support.annotation.ColorInt;

import com.evernote.android.job.JobManager;

import org.greenrobot.greendao.database.Database;

import cm.it.secret.gasmyr.toolkit.core.DaoMaster;
import cm.it.secret.gasmyr.toolkit.core.DaoSession;
import cm.it.secret.gasmyr.toolkit.core.jobs.ToolKitJobCreator;
import es.dmoral.toasty.Toasty;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class ToolKitApp extends Application {
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new ToolKitJobCreator());
        Toasty.Config.getInstance().apply();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "toolkit-db-encrypted" : "toolkit-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
