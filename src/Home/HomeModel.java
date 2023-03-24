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
    private List<Patient> patientData;

    public HomeModel(){
        this.filePath = "";
        this.patientData = new ArrayList<>();
    }

    public List<Patient> getPatientData(){
        return this.patientData;
    }

    public void setPatientData(List<Patient> patientData) {
        this.patientData = patientData;
    }

    String getFilePath(){
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        System.out.println(filePath);
        this.filePath = filePath;
    }

    public void chooseCSVFile() {
        // for testing
        JFileChooser jfc = new JFileChooser("/Users/gabrieltorres/Desktop/Laredo Health");
        //JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a CSV file");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv Files", "csv");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            setFilePath(jfc.getSelectedFile().getPath());
        }
    }

    public void readCSVData() {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Patient> newPatientData = new ArrayList<>();
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] patientList = line.split(",");
                Patient patient = new Patient();
                patient.setPatientID(patientList[0]);
                patient.setLastName(patientList[1]);
                patient.setFirstName(patientList[2]);
                patient.setMiddleName(patientList[3]);
                patient.setSuffix(patientList[4]);
                patient.setDateOfBirth(patientList[5]);
                patient.setAddress1(patientList[6]);
                patient.setAddress2(patientList[7]);
                patient.setCity(patientList[8]);
                patient.setState(patientList[9]);
                patient.setZipCode(patientList[10]);
                patient.setCounty(patientList[11]);
                patient.setWorkPhone(patientList[12]);
                patient.setHomePhone(patientList[13]);
                patient.setCellPhone(patientList[14]);
                patient.setRace(patientList[15]);
                patient.setEthnicity(patientList[16]);
                patient.setSex(patientList[17]);
                patient.setAge(patient.getDateOfBirth());
                newPatientData.add(patient);
            }
            setPatientData(newPatientData);
            System.out.println("Data set size: "+getPatientData().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}