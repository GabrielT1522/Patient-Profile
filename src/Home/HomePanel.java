package Home;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.DriverAction;

public class HomePanel extends JPanel {

    public HomePanel() throws IOException {

        JPanel mainPanel = new JPanel();
        JPanel logoPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel contentPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome! What would you like to do?");
        JTextField searchField = new JTextField();
        BufferedImage logoImage = ImageIO.read(new File("/Users/gabrieltorres/Desktop/Laredo Health/Patient Profile/src/Home/logoImg.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        JButton viewPatientButton = new JButton("View a Patient Profile");
        JButton viewDashboardButton = new JButton("View Patient Dashboard");
        JButton insertPatientButton = new JButton("Insert a New Patient Profile");
        Font headingFont = new Font("Times New Roman", Font.BOLD, 20);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        logoPanel.setBackground(Color.white);
        logoPanel.add(logoLabel);

        //infoPanel.setBackground(Color.white);
        welcomeLabel.setFont(headingFont);
        searchField.setColumns(20);
        infoPanel.add(welcomeLabel);
        //infoPanel.add(searchField);

        //contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(this.getMaximumSize());
        contentPanel.setBackground(new Color(0, 42, 92));


        //welcomeLabel.setPreferredSize(new Dimension(350, 50));
        viewPatientButton.setPreferredSize(new Dimension(350, 50));
        viewPatientButton.setFont(headingFont);
        viewDashboardButton.setPreferredSize(new Dimension(350, 50));
        viewDashboardButton.setFont(headingFont);
        insertPatientButton.setPreferredSize(new Dimension(350, 50));
        insertPatientButton.setFont(headingFont);

        //contentPanel.add(welcomeLabel, BorderLayout.NORTH);
        contentPanel.add(viewPatientButton);
        contentPanel.add(viewDashboardButton);
        contentPanel.add(insertPatientButton);

        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.SOUTH);


        this.setBackground(Color.white);
        this.add(mainPanel);
    }



}
