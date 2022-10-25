package com.coding.io;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InputReader {
    private String separator;
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;
    public InputReader(String separator, InputStreamReader is){
        bufferedReader = new BufferedReader(is);
        this.separator = separator;
        stringTokenizer = new StringTokenizer("", separator);
    }

    public InputReader(){
        this(" ", new InputStreamReader(System.in));
    }
    public InputReader(InputStreamReader is){
        this(" ", is);
    }

    public InputReader(String separator){
        this(" ", new InputStreamReader(System.in));
    }

    private String readEntireLine() throws IOException {
        return bufferedReader.readLine();
    }

    private String readNextWord() throws IOException{
        if (!stringTokenizer.hasMoreTokens()) {
            stringTokenizer = new StringTokenizer(readEntireLine(), separator);
        }
        return stringTokenizer.nextToken();
    }

    public long readLong() throws IOException{
        return Long.parseLong(readNextWord());
    }

    public int readInt() throws IOException{
        return Integer.parseInt(readNextWord());
    }

    public char readChar() throws IOException{
        return readNextWord().charAt(0);
    }

    public String readLine() throws IOException{
        return readEntireLine();
    }

    public String readRemainingLine() throws IOException{
        return stringTokenizer.toString();
    }

    public String readWord() throws IOException{
        return readNextWord();
    }

    public int [] readIntegerArray() throws Exception{
        return Arrays.stream(readLine().split(separator)).mapToInt(x -> Integer.parseInt(x)).toArray();
    }
}
