package cm.it.secret.gasmyr.toolkit.core.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import cm.it.secret.gasmyr.toolkit.util.DateUtils;
import cm.it.secret.gasmyr.toolkit.util.Utils;

/**
 * Created by gasmyr.mougang on 5/31/17.
 */

public class MyGreeterJob extends ToolKitJob {

    static final String TAG = "toolkit_my_greeting";

    @Override
    protected Job.Result onRunJob(Job.Params params) {
        if(DateUtils.isMorning()){
            notifyMeBefore("BONJOUR GASMYR RAPPELLE DES OBJECTIFD DU JOUR");
        }
        if(DateUtils.isAfternoon()){
            notifyMeBefore("GASMYR GASMYRRRRRR AS TU RESPECTER LE CODE 22");
        }
        if(DateUtils.isEvening()){
            notifyMeBefore("J'AI PAS ENCORE REÇU VOTRE TAUX DE PROGRESSION");
        }
        if(DateUtils.isNight()){
            notifyMeBefore("BONNE NUIT GASMYR! FAUT PRIER");
        }
        return Result.SUCCESS;
    }
    public static int schedulePeriodic() {
        int id = new JobRequest.Builder(HelloJob.TAG)
                .setExact(TimeUnit.HOURS.toMillis(1))
                .setPersisted(true)
                .build()
                .schedule();
        return id;
    }

    private void notifyMeBefore(String message){
        Utils.startSpeakerService(getContext(),message);
    }

    @Override
    protected void saveId() {
    }

    @Override
    protected void retrieveId() {
    }

}
