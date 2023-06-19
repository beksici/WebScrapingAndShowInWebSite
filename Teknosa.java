
import java.io.IOException;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.element.Element;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class Teknosa {

	public static void main(String[] args) {
		
	//	String url = "https://www.teknosa.com/laptop-notebook-c-116004";"https://www.teknosa.com/laptop-notebook-c-116004?s=%3Arelevance&page="+i;
		 ArrayList<String> urunLinkleri = new ArrayList<String>();
		 ArrayList<String> urunTitles = new ArrayList<String>();
		 ArrayList<String> fiyatlar = new ArrayList<String>();
		 ArrayList<String> markalar = new ArrayList<String>();
		 ArrayList<String> yildizlar = new ArrayList<String>();
		 ArrayList<String> dos = new ArrayList<String>();
		 ArrayList<String> site = new ArrayList<String>();
		 ArrayList<String> ekranBoyutu = new ArrayList<String>();
		 ArrayList<String> ram = new ArrayList<String>();
		 ArrayList<String> Ssd = new ArrayList<String>();
		 ArrayList<String> Hdd = new ArrayList<String>();
		 ArrayList<String> markasizTitles = new ArrayList<String>();
		 ArrayList<String> model = new ArrayList<String>();
		 ArrayList<String> islemci = new ArrayList<String>();
		 ArrayList<String> islemciTipi =new ArrayList<String>();
		 ArrayList<String> islemciNesil =new ArrayList<String>();
		 ArrayList<String> urunImage =new ArrayList<String>();
		 int boslukIndeksi,indeks;
		 String ssdBoyut = "-";
		 String cleaner;
		 int b;
		 String hddBoyut = "-";
		 int sonIndeks,basIndeks;
		  
		 String mainUrl ="https://www.teknosa.com/";
		 for(int i=0;i<1;i++) {
		
			String url1;
			if(i==0)
				url1="https://www.teknosa.com/laptop-notebook-c-116004";
			else
				url1="https://www.teknosa.com/laptop-notebook-c-116004?s=%3Arelevance&page="+i;
			System.out.println(url1);
		  
		try {
			final Document document = Jsoup.connect(url1).get();
			Elements elements = document.getElementsByClass("prd-link");
			for (org.jsoup.nodes.Element element : elements) {
				// System.out.println( element.getElementsByClass("list-ul").text());

				urunLinkleri.add(mainUrl + element.attr("href").toString());
				
				site.add("Teknosa");
				yildizlar.add("0");

			}
		
			
			 
			 Elements images = document.select("#product-item > div > div.prd-media > figure > img"); 
			 int k=0;
			 for (org.jsoup.nodes.Element image : images) {
				  if(k<4) {
					 urunImage.add(image.attr("src"));
				  }
				  
				  else {
					  urunImage.add(image.attr("data-srcset"));
				  }
				k++ ;
			 }							
			
			 
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	for (int i = 0; i < urunLinkleri.size(); i++) {
		
		
		String url = mainUrl+urunLinkleri.get(i);
		
		try {
			Document document = Jsoup.connect(url).get();
			Elements marka = document.getElementsByTag("h1");
			for (org.jsoup.nodes.Element element : marka) {
				urunTitles.add(element.text().toString());
									markalar.add(element.getElementsByTag("b").text());
									
								}
			
			Elements fiyatElement = document.getElementsByClass("pdp-prc2");

			for (org.jsoup.nodes.Element element : fiyatElement) {

			fiyatlar.add(element.text());
			}
			

			
			
			
		}
		
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		if (urunTitles.get(i).contains("FreeDOS")||urunTitles.get(i).contains("FreeDos"))
			dos.add("FreeDOS");
		else if(urunTitles.get(i).contains("Windows")|| urunTitles.get(i).contains("W11H")||
				urunTitles.get(i).contains("W10")||urunTitles.get(i).contains("W11")||
				urunTitles.get(i).contains("Win10") || urunTitles.get(i).contains("W10H") ||urunTitles.get(i).contains("Win11")||urunTitles.get(i).contains("WIN11"))
			dos.add("Windows");
		else if(urunTitles.get(i).contains("MacOS")||urunTitles.get(i).contains("macOS"))
			dos.add("MacOS");
		else if(urunTitles.get(i).contains("Linux"))
		dos.add("Linux");
		else if(urunTitles.get(i).contains("Ubuntu"))
			dos.add("Ubuntu");
		else dos.add("-");
		//TODO koşul koy tırnak olmayan var
		if(urunTitles.get(i).contains("\"")) {
		String ekran = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("\"")-4), (urunTitles.get(i).indexOf("\""))).toString();
		if(ekran.contains(" "))
		{boslukIndeksi = ekran.indexOf(" ");
		ekranBoyutu.add(ekran.substring(boslukIndeksi+1));
	 }
		else {
			ekranBoyutu.add(ekran);
		} 
	//System.out.println(ekranBoyutu.get(i));
		}else {
			ekranBoyutu.add("-");
			
		}
		//TODO Ram kelimesi olmayan var koşul koy
		if(urunTitles.get(i).contains("RAM")) {
		String rams = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("RAM")-6), (urunTitles.get(i).indexOf("RAM"))).toString();
	String clean=	rams.replaceAll("\\D+","");
	
	int a= Integer.parseInt(clean);
	ram.add(Integer.toString(a));
//System.out.println(ram.get(i));	
	}else if(urunTitles.get(i).contains("Ram")) {
		String rams = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("Ram")-6), (urunTitles.get(i).indexOf("Ram"))).toString();
		String clean=	rams.replaceAll("\\D+","");
		
		int a= Integer.parseInt(clean);
		ram.add(Integer.toString(a));
		
		
	}
	else {
		ram.add("-");
		
	}
		
		
	//TODO SSD Ssd HDD Hdd gibi kayıtlar var ikisi birlikte olanda var koşulları ayarla 
	if(urunTitles.get(i).contains("SSD") && urunTitles.get(i).contains("HDD"))
	{
		ssdBoyut = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("SSD")-7), (urunTitles.get(i).indexOf("SSD"))).toString();
		cleaner=	ssdBoyut.replaceAll("\\D+","");
		b= Integer.parseInt(cleaner);
		Ssd.add(Integer.toString(b));
		 hddBoyut = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("HDD")-7), (urunTitles.get(i).indexOf("HDD"))).toString();
		cleaner = hddBoyut.replaceAll("\\D+","");
		b = Integer.parseInt(cleaner);
		Hdd.add(Integer.toString(b));
		
	}
	else if(urunTitles.get(i).contains("Ssd")&& urunTitles.get(i).contains("Hdd")) {
		ssdBoyut = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("Ssd")-7), (urunTitles.get(i).indexOf("Ssd"))).toString();
		cleaner=	ssdBoyut.replaceAll("\\D+","");
		 b= Integer.parseInt(cleaner);
		Ssd.add(Integer.toString(b));
	  hddBoyut = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("Hdd")-7), (urunTitles.get(i).indexOf("Hdd"))).toString();
		cleaner = hddBoyut.replaceAll("\\D+","");
		b = Integer.parseInt(cleaner);
		Hdd.add(Integer.toString(b));
		
		
		
	}
	
	else if(urunTitles.get(i).contains("SSD")) {
		if(urunTitles.get(i).contains("NVMe")) {
			Ssd.add("256");
			Hdd.add("-");
		}
		else {
	ssdBoyut = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("SSD")-7), (urunTitles.get(i).indexOf("SSD"))).toString();
	cleaner=ssdBoyut.replaceAll("\\D+","");
	 b= Integer.parseInt(cleaner);
	Ssd.add(Integer.toString(b));
	Hdd.add("-");}
	
	}
	else if(urunTitles.get(i).contains("Ssd")){
		ssdBoyut = urunTitles.get(i).subSequence((urunTitles.get(i).indexOf("Ssd")-7), (urunTitles.get(i).indexOf("Ssd"))).toString();
		cleaner=	ssdBoyut.replaceAll("\\D+","");
		 b= Integer.parseInt(cleaner);
		Ssd.add(Integer.toString(b));
		Hdd.add("-");
		
		
		
	}
	
	
	else if (urunTitles.get(i).contains("HDD") )
	{
		hddBoyut = urunTitles.get(i).substring((urunTitles.get(i).indexOf("HDD")-7), (urunTitles.get(i).indexOf("HDD")));	
	 cleaner=	hddBoyut.replaceAll("\\D+","");
	 b= Integer.parseInt(cleaner);
	Hdd.add(Integer.toString(b));
Ssd.add("-");
	}
	else if(urunTitles.get(i).contains("Hdd")) {
		hddBoyut = urunTitles.get(i).substring((urunTitles.get(i).indexOf("Hdd")-7), (urunTitles.get(i).indexOf("Hdd")));	
		 cleaner=	hddBoyut.replaceAll("\\D+","");
		 b= Integer.parseInt(cleaner);
	 Hdd.add(Integer.toString(b));
	 Ssd.add("-");
		}
	else {
		Ssd.add(ssdBoyut);
		Hdd.add(hddBoyut);
		
		
	}

	boslukIndeksi = urunTitles.get(i).indexOf(" ");
	
markasizTitles.add(urunTitles.get(i).substring(boslukIndeksi+1));
	
	
	if(markasizTitles.get(i).contains("Intel")) {
	indeks = markasizTitles.get(i).indexOf("Intel") - 1;
	model.add(markasizTitles.get(i).substring(0, indeks));
	
	}else if(markasizTitles.get(i).contains("i7") &&(!markasizTitles.get(i).contains("Intel"))) {
		
		
		indeks = markasizTitles.get(i).indexOf("i7") - 1;
		model.add(markasizTitles.get(i).substring(0, indeks));
		
	}
	else if(markasizTitles.get(i).contains("AMD")) {
		indeks = markasizTitles.get(i).indexOf("AMD") - 1;
		model.add(markasizTitles.get(i).substring(0, indeks));
		}
	else if(markasizTitles.get(i).contains("M1")) {
	
		indeks = markasizTitles.get(i).indexOf("M1") - 1;
		model.add(markasizTitles.get(i).substring(0, indeks));
		}
	else if(markasizTitles.get(i).contains("M2")) {
		indeks = markasizTitles.get(i).indexOf("M2") - 1;
		model.add(markasizTitles.get(i).substring(0, indeks));
		}
	else if(markasizTitles.get(i).contains("Ryzen") &&  (!markasizTitles.get(i).contains("AMD")))
	{
		indeks = markasizTitles.get(i).indexOf("Ryzen") - 1;
		model.add(markasizTitles.get(i).substring(0, indeks));
		}
	else model.add("-");
	
	
	

	sonIndeks =markasizTitles.get(i).indexOf("\"")-3;
	if(sonIndeks ==-4)
	{islemci.add("-");
		islemciTipi.add("-");
		islemciNesil.add("-");
	}
	else if(markasizTitles.get(i).contains("Intel")) {
   
		basIndeks = markasizTitles.get(i).indexOf("Intel");
	    islemci.add( markasizTitles.get(i).substring(basIndeks, sonIndeks));
	    int islemciBasIndeks= islemci.get(i).indexOf("i");
	    if(islemciBasIndeks==-1) {
	    islemciTipi.add(islemci.get(i));
	    islemciNesil.add("-");
	    }
	    else if((islemciBasIndeks+5)> islemci.get(i).length())
	    {
	    	islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+2));
	    	 islemciNesil.add("-");
	    }
	    else {
	    islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+2));
	    islemciNesil.add(islemci.get(i).substring(islemciBasIndeks+3, islemciBasIndeks+5));
	    }
	}
else if(markasizTitles.get(i).contains("i7") &&(!markasizTitles.get(i).contains("Intel"))) {
		
	basIndeks = markasizTitles.get(i).indexOf("i7");
		islemci.add(markasizTitles.get(i).substring(basIndeks, sonIndeks));
		int islemciBasIndeks= islemci.get(i).indexOf("i");
		islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+2));
		islemciNesil.add(islemci.get(i).substring(islemciBasIndeks+3, islemciBasIndeks+5));
		
	}else if(markasizTitles.get(i).contains("AMD"))
	{
		
		basIndeks = markasizTitles.get(i).indexOf("AMD");
		islemci.add(markasizTitles.get(i).substring(basIndeks, sonIndeks));
		if(islemci.get(i).contains("Ryzen")) {
			int islemciBasIndeks= islemci.get(i).indexOf("Ryzen");
			islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+7));
			islemciNesil.add(islemci.get(i).substring(islemciBasIndeks+8, islemciBasIndeks+9));
		}else {
			islemciTipi.add("-");
			islemciNesil.add("-");
		}
		
		
		}
	else if(markasizTitles.get(i).contains("M1")) {
		basIndeks = markasizTitles.get(i).indexOf("M1");
		islemci.add(markasizTitles.get(i).substring(basIndeks, sonIndeks));
		int islemciBasIndeks=islemci.get(i).indexOf("M1");
		islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+2));
		islemciNesil.add("-");
	}
	else if(markasizTitles.get(i).contains("M2")) {
		basIndeks = markasizTitles.get(i).indexOf("M2");
		islemci.add(markasizTitles.get(i).substring(basIndeks, sonIndeks));
		int islemciBasIndeks=islemci.get(i).indexOf("M2");
		islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+2));
		islemciNesil.add("-");
	}
	else if(markasizTitles.get(i).contains("Ryzen") &&  (!markasizTitles.get(i).contains("AMD")))
	{
		basIndeks = markasizTitles.get(i).indexOf("Ryzen");
		islemci.add(markasizTitles.get(i).substring(basIndeks, sonIndeks));
		int islemciBasIndeks= islemci.get(i).indexOf("Ryzen");
		islemciTipi.add(islemci.get(i).substring(islemciBasIndeks, islemciBasIndeks+7));
		islemciNesil.add(islemci.get(i).substring(islemciBasIndeks+8, islemciBasIndeks+9));
		
		
	}
	else { islemci.add("-");
	islemciTipi.add("-");
	islemciNesil.add("-");
	}
	ekranBoyutu.get(i).replaceAll(",",".");	
	markalar.set(i,  markalar.get(i).toUpperCase());
	urunLinkleri.set(i, mainUrl.concat(urunLinkleri.get(i)));
	if(Hdd.get(i).trim().equals("500 GB"))
		Hdd.set(i, "500");
	if(islemciNesil.get(i).trim().equals("a"))
		islemciNesil.set(i, "11");
	if(islemciTipi.get(i).trim().equals("Intel Celeron N4020 1"))
		islemciTipi.set(i, "Celeron");
	if(islemciTipi.get(i).trim().equals("Ryzen5"))
		islemciTipi.set(i, "Ryzen 5");
	if(islemciTipi.get(i).trim().equals("Intel Core I5 1135G7 1")||islemciTipi.get(i).trim().equals("id"))
		islemciTipi.set(i, "i5");
	if(Ssd.get(i).trim().equals("256 GB"))
		Ssd.set(i, "256");
	
	
	
	islemciTipi.set(i, islemciTipi.get(i).replaceAll(" ", ""));
	ekranBoyutu.set(i, ekranBoyutu.get(i).replace(".",","));
	  fiyatlar.set(i,fiyatlar.get(i).trim().replaceAll(" TL","") ) ;
	  if(Ssd.get(i).contains("GB"))
		  Ssd.set(i,Ssd.get(i).replace("GB",""));
		  
	System.out.println("***********");
	System.out.println(i);
	System.out.print("marka = " + markalar.get(i)+ " ");
	System.out.print(" model= " + model.get(i)+ " ");
	System.out.print("ekran = "+ ekranBoyutu.get(i) + " " );
	System.out.print("ram = "+ ram.get(i)+" " );
	System.out.print("ssd= "+Ssd.get(i)+" " );
	System.out.print( "hdd =" +Hdd.get(i)+ " ");
	System.out.print("puan =" + yildizlar.get(i) + " ");
	System.out.print(" fiyat = " + fiyatlar.get(i) +" ");
	

	System.out.print("tip= "+ islemciTipi.get(i)+"  " +" nesil= "+ islemciNesil.get(i));
	System.out.println(urunImage.get(i));
	System.out.println();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	 
	for (int j= 0; j < urunTitles.size(); j++) {

		Baglanti.DatabaseYazdirNotDuplicateFirst("model","name",model.get(j).trim());
		int count = Baglanti.Count();
		Baglanti.DatabaseYazdir(count,"marka",markalar.get(j).trim());
		Baglanti.DatabaseYazdir(count,"ssd",Ssd.get(j).trim());
		Baglanti.DatabaseYazdir(count,"hdd", Hdd.get(j).trim());
		Baglanti.DatabaseYazdir(count,"islemci_tipi",islemciTipi.get(j).trim());
		Baglanti.DatabaseYazdir(count,"islemci_nesli",islemciNesil.get(j).trim()); 
		Baglanti.DatabaseYazdir(count,"isletim_sistemi",dos.get(j).trim());
		Baglanti.DatabaseYazdir(count,"ram",ram.get(j).trim());
		Baglanti.DatabaseYazdir(count,"ekran",ekranBoyutu.get(j).trim());
		
		Baglanti.DatabaseYazdir(count,"puan",yildizlar.get(j).trim());
		Baglanti.DatabaseYazdir(count,"site",site.get(j).trim());
		Baglanti.DatabaseModelSite(site.get(j).trim());
		Baglanti.DatabaseYazdirNotDuplicate(count,"model","fiyat",fiyatlar.get(j).trim());
		Baglanti.DatabaseYazdirNotDuplicate(count,"model","image_url",urunImage.get(j).trim());
		Baglanti.DatabaseYazdirNotDuplicate(count,"model","urun_url",urunLinkleri.get(j).trim());
		Baglanti.DatabaseYazdirNotDuplicate(count,"model","urun_title",urunTitles.get(j).trim());
		
	}
	
		
	}
}



