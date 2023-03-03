package Home;

import Dashboard.DashboardModel;
import Dashboard.DashboardPanel;
import Profile.ProfilePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class HomePanel extends JPanel {

    public final HomeModel homeModel = new HomeModel();

    ProfilePanel profilePanel = new ProfilePanel();
    DashboardPanel dashboardPanel = new DashboardPanel();

    JPanel cardPanel = new JPanel(new CardLayout());
    CardLayout cl = (CardLayout)(cardPanel.getLayout());

    public HomePanel() throws IOException {

        JPanel homePanel = new JPanel();
        JPanel homeLogoPanel = new JPanel();
        JPanel homeInfoPanel = new JPanel();
        JPanel homeContentPanel = new JPanel();

        // Build Home Panel
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
        viewPatientButton.addActionListener(e -> cl.show(cardPanel, "Profile"));

        viewDashboardButton.setPreferredSize(new Dimension(350, 50));
        viewDashboardButton.setFont(headingFont);
        viewDashboardButton.addActionListener(e -> showDashboard());

        insertPatientButton.setPreferredSize(new Dimension(350, 50));
        insertPatientButton.setFont(headingFont);
        insertPatientButton.addActionListener(e -> cl.show(cardPanel, "Insert"));

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
        cl.show(cardPanel, "Dashboard");
        // [Patient_no, Name_last, Name_first, Name_mid, Name_suffix, Dob, Address, Address_2, City, State, Zip_code, County, Work_phone, Home_phone, Cell_phone, Race, Ethnicity, Sex]
    }

    public void goHome() {
        cl.show(cardPanel, "Home");
        System.out.println("It Works");
    }

}

