<?php
    $con=mysqli_connect("localhost","user_name","password","ColorDB");

    $gender = $_POST["gender"];
    $age = $_POST["age"];
    $favColor = $_POST["favColor"];
    $blue = $_POST["blue"];
    $green = $_POST["green"];
    $orange = $_POST["orange"];
    $purple = $_POST["purple"];
    $red = $_POST["red"];
    $yellow = $_POST["yellow"];

    $statement = mysqli_prepare($con, "INSERT INTO User (gender, age, favColor, blue, green, orange, purple, red, yellow) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sisssssss", $gender, $age, $favColor, $blue, $green, $orange, $purple, $red, $yellow);
    mysqli_stmt_execute($statement);

    mysqli_stmt_close($statement);

    mysqli_close($con);
?>
