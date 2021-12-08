import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String address;

    private int port;

    private Socket socket;

    public Client(String address, int port) {
        System.out.println("客户端启动");
        try {
            this.address = address;
            this.port = port;
            this.socket = new Socket(address, port);
        } catch (Exception e) {
            this.socket = null;
            System.out.println("连接建立失败！\n" + e.getMessage());
        }
    }

    public void communicate() {
        if (this.socket == null) {
            System.out.println("连接未建立");
            return;
        }
        try {
            Scanner sc = new Scanner(System.in);
            InputStreamReader reader =
                    new InputStreamReader(this.socket.getInputStream());
            OutputStreamWriter writer =
                    new OutputStreamWriter(this.socket.getOutputStream());
            while (true) {
                //输入quit 默认退出
                String msg = sc.nextLine();
                writer.write(msg + "$");
                //
            }
        } catch (Exception e) {

        }

    }

}
