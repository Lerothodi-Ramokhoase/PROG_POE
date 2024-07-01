/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog_poe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author User
 */
public class PROG_POETest {

    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;

    @BeforeEach
    public void setUp() {
        // Clear existing tasks and users before each test
        PROG_POE.tasks = new Task[10];
        PROG_POE.taskCount = 0;
        PROG_POE.taskNumber = 0;

        // Setting up the tasks using the provided test data
        task1 = new Task("Create Login", 0, "Task to create login functionality", "Mike Smith", 5, "To Do");
        task2 = new Task("Create Add Features", 1, "Task to add new features", "Edward Harrison", 8, "Doing");
        task3 = new Task("Create Reports", 2, "Task to create reports", "Samantha Paulson", 2, "Done");
        task4 = new Task("Add Arrays", 3, "Task to add arrays", "Glenda Oberholzer", 11, "To Do");

        // Add tasks to the list
        PROG_POE.tasks[0] = task1;
        PROG_POE.tasks[1] = task2;
        PROG_POE.tasks[2] = task3;
        PROG_POE.tasks[3] = task4;
        PROG_POE.taskCount = 4;
        PROG_POE.taskNumber = 4;
    }

    @Test
    public void testCheckTaskDescription() {
        assertTrue(task1.checkTaskDescription(), "Task description should be valid");
        assertTrue(task2.checkTaskDescription(), "Task description should be valid");
    }

    @Test
    public void testCreateTaskID() {
        assertEquals("CR:0:TH", task1.createTaskID());
        assertEquals("CR:1:SON", task2.createTaskID());
    }

    @Test
    public void testPrintTaskDetails() {
        String expectedDetails1 = "Task Status: To Do\n" +
                "Developer Details: Mike Smith\n" +
                "Task Number: 0\n" +
                "Task Name: Create Login\n" +
                "Task Description: Task to create login functionality\n" +
                "Task ID: CR:0:TH\n" +
                "Task Duration: 5 hours";
        String expectedDetails2 = "Task Status: Doing\n" +
                "Developer Details: Edward Harrison\n" +
                "Task Number: 1\n" +
                "Task Name: Create Add Features\n" +
                "Task Description: Task to add new features\n" +
                "Task ID: CR:1:SON\n" +
                "Task Duration: 8 hours";

        assertEquals(expectedDetails1, task1.printTaskDetails());
        assertEquals(expectedDetails2, task2.printTaskDetails());
    }

    @Test
    public void testGetTaskDuration() {
        assertEquals(5, task1.getTaskDuration());
        assertEquals(8, task2.getTaskDuration());
    }

    @Test
    public void testReturnTotalHours() {
        int totalHours = PROG_POE.returnTotalHours();
        assertEquals(26, totalHours);
    }

    @Test
    public void testShowTasksWithStatusDone() {
        // Capture the output of the showTasksWithStatusDone method
        StringBuilder result = new StringBuilder("Tasks with status 'Done':\n");
        for (Task task : PROG_POE.tasks) {
            if (task != null && "Done".equalsIgnoreCase(task.getTaskStatus())) {
                result.append("Developer: ").append(task.getDeveloperDetails()).append(", Task Name: ").append(task.getTaskName()).append(", Task Duration: ").append(task.getTaskDuration()).append(" hours\n");
            }
        }
        assertTrue(result.toString().contains("Developer: Samantha Paulson, Task Name: Create Reports, Task Duration: 2 hours"));
    }

    @Test
    public void testShowTaskWithLongestDuration() {
        // Find task with longest duration
        Task longestTask = PROG_POE.tasks[0];
        for (Task task : PROG_POE.tasks) {
            if (task != null && task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        assertNotNull(longestTask);
        assertEquals("Add Arrays", longestTask.getTaskName());
        assertEquals(11, longestTask.getTaskDuration());
    }

    @Test
    public void testDeleteTaskByName() {
        // Delete a task
        String taskNameToDelete = "Create Add Features";
        for (int i = 0; i < PROG_POE.tasks.length; i++) {
            if (PROG_POE.tasks[i] != null && taskNameToDelete.equalsIgnoreCase(PROG_POE.tasks[i].getTaskName())) {
                PROG_POE.tasks[i] = null; // Remove the task
            }
        }

        // Check if the task is deleted
        for (Task task : PROG_POE.tasks) {
            if (task != null) {
                assertNotEquals("Create Add Features", task.getTaskName());
            }
        }
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = new String[PROG_POE.taskCount];
        for (int i = 0; i < PROG_POE.taskCount; i++) {
            actualDevelopers[i] = PROG_POE.tasks[i].getDeveloperDetails();
        }
        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testDisplayDeveloperAndDurationForLongestTask() {
        Task longestTask = PROG_POE.tasks[0];
        for (Task task : PROG_POE.tasks) {
            if (task != null && task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        String expected = "Glenda Oberholzer, 11";
        String actual = longestTask.getDeveloperDetails() + ", " + longestTask.getTaskDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchForTaskByName() {
        String taskNameToSearch = "Create Login";
        Task foundTask = null;
        for (Task task : PROG_POE.tasks) {
            if (task != null && taskNameToSearch.equalsIgnoreCase(task.getTaskName())) {
                foundTask = task;
                break;
            }
        }
        assertNotNull(foundTask);
        String expected = "Mike Smith, Create Login";
        String actual = foundTask.getDeveloperDetails() + ", " + foundTask.getTaskName();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developerToSearch = "Samantha Paulson";
        StringBuilder result = new StringBuilder();
        for (Task task : PROG_POE.tasks) {
            if (task != null && developerToSearch.equalsIgnoreCase(task.getDeveloperDetails())) {
                result.append(task.getTaskName()).append("\n");
            }
        }
        String expected = "Create Reports\n";
        assertEquals(expected, result.toString());
    }

    @Test
    public void testDeleteTaskByNameFromArray() {
        String taskNameToDelete = "Create Reports";
        for (int i = 0; i < PROG_POE.tasks.length; i++) {
            if (PROG_POE.tasks[i] != null && taskNameToDelete.equalsIgnoreCase(PROG_POE.tasks[i].getTaskName())) {
                PROG_POE.tasks[i] = null;
                break;
            }
        }
        boolean taskDeleted = true;
        for (Task task : PROG_POE.tasks) {
            if (task != null && "Create Reports".equalsIgnoreCase(task.getTaskName())) {
                taskDeleted = false;
                break;
            }
        }
        assertTrue(taskDeleted, "Entry 'Create Reports' successfully deleted");
    }

    @Test
    public void testDisplayAllTasks() {
        StringBuilder result = new StringBuilder("All captured tasks:\n");
        for (Task task : PROG_POE.tasks) {
            if (task != null) {
                result.append(task.printTaskDetails()).append("\n");
            }
        }
        String expected = "All captured tasks:\n" +
                "Task Status: To Do\n" +
                "Developer Details: Mike Smith\n" +
                "Task Number: 0\n" +
                "Task Name: Create Login\n" +
                "Task Description: Task to create login functionality\n" +
                "Task ID: CR:0:TH\n" +
                "Task Duration: 5 hours\n" +
                "Task Status: Doing\n" +
                "Developer Details: Edward Harrison\n" +
                "Task Number: 1\n" +
                "Task Name: Create Add Features\n" +
                "Task Description: Task to add new features\n" +
                "Task ID: CR:1:SON\n" +
                "Task Duration: 8 hours\n" +
                "Task Status: Done\n" +
                "Developer Details: Samantha Paulson\n" +
                "Task Number: 2\n" +
                "Task Name: Create Reports\n" +
                "Task Description: Task to create reports\n" +
                "Task ID: CR:2:SON\n" +
                "Task Duration: 2 hours\n" +
                "Task Status: To Do\n" +
                "Developer Details: Glenda Oberholzer\n" +
                "Task Number: 3\n" +
                "Task Name: Add Arrays\n" +
                "Task Description: Task to add arrays\n" +
                "Task ID: CR:3:ER\n" +
                "Task Duration: 11 hours\n";
        assertEquals(expected, result.toString());
    }
}
