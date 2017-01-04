<?php
include 'main.php';
echo query_json("SELECT news_id,news_img,news_title from news");
?>


