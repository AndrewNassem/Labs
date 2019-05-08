<%-- 
    Document   : index
    Created on : May 8, 2019, 10:24:19 AM
    Author     : Andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Css.css" />
    </head>
    <body>
        <h1>The Number Quiz</h1>
        <p> Your current score is : ${totalScore}</p>
        <p>Guess The next number in the Sequence.</p>

        

        <p>${quiz.getQuestions()[questionN]}</p>
        <form action='QuizServlet' method='POST'>
            Your Answer :  <input type='text' name='value' size='5'/>
            <input type="submit" value="Submit"/>
        </form>

    </body>
</html>