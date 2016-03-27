<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KuvaArvaus - Rekisteröinti</title>
        <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/public/signUp.js' />"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/public/signUp.css' />" />
        <script>
          (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
          (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
          })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
          ga('create', 'UA-58417001-6', 'auto');
          ga('send', 'pageview');
        </script>
    </head>
    <body>
        <center>
        <div id="signInCenterizingDiv">
            <h2>Rekisteröi tili</h2>
            <form method="POST" action="users">
                <div id="outerDiv">
                    <div>
                        <input id="nameInput" type="text" name="username" placeholder="Nimi" />
                    </div>
                    <div>
                        <input type="password" name="password" placeholder="Salasana" />
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input id="signInButton" type="submit" value="Rekisteröi" />
                </div>
            </form>
        </div>
        <a href='login'>Kirjaudu sisään!</a><br /><br />
        <a href="https://github.com/Aikain/KuvaArvaus">Github</a>
    </body>
</html>
