<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Admin Page</title>
        <link rel="stylesheet" href="../../../admin.css">
        <link rel="shortcut icon" href="/img/footgo-dark-icon.png" type="image/x-icon">
        <script src="/submissionAll.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="admin-main">
            <div class="admin-logo">
                <img class="admin-img" src="/img/footgo-dark-icon.png" alt="logo">
            </div>
        </div>
        <div class="requests">
            <p>Заявки</p>
            <c:choose>
                <c:when test="${submissions.size()>0}">
					<div class="all-requests">
					<c:forEach items="${submissions}" var="item">
						<a class="link-style" href="/admin/submission/edit?uuid=${item.id}">
							<div class="request-block">
								<h3>${item.teamName}</h3>
								<p>${item.captainName}</p>
							</div>
						</a>
					 </c:forEach>
					</div>
                </c:when>
                <c:otherwise>
                 	<h3>Наразі немає жодної заявки на участь</h3>
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>