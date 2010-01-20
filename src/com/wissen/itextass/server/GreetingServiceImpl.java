package com.wissen.itextass.server;

import java.awt.Color;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.txw2.Document;
import com.wissen.itextass.client.GreetingService;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sun.jkernel.BackgroundDownloader;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	com.itextpdf.text.Document doc;
	String s1 = "try.pdf";
	String s2 = "Pdf is Not Created succsessfully....";
	PdfPTable titleTable=new PdfPTable(1);
	PdfPTable addressTable=null;
	PdfPTable invoiceTable=null;
	PdfPTable add_invo_table;
	String DateString="1/1/2010",invoicenumber="001",custid;
	PdfPTable billTable,billto,comment,calculus,comm_cal_Table,footer;
	Paragraph footerPara;

	String custname,custadd,custcountry;
	double amount,rate,tax;
	float tax1,total;
	int invo_id;
	PdfWriter writer;
	
	 Font f= new Font(Font.HELVETICA,25,Font.BOLD);
     
	 
	public String greetServer() {
		
		doc = new com.itextpdf.text.Document();
		
		f.setColor(131, 148, 201);
		 try{
		       
		        
		        writer = PdfWriter.getInstance(doc,new FileOutputStream("try.pdf"));
		        doc.open();
		        
		        Phrase space = new Phrase("\n");
		        Rectangle r=doc.getPageSize();
		        System.out.println(r.getLeft());
		        System.out.println(r.getRight());
		        System.out.println(r.getBottom());
		        System.out.println(r.getTop());
		        
		        getValues();
		        
		        
		        setTitle();
		        setAddress();
		        setInvoice();
		        setBillto();
		        setBillTable();
		        setComment();
		        setCalculus();
		        setfooter();
		      
		        
		        
		        add_invo_table = new PdfPTable(2);
		        
		        PdfPCell add_invo_cell1 = new PdfPCell();
		        add_invo_cell1.addElement(addressTable);
		        add_invo_cell1.setBorder(0);
		        
		        PdfPCell add_invo_cell2 = new PdfPCell();
		        add_invo_cell2.addElement(invoiceTable);
		        add_invo_cell2.setBorder(0);
		        
		        PdfPCell add_invo_cell3 = new PdfPCell();
		        add_invo_cell3.addElement(billto);
		        add_invo_cell3.setBorder(0);
		        
		        PdfPCell add_invo_cell4 = new PdfPCell();
		        add_invo_cell4.addElement(new Phrase(""));
		        add_invo_cell4.setBorder(0);
		        
		        
		    
		        add_invo_table.addCell(add_invo_cell1);
		        add_invo_table.addCell(add_invo_cell2);
		        add_invo_table.addCell(add_invo_cell3);
		        add_invo_table.addCell(add_invo_cell4);
		    
		        
		        comm_cal_Table = new PdfPTable(2);
		        
		        PdfPCell commentcell = new PdfPCell();
		        commentcell.setBorder(0);
		        commentcell.addElement(new Phrase(""));
		        PdfPCell calculuscell = new PdfPCell();
		        calculuscell.setBorder(0);
		        calculuscell.addElement(calculus);
		        comm_cal_Table.addCell(commentcell);
		        comm_cal_Table.addCell(calculuscell);
		        
		        
		        
		        
		        
		        try {
			        doc.add(titleTable);
			        
			        doc.add(add_invo_table);
			        doc.add(space);			   
			        doc.add(space);
			        doc.add(billTable);
			        doc.add(comm_cal_Table);
			        doc.add(footer);
			        
			        
			    } catch (DocumentException e) {
			        
			        e.printStackTrace();
			        return(s2);
			    }
		        doc.close();
		        return(s1);
		        
		       
		        
		 }catch (Exception e) {
			System.out.println("Error Message :"+e.getMessage());
			 return(s2);
		 }
		
	}
	/**
	 * Method To set title of PDF
	 */
	void setTitle()
	{
		 
	      titleTable.setHorizontalAlignment(1);
	   
	      
	          
	      Phrase p=new Phrase("Sheet1");
	      
	      
	           
	      PdfPCell titleCell=new PdfPCell(p);
	      titleCell.setHorizontalAlignment(1);
	      titleCell.setBorder(0);
	      
	      titleTable.addCell(titleCell);
	      
	  
	   
	      
	}
	
	/**
	 * Method to set Address field of PDF
	 */
	
	void setAddress()
	{
		 	
	        addressTable=new PdfPTable(1);
	        addressTable.setHorizontalAlignment(0);
	        addressTable.setWidthPercentage(100);
	        
	        Font font = new Font(Font.HELVETICA, 15); 
	        
	        PdfPCell cell1=new PdfPCell(new Phrase("Wissen Labs", font));
	        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell1.setBorder(0);
	        PdfPCell cell4=new PdfPCell(new Phrase("	"));
	        cell4.setBorder(0);
	        PdfPCell cell5=new PdfPCell(new Phrase("	"));
	        cell5.setBorder(0);
	        PdfPCell cell2=new PdfPCell(new Phrase("4th Floor,Rajiv Enclave"));
	        cell2.setBorder(0);
	        PdfPCell cell3=new PdfPCell(new Phrase("New Pandit Colony,Nashik-13"));
	        cell3.setBorder(0);
	        PdfPCell cell6=new PdfPCell(new Phrase("\n"));
	        cell6.setBorder(0);
	        
	            
	        addressTable.addCell(cell1);
	        addressTable.addCell(cell4);
	        addressTable.addCell(cell5);
	        addressTable.addCell(cell2);
	        addressTable.addCell(cell3);
	        addressTable.addCell(cell6);
	        
	       
	}
	
	/**
	 * Method to set Invoice Information in PDF
	 */
	void setInvoice()
	{
		
		 	invoiceTable=new PdfPTable(1);
	        invoiceTable.setHorizontalAlignment(2);
	        invoiceTable.setWidthPercentage(100);
	        
	       
	        
	        PdfPCell invoiceCell1=new PdfPCell(new Phrase("INVOICE",f));
	        invoiceCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        invoiceCell1.setBorder(0);
	        
	        PdfPCell invoiceCell0=new PdfPCell(new Phrase(""));
	        invoiceCell0.setBorder(0);
	        
	        Calendar calendar = Calendar.getInstance();
	        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.US);
	        
	        
	        PdfPTable dateidTable = new PdfPTable(2);
	        dateidTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        
	        Font  f1 = new Font(Font.BOLD,12);
	        
	        PdfPCell invoiceCell2=new PdfPCell(new Phrase("Date : ",f1));
	        invoiceCell2.setBorder(0);
	       
	        
	        PdfPCell invoiceCell3=new PdfPCell(new Phrase(""+df.format(calendar.getTime())));
	        invoiceCell3.setBackgroundColor(new BaseColor(228,232,243));
	        invoiceCell3.setBorder(0);
	        invoiceCell3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell invoiceCell4=new PdfPCell(new Phrase("Invoice # : ",f1));
	        invoiceCell4.setBorder(0);
	       
	        PdfPCell invoiceCell5=new PdfPCell(new Phrase(""+invo_id));
	        invoiceCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        PdfPCell invoiceCell6=new PdfPCell(new Phrase("Customer ID : ",f1));
	        invoiceCell6.setBorder(0);
	        PdfPCell invoiceCell7=new PdfPCell(new Phrase(""+custid));
	        invoiceCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        
	        
	        dateidTable.addCell(invoiceCell2);
	        dateidTable.addCell(invoiceCell3);
	        dateidTable.addCell(invoiceCell4);
	        dateidTable.addCell(invoiceCell5);
	        dateidTable.addCell(invoiceCell6);
	        dateidTable.addCell(invoiceCell7);
	        
	        PdfPCell datecell = new PdfPCell();
	        datecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        datecell.setBorder(0);
	        datecell.addElement(dateidTable);
	       
	        invoiceTable.addCell(invoiceCell0);   
	        invoiceTable.addCell(invoiceCell0);   
	        invoiceTable.addCell(invoiceCell1);
	        invoiceTable.addCell(invoiceCell0);
	        invoiceTable.addCell(datecell);
	        
	}
	
	/**
	 * Method to set bill details in PDF
	 */
	 void setBillTable(){
		 
		 float w[]={80,20};
		 billTable=new PdfPTable(2);
         try {
			billTable.setTotalWidth(w);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         billTable.setHeaderRows(1);
         
        
         
         
         
     
         PdfPCell billTableCell1=new PdfPCell(new Phrase("DESCRIPTION",new Font(Font.STRIKETHRU, 12, Font.BOLD,BaseColor.WHITE)));
         billTableCell1.setBackgroundColor(new BaseColor(59,78,135));
         billTableCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         
         
         PdfPCell billTableCell2=new PdfPCell(new Phrase("AMOUNT",new Font(Font.STRIKETHRU, 12, Font.BOLD,BaseColor.WHITE)));
         billTableCell2.setBackgroundColor(new BaseColor(59,78,135));
         PdfPCell billTableCell3=new PdfPCell(new Phrase("Flight tickets for 3 persons traveling to Todi in July 2009"));
         billTableCell3.setExtraParagraphSpace(200);
         PdfPCell billTableCell4=new PdfPCell(new Phrase(""+amount));
         billTableCell4.setHorizontalAlignment(2);
         
         billTable.addCell(billTableCell1);
         billTable.addCell(billTableCell2);
         billTable.addCell(billTableCell3);
         billTable.addCell(billTableCell4);
         
        
         
     }
	 
	 /**
	  * Method to set name & address of bill to field
	  */
	 void setBillto()
	 {
		 billto=new PdfPTable(1);
		 billto.setHorizontalAlignment(0);
		 billto.setWidthPercentage(70);
		 
         PdfPCell billtoH,billtonm,billtoadd,billtocity;
         
         Font f2=new Font(Font.STRIKETHRU, 12, Font.BOLD);
         f2.setColor(BaseColor.WHITE);
         billtoH=new PdfPCell(new Phrase("BILL To ",f2));
         billtoH.setBackgroundColor(new BaseColor(59,78,135));
         billtoH.setBorder(0);
         billtonm=new PdfPCell(new Phrase(""+custname+""));
         billtonm.setBorder(0);
         billtoadd=new PdfPCell(new Phrase(""+custadd));
         billtoadd.setBorder(0);
         billtocity=new PdfPCell(new Phrase(""+custcountry+""));
         billtocity.setBorder(0);
         
         billto.addCell(billtoH);
         billto.addCell(billtonm);
         billto.addCell(billtoadd);
         billto.addCell(billtocity); 
         
        
        
	 }
	 
	 /**
	  * Method to set Comment table
	  */
	 void setComment()
	 {
		 comment = new PdfPTable(1);
		 comment.setWidthPercentage(100);
		 PdfPCell notify1=new PdfPCell(new Phrase(""));
         notify1.setBorder(0);
		 
		 PdfPCell cell1 = new PdfPCell(new Phrase("Other Comments",new Font(Font.STRIKETHRU, 12)));
		 
		 cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		 cell1.setBorder(0);
		 PdfPCell  cell2 = new PdfPCell(new Phrase("1. Total payment due in 30 days.\n2. Please include invoice number onyour check.",new Font(Font.STRIKETHRU,10)));
		 
		 
		 
		 
		
		 
		
		 
		 comment.addCell(notify1);
		 comment.addCell(cell1);
		 comment.addCell(cell2);
		 
		 
		 
		 
		 comment.setTotalWidth(200f);
	     comment.writeSelectedRows(0,-1,100,350,writer.getDirectContent());
	     
	     
		 
	 }
	 
	 /**
	  * Method to set Total,subtotal table
	  */
	 void setCalculus()
	 {
		 calculus = new PdfPTable(2); 
		 calculus.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 
		 
		 
		 PdfPCell subtoth,subtotd,taxrateh,taxrated,taxh,taxd,otherh,otherd,totalh,totald,notify1,notify2,notify3;
		 Font f= new Font(Font.STRIKETHRU,12);
		 f.setColor(BaseColor.BLACK);
		 
		
		
         subtoth=new PdfPCell(new Phrase("SUBTOTAL",f));
         subtoth.setBorder(0);
         taxrateh=new PdfPCell(new Phrase("TAX RATE",f));
         taxrateh.setBorder(0);
         otherh=new PdfPCell(new Phrase("OTHER",f));
         otherh.setBorder(0);
         totalh=new PdfPCell(new Phrase("TOTAL",f));
         
         totalh.setBorder(0);
         subtotd=new PdfPCell(new Phrase("$"+amount,f));
         subtotd.setBorder(0);
         subtotd.setBackgroundColor(new BaseColor(228,232,243));
         taxrated=new PdfPCell(new Phrase(""+rate+"%",f));
         taxrated.setBorder(0);
         taxh=new PdfPCell(new Phrase("TAX",f));
         taxh.setBorder(0);
         taxd=new PdfPCell(new Phrase("$"+tax1,f));
         taxd.setBorder(0);
         
         taxd.setBackgroundColor(new BaseColor(228,232,243));
         otherd=new PdfPCell(new Phrase("$0.0",f));
         otherd.setBorder(0);
        
         
        
         
         Font f1= new Font(Font.DEFAULTSIZE,12);
		 f.setColor(BaseColor.BLACK);
         
         totald=new PdfPCell(new Phrase("$"+total,f));
        
         totald.setBorder(0);
         totald.setBackgroundColor(new BaseColor(228,232,243));
         notify1=new PdfPCell(new Phrase(""));
         notify1.setBorder(0);
         notify1.setColspan(2);
         notify1.setHorizontalAlignment(Element.ALIGN_CENTER);
         notify2=new PdfPCell(new Phrase("Make checks payable to",new Font(Font.COURIER,10)));
         notify2.setBorder(0);
         notify2.setHorizontalAlignment(Element.ALIGN_CENTER);
         notify2.setColspan(2);
         notify3=new PdfPCell(new Phrase("Wissen Labs",new Font(Font.COURIER,12,Font.BOLD)));
         notify3.setBorder(0);
         notify3.setColspan(2);
         notify3.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         PdfPCell p1 = new PdfPCell();
         p1.setBorder(0);
         p1.setBorderWidthTop(1);
         
         PdfPCell p2 = new PdfPCell();
         p2.setBorder(0);
         p2.setBorderWidthTop(1);

         
         calculus.addCell(subtoth);
         calculus.addCell(subtotd);
         calculus.addCell(taxrateh);
         calculus.addCell(taxrated);
         
         calculus.addCell(taxh);
         calculus.addCell(taxd);
         calculus.addCell(otherh);
         calculus.addCell(otherd);
         calculus.addCell(p1);
         calculus.addCell(p2);
         calculus.addCell(totalh);
         calculus.addCell(totald);
         
         calculus.addCell(notify1);
         calculus.addCell(notify1);
         calculus.addCell(notify1);
         calculus.addCell(notify2);
         calculus.addCell(notify3);
         
	 }
	 
	 /**
	  * Method to set footer part of PDF
	  */
	 void setfooter()
	 {
		footer = new PdfPTable(1);
		 
		 PdfPCell cell = new PdfPCell(new Phrase("\n\n"));
		 cell.setBorder(0);
		 
		 PdfPCell paracell = new PdfPCell();
		 paracell.setBorder(0);
		 
		footerPara = new Paragraph("If you have any questions about this invoice, please contact.\n Sushrut Bidwai,+91 986 023 8124\n",new Font(Font.BOLD,12));
		footerPara.setAlignment(1);
		Font f = new Font(Font.BOLD,12);
		f.setColor(BaseColor.BLUE);
		Anchor anchor = new Anchor("sushrut@wissen.co.in",f);
		anchor.setReference("mailto:sushrut@wissen.co.in");
		
		footerPara.add(anchor);
		
		Phrase footerph = new Phrase("\n\n\nThank You For Your Business!",new Font(Font.ITALIC,14,Font.COURIER));
		footerPara.add(footerph);
		paracell.addElement(footerPara);
		
		footer.addCell(cell);
		footer.addCell(cell);
		footer.addCell(paracell);
		
		
		
       
	 }
	 
	 /**
	  * Method to get values from Database for PDF
	  */
	void getValues()
	 {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Invoice", "root", "wissen");
			java.sql.Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from Cust_invo where invo_id ="+101);
			rs.next();
			invo_id = 101;
			custid = rs.getString("cust_id");
			custname = rs.getString("cust_name");
			custadd = rs.getString("cust_add");
			custcountry = rs.getString("cust_country");
			amount = rs.getDouble("amt");
			rate = rs.getDouble("tax_rate");
			
			tax1 =  (float) ((rate/100) *amount);
			
			
			
			
			total = (float)(amount - tax1); 
			
			System.out.println("Cust id = "+custid);
			System.out.println("Cust id = "+custname);
			System.out.println("Cust id = "+custadd);
			System.out.println("Cust id = "+custcountry);
			System.out.println("Cust id = "+amount);
			System.out.println("Cust id = "+rate);
			System.out.println("Cust id = "+tax1);
			
			
			
			
			
		}catch(Exception e)
		{
			System.out.println("Error message : "+e.getMessage());
		}
	 }
	 
}
