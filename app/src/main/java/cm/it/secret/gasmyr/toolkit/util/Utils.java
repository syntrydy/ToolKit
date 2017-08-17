package cm.it.secret.gasmyr.toolkit.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import cm.it.secret.gasmyr.toolkit.app.SpeechService;
import cm.it.secret.gasmyr.toolkit.app.ToolkitConstants;
import cm.it.secret.gasmyr.toolkit.core.GreetingPerson;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class Utils {
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }
    public static void startSpeakerService(@NonNull Context context, String message) {
        Intent speakerServiceIntent = new Intent(context, SpeechService.class);
        speakerServiceIntent.putExtra(ToolkitConstants.SPEAKER_SERVICE_MESSAGE, message);
        speakerServiceIntent.putExtra(ToolkitConstants.SPEAKER_SERVICE_TARGET, false);
        context.startService(speakerServiceIntent);
    }
}
