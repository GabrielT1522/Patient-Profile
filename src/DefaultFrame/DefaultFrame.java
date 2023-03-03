package DefaultFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DefaultFrame extends JFrame
{
    private JFileChooser chooser;
    private File selectedFile;
    private JFrame loadFrame;
    private JEditorPane loadPane;
    private JFrame aboutFrame;
    private JEditorPane aboutPane;
    private JFrame helpFrame;
    private JEditorPane helpPane;
    public DefaultFrame() throws IOException {

        // Create menu bar, menus and menu items
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        JMenu homeMenu = new JMenu("Home");
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");
        JMenu helpMenu = new JMenu("Help");
        menubar.add(homeMenu);
        menubar.add(fileMenu);
        menubar.add(toolsMenu);
        menubar.add(helpMenu);
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        JMenuItem calculatorItem = new JMenuItem("Calculator");
        toolsMenu.add(calculatorItem);
        JMenuItem helpItem = new JMenuItem("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        // Create a listener and add it to the menu items
        MenuListener menuList = new MenuListener(this);
        homeMenu.addActionListener(menuList);
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
    public void openFile()
    {
        // Create loadFrame
        loadFrame = new JFrame();
        loadPane = new JEditorPane();
        JScrollPane loadScrollPane = new JScrollPane(loadPane);
        loadFrame.getContentPane().add(loadScrollPane, BorderLayout.CENTER);
        loadFrame.setTitle("");
        loadFrame.setSize(350,300);
        loadFrame.setLocation(350,300);
        loadFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        if (chooser == null) {
            chooser = new JFileChooser(new File("/Users/gabrieltorres/Desktop/Java/AccountGUI/Account Receipts"));
        }
        // Allow only .txt files
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt Files", "txt");
        chooser.addChoosableFileFilter(filter);
        //chooser.showOpenDialog(null);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            try {
                loadFrame.setVisible(true);
                FileReader reader = new FileReader(selectedFile);
                loadPane.read(reader, null);
                System.out.println(selectedFile.getName() + " successfully opened.");
                loadFrame.setTitle(selectedFile.getName());
                reader.close();
            } catch (IOException e) {
                System.out.println("Problems opening or reading " + selectedFile.getName());
            }
        }//if
        System.out.println("Menu item Load selected.");
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
        aboutFrame = new JFrame();
        aboutPane = new JEditorPane();
        JScrollPane aboutScrollPane = new JScrollPane(aboutPane);
        aboutFrame.getContentPane().add(aboutScrollPane, BorderLayout.CENTER);
        aboutFrame.setTitle("About");
        aboutFrame.setSize(295,200);
        aboutFrame.setLocation(295,200);
        aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        try {
            aboutFrame.setVisible(true);
            FileReader reader = new FileReader("About.txt");
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
