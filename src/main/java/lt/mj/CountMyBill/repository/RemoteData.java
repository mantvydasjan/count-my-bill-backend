package lt.mj.CountMyBill.repository;

import lombok.NoArgsConstructor;
import lt.mj.CountMyBill.config.RemoteDataConfig;
import lt.mj.CountMyBill.controller.PriceController;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Component
public class RemoteData {

    @Autowired
    public RemoteDataConfig remoteDataConfig;

    public double readData(String counterEndpointName) throws IOException {

        double value;
        Response response;
        Request request;
        OkHttpClient client = login();

        request = new Request.Builder()
                .url(remoteDataConfig.getIp() + "/sdcard/cpt/app/data_api.php?url=/app/objects/EasyIO/" + counterEndpointName + ".out")
                .method("GET", null)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Cookie", "CPTSESSID=47390923ce4a8f25de31f255b0d80cf7")
                .build();
        response = client.newCall(request).execute();
        String s = response.peekBody(2048).string();

        JSONObject obj = new JSONObject(s);

        JSONArray arr = obj.getJSONObject("response").getJSONArray("data");
        JSONArray path = arr.getJSONObject(0).getJSONArray("slots");
        String stringValue = path.getJSONObject(0).getString("value");
        value = Double.parseDouble(stringValue);
        return value;
    }

    private OkHttpClient login() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,
                "user[name]=" + remoteDataConfig.getUserName() +
                        "&user[password]=" + remoteDataConfig.getPassword());
        Request request = new Request.Builder()
                .url(remoteDataConfig.getIp() + "/sdcard/cpt/app/signin.php")
                .method("POST", body)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie", "CPTSESSID=47390923ce4a8f25de31f255b0d80cf7")
                .build();
        Response response = client.newCall(request).execute();
        response.close();

        return client;
    }

}
