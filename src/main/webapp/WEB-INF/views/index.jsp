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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="${_csrf.parameterName}" th:content="${_csrf.token}"/>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery-2.1.1.min.js' />"></script>     
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/private/index.js' />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/private/index.css' />" />
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
    <div class="top">
      <h1>KuvaArvaus - Tervetuloa ${user.username}!</h1>
      <div class="topics">
        <div class="topic"><a href="images">Kuvat</a> | </div>
        <div class="topic"><form method="post" action="logout"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <a href="#" onclick="$(this).closest('form').submit()">Kirjaudu ulos</a></form></div>
      </div>
    </div>
    <div class="content">
      <div class="aihe">
        <div class="left">
          <div class="select">
            <input id="upload" type="file" name="upload" title="Lisää kuva" onchange="preview(this)" accept="image/png,image/jpg,image/jpeg">
            <button onclick="$('#upload')[0].click()">Valitse kuva!</button>
            <button onclick="preview($('#upload')[0])">Generoi uusiksi</button>
          </div>
          <div class="send">
            <p id="msg">Valitse ja tarkasta kuva ennen lähettämistä!</p>
            <button onclick="sendImages()">Lähetä!</button>
          </div>
          <canvas id="preview" width="512px" height="512px"></canvas>
        </div>
        <div class="right">
          <center>
          <div class="halfImages" id="halfImages"></div>
          </center>
        </div>
      </div>
    </div>
    <div class="footer">
      <center>
      Copyright © Aikain - gosu.fi 
    </div>
  </body>
</html>
