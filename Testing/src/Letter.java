public class Letter {
    private char letter;
    private int frequency;

    // Constructor for Letter class, accepting 2 attributes which are letter and frequency
    public Letter(char letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    // Overridden toString method to display the letter and its frequency
    // Return a string
    @Override
    public String toString() {
        return "Letter{" +
                "letter=" + letter +
                ", frequency=" + frequency +
                '}';
    }

    // Getter for letter attribute
    // Return a character
    public char getLetter() {
        return letter;
    }

    // Getter for frequency attribute
    // Return an integer
    public int getFrequency() {
        return frequency;
    }

    // Setter for frequency attribute
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Add to frequency attribute
    public void addFrequency(){
        this.frequency++;
    }

    // Subtract the frequency attribute
    public void deductFrequency(){
        this.frequency--;
    }
}
