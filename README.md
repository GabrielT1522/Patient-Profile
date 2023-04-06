# Patient Management Application - City of Laredo Health Department
This is a desktop application developed in Java for patient management in the City of Laredo Health Department. The application is designed to manage patient data from a given dataset in .csv format. This documentation explains the features and functions of the application.

# Getting Started
To use this application, please follow the steps below:

- Clone or download the application from the GitHub repository.
- Make sure that you have the latest version of Java installed on your computer.
- Open the project in an IDE of your choice (such as Eclipse, NetBeans, or IntelliJ IDEA).
- Run the application.

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

- Select the "Choose a File" button in the Home tab
- Select the appropriate .csv file containing the patient data 
- Click "Open" to import the file into the application.
- The patient data set will now be ready to use.

#### Please note that when adding a CSV file, the file must contain the following headers exactly: 

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

- Click on the "View Patient" tab.
- Refine the search using the patient's ID, name, or date of birth in the text fields.
- Click the "Search" button.
- If the patient exists in the dataset, their information will be displayed on the table.
- Select the desired entry in the table and click the "Select this Patient" button.
- An individual patient profile with their information will be displayed.

## View Dashboard:

- Click on the "Dashboard" tab.
- The graphs will be displayed automatically.

## Insert Patient:

- Click on the "Insert Patient" tab.
- Fill out the patient details in the form provided.
- Click the "Submit" button.
- The new patient's information will be appended to the original .csv file. 

# Conclusion
This application provides a user-friendly interface for managing patient data in the City of Laredo Health Department. Its three tabs - view patient, dashboard, and insert patient - allow users to easily search for patient information, view statistics about the dataset, and add new patient information. If you have any questions or issues with the application, please feel free to contact the developer.
