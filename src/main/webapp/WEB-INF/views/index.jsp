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
        <header>
            <div class="logout">
                <form method="post" action="logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<!--                        <input type="image" src="<c:url value='/resources/private/logout.png' />" alt="Logout" />-->
                    <input type="image" width="50px" height="50px" src="https://yt3.ggpht.com/-gjxoCu8Fu3c/AAAAAAAAAAI/AAAAAAAAAAA/Uji17DdykF4/s100-c-k-no/photo.jpg" alt="Logout" />
                </form>
            </div>
            <h1>KuvaArvaus - Tervetuloa ${user.username}!</h1>
            <h2><a href="images">Kuvat</a></h2>
        </header>
        <div class="top">
            <div class="mainImage">
                <canvas id="preview" width="512px" height="512px"></canvas>
            </div>
            <div class="control">
                <div class="imageControl">
                    <input id="upload" type="file" name="upload" title="Lisää kuva" onchange="preview(this)" accept="image/png,image/jpg,image/jpeg">
                    <button onclick="sendImages()">Lähetä!</button>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="halfImages" id="halfImages">

            </div>
        </div>
        <footer>
            Copyright © Aikain - gosu.fi 
        </footer>
    </body>
</html>
