package com.swof.service;

import java.util.List;

import com.swof.exception.SWOFException;
import com.swof.models.Engineer;

public interface ShiftServiceIF {
   
	public List<Engineer> geShiftSchedule(String month,String year) throws SWOFException;
}
