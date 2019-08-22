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
            <!--<div class="admin-bar">-->
                <!--<p>FootGo</p>-->

                <!--&lt;!&ndash;<div class="b-someclass">&ndash;&gt;-->
                    <!--&lt;!&ndash;<div class="b-someclass-inner">&ndash;&gt;-->
                        <!--&lt;!&ndash;<input type="radio" name="check" id="check-1" checked>&ndash;&gt;-->
                        <!--&lt;!&ndash;<label for="check-1"></label>&ndash;&gt;-->

                        <!--&lt;!&ndash;<input type="radio" name="check" id="check-2">&ndash;&gt;-->
                        <!--&lt;!&ndash;<label for="check-2"></label>&ndash;&gt;-->

                        <!--&lt;!&ndash;<input type="radio" name="check" id="check-3">&ndash;&gt;-->
                        <!--&lt;!&ndash;<label for="check-3"></label>&ndash;&gt;-->

                        <!--&lt;!&ndash;&lt;!&ndash;<input type="radio" name="check" id="check-4">&ndash;&gt;&ndash;&gt;-->
                        <!--&lt;!&ndash;&lt;!&ndash;<label for="check-4"></label>&ndash;&gt;&ndash;&gt;-->

                        <!--&lt;!&ndash;&lt;!&ndash;<input type="radio" name="check" id="check-5">&ndash;&gt;&ndash;&gt;-->
                        <!--&lt;!&ndash;&lt;!&ndash;<label for="check-5"></label>&ndash;&gt;&ndash;&gt;-->

                        <!--&lt;!&ndash;&lt;!&ndash;<input type="radio" name="check" id="check-6">&ndash;&gt;&ndash;&gt;-->
                        <!--&lt;!&ndash;&lt;!&ndash;<label for="check-6"></label>&ndash;&gt;&ndash;&gt;-->

                        <!--&lt;!&ndash;<div class="b-someclass-line"></div>&ndash;&gt;-->
                    <!--&lt;!&ndash;</div>&ndash;&gt;-->
                <!--&lt;!&ndash;</div>&ndash;&gt;-->
            <!--</div>-->
        </div>
        <div class="requests">
            <p>Заявки</p>
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
        </div>
    </body>
</html>