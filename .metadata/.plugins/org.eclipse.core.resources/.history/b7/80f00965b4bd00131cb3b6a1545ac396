package alpha.java.cli;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import alpha.java.commonUtilities.CommonUtilities;


public class Apache_CLI
{
	private File inputFile, fileToEmbed, outputDirectory;
	private int tileRows, tileCols, rotation90, rotation180, rotation270;
	
	
	// Constructor
    public Apache_CLI()
    {
    	// Default parameter values
    	tileRows = CommonUtilities.ROWS_TO_TILE;
    	tileCols = CommonUtilities.COLS_TO_TILE;
    	rotation90 = 0;
    	rotation180 = 0;
    	rotation270 = 0;
    }
    
    
    // Configures the command line
    public Options createCommandLineOptions()
    {
        final Options options = new Options();
        int counter = 0;
        
        for (String option : CommonUtilities.CLI_OPTIONS)
        {
        	options.addOption(option, true, CommonUtilities.CLI_DESCRIPTIONS[counter]);
        	counter++;
        }

        options.addOption("help", false, "Show help information.");
        
        System.out.println("\t * Successfully created the command line options");
        
        return options;
    }
    
    
    // Prints the usage help text
    public void outputCommandLineHelp(final Options options)
    {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Please make sure you are using a correct syntax of CLI:\n", options);
    }

    
    // Scans the command line
    public void processCommandline(final CommandLine cl) throws IllegalArgumentException
    {
    	File file;
    	String value = "";
    	
    	if (cl != null)
    	{
            if (cl.hasOption("inputFile"))
            {
            	value = cl.getOptionValue("inputFile");
            	file = new File(value);
            	
            	if (file.isFile())
            		this.inputFile = file;
            	else
            		System.out.println("\t * The input file you have entered was not a valid file");
            	
                // Set input image
            }
            if (cl.hasOption("fileToEmbed"))
            {
            	value = cl.getOptionValue("fileToEmbed");
            	file = new File(value);
            	
            	if (new File(value).isFile())
            		this.fileToEmbed = file;
            	else
            		System.out.println("\t * The file to embed you have entered was not a valid file");
            		
                // Set file to embed
            }
            if (cl.hasOption("outputDir"))
            {
            	value = cl.getOptionValue("outputDir");
            	file = new File(value);
            	
            	if (file.isDirectory())
            		this.outputDirectory = file;
            	else
            		System.out.println("\t * The output directory you have entered was not a valid directory");
            	
            	// Set path to output directory
            }
            if (cl.hasOption("parameters"))
            {
            	parseParameterString(cl.getOptionValue("parameters"));
            }
            if (cl.hasOption("help"))
            {
            	System.out.println("User entered help-command:\n");
            	// Show help message
            }
    	}
    }
    
    
	// numberOfTiles=10&rotation90=10%&rotation180=50%&rotation270=30%
    private void parseParameterString(String value)
    {
    	int startIndex = 0;
    	
    	if (value.contains("numberOfTiles=") && value.charAt(16) == '-')
    	{
    		this.tileRows = Integer.parseInt(value.substring(startIndex + 14, value.indexOf('-')));
    		this.tileCols = Integer.parseInt(value.substring(startIndex + 17, value.indexOf('&')));
    		
    		System.out.println("\n\n\t * " + tileRows + " number of tile rows were extracted from the parameters string");
    		System.out.println("\t * " + tileCols + " number of tile cols were extracted from the parameters string");
    		
    		// ROTATION 90°
    		if (value.contains("&rotation90="))
    		{
    			startIndex = value.indexOf("&rotation90=");
    			this.rotation90 = Integer.parseInt(value.substring(startIndex + 12, startIndex + 14));
    			
    			System.out.println("\t * " + rotation90 + "% of 90° rotation was extracted from the parameters string");
    		}
    		// ROTATION 180°
    		if (value.contains("&rotation180="))
    		{
    			startIndex = value.indexOf("&rotation180=");
    			this.rotation180 = Integer.parseInt(value.substring(startIndex + 13, startIndex + 15));
    			
    			System.out.println("\t * " + rotation180 + "% of 180° rotation was extracted from the parameters string");
    		}
    		// ROTATION 270°
    		if (value.contains("&rotation270="))
    		{
    			startIndex = value.indexOf("&rotation270=");
    			this.rotation270 = Integer.parseInt(value.substring(startIndex + 13, startIndex + 15));
    			
    			System.out.println("\t * " + rotation270 + "% of 270° rotation was extracted from the parameters string");
    		}
    	}
    	else
    	{
			JOptionPane.showMessageDialog(null, "Parameters could not be parsed correctly, please make sure you entered all of the correct values.",
				      					 "Error", JOptionPane.ERROR_MESSAGE);
			
			System.exit(0);
    	}

	}


	public File[] extractArgumentPaths()
    {
    	if (inputFile != null && fileToEmbed != null && outputDirectory != null)
    	{
    		File[] returnArray = new File[3];
    		returnArray[0] = inputFile;
    		returnArray[1] = fileToEmbed;
    		returnArray[2] = outputDirectory;
    		
    		System.out.println("\n\n\t * Successfully extracted all the necessary arguments from the command line\n\n");
    		
    		return returnArray;
    	}
    	else
    		return null;
    }
	
	public int[] extractParameters()
	{
		return new int[] {tileRows, tileCols, rotation90, rotation180, rotation270};
	}
    
}