package by.example.stack;

public class DynamicStack extends Stack {
    private int[] array = new int[0];

    @Override
    public void push(int value) {
        int[] tempArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        tempArray[array.length] = value;
        this.array = tempArray;
    }

    @Override
    public int pop() {
        int lastItem = array[array.length - 1];
        int[] tempArray = new int[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            tempArray[i] = array[i];
        }
        this.array = tempArray;
        return lastItem;
    }
}
