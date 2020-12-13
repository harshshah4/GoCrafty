package com.GoCrafty.service;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Encryption {
	public String decrypt(String toDecrypt)
	{
		ArrayList<String> decryptedText=new ArrayList<String>();
		char[] de= new char[38];
		for(int i=0;i<38;i++)
		{
			de[i]=(toDecrypt.charAt(i));
		}
	
		//int actuaLen =Integer.parseInt(Character.toString(de[24]));
		
		//******get Length of orignal text
		StringBuffer temp = new StringBuffer();
		int once =Integer.parseInt(String.valueOf(de[36]));
		temp.append(once);

		int tens =Integer.parseInt(String.valueOf(de[37]));
		temp.append(tens);
		String temp2=temp.toString();
		int lengthOfOrignalText = Integer.parseInt(temp2);
		
		//System.out.println("lengthOfOrignalText"+lengthOfOrignalText);
		//******
		int lap;
		try {
		lap=38/lengthOfOrignalText;
		}
		catch (ArithmeticException e) {
			return null;
		}
		for (int i=0,j=0;j<lengthOfOrignalText;i=i+lap,++j)
		{
			char a=(char) (de[i]-lengthOfOrignalText);
			 decryptedText.add(j,Character.toString(a));
			//decryptedText.add(j,Character.toString(de[i]));
		}
		

		 StringBuffer sb2 = new StringBuffer();

			for(String ch:decryptedText)
			{
				sb2.append(ch);
			}
		      String finalDecryptedtext = sb2.toString();
		return finalDecryptedtext;
	}
	
	public String encrypt(String toEncr)
	{

		ArrayList<String> en=new ArrayList<String>();
		for(int i=0;i<38;i++)
			
		{
			//first approach
//			 int rand_int1 = rand.nextInt(1);
//			 String strAsciiTab = Character.toString((char) rand_int1);
			
			//second approach
			int randomNum = ThreadLocalRandom.current().nextInt(33, 126);
			 String strAsciiTab = Character.toString((char) randomNum);
			 en.add(i,strAsciiTab);
			//en.add(i, "-");
		}
		int len=toEncr.length(),lap;
		try {
		 lap= 38/len;
		}
		catch (ArithmeticException e) {
			return null;
		}
		char[] stringToArr= toEncr.toCharArray();
		
		
		for (int i=0,j=0;j<len;i=i+lap,++j)
		{
			char encryptedChar=(char) (stringToArr[j]+len);
			en.set(i, Character.toString(encryptedChar));
				//en.set(i,  Character.toString(stringToArr[j]));//inserting actual element at its place
		}
		
		if(len>9)
		{
			String a=String.valueOf(len);
			int once=Integer.parseInt(String.valueOf(a.charAt(0)));
			int  tens=Integer.parseInt(String.valueOf(a.charAt(1)));
			en.set(36,String.valueOf(once));
			en.set(37,String.valueOf(tens));
		}
		else
		{
			en.set(36, "0");
			en.set(37, String.valueOf(len));
		}
		StringBuffer sb = new StringBuffer();
		for(String temp:en)
		{
			sb.append(temp);
		}
	      String encrypted = sb.toString();
		
		return encrypted;
	}

}
