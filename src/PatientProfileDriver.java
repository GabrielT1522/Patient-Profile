import Home.HomeFrame;

import java.io.IOException;

public class PatientProfileDriver extends HomeFrame {

    public PatientProfileDriver() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.showIt("Laredo Health Patient Profiles");
    }
}