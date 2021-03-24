
package SystemClasses;

import Hash.Hasher;
import Hash.Txt;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Students {
    private String Name, TP, Phone_number, Email,Course , Username, Password, Rand, DSignature;
    private int TestMark, LabMark, FinalMark;
    
    private static ArrayList<Students>StudentList = new ArrayList<>();

    public static ArrayList<Students> getStudentList() {
        return StudentList;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getTP() {
        return TP;
    }
    public void setTP(String TP) {
        this.TP = TP;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String Phone_number) {
        this.Phone_number = Phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getTestMark() {
        return TestMark;
    }

    public void setTestMark(int TestMark) {
        this.TestMark = TestMark;
    }

    public int getLabMark() {
        return LabMark;
    }

    public void setLabMark(int LabMark) {
        this.LabMark = LabMark;
    }

    public int getFinalMark() {
        return FinalMark;
    }

    public void setFinalMark(int FinalMark) {
        this.FinalMark = FinalMark;
    }

    public String getRand() {
        return Rand;
    }

    public void setRand(String Rand) {
        this.Rand = Rand;
    }
    public String getDSignature() {
        return DSignature;
    }

    public void setDSignature(String DSignature) {
        this.DSignature = DSignature;
    }
    
    public boolean LoginStudents(String user , String pass){
      for (int i = 0; i<Students.StudentList.size(); i = i+1){
         try {
                String hash_user = Hasher.hash( Txt.append(Students.getStudentList().get(i).getRand(), user),"MD5");
                String hash_pass = Hasher.hash( Txt.append(Students.getStudentList().get(i).getRand(), pass),"MD5");
                if (Students.getStudentList().get(i).getUsername().equals(hash_user)
                        && Students.getStudentList().get(i).getPassword().equals(hash_pass)){
                    return true;
                }
            } catch (Exception ex) {}
      }
      return false;
    }

    public void Student_ADD(String Name, String TP, String Phone_number, 
            String Email, String Course, int TestMark, int LabMark, 
            int FinalMark, String Username, String Password, String Rand, String DSignature) throws IOException {
        PrintWriter ADD = new PrintWriter(new FileWriter("Student.txt", true));
        ADD.print(Name + ":");
        ADD.print(TP + ":");
        ADD.print(Phone_number + ":");
        ADD.print(Email + ":");
        ADD.print(Course + ":");
        ADD.print(TestMark+":");
        ADD.print(LabMark+":");
        ADD.print(FinalMark+":");
        ADD.print(Username+":");
        ADD.print(Password+":");
        ADD.print(Rand+":");
        ADD.println(DSignature);
        ADD.flush();
        {
            JOptionPane.showMessageDialog(null, "Student has been addeed successfully", "Successful Message", JOptionPane.INFORMATION_MESSAGE);
            Students.getStudentList().clear();
            ADD.close();
        }   
    }    
}
