
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baglanti {
	public static int Id=0;
	static Connection myConn;
	static Statement myStat;
	
	static ResultSet yap(String tablo) {
		ResultSet myRs = null;
		try {			
			myConn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/pcdb1","root","Mysql123");
			myStat =  myConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myRs = myStat.executeQuery("select * from "+tablo);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myRs;
	}
	
	static void ekle(String sql_sorgu) {
		
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void update(String sql_sorgu) {
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void sil(String sql_sorgu) {
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static ResultSet sorgula(String sql_sorgu) {
		ResultSet myRs =null;		
		try {			
			myRs = myStat.executeQuery(sql_sorgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myRs;
	}

	public static void DatabaseYazdir(int i ,String name,String deger) {
		String y = "";
		String sorgu = "select name from "+name+" where name='" + deger + "'";
	ResultSet myRes = Baglanti.yap(name);
	myRes = Baglanti.sorgula(sorgu);
	try {
		if (myRes.isBeforeFirst()==false) {
			sorgu = "insert into "+name+" (name) values ('" + deger + "')";
			Baglanti.ekle(sorgu);
			sorgu="select id from "+name+" where name='" + deger + "'";
			myRes = Baglanti.sorgula(sorgu);
			while(myRes.next()) {
				y = myRes.getString("id");
			}
			sorgu = "update model set "+name+"_id ="+y+" where id="+(i)+";";
			myRes = Baglanti.yap("model");
			Baglanti.update(sorgu);
			
		} else {
			try {
				while (myRes.next()) {
					if (myRes.getString("name").equals(deger)) {
						sorgu = "select id from "+name+" where name='" + deger + "'";
						myRes = Baglanti.sorgula(sorgu);
						while (myRes.next()) {
							y = myRes.getString("id");

						}
						sorgu =  "update model set "+name+"_id ="+y+" where id="+(i)+";";
						Baglanti.update(sorgu);
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public static void DatabaseYazdirNotDuplicateFirst(String kolon,String name,String deger) {
		ResultSet myRes = Baglanti.yap(kolon);
	String sorgu = "insert into " +kolon+" ("+name+") values('"+deger+"')";
	   Baglanti.ekle(sorgu);	
		

	}
	public static void DatabaseYazdirNotDuplicate(int i,String kolon,String name,String deger) {
		ResultSet myRes = Baglanti.yap(kolon);
	String sorgu = "update "+kolon+" set "+name+"='"+deger+"' where id="+(i)+";";
	   Baglanti.update(sorgu);	
		

	}
	public static void DatabaseModelSite (String deger)
	{
	int x;
	Id++;
		ResultSet myRes = Baglanti.yap("site");
	String sorgu="select id from site where name = '"+deger+"'";
	myRes = Baglanti.sorgula(sorgu);
	
	 try {
		 
			while(myRes.next()) {
				x=myRes.getInt("id");
				sorgu= "insert into model_site (site_id) values ('"+x+"');";
				Baglanti.ekle(sorgu);
			}
			
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
	public static int Count() {
		int x=0;
		ResultSet myRes = Baglanti.yap("model");
		String sorgu="select count(*) from model ";
		myRes = Baglanti.sorgula(sorgu);

		 try {
			 
				while(myRes.next()) {
					x=myRes.getInt("count(*)");
					if(x==0) return 1;
				}
				
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return x;
	}
	

}