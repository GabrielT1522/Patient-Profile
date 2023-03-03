import DefaultFrame.DefaultFrame;
import Home.HomePanel;

import java.io.IOException;

public class PatientProfileFrame extends DefaultFrame{
    public PatientProfileFrame() throws IOException {
        HomePanel homePanel = new HomePanel();
        this.getContentPane().add(homePanel);
        this.setSize(1200,900);
    }
}
