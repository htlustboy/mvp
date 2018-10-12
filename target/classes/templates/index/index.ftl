<#include "index_js.ftl">
<#import "../spring.ftl" as spring/>
<!DOCTYPE html> 
<html>
<head>
<title>MVP</title>
</head>
<body>
<div class="container">
	<section id="content">
		<form id="loginForm" action="">
			<h1>用户登录</h1>
			<div>
				<input type="text" name="username"  id="username" placeholder="用户名" required oninvalid="setCustomValidity('用户名不能为空')" oninput="setCustomValidity('')"/>
			</div>
			<div>
				<input type="password"  name="password" id="password" placeholder="密码" required oninvalid="setCustomValidity('密码不能为空')" oninput="setCustomValidity('')"/>
			</div>
			<div>
				<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
				<a href="#">忘记密码?</a>
				<a href="#">注  册</a>
			</div>
			<div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>		
			</div>
		</form>
		<div style="margin-bottom:20px">  
        	<span><a href="index?i18n_language=zh_CN">中文 </a></span>
			<span>|</span>  
        	<span><a href="index?i18n_language=en_US">English</a></span>  
    	</div>
	</section>
</div>
</body>
</html>