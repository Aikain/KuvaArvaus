<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>KuvaArvaus - Images</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="${_csrf.parameterName}" th:content="${_csrf.token}"/>

    <!--<script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery-2.1.1.min.js' />"></script>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery-latest.js' />"></script>-->

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/jquery.datetimepicker.min.css" />
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>

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
    <div id="dialog" title="Vaihda kulttuurien määrä">
      <p class="validateTips">Määritä kellon ajat:</p>
      <p><a id="singleLink"></a></p>
      <form>
        <label for="count">1. </label>
        <input class="datetimepicker" type="text" name="times['id']" id="halfImage-1" class="text ui-widget-content ui-corner-all"><br />
        <label for="count">2. </label>
        <input class="datetimepicker" type="text" name="times['id']" id="halfImage-2" class="text ui-widget-content ui-corner-all"><br />
        <label for="count">3. </label>
        <input class="datetimepicker" type="text" name="times['id']" id="halfImage-3" class="text ui-widget-content ui-corner-all"><br />
        <label for="count">4. </label>
        <input class="datetimepicker" type="text" name="times['id']" id="halfImage-4" class="text ui-widget-content ui-corner-all"><br />
        <label for="count">5. </label>
        <input class="datetimepicker" type="text" name="times['id']" id="halfImage-5" class="text ui-widget-content ui-corner-all"><br />
        <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
      </form>
    </div>
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
                <td style="width:120px">Nimi</td>
                <td style="width:160px">Luontiaika</td>
                <td>Poista</td>
                <td style="width:170px">1.</td>
                <td style="width:170px">2.</td>
                <td style="width:170px">3.</td>
                <td style="width:170px">4.</td>
                <td style="width:170px">5.</td>
              </tr>
            </head>
            <tbody>
              <c:forEach var="image" items="${images}">
                <tr>
                  <td><a href="images/${image.id}.png" onmouseover="showImage(this)">${image.name}</a></td>
                  <td><fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                  <td><button onclick="deleteImage(this, '${image.id}')">Poista!</button></td>
                  <c:forEach var="halfImage" items="${image.halfImages}">
                    <td class="halfImage">
                      <a href="images/halfImage/${halfImage.id}.png" onmouseover="showImage(this)">${halfImage.visibility}</a>
                      <span>
                        <c:if test="${halfImage.getSingleLinkTime() != null}"> (<fmt:formatDate value="${halfImage.getSingleLinkTime()}" pattern="yyyy-MM-dd HH:mm" />)</c:if>
                      </span>
                    </td>
                  </c:forEach>
                  <td><button onclick="addSingleLink(this, '${image.id}', '${image.singleLink.id}')">single</button></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <div id="pager">
            <form>
              <img src="/resources/private/icons/first.png" class="first"/>
              <img src="/resources/private/icons/prev.png" class="prev"/>
              <input type="text" class="pagedisplay" disabled/>
              <img src="/resources/private/icons/next.png" class="next"/>
              <img src="/resources/private/icons/last.png" class="last"/>
              <select class="pagesize">
                <option selected="selected" value="10">10</option>
                <option value="20">20</option>
                <option value="30">30</option>
                <option value="40">40</option>
              </select>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="footer">
      <center>
      Copyright © Aikain - gosu.fi
    </div>
  </body>
</html>
