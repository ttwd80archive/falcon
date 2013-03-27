$(function() {
	var NO_OF_YEARS = 2;
	var date = new Date();
	var options = "";
	for ( var i = 0; i < NO_OF_YEARS; i++) {
		var yearText = date.getFullYear() + i;
		options += '<option value="' + yearText + '">' + yearText + '</option>';
	}
	$('#chooseyear').html(options);
	$('#chooseyear option:nth(0)').attr("selected", "selected");
	$('#choosemonth').val(date.getMonth());
	renderSelectedMonth(date.getMonth(), date.getFullYear());
	$('#choosemonth').change(function() {
		var year = $('#chooseyear').val();
		var month = $(this).val();
		renderSelectedMonth(month, year);
	});
	$('#chooseyear').change(function() {
		var month = $('#choosemonth').val();
		var year = $(this).val();
		renderSelectedMonth(month, year);
	});
});

function renderSelectedMonth(month, year) {
	var date = new Date(year, month, 1);
	var monthNames = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var endDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	var endDay = endDays[month];
	if (month == 1) {
		var isLeap = new Date(date.getFullYear(), 1, 29).getMonth() == 1;
		if (isLeap) {
			endDay = endDay + 1;
		}
	}
	$('#selectedmonth').text(monthNames[date.getMonth()]);
	$('#selectedyear').text(year);
	var day = date.getDay();
	var dayCount = 1;
	var cellNo = 0;
	$('#tablebody tr').each(function() {
		$.each(this.cells, function() {
			if (cellNo < day) {
				$(this).text('');
				cellNo = cellNo + 1;
				return true;
			}
			if (dayCount > endDay) {
				$(this).text('');
				return true;
			}
			$(this).text(dayCount);
			dayCount = dayCount + 1;
		});
	});
};

