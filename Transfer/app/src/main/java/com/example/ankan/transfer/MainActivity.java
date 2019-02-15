package com.example.ankan.transfer;



import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.File;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;

import fi.iki.elonen.NanoHTTPD;

public class MainActivity extends AppCompatActivity {

    private WebServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String i="";
        i=ip.ipadd();

        TextView deviceIP = findViewById(R.id.textView3);
        deviceIP.setText("Device ip "+i);

        final Button button;
        button = findViewById(R.id.b);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    System.out.println(InetAddress.getLocalHost().getHostAddress());
                    server = new WebServer();
                    try {
                        server.start();
                    } catch (IOException ioe) {
                        Log.w("Httpd", "The server could not start.");
                    }
                    Log.w("Httpd", "Web server initialized.");
                    main.invoke();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }





            }

        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (server != null)
            server.stop();
    }
    public class WebServer extends NanoHTTPD {


        public WebServer() {
            super(8080);
        }

        public Response serve(String uri, Method method,
                              Map<String, String> header, Map<String, String> parameters,
                              Map<String, String> files) {

            File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/");


            Map<Integer, List<String>> prio = new HashMap<>();
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<String> list3 = new ArrayList<>();
            List<String> list4 = new ArrayList<>();
            List<String> list5 = new ArrayList<>();


            for (File file : folder.listFiles()) {
                //System.out.println(file.getName());

                if (GetFileExtension.getFileExtension(file).equals("txt")) {


                    list1.add(file.getName());
                    prio.put(new Integer(1), list1);

                }

                if (GetFileExtension.getFileExtension(file).equals("pdf")) {
                    list2.add(file.getName());
                    prio.put(new Integer(2), list2);
                }

                if (GetFileExtension.getFileExtension(file).equals("jpg")||GetFileExtension.getFileExtension(file).equals("png") || GetFileExtension.getFileExtension(file).equals("jpeg"))
                {     list3.add(file.getName());
                    prio.put(new Integer(3), list3);
                }

                if (GetFileExtension.getFileExtension(file).equals("mp3")) {
                    list4.add(file.getName());
                    prio.put(new Integer(4), list4);
                }

                if (GetFileExtension.getFileExtension(file).equals("mp4")) {
                    list5.add(file.getName());
                    prio.put(new Integer(5), list5);
                }
            }

            String filename = "";

            //TO CREATE THE WEBPAGE
            if (uri.equals("/")) {
                System.out.println(uri);

                String st = "";
                String x = "";

                for (Map.Entry<Integer, List<String>> en : prio.entrySet()) {
                    for (String obj : en.getValue()) {

                        x = obj;
                        st = st + "<a href=\"/get?name=" + x + "\">" + x + "</a>";
                        st = st + "<br>";


                    }
                }


                return new Response(Response.Status.OK, MIME_HTML, st);


            }

            //TO DOWNLOAD
            else if (uri.equals("/get"))

            {
//            System.out.println(uri);
//            String x = header.get("referer");
//            System.out.println("THE REFERER"+x);


                FileInputStream fis = null;
                File f = null;
                try {
                    f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/" + parameters.get("name")); //path exists and its correct
                    fis = new FileInputStream(f);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

                String mimeType = mimeTypesMap.getContentType(filename);
                return new NanoHTTPD.Response(Response.Status.OK, mimeType, fis);


            }




            else {
//            System.out.println(uri);

                return new NanoHTTPD.Response("404 File Not Found");
            }

        }
    }
}


