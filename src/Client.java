import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class Client implements Runnable {
    private Scanner scanner;

    public void run() {
        int port = 3456;
        String host = "localhost";
        Socket s = null;
        try {
            s = new Socket(host, port);

            PrintWriter printWriter = new PrintWriter(s.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner sc = new Scanner(System.in);
            while (true) {
                //send
                System.out.print("enter mess:");
                String message = scanner.nextLine();
                printWriter = new PrintWriter(s.getOutputStream(), true);
                printWriter.println(message);
                //get
                bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String x = bufferedReader.readLine();
                System.out.println("from net: "+ x);

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
        scanner = new Scanner(System.in);
        thread.start();
        this.name = name;

    }

    public static void main(String[] args) {
        Client client = new Client("client");
    }
}
