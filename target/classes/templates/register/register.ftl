<#include "register_js.ftl">
<!DOCTYPE html> 
<html>
<head>
<title>MVP</title>
</head>
<body>
<div class="container">
	<section id="content">
		<form id="registerForm" method="POST" action="/index/login.html">
			<h1>注册</h1>
			<div>
				<input type="text" name="username"  id="username" placeholder="<@spring.message 'message.username'/>" required oninvalid="setCustomValidity('用户名不能为空')" oninput="setCustomValidity('')"/>
			</div>
			<div>
				<input type="password"  name="password" id="password" placeholder="<@spring.message 'message.password'/>" required oninvalid="setCustomValidity('密码不能为空')" oninput="setCustomValidity('')"/>
			</div>
			<div>
				<input type="submit" value="注册" class="btn btn-primary"/>
				<input type="submit" value="取消" class="btn btn-primary" id="js-btn-cancle"/>
			</div>
			<div class="">
				<span class="help-block u-errormessage" style="color:red" id="js-server-helpinfo">${(message)!}&nbsp;</span>		
			</div>
		</form>
	</section>
</div>
</body>
</html>