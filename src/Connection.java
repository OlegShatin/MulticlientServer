import java.io.*;
import java.net.Socket;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class Connection implements Runnable {
    private Socket client;
    private Thread thread;
    private Server server;

    public Connection(Socket client, Server server) {
        this.client = client;
        this.server = server;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter printWriter = null;
            while (true) {

                String message = bufferedReader.readLine();
                System.out.println(message);
                for (Connection anotherClientConn: server.getConnections()) {

                    printWriter = new PrintWriter(anotherClientConn.getSocket().getOutputStream(), true);
                    printWriter.println(message);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        return client;
    }

    public void start() {
        thread.start();
    }
}
