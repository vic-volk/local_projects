package ru.bmstu.test.eemodule.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: volkov
 * Date: 17.09.14
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationServlet extends HttpServlet {

    private String message;

    private String login;
    private String password;

    public void init() throws ServletException
    {
        // Do required initialization
        message = "Hello";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        login = request.getParameter("login");
        password = request.getParameter("password");

        if(checkRequest(login, password)){
            out.println(login);
            out.println(password);
            out.println(request.getSession());
        } else{
            out.println("Access denied!");
        }

    }

    private boolean checkRequest(String login, String password){
        if(login.equals("admin")&&password.equals("admin")){
            return true;
        } else {
            return false;
        }

    }

    private void setUserAuthorized(){

    }

    public void destroy()
    {
        // do nothing.
    }

}
