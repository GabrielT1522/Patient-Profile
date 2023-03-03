package Home;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HomeModel {
    private String filePath;
    private List<String[]> patientData;

    public List<String[]> getPatientData(){
        return this.patientData;
    }

    String getFilePath(){
        return this.filePath;
    }

    public void chooseCSVFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a CSV file");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv Files", "csv");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            filePath = jfc.getSelectedFile().getPath();
            System.out.println(filePath);
        }
    }

    public void readCSVData() {
        String line;
        patientData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] patient = line.split(",");
                patientData.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}