package controller;

import java.util.ArrayList;
import java.util.Date;
import view.TaskProgramView;
import model.Task;
import model.TaskTime;
import model.TaskType;
import utils.InputHandle;

public class TaskController {

    private TaskProgramView view = new TaskProgramView();
    private InputHandle inputHandle = new InputHandle();
    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<TaskTime> taskTimeList = new ArrayList<>();

    // ------------------------- VIEW -------------------------
    public void getMenu() {
        view.printMenu();
    }

    // ------------------------- ADD TASK -------------------------
    public void addTask() {
        view.printTitle("Add Task");
        String name = getName();
        TaskType type = getType();
        Date date = getDate();
        double from = getFrom();
        double to = getTo();

        while (!checkFromAndTo(from, to)) {
            System.err.println("From must be less than To! Please input again:");
            from = getFrom();
            to = getTo();
        }

        String assignee = getAssignee();
        String reviewer = getReviewer();

        if (isDuplicatedTimeToMakeTask(date, from, to, assignee)) {
            System.err.println("Time is already assigned for this assignee. Please try another time.");
            return;
        }

        double time = to - from;
        addController(name, type, date, time, assignee, reviewer, from, to);
    }

    private void addController(String name, TaskType type, Date date, double time, String assignee, String reviewer, double timeFrom, double timeTo) {
        int id = taskList.isEmpty() ? 1 : taskList.get(taskList.size() - 1).getId() + 1;

        try {
            taskList.add(new Task(id, name, type, date, time, assignee, reviewer));
            addDatabaseTime(name, date, timeFrom, timeTo, assignee);
            System.out.println("Add Successful!");
        } catch (Exception e) {
            System.err.println("Add Failed!");
        }
    }

    private void addDatabaseTime(String nameTask, Date date, double timeFrom, double timeTo, String assignee) {
        taskTimeList.add(new TaskTime(date, timeFrom, timeTo, nameTask, assignee));
    }

    // ------------------------- DELETE TASK -------------------------
    public void deleteTask() {
        view.printTitle("Delete Task");
        int id = getID();
        while (findTaskByID(id) == null) {
            System.err.printf("No task with ID [%d] found!\n", id);
            id = getID();
        }

        deleteController(id);
    }

    private void deleteController(int id) {
        Task task = findTaskByID(id);
        boolean getYN = inputHandle.checkYN("Do you want to continue? Yes[Y]/No[N]: ");
        if (getYN) {
            taskList.remove(task);
            System.out.println("Delete Successful!");
        } else {
            System.out.println("Delete Cancelled.");
        }

        // Re-assign IDs to tasks
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setId(i + 1);
        }
    }

    // ------------------------- DISPLAY TASK -------------------------
    public void displayTask() {
        view.printView();
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    // ------------------------- SUPPORT FUNCTION -------------------------
    private boolean isDuplicatedTimeToMakeTask(Date inputDate, double timeFrom, double timeTo, String assignee) {
        for (TaskTime o : taskTimeList) {
            if (o.getDate().equals(inputDate) && o.getAsginee().equalsIgnoreCase(assignee)) {
                if (!checkTimeCanDo(timeFrom, timeTo, o.getTimeFrom(), o.getTimeTo())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Kiểm tra xem khoảng thời gian [from, to] có trùng với khoảng đã có hay không
    private boolean checkTimeCanDo(double newFrom, double newTo, double existingFrom, double existingTo) {
        return newTo < existingFrom || newFrom > existingTo;
    }

    private Task findTaskByID(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private boolean checkFromAndTo(double from, double to) {
        return from < to;
    }

    private void printTypeSelection() {
        System.out.println("Please choose selection number in list:");
        for (TaskType type : TaskType.values()) {
            System.out.println(type.getNumber() + " - " + type.getValue());
        }
    }

    public boolean isDBEmpty() {
        return taskList.isEmpty();
    }

    // ------------------------- INPUT -------------------------
    private String getName() {
        return inputHandle.getString("Requirement Name: ");
    }

    private TaskType getType() {
        printTypeSelection();
        int type = getIntUserInput();
        while (TaskType.fromType(type) == null) {
            System.err.println("Please choose a valid number from list:");
            printTypeSelection();
            type = getIntUserInput();
        }
        return TaskType.fromType(type);
    }

    private double getFrom() {
        return inputHandle.getDouble("From (8.0 -> 17.5): ", 8.0, 17.5);
    }

    private double getTo() {
        return inputHandle.getDouble("To (8.0 -> 17.5): ", 8.0, 17.5);
    }

    private String getAssignee() {
        return inputHandle.getString("Assignee: ");
    }

    private String getReviewer() {
        return inputHandle.getString("Reviewer: ");
    }

    public int getChoice(int min, int max) {
        return inputHandle.getUserLimitChoice("Your choice: ", min, max);
    }

    private int getIntUserInput() {
        return inputHandle.getInt("Task Type: ");
    }

    private int getID() {
        return inputHandle.getInt("ID: ");
    }

    private Date getDate() {
        return inputHandle.getDate("Date: ");
    }
}
