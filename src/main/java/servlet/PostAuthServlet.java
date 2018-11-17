package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PostAuthServlet", urlPatterns = {"/postauth"})
public class PostAuthServlet extends HttpServlet {

    String clientId = System.getProperty("CLIENT_ID", "something");
    String clientSecret = System.getProperty("CLIENT_SECRET", "blabla");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        String queryString = req.getQueryString();
        try {
            System.out.println("Query string is " + req.getQueryString());
            //getAuthorisation(queryString);
            //response.sendRedirect("trulayerLogin");
            createForm(response, req);
        } catch (IOException ioe) {
            response.sendRedirect("register");
        }
    }


    private void getAuthorisation(String path) throws IOException {
        String url = "https://tenii-trulayer-api.herokuapp.com/postauth/callback?" + path;
        ServletHelper.getRequest(url);
    }

    private void createForm(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Redirecting</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n")
                .append("<center>");
        writer
                .append("<br/>")
                .append("<br/>");
        writer
                .append("<br/>")
                .append("<br/>")
                .append("			<form action=\"https://auth.truelayer.com/connect/token\" method=\"POST\">\r\n")
                .append("Redirecting\r\n")
                .append("<br/>")
                .append("<br/>")
                .append("<input type=\"text\" name=\"grant_type\" value=\"authorization_code\" />\r\n")
                .append("<input type=\"hidden\" name=\"client_id\" value=\"" + clientId + "\" />\r\n")
                .append("<input type=\"hidden\" name=\"client_secret\" value=\"" + clientSecret + "\" />\r\n")
                .append("<input type=\"hidden\" name=\"code\" value=\"" + request.getParameter("code") + "\" />\r\n")
                .append("<input type=\"hidden\" name=\"redirect_uri\" value=\"https://tenii-demo.herokuapp.com/postauth\" />\r\n")
                .append("<input type=\"submit\" value=\"Submit\" />\r\n")
                .append("			</form>\r\n")
                .append("</center>")
                .append("		</body>\r\n")
                .append("</html>\r\n");
    }
}
