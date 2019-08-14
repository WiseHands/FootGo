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
                <form name="adminSignUpForm">
                    <div class="team-capitan">
                         <div class="capitan">
                            <p>Вхід для адміна</p>
                            <input type="email" name="email" placeholder="введіть email" required>
                            <input type="text" name="password" placeholder="введіть пароль" required>
                         </div>
                    </div>
                    <input id="adminSignUp" class="reg-input-button" type="submit" value="Увійти">
                </form>
            </div>
        </div>
        <script src="signUpScript.js" type="text/javascript"></script>
    </body>
</html>