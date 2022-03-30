<%-- 
    Document   : index.jsp
    Created on : Dec 13, 2021, 3:04:31 PM
    Author     : kdenn
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Entry form</h1><!-- comment -->

        <form name="Puzzle Settings" action="generate">
            Difficulty: <input type="text" name="difficulty"/><br><br><!-- Difficulty of sudoku puzzle -->
            Dimension: <input type ="text" name="dimension"/><br><br><!-- Dimension of puzzle -->
            <input type="submit" value="Generate" />
        </form>
        ${grid}
    </body>
</html>
