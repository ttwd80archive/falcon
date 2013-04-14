$(function() {
	$('.remove').click(function(){
		var url = '../apppointment_fetch?id=' + $(this).attr('id');
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
});