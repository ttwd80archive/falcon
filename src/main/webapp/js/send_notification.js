$(function() {
	function handle_item(item) {
		$("#recepient").val(item.name);
	}

	function do_nothing() {
	}

	$("#recepient").autocomplete({
		"minLength" : 0,
		"source" : $("#link-list-patient").attr("href"),
		"select" : function(event, ui) {
			ui.item ? handle_item(ui.item) : do_nothing();
			return false;
		}
	}).data("ui-autocomplete")._renderItem = function(ul, item) {
		return $("<li>").append("<a>" + item.name + "</a>").appendTo(ul);
	};
});