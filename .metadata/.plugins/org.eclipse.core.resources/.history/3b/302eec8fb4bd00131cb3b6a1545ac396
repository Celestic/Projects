package alpha.java.controllers;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import alpha.java.cli.Apache_CLI;
import alpha.java.commonUtilities.CommonUtilities;

public class CLIController
{
    private final Log LOGGER;
    private final String NL;
	private CommandLine cl;
	private String[] programArguments;
	private Options options;
	private Apache_CLI cli;
 
	
    public CLIController(String[] arguments)
    {
    	LOGGER = LogFactory.getLog(Apache_CLI.class);
    	NL = System.getProperty("line.separator");
    	programArguments = arguments;
    	cli = new Apache_CLI();
    }
    
    
    // Create a PosixParser and the commandLineOptions
    public void initiateCommandLineOptions()
    {
	    final Parser commandlineparser = new PosixParser();
	    options = cli.createCommandLineOptions();
	    
	    try
	    {
	        cl = commandlineparser.parse(options, programArguments, true);
	    }
	    catch (final ParseException exp)
	    {
	        LOGGER.error("Unexpected exception:" + exp.getMessage() + NL);
	    }
    }
   
    
    // Process command line and store parameter in attributes
    public void processCommandLine()
    {
	    try
	    {
	    	if (cl != null)
	    	{
	            if (cl.hasOption("help") || programArguments.length < CommonUtilities.CLI_OPTIONS.length)
	            	cli.outputCommandLineHelp(options);
	            else
	            	cli.processCommandline(cl);
	    	}
	    }
	    catch (final IllegalArgumentException e)
	    {
	        cli.outputCommandLineHelp(options);
	        LOGGER.warn("Illegal arguments on command line: " + e.getMessage() + NL);
	        
	        return;
	    }
    }
    
    
    public File[] extractArgumentPaths()
    {
    	File[] argumentPaths = cli.extractArgumentPaths();
    	
    	if (argumentPaths != null)
    		return argumentPaths;
    	else
    		cli.outputCommandLineHelp(options);
    	
    	return null;
    }
    
    
    public int[] extractParameters()
    {
    	return cli.extractParameters();
    }
}
