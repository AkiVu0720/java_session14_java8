package ra.impl;

import ra.entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;

public class Studentlmp {

        static  ArrayList<Student> studentArrayList = new ArrayList<>();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            readData();
            runMain(scanner);
        }
        public static void menu(){
            System.out.println("  *****************************MENU************************");
            System.out.println("1. Nhập thông tin các sinh viên");
            System.out.println("2. Tính tuổi các sinh viên");
            System.out.println("3. Tính điểm trung bình và xếp loại sinh viên");
            System.out.println("4. Sắp xếp sinh viên theo tuổi tăng dần");
            System.out.println("5. Thống kê sinh viên theo xếp loại sinh viên");
            System.out.println("6. Cập nhật thông tin sinh viên theo mã sinh viên");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thoát");
        }
        public static void  runMain(Scanner scanner){
            boolean isExit = true;
            do {
                try {
                    menu();
                    byte choice = Byte.parseByte(scanner.nextLine());
                    switch (choice){
                        case 1:
                            Student student = new Student();
                            student.inputData(scanner, studentArrayList);
                            studentArrayList.add(student);
                            break;
                        case 2:
                            for ( Student student1 :studentArrayList) {
                                student1.calAge();
                            }
                            System.out.println("Da tinh tuoi cac sinh vien");
                            break;
                        case 3:
                            for (Student student2:studentArrayList ) {
                                student2.calAvgMark_Rank();
                            }
                            break;
                        case 4:
                            studentArrayList.stream().sorted(Comparator.comparing(Student::getAge)).forEach(System.out::println);
                            System.out.println("Da sap xep xong");
                            break;
                        case 5:
                            studentByArrange();
                            break;
                        case 6:
                            updateStudent(scanner);
                            break;
                        case 7:
                            searchName(scanner);
                            break;
                        case 8:
                            writeData(studentArrayList);
                            isExit = false;
                            break;
                        default:
                            System.out.println("Yeu cau khong ton tai. Vui long chon lai");
                            break;

                    }
                }catch (NumberFormatException numberFormatException){
                    System.out.println("Loi khong dung dinh dang Number (Menu)");
                }
                catch (Exception e){
                    System.out.println("Loi trong qua trinh chon chuc nang Menu");
                }
            }while (isExit);
        }

        public static void studentByArrange(){
            int xuatXuac = 0, gioi = 0, kha = 0, trungBinh = 0, yeu= 0;
            for ( Student st: studentArrayList) {
                switch (st.getRank()){
                    case "Xuất Sắc":
                        xuatXuac+=1;
                        break;
                    case "Giỏi":
                        gioi+=1;
                        break;
                    case "Khá":
                        kha+=1;
                        break;
                    case "Trung bình":
                        trungBinh+=1;
                        break;
                    case "Yếu":
                        yeu+=1;
                }
            }
            System.out.println("Thong ke theo Xep loai: ");
            System.out.printf("Xuất Sắc: %3d - Giỏi: %3d - Khá:%3d - Trung bình: %3d - Yếu: %3d \n", xuatXuac,gioi,kha,trungBinh,yeu);
        }
        public static void writeData(ArrayList<Student> StudenArrayList) {
            //1 Khoi tao doi tuong file
            File file = new File("Student.txt");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(StudenArrayList);
                oos.flush();
            } catch (FileNotFoundException ex1) {
                System.err.println("Loi ghi du lieu vao  file");
            } catch (IOException ex2) {
                System.err.println("Loi khong ton tai file");
            } catch (Exception e) {
                System.err.println("Loi xay ra trong qua trinh ghi du lieu");
            } finally {
                try {
                    if (oos != null) {
                        oos.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex1) {
                    System.err.println("Loi dong cac stream");
                } catch (Exception e) {
                    System.err.println("Loi say ra trong qua trinh dong Stream");
                }
            }
        }

        public static ArrayList<Student> readData() {
            File file = new File("Student.txt");
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            ArrayList<Student> StudentArrayList1 = null;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                StudentArrayList1 = (ArrayList<Student>) ois.readObject();
            } catch (FileNotFoundException ex1) {
                System.out.println("Loi khong ton tai file khi doc");
            } catch (IOException ex2) {
                System.out.println("Loi khi doc file");
            } catch (Exception ex3) {
                System.out.println("Loi trong qua tring doc file");
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex1) {
                    System.out.println("Loi xay ra khi dong strem");
                } catch (Exception ex) {
                    System.out.println("Loi xay ra trong qua trinh dong cac Stream");
                }
                return StudentArrayList1;
            }
        }
        public static void updateStudent(Scanner scanner){
            System.out.println("Nhap ma sinh vien can cap nhap");
            String studentId = scanner.nextLine();
//            boolean isStudent = studentArrayList.stream().anyMatch(student -> student.getStudentId().equalsIgnoreCase(studentId));
            for (Student student :studentArrayList
                 ) {
                if (student.getStudentId().equalsIgnoreCase(studentId)){
                    student.inputData(scanner,studentArrayList);
                }
            }
        }

        public static void searchName(Scanner scanner){
            boolean isStudent = true;
            System.out.println("Nhap ten Sinh vien tim kiem: ");
            String studentName = scanner.nextLine();
            boolean isStudnet = studentArrayList.stream()
                    .anyMatch(student -> student.getStudentName().equalsIgnoreCase(studentName));
            if (isStudnet){
                studentArrayList.stream().filter(student -> student.getStudentName().equalsIgnoreCase(studentName)).forEach(System.out::println);
            }else {
                System.out.println("Khong tim thay Sinh vien");
            }
        }
}
