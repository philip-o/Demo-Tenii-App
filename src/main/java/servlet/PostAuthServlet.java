package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PostAuthServlet", urlPatterns = {"/postAuth"})
public class PostAuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        String queryString = req.getQueryString();
        if(queryString != null) {
            try {
                System.out.println("Query string is " + req.getQueryString());
                getAuthorisation(queryString);
                response.sendRedirect("login");
            } catch (IOException ioe) {
                response.sendRedirect("register");
            }
        }
        else {
            System.out.println("Query string is " + req.getQueryString());
            String access_token = req.getParameter("access_token");
            String permissions = req.getParameter("permissions");
            getAuthorisation("access_token=" + access_token + "&permissions=" + permissions);
            response.sendRedirect("login");
        }
    }


    private void getAuthorisation(String path) throws IOException {
        String url = "https://tenii-customer-api.herokuapp.com/teller/postauth?" + path;
        ServletHelper.getRequest(url);
    }
}
