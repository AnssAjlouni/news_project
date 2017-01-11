<?php
include 'main.php';
if(isset($_POST['section_id'])){
$section_id=$_POST['section_id'];
}
echo query_json("SELECT news_id,news_img,news_title from news where section_id='$section_id' order by news_id desc");
?>


