$(function() {
	populate_patron_list();
	handle_submit();

	function populate_patron_list() {

		function handle_ajax_success(data) {
			var c = data.length;
			var sel = $("#notification-send-target");
			sel.empty();
			var i;
			for (i = 0; i < c; i++) {
				var item = $("<option>");
				item.attr("value", data[i][0]);
				item.text(data[i][1]);
				sel.append(item);
			}
		}

		var url = $("#link-list-patrons").attr("href");
		$.ajax({
			"url" : url,
			"success" : handle_ajax_success
		});
	}

	function handle_submit() {
		$("#button-submit").click(function() {
			var form = $("#form-send-notification");
			var options = {
				"success" : handle_ajax_success
			};
			form.ajaxSubmit(options);
		});
		function handle_ajax_success(data) {
			alert("message sent");
		}
	}
});