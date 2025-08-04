/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.Course;
import model.Semester;
import model.Student;
import utils.InputHandle;
import view.ProgramView;

/**
 *
 * @author NguyenDucAnh
 */
public class StudentController {

    private ProgramView view = new ProgramView();
    private InputHandle inputHandle = new InputHandle();
    private ArrayList<Student> studentList = new ArrayList<>();

    private final int sizeStudentListMax = 10;

    public void generateDB() {
        studentList.add(new Student(1, "S 1", "Duc Anh", Semester.SUMMER, Course.JAVA));
        studentList.add(new Student(2, "S 1", "Duc Anh", Semester.SUMMER, Course.JAVA));
        studentList.add(new Student(3, "S 3", "Lan Huong", Semester.SPRING, Course.NET));
        studentList.add(new Student(4, "S 4", "Quoc Bao", Semester.SUMMER, Course.JAVA));
        studentList.add(new Student(5, "S 5", "Anh Thu", Semester.FALL, Course.CPP));
        studentList.add(new Student(6, "S 6", "Binh Minh", Semester.SPRING, Course.NET));
        studentList.add(new Student(7, "S 7", "Kim Ngan", Semester.SUMMER, Course.JAVA));
        studentList.add(new Student(8, "S 1", "Duc Anh", Semester.SUMMER, Course.JAVA));
        studentList.add(new Student(9, "S 9", "Thao Nguyen", Semester.SPRING, Course.NET));
        studentList.add(new Student(10, "S 10", "Phuc Hau", Semester.SUMMER, Course.JAVA));

    }

    // ------------------------------------- VIEW -------------------------------------
    public void getMenu() {
        view.printMenu();
    }

    // ------------------------------------- CREATE -------------------------------------
    public void createTask() {
        view.printTitle("Create Student");
        boolean getYN;
        if (studentList.size() >= sizeStudentListMax) {
            getYN = getYNInput("Do you want continue? Yes[Y]/No[N]: ");
            if (!getYN) {
                System.out.println("Create student cancel..");
                return;
            }
        }

        String studentID = getStudentID("Student ID: ");
        String studentName = getStudentName("Student Name: ");
        if (isDuplicatedStudent(studentID, studentName)) {
            System.err.println("Your input value is duplicated!");
            return;
        }
        Semester newSemester = getSemester();
        Course newCourse = getCourse();

        createController(studentID, studentName, newSemester, newCourse);
    }

    private void createController(String studentID, String studentName,
            Semester semesterValue, Course courseValue) {
        int id = 1;
        if (!studentList.isEmpty()) {
            id = studentList.get(studentList.size() - 1).getIdNumber() + 1;
        }
        try {
            Student student = new Student(id++, studentID, studentName, semesterValue, courseValue);
            studentList.add(student);
            System.out.println("Add student succeed!");
        } catch (Exception e) {
            System.err.println("Add student fail!");
        }
    }

    // ------------------------------------- FIND AND SORT -------------------------------------
    private ArrayList<Student> sortStudentByName() {
        studentList.sort(Comparator.comparing(Student::getStudentName));
        return studentList;
    }

    public void findStudentByNameTask() {
        view.printTitle("Find and Sort");
        String studentname = getStudentName("Student Name key: ");

        // Kiểm tra nếu không tìm thấy sinh viên nào chứa tên
        if (!findStudentByNameKey(studentname)) {
            System.err.println(String.format("No key in name [%s] was found!", studentname));
            return;
        }

        // Sắp xếp danh sách theo tên
        sortStudentByName();

        // In tiêu đề bảng
        view.printView("%-15s%-15s%-15s%-15s", "Student ID",
                "Student Name", "Semester", "Course Name");

        // In các sinh viên khớp key
        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(studentname.toLowerCase())) {
                view.printView("%-15s%-15s%-15s%-15s",
                        student.getStudentID(),
                        student.getStudentName(),
                        student.getSemester(),
                        student.getCourseName());
            }
        }
    }

    // ------------------------------------- UPDATE / DELETE -------------------------------------
    public void updateORdeleteTask() {
        view.printTitle("Update / Delete");
        boolean getYN;
        boolean getUD = getUDInput("Do you want Update[U]/Delete[D]: ");
        if (getUD) {
            // update student
            getYN = getYNInput("Do you want continue? Yes[Y]/No[N]: ");
            if (getYN) {
                updateStudent();
            } else {
                System.out.println("Update Cancel...");
                return;
            }
        }
        if (!getUD) {
            // delete student
            getYN = getYNInput("Do you want continue? Yes[Y]/No[N]: ");
            if (getYN) {
                deleteStudent();
            } else {
                System.out.println("Delete Cancel...");
                return;
            }
        }

    }

    private void deleteStudent() {
        String studentID = getStudentID("Enter to delete [Student ID]: ");
        if (findStudentByStudentID(studentID) == null) {
            System.err.printf("No student ID [%s] was found!\n", studentID);
            return;
        }

        // Lọc danh sách sinh viên có ID phù hợp
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getStudentID().equals(studentID)) {
                matchedStudents.add(student);
            }
        }

        if (matchedStudents.isEmpty()) {
            System.err.printf("No student records found for ID [%s]!\n", studentID);
            return;
        }

        // Hiển thị danh sách lựa chọn
        view.printView("%-5s%-15s%-20s%-15s%-15s%-10s",
                "No.", "Student ID", "Student Name", "Semester", "Course Name", "ID Number");
        int index = 1;
        for (Student student : matchedStudents) {
            view.printView("%-5d%-15s%-20s%-15s%-15s%-10d",
                    index,
                    student.getStudentID(),
                    student.getStudentName(),
                    String.valueOf(student.getSemester()),
                    String.valueOf(student.getCourseName()),
                    student.getIdNumber()
            );
            index++;
        }

        // Nhập số thứ tự bản ghi muốn xóa
        int no = getUserChoiceLimit("Enter No to delete: ", 1, matchedStudents.size());
        Student studentToDelete = matchedStudents.get(no - 1);

        // Xóa khỏi danh sách gốc
        studentList.remove(studentToDelete);

        // Reset lại idNumber cho toàn bộ danh sách
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setIdNumber(i + 1);
        }

        System.out.println("Delete student succeed!");
    }

    private void updateStudent() {
        String studentID = getStudentID("Enter to update [Student ID]: ");
        if (findStudentByStudentID(studentID) == null) {
            System.err.printf("No student ID [%s] was found!\n", studentID);
            return;
        }

        // Tạo danh sách các bản ghi của studentID đó
        ArrayList<Student> updateStudent = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getStudentID().equals(studentID)) {
                updateStudent.add(student);
            }
        }

        if (updateStudent.isEmpty()) {
            System.err.printf("No student records found for ID [%s]!\n", studentID);
            return;
        }

        // In danh sách sinh viên có cùng studentID
        view.printView("%-5s%-15s%-20s%-15s%-15s%-10s",
                "No.", "Student ID", "Student Name", "Semester", "Course Name", "ID Number");
        int idx = 1;
        for (Student student : updateStudent) {
            view.printView("%-5d%-15s%-20s%-15s%-15s%-10d",
                    idx,
                    student.getStudentID(),
                    student.getStudentName(),
                    String.valueOf(student.getSemester()),
                    String.valueOf(student.getCourseName()),
                    student.getIdNumber()
            );
            idx++;
        }

        // Lấy lựa chọn No. từ người dùng
        int noNumber = getUserChoiceLimit("Enter No: ", 1, updateStudent.size());
        Student studentToUpdate = updateStudent.get(noNumber - 1);

        // Nhập thông tin mới
        Semester newSemesterValue = getSemester();
        Course newCourseValue = getCourse();

        // Cập nhật thông tin
        studentToUpdate.setSemester(newSemesterValue);
        studentToUpdate.setCourseName(newCourseValue);

        System.out.println("Update student succeed!");
    }

    // ------------------------------------- REPORT -------------------------------------
    public void reportTask() {
        view.printTitle("Report");
        Map<String, Integer> studentCourseCount = new HashMap<>();

        for (Student student : studentList) {
            // Tạo key duy nhất dựa trên studentID, Name, Semester
            String key = student.getStudentID() + "|" + student.getStudentName() + "|" + student.getSemester();

            // Tăng số lượng course mỗi khi gặp bản ghi sinh viên đó
            studentCourseCount.put(key, studentCourseCount.getOrDefault(key, 0) + 1);
        }

        // In báo cáo
        System.out.printf("%-15s%-20s%-15s%-15s\n", "Student ID", "Student Name", "Semester", "Total Courses");
        for (Map.Entry<String, Integer> entry : studentCourseCount.entrySet()) {
            String[] parts = entry.getKey().split("\\|");
            String studentID = parts[0];
            String studentName = parts[1];
            String semester = parts[2];
            int totalCourses = entry.getValue();

            System.out.printf("%-15s%-20s%-15s%-15d\n", studentID, studentName, semester, totalCourses);
        }
    }

    // ------------------------------------- SUPPORT -------------------------------------    
    public boolean isDBEmpty() {
        return studentList.isEmpty();
    }

    private Student findStudentByStudentID(String studentID) {
        for (Student student : studentList) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private boolean findStudentByNameKey(String nameKey) {
        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(nameKey.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicatedStudent(String studentID, String studentName) {
        for (Student student : studentList) {
            if (student.getStudentID().equals(studentID)
                    && !student.getStudentName().equalsIgnoreCase(studentName)) {
                return true; // trung lap
            }
        }
        return false;
    }

    private void printSelectionSemesterChoice(int type, String msg) {
        if (type == 0) {
            System.out.println(msg);
        }
        if (type == -1) {
            System.err.println(msg);
        }
        for (Semester semester : Semester.values()) {
            System.out.println(semester.getNumberSemesterSelection() + " - " + semester.getSemesterValueString());
        }
    }

    private void printSelectionCourseChoice(int type, String msg) {
        if (type == 0) {
            System.out.println(msg);
        }
        if (type == -1) {
            System.err.println(msg);
        }
        for (Course course : Course.values()) {
            System.out.println(course.getNumberCourseSelection() + " - " + course.getCourseValueString());
        }
    }

    // ------------------------------------- INPUT -------------------------------------
    private String getStudentID(String msg) {
        return inputHandle.StudentIDValidate(msg);
    }

    private String getStudentName(String msg) {
        return inputHandle.getNameValue(msg);
    }

    private Semester getSemester() {
        printSelectionSemesterChoice(0, "Please input choice in list [Semester Menu]:");
        int semesterSelection = getUserChoiceLimit("Selection Semester: ", 1, Semester.values().length);
        while (Semester.fromSemester(semesterSelection) == null) {
            printSelectionSemesterChoice(-1, "Please again input choice in list [Semester Menu]:");
            semesterSelection = getUserChoiceLimit("Selection Semester: ", 1, Semester.values().length);
        }
        return Semester.fromSemester(semesterSelection);
    }

    private Course getCourse() {
        printSelectionCourseChoice(0, "Please input choice in list [Course Menu]:");
        int courseSelection = getUserChoiceLimit("Selection Course: ", 1, Course.values().length);
        while (Course.fromCourse(courseSelection) == null) {
            printSelectionCourseChoice(-1, "Please input choice in list [Course Menu]:");
            courseSelection = getUserChoiceLimit("Selection Course: ", 1, Course.values().length);
        }
        return Course.fromCourse(courseSelection);
    }

    private boolean getYNInput(String msg) {
        return inputHandle.checkYN(msg);
    }

    private boolean getUDInput(String msg) {
        return inputHandle.checkUD(msg);
    }

    public int getUserChoiceLimit(String msg, int min, int max) {
        return inputHandle.getUserLimitChoice(msg, min, max);
    }
}
