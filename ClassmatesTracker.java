import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ClassmatesTracker {
    public static void main(String[] args) throws Exception {
        // Map to store students and the classes they are taking.
        HashMap<String, Set<String>> studentClasses = new HashMap<>();

        // Array of class file names.
        String[] classFiles = {"CS-310.txt", "CS-262.txt", "STAT-344.txt", "ENGH-302.txt"};

        // Read each class file and store the data in the map.
        for (int i = 0; i < classFiles.length; i++) {
            String file = classFiles[i];
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // Read each line in the file, representing a student's name.
            while ((line = reader.readLine()) != null) {
                // Split the line by "; " to get individual student names.
                String[] names = line.split("; ");
                for (int j = 0; j < names.length; j++) {
                    String name = names[j];
                    // Add the class to the set of classes for each student.
                    studentClasses.computeIfAbsent(name, k -> new HashSet<>()).add(file.replace(".txt", ""));
                }
            }
            reader.close();
        }

        // Scanner for user input.
        Scanner scanner = new Scanner(System.in);
        System.out.println("This program was created to foster friendships. It helps you see who you're taking two or more classes with this semester.");
        System.out.print("\nEnter your full name on record: ");
        String studentName = scanner.nextLine();
        scanner.close();

        // Check if the student is in the map.
        if (studentClasses.containsKey(studentName)) {
            Set<String> classes = studentClasses.get(studentName);
            // Map to store common classes and the students taking them.
            HashMap<Set<String>, Set<String>> commonClasses = new HashMap<>();

            // Iterate through the classes the student is taking.
            for (Iterator<String> it = classes.iterator(); it.hasNext(); ) {
                String className = it.next();
                Set<String> classmates = getStudentsForClass(studentClasses, className);
                // Iterate through the classmates in each class.
                for (Iterator<String> it2 = classmates.iterator(); it2.hasNext(); ) {
                    String classmate = it2.next();
                    if (!classmate.equals(studentName)) {
                        // Find common classes between the student and their classmate.
                        Set<String> common = new HashSet<>(studentClasses.get(classmate));
                        common.retainAll(classes);
                        // If they have two or more classes in common, add to the map.
                        if (common.size() >= 2) {
                            commonClasses.computeIfAbsent(common, k -> new HashSet<>()).add(classmate);
                        }
                    }
                }
            }

            // Convert the map to a list of entries and sort based on the size of the key sets (common classes).
            List<Map.Entry<Set<String>, Set<String>>> sortedEntries = commonClasses.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getKey().size(), e1.getKey().size()))
                    .collect(Collectors.toList());

            // Print the sorted common classes and classmates.
            for (int i = 0; i < sortedEntries.size(); i++) {
                Map.Entry<Set<String>, Set<String>> entry = sortedEntries.get(i);
                List<String> classList = new ArrayList<>(entry.getKey());
                List<String> studentList = new ArrayList<>(entry.getValue());
                String classString = String.join(", ", classList.subList(0, classList.size() - 1))
                        + " and " + classList.get(classList.size() - 1);
                String studentString = String.join(", ", studentList.subList(0, studentList.size() - 1))
                        + " and " + studentList.get(studentList.size() - 1);
                System.out.println("\n* You're taking " + classString + " with " + studentString + ".");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    // Helper method to get the students for a given class.
    public static Set<String> getStudentsForClass(HashMap<String, Set<String>> studentClasses, String className) {
        Set<String> students = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : studentClasses.entrySet()) {
            if (entry.getValue().contains(className)) {
                students.add(entry.getKey());
            }
        }
        return students;
    }
}