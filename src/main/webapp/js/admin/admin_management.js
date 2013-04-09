$(function() {
	console.log('url: '+ document.location);
	var urlParams = $.url(document.location).param();
	var from  = urlParams.f;
	var tabs = ['staff-info' , 'patron-info', 'venue-info', 'service-info'];
	var header = ['staff-hdr' , 'patron-hdr', 'venue-hdr', 'service-hdr'];
	console.log(from);
	if(from){
		hideAllTabs();
		console.log('go to not null');
		from = parseInt(from) - 1;
		var fromForm = tabs[from];
		$('#'+ fromForm).css('display', 'block');
		fromForm = header[from];
		$('#'+ fromForm).attr('class','active');
	}else{
		console.log('go to null');
		$('#staff-info').css('display', 'block');
		$('#staff-hdr').attr('class','active');
	}
	$('#staff-hdr').click(function(){
		hideAllTabs();
		$('#staff-info').attr('class','');
		$('#staff-info').css('display', 'block');
		$('#staff-hdr').attr('class','active');
	});
	$('#patron-hdr').click(function(){
		hideAllTabs();
		$('#patron-info').attr('class','');
		$('#patron-info').css('display', 'block');
		$('#patron-hdr').attr('class','active');
	});
	$('#venue-hdr').click(function(){
		hideAllTabs();
		$('#venue-info').attr('class','');
		$('#venue-info').css('display', 'block');
		$('#venue-hdr').attr('class','active');
	});
	$('#service-hdr').click(function(){
		hideAllTabs();
		$('#service-info').attr('class','');
		$('#service-info').css('display', 'block');
		$('#service-hdr').attr('class','active');
	});
	
	var currentuser = $('#username').html();
	var url = '../list-staff-name/'+ currentuser;
	$("#fullname-staff").autocomplete({
		source: url,
		minLength: 3,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '?';
			url = url + 'name=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	
	url = '../list-staff-nric/'+ currentuser;
	$("#identificationnum-staff").autocomplete({
		source: url,
		minLength: 3,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '?';
			url = url + 'nric=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-staff-email/'+ currentuser;
	$("#email-staff").autocomplete({
		source: url,
		minLength: 3,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '?';
			url = url + 'email=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-staff-mobile/'+ currentuser;
	$("#mobilenum-staff").autocomplete({
		source: url,
		minLength: 3,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '?';
			url = url + 'mobile=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	$('#searchStaff').click(function(){
		var name = $('#fullname-staff').val();
		var hpTel = $('#mobilenum-staff').val();
		var nric = $('#identificationnum-staff').val();
		var email = $('#email-staff').val();
		var url = '../search-staff/' + currentuser + '?';
		if(email != ''){
			url = url + 'email=' + email;
		}
		if(nric != ''){
			url = url + '&nric=' + nric;
		}
		if(name != ''){
			url = url + '&name=' + name;
		}
		if(hpTel != ''){
			url = url + '&mobile=' + hpTel;
		}
		$.getJSON(url, function(data){
			 $('#id').val(data.id);
			 $('#fullname-staff').val(data.name);
			 $('#identificationnum-staff').val(data.nric);
			 $('#email-staff').val(data.email);
			 $('#mobilenum-staff').val(data.hpTel);
			 $('#smsnotification-staff').prop('checked', data.sendSms);
			 $('#emailnotification-staff').prop('checked', data.sendEmail);
		});
	});
	
	$('#saveStaff').click(function(){
		if($('#email-staff').val() != ''){
			$('#staffform').submit();
		}
	});
	
	$('#deleteStaff').click(function(){
		if($('#id').val() != null && $('#id').val() != ''){
			$('#valid').val('0');
			$('#staffform').submit();
		}
	});
	
});

function hideAllTabs(){
	console.log('hiding');
	$('#tab-window ul').each(function(){
		$(this).attr('class','hidden');
		$(this).css('display','none');
	});
	$('#tab-nav a').each(function(){
		$(this).attr('class','');
	});
}