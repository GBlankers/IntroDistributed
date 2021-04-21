import java.io.*;
import java.net.*;

public class ServerUdp {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4900);
        final String fileInput = "C:\\Users\\gblan\\OneDrive\\Documenten\\UA\\3_EI\\SEM2\\6-DistributedSystems\\Practica\\Introduction\\FileIn\\test.txt";
        File file = new File(fileInput);
        byte[] buf = new byte[1024];
        int port;
        int counter = 0;
        System.out.println("ServerUDP Started ....");

        while(true){
            counter ++;
            DatagramPacket pkt = new DatagramPacket(buf, buf.length);
            socket.receive(pkt);

            InetAddress address = pkt.getAddress();
            port = pkt.getPort();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(buf, 0, buf.length);

            pkt = new DatagramPacket(buf, buf.length, address, port);
            socket.send(pkt);
            System.out.println("Input nr: "+ counter + " accepted");
        }
    }
}
