/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
    private List<Student> studentList = new ArrayList<>(generateDB());
    private Map<String, List<Student>> studentBucket = new HashMap<>();

    public void getMenu() {
        view.printMenu();
    }

    public List<Student> sortStudentBucketFollowStudentID() {
        List<Student> allStudents = new ArrayList<>();

        for (List<Student> list : studentBucket.values()) {
            allStudents.addAll(list);
        }

        // Sắp xếp theo studentID -> rồi theo Semester -> rồi theo Course
        allStudents.sort(Comparator
                .comparing(Student::getStudentID)
                .thenComparing(Student::getSemester)
                .thenComparing(Student::getCourse)
        );

        return allStudents;
    }

    public List<Student> sortStudentBucketFollowStudentName() {
        List<Student> allStudents = new ArrayList<>();

        for (List<Student> list : studentBucket.values()) {
            allStudents.addAll(list);
        }

        // Sắp xếp theo studentName -> rồi theo Semester -> rồi theo Course
        allStudents.sort(Comparator
                .comparing(Student::getStudentName)
                .thenComparing(Student::getSemester)
                .thenComparing(Student::getCourse)
        );

        return allStudents;
    }

    public List<Student> generateDB() {
        List<Student> studentListLocal = List.of(
                new Student("HE 181446", "Nguyen Duc Anh", Semester.SUMMER, Course.CPP),
                new Student("HE 181446", "Nguyen Duc Anh", Semester.FALL, Course.CPP),
                new Student("HE 181447", "Nguyen Linh Nhi", Semester.FALL, Course.CPP),
                new Student("HE 181447", "Nguyen Linh Nhi", Semester.SPRING, Course.PYTHON),
                new Student("HE 181446", "Nguyen Duc Anh", Semester.SPRING, Course.CPP),
                new Student("HE 181447", "Nguyen Linh Nhi", Semester.SPRING, Course.CPP),
                new Student("HE 181447", "Nguyen Linh Nhi", Semester.SPRING, Course.JAVA)
        );

        // Tạo bản sao có thể chỉnh sửa (mutable) bằng ArrayList
        return new ArrayList<>(studentListLocal);
    }

    // ---------------------------------- CREATE STUDENT ------------------------------------- // ?
    public void createStudentTask() {
        view.printTitle("Create Student Task");

        String studentID = getStudentID("Enter student ID: ");
        String studentName = getStudentName("Enter Student Name: ");

        if (!isDuplicatedStudent(studentID, studentName)) {
            System.err.println("Duplicated ID with another Student Name");
            return;
        }

        Semester semester = getSemester();
        Course course = getCourse();

        // Thêm student vào danh sách
        Student newStudent = new Student(studentID, studentName, semester, course);
        studentList.add(newStudent);

        // Cập nhật vào studentBucket
        studentBucket.computeIfAbsent(studentID, k -> new ArrayList<>()).add(newStudent);

        // In ra toàn bộ studentBucket
        for (String id : studentBucket.keySet()) {
            System.out.println("ID: " + id);
            List<Student> students = studentBucket.get(id);
            for (Student st : students) {
                System.out.println("  " + st);
            }
        }
    }

    // --------------------------------------- FIND AND SORT -------------------------------
    public void findAndSortByStudentName() {
        view.printTitle("Find and Sort");
        String studentname = getStudentName("Student Name key: ");

        // Kiểm tra nếu không tìm thấy sinh viên nào chứa tên
        if (!findStudentByNameKey(studentname)) {
            System.err.println(String.format("No key in name [%s] was found!", studentname));
            return;
        }

        List<Student> sorted = sortStudentBucketFollowStudentName();

        String lastID = ""; // Theo dõi ID đã in
        for (Student st : sorted) {
            String currentID = st.getStudentID();

            if (!currentID.equals(lastID)) {
                System.out.println(currentID); // In ID mới nếu khác
                lastID = currentID;
            }

            System.out.println(st);
        }
    }

    // --------------------------------------- UPDATE /DELETE ----------------------
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
        view.printTitle("Delete Student");
        String id = getStudentID("Enter Student ID [Delete]: ");
        int no = 0;

        List<Student> studentListLocal = studentBucket.get(id);

        if (studentListLocal == null || studentListLocal.isEmpty()) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        // In danh sách sinh viên
        view.printView("%-5s%-25s%-15s%-15s", "No", "Name", "Semester", "Course");
        for (Student st : studentListLocal) {
            view.printView("%-5d%-25s%-15s%-15s", ++no, st.getStudentName(), st.getSemester(), st.getCourse());
        }

        // Tạm nhập No muốn xóa (ví dụ xóa student thứ 2)
        int noInput = 2;
        if (noInput < 1 || noInput > studentListLocal.size()) {
            System.out.println("Invalid No!");
            return;
        }

        // Xóa student khỏi list
        Student removed = studentListLocal.remove(noInput - 1);
        System.out.println("Deleted: " + removed);

        // Nếu danh sách rỗng sau khi xóa, xóa luôn bucket
        if (studentListLocal.isEmpty()) {
            studentBucket.remove(id);
        }
        System.out.println("Delete Succeed!");
    }

    private void updateStudent() {
        view.printTitle("Update Student");
        String id = getStudentID("Enter Student ID [Update]: ");
        int no = 0;

        List<Student> studentListLocal = studentBucket.get(id);

        if (studentListLocal == null || studentListLocal.isEmpty()) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        // In danh sách sinh viên
        view.printView("%-5s%-25s%-15s%-15s", "No", "Name", "Semester", "Course");
        for (Student st : studentListLocal) {
            view.printView("%-5d%-25s%-15s%-15s", ++no, st.getStudentName(), st.getSemester(), st.getCourse());
        }

        // Tạm nhập No muốn xóa (ví dụ xóa student thứ 2)
        int noInput = 2;
        if (noInput < 1 || noInput > studentListLocal.size()) {
            System.out.println("Invalid No!");
            return;
        }

        Semester newSemester = Semester.SUMMER;
        Course newCourse = Course.NET;

        // Cập nhật student 
        Student updated = studentListLocal.get(noInput - 1);
        updated.setSemester(newSemester);
        updated.setCourse(newCourse);
        System.out.println("Updated: " + updated);
    }

    public void report() {

        view.printTitle("Report");

        Map<String, Integer> studentCountCourse = new HashMap<>();

        for (Map.Entry<String, List<Student>> entry : studentBucket.entrySet()) {

            List<Student> students = entry.getValue();

            for (Student student : students) {

                String key = student.getStudentID() + "|" + student.getStudentName() + "|" + student.getSemester();

                studentCountCourse.put(key, studentCountCourse.getOrDefault(key, 0) + 1);
            }
        }

        view.printView("%-15s%-25s%-15s%-15s", "ID", "Name", "Semester", "Total");
        for (Map.Entry<String, Integer> entry : studentCountCourse.entrySet()) {
            String[] parts = entry.getKey().split("\\|");

            String id = parts[0];
            String name = parts[1];
            String semester = parts[2];
            int total = entry.getValue();

            view.printView("%-15s%-25s%-15s%-5d", id, name, semester, total);
        }
    }

    public void display() {

        for (String student : studentBucket.keySet()) {
            System.out.println(student);
            List<Student> printL = studentBucket.get(student);
            for (Student st : printL) {
                System.out.println(st.toString());
            }
        }
    }

    public void displaySorted01() {
        view.printTitle("Sorted Student List");

        List<Student> sorted = sortStudentBucketFollowStudentID();

        String lastID = ""; // Theo dõi ID đã in
        for (Student st : sorted) {
            String currentID = st.getStudentID();

            if (!currentID.equals(lastID)) {
                System.out.println(currentID); // In ID mới nếu khác
                lastID = currentID;
            }

            System.out.println(st);
        }
    }

    // ------------------------------------- SUPPORT -------------------------------------    
    public boolean isDBEmpty() {
        return studentBucket.isEmpty();
    }

    private boolean findStudentByNameKey(String nameKey) {
        for (String student : studentBucket.keySet()) {
            String id = student;

            List<Student> students = studentBucket.get(id);
            for (Student studentJ : students) {
                if (studentJ.getStudentName().toLowerCase().contains(nameKey.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicatedStudent(String studentID, String studentName) {
        List<Student> students = studentBucket.get(studentID);

        // Nếu chưa có ID này trong hệ thống => không trùng, cho phép tạo
        if (students == null) {
            return true;
        }

        // Đã có ID => kiểm tra tên
        for (Student student : students) {
            if (!student.getStudentName().equalsIgnoreCase(studentName)) {
                // Trùng ID nhưng tên khác => lỗi
                return false;
            }
        }

        // Tên giống với các bản ghi trước => hợp lệ
        return true;
    }

    private void printSelectionSemesterChoice(int type, String msg) {
        if (type == 0) {
            System.out.println(msg);
        }
        if (type == -1) {
            System.err.println(msg);
        }
        for (Semester semester : Semester.values()) {
            System.out.println(semester.getSelectionSemesterType() + " - " + semester.getSemesterStringValue());
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
            System.out.println(course.getSelectionCourseType() + " - " + course.getSelectionCourseType());
        }
    }

    // ------------------------------------------- INPUT -------------------------------------------
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
        while (Course.fromCouse(courseSelection) == null) {
            printSelectionCourseChoice(-1, "Please input choice in list [Course Menu]:");
            courseSelection = getUserChoiceLimit("Selection Course: ", 1, Course.values().length);
        }
        return Course.fromCouse(courseSelection);
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
