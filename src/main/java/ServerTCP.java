import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

    public static void main(String[] args) throws IOException{
        int serverPort = 5000;
        ServerSocket serverSocket = new ServerSocket(serverPort);

        int counter = 0;
        System.out.println("ServerTCP Started ....");

        while(true){
            try {
                counter ++;
                Socket socket = serverSocket.accept();
                System.out.println("Input nr: "+ counter + " accepted");
                new ServerClientThread(socket,counter).start();
            } catch (IOException e){
                System.out.println("IO exception");
                break;
            }

        }
    }

    static class ServerClientThread extends Thread{
        Socket serverClient;
        int clientNo;
        private final String fileInput = "C:\\Users\\gblan\\OneDrive\\Documenten\\UA\\3_EI\\SEM2\\6-DistributedSystems\\Practica\\Introduction\\FileIn\\test.txt";
        File file = new File(fileInput);

        public ServerClientThread(Socket inSocket, int counter){
            serverClient = inSocket;
            clientNo = counter;
        }

        public void run(){
            try {
                byte[] fileBytes = new byte[(int) file.length()];
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                int bytesRead = bufferedInputStream.read(fileBytes, 0, fileBytes.length);
                OutputStream os = serverClient.getOutputStream();
                os.write(fileBytes, 0, bytesRead);
                os.flush();
                bufferedInputStream.close();
                os.close();
                serverClient.close();
            } catch (IOException ioException){
                System.out.println("Can't open file");
            }

            System.out.println("Client nr: " + clientNo + " closed");
        }
    }
}
