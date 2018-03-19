
$(document).ready(function(){
	var numbers = [1, 2, 3, 4, 5];
	var option = '';
	for (var i=0;i<numbers.length;i++){
	   option += '<option value="'+ numbers[i] + '">' + numbers[i] + '</option>';
	}
	$('#month').append(option);
	
	
});

function getSchedule(){
	
	var selectedMonth = $(".availMonthDropdown option:selected").val();
	var selectYear=$(".availYearDropdown option:selected").val();
	
	 if(selectedMonth=="" || selectYear=="" ){
		 alert("Please select Month and Year");
		 return;
	 }
	
	   var result="";
		$.ajax({
        url: '/swof/schedule/'+selectedMonth+'/'+selectYear+'',
        type: 'GET',

        contentType: 'application/json; charset=utf-8',
        success: function (engineerList, textStatus, xhr) {
             $.each(engineerList, function(index, engineer) {  
            	 
            	 result=result.concat( '<tr><td style="width: 20%">' + engineer.name + '</td>' );
            	 result=result.concat( '<td style="width: 10%">' + engineer.id + '</td>' );
            	 result=result.concat( '<td style="width: 30%">' + engineer.shiftDayDate +'('+ engineer.shiftDay +'\\'+engineer.shiftDayTime+')'+'</td>' );
            	 result=result.concat( '<td style="width: 30%">' + engineer.shiftNightDate +'('+ engineer.shiftNight +'\\'+engineer.shiftNightTime+')'+'</td></tr>' );
            	});
                 $('#result_data').html( result);            	 
        },
        error: function (data, textStatus, xhr) {
             $('#result_data').append( '<td colspan="4">Looks like there is no data available or Internal Api error</td>');
        }
    })
}	   
	    