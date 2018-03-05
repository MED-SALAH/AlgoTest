package recognizer;
import java.io.File;
import java.io.FilenameFilter;


public class ExtensionFilter implements FilenameFilter
{
	public ExtensionFilter(String ext)
	{
		extension = "." + ext;
	}
	public boolean accept(File dir, String name)
	{
		return name.endsWith(extension);
	}
	private String extension;
}
