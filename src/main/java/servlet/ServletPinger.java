package servlet;

import java.io.IOException;

public class ServletPinger {

    private static ServletPinger instance;

    private ServletPinger() {}

    public static ServletPinger getInstance() {
        if(instance == null)
            instance = new ServletPinger();
        return instance;
    }

    public static void pingServlets() {
        try {
            String url = "https://tenii-customer-api.herokuapp.com/ping";
            ServletHelper.getRequest(url, null);
            url = "https://tenii-products-api.herokuapp.com/ping";
            ServletHelper.getRequest(url, null);
            url = "https://tenii-payments-api.herokuapp.com/ping";
            ServletHelper.getRequest(url, null);
            url = "https://tenii-trulayer-api.herokuapp.com/ping";
            ServletHelper.getRequest(url, null);
            url = "https://tenii-student-loans-api.herokuapp.com/ping";
            ServletHelper.getRequest(url, null);
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
