function loginAction() {
	let userId = document.querySelector("#userId").value
	let userPassword = document.querySelector("#userPassword").value
	
	$.ajax({
		url: "/BBS/user/loginAction",
		data: { "userId": userId, "userPassword": userPassword },
		success: function(re) {
			result = JSON.parse(re)
			if(result == 1) {
				location.href = 'main.jsp'
			} else if (result == 0) {
				alert("비밀번호가 틀립니다.")
				history.back()
			} else if (result == -1) {
				alert("존재하지 않는 아이디입니다.")
				history.back()
			} else if (result == -2) {
				alert("데이터베이스 오류가 발생했습니다.")
				history.back()
			}
		}
	})
	
}
