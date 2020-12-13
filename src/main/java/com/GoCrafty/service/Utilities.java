package com.GoCrafty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utilities {

	public static HashMap<String, String> getVideoLinks(String videoLink) {
		HashMap<String, String> videos=new HashMap<String, String>();
		try {
		String[] videoLinks=videoLink.split(",");  //split videos by , 
		for(String video:videoLinks)
		{
			String[] nameAndLink=video.split("@");//split link by : to get name and link
			videos.put(nameAndLink[0], nameAndLink[1]);
		}
		}
		catch (Exception e) {
				videos.put("null", null);
				return videos;
		}
		return videos;
	}
	
	public static HashMap<String, String> getQuizLinks(String quizLink) {
		HashMap<String, String> quiz=new HashMap<String, String>();
		try {
		String[] quizLinks=quizLink.split(",");  //split videos by , 
		for(String video:quizLinks)
		{
			String[] nameAndLink=video.split("@");//split link by : to get name and link
			quiz.put(nameAndLink[0], nameAndLink[1]);
		}
		}
		catch (Exception e) {

				quiz.put("null", null);
				return quiz;
		}
		return quiz;
	}

	public static String getEmbededLink(String videoURL) {
		String[] temp2;
		if(!videoURL.contains("form")) {
//			System.out.println("video url: "+videoURL);
			String[] temp=videoURL.split("\\?v=");
			temp2=temp[1].split("&");
		}
		else {
//			System.out.println("Form url: "+videoURL);
			String[] temp=videoURL.split("/e/");
			temp2=temp[1].split("/viewform");
		}
		return temp2[0];
	}
	
	public static List<String> getResponseLink(String responseLinks) {
		
		List<String> sheetsID = new ArrayList<String>();
		String[] responseLinkSplit = responseLinks.split(",");  //split response links by ,
		System.out.println(responseLinkSplit[0]);
		for(String responseLink : responseLinkSplit) {
			if(responseLink.contains("spreadsheets")) {
				System.out.println("Form url: "+responseLink);
				String[] temp=responseLink.split("/d/");
				sheetsID.add(temp[1].split("/edit")[0]);
			}
		}
		return sheetsID;
	}
	
	

}
