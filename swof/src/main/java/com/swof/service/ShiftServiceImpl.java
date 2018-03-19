package com.swof.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swof.dao.ShiftDaoIF;
import com.swof.exception.SWOFException;
import com.swof.models.Engineer;

@Service
public class ShiftServiceImpl implements ShiftServiceIF {

	@Autowired
	ShiftDaoIF shiftDaoIF;

	public List<Engineer> geShiftSchedule(String month, String year) throws SWOFException {

		return shiftDaoIF.geShiftSchedule(month, year);
	}

}
