import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

/**
* The ArrayVsLists program reads each text file containing a list of integers.
* It converts them into integers and puts them into an arrayList.
* It then sorts the arrayList and calculates the mean, median, and mode.
* It then displays the numbers sorted from smallest to largest.
* Finally, it displays the mean, median, and mode.

*
* @author Noah Smith
* @version 1.0
* @since 2025-03-25
*/

final class ArraysVsLists {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private ArraysVsLists() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is calculate mean method.
     * @param arrayInt Array of integers.
     * @return mean as a double.
     */

    public static double calcMean(final int[] arrayInt) {

        // initialize sum
        double sum = 0.0;

        // iterate through the array
        for (double integer : arrayInt) {
            // add the integer of the current index to the sum
            sum += integer;
        }

        // return the mean
        return (sum / arrayInt.length);
    }

    /**
     * This is calculate mean method.
     * @param arrayInt Array of integers.
     * @return median as a double.
     */

    public static double calcMedian(final int[] arrayInt) {

        // initialize median
        double median = 0.0;

        // If the length of the array is even
        if (arrayInt.length % 2 == 0) {
            // find the two middle indexes
            int medianIndex1 = arrayInt.length / 2;
            int medianIndex2 = medianIndex1 - 1;

            // calculate the median
            median = (arrayInt[medianIndex1] + arrayInt[medianIndex2]) / 2.0;
        // If the length of the array is odd
        } else {
            // find the middle index
            int medianIndex = arrayInt.length / 2;

            // set the value at the middle index to the median
            median = arrayInt[medianIndex];
        }

        // return the median
        return median;
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {

        // create a scanner object
        Scanner scanner = new Scanner(System.in);

        // initialize the file name
        String fileName = "";

        do {
            // Ask the user for the file name
            System.out.print("Enter the number set you would like to use");
            System.out.print("(1, 2, 3, etc) or 'q' to quit: ");

            // get the file name
            fileName = scanner.nextLine();

            // if the user enters 'q'
            if (fileName.equals("q")) {
                // Display goodbye message
                System.out.println("Goodbye!");
            } else if ((!fileName.equals("1"))
                    && (!fileName.equals("2"))
            && (!fileName.equals("3"))) {

                // if the user enters an invalid input
                System.out.print("Invalid input.");
                System.out.println("Please enter a number set (1, 2, or 3).");
            } else {

                // set the file name
                fileName = "Unit2-06-set" + fileName + ".txt";

                // create a file object
                File file = new File(fileName);

                // create a second scanner object
                Scanner fileScanner = new Scanner(file);

                // create an array list
                ArrayList<String> numbers = new ArrayList<String>();

                // iterate through the arraylist
                while (fileScanner.hasNext()) {
                    // add the integer to the array list
                    numbers.add(fileScanner.next());
                }

                // Convert the arrayList of strings into an array of numbers
                int[] arrayInt = new int[numbers.size()];

                // iterate through the array
                for (int counter = 0; counter < numbers.size(); counter += 1) {
                    // add the integer of the current index to the array list
                    arrayInt[counter] = Integer.parseInt(
                                numbers.set(counter,
                                numbers.get(counter)));
                }

                // close the file scanner
                fileScanner.close();

                // sort the array
                java.util.Arrays.sort(arrayInt);

                // calculate the mean
                double mean = calcMean(arrayInt);

                // calculate the median
                double median = calcMedian(arrayInt);

                // create a file writer object
                FileWriter fileWriter =
                new FileWriter(fileName + "-output.txt");

                // iterate through the array list
                for (int counter = 0;
                    counter < arrayInt.length;
                    counter += 1) {
                    // write the integers to the file,
                    // one by one, separated by a space
                    fileWriter.write(arrayInt[counter] + " ");
                }

                // Newline for spacing
                fileWriter.write("\n");

                // write the mean to the file
                fileWriter.write("Mean: " + mean + "\n");

                // write the median to the file
                fileWriter.write("Median: " + median + "\n");

                // close the file writer
                fileWriter.close();

                // Display a success message
                System.out.println("The file '"
                    + fileName + "' has been written successfully.");
            }

        // Keep looping until the user enters 'q'
        } while (!fileName.equals("q"));

        // close the scanner
        scanner.close();
    }
}
