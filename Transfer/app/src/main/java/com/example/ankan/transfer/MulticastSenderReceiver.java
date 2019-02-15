package com.example.ankan.transfer;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.List;

public class MulticastSenderReceiver {
    public static List<String> Get(List<String> ips,String ip)
    {
        try {
            int port=8800;
            InetAddress group = InetAddress.getByName("255.4.5.6");
            MulticastSocket sendSocket = new MulticastSocket();
            readThread rt =new readThread(group,port);
            rt.start();
            String msg = ip;
            DatagramPacket packet= new DatagramPacket(msg.getBytes(),msg.length(),group,8800);
            sendSocket.send(packet);
            sendSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ips;
    }
}

class readThread extends Thread{
    InetAddress group;
    int multicastport;
    int max=100;
    readThread(InetAddress g, int port)
    {
        group=g;
        multicastport=port;

    }
    public void run()
    {
        try
        {
            MulticastSocket readSocket = new MulticastSocket(multicastport);
            readSocket.joinGroup(group);
            while (true)
            {
                byte[] msg = new byte[max];
                DatagramPacket packet = new DatagramPacket(msg,msg.length);
                readSocket.receive(packet);

                System.out.println(new String(msg));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
