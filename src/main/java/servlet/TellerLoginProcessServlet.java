package servlet;

import builders.LoginBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dtos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

@WebServlet(name = "TellerLoginProcessServlet", urlPatterns = {"/tLogin"})
public class TellerLoginProcessServlet extends HttpServlet {

    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<TellerAccount>>(){}.getType();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String ipAddress = req.getParameter("ipAddress");

        LoginBuilder builder = new LoginBuilder();
        Login request = builder
                .withPassword(password)
                .withEmail(email)
                .withIPAddress(ipAddress)
                .buildObject();
        String result = postLogin(request);
        TellerAccountsResponse res = gson.fromJson(result, TellerAccountsResponse.class);
        result = getPot(res.getId());
        Pot pot = gson.fromJson(result, PotResponse.class).getPot();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();

        writer.append("<!DOCTYPE html>\r\n");
        writer.append("<html>\r\n");
        writer.append("		<head>\r\n");
        writer.append("			<title>Accounts</title>\r\n");
        writer.append("		</head>\r\n");
        writer.append("		<body>\r\n");
        writer.append("<center>");
        writer.append("<table border=\"1\"><tr><th>Sort Code</th><th>Account Number</th><th>Balance</th></tr>");
        res.getAccounts().forEach(acc -> buildResponse(writer, acc));
        writer.append("</table>");
        writer.append("<br/>");
        writer.append("<br/>");
        writer.append("<table border=\"1\"><tr><th>Pot Amount</th><th>Limit</th></tr>");
        writer.append("<tr><td>" + pot.getAmount() + "</td><td>" + pot.getLimit() + "</td></tr>");
        writer.append("</table>");
        writer.append("</center>");
        writer.append("		</body>\r\n");
        writer.append("</html>\r\n");
    }

    private void buildResponse(PrintWriter writer, TellerAccount account){
        writer.append("<tr><td>" + account.getBank_code() + "</td>");
        writer.append("<td>" + account.getAccount_number() + "</td>");
        writer.append("<td>" + account.getBalance() + "</td></tr>");
    }

    private String postLogin(Login login) throws IOException {
        String url = "https://tenii-customer-api.herokuapp.com/tellerLogin";
        return ServletHelper.postRequest(url, gson.toJson(login), null);
    }

    private String getPot(String id) throws IOException {
        String url = "https://tenii-payments-api.herokuapp.com/teller/" + id;
        return ServletHelper.getRequest(url, null);
    }
}
