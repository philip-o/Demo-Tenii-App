package servlet;

import com.google.gson.Gson;
import dtos.SourceBankAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SourceBankServlet", urlPatterns = {"/sourceBank"})
public class SourceBankServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String user = req.getParameter("user");
        String accountId = req.getParameter("accountId");

        SourceBankAccount account = new SourceBankAccount();
        account.setAccountIds(accountId);
        account.setTeniiId(user);
        String resp = postAccount(account);
        System.out.println("Response is: " + resp);
        response.sendRedirect("login");
    }

    private String postAccount(SourceBankAccount account) throws IOException {
        String url = "https://tenii-products-api.herokuapp.com/bankAccount";
        return ServletHelper.postRequest(url, gson.toJson(account), null);
    }
}
