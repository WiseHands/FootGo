<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Таблиця</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="img/footgo-dark-icon.png" type="image/x-icon">


</head>
<body>

<header>
    <div class="header-full">
        <div class="header-first-block">
            <div class="header-icon">
                <a href="/"><img class="header-img" src="img/footgo-light-icon.png" alt="wiselogo"></a>
            </div>
            <div class="header-null"></div>
            <div id="nav-icon">
                <span></span>
                <span></span>
                <span></span>
            </div>

            <div class="header-text">
                <a href="/signup"><p>Реєстрація</p></a>
                <a href="/results"><p>Результати</p></a>
                <a href="/gametable"><p>Таблиця</p></a>
                <a href="/bombardier"><p>Бомбардири</p></a>
            </div>
        </div>
    </div>
</header>

<div class="table-full">
<div class="table-head">
    <p>Таблиця чемпіонату</p>
</div>
    <div class="table">
        <div class="table-position">
            <div class="table-title">№</div>
            <div class="table-firstplace">1</div>
            <div class="table-secondplace">2</div>
            <div class="table-thirdplace">3</div>
            <div>4</div>
            <div>5</div>
            <div>6</div>
            <div>7</div>
            <div>8</div>
            <div>9</div>
            <div>10</div>
        </div>
        <div class="table-team-name">
            <div class="table-title">Команда</div>
            <div class="table-firstplace"><p>${firstPlace.teamName}</p></div>
            <div class="table-secondplace"><p>${secondPlace.teamName}</p></div>
            <div class="table-thirdplace"><p>${thirdPlace.teamName}</p></div>
            <c:forEach items="${teamList}" var="item">
                 <div><p><c:out value="${item.teamName}" /></p></div>
            </c:forEach>
        </div>
        <div class="table-games table-offset ">
            <div class="table-title">І</div>
            <div class="table-firstplace">0</div>
            <div class="table-secondplace">0</div>
            <div class="table-thirdplace">0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
        </div>
        <div class="table-wins table-offset">
            <div class="table-title">В</div>
            <div class="table-firstplace">0</div>
            <div class="table-secondplace">0</div>
            <div class="table-thirdplace">0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
        </div>
        <div class="table-draws table-offset">
            <div class="table-title">Н</div>
            <div class="table-firstplace">0</div>
            <div class="table-secondplace">0</div>
            <div class="table-thirdplace">0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
        </div>
        <div class="table-defeats table-offset">
            <div class="table-title">П</div>
            <div class="table-firstplace">0</div>
            <div class="table-secondplace">0</div>
            <div class="table-thirdplace">0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
        </div>
        <div class="table-goals">
            <div class="table-title">З-П</div>
            <div class="table-firstplace">0-0</div>
            <div class="table-secondplace">0-0</div>
            <div class="table-thirdplace">0-0</div>
            <div>0-0</div>
            <div>0-0</div>
            <div>0-0</div>
            <div>0-0</div>
            <div>0-0</div>
            <div>0-0</div>
            <div>0-0</div>
        </div>
        <div class="table-points table-offset">
            <div class="table-title">О</div>
            <div class="table-firstplace">0</div>
            <div class="table-secondplace">0</div>
            <div class="table-thirdplace">0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
            <div>0</div>
        </div>
    </div>
</div>

<script src="signUpScript.js"></script>

</body>
</html>