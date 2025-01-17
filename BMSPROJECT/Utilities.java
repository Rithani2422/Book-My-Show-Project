package BMSPROJECT;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final LocalDate TODAY = LocalDate.now();

    // Getters for formatters and current date
    public static DateTimeFormatter getTimeFormatter() {
        return TIME_FORMATTER;
    }

    public static LocalDate getToday() {
        return TODAY;
    }

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }


    public static HashMap<Character, ArrayList<String>> generateGrid(long numberOfSeats, String gridPattern) {
        if (numberOfSeats <= 0 || gridPattern == null || gridPattern.isEmpty()) {
            System.out.println("Invalid input: Number of seats must be positive, and grid pattern cannot be empty.");
            return null;
        }

        // Split the grid pattern by '*' to determine group sizes per row
        String[] groups = gridPattern.split("\\*");
        long totalGroupSize = 0;

        // Calculate the total number of seats in one row based on the pattern
        for (String group : groups) {
            // Check if the group is numeric
            if (!isNumeric(group)) {
                System.out.println("Invalid grid pattern: Non-numeric group size encountered.");
                return null;
            }
            totalGroupSize += Long.parseLong(group);  // Sum the group sizes
        }

        // Check if the number of seats matches the grid pattern
        if (numberOfSeats % totalGroupSize != 0) {
            System.out.println("Error: The total number of seats is not divisible by the sum of group sizes in the grid pattern.");
            return null;
        }

        // Initialize the seat grid
        HashMap<Character, ArrayList<String>> seatGrid = new HashMap<>();
        char rowLabel = 'A';
        long seatsLeft = numberOfSeats;

        // Create the seat grid row by row
        while (seatsLeft > 0) {
            ArrayList<String> row = new ArrayList<>();
            for (int i = 0; i < groups.length; i++) {
                long groupSize = Long.parseLong(groups[i]);

                // Add seats for the current group
                for (int j = 0; j < groupSize; j++) {
                    row.add(rowLabel + String.valueOf(j + 1));  // Add seat labels like A1, A2, etc.
                }

                // Add space between groups, but not at the end of the last group
                if (i < groups.length - 1) {
                    row.add("<SPACE>");
                }

                seatsLeft -= groupSize;
            }
            seatGrid.put(rowLabel, row);
            rowLabel++;  // Move to the next row (A, B, C, etc.)
        }

        return seatGrid;
    }

    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}