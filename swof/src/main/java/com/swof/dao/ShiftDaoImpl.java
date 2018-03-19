package com.swof.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swof.config.GenerateEngineersData;
import com.swof.constants.Constants;
import com.swof.models.Engineer;
import com.swof.utils.DateTimeExtensions;

@Component
public class ShiftDaoImpl implements ShiftDaoIF{

	@Autowired
	GenerateEngineersData generateEngineersData; 	
    
	@Autowired
	DateTimeExtensions DateTimeExtensions;
	
	
public List<Engineer> geShiftSchedule(String month,String year){
	List<Engineer> availableEngineerList=generateEngineersData.getListFromJson();
	List<Engineer> scheduleEngineerList=new ArrayList<Engineer>();
	List<LocalDate> workingDatesList=getWorkingDates(month,year);
	List<List<Integer>> randomNumber=null;
	List<Integer> randomListDay=null;
	List<Integer> randomListNight=null;
	
	
	String dayShiftTimeDetails=DateTimeExtensions.dayShiftTime();
	String nightShiftTimeDetails=DateTimeExtensions.nightShiftTime();
	
	Engineer engineer=null;
	if(workingDatesList.size()>=availableEngineerList.size()) {
		randomNumber=DateTimeExtensions.getRandomNumberList(workingDatesList.size());
		randomListDay=randomNumber.get(0);
		randomListNight=randomNumber.get(1);
		for(int i=0;i<randomListDay.size();i++) {
			
				if (i < availableEngineerList.size()) {
					engineer = availableEngineerList.get(i);
					LocalDate shiftDayDate = workingDatesList.get(randomListDay.get(i));
					engineer.setShiftDayDate(shiftDayDate.toString());
					engineer.setShiftDay(shiftDayDate.getDayOfWeek().toString());
					LocalDate shiftNightDate = workingDatesList.get(randomListNight.get(i));

					engineer.setShiftNightDate(shiftNightDate.toString());
					engineer.setShiftNight(shiftNightDate.getDayOfWeek().toString());

				}
				engineer.setShiftDayTime(dayShiftTimeDetails);
				engineer.setShiftNightTime(nightShiftTimeDetails);
				scheduleEngineerList.add(engineer);
			}
		}
	
	
	
	return scheduleEngineerList;
}	

//Exclude Weekend off from shift schedule weeks
private List<LocalDate>	getWorkingDates(String month,String year){
	//no of weeks
	String schedulePeriod=DateTimeExtensions.schedulePeriod();
	
	//From application property file 1->Monday(First day of month),2-Tuesday(First day of month)
	LocalDate shiftStartDate=DateTimeExtensions.getShiftStartDate(month, year);
	List<LocalDate> workingLocalDates=new ArrayList<LocalDate>();
	//off days Max 2
	String weekendOffdays=DateTimeExtensions.getShiftWeekendOffdays();
	//calculate total  days based on no of weeks 
	int shiftdays=(7*(Integer.parseInt(schedulePeriod)));
	
	//calculate total working days exclude weekend off from shift schedule weeks
    for(int shiftstartday=0;shiftstartday<shiftdays;shiftstartday++) 
    {
	  if(!(weekendOffdays.contains(shiftStartDate.getDayOfWeek().toString()))) 
		   workingLocalDates.add(shiftStartDate);
		  
	   shiftStartDate = shiftStartDate.plusDays( 1 );
	  
    }
	
    //workingLocalDates .stream().forEach(System.out::println);
    return workingLocalDates;
	
	
}

}