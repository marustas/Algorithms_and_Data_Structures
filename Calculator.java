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
     */
    String test = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 + * + * + * + * + * + * + * +";
    Item[] expr = Parser.parse(test);
    Calculator calc = new Calculator(expr);
    int res = calc.run();
    System.out.println(" Calculator: res = " + res);

    // 10 2 5 * +   in reversed Polish notation

    //System.out.println(stack.pop());
}
}
