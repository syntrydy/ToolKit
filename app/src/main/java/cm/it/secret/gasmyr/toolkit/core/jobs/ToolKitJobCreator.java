package cm.it.secret.gasmyr.toolkit.core.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class ToolKitJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag) {
            case HelloJob.TAG:
                return new HelloJob();
            case MyGreeterJob.TAG:
                return new MyGreeterJob();
            default:
                return null;
        }
    }
}
