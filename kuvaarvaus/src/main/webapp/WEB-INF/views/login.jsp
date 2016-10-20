<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="google-site-verification" content="yCVU8hxdqy0nrTI9cGOX6v_y1e5D-CWd5Z-SMHE_VCw">
        <link rel="shortcut icon" href="/favicon.ico">

        <title>KuvaArvaus</title>
        <meta name="description" content="KuvaArvaus on visailua varten sivusto, jolla saa kuvasta peitettyjä automaattisesti osia sekä luotua uniikit toisiinsa yhdistämättömät osoitteet kuville.">
        <link rel="canonical" href="http://kuvaarvaus.gosu.fi/">

        <meta property="og:site_name" content="KuvaArvaus">
        <meta property="og:type" content="website">
        <meta property="og:title" content="KuvaArvaus">
        <meta property="og:description" content="KuvaArvaus on visailua varten sivusto, jolla saa kuvasta peitettyjä automaattisesti osia sekä luotua uniikit toisiinsa yhdistämättömät osoitteet kuville.">
        <meta property="og:url" content="http://kuvaarvaus.gosu.fi/">

        <meta name="twitter:card" content="summary">
        <meta name="twitter:title" content="KuvaArvaus">
        <meta name="twitter:description" content="KuvaArvaus on visailua varten sivusto, jolla saa kuvasta peitettyjä automaattisesti osia sekä luotua uniikit toisiinsa yhdistämättömät osoitteet kuville.">
        <meta name="twitter:url" content="http://kuvaarvaus.gosu.fi/">

        <script async="" src="//www.google-analytics.com/analytics.js"></script><script type="application/ld+json">
        {
          "@context": "http://schema.org",
          "@type": "Website",
          "publisher": "KuvaArvaus",
          "url": "http://kuvaarvaus.gosu.fi/",
          "description": "KuvaArvaus on visailua varten sivusto, jolla saa kuvasta peitettyjä automaattisesti osia sekä luotua uniikit toisiinsa yhdistämättömät osoitteet kuville."
        }
        </script>

        <title>KuvaArvaus - Sisäänkirjautuminen</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/public/login.css' />" />
        <script type="text/javascript" charset="UTF-8" src="<c:url value="/resources/public/login.js" />"></script>
        <script>
         (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
         (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
          })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
          ga('create', 'UA-58417001-6', 'auto');
          ga('send', 'pageview');
        </script>
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
