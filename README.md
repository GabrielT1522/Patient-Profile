# Patient Management Application - City of Laredo Health Department
This is a desktop application developed in Java for patient management in the City of Laredo Health Department. The application is designed to manage patient data from a given dataset in .csv format. This documentation explains the features and functions of the application.

# Getting Started
To run the application, follow these steps:

1. Download and unzip/extract this repository on your machine.

2. Open a terminal (Command Prompt on Windows, or Terminal on macOS/Linux).

3. Navigate to the directory where you downloaded the JAR file and the script file.

4. Run the script file by executing the following command:

   ## On Windows:
   
   ```
   RunOnWindows.bat
   ```
   
   ## On Mac:
   
   ```
   ./RunOnMac.sh
   ```
   
 
   This will execute the Java command to run the JAR file.
   

The Java GUI application should now start and you can interact with it.

#### Note: If you encounter any issues or errors, please make sure you have Java installed on your system and that the Java command is in your system's path. Moving any files out of their original folder may cause the program to stop working. To add the file on the desktop, or anywhere on your computer for easier access, you need to make a shortcut of the program. 

# Making a Shortcut
Follow these steps to properly make an executable shortcut:

1. Locate the Patient Profile.jar and RunOnWindows.bat files in the Patient-Profile-master folder.
2. Make sure that the program works on the original "Patient Profile.jar" and "RunOnWindows.bat" files.
3. Right click on "Patient Profile.jar"
4. Select Create Shortcut
5. Find the new shortcut instance. It should be named "Patient Profile - Shortcut" or similar. This file may be placed anywhere on the computer for easier access.

You may change the icon of the shortcut for better recognition:

1. Right click on the newly made shortcut.
2. Click Change Icon
3. Browse to the Patient-Profile-master folder and select the "logoImg.ico" or choose one of the many icons included with Windows.
4. Select Apply
   
# Features
The application has three main tabs:

### View Patient 
- This tab allows the user to search for a patient by patient ID, name, and date of birth. Once a patient is selected, an individual patient profile with their information will be displayed.

### Dashboard 
- This tab displays visual statistical graphs about the patient data set. The graphs give insights into patient demographics and other relevant information. JFreeChart is used to make the visual statistics.

### Insert Patient 
- This tab allows the user to insert new patient information into the original .csv file. The user can enter details such as patient ID, name, date of birth, and other general information.

# How to Use

## Insert a file:

1. Select the "Choose a File" button in the Home tab
2. Select the appropriate .csv file containing the patient data 
3. Click "Open" to import the file into the application.
4. The patient data set will now be ready to use.

#### Note: When adding a CSV file, the file must contain the following headers exactly: 

- Patient_no,
- Name_last
- Name_first,
- Name_mid,
- Name_suffix
- Dob
- Address,
- Address_2,
- City
- State
- Zip_code
- County
- Work_phone
- Home_phone,
- Cell_phone
- Race
- Ethnicity
- Sex

#### Otherwise, the data will not be parsed correctly and will not work.

## View Patient:

1. Click on the "View Patient" tab.
2. Refine the search using the patient's ID, name, or date of birth in the text fields.
3. Click the "Search" button.
4. If the patient exists in the dataset, their information will be displayed on the table.
5. Select the desired entry in the table and click the "Select this Patient" button.
6. An individual patient profile with their information will be displayed.

## View Dashboard:

1. Click on the "Dashboard" tab.
2. The graphs will be displayed automatically.

## Insert Patient:

1. Click on the "Insert Patient" tab.
2. Fill out the patient details in the form provided.
3. Click the "Submit" button.
4. The new patient's information will be appended to the original .csv file.

# Conclusion
This application provides a user-friendly interface for managing patient data in the City of Laredo Health Department. Its three tabs - view patient, dashboard, and insert patient - allow users to easily search for patient information, view statistics about the dataset, and add new patient information. If you have any questions or issues with the application, please feel free to contact the developer.
