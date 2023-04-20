public class Letter {
    private char letter;
    private int frequency;

    public Letter(char letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "letter=" + letter +
                ", frequency=" + frequency +
                '}';
    }

    public char getLetter() {
        return letter;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void addFrequency(){
        this.frequency++;
    }
    public void deductFrequency(){
        this.frequency--;
    }
}
