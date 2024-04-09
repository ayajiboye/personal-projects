/*
Ayooluwatomiwa Ajiboye, G01397258
CS 262, Lab section 208
Lab 9
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure for the date of birth.
typedef struct {
    int day;
    int month;
    int year;
} DOB;

// Structure for an employee.
typedef struct {
    int id;
    char firstName[50];
    char lastName[50];
    DOB dob;
    double salary;
} Employee;

// Function prototypes.
void insertEmployee(Employee **employees, int *size, int *count);
void updateEmployee(Employee *employees, int count);
void searchEmployee(Employee *employees, int count);
void printDatabase(Employee *employees, int count);
void freeMemory(Employee **employees, int *size, int *count);

int main() {
    int size = 3;                                                      // Initial size of the array.
    int count = 0;                                                     // Count of employees.
    Employee *employees = (Employee *)calloc(size, sizeof(Employee));  // Memory allocation for the array of employees.

    char input[100];  // Buffer to store the input line.
    char choice;      // Variable to store the user's menu choice.
    do {
        // Display the menu.
        printf("\nEnter your Choice:\n");
        printf("'i' - Insert data\n'u' - Update data\n's' - Search employee\n'p' - Print the database\n'q' - Quit\n");

        // Read the entire line.
        fgets(input, sizeof(input), stdin);

        // Parse the first character as the choice.
        sscanf(input, "%c", &choice);

        // Handle the user's choice.
        switch (choice) {
            case 'i':
            case 'I':
                insertEmployee(&employees, &size, &count);
                break;
            case 'u':
            case 'U':
                updateEmployee(employees, count);
                break;
            case 's':
            case 'S':
                searchEmployee(employees, count);
                break;
            case 'p':
            case 'P':
                printDatabase(employees, count);
                break;
            case 'q':
            case 'Q':
                freeMemory(&employees, &size, &count);
                break;
            default:
                printf("%c is not a valid choice.\n", choice);  // Case for invalid choices.
                break;
        }
    } while (choice != 'q' && choice != 'Q');  // Continue until the user quits.

    return 0;
}

// Function to insert a new employee into the database.
void insertEmployee(Employee **employees, int *size, int *count) {
    Employee newEmployee;
    char input[100];

    printf("Enter ID: ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%d", &newEmployee.id);

    // Check if ID already exists.
    for (int i = 0; i < *count; i++) {
        if ((*employees)[i].id == newEmployee.id) {
            printf("Error! ID is already in the database.\n");
            return;
        }
    }

    // Read the new employee's data.
    printf("Enter First Name: ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%49s", newEmployee.firstName);  // 49 to leave room for the null terminator.

    printf("Enter Last Name: ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%49s", newEmployee.lastName);

    printf("Enter Date of Birth (MMDDYYYY): ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%2d%2d%4d", &newEmployee.dob.month, &newEmployee.dob.day, &newEmployee.dob.year);

    printf("Enter Salary: ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%lf", &newEmployee.salary);

    // If the array is full, double its size.
    if (*count == *size) {
        *size *= 2;
        *employees = (Employee *)realloc(*employees, *size * sizeof(Employee));
    }

    // Add the new employee to the array and increment the count.
    (*employees)[*count] = newEmployee;
    (*count)++;
}

// Function to update an existing employee's data.
void updateEmployee(Employee *employees, int count) {
    int id;
    int option;
    char input[100];

    printf("Enter ID of the employee to update: ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%d", &id);

    // Find the employee with the given ID.
    int index = -1;
    for (int i = 0; i < count; i++) {
        if (employees[i].id == id) {
            index = i;
            break;
        }
    }

    // If the employee is not found, display an error message.
    if (index == -1) {
        printf("ID doesn't exist.\n");
        return;
    }

    do {
        // Ask the user which data to update.
        printf("Which data do you want to update?\n");
        printf("1 - First Name\n2 - Last Name\n3 - DOB\n4 - Salary\n5 - Do not update\n");
        fgets(input, sizeof(input), stdin);
        sscanf(input, "%d", &option);

        // Update the selected data.
        switch (option) {
            case 1:
                printf("Enter new First Name: ");
                fgets(input, sizeof(input), stdin);
                sscanf(input, "%49s", employees[index].firstName);
                break;
            case 2:
                printf("Enter new Last Name: ");
                fgets(input, sizeof(input), stdin);
                sscanf(input, "%49s", employees[index].lastName);
                break;
            case 3:
                printf("Enter new Date of Birth (MMDDYYYY): ");
                fgets(input, sizeof(input), stdin);
                sscanf(input, "%2d%2d%4d", &employees[index].dob.month, &employees[index].dob.day, &employees[index].dob.year);
                break;
            case 4:
                printf("Enter new Salary: ");
                fgets(input, sizeof(input), stdin);
                sscanf(input, "%lf", &employees[index].salary);
                break;
            case 5:
                return;
            default:
                printf("Option is not valid, try it again.\n");
                break;
        }
    } while (option < 1 || option > 5 || option == 0);
}

// Function to search for an employee by ID and display their information.
void searchEmployee(Employee *employees, int count) {
    int id;
    char input[100];

    printf("Enter ID of the employee to search for: ");
    fgets(input, sizeof(input), stdin);
    sscanf(input, "%d", &id);

    // Find the employee with the given ID and display their information.
    for (int i = 0; i < count; i++) {
        if (employees[i].id == id) {
            printf("*********************************************************\n");
            printf("ID: %d\n", employees[i].id);
            printf("First Name: %s\n", employees[i].firstName);
            printf("Last Name: %s\n", employees[i].lastName);
            printf("DOB: %d/%d/%d\n", employees[i].dob.month, employees[i].dob.day, employees[i].dob.year);
            printf("Salary: %.2lf\n", employees[i].salary);
            printf("*********************************************************\n");
            return;
        }
    }

    // If the employee does not exist, display an error message.
    printf("ID Not Found.\n");
}

// Function to print all the information in the database.
void printDatabase(Employee *employees, int count) {
    printf("*********************************************************\n");
    printf("ID\tName\t\tDOB\t\tSalary\n");
    for (int i = 0; i < count; i++) {
        printf("%d\t%s %s\t%d/%d/%d\t%.2lf\n", employees[i].id, employees[i].firstName, employees[i].lastName,
               employees[i].dob.month, employees[i].dob.day, employees[i].dob.year, employees[i].salary);
    }
    printf("*********************************************************\n");
}

// Function to free all dynamically allocated memory and exit the program.
void freeMemory(Employee **employees, int *size, int *count) {
    free(*employees);   // Free the memory allocated for the array of employees.
    *employees = NULL;  // Set the pointer to NULL.
    *size = 0;          // Reset the size.
    *count = 0;         // Reset the count.
}