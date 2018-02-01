<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>登录</title>
	
	<script language="javascript" src="/whwr/js/jquery2.js"></script>
	<script language="javascript" src="/whwr/js/whwr.form.js"></script>
	<script language="javascript" src="/whwr/js/json2.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#xm, #mm").val("");
			$("#xm").focus();
			$(document).fmBuilt();
		});

		function check() {
			if ($('#xm').val().length === 0) {
				alert("用户名为空，请输入用户名!");
				$('#xm').focus();
				return false;
			}
			if ($('#mm').val().length === 0) {
				alert("密码为空，请输入密码!");
				$('#mm').focus();
				return false;
			}
			return true;
		}
                
                function reset(){
                    $('#xm').val("");
                    $('#mm').val("");
                }

		function login() {
			if (check()) {
				showProccess();
				var json = {"dlm": $("#xm").val(), "mm": $("#mm").val()};
				json={"jsonObj":JSON.stringify(json)};
				var s = $.ajax({
					url: "/whwr/checklogin.action",
					data: json,
					dataType: "json",
					type: "post",
					cache: false,
					complete: function (XMLHttpRequest, textStatus) { hindProccess(); },
					error: function (msg, textStatus) {
						if (confirm("登录失败，您要查看错误信息吗？")) { alert(msg.responseText); } },
					success: function (json) {
						if (json.result === 0) {
							window.location.href = "/whwr/dl.action";
						} else {
							alert(json.msg);
						}
					}
				});
			}
		}

		function showProccess() {
			$('#bar').show();
			barTimer = setInterval("mypro()", 200);
			$('#login').disabled = 'disabled';
		}

		function hindProccess() {
			clearTimeout(barTimer);
			$('#bar').hide();
			$('#login').disabled = '';
		}

		var num = 0;
		var barTime;
		function mypro() {
			num += 1;
			if (num > 20) {
				$("#jd").html('<font color="#0000FF">■</font>');
				num = 0;
			} else {
				$("#jd").html($("#jd").html() + '<font color="#0000FF">■</font>');
			}
		}
	</script>
	<style type="text/css">
		body {
			background-color: #D2E7F4;
		}
		
		.dengLu{
			border-top: 3px solid #FFFFFF;
			border-right: 3px solid #CCCCCC;
			border-bottom: 3px solid #CCCCCC;
			border-left: 3px solid #FFFFFF;
			background-color: #E1EBF5;
			width: 100%;
		}

		table td {
			padding: 10px;
		}

		tr.biaoTi {
			background-color: #EBEBEB;
			color:#7F7F7F;
			font-size:16px;
			font-family: Arial, Helvetica, sans-serif;
		}

		input[type=text], input[type=password] {
			width:150px;
			height:25px;
		}
	</style>
</head>

<body style="background-image: url(/whwr/images/login_back.png);">
	<div align="center" style="position:absolute; top:50%; left:50%; margin-top:-136px; margin-left:-150px;">
		<div>
			<table>
				<tbody><tr>
					<td style="font-size: 32px;font-weight: bold;color: #FFFFFF; padding:15px;">仓库管理系统</td>
				</tr>
			</tbody></table>
		</div>						   
		<div align="center" style="height: 200px; width: 300px;">
			<table class="dengLu">		
				<tbody><tr class="biaoTi">
					<td colspan="2"></td>
				</tr>
				<tr>
					<td style="text-align: right">用户名:</td>
					<td style="text-align: left"><input id="xm" type="text"></td>
				</tr>
				<tr>
					<td style="text-align: right">密码:</td>
					<td style="text-align: left"><input id="mm" type="password"></td>
				</tr>
				<tr>
                                    <td></td><td><input id="login" type="button" value="登录" onclick="javascript:login()" style="margin-right:40px;width: 50px;" /><input id="reset" type="button" value="重置" onclick="javascript:reset()" style="width: 50px;" /></div</td>
				</tr>
			</tbody></table>
		</div>
		<div id="bar" style="width:300px; height:100px; z-index:10; display: none;">
			<table width="100%" bgcolor="FFFFFF">
				<tbody><tr>
					<td>登录中，请稍后…</td>
				</tr>
				<tr>
					<td id="jd"></td>
				</tr>
			</tbody></table>
		</div>
	</div>

</body>
</html>
