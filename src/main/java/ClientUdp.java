import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

public class ClientUdp {
    private final static String fileOutput = "C:\\Users\\gblan\\OneDrive\\Documenten\\UA\\3_EI\\SEM2\\6-DistributedSystems\\Practica\\Introduction\\FileOut\\test.txt";

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = new byte[1024];
        byte[] bytearray;
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket pkt = new DatagramPacket(buf, buf.length, address, 4900);

        socket.send(pkt);

        socket.receive(pkt);

        bytearray = pkt.getData();
        FileOutputStream fos = new FileOutputStream(fileOutput);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(bytearray, 0, bytearray.length);
        bos.close();

    }
}
