<!DOCTYPE html>
<html>

<head>
	<title></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="assets/images/favicon.ico" />

	<script type='text/javascript'>
		$(window).load(function () {
			$.ajax({
				url: "/myfcarewards/content/${page}",
				type: "GET",
				success:
				function (data) {
					alert("success")
					console.log(data)
					document.getElementById("cms").innerHTML = data;
				},
				error:
				function (msg) {
					alert("failure");
				}
			});
		});
	</script>
</head>
<body>
	<div id="cms"></div>
</body>
</html>