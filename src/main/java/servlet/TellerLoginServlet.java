package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

@WebServlet(name = "TellerLoginServlet", urlPatterns = {"/tellerLogin"})
public class TellerLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        // set response headers
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Login</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n")
                .append("<center>")
                .append("			<form action=\"tLogin\" method=\"POST\">\r\n")
                .append("				Please enter your login details: \r\n")
                .append("<br/>")
                .append("<br/>")
                .append("Email: <input type=\"text\" name=\"email\" />\r\n")
                .append("<br/>")
                .append("<br/>")
                .append("Password: <input type=\"password\" name=\"password\" />\r\n")
                .append("<br/>")
                .append("<br/>")
                .append("<input type=\"hidden\" name=\"ipAddress\" value=\"" + ipAddress + "\" />\r\n")
                .append("<input type=\"submit\" value=\"Submit\" />\r\n")
                .append("			</form>\r\n")
                .append("</center>")
                .append("		</body>\r\n")
                .append("</html>\r\n");
    }
}