function createPass(){
	var userName = document.getElementById("userName");
	var password = document.getElementById("password");
	var comfirmPass = document.getElementById("comfirmPass");
	
	password.value = userName.value;
	comfirmPass.value = userName.value;
}

function exefunction(numberOfQuiz, numberOfAnswer){
	var total = 0;
	for(var i = 1; i <= numberOfQuiz; i++){
		for(var j = 0; j < numberOfAnswer; j++){
				if(document.getElementById("ck_" + i + j).checked){
					total++;
					break;
				}
		}
	}
	var txt = "Bạn có muôn tạo topic? (Đã làm "+total+"/"+numberOfQuiz+" câu)";
    return confirm (txt);
}

function checkSubmit(){
	var form = document.getElementById("submit");
	if (form.onsubmit()){
		form.submit();
	}
}
