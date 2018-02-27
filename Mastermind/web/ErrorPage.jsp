<%-- 
    Document   : ErrorPage
    Created on : Oct 25, 2014, 11:16:09 AM
    Author     : Blake
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="true"%>
<link rel="stylesheet" href="errors.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mastermind : Error Page</title>
    </head>
    <body>
        <h1>Oops, you have found a glitch in the Matrix!</h1>
        <img src="images/Matrix.jpg" width="800" height="600" alt="Matrix"/>
        <h1>Take the blue pill to forget what you saw</h1>
        <form action="<c:url value="Mastermind"/>" method="post">
            <input type="image" src="images/BluePill.jpg" width="160" height="56" 
               alt="BluePill" name="newGameButton">
        </form>
    </body>
</html>
