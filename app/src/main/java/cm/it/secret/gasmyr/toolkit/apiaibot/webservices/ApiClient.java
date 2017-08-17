package cm.it.secret.gasmyr.toolkit.apiaibot.webservices;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by gasmyr.mougang on 6/18/17.
 */

public class ApiClient {
    private static Retrofit retrofit = null;
   public  static Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiAiConstants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
