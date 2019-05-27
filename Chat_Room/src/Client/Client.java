package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{
    private Socket socket;
    private BufferedReader bReader;
    private PrintWriter pWriter;
    //初始化数据
    public Client()throws IOException {
        socket = new Socket("127.0.0.1",9003);
        bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pWriter = new PrintWriter(socket.getOutputStream());
        Login();
    }
    //验证登陆
    public void Login()throws IOException{
        int login = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (login < 2){//两次读取账号密码
            String str = bReader.readLine();
            if (str!=null){
                System.out.println(str);
            }
            pWriter.println(in.readLine());
            pWriter.flush();
            login++;
        }
        chat();
    }
    public void chat() throws IOException{
        start();//开启线程
        while (true){
            String str = bReader.readLine();
            if (str!=null){
                System.out.println(str);
                if (str.equals("没有权限！")){
                    socket.close();
                    System.exit(1);
                }
            }
        }
    }

    @Override
    //线程读取输入信息，将其传输给服务器
    public void run(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true){
                String str = in.readLine();
                pWriter.println(str);
                pWriter.flush();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String arg[]) throws IOException{
        Client client = new Client();
    }
}
