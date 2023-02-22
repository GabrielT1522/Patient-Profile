package Home;
import DefaultFrame.DefaultFrame;

import java.io.IOException;

public class HomeFrame extends DefaultFrame{
    public HomeFrame() throws IOException {
        HomePanel homePanel = new HomePanel();
        this.getContentPane().add(homePanel);
        this.setSize(1200,900);
    }
}
