import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientUdp {
    private final static String fileOutput = "C:\\Users\\gblan\\OneDrive\\Documenten\\UA\\3_EI\\SEM2\\6-DistributedSystems\\Practica\\Introduction\\FileOut\\test.txt";

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = new byte[1024];
        byte[] mybytearray = new byte[1024];
        InetAddress adress = InetAddress.getByName("localhost");
        DatagramPacket pkt = new DatagramPacket(buf, buf.length, adress, 4900);

        socket.send(pkt);

        socket.receive(pkt);

        mybytearray = pkt.getData();
        FileOutputStream fos = new FileOutputStream(fileOutput);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(mybytearray, 0, mybytearray.length);
        bos.close();

    }
}
