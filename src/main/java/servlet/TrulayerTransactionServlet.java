package servlet;

import builders.TellerRegistrationBuilder;
import builders.TrulayerTransactionBuilder;
import com.google.gson.Gson;
import dtos.Register;
import dtos.RoarType;
import dtos.trulayer.TrulayerTransaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrulayerTransactionServlet", urlPatterns = {"/tTransaction"})
public class TrulayerTransactionServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String token = req.getParameter("token");
        String accountId = req.getParameter("accountId");

        TrulayerTransactionBuilder builder = new TrulayerTransactionBuilder();
        TrulayerTransaction trans = builder
                .withAccountid(accountId)
                .buildObject();
        String url = postTransaction(trans, token);
        System.out.println("URL is: " + url);
        response.sendRedirect(url.replace("\"", ""));
    }

    private String postTransaction(TrulayerTransaction trans, String token) throws IOException {
        String url = "https://tenii-trulayer-api.herokuapp.com/transactions";
        return ServletHelper.postRequest(url, gson.toJson(trans), token);
    }
}
