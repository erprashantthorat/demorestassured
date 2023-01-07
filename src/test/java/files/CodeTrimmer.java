package files;

public class CodeTrimmer {

	public String codetrim(String url)
	{
		String urlcode= url;
		String partialurl = urlcode.split("code=")[1];
		String URL = partialurl.split("&scope")[0];				
		return URL;
	}
	
}
