public class LetterArray {
    private Letter[] array;
    private int size;
    private int lastAvailableLetter;

    // Constructor for LetterArray class, accepting 1 attribute which is the size of the array of unique letters
    public LetterArray(int size) {
        this.array = new Letter[size];  // Initialize the array of the input size
        this.size = size;
    }

    // A method to print out each letter in the array with each frequency
    public void print(){
        // Loop through the array
        for (Letter letter : array) {
            System.out.print(letter.getLetter() + ":" + letter.getFrequency() + ", ");
        }
        System.out.println();
    }

    // This method add a new letter and its frequency to the array,
    // accepting the input character, its frequency and index
    public void add(char c, int freq, int index){
        // Create a Letter object with the input character and frequency
        Letter letter = new Letter(c,freq);
        // Add this created letter to the array at the input index
        this.array[index] = letter;
    }

    // This method will sort the array in the descending order based on the frequency,
    public void sortDescending(){
        // use the mergeSort method to do the sorting
        mergeSort(array, 0, array.length);

        // Loop through each Letter object in the array
        for(int i = 0; i < array.length; i++){
            // Check if its frequency is 0 to shorten the list of the remaining characters
            // To improve the performance of the program
            if(array[i].getFrequency() == 0) {
                // Set the lastAvailableElement to the index of the last non-zero frequency
                lastAvailableLetter = i - 1;
                return;
            }
        }
        // If all frequencies are non-zero, set the lastAvailableElement to the last index
        lastAvailableLetter = array.length - 1;
    }

    // This method uses the Merge Sort approach to sort the array of Letter objects based on their frequency,
    // accepting the Letter object array, start and end index
    public void mergeSort(Letter[] input, int start, int end) {
        // If the difference between the end and start indices is less than 2,
        // the array contains less than 2 components, so return
        if (end - start < 2) {
            return;
        }

        // Calculate the midpoint index
        int mid = (start + end) / 2;

        // Recursively call mergeSort on the left half of the array
        mergeSort(input, start, mid);

        // Recursively call mergeSort on the right half of the array
        mergeSort(input, mid, end);

        // Merge the left and right halves of the array
        merge(input, start, mid, end);
    }

    // This method merges the 2 halves of the Letter object array
    // accepting the input Letter object array, the start, mid and end index
    public void merge(Letter[] input, int start, int mid, int end) {
        // If the frequency of the last element in the left half of the array
        // is greater than the first element in the right half of the array,
        // there is nothing to merge, so return
        if (input[mid - 1].getFrequency() > input[mid].getFrequency()) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        // Create a temporary Letter object array for saving the merged sorted array of Letter objects
        Letter[] temp = new Letter[end - start];

        // Loop until reaches the end of the right half and the left half of the array
        while (i < mid && j < end) {
            // If the frequency of the Letter object at index i is greater than
            // the frequency of the Letter object at index j, or if they have the
            // same frequency but i is less than j, add the Letter object at index i
            // to the temporary array and increase i. Otherwise, add the Letter object
            // at index j to the temporary array and increase j.
            temp[tempIndex++] = input[i].getFrequency() > input[j].getFrequency() ||
                    (input[i].getFrequency() == input[j].getFrequency() && i < j)
                    ? input[i++] : input[j++];
        }

        // Copy any remaining elements from the left half of the array to the end of the input array
        System.arraycopy(input, i, input, start + tempIndex, mid - i);

        // Copy the merged elements from the temporary array back into the input array
        System.arraycopy(temp, 0, input, start, tempIndex);
    }

    // This method uses the deductFrequency() method from the Letter class to deduct the frequency of a character
    public void deductFrequency(int index){
        array[index].deductFrequency();
    }

    // This method uses the getter - getLetter() - to return the letter of a specific index
    // Return a character
    public char getLetterByIndex(int index) {
        return array[index].getLetter();
    }

    // This method uses the getter - getFrequency() - to return the frequency of a specific letter
    // Return an integer
    public int getFreqByIndex(int index){
        return array[index].getFrequency();
    }

    // Getter for the size attribute of the array
    // Return an integer
    public int getSize() {
        return size;
    }

    // Getter for the last available element in the array of Letter objects
    // Return an integer
    public int getLastAvailableLetter() {
        return lastAvailableLetter;
    }
}
