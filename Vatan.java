import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.lang.model.element.Element;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;



public class Vatan {
public static int hangiId;
	public static void main(String[] args) {

		int boslukIndeksi, ilkBoslukIndeks, indeks;
		String ssdBoyut = "-";
		String cleaner;
		int b;
		String hddBoyut = "-";
		ArrayList<String> urunLinkleri = new ArrayList<String>();
		ArrayList<String> urunTitles = new ArrayList<String>();
		ArrayList<String> fiyatlar = new ArrayList<String>();
		ArrayList<String> yildizlar = new ArrayList<String>();
		ArrayList<String> site = new ArrayList<String>();
		ArrayList<String> urunImage = new ArrayList<String>();
		ArrayList<String> ekranBoyutu = new ArrayList<String>();
		ArrayList<String> dos = new ArrayList<String>();
		ArrayList<String> ram = new ArrayList<String>();
		ArrayList<String> markalar = new ArrayList<String>();
		ArrayList<String> markasizTitles = new ArrayList<String>();
		ArrayList<String> Ssd = new ArrayList<String>();
		ArrayList<String> Hdd = new ArrayList<String>();
		ArrayList<String> islemciTipi = new ArrayList<String>();
		ArrayList<String> islemciNesil = new ArrayList<String>();
		ArrayList<String> model = new ArrayList<String>();
		String mainUrl= "https://www.vatanbilgisayar.com";
		for (int i = 0; i <1 ; i++) {

			String url1;
			if (i == 0) {
				url1 = "https://www.vatanbilgisayar.com/notebook/";
				i++;
			} else
				url1 = "https://www.vatanbilgisayar.com/notebook/?page=" + i;
			System.out.println(url1);
			System.out.println();

			try {
				final Document document = Jsoup.connect(url1).get();
				Elements images = document.getElementsByClass("product-list product-list--list-page");

				for (org.jsoup.nodes.Element image : images) {
					// System.out.println(image.text());
					System.out.println(image.getElementsByTag("h3").text());// TITTLE
					urunTitles.add(image.getElementsByTag("h3").text());
					// System.out.println(image.getElementsByClass("product-list__price").text());//FİYAT
					// BİLGİSİ
					fiyatlar.add(image.getElementsByClass("product-list__price").text());
					// System.out.println( image.select("a").attr("href").toString());//URUN
					// LİNKLERİ
					urunLinkleri.add(mainUrl+image.select("a").attr("href").toString());
					// System.out.println(image.getElementsByClass("score").attr("style").replaceAll("\\D+",""));
					urunImage.add(image.getElementsByClass("lazyimg").attr("data-src"));

					String clean = image.getElementsByClass("score").attr("style").replaceAll("\\D+", "");
					int yildizz = Integer.parseInt(clean);
					yildizz /= 20;// Yildizi
					String yildiz = "" + yildizz;
					yildizlar.add(yildiz);

					site.add("Vatan");

					// System.out.println(image.attributes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < urunTitles.size(); i++) {

			if (urunTitles.get(i).contains("inc")) {
				String ekran = urunTitles.get(i)
						.subSequence((urunTitles.get(i).indexOf("inc") - 4), (urunTitles.get(i).indexOf("inc")))
						.toString();
				if (ekran.contains("-")) {
					boslukIndeksi = ekran.indexOf("-");
					ekranBoyutu.add(ekran.substring(boslukIndeksi + 1));
				} else {
					ekranBoyutu.add(ekran);
				}
				// System.out.println(ekranBoyutu.get(i));
			} else {
				ekranBoyutu.add("-");

			}

			if (urunTitles.get(i).contains("Freedos") || urunTitles.get(i).contains("Dos"))
				dos.add("FreeDOS");
			else if (urunTitles.get(i).contains("Windows") || urunTitles.get(i).contains("W11H")
					|| urunTitles.get(i).contains("W10") || urunTitles.get(i).contains("W11")
					|| urunTitles.get(i).contains("Win10") || urunTitles.get(i).contains("W10H")
					|| urunTitles.get(i).contains("Win11") || urunTitles.get(i).contains("WIN11"))
				dos.add("Windows");
			else if (urunTitles.get(i).contains("M1") || urunTitles.get(i).contains("M2"))
				dos.add("MacOS");
			else if (urunTitles.get(i).contains("Linux"))
				dos.add("Linux");
			else if (urunTitles.get(i).contains("Ubuntu"))
				dos.add("Ubuntu");
			else
				dos.add("-");

			int sonIndeks = urunTitles.get(i).indexOf("Gb");
			int baslangicIndeks = urunTitles.get(i).indexOf("Gb") - 2;
			String rams, clean;
			if (urunTitles.get(i).contains("Gb") && urunTitles.get(i).contains("-")) {
				rams = urunTitles.get(i).substring(baslangicIndeks, sonIndeks);
				clean = rams.replaceAll("\\D+", "");
				int a = Integer.parseInt(clean);
				ram.add(Integer.toString(a));
			} else {
				ram.add("-");

			}
//MARKAYI ALDIK
			ilkBoslukIndeks = urunTitles.get(i).indexOf(" ");
			markalar.add(urunTitles.get(i).substring(0, ilkBoslukIndeks));

			markasizTitles.add(urunTitles.get(i).substring(ilkBoslukIndeks + 1, (urunTitles.get(i).length() - 1)));

			if (urunTitles.get(i).contains("Hdd") && urunTitles.get(i).contains("Ssd")) {

				ssdBoyut = urunTitles.get(i)
						.subSequence((urunTitles.get(i).indexOf("Ssd") - 6), (urunTitles.get(i).indexOf("Ssd")))
						.toString();
				cleaner = ssdBoyut.replaceAll("\\D+", "");
				b = Integer.parseInt(cleaner);
				Ssd.add(Integer.toString(b));
				hddBoyut = urunTitles.get(i)
						.subSequence((urunTitles.get(i).indexOf("Hdd") - 6), (urunTitles.get(i).indexOf("Hdd")))
						.toString();
				cleaner = hddBoyut.replaceAll("\\D+", "");
				b = Integer.parseInt(cleaner);
				Hdd.add(Integer.toString(b));

			} else if (urunTitles.get(i).contains("Ssd")) {
				ssdBoyut = urunTitles.get(i)
						.subSequence((urunTitles.get(i).indexOf("Ssd") - 6), (urunTitles.get(i).indexOf("Ssd")))
						.toString();
				cleaner = ssdBoyut.replaceAll("\\D+", "");
				b = Integer.parseInt(cleaner);
				Ssd.add(Integer.toString(b));
				Hdd.add("-");

			} else if (urunTitles.get(i).contains("Hdd")) {
				hddBoyut = urunTitles.get(i).substring((urunTitles.get(i).indexOf("Hdd") - 6),
						(urunTitles.get(i).indexOf("Hdd")));
				cleaner = hddBoyut.replaceAll("\\D+", "");
				b = Integer.parseInt(cleaner);
				Hdd.add(Integer.toString(b));
				Ssd.add("-");

			} else {
				Ssd.add("-");
				Hdd.add("-");
			}

			if (urunTitles.get(i).contains("Core")) {
				indeks = urunTitles.get(i).indexOf("Core") + 5;
				islemciTipi.add(urunTitles.get(i).substring(indeks, indeks + 2));
				islemciNesil.add(urunTitles.get(i).substring(indeks + 3, indeks + 5));
			} else if (urunTitles.get(i).contains("Ryzen")) {
				indeks = urunTitles.get(i).indexOf("Ryzen");
				islemciTipi.add(urunTitles.get(i).substring(indeks, indeks + 7));
				indeks = indeks + 8;
				islemciNesil.add(urunTitles.get(i).substring(indeks, indeks + 1));
			} else if (urunTitles.get(i).contains("M1")) {
				islemciTipi.add("M1");
				islemciNesil.add("-");
			} else if (urunTitles.get(i).contains("M2")) {
				islemciTipi.add("M2");
				islemciNesil.add("-");

			} else {
				islemciTipi.add("-");
				islemciNesil.add("-");
			}

			if (markasizTitles.get(i).contains("M1")) {
				indeks = markasizTitles.get(i).indexOf("M1") - 1;
				model.add(markasizTitles.get(i).substring(0, indeks));
			} else if (markasizTitles.get(i).contains("M2")) {
				indeks = markasizTitles.get(i).indexOf("M2") - 1;
				model.add(markasizTitles.get(i).substring(0, indeks));
			}

			else if (markasizTitles.get(i).contains(".")) {
				indeks = markasizTitles.get(i).indexOf(".") - 2;
				if (indeks > (markasizTitles.get(i).length() / 2))
					model.add("-");
				else
					model.add(markasizTitles.get(i).substring(0, indeks));

			} else {
				model.add("-");
			}
			
			markalar.set(i,  markalar.get(i).toUpperCase());
			
			if(ekranBoyutu.get(i).trim().equals("5.6"))
				ekranBoyutu.set(i, "15.6");
			if(islemciTipi.get(i).trim().equals("5"))
				islemciTipi.set(i, "i5");
			if(islemciTipi.get(i).trim().equals("9"))
				islemciTipi.set(i, "i9");
			if(islemciNesil.get(i).trim().equals("29"))
				islemciNesil.set(i, "12");
			if(islemciNesil.get(i).trim().equals("25"))
				islemciNesil.set(i, "12");
			if(islemciNesil.get(i).trim().equals("L1"))
				islemciNesil.set(i, "11");
			
			islemciTipi.set(i, islemciTipi.get(i).replaceAll(" ", ""));
			ekranBoyutu.set(i, ekranBoyutu.get(i).replace(".",","));
			
			
			  System.out.println("***********"); System.out.print("marka = " +
			  markalar.get(i)+ " "); System.out.print(" model= " + model.get(i)+ " ");
			  System.out.print("ekran = "+ ekranBoyutu.get(i) + " " );
			  System.out.print("ram = "+ ram.get(i)+" " );
			  System.out.print("ssd= "+Ssd.get(i)+" " ); System.out.print( "hdd ="
			  +Hdd.get(i)+ " "); System.out.print("puan =" + yildizlar.get(i) + " ");
			  System.out.print(" fiyat = " + fiyatlar.get(i) +" ");
			  
			  
			  System.out.print("tip= "+ islemciTipi.get(i)+"  " +" nesil= "+
			  islemciNesil.get(i)); System.out.println(urunImage.get(i));
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
	
	
		
		
//DatabaseIsletimTipiNesil();
		
	}

	
	
	//ÖLÜ METHOD
	public static void DatabaseIsletimTipiNesil()
	{ int x;
	String y="";
		ResultSet myRes = Baglanti.yap("model");
	String sorgu="select islemci_tipi_id from model ";
	myRes = Baglanti.sorgula(sorgu);
	try {
		while(myRes.next()) {
			x=myRes.getInt("islemci_tipi_id");
			myRes=Baglanti.yap("islemci_tipi_nesli");
			sorgu="insert into islemci_tipi_nesli (islemci_tipi_id) values ('"+x+"');";
			Baglanti.ekle(sorgu);
			
		}
		myRes = Baglanti.yap("model");
		sorgu="select islemci_nesli_id from model ";
		myRes = Baglanti.sorgula(sorgu);
		while(myRes.next()) {
			x=myRes.getInt("islemci_nesli_id");
			myRes=Baglanti.yap("islemci_tipi_nesli");
			sorgu="insert into islemci_tipi_nesli (islemci_tipi_id) values ('"+x+"');";
			Baglanti.ekle(sorgu);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

		
		
		
	}	
	
	
}
