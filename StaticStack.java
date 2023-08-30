class StaticStack extends Stack {
    private int[] array;
    private int stackPointer;

    public StaticStack(int size) {
        array = new int[size];
        stackPointer = -1;
    }

    @Override
    public void push(int value) {
        stackPointer++;
        array[stackPointer] = value;
    }

    @Override
    public int pop() {
        int r = array[stackPointer];
        stackPointer--;
        return r;
    }
}
