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

//    public void sortDescending(){
//        for(int i = 0; i < size - 1; i++){
//            for(int j = i + 1; j < size; j++){
//                if(array[i].getFrequency() < array[j].getFrequency()){
//                    Letter tmp = array[i];
//                    array[i] = array[j];
//                    array[j] = tmp;
//                }
//            }
//        }
//        for(int i = 0; i < array.length; i++){
//            if(array[i].getFrequency() == 0) {
//                lastAvailableElement = i-1;
//                return;
//            }
//        }
//        lastAvailableElement = array.length - 1;
//    }

    public void sortDescending(){
        mergeSort(array, 0, array.length);

        for(int i = 0; i < array.length; i++){
            if(array[i].getFrequency() == 0) {
                lastAvailableElement = i-1;
                return;
            }
        }
        lastAvailableElement = array.length - 1;
    }

    public void mergeSort(Letter[] input, int start, int end) {
        if (end - start < 2) {
            return;
        }
//        System.out.println("Merge Sort");
//        for (int i = 0; i < input.length; i++) System.out.println(input[i]);
        int mid = (start + end) / 2;
        mergeSort(input, start, mid);
        mergeSort(input, mid, end);
        merge(input, start, mid, end);
    }

    public void merge(Letter[] input, int start, int mid, int end) {

        if (input[mid - 1].getFrequency() > input[mid].getFrequency()) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;
        Letter[] temp = new Letter[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = input[i].getFrequency() > input[j].getFrequency() ||
                    (input[i].getFrequency() == input[j].getFrequency() && i < j)
                    ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        System.arraycopy(temp, 0, input, start, tempIndex);

//        System.out.println("After Merged");
//        for (int k = 0; k < input.length; k++) System.out.println(input[k]);
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
