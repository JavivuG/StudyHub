package studyhub.controlador;
import javax.servlet.http.HttpServletRequest;
import studyhub.data.UserDB;

public class UserInfo {
    public static String getUserNickname(HttpServletRequest request) {
        return UserDB.selectUser(request.getRemoteUser()).getNickname();
    }
}
