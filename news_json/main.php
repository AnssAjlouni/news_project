<?php
//هنا لدعم اللغة العربية في json
header('Content-Type: text/html; charset=utf-8');
header('Content-Type: application/json');
//اسم السيرفر 
 $servername = "localhost";
 //اسم المستخدم
 $username = "root";
 //كلمة المرور
 $password = "";
 //اسم قاعدة البيانات
 $dbname = "news_project";
 //الاتصال بالسيرفر
 $conn = mysqli_connect($servername, $username, $password, $dbname);
	mysqli_query($conn, "SET NAMES 'utf8'");
	//بحال لم يعمل الاتصال
	if (!$conn) {
		die("Connection failed: " . mysqli_connect_error());
	}



//هذه دالة لعمل json
function query_json($sql){
global $conn;
	$result = mysqli_query($conn, $sql);
	$rows = array();
	while($r = mysqli_fetch_assoc($result)) {
		$rows[] = $r;
	}
	
	return json_encode($rows);
	
	mysqli_close($conn);
}

function query_row($sql){
global $conn;
$result = mysqli_query($conn, $sql);
return mysqli_fetch_assoc($result);
}
//دالة الاضافة وترجع لنا رقم  الشئ الذي تم اضافته
function query_insert($sql){

global $conn;
	mysqli_query($conn, $sql);
	return mysqli_insert_id($conn);
	
	mysqli_close($conn);
}

function query_execute($sql){
	global $conn;
	mysqli_query($conn, $sql);
	mysqli_close($conn);
}
?>