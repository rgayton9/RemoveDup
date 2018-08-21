package com.fico;

import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ProcessFile
 * Processes files - removes duplicate data
 * Created by Ray Gayton on 4/20/2016.
 * @param - full path of the file to process *
 */
public class ProcessFile implements IRemoveDup {

    public ProcessFile() {
    }

    /**
     * De-duplicate the text file
     * @param
     * @return
     */
    public void processFile(File inFile)
	{
		try
        {
            // de-duplicate the input file
            List<String> textData = TextFileUtils.readTextFile(inFile.getAbsolutePath());

            // re-write the edited input file
            TextFileUtils.writerTextFile(textData, inFile.getAbsolutePath());
        }
        catch (IOException e)
        {
            System.err.println(e);
        }		
	}

}
