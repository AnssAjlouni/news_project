<?php
include 'main.php';

//هنا يتم استقبال النص الذي تم ارساله من الهاتف باسم id
//ويتم تخزينه داخل متغير باسم id
if(isset($_POST['id'])){
$id=$_POST['id'];
}
//هنا يتم عمل استعلام يعرض جميع بيانات الخبر بناء على رقم id الذي تم ارساله من الهاتف 
echo query_json("SELECT * from news where news_id='$id'");
?>


