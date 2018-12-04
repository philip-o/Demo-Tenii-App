package servlet;

import builders.TrulayerTransactionRequestBuilder;
import com.google.gson.Gson;
import dtos.trulayer.TrulayerTransaction;
import dtos.trulayer.TrulayerTransactionRequest;
import dtos.trulayer.TrulayerTransactions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TrulayerTransactionServlet", urlPatterns = {"/tTransaction"})
public class TrulayerTransactionServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String token = req.getParameter("token");
        String accountId = req.getParameter("accountId");

        TrulayerTransactionRequestBuilder builder = new TrulayerTransactionRequestBuilder();
        TrulayerTransactionRequest trans = builder
                .withAccountid(accountId)
                .buildObject();
        String transactions = getTransactions(trans, token);
        TrulayerTransactions truTrans = gson.fromJson(transactions, TrulayerTransactions.class);
        System.out.println("URL is: " + transactions);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Transactions</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n")
                .append("<center>")
                .append("<table border=\"1\"><tr><th>Date</th><th>Description</th><th>Amount</th><th>Type</th></tr><tr>");
        truTrans.getTransactions().forEach(tr -> writeTransaction(writer, tr));
        writer.append("</tr></table>");
        writer
                .append("<br/>")
                .append("<br/>")
                .append("</center>")
                .append("		</body>\r\n")
                .append("</html>\r\n");
    }

    private void writeTransaction(PrintWriter writer, TrulayerTransaction transaction) {

        writer.append("<td>" + transaction.getTimestamp() + "</td>");
        writer.append("<td>" + transaction.getDescription() + "</td>");
        writer.append("<td>" + transaction.getAmount() + "</td>");
        writer.append("<td>" + transaction.getTransaction_type() + "</td>");
    }

    private String getTransactions(TrulayerTransactionRequest trans, String token) throws IOException {
        String url = "https://tenii-trulayer-api.herokuapp.com/transactions/" + trans.getAccountId();
        return ServletHelper.getRequest(url, token);
    }
}
