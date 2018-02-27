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
<title>Mastermind</title>
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
            <li><a href="Mastermind?rules=true">How to play</a></li>
        </ul>
        </nav>
        
        <div class="leftcolumn">
            <p>Press "New Game" to start a new game.</p><br>
            <p>Press "Quit" to give up and see the hidden code.</p><br><br>
            <p>Press "Check Guess" to see if you have guessed the code.</p><br><br>
        </div> <!-- end left column -->
            
        <div class="rightcolumn">
            <form action="Mastermind" method="post">
                <input type="submit" value="New Game" name="newGameButton"/><br>
                <c:choose>
                    <c:when test="${gameOver eq 'true'}">
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Quit Game" name="quitGameButton"/>
                    </c:otherwise>
                </c:choose>                 
            </form>
            <br><br>
            <c:if test="${gameOver eq 'true'}">
                <c:choose>
                    <c:when test="${winner eq 'true'}">
                        <h3>Congratulations!</h3><br>
                        <h3>You correctly guessed the code.</h3>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${quitGame eq 'true'}">
                                <h3>You quit.</h3><br>
                                <h3>Better luck next time.</h3>
                            </c:when>
                            <c:otherwise>
                                <h3>You are out of guesses.</h3><br>
                                <h3>Better luck next time.</h3>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div> <!-- end right column -->

        <div class="content">
            <c:choose>
                <c:when test="${msg eq null}">
                    <h2>Welcome to Mastermind! Press "New Game" or "How to Play" to get started.</h2>
                </c:when>
                <c:otherwise>


                    <h3>Board</h3>
                    <form action="<c:url value="Mastermind"/>" method="post">
                        <table id="guesses">
                            <c:forEach var="guess" items="${guesses}" varStatus="guessStat">
                                <tr>
                                    <c:forEach var="codePeg" items="${guess}" varStatus="codePegStat">
                                        <td>
                                            <div id="${codePeg.color()}CodePegCircle">
                                                <span id="invisible">${codePeg.color()}</span>
                                            </div>
                                            <c:if test="${currentGuessNumber == guessStat.count}">
                                                <select name="pegGuess${codePegStat.index}">
                                                    <option value="red">Red</option>
                                                    <option value="blue">Blue</option>
                                                    <option value="green">Green</option>
                                                    <option value="yellow">Yellow</option>
                                                    <option value="purple">Purple</option>
                                                    <option value="orange">Orange</option>
                                                </select>
                                            </c:if>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                        <table id="hints">
                            <c:forEach var="hint" items="${hints}">
                                <tr>
                                    <td>
                                        <c:forEach var="currentHint" items="${hint}" varStatus="hintStat">
                                            <div id="${currentHint.color()}KeyPegCircle">
                                                <span id="invisible">${currentHint.value()}</span>
                                            </div>
                                            <c:if test="${hintStat.count % 2 == 0}">
                                                <br>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:choose>
                            <c:when test="${gameOver eq 'true'}">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Check Guess" name="checkGuessButton"/>
                            </c:otherwise>
                        </c:choose>
                    </form>
                    <table id="code">
                        <tr>
                            <c:forEach var="codePeg" items="${code}">
                                <td>
                                    <c:choose>
                                        <c:when test="${shield eq 'closed'}">
                                            <div id="blackCodePegCircle">
                                        </c:when>
                                        <c:otherwise>
                                            <div id="${codePeg.color()}CodePegCircle">
                                        </c:otherwise>
                                    </c:choose>
                                        <span id="invisible">${codePeg.color()}</span>
                                    </div>
                                </td>
                            </c:forEach>
                        </tr>
                    </table>
                </div>  
            </c:otherwise>
        </c:choose>
        </div> <!-- end content -->
        
        <footer class="clearboth">
       	<p>Copyright &copy; Blake Edwards 2014</p>
        <p>blakeedwards28@gmail.com</p>
        </footer>
    </div> <!-- end wrapper -->
</body>
</html>
