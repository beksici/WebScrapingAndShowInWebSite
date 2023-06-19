import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;




public class Trendyol {

	public static void main(String[] args) {

		ArrayList<String> urunOzellikler = new ArrayList<String>();
		ArrayList<String> urunTitles = new ArrayList<String>();
		ArrayList<String> urunLinkleri = new ArrayList<String>();
		ArrayList<String> markalar = new ArrayList<String>();
		ArrayList<String> fiyatlar = new ArrayList<String>();
		ArrayList<String> site = new ArrayList<String>();
		ArrayList<String> sadeceOzellikler = new ArrayList<String>();
		ArrayList<String> ekranBoyutu = new ArrayList<String>();
		ArrayList<String> islemciTipi = new ArrayList<String>();
		ArrayList<String> islemciNesil = new ArrayList<String>();
		ArrayList<String> model = new ArrayList<String>();
		ArrayList<String> dos = new ArrayList<String>();
		ArrayList<String> ram = new ArrayList<String>();
		ArrayList<String> Ssd = new ArrayList<String>();
		ArrayList<String> Hdd = new ArrayList<String>();
		ArrayList<String> urunImage = new ArrayList<String>();
		ArrayList<String> yildizlar = new ArrayList<String>();// YILDIZ CEKİLMİYOR
		int indeks;
		String mainUrl = "https://www.trendyol.com";
		String url1 = "https://www.trendyol.com/laptop-x-c103108";
		for (int i = 0; i < 1; i++) {

			if (i == 0) {
				i++;
			} else
				url1 = url1 + "?pi=" + i;
			System.out.println(url1);
			System.out.println();
			
			

			try {

				final Document document = Jsoup.connect(url1).get();
				Elements elements = document.getElementsByClass("p-card-wrppr with-campaign-view");
				for (org.jsoup.nodes.Element element : elements) {
					// System.out.println( element.getElementsByClass("list-ul").text());

					urunLinkleri.add(mainUrl + element.getElementsByTag("a").attr("href"));
					site.add("Trendyol");
					yildizlar.add("-");

				}
				

			} catch (IOException e) {
				e.printStackTrace();
			}

			

		}
		for (int j = 0; j < urunLinkleri.size(); j++) {
			url1 = urunLinkleri.get(j);
			
			
			
			
			
			

			try {
				
				Document document = Jsoup.connect(url1).get();
				Elements elements = document.getElementsByClass("pr-new-br");
				for (org.jsoup.nodes.Element element : elements) {

					urunTitles.add(element.text());
					markalar.add(element.getElementsByTag("a").text());

				}
				Elements fiyatElement = document.getElementsByClass("product-price-container");

				for (org.jsoup.nodes.Element element : fiyatElement) {
					fiyatlar.add(element.getElementsByClass("prc-dsc").first().text());

	//				System.out.println(fiyatlar.get(j));
				}
				Elements ozellikElement = document.getElementsByClass("detail-attr-container");
				for (org.jsoup.nodes.Element element : ozellikElement) {
			sadeceOzellikler.add(element.getElementsByTag("b").text());
	urunOzellikler.add(element.getElementsByTag("span").text());
	
				 	
	//System.out.println( element.getElementsByTag("span").text());
	//System.out.println(element.getElementsByTag("b").text());

				}
				
				Elements imageElement = document.getElementsByTag("img");
				int k=0;
				for (org.jsoup.nodes.Element element : imageElement) {
				String imageLink =element.attr("src").toString();
				if(k==1) {
					urunImage.add(imageLink);
				} 
				k++;
				}
			
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		System.out.println(	urunOzellikler.get(j));
			
			if(urunOzellikler.get(j).contains("inç") )
			{indeks = urunOzellikler.get(j).indexOf("inç");
			String ekran = urunOzellikler.get(j).substring(indeks-4, indeks-1);
			if (ekran.contains(",")) {
			ekran ="1"+ ekran;
			ekranBoyutu.add(ekran);
			} else {
				ekran = ekran.trim();
				ekranBoyutu.add(ekran);
			}
			}else {
				ekranBoyutu.add("-");

			}
			//System.out.println(ekranBoyutu.get(j));
			
			if (urunOzellikler.get(j).contains("Core")) {
				indeks = urunOzellikler.get(j).indexOf("Core") + 5;
				islemciTipi.add(urunOzellikler.get(j).substring(indeks, indeks + 2));
				if(urunOzellikler.get(j).contains(". Nesil")) {
					indeks=urunOzellikler.get(j).indexOf(". Nesil")-2;
					
					islemciNesil.add(urunOzellikler.get(j).substring(indeks, indeks+2).trim());
					
				}else {
					islemciNesil.add("-");
					
				}
				
				
			}else if(!(urunOzellikler.get(j).contains("Core"))&&  (urunOzellikler.get(j).contains("Intel"))) {
				islemciTipi.add("Celeron");
				if(urunOzellikler.get(j).contains(". Nesil")) {
					indeks=(urunOzellikler.get(j).indexOf(". Nesil")-2);
					islemciNesil.add(urunOzellikler.get(j).substring(indeks, indeks+2).trim());
					
				}else {
					islemciNesil.add("-");
					
				}
	
			}
			
			else if (urunOzellikler.get(j).contains("Ryzen")&& !urunOzellikler.get(j).endsWith("Ryzen") ) {
				indeks = urunOzellikler.get(j).indexOf("Ryzen");
				islemciTipi.add(urunOzellikler.get(j).substring(indeks, indeks + 7));
				if(urunOzellikler.get(j).contains(". Nesil")) {
					indeks=urunOzellikler.get(j).indexOf(". Nesil")-2;
					islemciNesil.add(urunOzellikler.get(j).substring(indeks, indeks+2).trim());
					
				}else {
					islemciNesil.add("-");
					
				}
				
				
			}else if(urunOzellikler.get(j).endsWith("Ryzen"))
			{
				islemciNesil.add("-");
				islemciTipi.add("-");
			}
			else if (urunOzellikler.get(j).contains("M1")) {
				islemciTipi.add("M1");
				islemciNesil.add("-");
				
			} else if (urunOzellikler.get(j).contains("M2")) {
				islemciTipi.add("M2");
				islemciNesil.add("-");

			} else {
				islemciTipi.add("-");
				islemciNesil.add("-");
				
			}
			
		//System.out.println(islemciTipi.get(j));
			//System.out.println(islemciNesil.get(j));
			//System.out.println();

 indeks =markalar.get(j).length()+1;
 int geciciIndeks= urunTitles.get(j).indexOf(" ", indeks);
 model.add(urunTitles.get(j).substring(indeks,geciciIndeks ));
 //System.out.println(model.get(j));
		
 
 if (urunOzellikler.get(j).contains("Free Dos") || urunOzellikler.get(j).contains("Dos"))
		dos.add("FreeDOS");
	else if (urunOzellikler.get(j).contains("Windows") || urunOzellikler.get(j).contains("W11H")
			|| urunOzellikler.get(j).contains("W10") || urunOzellikler.get(j).contains("W11")
			|| urunOzellikler.get(j).contains("Win10") || urunOzellikler.get(j).contains("W10H")
			|| urunOzellikler.get(j).contains("Win11") || urunOzellikler.get(j).contains("WIN11"))
		dos.add("Windows");
	else if (urunOzellikler.get(j).contains("Mac Os"))
		dos.add("MacOS");
	else if (urunOzellikler.get(j).contains("Linux"))
		dos.add("Linux");
	else if (urunOzellikler.get(j).contains("Ubuntu"))
		dos.add("Ubuntu");
	else
		dos.add("-");
 
 
		//System.out.println(dos.get(j));
 
 if(urunOzellikler.get(j).contains("(Sistem Belleği) ")) {
	 indeks = urunOzellikler.get(j).indexOf("(Sistem Belleği) ")+ "(Sistem Belleği) ".length();
	 ram.add(urunOzellikler.get(j).substring(indeks, indeks+2).trim());
	
	 
 }else {
	 ram.add("-");
	 
 }
 //System.out.println(ram.get(j));
 
 if(urunOzellikler.get(j).contains("SSD Kapasitesi ")) {
	indeks=urunOzellikler.get(j).indexOf("SSD Kapasitesi ") + "SSD Kapasitesi ".length();
	String cleaner= urunOzellikler.get(j).substring(indeks, indeks+3);
	if(cleaner.contains("Yok") || cleaner.contains("SSD")) {
		Ssd.add("-");
	}else {
	int b;
	cleaner=cleaner.replaceAll("\\D+", "");
	 b= Integer.parseInt(cleaner);
	 Ssd.add(Integer.toString(b));
	
	}
	
	 if(urunOzellikler.get(j).contains("Hard Disk Kapasitesi ")) {
			indeks=urunOzellikler.get(j).indexOf("Hard Disk Kapasitesi ") + "Hard Disk Kapasitesi ".length();
			 cleaner= urunOzellikler.get(j).substring(indeks, indeks+3).trim();
			if(cleaner.contains("Yok") || cleaner.contains("HDD")) {
				Hdd.add("-");
			}else {
			int b;
			cleaner=cleaner.replaceAll("\\D+", "");
			 b= Integer.parseInt(cleaner);
			 Hdd.add(Integer.toString(b));}}
	 else {
		 Hdd.add("-");
	 }
	 
	 
 }else if(Hdd.get(j).contains("-") && urunOzellikler.get(j).contains("Hard Disk Kapasitesi ")) {
		indeks=urunOzellikler.get(j).indexOf("Hard Disk Kapasitesi ") + "Hard Disk Kapasitesi ".length();
		String cleaner= urunOzellikler.get(j).substring(indeks, indeks+3).trim();
		if(cleaner.contains("Yok") || cleaner.contains("HDD")) {
			Hdd.remove(j);
			Hdd.add("-");
		}else {
		int b;
		cleaner=cleaner.replaceAll("\\D+", "");
		 b= Integer.parseInt(cleaner);
		 Hdd.add(Integer.toString(b));
	 
		}
	 
 }else {
	 Hdd.add("-");
	 Ssd.add("-");
 }
 
 ekranBoyutu.set(j, ekranBoyutu.get(j).replace(".",","));
  markalar.set(j,  markalar.get(j).toUpperCase());
  fiyatlar.set(j,fiyatlar.get(j).trim().replaceAll(" TL","") ) ;

islemciTipi.set(j, islemciTipi.get(j).replaceAll(" ", ""));
 System.out.println("***********");
	System.out.print("marka = " + markalar.get(j)+ " ");
	System.out.print(" model= " + model.get(j)+ " ");
	System.out.print("ekran = "+ ekranBoyutu.get(j) + " " );
	System.out.print("ram = "+ ram.get(j)+" " );
	System.out.print("ssd= "+Ssd.get(j)+" " );
	System.out.print( "hdd =" +Hdd.get(j)+ " ");
	System.out.print("puan =" + yildizlar.get(j) + " ");
	System.out.print(" fiyat = " + fiyatlar.get(j) +" ");
	

	System.out.print("tip= "+ islemciTipi.get(j)+"  " +" nesil= "+ islemciNesil.get(j));
	System.out.println(urunImage.get(j));
	System.out.println();
		
 
 
		
		
		
		}


		int j; 
	for (j= 0; j < urunTitles.size(); j++) {

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
