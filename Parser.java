import java.util.ArrayList;

public class Parser {
    public ArrayList<Item> parse(String input) {
        String[] inputSplit = input.split(" ");
        ArrayList<Item> expr = new ArrayList<Item>();
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
