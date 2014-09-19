<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>EEModule</title>

      <style>

          #loginForm{

              border: 1px solid gray;
              width: 20%;
              align: center;

          }

      </style>

  </head>
  <body>
        <h2>EEModule</h2>
        <a href="/eemodule/EEModule">test ee</a>
        <form name="myform" action="/eemodule/AuthorizationServlet" method="GET">
            <div id="loginForm">
                <br><br>
                Login    <input id="login" type="text" size="25" value="">
                <br>
                Password <input id="password" type="password" size="25" value="">
                <br>
                <input type="submit" id="submit" value="log in">
                <br>
            </div>
        </form>
  </body>
</html>