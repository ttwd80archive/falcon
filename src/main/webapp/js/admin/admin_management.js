$(function() {
	$('#staffform').validationEngine();
	$('#patronform').validationEngine();
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
	
	var url = '';
	var currentuser = $('#username').html();
	currentuser = encodeURIComponent(currentuser).replace(/[!'().]/g, escape).replace(/\*/g, "%2A");
	/** for staff **/
	
	url = '../list-staff-name/'+ currentuser + '/99999999';
	$("#fullname-staff").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '/99999999' + '?';
			var index = ui.item.value.indexOf("(");
			var theName = ui.item.value.substring(0, index);
			url = url + 'name=' + theName;
			$.getJSON(url, function(data){
				 $('#id-staff').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-staff-nric/'+ currentuser + '/99999999';
	$("#identificationnum-staff").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '/99999999' + '?';
			url = url + 'nric=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id-staff').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-staff-email/'+ currentuser + '/99999999';
	$("#email-staff").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '/99999999' + '?';
			url = url + 'email=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id-staff').val(data.id);
				 $('#fullname-staff').val(data.name);
				 $('#identificationnum-staff').val(data.nric);
				 $('#email-staff').val(data.email);
				 $('#mobilenum-staff').val(data.hpTel);
				 $('#smsnotification-staff').prop('checked', data.sendSms);
				 $('#emailnotification-staff').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-staff-mobile/'+ currentuser + '/99999999';
	$("#mobilenum-staff").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-staff/' + currentuser + '/99999999' + '?';
			url = url + 'mobile=' + ui.item.value;
			$.getJSON(url, function(data){
				 $('#id-staff').val(data.id);
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
		var url = '../search-staff/' + currentuser + '/99999999' + '/?';
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
			 $('#id-staff').val(data.id);
			 $('#fullname-staff').val(data.name);
			 $('#identificationnum-staff').val(data.nric);
			 $('#email-staff').val(data.email);
			 $('#mobilenum-staff').val(data.hpTel);
			 $('#smsnotification-staff').prop('checked', data.sendSms);
			 $('#emailnotification-staff').prop('checked', data.sendEmail);
		});
	});
	
	$('#saveStaff').click(function(){
		$('#staffform').submit();
		return false;
	});
	
	$('#deleteStaff').click(function(){
		if($('#id-staff').val() != null && $('#id-staff').val() != ''){
			$('#valid-staff').val('0');
			$('#staffform').submit();
		}
	});
	
	
	/** For Patron **/
	
	url = '../list-patron-name/'+ currentuser + '/99999999';
	$("#fullname-patron").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			var index = ui.item.value.indexOf("(");
			var theNo = ui.item.value.substring(index + 1, ui.item.value.length - 1);
			url = '../search-patron/' + currentuser + '/99999999' + '?';
			url = url + 'nric=' + theNo;
			$.getJSON(url, function(data){
				$('#username-patron').val(data.username);
				 $('#fullname-patron').val(data.name);
				 $('#identificationnum-patron').val(data.nric);
				 $('#email-patron').val(data.email);
				 $('#mobilenum-patron').val(data.phone);
				 $('#smsnotification-patron').prop('checked', data.sendSms);
				 $('#emailnotification-patron').prop('checked', data.sendEmail);
				 if(data.valid){
					 $('#valid-patron').val(1);
				 }else{
					 $('#valid-patron').val(0);
				 }
				 console.log('this user validty is:' + data.valid);
			});
		}
	});
	
	url = '../list-patron-nric/'+ currentuser + '/99999999';
	$("#identificationnum-patron").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-patron/' + currentuser + '/99999999' + '?';
			url = url + 'nric=' + ui.item.value;
			$.getJSON(url, function(data){
				$('#username-patron').val(data.username);
				 $('#fullname-patron').val(data.name);
				 $('#identificationnum-patron').val(data.nric);
				 $('#email-patron').val(data.email);
				 $('#mobilenum-patron').val(data.phone);
				 $('#smsnotification-patron').prop('checked', data.sendSms);
				 $('#emailnotification-patron').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-patron-phone/'+ currentuser + '/99999999';
	$("#mobilenum-patron").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-patron/' + currentuser + '/99999999' + '?';
			url = url + 'mobile=' + ui.item.value;
			$.getJSON(url, function(data){
				$('#username-patron').val(data.username);
				 $('#fullname-patron').val(data.name);
				 $('#identificationnum-patron').val(data.nric);
				 $('#email-patron').val(data.email);
				 $('#mobilenum-patron').val(data.phone);
				 $('#smsnotification-patron').prop('checked', data.sendSms);
				 $('#emailnotification-patron').prop('checked', data.sendEmail);
			});
		}
	});
	
	url = '../list-patron-email/'+ currentuser + '/99999999';
	$("#email-patron").autocomplete({
		source: url,
		minLength: 4,
		select: function(event, ui){
			console.log(ui.item.value);
			var url = '../search-patron/' + currentuser  + '/99999999' + '?';
			url = url + 'email=' + ui.item.value;
			$.getJSON(url, function(data){
				$('#username-patron').val(data.username);
				$('#fullname-patron').val(data.name);
				$('#identificationnum-patron').val(data.nric);
				$('#email-patron').val(data.email);
				$('#mobilenum-patron').val(data.phone);
				$('#smsnotification-patron').prop('checked', data.sendSms);
				$('#emailnotification-patron').prop('checked', data.sendEmail);
			});
		}
	});
	
	$('#savePatron').click(function(){
		$('#patronform').submit();
		return false;
	});
	
	$('#deletePatron').click(function(){
		if($('#username-patron').val() != null && $('#username-patron').val() != ''){
			$('#valid-patron').val('0');
			$('#patronform').submit();
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