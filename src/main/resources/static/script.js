function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();

    if (password != confirmPassword) {
		$("#divCheckPasswordMatch").html("Passwords do not match!");
		$("#sender").prop('disabled', true);
	}
    else {
		$("#divCheckPasswordMatch").html("");
		$("#sender").prop('disabled', false);
	}
}

$(document).ready(function () {
   $("#txtConfirmPassword").keyup(checkPasswordMatch);
});