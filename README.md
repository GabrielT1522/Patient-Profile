# Patient Management Application - City of Laredo Health Department
This is a desktop application developed in Java for patient management in the City of Laredo Health Department. The application is designed to manage patient data from a given dataset in .csv format. This documentation explains the features and functions of the application.

# Getting Started
To run the application, follow these steps:

1. Download and unzip this repository on your machine.

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

5. The Java GUI application should now start and you can interact with it.

#### Note: If you encounter any issues or errors, please make sure you have Java installed on your system and that the Java command is in your system's PATH.
   
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
