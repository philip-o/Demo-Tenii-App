package servlet;

import com.google.gson.Gson;
import dtos.Pot;
import dtos.PotResponse;
import dtos.SourceBankAccount;
import dtos.trulayer.Account;
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
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class ServletHelper {

    private static Gson gson = new Gson();

    private static Set<Integer> validPostCodes = new HashSet<>();
    private static Set<Integer> validGetCodes = new HashSet<>();

    static {
        validPostCodes.add(200);
        validPostCodes.add(201);
        validPostCodes.add(308);
        validGetCodes.add(200);
    }

    public static String getRequest(String url, String tokenHeader) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        if(tokenHeader != null)
            get.addHeader("token", tokenHeader);
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

    public static String postRequest(String url, String payload, String tokenHeader) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        if(tokenHeader != null)
            post.addHeader("token", tokenHeader);
        post.setEntity(new StringEntity(payload,
                ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(post);
        if(validPostCodes.contains(response.getStatusLine().getStatusCode())) {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        else
            throw new IOException("Status code of " + response.getStatusLine().getStatusCode());
    }

    public static String addTransactionForm(Account account, String token) {
        return "<td><form action=\"tTransaction\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"token\" value=\"" + token + "\" />" +
                "<input type=\"hidden\" name=\"accountId\" value=\"" + account.getAccount_id() + "\" />" +
                "<input type=\"submit\" value=\"Transactions\" /></form>" +
                "</td></tr>";
    }

    public static void getPot(String id, PrintWriter writer) throws IOException {
        String url = "https://tenii-payments-api.herokuapp.com/pot/" + id + "/balance";
        Pot pot = gson.fromJson(ServletHelper.getRequest(url, null), PotResponse.class).getPot();
        writer.append("<br/>");
        writer.append("<br/>");
        writer.append("<table border=\"1\"><tr><th>Pot Amount</th><th>Limit</th></tr>");
        writer.append("<tr><td>" + pot.getAmount() + "</td><td>" + pot.getLimit() + "</td></tr>");
        writer.append("</table>");
    }

    public static void checkForSourceBank(String id, PrintWriter writer, List<Account> accounts) throws IOException {
        String url = "https://tenii-products-api.herokuapp.com/bankAccount/" + id;
        SourceBankAccount account = gson.fromJson(ServletHelper.getRequest(url, null), SourceBankAccount.class);
        if(!Optional.ofNullable(account.getAccountId()).isPresent()) {
            writer.append("<br/>");
            writer.append("Set Source bank account");
            writer.append("<br/>");
            writer.append("<br/>");
            writer.append("<table border=\"1\"><tr><th>Account Number</th><th>Sort Code</th><th>Set as Source</th></tr>");
            accounts.stream().map(acc -> writer.append("<tr><td>" + acc.getAccount_number().getNumber() +
                    "</td><td>" + acc.getAccount_number().getSort_code() + "</td><td>" + addAccountForm(id, acc.getAccount_id()) + "</td></tr>"));
            writer.append("</table>");
        }
    }

    private static String addAccountForm(String userId, String accountId) {
        String line = "<form action=\"something\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"user\" value=\"" + userId + "\" />" +
                "<input type=\"hidden\" name=\"accountId\" value=\"" + accountId + "\" />" +
                "<input type=\"submit\" value=\"Set as Source Account\" /></form>";
        System.out.println(line);
        return line;
    }
}
