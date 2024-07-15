import business.UserController;
import core.Helper;
import entity.User;
import view.DashboardUI;
import view.LoginUI;


public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        LoginUI loginUI = new LoginUI();
        UserController userController = new UserController();
        User user = userController.findByLogin("boratoroslu@gmail.com","123");
        DashboardUI dashboardUI = new DashboardUI(user);

    }


}
