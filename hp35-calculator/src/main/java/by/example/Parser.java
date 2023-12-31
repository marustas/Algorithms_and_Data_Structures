package by.example;

import by.example.expression.Item;

public class Parser {

    private Parser() {
    }

    public static Item[] parse(String input) {
        String[] inputSplit = input.split(" ");
        int ip = 0;
        Item[] expr = new Item[inputSplit.length];
        for (String each : inputSplit) {
            switch (each) {
                case "*":
                    expr[ip] = (Item.Mul());
                    break;
                case "-":
                    expr[ip] = (Item.Sub());
                    break;
                case "+":
                    expr[ip] = (Item.Add());
                    break;
                case "/":
                    expr[ip] = (Item.Div());
                    break;
                default:
                    expr[ip] = (Item.Value(Integer.parseInt(each)));
                    break;
            }
            ip++;
        }
        return expr;
    }
}
