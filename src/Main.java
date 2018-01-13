import gui.setup.SetupController;
import gui.setup.SetupView;

/**
 * Created by oztiryakimeric on 12.12.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SetupView setupView = new SetupView();
        SetupController controller = new SetupController(setupView);
        controller.start();
    }
}
