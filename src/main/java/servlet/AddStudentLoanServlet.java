package servlet;

import com.google.gson.Gson;
import dtos.SourceBankAccount;
import dtos.StudentLoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddStudentLoanServlet", urlPatterns = {"/addStudentLoan"})
public class AddStudentLoanServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //userId: String, accountId: String, balance: Double, rate: Double, password: String
        String userId = req.getParameter("userId");
        String accountId = req.getParameter("accountId");
        double balance = Double.valueOf(req.getParameter("balance"));
        StudentLoan loan = new StudentLoan();

        SourceBankAccount account = new SourceBankAccount();
        account.setAccountId(accountId);
        account.setTeniiId(userId);
        String resp = postAccount(account);
        System.out.println("Response is: " + resp);
        response.sendRedirect("login");
    }

    private String postAccount(SourceBankAccount account) throws IOException {
        String url = "https://tenii-student-loans-api.herokuapp.com/bankAccount";
        return ServletHelper.postRequest(url, gson.toJson(account), null);
    }
}
