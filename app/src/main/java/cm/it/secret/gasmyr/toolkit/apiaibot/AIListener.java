package cm.it.secret.gasmyr.toolkit.apiaibot;

import ai.api.model.AIError;
import ai.api.model.AIResponse;

/**
 * Created by gasmyr.mougang on 6/18/17.
 */

public interface AIListener {
    void onResult(AIResponse result); // here process response
    void onError(AIError error); // here process error
    void onAudioLevel(float level); // callback for sound level visualization
    void onListeningStarted(); // indicate start listening here
    void onListeningCanceled(); // indicate stop listening here
    void onListeningFinished(); // indicate stop listening here
}
