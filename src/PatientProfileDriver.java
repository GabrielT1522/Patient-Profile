import DefaultFrame.DefaultFrame;
import java.io.IOException;

public class PatientProfileDriver extends DefaultFrame {

    public PatientProfileDriver() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        PatientProfileFrame patientProfileFrame = new PatientProfileFrame();
        patientProfileFrame.showIt("Laredo Health Patient Profiles");
    }
}