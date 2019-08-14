<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Реєстрація</title>
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
<div class="registration-photo">
 <div class="registration-form">

     <form name="signUpForm">
            <div id="hideifsuccess_0" class="team-capitan">
                <div class="team-name">
                    <p>Назва команди</p>
                    <input type="text" name="teamName" placeholder="Назва команди" required>
                </div>
                <div class="capitan">
                    <p>Капітан команди</p>
                    <input type="text" name="captainName" placeholder="Прізвище ім'я" required>
                    <input type="tel" name="captainPhone" pattern="[0-9]{10}" placeholder="номер телефону 093 ..." required>
                    <input type="email" name="captainEmail" placeholder="введіть email" required>
                </div>
            </div>
            <div id="success" class="player-bg"><p>Запит успішно надіслано!</p></div>
            <div id="hideifsuccess_1" class="player-bg">
            <p class="note">* Для реєстрації не обовязково заповняти всі поля</p>
                <div class="player1-3">
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player1" placeholder="Прізвище Ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player2" placeholder="Прізвище Ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player3" placeholder="Прізвище Ім'я">
                    </div>
                </div>
                <div class="player1-3">
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player4" placeholder="Прізвище Ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player5" placeholder="Прізвище Ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player6" placeholder="Прізвище Ім'я">
                    </div>
                </div>
                <div class="player1-3">
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player7" placeholder="Прізвище Ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player8" placeholder="Прізвище Ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player9" placeholder="Прізвище Ім'я">
                    </div>
                </div>
                <div class="player1-3">
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player10" placeholder="Прізвище ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player11" placeholder="Прізвище ім'я">
                    </div>
                    <div class="player">
                        <p>Гравець</p>
                        <input type="text" name="player12" placeholder="Прізвище ім'я">
                    </div>
                </div>
            </div>
        <input class="reg-input-button" type="submit" onclick="submitform()" value="Зареєструватися">
     </form>
 </div>
</div>
<script src="signUpScript.js" type="text/javascript"></script>


</body>
</html>