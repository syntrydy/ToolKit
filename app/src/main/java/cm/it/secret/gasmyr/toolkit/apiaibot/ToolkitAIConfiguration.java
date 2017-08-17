package cm.it.secret.gasmyr.toolkit.apiaibot;

import android.content.Context;

import ai.api.android.AIConfiguration;
import ai.api.android.AIService;

/**
 * Created by gasmyr.mougang on 6/18/17.
 */

public class ToolkitAIConfiguration {
    public static final String CLIENT_ACCESS_TOKEN= "98190228af02457ca484b6cfdba7db60";
    private final AIConfiguration configuration=new AIConfiguration(CLIENT_ACCESS_TOKEN,
            AIConfiguration.SupportedLanguages.French,
            AIConfiguration.RecognitionEngine.System);
    private AIService aiService;

    public AIConfiguration getConfig() {
        return configuration;
    }
    private AIService getAiService(Context context){
        aiService=AIService.getService(context, configuration);
        return aiService;
    }


}
