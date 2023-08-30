import java.util.EmptyStackException;

class StaticStack extends Stack {
    private int[] array;
    private int index;

    public StaticStack(int size) {
        array = new int[size];
        index = -1;
    }

    @Override
    public void push(int value) {
        index++;
                if(index >= array.length){
            throw new StackOverflowError("Stack is full");
        }
        array[index] = value;
    }

    @Override
    public int pop() {
        int r = array[index];
        index--;
            if (index == -1) {
            throw new EmptyStackException();
        }
        return r;
    }
}
