import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;

    public Server(Integer port) {
        try{
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("服务创建失败！");
            this.serverSocket = null;
            System.out.println(e.getMessage());
        }

    }

    public void start() {
        System.out.println("#####启动聊天服务器######");
        if (this.serverSocket == null) {
            System.out.println("服务未初始化");
            return;
        }
        try {
            while (true) {
                //单线程
                Socket client = this.serverSocket.accept();
                InputStreamReader reader =
                        new InputStreamReader(client.getInputStream());
                OutputStreamWriter writer =
                        new OutputStreamWriter(client.getOutputStream());
                char[] charBuff = new char[100];
                CharacterQueue queue = new CharacterQueue();
                int n = 0;
                while ((n = reader.read(charBuff)) != -1) {
                    //约定$表示一条短信的分割 以解决粘包问题
                    queue.add(charBuff, n);
                    String msg = queue.get();
                    if (msg != null) {
                        writer.write(msg);
                    }
                }
                // 客户端关闭连接
                reader.close();
                writer.close();
                client.close();
            }
        } catch (Exception e) {
            System.out.println("出现错误\n" + e.getMessage());
        }

    }


}
