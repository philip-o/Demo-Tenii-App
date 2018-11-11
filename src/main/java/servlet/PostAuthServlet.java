package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PostAuthServlet", urlPatterns = {"/postauth"})
public class PostAuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        String queryString = req.getQueryString();
        try {
            System.out.println("Query string is " + req.getQueryString());
            getAuthorisation(queryString);
            response.sendRedirect("trulayerLogin");
        } catch (IOException ioe) {
            response.sendRedirect("register");
        }
    }


    private void getAuthorisation(String path) throws IOException {
        String url = "https://tenii-trulayer-api.herokuapp.com/postauth/callback?" + path;
        ServletHelper.getRequest(url);
    }
}
