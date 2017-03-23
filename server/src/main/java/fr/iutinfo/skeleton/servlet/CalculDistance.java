package fr.iutinfo.skeleton.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.ControllerEventListener;

@WebServlet("/CalculDistance")
public class CalculDistance extends HttpServlet {

	public static void main (String [] args) {
		System.setProperty("http.proxyHost", "cache.univ-lille1.fr");
		System.setProperty("http.proxyPort", "3128");
		
		String to = "107 rue Gaston Baratte Villeneuve d'ascq";
		String test = "33 place Beaulieu Lomme";
		

		String locationV[] = requeteCoord(to);
		String [] locationL = requeteCoord(test);
		double[] dblV = {Float.parseFloat(locationV[0]),Float.parseFloat(locationV[1])};
		double [] dblL = {Float.parseFloat(locationL[0]),Float.parseFloat(locationL[1])};
		

		double result = distance(dblV[0], dblV[1], dblL[0], dblL[1], "K");
		
		System.out.println(to + "  latitude : " +locationV[0]+" / longitude "+locationV[1]);
		System.out.println(test + "  latitude : " +locationL[0]+" / longitude : "+locationL[1]);
		
		System.out.println("distance : "+result + " km");
		
		System.out.println(controle(result));
	}
	public void service( HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException
			{
			String from=req.getParameter("ville");
			String to = "107 rue Gaston Baratte Villeneuve d'ascq";
			String test = "50 rue du marché Lille";
			

			String locationV[] = requeteCoord(to);
			String [] locationL = requeteCoord(test);
			double[] dblV = {Float.parseFloat(locationV[0]),Float.parseFloat(locationV[1])};
			double [] dblL = {Float.parseFloat(locationL[0]),Float.parseFloat(locationL[1])};
			
			double result = distance(dblV[0], dblV[1], dblL[0], dblL[1], "N");
			System.out.println("distance : "+result);
			
					
			}
			

	public static String[] requeteCoord(String lieu){
		try {
			String lieumodif = changeString(lieu);
			
			 URL u = new URL("http://nominatim.openstreetmap.org/search/"+lieumodif+"?format=json&addressdetails=1&limit=1&polygon_svg=1");
	
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			
			String inputLine;
			String coordonnees[]= new String[2];
			
			while ((inputLine = in.readLine()) != null) {
				coordonnees[0] = inputLine.substring(inputLine.indexOf("\"lat\"")+7,inputLine.indexOf("\"lat\"")+15);
				coordonnees[1] = inputLine.substring(inputLine.indexOf("\"lon\"")+7,inputLine.indexOf("\"lon\"")+14);

				
			}
			in.close();
			return coordonnees;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String changeString(String s) {
		String[] tmp = s.split(" ");
		String res="";
		for(String st : tmp){
			res+=st+"%20";
		}
		res =res.substring(0, res.length()-3);
		
		return res;
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	
	public static String controle(double d){
		if (d < 15.0){
			return "distance inférieure à 15km ---> livraison possible";
		}
		return "distance trop élevée ---> livraison impossible ";
	}

}
