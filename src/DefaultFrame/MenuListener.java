package DefaultFrame;

import Home.HomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuListener implements ActionListener
{
    private final DefaultFrame defaultFrame;
    private final HomePanel homePanel = new HomePanel();
    public MenuListener(DefaultFrame hm) throws IOException {
        defaultFrame = hm;
    }
    public void actionPerformed(ActionEvent evt)
    {
        String actionCommand = evt.getActionCommand();

        switch (actionCommand) {
            case "Home" ->  homePanel.goHome();
            case "About" -> defaultFrame.openAbout();
            case "Help" -> defaultFrame.openHelp();
            case "Exit" -> System.exit(0);
            default -> System.out.println("ERROR: unknown action command.");
        }
    }
}
