package DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

public class DefaultFrame extends JFrame
{
    private JFrame helpFrame;
    private JEditorPane helpPane;
    public DefaultFrame() throws IOException {

        // Create menu bar, menus and menu items
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        JMenu fileMenu = new JMenu("File");
        JMenu tabsMenu = new JMenu("Tabs");
        //JMenu toolsMenu = new JMenu("Tools");
        JMenu helpMenu = new JMenu("Help");
        menubar.add(fileMenu);
        menubar.add(tabsMenu);
        //menubar.add(toolsMenu);
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
        JMenuItem calculatorItem = new JMenuItem("Calculator");
        //toolsMenu.add(calculatorItem);
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        // Create a listener and add it to the menu items
        MenuListener menuList = new MenuListener(this);
        tabsMenu.addActionListener(menuList);
        homeItem.addActionListener(menuList);
        viewPatientItem.addActionListener(menuList);
        dashboardItem.addActionListener(menuList);
        insertPatientItem.addActionListener(menuList);

        loadItem.addActionListener(menuList);
        exitItem.addActionListener(menuList);
        calculatorItem.addActionListener(menuList);
        helpItem.addActionListener(menuList);
        aboutItem.addActionListener(menuList);

        //this.setResizable(false);
        this.setSize(450,350);
        this.setLocation(450,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void openHelp(){
        // Create helpFrame
        helpFrame = new JFrame();
        helpPane = new JEditorPane();
        JScrollPane helpScrollPane = new JScrollPane(helpPane);
        helpFrame.getContentPane().add(helpScrollPane, BorderLayout.CENTER);
        helpFrame.setTitle("About");
        helpFrame.setSize(450,300);
        helpFrame.setLocation(450,300);
        helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        try {
            helpFrame.setVisible(true);
            FileReader reader = new FileReader("Help.txt");
            helpPane.read(reader, null);
            System.out.println("Help.txt successfully opened.");
            helpFrame.setTitle("Help");
            reader.close();
        } catch (IOException e) {
            System.out.println("Problems opening or reading Help.txt");
        }
        System.out.println("Menu item Help selected.");
    }
    public void openAbout()
    {
        // Create aboutFrame
        JFrame aboutFrame = new JFrame();
        JEditorPane aboutPane = new JEditorPane();
        JScrollPane aboutScrollPane = new JScrollPane(aboutPane);
        aboutFrame.getContentPane().add(aboutScrollPane, BorderLayout.CENTER);
        aboutFrame.setTitle("About");
        aboutFrame.setSize(700,300);
        aboutFrame.setLocation(295,200);
        aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        aboutFrame.setResizable(false);
        aboutPane.setEditable(false);
        try {
            aboutFrame.setVisible(true);
            FileReader reader = new FileReader("src/About.txt");
            aboutPane.read(reader, null);
            System.out.println("About.txt successfully opened.");
            aboutFrame.setTitle("About");
            reader.close();
        } catch (IOException e) {
            System.out.println("Problems opening or reading About.txt");
        }
        System.out.println("Menu item About selected.");
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
