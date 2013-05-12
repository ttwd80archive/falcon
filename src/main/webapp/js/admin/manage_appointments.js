$(function() {
	var currentuser = $('#username').html();
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
				list = list + data.patrons[i].name;
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
	$('.addPatron').click(function(){
		var url = '../apppointment_fetch/' + $(this).attr('id');
		$.getJSON(url, function(data){
			var selectedKeys = new Array();
			for(var i = 0; i < data.patrons.length ; i++){
				console.log('patronz: ' + data.patrons[i].key);
				selectedKeys[i] = data.patrons[i].key;
			}
			console.log('patrons:' + data.patrons);
			$("#appointmentId").val(data.id);
			$("#managePatronDate").html(data.appointmentDate);
			$("#managePatronTime").html(data.appointmentTime);
			$("#managePatronTimeEnd").html(data.appointmentTimeEnd);
			$("#managePatronVenue").html(data.location);
			$("#managePatronStaff").html(data.staff);
			var date = data.appointmentDate.replace(/-/g, '');
			var starttime = data.appointmentTime.replace(/:/g, '');
			var endtime =  data.appointmentTime.replace(/:/g, '');
			if(starttime.substring(5,7) == 'pm'){
				starttime = starttime.substring(0,4);
				starttime = parseInt(starttime) + 1200;
			}else{
				starttime = starttime.substring(0,4);
			}
			if(endtime.substring(5,7) == 'pm'){
				endtime = endtime.substring(0,4);
				console.log(parseInt(endtime));
				if(parseInt(endtime) <= 1200 ){
					console.log("adding to pm");
					endtime = parseInt(endtime) + 1200;
					console.log(endtime);
				}
			}else{
				endtime = endtime.substring(0,4);
			}
			console.log("date:" + date);
			console.log("start:" + starttime + " end: "+ endtime );
			var url = '../list-patient/' + currentuser + '/' +  date + '/' + starttime + '/' + endtime;
			$.getJSON(url, function(data) {
				setSelectOptions($('#managePatronPatrons'), data, 'username', 'name', selectedKeys);
				$(".chzn-select").chosen();
				$(".chzn-select").trigger("liszt:updated");
			});
		});
		$("#createappt-box").css("display","block");
		$("#bg").css("display","block");
		$('#updateAppointment').click(function(){
			$("#appointmentupdateform").submit();
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
			$("#rescheduleVenue").val(data.locationId);
			$("#rescheduleStaff").html(data.staff);
			var list = "";
			for(var i = 0; i < data.patrons.length; i++){
				if(i > 0){
					list = list + ', ';
				}
				list = list + data.patrons[i].name;
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
		console.log(currentuser);
		$.getJSON('../list-location/' + currentuser, function(data) {
			setSelectOptions($('#rescheduleVenue'), data, 'id', 'name', '');
		});
		$('#update').click(function(){
			var url = "reschedule_appointment/" + $("#rescheduleId").html() + "/" + $("#rescheduleDate").val() + "/" + $("#rescheduleTime").val() + "/" + $("#rescheduleTimeEnd").val() + "/" + $("#rescheduleVenue").val() + "/";
			console.log(url);
			$.ajax({
				  url: url
			}).done(function(){
				window.location = "manage-appointments";
			});
		});
	});
	$.getJSON('../list-staff/'+ currentuser, function(data) {
		console.log('in here');
		setSelectOptions($('#staffs'), data, 'id', 'name', '');
	});
	$.getJSON('../list-patient/' + currentuser, function(data) {
		setSelectOptions($('#patrons'), data, 'username', 'name', '');
	});

	$.getJSON('../list-location/' + currentuser, function(data) {
		setSelectOptions($('#locations'), data, 'id', 'name', '');
	});
	
	$.getJSON('../list-services/' + currentuser, function(data) {
		setSelectOptions($('#services'), data, 'id', 'name', '');
	});
	$("#appointmentdate").datepicker({
		dateFormat: 'dd-mm-yy'
	});
	$('#appointmenttime').timepicker({
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
	$('#querybutton').click(function(){
		$('#searchform').submit();
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