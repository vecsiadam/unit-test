package com.task.stringnumtest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringNumGenerator {

	// bejön egy string
	public String addString(String input) {
		if (input.isEmpty()) {
			return "0";
		}
		if (!input.isEmpty()) {
			if (isPositiveOrZero(input) && isLessThanThousands(input)) {
				return input;
			}
			if (input.contains(",") || input.contains("\n")) {
				int sum = twoOrMoreNumbersSum(input);
				return String.valueOf(sum);
			}
		}
		return "";
	}

	// szám?
	public boolean isNumeric(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// kisebb mint 1000?
	public boolean isLessThanThousands(String input) {
		if (!input.isEmpty() && isNumeric(input)) {
			int number = Integer.parseInt(input);
			if (number <= 1000) {
				return true;
			}
		}
		return false;
	}

	// pozitív vagy 0?
	public boolean isPositiveOrZero(String input) {
		if (!input.isEmpty() && isNumeric(input)) {
			int number = Integer.parseInt(input);
			if (0 <= number) {
				return true;
			}
		}
		return false;
	}

	// hogyha 2 vagy több szám között vessző van, vagy új sor akkor összeadja
	public int twoOrMoreNumbersSum(String input) {
		int sum = 0;
		if (input.contains(",") || input.contains("\n")) {
			List<Integer> numbers = splitString(input);
			for (int i = 0; i < numbers.size(); i++) {
				sum += numbers.get(i);
			}
		}
		return sum;
	}

	// listába gyűjti a vesszővel elválasztott vagy új sorban lévő számokat
	public List<Integer> splitString(String input) {
		if (input.startsWith("//") && !input.contains(",")) {
			Matcher m = Pattern.compile("//\\[(.*)\\]\n(.*)").matcher(input);
			m.matches();
			String customDelimiterString = m.group(1);
			String numbers = m.group(2);
			return listOfNumbers(numbers.split(Pattern.quote(customDelimiterString)));

		} else if (input.startsWith("//") && input.contains(",")) {
			Matcher m = Pattern.compile("//\\[(.*)\\]\\,\\[(.*)\\]\n(.*)").matcher(input);
			m.matches();
			String customDelimiterString = m.group(1);
			String secondCustomDelimiterString = m.group(2);
			String numbers = m.group(3);
			String[] result = numbers
					.split(Pattern.quote(secondCustomDelimiterString) + "|" + Pattern.quote(customDelimiterString));
			return listOfNumbers(result);
		} else {
			String[] inputNumbers = input.split(",|\n");
			return listOfNumbers(inputNumbers);
		}
	}

	// számok listába gyűtése
	public List<Integer> listOfNumbers(String[] inputArray) {
		List<Integer> numberList = new ArrayList<Integer>();
		String[] inputNumbers = inputArray;
		for (String num : inputNumbers) {
			if (isNumeric(num) && isPositiveOrZero(num) && isLessThanThousands(num)) {
				numberList.add(Integer.parseInt(num));
			}
		}
		return numberList;
	}
}
