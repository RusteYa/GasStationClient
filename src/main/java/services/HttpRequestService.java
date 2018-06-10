package services;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class HttpRequestService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String LOCAL_ADDRESS = "http://localhost:8080/client/";
    private static final String REMOTE_ADDRESS = "https://gas-station-itis.herokuapp.com/client/";

    private static OkHttpClient client = new OkHttpClient();

    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(REMOTE_ADDRESS + url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String resp = Objects.requireNonNull(response.body()).string();
        System.out.println(resp);
        return resp;
    }

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(REMOTE_ADDRESS + url)
                .build();
        Response response = client.newCall(request).execute();
        String resp = Objects.requireNonNull(response.body()).string();
        System.out.println(resp);
        return resp;
    }
}