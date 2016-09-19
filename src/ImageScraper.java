/* The code snippet to form an HTTP connection was retrieved from
 * https://github.com/ThomasLengeling/Processing-Sketch/blob/master/CustomSearchJSON/Search.pde
 * The GResults class used in the code was also retrieved from the same source.
 */


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ImageScraper
{
	public List<String> urlFinder(String keyword) throws MalformedURLException, URISyntaxException, IOException, JSONException
	{

	String key = "AIzaSyAyxXiBAe-I2RMbLBTOJHjIBJ8vFt3OyAo";
//	String qry = "nebulas"; 
	String qry = keyword; 
	String cx  = "006559764716072427351:xnqz1gk0may";
	String fileType = "jpg";
	String searchType = "image";
	String number = "10";
	
	URL url = new URL ("https://www.googleapis.com/customsearch/v1?key=" +key+ "&cx=" +cx+ "&q=" +qry+"&fileType="+fileType+"&searchType="+searchType+"&num="+number+"&alt=json");
//	System.out.println("https://www.googleapis.com/customsearch/v1?key=" +key+ "&cx=" +cx+ "&q=" +qry+"&fileType="+fileType+"&searchType="+searchType+"&num=10&alt=json");
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setRequestProperty("Accept", "application/json");
	BufferedReader br = new BufferedReader(new InputStreamReader ( ( conn.getInputStream() ) ) );
	GResults results = new Gson().fromJson(br, GResults.class);

	conn.disconnect();

	Gson gson = new Gson();
	String jsonString = gson.toJson(results);

	JSONObject jsonObject = new JSONObject(jsonString);

	JSONArray items_arr = jsonObject.getJSONArray("items");

	List<String> urlList = new ArrayList<String>();

	for (int i = 0; i < items_arr.length(); i++) {
	  JSONObject j_obj = items_arr.getJSONObject(i);
	  String urllink = j_obj.getString("link");
	  urlList.add(urllink);
	}  
	
	System.out.println();
	System.out.println("Images will be downloaded from the following URLs:");
	System.out.println();
		
	  for (String s : urlList) 
		{
	  	System.out.println(s);
		}  
	  
	System.out.println();
	  
	  return urlList;
	  
	}
}