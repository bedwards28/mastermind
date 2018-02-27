<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : Mastermind
    Created on : Dec 3, 2014, 7:38:29 PM
    Author     : Blake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lan="eng">
<head>
<title>Mastermind : How to Play</title>
<meta charset="utf-8">
<link rel="stylesheet" href="reset.css">
<link rel="stylesheet" href="mastermind3column.css">
</head>
<body>
	<div id="wrapper">
        <header>
        <h1>Mastermind</h1>
        </header>
    
        <nav>
        <ul>
            <li><a href="Mastermind">Back to Game</a></li>
        </ul>
        </nav>
        
        <div class="leftcolumn">
        </div> <!-- end left column -->
            
        <div class="rightcolumn">
        </div> <!-- end right column -->

        <div class="content">
            <h3>Mastermind is a codebreaking game for one player.</h3>
            <br>
            <h3>The computer (codemaker) will select a pattern of four colored pegs. 
                The colors can be one of the following: red, blue, yellow, 
                green, purple, or orange.  Duplicate colors are allowed.</h3>
            <br>
            <h3>The player (codebreaker) will try to guess the pattern, in both order 
                and color, within twelve turns. Each guess is made by selecting 
                a row code pegs on the decoding board. Once placed, the 
                codemaker provides feedback by placing from zero to four 
                key pegs in the small holes of the row with the guess. 
                A black peg is placed for each code peg from the guess 
                which is correct in both color and position. A white key 
                peg indicates the existence of a correct color code peg 
                placed in the wrong position.</h3>
            <br>
            <h3>Once feedback is provided, another guess is made. Guesses 
                and feedback continue to alternate until either the 
                codebreaker guesses correctly or twelve incorrect guesses 
                are made.</h3>
            <br>
            <h3>Click "Back to Game" to return to your current game or 
                start a new game if you haven't started one yet.</h3>
        </div> <!-- end content -->
        
        <footer class="clearboth">
       	<p>Copyright &copy; Blake Edwards 2014</p>
        <p>blakeedwards28@gmail.com</p>
        </footer>
    </div> <!-- end wrapper -->
</body>
</html>
