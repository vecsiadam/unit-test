package com.task.stringnumtest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringNumGeneratorTest {
	
	StringNumGenerator stringNumGenerator = new StringNumGenerator();
	
	@Test
	public void stringIsEmptyReturnsZero() {
		assertEquals("0", stringNumGenerator.addString(""));
	}
	
	@Test
	public void stringIsNumberReturnsNumber() {
		assertEquals("200.01", stringNumGenerator.addString("200.01"));
	}
	
	@Test
	public void stringIsBigerThanThousandsReturnsNothing() {
		assertEquals("", stringNumGenerator.addString("2000"));
	}
	
	@Test
	public void stringTwoNumbersReturnsSumDelimitedByComma() {
		assertEquals("9.4",stringNumGenerator.addString("5,4.4"));
	}
	
	@Test
	public void stringMoreNumbersReturnsSumDelimitedByComma() {
		assertEquals("16.5",stringNumGenerator.addString("5.5,4,7"));
	}
	
	@Test
	public void stringMoreNumbersReturnsSumDelimitedByCommaAndNewLine() {
		assertEquals("22.3",stringNumGenerator.addString("5\n6,4.3\n7"));
	}
	
	@Test
	public void stringMoreNumbersReturnsSumDelimitedByCommaAndNewLineBigerThanThousandAndMinus() {
		assertEquals("17.0",stringNumGenerator.addString("-5\n6,4\n7,10001"));
	}
	
	
	@Test
	public void createCustomDelimiter() {
		assertEquals("13.0",stringNumGenerator.addString("//[;]\n7;6"));
	}
	
	@Test
	public void customDelimiterIsSpecialChar() {
		assertEquals("6.0",stringNumGenerator.addString("//[###]\n1###2###3"));
	}
	
	@Test
	public void customDelimiterIsSpecialCharWithnegativeNumberAndBigerThanThousands() {
		assertEquals("3.0",stringNumGenerator.addString("//[**]\n1010**-2**1**2"));
	}
	
	@Test
	public void multipleDelimitersWithNegativeAndBigerThanThousands() {
		assertEquals("14.0",stringNumGenerator.addString("//[;;],[*]\n1010*-2;;1;;2*4;;5*2"));
	}
}