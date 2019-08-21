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
            <div class="admin-bar">
                <p>FootGo</p>
                <div class="b-someclass">
                    <div class="b-someclass-inner">
                        <input type="radio" name="check" id="check-1" checked>
                        <label for="check-1"></label>

                        <input type="radio" name="check" id="check-2">
                        <label for="check-2"></label>

                        <input type="radio" name="check" id="check-3">
                        <label for="check-3"></label>

                        <!--<input type="radio" name="check" id="check-4">-->
                        <!--<label for="check-4"></label>-->

                        <!--<input type="radio" name="check" id="check-5">-->
                        <!--<label for="check-5"></label>-->

                        <!--<input type="radio" name="check" id="check-6">-->
                        <!--<label for="check-6"></label>-->

                        <div class="b-someclass-line"></div>
                    </div>
                </div>
        </div>
        </div>

    <p class="admin-tour-title">Тур 1</p>


        <div class="tour-match">
            <div class="tour-team1"><p>Команда 1</p><span id="myBtn">+</span></div>
            <div class="tour-score"><span id="goalBtn"><p>0 : 0</p></span></div>
            <div class="tour-team2"><span id="myBtn">+</span><p>Команда 2</p></div>
        </div>    <div class="tour-match">
            <div class="tour-team1"><p>Команда 1</p><span id="myBtn">+</span></div>
            <div class="tour-score"><p>0 : 0</p></div>
            <div class="tour-team2"><span id="myBtn">+</span><p>Команда 2</p></div>
        </div>    <div class="tour-match">
            <div class="tour-team1"><p>Команда 1</p><span id="myBtn">+</span></div>
            <div class="tour-score"><p>0 : 0</p></div>
            <div class="tour-team2"><span id="myBtn">+</span><p>Команда 2</p></div>
        </div>    <div class="tour-match">
            <div class="tour-team1"><p>Команда 1</p><span id="myBtn">+</span></div>
            <div class="tour-score"><p>0 : 0</p></div>
            <div class="tour-team2"><span id="myBtn">+</span><p>Команда 2</p></div>
        </div>
        </div>    <div class="tour-match">
            <div class="tour-team1"><p>Команда 1</p><span id="myBtn">+</span></div>
            <div class="tour-score"><p>0 : 0</p></div>
            <div class="tour-team2"><span id="myBtn">+</span><p>Команда 2</p></div>
        </div>

        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Гол</h2>
                </div>
                <div class="modal-body">
                    <div class="goal-minute">
                        <p>Хвилина</p>
                        <input type="text" name="minute">
                    </div>
                    <div class="goal-author">
                        <p>Автор</p>
                        <select>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                            <option value="">Гравець1</option>
                        </select>
                    </div>
                </div>
                <div class="goal-add">
                    <input class="main-input-button" type="button" value="Додати">
                </div>
            </div>
        </div>

        <div id="goalModal" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <div class="modal-goal">
                    <div class="team1-goal">
                        <h3>Команда 1</h3>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 32'<span>-</span></p>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 32'<span>-</span></p>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 32'<span>-</span></p>
                    </div>

                    <div class="team2-goal">
                        <h3>Команда 2</h3>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 66'<span>-</span></p>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 66'<span>-</span></p>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 66'<span>-</span></p>
                        <p><img src="../../../img/soccer-ball.png">Гравець, 66'<span>-</span></p>
                    </div>
                </div>

            </div>

        </div>

<script src="/modalScript.js" type="text/javascript"></script>
    </body>
</html>