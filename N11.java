import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class N11 {

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
		ArrayList<String> yildizlar = new ArrayList<String>();
		ArrayList<String> SubIslemci = new ArrayList<String>();
		int indeks;
		String url1 = "https://www.n11.com/bilgisayar/dizustu-bilgisayar";
		for (int i = 0; i < 1; i++) {

			if (i == 0) {
				i++;
			} else
				url1 = url1 + "?pg=" + i;
			System.out.println(url1);
			System.out.println();

			try {

				final Document document = Jsoup.connect(url1).get();
				Elements elements = document.getElementsByClass("pro");
				for (org.jsoup.nodes.Element element : elements) {

					// System.out.println(element.getElementsByTag("a").attr("href"));
					urunLinkleri.add(element.getElementsByTag("a").attr("href"));
					site.add("N11");

				}
				Elements fiyatElement = document.getElementsByClass("priceContainer ");

				for (org.jsoup.nodes.Element element : fiyatElement) {

					fiyatlar.add(element.getElementsByTag("ins").text());
				}
				

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		for (int j = 0; j < urunLinkleri.size(); j++) {
			url1 = urunLinkleri.get(j);

			try {
				Document document = Jsoup.connect(url1).get();
				Elements elements = document.getElementsByClass("proName");
				for (org.jsoup.nodes.Element element : elements) {
					urunTitles.add(element.text());

				}
				
				

				Elements imageElement = document.getElementsByClass("imgObj");

				for (org.jsoup.nodes.Element element : imageElement) {
					String imageLink = element.getElementsByTag("a").attr("href");
					urunImage.add(imageLink);
				}

				Elements yildiz = document.getElementsByClass("ratingCont ");
				for (org.jsoup.nodes.Element element : yildiz) {
					yildizlar.add(element.getElementsByTag("strong").text());
				}

				Elements ozellikElement = document.getElementsByClass("unf-prop-context");
				for (org.jsoup.nodes.Element element : ozellikElement) {
					sadeceOzellikler.add(element.getElementsByClass("unf-prop-list-prop").text());
					urunOzellikler.add(element.getElementsByTag("p").text());

				}

			} catch (IOException e) {
				
				e.printStackTrace();
			}

			if (urunOzellikler.get(j).contains("Bellek Kapasitesi ")) {
				indeks = urunOzellikler.get(j).indexOf("Bellek Kapasitesi ") + "Bellek Kapasitesi ".length();
				ram.add(urunOzellikler.get(j).substring(indeks, indeks + 2).trim());

			} else {
				ram.add("-");

			}

			if (urunOzellikler.get(j).contains("Ekran Boyutu ")) {
				indeks = urunOzellikler.get(j).indexOf("Ekran Boyutu ") + "Ekran Boyutu ".length();
				ekranBoyutu.add(urunOzellikler.get(j).substring(indeks, urunOzellikler.get(j).indexOf("\"")).trim());

			} else {
				ekranBoyutu.add("-");

			}

			if ((urunOzellikler.get(j).contains("Model ")) && (urunOzellikler.get(j).contains("HDMI"))
					&& (urunOzellikler.get(j).indexOf("HDMI") < urunOzellikler.get(j).indexOf("Marka "))) {
				indeks = urunOzellikler.get(j).indexOf("Model ") + "Model ".length();
				model.add(urunOzellikler.get(j).substring(indeks, urunOzellikler.get(j).indexOf("HDMI")).trim());

			} else if ((urunOzellikler.get(j).contains("Model ") && urunOzellikler.get(j).contains("Marka "))) {
				indeks = urunOzellikler.get(j).indexOf("Model ") + "Model ".length();
				model.add(urunOzellikler.get(j).substring(indeks, urunOzellikler.get(j).indexOf("Marka ")).trim());

			} else {
				model.add("-");
			}

			indeks = 0;
			if (urunOzellikler.get(j).contains("İşlemci Modeli ")) {
				indeks = urunOzellikler.get(j).indexOf("İşlemci Modeli ") + "İşlemci Modeli ".length();
				SubIslemci.add(urunOzellikler.get(j).substring(indeks, (urunOzellikler.get(j).length() - 1)));
				if (SubIslemci.get(j).contains("Ryzen ")) {

					indeks = SubIslemci.get(j).indexOf("-");
					islemciNesil.add(SubIslemci.get(j).substring(indeks + 1, indeks + 2));
					islemciTipi.add("Ryzen " + SubIslemci.get(j).substring(indeks - 1, indeks));
				}
				if (SubIslemci.get(j).contains("Celeron ")) {
					islemciTipi.add("Celeron");
					islemciNesil.add("-");
				}
				if (SubIslemci.get(j).contains("Intel ") && (!SubIslemci.get(j).contains("Celeron "))) {
					indeks = SubIslemci.get(j).indexOf("-");
					islemciTipi.add(SubIslemci.get(j).substring(indeks - 2, indeks));
					islemciNesil.add(SubIslemci.get(j).substring(indeks + 1, indeks + 3));
					if (!islemciNesil.get(j).contains("1")) {
						String a = islemciNesil.get(j).substring(0, 1);
						islemciNesil.remove(j);
						islemciNesil.add(a);
					}
				}
				if (SubIslemci.get(j).contains("M1")) {
					islemciTipi.add("M1");
					islemciNesil.add("-");
				}
				if (SubIslemci.get(j).contains("M2")) {
					islemciTipi.add("M2");
					islemciNesil.add("-");
				}
				if (SubIslemci.get(j).contains("Xeon")) {
					islemciTipi.add("Xeon");
					islemciNesil.add("-");
				}
				if (SubIslemci.get(j).contains("Pentium")) {
					islemciTipi.add("Pentium");
					islemciNesil.add("-");
				}

			} else {
				islemciNesil.add("-");
				islemciTipi.add("-");

			}

			if (urunOzellikler.get(j).contains("Disk Türü ")) {
				indeks = urunOzellikler.get(j).indexOf("Disk Türü ") + "Disk Türü ".length();
				String geciciStr = urunOzellikler.get(j).substring(indeks, indeks + 11);
				if (geciciStr.contains("HDD - SSD")) {
					int gecicindeks;
					gecicindeks = urunOzellikler.get(j).indexOf("Disk Kapasitesi ") + "Disk Kapasitesi ".length();
					Hdd.add(urunOzellikler.get(j).substring(gecicindeks, gecicindeks + 2).trim());
					gecicindeks = urunOzellikler.get(j).indexOf("+ ", gecicindeks) + 2;
					geciciStr = urunOzellikler.get(j).substring(gecicindeks, gecicindeks + 5);
					geciciStr = geciciStr.replaceAll("\\D+", "");
					int c;
					c = Integer.parseInt(geciciStr);
					Ssd.add(Integer.toString(c));
				} else if (geciciStr.contains("HDD")) {
					int gecicindeks;
					gecicindeks = urunOzellikler.get(j).indexOf("Disk Kapasitesi ") + "Disk Kapasitesi ".length();
					geciciStr = urunOzellikler.get(j).substring(gecicindeks, gecicindeks + 4);
					geciciStr = geciciStr.replaceAll("\\D+", "");
					int c;
					c = Integer.parseInt(geciciStr);
					Hdd.add(Integer.toString(c));
					Ssd.add("-");
				} else if (geciciStr.contains("SSD")) {
					int gecicindeks;
					gecicindeks = urunOzellikler.get(j).indexOf("Disk Kapasitesi ") + "Disk Kapasitesi ".length();
					geciciStr = urunOzellikler.get(j).substring(gecicindeks, gecicindeks + 4);
					geciciStr = geciciStr.replaceAll("\\D+", "");
					int c;
					c = Integer.parseInt(geciciStr);
					Ssd.add(Integer.toString(c));
					Hdd.add("-");
				}

			} else {
				Hdd.add("-");
				Ssd.add("-");
			}

			if (urunOzellikler.get(j).contains("Freedos") || urunOzellikler.get(j).contains("Dos"))
				dos.add("FreeDOS");
			else if (urunOzellikler.get(j).contains("Windows"))
				dos.add("Windows");
			else if (urunOzellikler.get(j).contains("Macos"))
				dos.add("MacOS");
			else if (urunOzellikler.get(j).contains("Linux"))
				dos.add("Linux");
			else if (urunOzellikler.get(j).contains("Ubuntu"))
				dos.add("Ubuntu");
			else
				dos.add("-");
			markalar.add(urunTitles.get(j).substring(0, urunTitles.get(j).indexOf(" ")));
			markalar.set(j,  markalar.get(j).toUpperCase());
			
			ekranBoyutu.set(j, ekranBoyutu.get(j).replace(".",","));
			  markalar.set(j,  markalar.get(j).toUpperCase());
			  fiyatlar.set(j,fiyatlar.get(j).trim().replaceAll(" TL","") ) ;

			islemciTipi.set(j, islemciTipi.get(j).replaceAll(" ", ""));
		
			
			System.out.println("***********");
			System.out.print("marka = " + markalar.get(j) + " ");
			System.out.print(" model= " + model.get(j) + " ");
			System.out.print("ekran = " + ekranBoyutu.get(j) + " ");
			System.out.print("ram = " + ram.get(j) + " ");
			System.out.print("ssd= " + Ssd.get(j) + " ");
			System.out.print("hdd =" + Hdd.get(j) + " ");
			System.out.print("puan =" + yildizlar.get(j) + " ");
			System.out.print(" fiyat = " + fiyatlar.get(j) + " ");

			System.out.print("tip= " + islemciTipi.get(j) + "  " + " nesil= " + islemciNesil.get(j));
			System.out.println(" " + urunImage.get(j));
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
