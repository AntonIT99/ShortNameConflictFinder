import java.util.regex.Pattern;

public class Item {
	
	public String shortName, path;
	
	public Item(String sh, String p) {
		shortName = sh;
		path = p;
	}
	
	public static String strictLocation(String fullPath) {
		
		return fullPath.split(Pattern.quote("C:\\Users\\alpha\\AppData\\Roaming\\.minecraft\\Flan"))[1];
	}

}
