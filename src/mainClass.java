import java.util.ArrayList;
import java.util.List;

public class mainClass 
{
	public static void main(String args[]) throws Exception 
	{
		KeywordInputter obj1 = new KeywordInputter();
		obj1.setter();
		String keyword = obj1.getter();
		
		ImageScraper obj2 = new ImageScraper();
		List<String> urlList = new ArrayList<String>();
		urlList = obj2.urlFinder(keyword);
		
		DownloadImages obj3 = new DownloadImages();
		obj3.downloaderCaller(urlList, keyword);
	}
}
