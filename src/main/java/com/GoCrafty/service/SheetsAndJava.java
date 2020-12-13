package com.GoCrafty.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoCounter;

public class SheetsAndJava {
	
	private static int total = 0;
	private static int obtained = 0;

	public static Sheets sheetsService;
	private static String Application_Name = "Quiz responses";
//	private static String SpreadSheet_ID = "1dXUrRTzQXVjuRohTgL9boUCOPdnU2V0KxKz0taKO0K8";
	
	//Exchange Oauth to grant application exchange
	private static Credential authorize() throws IOException, GeneralSecurityException {
		InputStream in = new FileInputStream(SheetsAndJava.class.getProtectionDomain().getCodeSource().getLocation()+"/../../resources/credentials/credentials.json");
//		InputStream in = new FileInputStream("C:/Users\\harsh\\eclipse-workspace\\GoCrafty/src/main/webapp/resources/credentials/credentials.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
		List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
				.setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		return credential;
		
	}
	
	public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		Credential credential = authorize();
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName(Application_Name)
				.build();
	}
	
	public static float getGrades(String email, String response_ID) throws IOException, GeneralSecurityException {
		System.out.println(email +" "+ response_ID);
		sheetsService = getSheetsService();
		String range = "A2:E10";
		ValueRange resp = sheetsService.spreadsheets().values()
				.get(response_ID,range)
				.execute();
		List<List<Object>> values = resp.getValues();
		if(values == null || values.isEmpty()) {
			System.out.println("No data found");
		} else {
			total = obtained = 0;
			for(List<?> row : values) {
				System.out.println(row.get(0)+" " +row.get(1)+" " +row.get(2)+" " +row.get(3)+" " +row.get(4));
				if (row.get(2).equals(email)) {
					getPercentage(row.get(1).toString());	
				}
			}
		}
		return total == 0 ? 0 : obtained*100/total;
	}
	 public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
			 throws MalformedURLException, IOException {
			 BufferedInputStream in = null;
			 FileOutputStream fout = null;
			 try {
			 in = new BufferedInputStream(new URL(fileUrl).openStream());
			 fout = new FileOutputStream(fileName);

			byte data[] = new byte[1024];
			 int count;
			 while ((count = in.read(data, 0, 1024)) != -1) {
			 fout.write(data, 0, count);
			 }
			 } finally {
			 if (in != null)
			 in.close();
			 if (fout != null)
			 fout.close();
			 }
			 }
	public static void main(String[] args) {
		
		
		try {
			float score = getGrades("harshshah0110@gmail.com", "1mHBWvYDdyA5whM5x1JY-HuckKfqqUcBXrKaedVS2fLY");
			PdfCreator.genrateCertificate("Harsh Shah", "course", "instructor", score+"");
//			saveFileFromUrlWithJavaIO("logo.png", "file:/C:/Users/harsh/eclipse-workspace/GoCrafty/src/main/webapp/resources/images/");
		}
		 catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getPercentage(String score) {
		String[] scores = score.split("/");
		obtained += Integer.parseInt(scores[0].trim());
		total += Integer.parseInt(scores[1].trim());
	}
}
