package com.task.stringnumtest;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Adjon meg egy stringet:");
		StringNumGenerator stringNumGenerator = new StringNumGenerator();
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		System.out.println(stringNumGenerator.addString(input));
		in.close();		
	}

}
