package Dashboard;

import Home.HomeModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/*
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
*/


public class DashboardPanel extends JPanel {

    private HomeModel homeModel = new HomeModel();
    private Map<String, Integer> ageCounts;
    private Map<String, Integer> genderCounts;
    private Map<String, Integer> raceCounts;
    private JLabel genderPieChartLabel;
    private JLabel ageBarChartLabel;
    private JTextArea descriptiveStatsTextArea;
    private JPanel mapPanel;

    public DashboardPanel(){

        JPanel dashboardPanel = new JPanel();
        JPanel dashboardContentPanel = new JPanel();
        dashboardPanel.add(dashboardContentPanel, BorderLayout.NORTH);
        ageCounts = new HashMap<>();
        genderCounts = new HashMap<>();
        raceCounts = new HashMap<>();

        this.add(dashboardPanel);
    }
}