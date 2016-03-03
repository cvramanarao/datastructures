package com.javaassets.training.simple;


import java.util.Arrays;

public class SimpleMain {

	public static void main(String[] args) {
		
		String fromNameAndAddress = " Manoj Kumar <manoj@graphustest.com> ";
		String toNameAndAddress = " <vishal@graphustest.com> ";
		
		String[] fromNames = fromNameAndAddress.split("<");
		String fromName = fromNames[0].trim();
		String fromAddress = fromNames[1].replace('>', ' ').trim();
		System.out.println(Arrays.toString(fromNames));
		System.out.println(fromName+" <--> "+fromAddress);
		
		String[] toNames = toNameAndAddress.split("<");
		
		if(toNames.length > 1) {
			String toName = toNames[0].trim();
			String toAddress = toNames[1].replace('>', ' ').trim();
			System.out.println(Arrays.toString(toNames));
			System.out.println(toName+" <--> "+toAddress);
		} else {
			String toAddress = toNameAndAddress;
		}
	}

}
