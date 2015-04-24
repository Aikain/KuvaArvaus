<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KuvaArvaus - Images</title>
        <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/libraries/jquery-2.1.1.min.js' />"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/private/images.css' />" />
        <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/private/images.js' />"></script>
    </head>
    <body>
        <a href="index">Takaisin</a>
        <div style="width:512px;height:512px;border: 2px solid">
            <img id="img" />
        </div>
        <div>
            <ul>
                <c:forEach var="image" items="${images}">    
                    <li><a href="images/${image.id}.png" onmouseover="showImage(this)">${image.id}</a></li>
                    <ul>
                        <c:forEach var="halfImage" items="${image.halfImages}">
                            <li><a href="images/halfImage/${halfImage.id}.png" onmouseover="showImage(this)">${halfImage.visibility} - ${halfImage.id}</a></li>
                            </c:forEach>
                    </ul>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>