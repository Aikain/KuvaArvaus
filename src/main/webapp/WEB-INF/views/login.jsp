<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KuvaArvaus - Sisäänkirjautuminen</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/public/login.css' />" />
        <script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/public/login.js" />"></script>
    </head>
    <body id="body">
        <center>
        <div id="logInCenterizingDiv">
            <h2 id="hObject">Kirjaudu sisään</h2>
            <form id="credentialsForm" method="POST" action="authenticate">
                <div id="nameDiv">
                    <input id="nameInput" type="text" name="username" placeholder="Käyttäjän nimi" />
                </div>
                <div id="passwdDiv">
                    <input id="passwdInput" type="password" name="password" placeholder="Salasana" />
                </div>
                <div id="logInButtonDiv">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input id="logInButton" type="submit" value="Kirjaudu" />
                </div>
            </form>
        </div>
        <a href="signup">Rekisteröidy!</a><br /><br />
        <a href="https://github.com/Aikain/KuvaArvaus">Github</a>
    </body>
</html>
