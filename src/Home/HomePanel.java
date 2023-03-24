package Home;

import Dashboard.DashboardPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.*;

public class HomePanel extends JPanel {

    public HomeModel homeModel;

    DashboardPanel dashboardPanel = new DashboardPanel();

    private final JPanel cardPanel = new JPanel(new CardLayout());
    private final CardLayout cardLayout = (CardLayout)(cardPanel.getLayout());

    // Profile Panel
    private final JTextField patientIDSearchField;
    private final JTextField firstNameSearchField;
    private final JTextField lastNameSearchField;
    private final JTextField DOBSearchField;

    private final DefaultTableModel tableModel;

    public HomePanel() throws IOException {
        JPanel homePanel = new JPanel();
        JPanel homeLogoPanel = new JPanel();
        JPanel homeInfoPanel = new JPanel();
        JPanel homeContentPanel = new JPanel();

        // ***** Build Home Panel *****
        JLabel welcomeLabel = new JLabel("Please enter the patient data as a .csv file:");
        BufferedImage logoImage = ImageIO.read(new File("/Users/gabrieltorres/Desktop/Laredo Health/Patient Profile/src/Home/logoImg.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        JButton chooseFileButton = new JButton("Choose a File");
        JButton viewPatientButton = new JButton("View a Patient Profile");
        JButton viewDashboardButton = new JButton("View Patient Dashboard");
        JButton insertPatientButton = new JButton("Insert a New Patient Profile");
        Font headingFont = new Font("Times New Roman", Font.BOLD, 20);

        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        homeLogoPanel.setBackground(Color.white);
        homeLogoPanel.add(logoLabel);

        welcomeLabel.setFont(headingFont);
        chooseFileButton.setFont(headingFont);
        chooseFileButton.addActionListener(e -> chooseFileListener());
        homeInfoPanel.add(welcomeLabel);
        homeInfoPanel.add(chooseFileButton);

        homeContentPanel.setPreferredSize(this.getMaximumSize());
        homeContentPanel.setBackground(new Color(0, 42, 92));

        viewPatientButton.setPreferredSize(new Dimension(350, 50));
        viewPatientButton.setFont(headingFont);
        viewPatientButton.addActionListener(e -> cardLayout.show(cardPanel, "Profile"));

        viewDashboardButton.setPreferredSize(new Dimension(350, 50));
        viewDashboardButton.setFont(headingFont);
        viewDashboardButton.addActionListener(e -> showDashboard());

        insertPatientButton.setPreferredSize(new Dimension(350, 50));
        insertPatientButton.setFont(headingFont);
        insertPatientButton.addActionListener(e -> cardLayout.show(cardPanel, "Insert"));

        // ***** Build Profile Panel *****

        JPanel profilePanel = new JPanel();
        JPanel profileContentPanel = new JPanel();

        JLabel refineSearchLabel = new JLabel("Refine Search");
        JLabel IDSearchLabel = new JLabel("Patient ID:");
        JLabel firstNameSearchLabel = new JLabel("First Name:");
        JLabel lastNameSearchLabel = new JLabel("Last Name:");
        JLabel DOBSearchLabel = new JLabel("Date of Birth (MM/DD/YYYY):");

        //Font headingFont = new Font("Times New Roman", Font.BOLD, 20);

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
                "Date of Birth",
        });
        table.setModel(tableModel);

        profilePanel.setBackground(Color.white);
        profilePanel.add(profileContentPanel);

        // ***** Build Dashboard Panel *****

        JPanel dashboardPanel = new JPanel();
        JPanel dashboardContentPanel = new JPanel();

        Map<String, Integer> ageCounts;
        Map<String, Integer> genderCounts;
        Map<String, Integer> raceCounts;
        JLabel genderPieChartLabel;
        JLabel ageBarChartLabel;
        JTextArea descriptiveStatsTextArea;
        JPanel mapPanel;

        dashboardPanel.add(dashboardContentPanel, BorderLayout.NORTH);
        ageCounts = new HashMap<>();
        genderCounts = new HashMap<>();
        raceCounts = new HashMap<>();


        dashboardPanel.setBackground(Color.white);
        dashboardPanel.add(dashboardContentPanel);

        // ***** Assemble cardPanel *****

        homeContentPanel.add(viewPatientButton);
        homeContentPanel.add(viewDashboardButton);
        homeContentPanel.add(insertPatientButton);

        homePanel.add(homeLogoPanel, BorderLayout.NORTH);
        homePanel.add(homeInfoPanel, BorderLayout.CENTER);
        homePanel.add(homeContentPanel, BorderLayout.SOUTH);

        cardPanel.add(homePanel, "Home");
        cardPanel.add(profilePanel, "Profile");
        cardPanel.add(dashboardPanel, "Dashboard");

        this.setBackground(Color.white);
        this.add(cardPanel);
    }

    public void chooseFileListener()
    {
        homeModel.chooseCSVFile();
        homeModel.readCSVData();
    }

    public void showDashboard(){
        cardLayout.show(cardPanel, "Dashboard");

        // Get the list of patients
        ArrayList<Patient> patientList = (ArrayList<Patient>) homeModel.getPatientData();

// Create a dataset of patient ages
        DefaultCategoryDataset ageDataset = new DefaultCategoryDataset();
        for (Patient patient : patientList) {
            int age = patient.getAge();
            ageDataset.addValue(1, "Age", Integer.toString(age));
        }

// Create the age bar chart
        JFreeChart ageChart = ChartFactory.createBarChart("Patient Ages", "Age", "Count", ageDataset, PlotOrientation.VERTICAL, false, true, false);

// Set the chart style
        CategoryPlot agePlot = ageChart.getCategoryPlot();
        agePlot.setBackgroundPaint(Color.WHITE);
        agePlot.setRangeGridlinePaint(Color.BLACK);

// Create the age chart panel
        ChartPanel ageChartPanel = new ChartPanel(ageChart);

// Add the age chart panel to the dashboard content panel
        dashboardContentPanel.add(ageChartPanel);

        // [Patient_no, Name_last, Name_first, Name_mid, Name_suffix, Dob, Address, Address_2, City, State, Zip_code, County, Work_phone, Home_phone, Cell_phone, Race, Ethnicity, Sex]
    }

    public void goHome() {
        cardLayout.show(cardPanel, "Home");
    }

    public Vector<String> makeRow(Patient patient){
        Vector<String> row = new Vector<>();
        row.add(patient.getPatientID());
        row.add(patient.getLastName());
        row.add(patient.getFirstName());
        row.add(patient.getMiddleName());
        row.add(patient.getDateOfBirth());
        return row;
    }

    public void searchPatients() {
        String IDSearchTerm = patientIDSearchField.getText();
        String lastNameSearchTerm = lastNameSearchField.getText().toLowerCase();
        String firstNameSearchTerm = firstNameSearchField.getText().toLowerCase();
        String DOBSearchTerm = DOBSearchField.getText();
        tableModel.setRowCount(0);

        // Set up boolean flags
        boolean hasIDSearchTerm = false;
        boolean hasLastNameSearchTerm = false;
        boolean hasFirstNameSearchTerm = false;
        boolean hasDOBSearchTerm = false;

        for (Patient patient : homeModel.getPatientData()) {
            int matchCount = 0;

            if (!Objects.equals(IDSearchTerm, "") && patient.getPatientID().equals(IDSearchTerm)) {
                hasIDSearchTerm = true;
                matchCount++;
            }
            if (!Objects.equals(lastNameSearchTerm, "") && patient.getLastName().equalsIgnoreCase(lastNameSearchTerm)) {
                hasLastNameSearchTerm = true;
                matchCount++;
            }
            if (!Objects.equals(firstNameSearchTerm, "") && patient.getFirstName().equalsIgnoreCase(firstNameSearchTerm)) {
                hasFirstNameSearchTerm = true;
                matchCount++;
            }
            if (!Objects.equals(DOBSearchTerm, "") && patient.getDateOfBirth().equals(DOBSearchTerm)) {
                hasDOBSearchTerm = true;
                matchCount++;
            }

            if (matchCount == 1 && hasIDSearchTerm) {
                tableModel.addRow(makeRow(patient));
            } else if (matchCount == 1 && hasLastNameSearchTerm){
                tableModel.addRow(makeRow(patient));
            }else if (matchCount == 1 && hasFirstNameSearchTerm){
                tableModel.addRow(makeRow(patient));
            }else if (matchCount == 1 && hasDOBSearchTerm){
                tableModel.addRow(makeRow(patient));
            } else if (matchCount == 2 && hasLastNameSearchTerm && hasFirstNameSearchTerm) {
                tableModel.addRow(makeRow(patient));
            }else if (matchCount == 3 && hasLastNameSearchTerm && hasFirstNameSearchTerm && hasDOBSearchTerm) {
                tableModel.addRow(makeRow(patient));
            }else if (matchCount == 4 && hasIDSearchTerm && hasLastNameSearchTerm && hasFirstNameSearchTerm && hasDOBSearchTerm) {
                tableModel.addRow(makeRow(patient));
            }

            hasIDSearchTerm = false;
            hasLastNameSearchTerm = false;
            hasFirstNameSearchTerm = false;
            hasDOBSearchTerm = false;
        }
    }

    public void openPatientProfile(Patient patient){
    }

}

