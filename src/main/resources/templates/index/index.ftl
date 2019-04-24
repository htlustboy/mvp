<#include "index_js.ftl">
<!DOCTYPE html> 
<html>
<head>
<title>MVP</title>
</head>
<body>
<div class="container">
	<section id="content">
		<form id="loginForm" method="POST" action="/index/login.html">
			<input type="hidden" name="token" value="${(token)!}" />
			<h1><@spring.message 'message.welcome'/></h1>
			<div>
				<input type="text" name="username"  id="username" placeholder="<@spring.message 'message.username'/>" required oninvalid="setCustomValidity('用户名不能为空')" oninput="setCustomValidity('')"/>
			</div>
			<div>
				<input type="password"  name="password" id="password" placeholder="<@spring.message 'message.password'/>" required oninvalid="setCustomValidity('密码不能为空')" oninput="setCustomValidity('')"/>
			</div>
			<div>
				<input type="submit" value="<@spring.message 'message.login'/>" class="btn btn-primary" id="js-btn-login"/>
				<a href="/index/findPassword"><@spring.message 'message.findPassword'/>?</a>
				<a href="/index/register"><@spring.message 'message.register'/></a>
			</div>
			<div class="">
				<span class="help-block u-errormessage" style="color:red" id="js-server-helpinfo">${(message)!}&nbsp;</span>		
			</div>
		</form>
		<div style="margin-bottom:20px">  
        	<span><a href="index?lang=zh_CN">中文  </a></span>
			<span>|</span>  
        	<span><a href="index?lang=en_US">English</a></span>  
    	</div>
	</section>
</div>
</body>
</html>