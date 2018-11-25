package servlet;

import com.google.gson.Gson;
import dtos.BankAccount;
import dtos.Mortgage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(username == null || username.isEmpty()) {
            response.sendRedirect("login");
        }
        else {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            // create HTML form
            PrintWriter writer = response.getWriter();
            writer.append("<!DOCTYPE html>\r\n")
                    .append("<html>\r\n")
                    .append("		<head>\r\n")
                    .append("			<title>Accounts</title>\r\n")
                    .append("		</head>\r\n")
                    .append("		<body>\r\n")
                    .append("<center>");
                    loadAccount(writer, username);
                    writer
                            .append("<br/>")
                            .append("<br/>");
                    loadMortgage(writer, username);
                    writer
                            .append("<br/>")
                            .append("<br/>")
                            .append("			<form action=\"transaction\" method=\"POST\">\r\n")
                    .append("				Enter the details of your transaction to add to the bank account: \r\n")
                    .append("<br/>")
                    .append("<br/>")
                    .append("<input type=\"hidden\" name=\"userId\" value=\"" + username + "\" />\r\n")
                    .append("Vendor: <input type=\"text\" name=\"provider\" />\r\n")
                    .append("<input type=\"hidden\" name=\"sortCode\" value=\"20-66-55\" />\r\n")
                    .append("<input type=\"hidden\" name=\"accountNumber\" value=\"10522481\" />\r\n")
                    .append("Amount: <input type=\"text\" name=\"amount\" value=\"\" />\r\n")
                    .append("<input type=\"submit\" value=\"Submit\" />\r\n")
                    .append("			</form>\r\n")
                    .append("</center>")
                    .append("		</body>\r\n")
                    .append("</html>\r\n");
        }
    }

    private void loadAccount(PrintWriter writer, String username) throws IOException {
        String url = "https://tenii-products-api.herokuapp.com/bankAccount?userId=" + username;
        String result = ServletHelper.getRequest(url, null);
        BankAccount account = gson.fromJson(result, BankAccount.class);
        writer.append("<table border=\"1\"><tr><th>Provider</th><th>Sort Code</th><th>Account Number</th><th>Balance</th></tr><tr>");
        writer.append("<td>" + account.getProvider() + "</td>");
        writer.append("<td>" + account.getSortCode() + "</td>");
        writer.append("<td>" + account.getAccountNumber() + "</td>");
        writer.append("<td>" + account.getBalance() + "</td>");
        writer.append("</tr></table>");
    }


    private void loadMortgage(PrintWriter writer, String username) throws IOException {
        String url = "https://tenii-products-api.herokuapp.com/mortgage?userId=" + username;
        String result = ServletHelper.getRequest(url, null);
        Mortgage mortgage = gson.fromJson(result, Mortgage.class);
        writer.append("<table border=\"1\"><tr><th>Provider</th><th>Account Number</th><th>Mortgage Type</th><th>Monthly Payment</th><th>Rate</th><th>Balance</th></tr><tr>");
        writer.append("<td>" + mortgage.getProvider() + "</td>");
        writer.append("<td>" + mortgage.getAccountNumber() + "</td>");
        writer.append("<td>" + mortgage.getMortgageType() + "</td>");
        writer.append("<td>" + mortgage.getMonthlyPayment() + "</td>");
        writer.append("<td>" + mortgage.getRate() + "</td>");
        writer.append("<td>" + mortgage.getBalance() + "</td>");
        writer.append("</tr></table>");
    }
}
