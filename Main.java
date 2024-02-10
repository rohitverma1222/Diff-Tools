import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        String filePath1= args[0]; 

        String filePath2=args[1];

        try {
            // Read the content from file
            
            String content1 = new String(Files.readAllBytes(Paths.get(filePath1)));
            String content2 = new String(Files.readAllBytes(Paths.get(filePath2)));
            // Split the string by "."
            System.out.println(content1);
            
            String[] sentences1 = content1.split("\\.");
            String[] sentences2 = content2.split("\\.");
                
            findDifference(sentences1,sentences2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void findDifference(String []arr1,String []arr2)
    {
        TreeSet<String> hs=new TreeSet<>();
        for(String str:arr1)
            hs.add(str);
        
        for(String str:arr2)
            hs.add(str);

        String []lcs=sentenceLcs(arr1,arr2,0,0).split(",");
        for(String str:lcs)
        {
            hs.remove(str);
        }
        int idx=1;
        for(String str:hs)
        {
            String sign=idx++%2==0?"< ":"> ";
            System.out.println(sign+str);
        }
            
    }

    public static String sentenceLcs(String []str1,String []str2,int i,int j)
    {
        if (i >= str1.length || j >= str2.length)
            return "";

        if(str1[i].equals(str2[j]))
        {
            return str1[i]+","+sentenceLcs(str1,str2,i+1,j+1);
        }
        else{
            String left=sentenceLcs(str1,str2,i+1,j);
            String right=sentenceLcs(str1,str2,i,j+1);

            return  Math.max(left.length(), right.length()) == left.length() ? left : right;
        }
    }
}