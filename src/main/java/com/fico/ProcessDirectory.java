package com.fico;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ProcessDirectory
 * Processes files - removes duplicate data
 * Created by Ray Gayton on 4/20/2016.
 * @param - full path of the directory to process *
 */

public class ProcessDirectory  implements IRemoveDup{

    private String filePath;

    public ProcessDirectory() {

    }

    /**
     * Walk the directory tree to process files
     *
     * @param
     * @return
     */
    public void processDirectory(File inDir)
	{
        File[] files = inDir.listFiles();

        for (File file : files) {
            // if this is a file, process it.
            if (file.isFile()) {
                System.out.println("File: " + file.getName());
            } else {
                readDirectory(file);
            }
        }
	}

    /**
     * Recursive directory reads
     * @param dirs
     */
    public void readDirectory (File dirs)
    {
        File[] files = dirs.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                System.out.println("Path/File: " + file.getAbsolutePath());
                deduplicateFile(file.getAbsoluteFile());

            } else {
                readDirectory(file);
            }
        }
    }

    /**
     * De-duplicate the text file
     *
     * @param
     * @return
     */
    public void deduplicateFile(File file)
    {
        try
        {
            this.filePath = file.getAbsolutePath();

            // de-duplicate the input file
            List<String> textData = TextFileUtils.readTextFile(filePath);

            // re-write the edited input file
            TextFileUtils.writerTextFile(textData, filePath);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

    }
}
