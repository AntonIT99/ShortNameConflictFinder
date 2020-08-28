import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class Main {

	public static void main(String[] args) throws IOException
    {
		File dir = new File("C:/Users/alpha/AppData/Roaming/.minecraft/Flan");
		
		String[] extensions = new String[] {"txt"};
		
		IOFileFilter fileFilter = FileFilterUtils.suffixFileFilter("txt");
		IOFileFilter dirFilter1 = FileFilterUtils.notFileFilter(FileFilterUtils.nameFileFilter("classes", null));
		IOFileFilter dirFilter2 = FileFilterUtils.notFileFilter(FileFilterUtils.nameFileFilter("teams", null));
		IOFileFilter dirFilter3 = FileFilterUtils.notFileFilter(FileFilterUtils.nameFileFilter("armorFiles old", null));
		FileFilter dirFilter =   FileFilterUtils.and(dirFilter1, dirFilter2, dirFilter3);
		List<File> files =  (List<File>)FileUtils.listFiles(dir , fileFilter ,(IOFileFilter)dirFilter);
		//List<File> files = (List<File>)FileUtils.listFiles(dir, extensions, true);
		ArrayList<Item> items = new ArrayList<Item>();
		
		System.out.print("loading");
		int n = 0;
		
		for (File file : files) {
			
			if(n%50 == 0) System.out.print(".");
			n++;
			
			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			
			String line = reader.readLine();
			 
	        while(line != null) {
	            
	            if (line.contains("ShortName")) 
	            {
	            	items.add(new Item(line.split(Pattern.quote(" "))[1], file.getCanonicalPath()));
	            }
	            line = reader.readLine();
	        }
	        
	        reader.close();
	        fis.close();
		}
		
		System.out.println("\nchecking potential conflicts");
		
		for (int i = 0; i<items.size(); i++)
		{
			for (int j = 0; j<items.size(); j++)
			{
				if (i != j)
				{
					if (items.get(i).shortName.equals(items.get(j).shortName))
					{
						System.out.println("ShortName conflict between " + Item.strictLocation(items.get(i).path) + " and " + Item.strictLocation(items.get(j).path));
					}
				}
			}
		}
				
    }
}
