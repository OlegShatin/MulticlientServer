import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Oleg Shatin
 *         11-501
 */
public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = 3456;
        ServerSocket s = new ServerSocket(PORT);
        Socket client1 = s.accept();
        while (true) {

            OutputStream os = client1.getOutputStream();
            byte b = (byte) new Random().nextInt(127);
            os.write(b);
        }
    }


}
