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
                    .append(ServletHelper.addTransactionForm(account, accounts.getTeniiId()))
            );
            writer.append("</table>");
            ServletHelper.getPot(accounts.getTeniiId(), writer);
            accounts.getAccounts().forEach(acc -> System.out.println("Account: " + acc.getAccount_id()));
            ServletHelper.checkForSourceBank(accounts.getTeniiId(), writer, accounts.getAccounts());
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
}
