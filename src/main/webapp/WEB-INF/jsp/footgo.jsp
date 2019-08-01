<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Foot&Go</title>
    <link rel="shortcut icon" href="img/footgo-dark-icon.png" type="image/x-icon">
    <link rel="stylesheet" href="style.css">
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
            <div class="header-null"></div>
            <div id="nav-icon">
                <span></span>
                <span></span>
                <span></span>
            </div>
            <!---->
            <div class="dropdown">
                <button onclick="myFunction()" class="dropbtn">Dropdown</button>
                <div id="myDropdown" class="dropdown-content">
                    <a href="#home">Home</a>
                    <a href="#about">About</a>
                    <a href="#contact">Contact</a>
                </div>
            </div>
            <!---->
            <div class="header-text">
                <a href="/signup"><p>Реєстрація</p></a>
                <a href="/results"><p>Результати</p></a>
                <a href="/gametable"><p>Таблиця</p></a>
                <a href="/bombardier"><p>Бомбардири</p></a>
            </div>
        </div>
        <!--<div class="header-second-block">-->

            <!--&lt;!&ndash;<div class="signup">-->
                <!--<a href="#"><p>Зареєструватися</p></a>-->
            <!--</div>-->
            <!--<div class="login" >-->
                <!--<a href="https://wisehands.me/#/"><p>Увійти</p></a>-->
            <!--</div>&ndash;&gt;-->
        <!--</div>-->
    </div>
</header>

<div class="main-photo">
    <div class="slogan-null"></div>
    <div class="slogan-text">
        <h1>Аматорський турнір</h1>
        <h3>по футболу 8*8</h3>
        <div class="slogan-button">
            <input class="input-button" type="submit" value="Взяти участь">
        </div>
    </div>
</div>

<script src="scrypt.js"></script>

</body>
</html>