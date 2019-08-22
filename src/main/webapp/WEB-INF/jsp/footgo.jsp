<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Foot&Go</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="img/footgo-dark-icon.png" type="image/x-icon">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="hamburger.css">
    <link href="https://fonts.googleapis.com/css?family=Gugi" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
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

<div class="main-photo">
    <div class="slogan-null"></div>
    <div class="slogan-text">
        <h1>Аматорський турнір</h1>
        <h3>з футболу 8*8</h3>
        <div class="slogan-button">
            <input class="main-input-button" type="button" value="Прийняти участь" onclick="location.href='/signup';">
        </div>
    </div>
</div>

<script src="signUpScript.js"></script>
<script src="hamburger.js"></script>

</body>
</html>