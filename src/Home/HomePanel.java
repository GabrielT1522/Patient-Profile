package Home;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;


public class HomePanel extends JPanel {

    // ***** Home Panel Contents *****
    public final HomeModel homeModel = new HomeModel();

    private final JPanel homePanel = new JPanel();
    private final JPanel homeLogoPanel = new JPanel();
    private final JPanel homeInfoPanel = new JPanel();
    private final JPanel homeContentPanel = new JPanel();

    public final JLabel welcomeLabel = new JLabel("Please enter the patient data as a .csv file:");
    public final BufferedImage logoImage = ImageIO.read(new File("src/logoImg.png"));
    public final JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
    public final JButton chooseFileButton = new JButton("Choose a File");
    public final JButton viewPatientButton = new JButton("View a Patient Profile");
    public final JButton viewDashboardButton = new JButton("View Patient Dashboard");
    public final JButton insertPatientButton = new JButton("Insert a New Patient Profile");
    public final  Font headingFont = new Font("Times New Roman", Font.BOLD, 20);

    public final  JPanel dashboardPanel = new JPanel();
    public final JPanel dashboardContentPanel = new JPanel();

    private final JPanel cardPanel = new JPanel(new CardLayout());
    private final CardLayout cardLayout = (CardLayout)(cardPanel.getLayout());

    // ***** Patient Profile Panel Contents *****
    private final JPanel profilePanel = new JPanel();
    private final JPanel profileContentPanel = new JPanel();

    private final JLabel IDSearchLabel = new JLabel("Patient ID:");
    private final JLabel firstNameSearchLabel = new JLabel("First Name:");
    private final JLabel lastNameSearchLabel = new JLabel("Last Name:");
    private final JLabel DOBSearchLabel = new JLabel("Date of Birth (MM/DD/YYYY):");

    private JTextField patientIDSearchField;
    private JTextField firstNameSearchField;
    private JTextField lastNameSearchField;
    private JTextField DOBSearchField;

    private final JTable searchTable = new JTable();
    private DefaultTableModel searchTableModel;

    // ***** Dashboard Panel Contents *****

    // ***** Insert Patient Panel Contents *****
    JPanel insertPatientPanel = new JPanel();

    public HomePanel() throws IOException {


        // ***** Build Home Panel *****
        buildHome();

        // ***** Assemble cardPanel *****

        cardPanel.add(homePanel, "Home");
        cardPanel.add(profilePanel, "Profile");
        cardPanel.add(dashboardPanel, "Dashboard");
        cardPanel.add(insertPatientPanel, "Insert");

        this.setBackground(Color.white);
        this.add(cardPanel);
    }

    public void displayHomeCard() {
        cardLayout.show(cardPanel, "Home");
        System.out.println("Home card now showing.");
    }

    public void displayPatientCard() {
        // Clear fields and table before displaying
        patientIDSearchField.setText("");
        firstNameSearchField.setText("");
        lastNameSearchField.setText("");
        DOBSearchField.setText("");
        searchTableModel.setRowCount(0);
        cardLayout.show(cardPanel, "Profile");
        System.out.println("Patient card now showing.");
    }

    public void displayDashboardCard() {
        cardLayout.show(cardPanel, "Dashboard");
        System.out.println("Dashboard card now showing.");
    }

    public void displayInsertCard() {
        cardLayout.show(cardPanel, "Insert");
        System.out.println("Insert card now showing.");
    }

    public void chooseFileListener()
    {
        try{
            homeModel.chooseCSVFile();
            homeModel.readCSVData();

            cardLayout.show(cardPanel, "Home");
            viewPatientButton.setEnabled(true);
            viewDashboardButton.setEnabled(true);
            insertPatientButton.setEnabled(true);

            // ***** Build Profile Panel *****
            buildPatientProfile();

            // ***** Build Dashboard Panel *****
            buildDashboard();

            // ***** Build insertPatient Panel
            buildInsertPatient();

            welcomeLabel.setText("File Chosen: "+homeModel.getFilePath());
            welcomeLabel.setForeground(new Color(0, 42, 92));
        }catch (Exception e){
            welcomeLabel.setText("Please enter a valid .csv file:");
            welcomeLabel.setForeground(Color.red);
            viewPatientButton.setEnabled(false);
            viewDashboardButton.setEnabled(false);
            insertPatientButton.setEnabled(false);
        }finally {
            if (Objects.equals(homeModel.getFilePath(), "")){
                welcomeLabel.setText("Please enter a valid .csv file:");
                welcomeLabel.setForeground(Color.red);
                viewPatientButton.setEnabled(false);
                viewDashboardButton.setEnabled(false);
                insertPatientButton.setEnabled(false);
            }
        }
    }

    public void buildHome(){
        // ***** Build Home Panel *****
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
        viewPatientButton.addActionListener(e -> displayPatientCard());

        viewDashboardButton.setPreferredSize(new Dimension(350, 50));
        viewDashboardButton.setFont(headingFont);
        viewDashboardButton.addActionListener(e -> displayDashboardCard());

        insertPatientButton.setPreferredSize(new Dimension(350, 50));
        insertPatientButton.setFont(headingFont);
        insertPatientButton.addActionListener(e -> displayInsertCard());

        homeContentPanel.add(viewPatientButton);
        homeContentPanel.add(viewDashboardButton);
        homeContentPanel.add(insertPatientButton);

        viewPatientButton.setEnabled(false);
        viewDashboardButton.setEnabled(false);
        insertPatientButton.setEnabled(false);

        homePanel.add(homeLogoPanel, BorderLayout.NORTH);
        homePanel.add(homeInfoPanel, BorderLayout.CENTER);
        homePanel.add(homeContentPanel, BorderLayout.SOUTH);
    }

    public void buildPatientProfile(){
        profileContentPanel.removeAll();
        revalidate();
        repaint();

        // Build Panel
        profilePanel.add(profileContentPanel, BorderLayout.NORTH);
        profileContentPanel.setLayout(new BoxLayout(profileContentPanel, BoxLayout.Y_AXIS));

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
        scrollPane.setPreferredSize(new Dimension(1150, 450));

        scrollPane.setViewportView(searchTable);

        searchTableModel = new DefaultTableModel(new Object[][] {}, new String[] {
                "Patient ID",
                "Last Name",
                "First Name",
                "Middle Name",
                "Date of Birth",
        });
        searchTable.setModel(searchTableModel);

        JButton selectPatientButton = new JButton("Select this Patient");
        profileContentPanel.add(selectPatientButton);
        selectPatientButton.addActionListener(e -> openPatientProfile());

        profilePanel.setBackground(Color.white);
        profilePanel.add(profileContentPanel);
    }

    public void buildDashboard(){
        dashboardContentPanel.removeAll();
        revalidate();
        repaint();
        // Get the list of patients
        ArrayList <Patient> patientList = (ArrayList <Patient> ) homeModel.getPatientData();

        // Create a dataset of patient ages
        HistogramDataset ageDataset = new HistogramDataset();
        double[] ageValues = new double[patientList.size()];
        for (int i = 0; i < patientList.size(); i++) {
            ageValues[i] = patientList.get(i).getAge();
        }
        int binSize = 50; // interval size for bins
        int maxAge = 120; // maximum age for the chart
        ageDataset.addSeries("Age", ageValues, binSize, 0, maxAge);

        // Create the age histogram chart
        JFreeChart ageChart = ChartFactory.createHistogram("Patient Ages", "Age", "Count", ageDataset, PlotOrientation.VERTICAL, false, true, false);

        // Set the chart style
        XYPlot agePlot = ageChart.getXYPlot();
        agePlot.setBackgroundPaint(Color.WHITE);
        agePlot.setRangeGridlinePaint(Color.BLACK);

        // Create the age chart panel
        ChartPanel ageChartPanel = new ChartPanel(ageChart);

        // Add the age chart panel to the dashboard content panel
        dashboardContentPanel.add(ageChartPanel);

        // Count the number of patients for each race and ethnicity
        HashMap< String, Integer > raceCounts = new HashMap < > ();
        HashMap < String, Integer > ethnicityCounts = new HashMap < > ();
        for (Patient patient: patientList) {
            String race = patient.getRace();
            String ethnicity = patient.getEthnicity();

            // Skip any values that are numeric
            if (race.matches("\\d+") || ethnicity.matches("\\d+")) {
                continue;
            }

            raceCounts.put(race, raceCounts.getOrDefault(race, 0) + 1);
            ethnicityCounts.put(ethnicity, ethnicityCounts.getOrDefault(ethnicity, 0) + 1);
        }

        // Create the race pie chart
        DefaultPieDataset raceDataset = new DefaultPieDataset();
        for (String race: raceCounts.keySet()) {
            raceDataset.setValue(race, raceCounts.get(race));
        }
        JFreeChart raceChart = ChartFactory.createPieChart("Patient Race Breakdown", raceDataset, true, true, false);

        // Create the ethnicity pie chart
        DefaultPieDataset ethnicityDataset = new DefaultPieDataset();
        for (String ethnicity: ethnicityCounts.keySet()) {
            ethnicityDataset.setValue(ethnicity, ethnicityCounts.get(ethnicity));
        }
        JFreeChart ethnicityChart = ChartFactory.createPieChart("Patient Ethnicity Breakdown", ethnicityDataset, true, true, false);

        // Create the race and ethnicity chart panels
        ChartPanel raceChartPanel = new ChartPanel(raceChart);
        ChartPanel ethnicityChartPanel = new ChartPanel(ethnicityChart);

        // Add the race and ethnicity chart panels to the dashboard content panel
        dashboardContentPanel.setLayout(new GridLayout(3, 0));
        dashboardContentPanel.add(raceChartPanel);
        dashboardContentPanel.add(ethnicityChartPanel);

        // Count the number of patients in each state
        HashMap < String, Integer > stateCounts = new HashMap < > ();
        for (Patient patient: patientList) {
            String state = patient.getState();
            stateCounts.put(state, stateCounts.getOrDefault(state, 0) + 1);
        }

        // Create the map dataset
        DefaultCategoryDataset mapDataset = new DefaultCategoryDataset();
        for (String state: stateCounts.keySet()) {
            mapDataset.addValue(stateCounts.get(state), "Patients", state);
        }

        // Create the map chart
        JFreeChart mapChart = ChartFactory.createBarChart("Patient Location", "State", "Number of Patients", mapDataset, PlotOrientation.VERTICAL, false, true, false);

        // Set the chart style
        CategoryPlot mapPlot = mapChart.getCategoryPlot();
        mapPlot.setBackgroundPaint(Color.WHITE);
        mapPlot.setRangeGridlinePaint(Color.BLACK);

        // Create the map chart panel
        ChartPanel mapChartPanel = new ChartPanel(mapChart);

        // Add the map chart panel to the dashboard content panel
        //dashboardContentPanel.add(mapChartPanel);

        // Count the number of patients for each gender
        HashMap<String, Integer> genderCounts = new HashMap<>();
        for (Patient patient : patientList) {
            String gender = patient.getSex();
            if (gender.equalsIgnoreCase("Male")) {
                genderCounts.put("Male", genderCounts.getOrDefault("Male", 0) + 1);
            } else if (gender.equalsIgnoreCase("Female")) {
                genderCounts.put("Female", genderCounts.getOrDefault("Female", 0) + 1);
            }else {
                genderCounts.put("Other", genderCounts.getOrDefault("Other", 0) + 1);
            }
        }

        // Create the gender pie chart
        DefaultPieDataset genderDataset = new DefaultPieDataset();
        for (String gender: genderCounts.keySet()) {
            genderDataset.setValue(gender, genderCounts.get(gender));
        }
        JFreeChart genderChart = ChartFactory.createPieChart("Patient Gender Breakdown", genderDataset, true, true, false);

        // Set the chart style
        genderChart.setBackgroundPaint(Color.WHITE);
        PiePlot genderPlot = (PiePlot) genderChart.getPlot();
        genderPlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));
        genderPlot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        genderPlot.setBackgroundPaint(Color.WHITE);
        genderPlot.setOutlineVisible(false);
        genderPlot.setShadowPaint(null);
        genderPlot.setSectionPaint("Male", new Color(86, 137, 186));
        genderPlot.setSectionPaint("Female", new Color(217, 83, 79));

        // Create the gender chart panel
        ChartPanel genderChartPanel = new ChartPanel(genderChart);

        // Add the gender chart panel to the dashboard content panel
        dashboardContentPanel.setBorder(new EmptyBorder(15, 500, 15, 500));
        dashboardContentPanel.add(genderChartPanel);

        dashboardPanel.setBackground(Color.white);
        dashboardPanel.add(dashboardContentPanel);
    }

    public void buildInsertPatient() {
        //insertPatientPanel.setLayout(new BorderLayout());
        JPanel insertPatientContentPanel = new JPanel(new GridLayout(21, 2, 5, 5));
        //insertPatientContentPanel.removeAll();
        //revalidate();
        //repaint();

        JLabel insertPatientLabel = new JLabel("Insert the new patient information.");
        //insertPatientContentPanel.setBackground(new Color(0, 42, 92));
        insertPatientContentPanel.add(insertPatientLabel, BorderLayout.NORTH);
        insertPatientContentPanel.add(new JLabel(""));

        JLabel patientIDLabel = new JLabel("Patient ID: ");
        JTextField patientIDField = new JTextField("");
        insertPatientContentPanel.add(patientIDLabel);
        insertPatientContentPanel.add(patientIDField);

        JLabel firstNameLabel = new JLabel("First Name: ");
        JTextField firstNameField = new JTextField("");
        insertPatientContentPanel.add(firstNameLabel);
        insertPatientContentPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameField = new JTextField("");
        insertPatientContentPanel.add(lastNameLabel);
        insertPatientContentPanel.add(lastNameField);

        JLabel middleNameLabel = new JLabel("Middle Name: ");
        JTextField middleNameField = new JTextField("");
        insertPatientContentPanel.add(middleNameLabel);
        insertPatientContentPanel.add(middleNameField);

        JLabel suffixLabel = new JLabel("Suffix: ");
        JTextField suffixField = new JTextField("");
        insertPatientContentPanel.add(suffixLabel);
        insertPatientContentPanel.add(suffixField);

        JLabel dateOfBirthLabel = new JLabel("Date of Birth: ");
        JTextField dateOfBirthField = new JTextField("");
        insertPatientContentPanel.add(dateOfBirthLabel);
        insertPatientContentPanel.add(dateOfBirthField);

        JLabel address1Label = new JLabel("Address Line 1: ");
        JTextField address1Field = new JTextField("");
        insertPatientContentPanel.add(address1Label);
        insertPatientContentPanel.add(address1Field);

        JLabel address2Label = new JLabel("Address Line 2: ");
        JTextField address2Field = new JTextField("");
        insertPatientContentPanel.add(address2Label);
        insertPatientContentPanel.add(address2Field);

        JLabel cityLabel = new JLabel("City: ");
        JTextField cityField = new JTextField("");
        insertPatientContentPanel.add(cityLabel);
        insertPatientContentPanel.add(cityField);

        JLabel stateLabel = new JLabel("State: ");
        JTextField stateField = new JTextField("");
        insertPatientContentPanel.add(stateLabel);
        insertPatientContentPanel.add(stateField);

        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        JTextField zipCodeField = new JTextField("");
        insertPatientContentPanel.add(zipCodeLabel);
        insertPatientContentPanel.add(zipCodeField);

        JLabel countyLabel = new JLabel("County: ");
        JTextField countyField = new JTextField("");
        insertPatientContentPanel.add(countyLabel);
        insertPatientContentPanel.add(countyField);

        JLabel workPhoneLabel = new JLabel("Work Phone: ");
        JTextField workPhoneField = new JTextField("");
        insertPatientContentPanel.add(workPhoneLabel);
        insertPatientContentPanel.add(workPhoneField);

        JLabel homePhoneLabel = new JLabel("Home Phone: ");
        JTextField homePhoneField = new JTextField("");
        insertPatientContentPanel.add(homePhoneLabel);
        insertPatientContentPanel.add(homePhoneField);

        JLabel cellPhoneLabel = new JLabel("Cell Phone: ");
        JTextField cellPhoneField = new JTextField("");
        insertPatientContentPanel.add(cellPhoneLabel);
        insertPatientContentPanel.add(cellPhoneField);

        JLabel raceLabel = new JLabel("Race: ");
        JTextField raceField = new JTextField("");
        insertPatientContentPanel.add(raceLabel);
        insertPatientContentPanel.add(raceField);

        JLabel ethnicityLabel = new JLabel("Ethnicity: ");
        JTextField ethnicityField = new JTextField("");
        insertPatientContentPanel.add(ethnicityLabel);
        insertPatientContentPanel.add(ethnicityField);

        JLabel sexLabel = new JLabel("Sex: ");
        JTextField sexField = new JTextField("");
        insertPatientContentPanel.add(sexLabel);
        insertPatientContentPanel.add(sexField);

        JButton submitButton = new JButton("Submit");
        insertPatientContentPanel.add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(e -> {
            // Get the values entered by the user
            String patientID = patientIDField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String middleName = middleNameField.getText();
            String suffix = suffixField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            String address1 = address1Field.getText();
            String address2 = address2Field.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zipCode = zipCodeField.getText();
            String county = countyField.getText();
            String workPhone = workPhoneField.getText();
            String homePhone = homePhoneField.getText();
            String cellPhone = cellPhoneField.getText();
            String race = raceField.getText();
            String ethnicity = ethnicityField.getText();
            String sex = sexField.getText();

            // Show a message dialog to confirm the new patient was added successfully
            JOptionPane.showMessageDialog(null, "Patient added successfully.");

            // Create a FileWriter object to append to the CSV file
            FileWriter writer;
            try {
                writer = new FileWriter(homeModel.getFilePath(), true);
                // Write the new patient information to the CSV file
                writer.write("\r\n"+ patientID + "," + firstName + "," + lastName + "," + middleName + "," + suffix + ","
                        + dateOfBirth + "," + address1 + "," + address2 + "," + city + "," + state + "," + zipCode + ","
                        + county + "," + workPhone + "," + homePhone + "," + cellPhone + "," + race + "," + ethnicity + ","
                        + sex);

                // Close the FileWriter object to flush and release resources
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Update local CSV data
            homeModel.readCSVData();
            buildDashboard();

            // Clear the fields for the next entry
            patientIDField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            middleNameField.setText("");
            suffixField.setText("");
            dateOfBirthField.setText("");
            address1Field.setText("");
            address2Field.setText("");
            cityField.setText("");
            stateField.setText("");
            zipCodeField.setText("");
            countyField.setText("");
            workPhoneField.setText("");
            homePhoneField.setText("");
            cellPhoneField.setText("");
            raceField.setText("");
            ethnicityField.setText("");
            sexField.setText("");
        });

        insertPatientPanel.add(insertPatientContentPanel, BorderLayout.CENTER);
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
        searchTableModel.setRowCount(0);

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
                searchTableModel.addRow(makeRow(patient));
            } else if (matchCount == 1 && hasLastNameSearchTerm){
                searchTableModel.addRow(makeRow(patient));
            }else if (matchCount == 1 && hasFirstNameSearchTerm){
                searchTableModel.addRow(makeRow(patient));
            }else if (matchCount == 1 && hasDOBSearchTerm){
                searchTableModel.addRow(makeRow(patient));
            } else if (matchCount == 2 && hasLastNameSearchTerm && hasFirstNameSearchTerm) {
                searchTableModel.addRow(makeRow(patient));
            }else if (matchCount == 3 && hasLastNameSearchTerm && hasFirstNameSearchTerm && hasDOBSearchTerm) {
                searchTableModel.addRow(makeRow(patient));
            }else if (matchCount == 4 && hasIDSearchTerm && hasLastNameSearchTerm && hasFirstNameSearchTerm && hasDOBSearchTerm) {
                searchTableModel.addRow(makeRow(patient));
            }

            hasIDSearchTerm = false;
            hasLastNameSearchTerm = false;
            hasFirstNameSearchTerm = false;
            hasDOBSearchTerm = false;
        }
    }

    public void openPatientProfile(){
        // Get the list of patients
        ArrayList <Patient> patientList = (ArrayList <Patient> ) homeModel.getPatientData();

        Patient selectedPatient = new Patient();
        int column = 0;
        int row = searchTable.getSelectedRow();
        String value = searchTable.getModel().getValueAt(row, column).toString();
        for (Patient patient : patientList){
            if (Objects.equals(patient.getPatientID(), value)){
                selectedPatient = patient;
            }
        }

        JFrame patientFrame = new JFrame();
        JPanel patientPanel = new JPanel(new GridLayout(0, 2));
        JLabel patientIDLabel = new JLabel("Patient ID: ");
        JLabel firstNameLabel = new JLabel("First Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel middleNameLabel = new JLabel("Middle Name: ");
        JLabel suffixLabel = new JLabel("Suffix: ");
        JLabel dateOfBirthLabel = new JLabel("Date of Birth: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel address1Label = new JLabel("Address Line 1: ");
        JLabel address2Label = new JLabel("Address Line 2: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel stateLabel = new JLabel("State: ");
        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        JLabel countyLabel = new JLabel("County: ");
        JLabel workPhoneLabel = new JLabel("Work Phone: ");
        JLabel homePhoneLabel = new JLabel("Home Phone: ");
        JLabel cellPhoneLabel = new JLabel("Cell Phone: ");
        JLabel raceLabel = new JLabel("Race: ");
        JLabel ethnicityLabel = new JLabel("Ethnicity: ");
        JLabel sexLabel = new JLabel("Sex: ");

        JTextField patientIDField = new JTextField(selectedPatient.getPatientID());
        JTextField firstNameField = new JTextField(selectedPatient.getFirstName());
        JTextField lastNameField = new JTextField(selectedPatient.getLastName());
        JTextField middleNameField = new JTextField(selectedPatient.getMiddleName());
        JTextField suffixField = new JTextField(selectedPatient.getSuffix());
        JTextField dateOfBirthField = new JTextField(selectedPatient.getDateOfBirth());
        JTextField ageField = new JTextField(Integer.toString(selectedPatient.getAge()));
        JTextField address1Field = new JTextField(selectedPatient.getAddress1());
        JTextField address2Field = new JTextField(selectedPatient.getAddress2());
        JTextField cityField = new JTextField(selectedPatient.getCity());
        JTextField stateField = new JTextField(selectedPatient.getState());
        JTextField zipCodeField = new JTextField(selectedPatient.getZipCode());
        JTextField countyField = new JTextField(selectedPatient.getCounty());
        JTextField workPhoneField = new JTextField(selectedPatient.getWorkPhone());
        JTextField homePhoneField = new JTextField(selectedPatient.getHomePhone());
        JTextField cellPhoneField = new JTextField(selectedPatient.getCellPhone());
        JTextField raceField = new JTextField(selectedPatient.getRace());
        JTextField ethnicityField = new JTextField(selectedPatient.getEthnicity());
        JTextField sexField = new JTextField(selectedPatient.getSex());

        patientPanel.add(patientIDLabel);
        patientPanel.add(patientIDField);
        patientPanel.add(firstNameLabel);
        patientPanel.add(firstNameField);
        patientPanel.add(lastNameLabel);
        patientPanel.add(lastNameField);
        patientPanel.add(middleNameLabel);
        patientPanel.add(middleNameField);
        patientPanel.add(suffixLabel);
        patientPanel.add(suffixField);
        patientPanel.add(dateOfBirthLabel);
        patientPanel.add(dateOfBirthField);
        patientPanel.add(ageLabel);
        patientPanel.add(ageField);
        patientPanel.add(address1Label);
        patientPanel.add(address1Field);
        patientPanel.add(address2Label);
        patientPanel.add(address2Field);
        patientPanel.add(cityLabel);
        patientPanel.add(cityField);
        patientPanel.add(stateLabel);
        patientPanel.add(stateField);
        patientPanel.add(zipCodeLabel);
        patientPanel.add(zipCodeField);
        patientPanel.add(countyLabel);
        patientPanel.add(countyField);
        patientPanel.add(workPhoneLabel);
        patientPanel.add(workPhoneField);
        patientPanel.add(homePhoneLabel);
        patientPanel.add(homePhoneField);
        patientPanel.add(cellPhoneLabel);
        patientPanel.add(cellPhoneField);
        patientPanel.add(raceLabel);
        patientPanel.add(raceField);
        patientPanel.add(ethnicityLabel);
        patientPanel.add(ethnicityField);
        patientPanel.add(sexLabel);
        patientPanel.add(sexField);


        // Add the patient panel to the patient frame
        patientFrame.getContentPane().add(patientPanel, BorderLayout.CENTER);
        patientFrame.setTitle("Patient #"+selectedPatient.getPatientID()+" Profile");

        // Add the logo image to the top left corner of the frame
        JLabel logoLabel2 = new JLabel(new ImageIcon(logoImage));
        patientFrame.getContentPane().setBackground(Color.white);
        patientFrame.getContentPane().add(logoLabel2, BorderLayout.WEST);

        patientFrame.pack();
        patientFrame.setVisible(true);
    }

}

