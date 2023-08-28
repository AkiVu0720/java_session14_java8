package ra.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Student implements IEntity<Student>, Serializable {
    //1 Attribute
    private String studentId;
    private String studentName;
    private Date birthday;
    private int age;
    private boolean sex;
    private float mark_html;
    private float mark_css;
    private float mark_javascrip;
    private float avgMark;
    private String rank;

//2 get,set


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getMark_html() {
        return mark_html;
    }

    public void setMark_html(float mark_html) {
        this.mark_html = mark_html;
    }

    public float getMark_css() {
        return mark_css;
    }

    public void setMark_css(float mark_css) {
        this.mark_css = mark_css;
    }

    public float getMark_javascrip() {
        return mark_javascrip;
    }

    public void setMark_javascrip(float mark_javascrip) {
        this.mark_javascrip = mark_javascrip;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public float getAvgMark() {
        return avgMark;
    }
    //3. Constructor


    public Student() {
    }

    public Student(String studentId, String studentName, Date birthday, int age, boolean sex, float mark_html, float mark_css, float mark_javascrip) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.mark_html = mark_html;
        this.mark_css = mark_css;
        this.mark_javascrip = mark_javascrip;
    }
    //4 input, output

    @Override
    public void inputData(Scanner scanner, List<Student> arrayList) {
        boolean isExit = true;
        do {
            try {
                boolean isFit = true;
                System.out.println("Nhap Ma Sinh Vien(VD: S001)");
                String studentId = scanner.nextLine();
                if (studentId.length()==4){
                    if (studentId.startsWith("S")){
                        for (Student student : arrayList) {
                            if (!student.studentId.equalsIgnoreCase(studentId)){
                                isFit = false;
                            }
                        }
                    }
                }
                if (!isFit){
                    this.studentId = studentId;
                    isExit=false;
                }else {
                    System.out.println("Ma da ton tai. Hoac xay ra loi. Vui long nhap lai.");
                }
            }catch (NullPointerException nullPointerException){
                System.out.println("Loi null o list Sinh Vien trong qua trinh nhap Ma");

            }
            catch (Exception e){
                System.out.println(" Loi trong qua trinh nhap Ma Sinh Vien");
            }
        }while (isExit);

        isExit = true;
        do {
            System.out.println("Nhap ten Sinh Vien:");
            String regex = "^{10,50}$";
            String studentName = scanner.nextLine();
            boolean isName = studentName.matches(regex);
            if (isName){
                this.studentName = studentName;
                isExit = false;
            }else {
                System.out.println("Ten bat buoc co tu 10-50 ki tu.");
            }
        }while (isExit);
        isExit = true;
        do {
            System.out.println("Gioi tinh: true(Nam)- false(Nu)");
            String sex = scanner.nextLine();
            if (sex.equalsIgnoreCase("true")||sex.equalsIgnoreCase("false")){
                this.sex = Boolean.parseBoolean(sex);
                isExit = false;
            }else {
                System.out.println("Khong dung dinh dang. Vui long nhap lai.");
            }
        }while (isExit);

        do {
            try {
                System.out.println("NHap diem Html: (0-10)");
                this.mark_html =Float.parseFloat(scanner.nextLine());
            }catch (NumberFormatException numberFormatException){
                System.out.println("Loi khong dung dinh dang Number-Html");
            }
            catch (Exception e){
                System.out.println("Loi trong qua trinh nhap diem Html");
            }

        }while (this.mark_html<0 || this.mark_html>10);

        do {
            try {
                System.out.println("NHap diem Html: (0-10)");
                this.mark_css =Float.parseFloat(scanner.nextLine());
            }catch (NumberFormatException numberFormatException){
                System.out.println("Loi khong dung dinh dang Number - Css");
            }
            catch (Exception e){
                System.out.println("Loi trong qua trinh nhap diem Css");
            }

        }while (this.mark_css<0 || this.mark_css>10);

        do {
            try {
                System.out.println("NHap diem Html: (0-10)");
                this.mark_javascrip =Float.parseFloat(scanner.nextLine());
            }catch (NumberFormatException numberFormatException){
                System.out.println("Loi khong dung dinh dang Number - javascrip");
            }
            catch (Exception e){
                System.out.println("Loi trong qua trinh nhap diem javascrip");
            }

        }while (this.mark_javascrip<0 || this.mark_javascrip>10);
    }

    @Override
    public void displayData() {
        System.out.printf("Ma Sv %3s - TenSV %3s -  Tuoi %3d - DiemTb %3.1f - Xep Loai %3s",
                this.studentId, this.studentName, this.age, this.avgMark, this.rank);
    }

    @Override
    public void calAge() {

    }

    @Override
    public void calAvgMark_Rank() {
        this.avgMark = ( this.mark_html+this.mark_css+ this.mark_javascrip)/3;
        if (this.avgMark >= 9) {
            this.rank = "Xuất Sắc";
        } else if (this.avgMark >= 8) {
            this.rank = "Giỏi";
        } else if (this.avgMark >= 7) {
            this.rank = "Khá";
        } else if (this.avgMark >= 5) {
            this.rank = "Trung bình";
        } else {
            this.rank = "Yếu";
        }
    }
}