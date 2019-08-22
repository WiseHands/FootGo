<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Таблиця</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="hamburger.css">
    <link rel="shortcut icon" href="img/footgo-dark-icon.png" type="image/x-icon">


</head>
<body>

<header>
    <div class="header-full">
        <div class="header-first-block">
            <div class="header-icon">
                <a href="/"><img class="header-img" src="img/footgo-light-icon.png" alt="wiselogo"></a>
            </div>
            <div class="header-null">
                <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                    <i class="hamburger fa fa-bars"></i>
                </a>
            </div>
            <div class="header-text">
                <a href="/signup"><p>Реєстрація</p></a>
                <a href="/results"><p>Результати</p></a>
                <a href="/gametable"><p>Таблиця</p></a>
                <a href="/bombardier"><p>Бомбардири</p></a>
            </div>
        </div>
    </div>
    <div id="myLinks">
        <div><a class="link-item" href="/signup">Реєстрація</a></div>
        <div><a class="link-item" href="/results">Результати</a></div>
        <div><a class="link-item" href="/gametable">Таблиця</a></div>
        <div><a class="link-item" href="/bombardier">Бомбардири</a></div>
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
            <div class="table-firstplace">${firstPlace.numberOfGames}</div>
            <div class="table-secondplace">${secondPlace.numberOfGames}</div>
            <div class="table-thirdplace">${thirdPlace.numberOfGames}</div>
            <c:forEach items="${teamList}" var="item">
                 <div><p><c:out value="${item.numberOfGames}" /></p></div>
            </c:forEach>
        </div>
        <div class="table-wins table-offset">
            <div class="table-title">В</div>
            <div class="table-firstplace">${firstPlace.numberOfWins}</div>
            <div class="table-secondplace">${secondPlace.numberOfWins}</div>
            <div class="table-thirdplace">${thirdPlace.numberOfWins}</div>
            <c:forEach items="${teamList}" var="item">
                 <div><p><c:out value="${item.numberOfWins}" /></p></div>
            </c:forEach>
        </div>
        <div class="table-draws table-offset">
            <div class="table-title">Н</div>
            <div class="table-firstplace">${firstPlace.numberOfDraws}</div>
            <div class="table-secondplace">${secondPlace.numberOfDraws}</div>
            <div class="table-thirdplace">${thirdPlace.numberOfDraws}</div>
            <c:forEach items="${teamList}" var="item">
                 <div><p><c:out value="${item.numberOfDraws}" /></p></div>
            </c:forEach>
        </div>
        <div class="table-defeats table-offset">
            <div class="table-title">П</div>
            <div class="table-firstplace">${firstPlace.numberOfLoses}</div>
            <div class="table-secondplace">${secondPlace.numberOfLoses}</div>
            <div class="table-thirdplace">${thirdPlace.numberOfLoses}</div>
            <c:forEach items="${teamList}" var="item">
                 <div><p><c:out value="${item.numberOfLoses}" /></p></div>
            </c:forEach>
        </div>
        <div class="table-goals">
            <div class="table-title">З-П</div>
            <div class="table-firstplace">${firstPlace.numberOfGoalsScored} - ${firstPlace.numberOfGoalsMissed}</div>
            <div class="table-secondplace">${secondPlace.numberOfGoalsScored} - ${secondPlace.numberOfGoalsMissed}</div>
            <div class="table-thirdplace">${thirdPlace.numberOfGoalsScored} - ${thirdPlace.numberOfGoalsMissed}</div>
            <c:forEach items="${teamList}" var="item">
                 <div><p><c:out value="${item.numberOfGoalsScored} - ${item.numberOfGoalsMissed}" /></p></div>
            </c:forEach>
        </div>
        <div class="table-points table-offset">
            <div class="table-title">О</div>
            <div class="table-firstplace">${firstPlace.points}</div>
            <div class="table-secondplace">${secondPlace.points}</div>
            <div class="table-thirdplace">${thirdPlace.points}</div>
            <c:forEach items="${teamList}" var="item">
				 <div><p><c:out value="${item.points}" /></p></div>
			</c:forEach>
        </div>
    </div>
</div>

<script src="signUpScript.js"></script>
<script src="hamburger.js"></script>

</body>
</html>