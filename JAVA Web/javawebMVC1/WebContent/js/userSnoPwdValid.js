/**
 * 
 */
	
	window.onload=function(){
		//页面启动时开始注册提交按钮无效
		document.getElementById("submit").setAttribute("disabled",false);
		//页面启动检查信息是否合格
		//1、用户名是否有效
		checkUserSno();
		//2、密码是否有效
		checkPassword();
		
	}
	//检验用户名是否合格
	function checkUserSno() {
		// body...
		var oUserSno = document.getElementById('userSno').value;
		
		var oUserSno = document.getElementById('userSno').value;
		var pattern = /^[0-9]{2,10}$/;
		if (!pattern.test(oUserSno)) {
			showUseSnoInfoNoOk();
		}else{
			showUseSnoInfoOk();
		}
		enableButton();       //调用
	}
	//显示合格信息
	function showUseSnoInfoOk(){
		document.getElementById("useSnoHit").innerHTML = '<font class="ok">合格</font>';
	}
	//显示不合格信息
	function showUseSnoInfoNoOk(){
		document.getElementById("useSnoHit").innerHTML = '<font class="noOk">1、用户名长度必须大于1位小于11位。2、且必须为数字</font>'
	}
	
	//检验用户密码是否合格
	//密码检验 只能是1-5 四位
	function checkPassword(){
		var oPassword = document.getElementById('password').value;
		
		var pattern = /^[0-5]{1,4}$/;
		if (pattern.test(oPassword)) {
			showPasswordInfoOk();
		}else{
			showPasswordInfoNoOk();
		}
		enableButton();           //调用
	}
	//密码不OK
	function showPasswordInfoNoOk(){
		document.getElementById("passwordHit").innerHTML = '<font class="noOk">1、密码只能是数字且为1-5。 2、四位密码</font>'
	}
	//密码OK
	function showPasswordInfoOk(){
		document.getElementById("passwordHit").innerHTML = '<font class="ok">密码可以</font>'
	}
	
	//登录按钮是无效,如果输入符合条件才可以点
	function enableButton(){
		var ableSno = document.getElementById("useSnoHit").innerText;
		var ablePwd = document.getElementById("passwordHit").innerText;
		if(ableSno == "合格" && ablePwd=="密码可以"){	
			document.getElementById("submit").removeAttribute("disabled");
		}else{
			document.getElementById("submit").setAttribute("disabled",false);
		}
	}