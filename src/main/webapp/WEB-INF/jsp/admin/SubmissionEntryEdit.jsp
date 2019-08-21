<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Admin Page</title>
        <link rel="stylesheet" href="../../../admin.css">
        <link rel="shortcut icon" href="/img/footgo-dark-icon.png" type="image/x-icon">

    </head>
    <body>
        <div class="admin-main">
            <div class="admin-logo">
                <img class="admin-img" src="/img/footgo-dark-icon.png" alt="logo">
            </div>
        </div>

        <div class="team-request">
            <h3>Заявка команди 1</h3>
            <div class="admin-team-name">
                <p>Назва команди</p>
                <input type="text" name="teamName" placeholder="Назва команди" required>
            </div>
            <p class="admin-title-row">Капітан</p>
            <div class="admin-capitan">
                <input type="text" name="captainName" placeholder="Прізвище ім'я" required>
                <input type="tel" name="captainPhone" pattern="[0-9]{10}" placeholder="номер телефону 093 ..." required>
                <input type="email" name="captainEmail" placeholder="введіть email" required>
            </div>
            <p  class="admin-title-row">Гравці</p>
            <div class="admin-players">
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
                <div class="admin-player-name"><p>Ім'я</p><input type="text" name="player6"></div>
            </div>
            <div class="slogan-button">
                  <input class="main-input-button" type="button" value="Зареєструвати"">
            </div>
        </div>

    </body>
</html>