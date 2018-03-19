package com.swof.dao;

import java.util.List;

import com.swof.exception.SWOFException;
import com.swof.models.Engineer;

public interface ShiftDaoIF {
	
	public List<Engineer> geShiftSchedule(String month, String year) throws SWOFException;
	
}
