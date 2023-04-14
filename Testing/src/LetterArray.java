public class LetterArray {
    private Letter[] array;
    private int size;
    private int lastAvailableElement;

    public LetterArray(int size) {
        this.array = new Letter[size];
        this.size = size;
    }

    public void print(){
        for (Letter letter : array) {
            System.out.print(letter.getLetter() + ":" + letter.getFrequency() + ", ");
        }
        System.out.println();
    }

    public void add(char c, int freq, int index){
        Letter letter = new Letter(c,freq);
        this.array[index] = letter;
    }

    public void sortDescending(){
        for(int i = 0; i < size - 1; i++){
            for(int j = i + 1; j < size; j++){
                if(array[i].getFrequency() < array[j].getFrequency()){
                    Letter tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        for(int i = 0; i < array.length; i++){
            if(array[i].getFrequency() == 0) {
                lastAvailableElement = i-1;
                return;
            }
        }
        lastAvailableElement = array.length - 1;
    }

    public void deductFrequency(int index){
        array[index].deductFrequency();
    }

    public char getLetterByIndex(int index) {
        return array[index].getLetter();
    }

    public int getFreqByIndex(int index){
        return array[index].getFrequency();
    }

    public int getSize() {
        return size;
    }

    public int getLastAvailableElement() {
        return lastAvailableElement;
    }
}
