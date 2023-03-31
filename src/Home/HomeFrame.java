package Home;
import DefaultFrame.DefaultFrame;
import java.io.IOException;

public class HomeFrame extends DefaultFrame{
    public final HomePanel homePanel = new HomePanel();

    public HomeFrame() throws IOException {
        this.getContentPane().add(homePanel);
        this.pack();
        //this.setSize(1200,900);
    }
}
