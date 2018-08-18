package servlet;

import com.google.gson.Gson;
import dtos.Transaction;
import dtos.TransactionBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TransactionServlet", urlPatterns = {"/transaction"})
public class TransactionServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String provider = req.getParameter("provider");
        String sortCode = req.getParameter("sortCode");
        String accountNumber = req.getParameter("accountNumber");
        double amount = Double.valueOf(req.getParameter("amount"));

        TransactionBuilder builder = new TransactionBuilder();
        Transaction transaction = builder
                .withUserId(userId)
                .withProvider(provider)
                .withSortCode(sortCode)
                .withAccountNumber(accountNumber)
                .withAmount(amount)
                .buildObject();
        postTransaction(transaction);
        response.sendRedirect(response.encodeRedirectURL("account?username=" + userId ));

    }

    private void postTransaction(Transaction transaction) throws IOException {
        String url = "https://tenii-products-api.herokuapp.com/transaction";
        String result = ServletHelper.postRequest(url, gson.toJson(transaction));

    }
}
