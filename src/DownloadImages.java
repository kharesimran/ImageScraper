/* A code snippet retrieved from Stack Overflow helped me to learn how to download an image from a web page. 
 * http://stackoverflow.com/questions/5882005/how-to-download-image-from-any-web-page-in-java
 * I modified it to specify a new path for each image file. 
 */

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadImages
{
	public void downloaderCaller(List<String> urlList, String keyword) throws Exception 
	{
		String dirPath = null;
		String imgPath = null;
		String imgSrc = null;
		int count = 1;
		dirPath = "D:\\ImageScraper\\" + keyword;		
        new File(dirPath).mkdirs();
        
        List<String> imgPathList = new ArrayList<String>();
        
 		System.out.println("Following are the paths of the downloaded images:");
 		System.out.println();
  		
        
		for (int i = 0; i < urlList.size(); i++) 
    	{
      		String webUrl = urlList.get(i);
      		int k = webUrl.lastIndexOf(".");	
      		imgSrc  = webUrl.substring(webUrl.lastIndexOf("/") + 1, k+4);
      		imgPath = "D:/ImageScraper/" + keyword + "/" + imgSrc;	
      		
      		imgPathList.add(imgPath);
    	}
		
/*If multiple images have the same name,
 *  it renames the images so that each downloaded image has a unique path. 
 *  For example, if there are three images named ‘Earth’, it will rename them as Earth, Earth1, Earth2.
 */
	
		for (int i = 1; i < imgPathList.size(); i++) 
    	{
			for(int j = i-1; j >= 0 ; j--)
			{
				if( imgPathList.get(i).equals(imgPathList.get(j)) )
				{
					int k = imgPathList.get(i).lastIndexOf(".");					
					String s = imgPathList.get(i).substring(0, k) + String.valueOf(count) + ".jpg";
					imgPathList.set(i, s); 
					count++;
				}
			}
    	}
				
  		
  		for (int i = 0; i < imgPathList.size(); i++) 
  		{
  			System.out.println("Downloading image " + String.valueOf(i+1) + " of " + imgPathList.size() + " to " + imgPathList.get(i));
  			try
  			{
  			imageDownloader(urlList.get(i), imgPathList.get(i));
  			}
  			catch (IOException ex)
  			{
                    System.out.println(ex.getMessage());
                    System.out.println("The image "+ (i+1) + " cannot be downloaded due to an exception.");
  			}
  		}
		
		
		System.out.println();
		System.out.println("----------------------------Download Complete------------------------------------");
		
	}
	
	public void imageDownloader(String webUrl, String imgPath) throws IOException
	{
	 URL url = new URL(webUrl);
	 InputStream in = new BufferedInputStream(url.openStream());
	 ByteArrayOutputStream out = new ByteArrayOutputStream();
	 byte[] buf = new byte[1024];
	 int n = 0;
	 while (-1!=(n=in.read(buf)))
	 {
	    out.write(buf, 0, n);
	 }
	 out.close();
	 in.close();
	 byte[] response = out.toByteArray();

	 FileOutputStream fos = new FileOutputStream(imgPath);
	 fos.write(response);
	 fos.close();
	}
}
