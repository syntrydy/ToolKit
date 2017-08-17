package cm.it.secret.gasmyr.toolkit.apiaibot.webservices;

import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by gasmyr.mougang on 6/18/17.
 */

public interface ToolkitBotService {

    @Headers({"Authorization: Bearer "+ApiAiConstants.ACCESS8TOKEN})
    @GET("/message")
    public void sendMessage(String message);

}