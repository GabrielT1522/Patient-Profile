package Home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Patient {
    private String patientID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String suffix;
    private String dateOfBirth;
    private int age;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String county;
    private String workPhone;
    private String homePhone;
    private String cellPhone;
    private String race;
    private String ethnicity;
    private String sex;

    public Patient(){
        this.patientID = "";
        this.firstName = "";
        this.lastName = "";
        this.middleName = "";
        this.suffix = "";
        this.dateOfBirth = "";
        this.age = 0;
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zipCode = "";
        this.county = "";
        this.workPhone = "";
        this.homePhone = "";
        this.cellPhone = "";
        this.race = "";
        this.ethnicity = "";
        this.sex = "";
    }

    public String getPatientID(){
        return this.patientID;
    }

    public void setPatientID(String patientID){
        this.patientID = removeQuotations(patientID);
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = removeQuotations(firstName);
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = removeQuotations(lastName);
    }

    public String getMiddleName(){
        return this.middleName;
    }

    public void setMiddleName(String middleName){
        this.middleName = removeQuotations(middleName);
    }

    public String getSuffix(){
        return this.suffix;
    }

    public void setSuffix(String suffix){
        this.suffix = removeQuotations(suffix);
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = removeQuotations(dateOfBirth);
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(String dateOfBirth){
        this.age = calculateAge(dateOfBirth);
    }

    public String getAddress1(){
        return this.address1;
    }

    public void setAddress1(String address1){
        this.address1 = removeQuotations(address1);
    }

    public String getAddress2(){
        return this.address2;
    }

    public void setAddress2(String address2){
        this.address2 = removeQuotations(address2);
    }

    public String getCity(){
        return this.city;
    }

    public void setCity(String city){
        this.city = removeQuotations(city);
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = removeQuotations(state);
    }

    public String getZipCode(){
        return this.zipCode;
    }

    public void setZipCode(String zipCode){
        this.zipCode = removeQuotations(zipCode);
    }

    public String getCounty(){
        return this.county;
    }

    public void setCounty(String county){
        this.county = removeQuotations(county);
    }

    public String getWorkPhone(){
        return this.workPhone;
    }

    public void setWorkPhone(String workPhone){
        this.workPhone = removeQuotations(workPhone);
    }

    public String getHomePhone(){
        return this.homePhone;
    }

    public void setHomePhone(String homePhone){
        this.homePhone = removeQuotations(homePhone);
    }

    public String getCellPhone(){
        return this.cellPhone;
    }

    public void setCellPhone(String cellPhone){
        this.cellPhone = removeQuotations(cellPhone);
    }

    public String getRace(){
        return this.race;
    }

    public void setRace(String race){
        this.race = removeQuotations(race);
    }

    public String getEthnicity(){
        return this.ethnicity;
    }

    public void setEthnicity(String ethnicity){
        this.ethnicity = removeQuotations(ethnicity);
    }

    public String getSex(){
        return this.sex;
    }

    public void setSex(String sex){
        this.sex = removeQuotations(sex);
    }

    public String removeQuotations(String string){
        return string.replace("\"", "");
    }

    public int calculateAge(String dob) {
        // Calculate age based on date of birth
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar dobCal = Calendar.getInstance();
        Calendar nowCal = Calendar.getInstance();
        try {
            dobCal.setTime(sdf.parse(dob));
        } catch (ParseException ignored) {}

        int dobYear = dobCal.get(Calendar.YEAR);
        int dobMonth = dobCal.get(Calendar.MONTH);
        int dobDay = dobCal.get(Calendar.DAY_OF_MONTH);
        int nowYear = nowCal.get(Calendar.YEAR);
        int nowMonth = nowCal.get(Calendar.MONTH);
        int nowDay = nowCal.get(Calendar.DAY_OF_MONTH);
        int age = nowYear - dobYear;
        if (nowMonth < dobMonth || (nowMonth == dobMonth && nowDay < dobDay)) {
            age--;
        }
        return age;
    }

    public void displayPatient() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Middle Name: " + middleName);
        System.out.println("Suffix: " + suffix);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Age: " + age);
        System.out.println("Address 1: " + address1);
        System.out.println("Address 2: " + address2);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Zip Code: " + zipCode);
        System.out.println("County: " + county);
        System.out.println("Work Phone: " + workPhone);
        System.out.println("Home Phone: " + homePhone);
        System.out.println("Cell Phone: " + cellPhone);
        System.out.println("Race: " + race);
        System.out.println("Ethnicity: " + ethnicity);
        System.out.println("Sex: " + sex);
    }
}

