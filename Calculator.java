public class Calculator {
    Item[] expr;
    int instructionPointer;
    Stack stack;

    public Calculator(Item[] expr) {
        this.expr = expr;
        this.instructionPointer = 0;
        this.stack = new Stack();
    }

    public int run() {
        while (instructionPointer < expr.length) {
            step();
        }
        return stack.pop();
    }

public void getOperationType(char operator){
       int y = stack.pop();
       int x = stack.pop();
       int res = 0;
       switch (operator) {
        case '+':
            res = x+y;
            break;
        case '-':
            res = x-y;
            break;
        case '*':
            res = x*y; 
            break;
        case '/':
            res = x/y;
            break;
       }
        stack.push(res);
}
    public void step() {
        Item next = expr[instructionPointer++];
        switch (next.getType()) {
            case ADD: {
            getOperationType('+');
            break;
            }
            case SUB: {
            getOperationType('-');
            break;
            }
            case DIV: {
            getOperationType('/');
            break;
            }
            case MUL: {
            getOperationType('*');
            break;
            }
            case VALUE: {
            stack.push(next.getValue());
            break;
            }
        }
    }

public static void main(String[] args) { 
    /*
    StaticStack stack = new StaticStack(3);
    stack.push(5);
    stack.push(4);
    System.out.println(stack.pop());
    stack.push(5);
    stack.push(2);
    System.out.println(stack.pop());
     */
    // 10 2 5 * +   in reversed Polish notation
    Item[] expr = {
        Item.Value(10),
        Item.Value(2),
        Item.Value(5),
        Item.Mul(),
        Item.Value(5),
        Item.Add(),
        Item.Add(),
};
    Calculator calc = new Calculator(expr);
    int res = calc.run();
    System.out.println(" Calculator: res = " + res);
}
}
