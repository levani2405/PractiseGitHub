import java.util.HashMap; // Import the HashMap class to store variables

public class MinimalInterpreter {
    // Create a HashMap to store variable names and their corresponding integer values
    private final HashMap<String, Integer> variables = new HashMap<>();

    // Method to evaluate the code passed as a string
    public void eval(String code) {
        // Split the code into individual statements using ";" as the delimiter
        String[] lines = code.split(";");
        for (String line : lines) {
            line = line.trim(); // Remove leading and trailing whitespace
            if (line.isEmpty()) continue; // Skip empty lines

            // Check if the line contains an assignment (i.e., a variable assignment)
            if (line.contains("=")) {
                handleAssignment(line); // Call the method to handle variable assignment
            }
            // Check if the line starts with "PRINT" to handle print statements
            else if (line.startsWith("PRINT")) {
                handlePrint(line); // Call the method to handle print statements
            }
        }
    }

    // Method to handle variable assignment
    private void handleAssignment(String line) {
        // Split the line into variable name and expression using "=" as the delimiter
        String[] parts = line.split("=");
        String varName = parts[0].trim(); // Get the variable name and trim whitespace
        String expression = parts[1].trim(); // Get the expression and trim whitespace

        // Split the expression by "+" to handle addition (this example only supports simple addition)
        String[] numbers = expression.split("\\+");
        // Parse the two numbers from the expression, trim whitespace, and add them
        int value = Integer.parseInt(numbers[0].trim()) + Integer.parseInt(numbers[1].trim());
        // Store the result in the variables HashMap with the variable name as the key
        variables.put(varName, value);
    }

    // Method to handle print statements
    private void handlePrint(String line) {
        // Extract the variable name from the PRINT statement
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        // Check if the variable exists in the HashMap
        if (variables.containsKey(varName)) {
            // Print the value of the variable from the HashMap
            System.out.println(variables.get(varName));
        } else {
            // Print an error message if the variable is not defined
            System.out.println("Undefined variable: " + varName);
        }
    }

    // Main method to run the interpreter
    public static void main(String[] args) {
        MinimalInterpreter interpreter = new MinimalInterpreter(); // Create an instance of the interpreter

        // Example program: Calculate and print the sum of 10 and 20
        String program = """
            sum = 10 + 20; // Assign the sum of 10 and 20 to the variable 'sum'
            PRINT(sum);     // Print the value of 'sum'
        """;

        interpreter.eval(program); // Evaluate the example program
    }
}