package com.GoCrafty.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
 


public class PdfCreator {
	
	public static void genrateCertificate(String studentName,String courseName,String instrucutorName, String percentage) throws DocumentException, IOException
	{
		
		Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		PdfWriter.getInstance(document, new FileOutputStream(PdfCreator.class.getProtectionDomain().getCodeSource().getLocation()+"/../../resources/certificate.pdf"));
//		PdfWriter.getInstance(document, new FileOutputStream("C:/Users\\harsh\\eclipse-workspace\\GoCrafty/src/main/webapp/resources/certificates/certificate.pdf"));

		document.open();
		Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 50,
	            Font.BOLDITALIC,BaseColor.BLUE);
		Paragraph preface = new Paragraph("Certificate of Completion", headingFont);
		preface.setAlignment(Element.ALIGN_CENTER);
		
		
		
		
		Font paraFont = new Font(Font.FontFamily.TIMES_ROMAN, 30,
	            Font.ITALIC,BaseColor.BLUE);
		Paragraph preface2 = new Paragraph("This is to certify that "+studentName+" successfully completed "+courseName
				+" on Go Crafty online course on "+dateFormat.format(date)+" with a percentage score of " +percentage, paraFont);
		
		
		Font signFont = new Font(Font.FontFamily.COURIER, 20,
	            Font.BOLD,BaseColor.BLUE);
		Paragraph preface3 = new Paragraph(instrucutorName,signFont);
		
		Font signFont2 = new Font(Font.FontFamily.COURIER, 10,
	            Font.ITALIC,BaseColor.BLUE);
		Paragraph preface4 = new Paragraph("Go Crafty Team, Instructor",signFont2);
		
		preface.setAlignment(Element.ALIGN_CENTER);
		preface2.setAlignment(Element.ALIGN_CENTER);
		preface3.setAlignment(Element.ALIGN_CENTER);
		preface4.setAlignment(Element.ALIGN_CENTER);
		
		
		//img block
		
		String filename =  PdfCreator.class.getProtectionDomain().getCodeSource().getLocation()+"/../../resources/images/logo.png";
//		String filename = "C:\\Users\\harsh\\eclipse-workspace\\GoCrafty/src/main/webapp/resources/images/logo.png";
        Image image = Image.getInstance(filename);
        image.setAlignment(1);  	
	      
		//img block
        
		//border
		Rectangle rect= new Rectangle(825,575,18,15); // you can resize rectangle 
	     rect.enableBorderSide(1);
	     rect.enableBorderSide(2);
	     rect.enableBorderSide(4);
	     rect.enableBorderSide(8);
	     rect.setBorderColor(BaseColor.BLACK);
	     rect.setBorderWidth(3);
	     addEmptyLine(preface, 4);
	     document.add(rect);
	     document.add(preface);
	     
	     addEmptyLine(preface2, 3);
	     document.add(preface2);
	     
	     addEmptyLine(preface3, 1);
	     document.add(preface3);
	     
	     document.add(preface4);
	     document.add(image);   
	     
	   //border
		
		document.close();
	}
	
	 private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }

}
