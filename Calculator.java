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

public void operation(char operator){
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
            operation('+');
            break;
            }
            case SUB: {
            operation('-');
            break;
            }
            case DIV: {
            operation('/');
            break;
            }
            case MUL: {
            operation('*');
            break;
            }
            case VALUE: {
            stack.push(next.getValue());
            break;
            }
        }
    }

public static void main(String[] args) { 
    // 10 2 5 * +   in reversed Polish notation
    Item[] expr = {
        Item.Value(10),
        Item.Value(2),
        Item.Value(5),
        Item.Mul(),
        Item.Add()
};
    Calculator calc = new Calculator(expr);
    int res = calc.run();
    System.out.println(" Calculator: res = " + res);
}
}
