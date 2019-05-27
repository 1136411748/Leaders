package Server;

import java.util.HashMap;
import java.util.Map;

public class ListUser {
    private static Map<String,UserInfo> userlist = new HashMap<>();
    static {
        for (int i = 1;i <= 10; i++){
            UserInfo user = new UserInfo();
            user.setUserName("name"+i);
            user.setPassward("psw"+i);
            userlist.put(user.getUserName(),user);
        }
    }

    public static boolean UserIsRight(UserInfo user){
        if (userlist.containsKey(user.getUserName())){
            return true;
        }
        System.out.println("用户"+user.getUserName()+"验证失败");
        return false;
    }
}
