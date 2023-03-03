package Profile;

import Home.HomeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        searchButton.addActionListener(e -> searchPatients());

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
                /*"Address",
                "Address 2",
                "City",
                "State",
                "Zip Code",
                "County",
                "Work Phone",
                "Home Phone",
                "Cell Phone",
                "Race",
                "Ethnicity",
                "Sex"*/
        });
        table.setModel(tableModel);

        this.setBackground(Color.white);
        this.add(profilePanel);
    }

    public void searchPatients()
    {
        HomeModel homeModel = new HomeModel();
        String IDSearchTerm = patientIDSearchField.getText().toLowerCase();
        String lastNameSearchTerm = lastNameSearchField.getText().toLowerCase();
        String firstNameSearchTerm = firstNameSearchField.getText().toLowerCase();
        String DOBSearchTerm = DOBSearchField.getText();
        tableModel.setRowCount(0);

        for (String[] patient : homeModel.getPatientData()) {
            if (patient[0].toLowerCase().equals(IDSearchTerm)) {
                tableModel.addRow(patient);
            } else if ((patient[1].contains(lastNameSearchTerm) && patient[2].contains(firstNameSearchTerm) && patient[5].contains(DOBSearchTerm))) {
                tableModel.addRow(patient);
            }
        }
    }
}
