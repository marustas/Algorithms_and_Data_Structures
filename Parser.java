import java.util.ArrayList;

public class Parser {
    public Item[] parse(String input) {
        String[] inputSplit = input.split(" ");
        Item[] expr;
        for (String each : inputSplit) {
            switch (each) {
                case "*":
                    expr.add(Item.Mul());
                    break;
                case "-":
                    expr.add(Item.Sub());
                    break;
                case "+":
                    expr.add(Item.Add());
                    break;
                case "/":
                    expr.add(Item.Div());
                    break;
                default:
                    expr.add(Item.Value(Integer.parseInt(each)));
                    break;
            }
        }
        return expr;
    }
}
