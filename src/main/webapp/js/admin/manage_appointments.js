$(function() {
	$('.remove').click(function(){
		var url = '../apppointment_fetch/' + $(this).attr('id');
		console.log(url);
		$.getJSON(url, function(data){
			console.log(data);
			$("#removeappt-box").css("display","block");
			$("#bg").css("display","block");
			$("#deleteDate").html(data.appointmentDate);
			$("#deleteTime").html(data.appointmentTime);
			$("#deleteLocation").html(data.location);
			$("#deleteStaff").html(data.staff);
			var list = "";
			for(var i = 0; i < data.patrons.length; i++){
				if(i > 0){
					list = list + ', ';
				}
				list = list + data.patrons[i];
			}
			$("#deletePatrons").html(list);
			$("#deleteId").html(data.id);
			console.log($("#deletePatrons").html());
		});
		$("#delete").click(function(){
			var url = "delete_appointment/" + $("#deleteId").html();
			console.log(url);
			$.ajax({
				  url: url
			}).done(function(){
				window.location = "manage-appointments";
			});
		});
	});
	
	$('.reschedule').click(function(){
		console.log('clicked');
		var url = '../apppointment_fetch/' + $(this).attr('id');
		$.getJSON(url, function(data){
			console.log(data);
			$("#rescheduleappt-box").css("display","block");
			$("#bg").css("display","block");
			$("#rescheduleDate").val(data.appointmentDate);
			$("#rescheduleTime").val(data.appointmentTime);
			$("#rescheduleTimeEnd").val(data.appointmentTimeEnd);
			$("#rescheduleLocation").val(data.location);
			$("#rescheduleStaff").html(data.staff);
			var list = "";
			for(var i = 0; i < data.patrons.length; i++){
				if(i > 0){
					list = list + ', ';
				}
				list = list + data.patrons[i];
			}
			$("#reschedulePatrons").html(list);
			$("#rescheduleId").html(data.id);
			$("#rescheduleDate").datepicker({
				dateFormat: 'dd-mm-yy'
			});
			$('#rescheduleTime').timepicker({
				controlType: 'select',
				timeOnly: true,
				timeFormat: 'hh:mm tt',
				defaultValue:	function(){
					var now = new Date();
					var hours = now.getHours();
					var hour = hours % 12;
					var min = now.getMinutes();
					var ampm = hours >= 12 ? 'pm' : 'am';
					min = min < 10 ? '0' + min : min;
					console.log(hour + ':' + min + ' ' + ampm);
					return hours + ':' + min + ' ' + ampm;
				}
			});
			$('#rescheduleTimeEnd').timepicker({
				controlType: 'select',
				timeOnly: true,
				timeFormat: 'hh:mm tt',
				minTime: $('#appointmenttime').val(),
				maxTime: '12:00pm',
				defaultValue:	function(){
					var now = new Date();
					var hours = now.getHours();
					var hour = hours % 12;
					var min = now.getMinutes();
					var ampm = hours >= 12 ? 'pm' : 'am';
					min = min < 10 ? '0' + min : min;
					console.log(hour + ':' + min + ' ' + ampm);
					return hours + ':' + min + ' ' + ampm;
				}
			});
		});
		var currentuser = $('#username').html();
		console.log(currentuser);
		$.getJSON('../list-location/' + currentuser, function(data) {
			setSelectOptions($('#rescheduleVenue'), data, 'id', 'name', '');
		});
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
            var html = "";

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
        if (typeof defaultValue != 'undefined') {
            selectElement.children('option[value="' + defaultValue + '"]').attr('selected', 'selected');
        }
    }
    return false;
}