<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>KuvaArvaus - Images</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="${_csrf.parameterName}" th:content="${_csrf.token}"/>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery-2.1.1.min.js' />"></script>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery.tablesorter.min.js' />"></script>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery.tablesorter.pager.js' />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/private/index.css' />" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/private/images.css' />" />
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/private/images.js' />"></script>
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
      <h1>KuvaArvaus</h1>
      <div class="topics">
        <div class="topic"><a href="index">Etusivu</a> | </div>
        <div class="topic"><a href="index">Kirjaudu ulos</a></div>
      </div>
    </div>
    <div class="content">
      <center>
      <div class="aihe">
        <div class="left">
          <img id="img" />
        </div>
        <div class="right">
          <table class="tablesorter">
            <thead>
              <tr>
                <td style="width:200px">Nimi</td>
                <td style="width:200px">Luontiaika</td>
                <td>Poista</td>
                <td>1.</td>
                <td>2.</td>
                <td>3.</td>
                <td>4.</td>
                <td>5.</td>
              </tr>
            </head>
            <tbody>
              <c:forEach var="image" items="${images}">
                <tr>
                 <td><a href="images/${image.id}.png" onmouseover="showImage(this)">${image.name}</a></td><td>${image.createTime}</td><td><button onclick="deleteImage(this, '${image.id}')">Poista!</button></td>
                  <c:forEach var="halfImage" items="${image.halfImages}">
                    <td><a href="images/halfImage/${halfImage.id}.png" onmouseover="showImage(this)">${halfImage.visibility}</a></td>
                  </c:forEach>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="footer">
      <center>
      Copyright © Aikain - gosu.fi
    </div>
  </body>
</html>
