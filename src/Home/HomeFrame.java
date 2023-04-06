package Home;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.awt.Toolkit;

public class HomeFrame extends JFrame
{
    public final HomePanel homePanel = new HomePanel();
    public HomeFrame() throws IOException {
        Image icon = Toolkit.getDefaultToolkit().getImage("logoImg.png");
        this.setIconImage(icon);

        // Create menu bar, menus and menu items
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        JMenu fileMenu = new JMenu("File");
        JMenu tabsMenu = new JMenu("Tabs");
        JMenu helpMenu = new JMenu("Help");
        menubar.add(fileMenu);
        menubar.add(tabsMenu);
        menubar.add(helpMenu);

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem viewPatientItem = new JMenuItem("View Patient");
        JMenuItem dashboardItem = new JMenuItem("Dashboard");
        JMenuItem insertPatientItem = new JMenuItem("Insert Patient");
        tabsMenu.add(homeItem);
        tabsMenu.addSeparator();
        tabsMenu.add(viewPatientItem);
        tabsMenu.add(dashboardItem);
        tabsMenu.add(insertPatientItem);


        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        // Create a listener and add it to the menu items
        MenuListener menuList = new MenuListener(this, homePanel);
        tabsMenu.addActionListener(menuList);

        homeItem.addActionListener(menuList);
        viewPatientItem.addActionListener(menuList);
        dashboardItem.addActionListener(menuList);
        insertPatientItem.addActionListener(menuList);

        loadItem.addActionListener(menuList);
        exitItem.addActionListener(menuList);

        helpItem.addActionListener(menuList);
        aboutItem.addActionListener(menuList);

        //this.setResizable(false);
        this.setSize(450,350);
        this.setLocation(450,350);
        this.getContentPane().add(homePanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void openHelp() {
        // Create helpFrame
        JFrame helpFrame = new JFrame();
        JEditorPane helpPane = new JEditorPane();
        JScrollPane helpScrollPane = new JScrollPane(helpPane);

        helpPane.setEditable(false);
        helpPane.setMargin(new Insets(10, 10, 10, 10)); // add padding

        helpFrame.getContentPane().add(helpScrollPane, BorderLayout.CENTER);
        helpFrame.setTitle("About");
        helpFrame.setSize(550, 400);
        helpFrame.setResizable(false); // make the frame not resizable
        helpFrame.setLocationRelativeTo(null); // center the frame
        helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        String path = "README.md";
        try {
            FileReader reader = new FileReader(path);
            helpPane.read(reader, null);
            System.out.println(path + " successfully opened.");
            reader.close();
        } catch (IOException e) {
            System.out.println("Problems opening or reading " + path);
        }

        helpFrame.setVisible(true);
    }

    public void openAbout() {
        // Create aboutFrame
        JFrame aboutFrame = new JFrame();
        JEditorPane aboutPane = new JEditorPane();
        JScrollPane aboutScrollPane = new JScrollPane(aboutPane);
        aboutPane.setEditable(false);
        URL url = getClass().getResource("/About.html");

        try {
            aboutPane.setPage(url);
        } catch (IOException e) {
            aboutPane.setContentType("text/html");
            aboutPane.setText("<html>Page not found.</html>");
        }

        // Handle hyperlink clicks
        aboutPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    URI uri = e.getURL().toURI();
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        aboutFrame.getContentPane().add(aboutScrollPane, BorderLayout.CENTER);
        aboutFrame.setTitle("About");
        aboutFrame.setSize(600, 400);
        aboutFrame.setLocationRelativeTo(null); // Center the frame on screen
        aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        aboutFrame.setResizable(false);
        aboutPane.setEditable(false);
        aboutFrame.setVisible(true);

        System.out.println("About.html successfully opened.");
    }


    // Makes the frame visible.
    public void showIt(){
        this.setVisible(true);
    }
    // Makes the frame visible and sets the title text.
    public void showIt(String title){
        this.setTitle(title);
        this.setVisible(true);
    }
    // Makes the frame visible and sets the title text // and the position of the window.
    public void showIt(String title,int x, int y){
        this.setTitle(title);
        this.setLocation(x,y);
        this.setVisible(true);
    }
    // Makes the frame invisible.
    public void hideIt(){
        this.setVisible(false);
    }
}
