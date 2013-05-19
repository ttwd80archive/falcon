$(function() {
	$("#signupbutton").click(function() {
		var location = $("#registration-link").attr("href");
		window.location = location;
	});
});