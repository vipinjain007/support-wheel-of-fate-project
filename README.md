Support Wheel of Fate - API
A REST API that receives a request  with   Month and Year  and given all  engineers shift plan for first 2 weeks .
Details Endpoints
( Api ) - http://localhost:8085/swof/schedule/<month>/<year>
(Front End ) - http://localhost:8085/home/

Details Code Setup
[1]This Is maven base spring boot project So once we import maven project , update project  and run using springboot  you  are able to test  support wheel of fate  api using swagger
[2]Configuration(One time configuration)
File:application.properties [path:src\main\resources]
#This is one time configration
#set how many weeks to take in count to filter engineers
#Min value should be 2 
#eg 2:means 2 weeks (From schedule start date ).3 means 3 week ,4 means 4 weeks (one month)
swif.week_scan_period=2

#set which day you want to start the shift 1(Monday),2(Tuesday),3(Wednesday),4(Thrusday),5(Friday),6(Saturday),7(Sunday)
#This day  will be consider first day of Month
#eg  1->First Monday of Month(month you pass in API)
swif.schedule_start_day=1

#set which day you want to give week off  
#eg 1(Monday),2(Tuesday),3(Wednesday),4(Thrusday),5(Friday),6(Saturday),7(Sunday)
#note swif.schedule_start_day !=swif.weekend_off_day
#6,7:Saturday,Sunday (Max 2 days)
swif.weekend_off_day=6,7

#Set Moring shift time .This is is use only time information purpose
swif.day_shift_time=12PM TO 11PM

#Set Night shift time .This is is use only time information purpose
swif.night_shift_time=12 AM TO  11AM


#Default port is 8085 if you change port then swagger ,api ,web all url will access from that port
server.port=8085

Details  Application Url

 [1]Swagger Integration :

Swagger url : http://localhost:8085/swagger-ui.html 
This url Giving complete details about Shift Controller (schedule api)
 


[2]Web Integration :
WebUrl: http://localhost:8085/home/
 

:
Approach

1 DateTimeExtensions:
This utility having different function which give require details  using appliation.propery file

•	getShiftStartDate(mionth,year) :
This Function is return date  ,when shift should start .This function calculat date based on pass month ,year and schedule_start_day propery value
eg: Year 2018 ,Month:4 ,schedule_start_day :1 (Monday) ->Get First Monday date for April ,2018

•	getShiftWeekendOffdays :
This Function return day off name based on weekend_off_day propery value its max value must be 2 parameters like 6,7 and not >7.
eg: Suppose property values 6,7 then its return SATURADAY,SUNDAY .So latter on this days will exclude form shift days.

•	schedulePeriod:
This Function ruturn no of weeks for shift .Its get from week_scan_period property and return the same.

•	getRandomNumberList:
This function is retunrn 2 seperate list(day ,night ) for random numbers (which is not greater then working day list size) ,its use as index to get date from working days list

2.ShiftDaoImpl:
This Class is take care all major business logic.
•	getWorkingDates(String month,String year) :This Function is giving working days list based on no of weeks (schedulePeriod) -getShiftWeekendOffdays
•	geShiftSchedule(String month,String year):This function is core part of application and its crating all Engineers shift plan .
3.GenerateEngineersData:
This class is responsible to generate List<Engineer> from json txt file on application init level.
src\main\resources\json\engineersJson.txt





 

