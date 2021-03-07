import controller.AccountController;
import controller.EventController;
import controller.FileController;
import controller.UserController;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        UserController  controller = new UserController();
        controller.getAll();
    }
}
