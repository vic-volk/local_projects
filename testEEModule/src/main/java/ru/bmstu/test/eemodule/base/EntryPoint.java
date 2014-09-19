package ru.bmstu.test.eemodule.base;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class EntryPoint extends HttpServlet {

    private String message;
    private int SIZE = 16000;

    public void init() throws ServletException
    {
        // Do required initialization
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        out.println("{");
        for(int i = 0; i < SIZE; i++){
            out.println(i + ":" + Math.pow(i*Math.PI + 9847252, Math.E) + ",");
        }
        out.println("}");
    }

    public void destroy()
    {
        // do nothing.
    }
}
