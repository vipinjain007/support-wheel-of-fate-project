package com.swof.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.swof.constants.Constants;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

@Component
public class DateTimeExtensions {

	@Value("${swif.week_scan_period}")
	private String weekScanPeriod;

	@Value("${swif.schedule_start_day}")
	private String swiftStartDayofWeek;

	@Value("${swif.weekend_off_day}")
	private String weekendOff;

	@Value("${swif.night_shift_time}")
	private String nightShiftTime;

	@Value("${swif.day_shift_time}")
	private String dayShiftTime;

	public LocalDate getShiftStartDate(String month, String year) {

		LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		while (localDate.getDayOfWeek() != Constants.DAY_OF_WEEK.get(swiftStartDayofWeek)) {
			localDate = localDate.plusDays(1);
		}
		return localDate;
	}

	public String getShiftWeekendOffdays() {
		String[] weekendOffdays = weekendOff.split(",");
		String weekendOffDaysName = "";
		for (int day = 0; day < weekendOffdays.length; day++) {
			weekendOffDaysName = weekendOffDaysName
					.concat(Constants.DAY_OF_WEEK.get(weekendOffdays[day]).toString().concat(","));
		}
		return weekendOffDaysName;
	}

	public String schedulePeriod() {
		return weekScanPeriod;
	}

	public String dayShiftTime() {
		return dayShiftTime;
	}

	public String nightShiftTime() {
		return nightShiftTime;
	}

	// Generate Random Number to assign different shift to Engineer
	public List<List<Integer>> getRandomNumberList(int size) {

		List<List<Integer>> randomList = new ArrayList<List<Integer>>();
		List<Integer> morningList = new ArrayList<Integer>();
		List<Integer> nightList = new ArrayList<Integer>();

		
		for (int i = 0; i < size; i++) {
			morningList.add(i);

		}
		// Collections.shuffle(randomList);
		for (int i = 0; i < size; i++) {
			nightList.add(i);

		}
		Collections.shuffle(nightList);

		for (int i = 0; i < nightList.size(); i++) {
			if (morningList.get(i).equals(nightList.get(i)) && i != nightList.size() - 1) {
				interChange(nightList, i);
			} else if (morningList.get(i).equals(nightList.get(i)) && i == nightList.size() - 1) {
				interChangeLastIndex(nightList, i);
			}
		}

		randomList.add(morningList);
		randomList.add(nightList);

		return randomList;

	}

	private static void interChange(List<Integer> randomListNight, int index) {
		int temp = randomListNight.get(index);
		randomListNight.set(index, randomListNight.get(index + 1));
		randomListNight.set(index + 1, temp);
	}

	private static void interChangeLastIndex(List<Integer> randomListNight, int index) {
		int temp = randomListNight.get(index);
		randomListNight.set(index, randomListNight.get(index - 1));
		randomListNight.set(index - 1, temp);
	}

}
