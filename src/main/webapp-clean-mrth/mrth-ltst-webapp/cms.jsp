<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="assets/images/favicon.ico" />
	<script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-1.8.3.js"></script>
	<script type='text/javascript'>
		function getParameterByName(name, url) {
			if (!url) url = window.location.href;
			name = name.replace(/[\[\]]/g, "\\$&");
			var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
				results = regex.exec(url);
			if (!results) return null;
			if (!results[2]) return '';
			return decodeURIComponent(results[2].replace(/\+/g, " "));
		}
		var page = getParameterByName('page');
		alert(page)

		$(window).load(function () {
			debugger
			$.ajax({
				url: "/myfcarewards/content/" + page,
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