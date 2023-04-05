package Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {
    private final HomeFrame homeFrame;
    private final HomePanel homePanel;

    public MenuListener(HomeFrame homeFrame, HomePanel homePanel) {
        this.homeFrame = homeFrame;
        this.homePanel = homePanel;
    }

    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        switch (actionCommand) {
            case "Home" -> homePanel.displayHomeCard();
            case "View Patient" -> homePanel.displayPatientCard();
            case "Dashboard" -> homePanel.displayDashboardCard();
            case "Insert Patient" -> homePanel.displayInsertCard();
            case "About" -> homeFrame.openAbout();
            case "Help" -> homeFrame.openHelp();
            case "Exit" -> System.exit(0);
            default -> System.out.println("ERROR: unknown action command.");
        }
    }
}
