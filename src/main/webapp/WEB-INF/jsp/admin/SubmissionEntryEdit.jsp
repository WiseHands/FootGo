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
            <p>Заявка команди 1</p>
            <div class="admin-team-name">
                <p>Назва команди</p>
                <input type="text" name="teamName" placeholder="Назва команди" required>
            </div>
            <div class="admin-capitan">
                <p>Капітан</p>
                <input type="text" name="captainName" placeholder="Прізвище ім'я" required>
                <input type="tel" name="captainPhone" pattern="[0-9]{10}" placeholder="номер телефону 093 ..." required>
                <input type="email" name="captainEmail" placeholder="введіть email" required>
            </div>
            <p>Гравці</p>
            <div class="admin-players">

                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
                <div><p>Ім'я<input type="text" name="player6"></p></div>
            </div>
        </div>

    </body>
</html>