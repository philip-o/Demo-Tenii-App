package servlet;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ServletHelper {

    private static Set<Integer> validPostCodes = new HashSet<>();
    private static Set<Integer> validGetCodes = new HashSet<>();

    static {
        validPostCodes.add(200);
        validPostCodes.add(201);
        validPostCodes.add(308);
        validGetCodes.add(200);
    }

    public static String getRequest(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        if(validGetCodes.contains(response.getStatusLine().getStatusCode())) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        else
            throw new IOException("Status code of " + response.getStatusLine().getStatusCode());
    }

    public static String postRequest(String url, String payload) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(payload,
                ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(post);
        if(validPostCodes.contains(response.getStatusLine().getStatusCode())) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        else
            throw new IOException("Status code of " + response.getStatusLine().getStatusCode());
    }
}
