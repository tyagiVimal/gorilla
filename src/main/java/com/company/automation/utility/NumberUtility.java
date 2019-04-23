
package com.company.automation.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NumberUtility {

	public static long generateRandomNumber(int noOfDigits) {
		Random rand = new Random();
		long end = 10L;
		long start = 1L;
		for (int i = 1; i < noOfDigits; i++) {
			end = end * 10;
			start = start * 10;
		}
		return start + ((long) (rand.nextDouble() * (end - start)));
	}

	public static List<Integer> getPinDigits(String str) {
		List<Integer> returnList = null;
		if (str.trim().split(":").length == 4) {
			returnList = new ArrayList<Integer>();
			Map<String, Integer> keypadButtons = new HashMap<String, Integer>();
			for (int p = 0; p <= 9; p++) {
				if (p == 0)
					keypadButtons.put(Integer.toString(p), 10);
				else
					keypadButtons.put(Integer.toString(p), p - 1);
			}
			keypadButtons.put("CLEAR", 9);
			keypadButtons.put("DELETE", 11);
			for (String tempStr : str.trim().split(":")) {
				if (keypadButtons.containsKey(tempStr))
					returnList.add((Integer) keypadButtons.get(tempStr));
			}
		}
		return returnList;
	}
}