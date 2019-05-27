package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public void setUpServer(int port)throws IOException {
        ServerSocket sSocket = new ServerSocket(port);
        //创建接口，并将新线程加入线程池
        System.out.println("服务器创建成功，端口号："+port);
        ThreadPool threadPool = new ThreadPool(50,10000);
        Socket socket;
        while (true){
            socket = sSocket.accept();
            System.out.println("进入一个客户机连接："+socket.getRemoteSocketAddress().toString());
            threadPool.addThread(new SeverThread(socket));
        }
    }

    public static void main(String args[])throws IOException{
        ChatServer chatServer = new ChatServer();
        chatServer.setUpServer(9003);
    }
}
