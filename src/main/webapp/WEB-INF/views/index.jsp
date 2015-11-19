<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>KuvaArvaus</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="${_csrf.parameterName}" th:content="${_csrf.token}"/>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery-2.1.1.min.js' />"></script>     
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/private/index.js' />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/private/index.css' />" />
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
