import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    private final static String fileOutput = "C:\\Users\\gblan\\OneDrive\\Documenten\\UA\\3_EI\\SEM2\\6-DistributedSystems\\Practica\\Introduction\\FileOut\\test.txt";

    public static void main(String[] args){
        try {
            Socket sock = new Socket("127.0.0.1", 5000);
            byte[] bytearray = new byte[1024];
            InputStream is = sock.getInputStream();
            FileOutputStream fos = new FileOutputStream(fileOutput);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int bytesRead = is.read(bytearray, 0, bytearray.length);
            bos.write(bytearray, 0, bytesRead);
            bos.close();
            sock.close();
        } catch (UnknownHostException ukh){
            System.out.println("Can't find host");
        } catch (IOException ioe){
            System.out.println("Can't open file");
        }
    }
}
