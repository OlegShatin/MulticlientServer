import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class Client implements Runnable {
    public void run() {
        int port = 3456;
        String host = "localhost";
        Socket s = null;
        try {
            s = new Socket(host, port);

            while (true) {
                InputStream is = s.getInputStream();
                int x = is.read();
                System.out.println(name+": " + x);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Thread thread;
    private String name;

    public Client(String name) {
// создаем поток, передавая поведение
        // нашего MyAwesomeThread
        thread = new Thread(this);
        thread.start();
        this.name = name;
    }

    public static void main(String[] args) {
        Client client = new Client("client");
    }
}
