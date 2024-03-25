import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a task in a todo list with a description and a completion status.
 */
class Task {
    String description;
    boolean completed;

    /**
     * Constructs a new Task with the given description.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        completed = true;
    }

    /**
     * Edits the task's description.
     * @param newDescription the new description of the task
     */
    public void editTask(String newDescription) {
        description = newDescription;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + description;
    }
}

/**
 * A simple todo list application that allows users to add, edit, delete, and mark tasks as completed.
 */
public class TodoList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main method that provides the menu-driven interface for the todo list application.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        int choice;
        System.out.println("\nThank you for using Ayo's to-do List Application. The following actions can be performed:\n");
        do {
            System.out.println("1. Add task");
            System.out.println("2. Edit task");
            System.out.println("3. Delete task");
            System.out.println("4. Mark task as completed");
            System.out.println("5. View tasks");
            System.out.println("0. Exit");
            System.out.print("\nEnter one of the numbers above to perform an action: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    markTaskCompleted();
                    break;
                case 5:
                    viewTasks();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    /**
     * Adds a new task to the todo list.
     */
    private static void addTask() {
        System.out.print("\nEnter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("\nTask added.");
    }

    /**
     * Edits the description of an existing task in the todo list.
     */
    private static void editTask() {
        viewTasks();
        System.out.print("\nEnter task number to edit: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            System.out.print("\nEnter new description: ");
            String newDescription = scanner.nextLine();
            tasks.get(taskNumber - 1).editTask(newDescription);
            System.out.println("\nTask updated.");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

    /**
     * Deletes an existing task from the todo list.
     */
    private static void deleteTask() {
        viewTasks();
        System.out.print("\nEnter task number to delete: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("\nTask deleted.");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

    /**
     * Marks an existing task in the todo list as completed.
     */
    private static void markTaskCompleted() {
        viewTasks();
        System.out.print("\nEnter task number to mark as completed: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markCompleted();
            System.out.println("\nTask marked as completed.");
        } else {
            System.out.println("\nInvalid task number.");
        }
    }

    /**
     * Displays the tasks in the todo list.
     */
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks in the list.");
        } else {
            System.out.println("\nTasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}