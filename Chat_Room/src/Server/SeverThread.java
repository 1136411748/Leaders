package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SeverThread extends Thread{
    private Socket client;
    private PrintWriter output;
    UserInfo user;

    public SeverThread(Socket socket){
        this.client = socket;
    }

    public UserInfo getUser() {
        return this.user;
    }

    @Override
    public void run() {
        process();
    }

    public void process(){
        try {
            output = new PrintWriter(client.getOutputStream());
            BufferedReader bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            sengMsg("欢迎来到聊天室请输入用户名：");
            String name = bReader.readLine();
            sengMsg("请输入密码：");
            String passward = bReader.readLine();
            user = new UserInfo();
            user.setUserName(name);
            user.setPassward(passward);
            boolean bs = ListUser.UserIsRight(user);
            System.out.println(bs);
            if(!bs){
                sengMsg("没有权限！");
                client.close();
                return;
            }
            ChatTools.addThreadList(this);
            String line;
            while(!(line = bReader.readLine()).equals("bye")){
                System.out.println("服务器收集到的数据为："+line);
                ChatTools.giveMsg(this.user,line);
            }
            if (line.equals("bye")){
                this.client.close();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void sengMsg(String msg){
        output.println(msg);
        output.flush();
    }
}
