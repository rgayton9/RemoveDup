package com.fico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***************************************************************************
* Example 1-3a TextFileUtils.java
*
* A utility class that demonstrate creating a static library and convenient
* methods to read a text file and store lines of text as a list.  It also
* provides the converse capability of writing output to a text file.
*
* email:  kentyang@alumni.ucsd.edu
* URL:    www.javathehut.org
*
* If you enjoy the Java 3 class and the examples provided, please look
* for other classes I teach.  In addition, please recommend my classes to
* your co-workers, friends and family. Thank you.
*
* @author Kent Yang
* @version 1.0 2010-01-13
* @revision 1.1 2016-04-25
* @author Ray Gayton
***************************************************************************/
public class TextFileUtils {

    /**
     * readTextFile - read the text file
     * @param inputFile
     * @return
     * @throws IOException
     */
    public static List<String> readTextFile(String inputFile)
        throws IOException
    {
        String str;

        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFile)));

        List<String> list = new ArrayList<String>();

        while ((str = in.readLine()) != null) {
            String content = null;
            content = removeDuplicates(str);
            list.add(removeExtraSpaces(content));
        }
        in.close();
        return list;
    }

    /**
     * writerTextFile - write the text file
     * @param list
     * @param outputFile
     * @throws IOException
     */
    public static void writerTextFile(List<String> list, String outputFile)
        throws IOException
    {
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(outputFile)));
        for(String str : list)
        {
            out.println(str);
        }
        out.close();
    }

    /**
     * removeDuplicates
     * @param line
     * @return string with duplicated words removed (only if more than one instance of a word is found).
     */
    public static String removeDuplicates(String line)
    {

        String patternString = "\\b(\\w+)\\b\\s+\\b\\1\\b";
        Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);
        String Replace = "";
        int count = 0;

        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            line = matcher.replaceAll(Replace);
        }

        return line;
    }

    /**
     * removeExtraSpaces
     * @param str
     * @return string with extra spaces removed.
     */
    public static String removeExtraSpaces(String str)
    {
        String trimmed;
        trimmed = str.replaceAll("\\s{2,}", " ").trim();
        return trimmed;
    }

    // private constructor to disallow instantiation outside of the class.
    // Our goal is to provide a static library of the tools.
    private TextFileUtils()
    {
    }
}