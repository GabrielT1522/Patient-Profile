package Dashboard;

import Home.HomeModel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DashboardPanel extends JPanel {

    HomeModel homeModel = new HomeModel();

    public DashboardPanel(){

        JPanel dashboardPanel = new JPanel();
        JPanel dashboardContentPanel = new JPanel();
        dashboardPanel.add(dashboardContentPanel, BorderLayout.NORTH);
        this.add(dashboardPanel);
    }
}
