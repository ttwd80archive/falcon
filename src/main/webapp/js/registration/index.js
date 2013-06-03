$(function() {
	$("#registrationform").validationEngine();
	$.getJSON('list-organizations/', function(data) {
		setSelectOptions($('#organization'), data, 'username', 'name', '');
	});
});

function setSelectOptions(selectElement, values, valueKey, textKey, defaultValue) {
    if (typeof (selectElement) == "string") {
        selectElement = $(selectElement);
    }
    selectElement.empty();
    if (typeof (values) == 'object') {
        if (values.length) {
            var type = typeof (values[0]);
            var html = '<option value=""></option>'; ;

            if (type == 'object') {
                // values is array of hashes
                $.each(values, function () {
                	console.log('key:' + this[valueKey] + ' val:' + this[textKey]);
                    html += '<option value="' + this[valueKey] + '">' + this[textKey] + '</option>';                    
                });

            } else {
                $.each(values, function () {
                    var value = this.toString();
                    html += '<option value="' + value + '">' + value + '</option>';                    
                });
            }
            selectElement.html(html);
        }
        // select the defaultValue is one was passed in
        console.log('default: ' +defaultValue);
        if (typeof defaultValue != 'undefined') {
        	for(var i = 0; i < defaultValue.length ; i++){
				var key = defaultValue[i];
				selectElement.children('option[value="' + key + '"]').attr('selected', 'selected');
			}
        }
    }
    return false;
}