import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SimpleProtocol {

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String keyword = "software design";
		String mostRatingBook = firstService(keyword);
		System.out.println("URL of the most rating book: " + mostRatingBook);
		String bookTitle = secondService(mostRatingBook);
		System.out.println("The book title: " + bookTitle);
		
	}
	
//	static String url1 = "http://www.goodreads.com/shelf/show/";
	/**
	 * This method will parse the contents, collect the rating number of each book and return URL that has the most rating </br> 
	 * @param keyword the input value to search books </br>
	 * @return the relative URL of book that has the largest number of rating </br>
	 */
	public static String firstService(String keyword){
		
		keyword = keyword.replace(" ", "+");
		String url = "http://www.goodreads.com/shelf/show/" + keyword;
		try{
			return firstServiceParser(getResponse(url));
			//System.out.println(getResponse(url));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * The method will parse the response's content and return the title of this url </br>
	 * @param relativeUrl the Url found in the firstService() method </br>
	 * @return String title of the most rating book that found in the response's content </br>
	 * @throws IOException throw an {@link Exception} if the URL does not exist </br>
	 */
	public static String secondService(String relativeUrl) throws IOException{
		
		String url = "http://www.goodreads.com" + relativeUrl.replace("\"", "");
		return secondServiceParser(getResponse(url));
	}
	
	static String firstServiceParser(InputStream is) throws NumberFormatException, IOException{
		
		String theMostRatingURL = null;
		Double maxRating = 0.0;
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(is));
		String inputLine;
		List<String> listLink = new ArrayList<String>();
		while ((inputLine = in.readLine()) != null) {
			//collect all possible URLs before calculating their ratings
			String hrefTemp = null;
			try{
				int startLink = inputLine.indexOf("href");
				if (startLink > -1){
					int endLink = inputLine.indexOf("class");
						hrefTemp = inputLine.substring(startLink + "href=".length(), endLink);
						listLink.add(hrefTemp);
				}

			}catch(Exception e){
				
			}
			if (inputLine.indexOf("avg rating ") > -1){
				//System.out.println("--------------------" + hrefTemp);
				int ratingIndex = inputLine.indexOf("avg rating") + "avg rating ".length() ;
				String ratingValue = inputLine.substring(ratingIndex , ratingIndex + 4 );
				if (maxRating < Double.parseDouble(ratingValue.trim())){
					maxRating = Double.parseDouble(ratingValue.trim());
					//strRespone = inputLine;
					if(!listLink.isEmpty())
						theMostRatingURL = listLink.get(0);
					listLink.clear();
					
				}
			}
		}
		in.close();
		return theMostRatingURL;
	}
	
	static String secondServiceParser(InputStream is) throws IOException{
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(is));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			
			int startTitleIndex = inputLine.indexOf("<title>");
			if (startTitleIndex > -1){
				int endTitleIndex = inputLine.indexOf("</title>");
				String title = inputLine.substring(startTitleIndex + "<title>".length(), endTitleIndex);
				//System.out.println("found : " + title);
				return title;
				
			}
			
		}//while
		in.close();
		return null;
	}
	
	static InputStream getResponse(String url) throws IOException{

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		return con.getInputStream();
		
	}
	

}
