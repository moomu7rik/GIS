package com.example.ankan.transfer;



import android.os.Environment;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class main {

    public static void invoke() throws IOException {


        String ip = "192.168.0.9";
        String urlString = "http://"+ip+":8080";

        Collection A = new ArrayList();

        Collection B = new ArrayList();
        String filename = new String();
        String fileURL = new String();
        String saveDir = new String();


        String name = String.format("%s/test/", Environment.getExternalStorageDirectory().getAbsolutePath());
        File folder = new File(name);

        for (File file : folder.listFiles()) {


            B.add(file.getName());

        }
        System.out.println("Files in My system\n"+B);

        URL url = new URL(urlString);


        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));


        String line;
        line = reader.readLine();


        int i;
        for (i = 0; i < line.length(); i++) {



            if (line.charAt(i) == 'e' && line.charAt(i + 1) == '=') {

                i = i + 2;
                while (line.charAt(i) != '"') {

                    filename += line.charAt(i);
                    i++;

                }



                A.add(filename);
                filename="";
            }


        }
        System.out.println("Files in Server\n"+A);




        Collection diff = new ArrayList(A);
        diff.removeAll(B);
        System.out.println("File To be Taken from Server\n"+diff);
        Iterator it = diff.iterator();
        while (it.hasNext()) {
            String x = (String) it.next();


            fileURL = "http://" + ip + ":8080/get?name=" + x;

            saveDir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/test/"+x;



            File out = new File(saveDir);

            new Thread(new download(fileURL, out)).start();


        }


        reader.close();


    }
}

