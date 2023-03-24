package Profile;

import Home.HomeModel;
import Home.HomePanel;
import Home.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ProfilePanel extends JPanel {

    private final JTextField patientIDSearchField;
    private final JTextField firstNameSearchField;
    private final JTextField lastNameSearchField;
    private final JTextField DOBSearchField;

    private final DefaultTableModel tableModel;

    public ProfilePanel(){
        // Build Patient Profile Panel

        JPanel profilePanel = new JPanel();
        JPanel profileContentPanel = new JPanel();

        JLabel refineSearchLabel = new JLabel("Refine Search");
        JLabel IDSearchLabel = new JLabel("Patient ID:");
        JLabel firstNameSearchLabel = new JLabel("First Name:");
        JLabel lastNameSearchLabel = new JLabel("Last Name:");
        JLabel DOBSearchLabel = new JLabel("Date of Birth (MM/DD/YYYY):");

        Font headingFont = new Font("Times New Roman", Font.BOLD, 20);

        profilePanel.add(profileContentPanel, BorderLayout.NORTH);
        profileContentPanel.setLayout(new BoxLayout(profileContentPanel, BoxLayout.Y_AXIS));

        refineSearchLabel.setFont(headingFont);
        //profileContentPanel.add(refineSearchLabel);

        IDSearchLabel.setFont(headingFont);
        patientIDSearchField = new JTextField();
        profileContentPanel.add(IDSearchLabel);
        profileContentPanel.add(patientIDSearchField);
        patientIDSearchField.setColumns(20);

        firstNameSearchLabel.setFont(headingFont);
        firstNameSearchField = new JTextField();
        profileContentPanel.add(firstNameSearchLabel);
        profileContentPanel.add(firstNameSearchField);
        firstNameSearchField.setColumns(20);

        lastNameSearchLabel.setFont(headingFont);
        lastNameSearchField = new JTextField();
        profileContentPanel.add(lastNameSearchLabel);
        profileContentPanel.add(lastNameSearchField);
        lastNameSearchField.setColumns(20);

        DOBSearchLabel.setFont(headingFont);
        DOBSearchField = new JTextField();
        profileContentPanel.add(DOBSearchLabel);
        profileContentPanel.add(DOBSearchField);
        DOBSearchField.setColumns(20);

        JButton searchButton = new JButton("Search");
        profileContentPanel.add(searchButton);
        searchButton.addActionListener(e -> {
            try {
                searchPatients();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        profileContentPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(1150, 800));

        JTable table = new JTable();
        scrollPane.setViewportView(table);

        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {
                "Patient ID",
                "Last Name",
                "First Name",
                "Middle Name",
                "Suffix",
                "Date of Birth",
        });
        table.setModel(tableModel);

        this.setBackground(Color.white);
        this.add(profilePanel);
    }

    public Vector<String> makeRow(Patient patient){
        Vector<String> row = new Vector<>();
        row.add(patient.getPatientID());
        return row;
    }

    public void searchPatients() throws IOException {
        HomeModel homeModel = new HomeModel();

        String IDSearchTerm = patientIDSearchField.getText().toLowerCase();
        String lastNameSearchTerm = lastNameSearchField.getText().toLowerCase();
        String firstNameSearchTerm = firstNameSearchField.getText().toLowerCase();
        String DOBSearchTerm = DOBSearchField.getText();
        tableModel.setRowCount(0);

        ArrayList<Patient> patientData = (ArrayList<Patient>) homeModel.getPatientData();
        System.out.println(patientData.size());
        for (Patient patient : homeModel.getPatientData()) {
            if (patient.getPatientID().toLowerCase().equals(IDSearchTerm)) {
                tableModel.addRow(makeRow(patient));
            } else if ((patient.getLastName().contains(lastNameSearchTerm) && patient.getFirstName().contains(firstNameSearchTerm) && patient.getDateOfBirth().contains(DOBSearchTerm))) {
                tableModel.addRow(makeRow(patient));
            }
        }
    }
}
