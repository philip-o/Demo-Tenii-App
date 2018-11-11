package servlet;

import builders.TellerRegistrationBuilder;
import com.google.gson.Gson;
import dtos.Register;
import dtos.RoarType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrulayerRegistrationServlet", urlPatterns = {"/truRegistration"})
public class TrulayerRegistrationServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String forename = req.getParameter("forename");
        String surname = req.getParameter("surname");
        String dob = req.getParameter("dob");
        String password = req.getParameter("password");
        int amount = Integer.valueOf(req.getParameter("roarType"));
        String mobile = req.getParameter("mobile");
        String email = req.getParameter("email");
        String ipAddress = req.getParameter("ipAddress");

        RoarType roarType = new RoarType();
        roarType.setLimit(amount);
        roarType.setRoar("BALANCED");

        TellerRegistrationBuilder builder = new TellerRegistrationBuilder();
        Register register = builder
                .withTitle("Mr")
                .withForename(forename)
                .withSurname(surname)
                .withDOB(dob)
                .withPassword(password)
                .withRoarType(roarType)
                .withMobile(mobile)
                .withEmail(email)
                .withIPAddress(ipAddress)
                .buildObject();
        String url = postTransaction(register);
        System.out.println("URL is: " + url);
        response.sendRedirect(url.replace("\"", ""));
    }

    private String postTransaction(Register register) throws IOException {
        String url = "https://tenii-customer-api.herokuapp.com/trulayer";
        return ServletHelper.postRequest(url, gson.toJson(register));
    }
}
