package Server;

import java.util.ArrayList;

public class ChatTools {//全部为静态方法，没有实例对象
    private static ArrayList<SeverThread> threadList = new ArrayList<>();
    //线程队列
    private ChatTools(){}//不需要实例化，所以构造函数设置为私有
    //将当前用户加入到线程队列
    public static void addThreadList(SeverThread client){
        threadList.add(client);
        giveMsg(client.getUser(),"我上线了！目前人数："+threadList.size());
    }

    public static void giveMsg(UserInfo user,String msg){
        msg = user.getUserName()+"说:"+msg;
        for (int i= 0;i<threadList.size();i++){
            SeverThread st = threadList.get(i);
            if ((st.user.getUserName().equals(user.getUserName()))&&msg.contains("没有权限！")){
                st.sengMsg(msg);
            }
            if (!(st.user.getUserName().equals(user.getUserName()))){
                st.sengMsg(msg);
            }
        }
    }
}
