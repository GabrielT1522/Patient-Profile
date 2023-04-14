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
            case "Home":
                homePanel.displayHomeCard();
                break;
            case "View Patient":
                homePanel.displayPatientCard();
                break;
            case "Dashboard":
                homePanel.displayDashboardCard();
                break;
            case "Insert Patient":
                homePanel.displayInsertCard();
                break;
            case "About":
                homeFrame.openAbout();
                break;
            case "Help":
                homeFrame.openHelp();
                break;
            case "Load":
                homePanel.chooseFileListener();
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                System.out.println("ERROR: unknown action command.");
                break;
        }
    }
}
