package servlet;

import com.google.gson.Gson;
import dtos.trulayer.Account;
import dtos.trulayer.TrulayerAccounts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PostAuthServlet", urlPatterns = {"/postauth"})
public class PostAuthServlet extends HttpServlet {

    Gson gson = new Gson();
    String clientId = System.getenv("CLIENT_ID");
    String clientSecret = System.getenv("CLIENT_SECRET");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        String queryString = req.getQueryString();
        try {
            System.out.println("Query string is " + req.getQueryString());
            String jsonResponse = getAuthorisation(queryString);
            TrulayerAccounts accounts = gson.fromJson(jsonResponse, TrulayerAccounts.class);
            PrintWriter writer = response.getWriter();
            writer.append("<!DOCTYPE html>\r\n")
                    .append("<html>\r\n")
                    .append("		<head>\r\n")
                    .append("			<title>Accounts</title>\r\n")
                    .append("		</head>\r\n")
                    .append("		<body>\r\n")
                    .append("<center>");
            writer.append("<table border=\"1\"><tr><th>Provider</th><th>Sort Code</th><th>Account Number</th><th>Balance</th><th>Transactions</th></tr><tr>");
            accounts.getAccounts().forEach(
                    account -> writer.append("<tr><td>" + account.getProvider().getDisplay_name() + "</td>")
                            .append("<td>" + account.getAccount_number().getSort_code() + "</td>")
                            .append("<td>" + account.getAccount_number().getNumber() + "</td>")
                            .append("<td>" + account.getBalance() + "</td>")
                    .append(addTransactionForm(account, accounts.getAccessToken()))
            );
            writer.append("</table>");
            writer.append("</center>")
                    .append("		</body>\r\n")
                    .append("</html>\r\n");
        } catch (IOException ioe) {
            response.sendRedirect("register");
        }
    }


    private String getAuthorisation(String path) throws IOException {
        String url = "https://tenii-trulayer-api.herokuapp.com/postauth/callback?" + path;
        return ServletHelper.getRequest(url, null);
    }

    private String addTransactionForm(Account account, String token) {
        return "<td><form action=\"tTransaction\" method=\"POST\">" +
                "<input type=\"hidden\" name=\"token\" value=\"" + token + "\" />" +
                "<input type=\"hidden\" name=\"accountId\" value=\"" + account.getAccount_id() + "\" />" +
                "<input type=\"submit\" value=\"Transactions\" /></form>" +
                "</td></tr>";
    }

    private void createForm(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Redirecting</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n")
                .append("<center>");
        writer
                .append("<br/>")
                .append("<br/>");
        writer
                .append("<br/>")
                .append("<br/>")
                .append("			<form action=\"https://auth.truelayer.com/connect/token\" method=\"POST\">\r\n")
                .append("Redirecting\r\n")
                .append("<br/>")
                .append("<br/>")
                .append("<input type=\"hidden\" name=\"grant_type\" value=\"authorization_code\" />\r\n")
                .append("<input type=\"hidden\" name=\"client_id\" value=\"" + clientId + "\" />\r\n")
                .append("<input type=\"hidden\" name=\"client_secret\" value=\"" + clientSecret + "\" />\r\n")
                .append("<input type=\"hidden\" name=\"code\" value=\"" + request.getParameter("code") + "\" />\r\n")
                .append("<input type=\"hidden\" name=\"redirect_uri\" value=\"https://tenii-demo.herokuapp.com/postauth\" />\r\n")
                .append("<input type=\"submit\" value=\"Submit\" />\r\n")
                .append("			</form>\r\n")
                .append("</center>")
                .append("		</body>\r\n")
                .append("</html>\r\n");
    }
}
