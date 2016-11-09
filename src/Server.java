import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class Server implements Runnable {


    private ArrayList<Connection> connections;
    final int PORT = 3456;
    private Thread thread;
    public Server(){
        connections  = new ArrayList<>();
        thread = new Thread(this);
        thread.start();
    }
    public ArrayList<Connection> getConnections() {
        return connections;
    }
    @Override
    public void run() {
        ServerSocket s = null;
        try {
            s = new ServerSocket(PORT);
            while (true) {
                Socket client = s.accept();
                Connection connection = new Connection(client, this);
                connections.add(connection);
                connection.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

}
