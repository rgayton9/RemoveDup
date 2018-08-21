package com.fico;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;
import static java.lang.System.in;

/**
 * RemoveDup
 * Processes files - removes duplicate data
 * Created by Ray Gayton on 4/20/2016.
 * @param - full path of the directory to process
 *
 */
public class RemoveDup
{
    public static void main(String args[])    {

        List<String> list = new ArrayList<String>();
        File fileName = null;

        /**
         * Check that the user entered a command line argument. If not, display an error message and exit.
         */
        if (args.length == 0) {
            System.out.println("Invalid number of arguments - missing input file name or directory.");
            System.out.println("\nRun as: RemoveDup [file name] or [directory name] ");
            exit (-1);
        }
        else
        {
            fileName = new File(args[0]);
        }

        /**
         * Check if input is a file or directory.
         * The file must end with .txt to indicate a text file.
         * If fileNames is a file and it is valid add it to the list of files to be processed.
         * If fileNames is a directory, walk the directories in readDirectory
         */
        if (fileName.isFile())
        {
            if (fileName.getAbsolutePath().endsWith((".txt")))
            {
                list.add(fileName.getAbsolutePath());
            }
            else
            {
                System.out.println("Invalid file type found: " + fileName.getAbsolutePath());
            }
        }
        else if (fileName.isDirectory())
        {
            readDirectory(fileName, list);
        }
        else
        {
            System.out.println("The file or directory entered is not found: " + fileName.getAbsolutePath());
            System.out.println("\nRun as: RemoveDup [file name] or [directory name] ");
            exit (-2);

        }
        /**
         * De-duplicate the files in the list
         * and re-write the text file.
         */
        for (String files : list)
        {
            try
            {
                // de-duplicate the input file
                List<String> textData = TextFileUtils.readTextFile(files);

                // re-write the de-duped input file
                TextFileUtils.writerTextFile(textData, files);
            }
            catch (IOException e)
            {
                System.err.println(e);
            }
        }

        System.out.println("RemoveDup completed.");
    }


    /**
     * Walk the directories - add valid files to List
     * @param fileDir
     * @param list
     */
    public static void readDirectory(File fileDir, List list)
    {
        File[] files = fileDir.listFiles();

        for (File file : files)
        {
            if (file.isFile())
            {
                if (file.getAbsolutePath().endsWith(".txt"))
                {
                    list.add(file.getAbsolutePath());
                }
                else
                {
                    System.out.println("Invalid file type found: " + file.getAbsolutePath());
                }
            }
            else
            {
                readDirectory(file, list);
            }
        }
    }
}