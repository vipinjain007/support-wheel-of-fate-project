/**
 * 
 */
package com.swof.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swof.exception.SWOFException;
import com.swof.models.Engineer;
import com.swof.service.ShiftServiceIF;

/**
 * @author vipin
 *
 */
@Controller
@RequestMapping("/swof")
public class ShiftController {

	@Autowired
	ShiftServiceIF shiftService;
	
	@RequestMapping(value ="/hello-swof", method= RequestMethod.GET)
	@ResponseBody
    public String sayHello() {
        return "hello";
    }
	
	
	@RequestMapping(value ="/schedule/{month}/{year}", method= RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<List<Engineer>> getSchedule(@PathVariable("month") String month, 
            @PathVariable("year") String year) {
		List<Engineer> engineerShiftList=new ArrayList<Engineer>();
		try {
		 engineerShiftList=shiftService.geShiftSchedule(month, year);
		 if(engineerShiftList.size()<1) {
			 return new ResponseEntity<>(engineerShiftList,new HttpHeaders(), HttpStatus.NOT_FOUND); 
		 }
		 
		}catch (SWOFException e) {
			return new ResponseEntity<>(engineerShiftList,new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<>(engineerShiftList,new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(engineerShiftList,new HttpHeaders(), HttpStatus.OK);
    }
	
}
