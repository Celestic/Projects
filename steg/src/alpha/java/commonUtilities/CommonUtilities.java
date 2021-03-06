package alpha.java.commonUtilities;

public final class CommonUtilities
{

	public static final String CLI_OPTIONS[] = {"inputFile", "fileToEmbed", "outputDir", "parameters"};
	
	public static final String CLI_DESCRIPTIONS[] = {"Parameter representing the path to the input image.",
													 "Parameter representing the path to the file that " +
													 "has to be hidden inside the image.",
													 "Parameter representing the path to the output directory.",
													 "String representation in in the form of a HTTP GET String " + 
		        									 "with \\ representing a line break between parameters:\n" +
		        									 "numberOfTiles \n" +
		        									 "rotation90 | rotation180 | rotation270 in %\n" +
		        									 "customManipulation in % \n" +
		        									 "customMethod = Class.method()"};
	
	public static final String PATH_TO_TILED_IMAGES = "D:/gegevens/desktop/";
	
	public static final int ROWS_TO_TILE = 4;
	public static final int COLS_TO_TILE = 4;
	
}
