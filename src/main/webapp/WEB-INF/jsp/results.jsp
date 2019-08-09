<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Результати</title>
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
<div class="results-full">
    <div class="results">
        <div class="results-bg">
            <c:forEach items="${tourList}" var="tour">

                <div class="results-block">
                    <div>
                        <p class="results-tour-title">
                            Тур <c:out value="${tour.tourNumber}"/>
                        </p>
                    </div>
                    <c:forEach items="${tour.gameList}" var="game">
                        <div class="results-tour">
                            <div class="results-date">
                                <p>
                                    ${game.gameTime}
                                </p>
                            </div>
                            <div class="results-team1">
                                <p>
                                    ${game.firstTeam.teamName}
                                </p>
                            </div>
                            <div class="results-score"><p>${game.teamAGoals.size()} : ${game.teamBGoals.size()}</p></div>
                            <div class="results-team2">
                                <p>
                                    ${game.secondTeam.teamName}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
<script src="signUpScript.js"></script>


</body>
</html>