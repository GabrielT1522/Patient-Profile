package DefaultFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener
{
    private final DefaultFrame defaultFrame;
    public MenuListener(DefaultFrame hm)
    {
        defaultFrame = hm;
    }
    public void actionPerformed(ActionEvent evt)
    {
        String actionCommand = evt.getActionCommand();

        switch (actionCommand) {
            case "About" -> defaultFrame.openAbout();
            case "Help" -> defaultFrame.openHelp();
            case "Load" -> defaultFrame.openFile();
            case "Calculator" -> defaultFrame.openCalculator();
            case "Exit" -> System.exit(0);
            default -> System.out.println("ERROR: unknown action command.");
        }
    }
}
