<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up  - Spotify</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body>
    <div id="logo" class="text-center">
        <div class="container" id="box-logo" style="padding:5px;"><img src="assets/img/spotify-logo.jpg" id="logo-spotify"></div>
    </div>
    <div id="form" class="text-center container" style="padding:0px;">
        <div class="jumbotron" id="formulario">
            <div class="container">
                <button class="btn btn-default btn btn-block btn-facebook" type="button">SIGN UP WITH FACEBOOK</button>
                <div class="line-thru">
                    <h5 style="margin:0px;padding:5px 0px 0px;"> <span><strong>or</strong></span></h5>
                    <div id="line" style="margin:0px;"></div>
                </div>
            </div>
            <div class="container">
                <h2 class="center">Sign up with your email adress</h2></div>

            <div class="container">
                <form action="SignUp" method="post" id = "form1">
                    <fieldset>
                        <input class="form-control" type="text" name="email" placeholder="Email">
                        <input class="form-control" type="text" name="confirmEmail" placeholder="Confirme email">
                        <input class="form-control" type="password" name="password" placeholder="Password">
                        <input class="form-control" type="text" name="username" placeholder="Username">
                    </fieldset>
                </form>
            </div>
            <div class="container">
                <button class="btn btn-default btn  btn-green" type="submit" form="form1" value="Submit">
                    SIGN UP 
                </button>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

