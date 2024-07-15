import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    static BinaryOperator<Integer> plus = (x, y) -> x + y;
    static BinaryOperator<Integer> minus = (x, y) -> x - y;
    static BinaryOperator<Integer> multiply = (x, y) -> x * y;
    static BinaryOperator<Integer> devide = (x, y) -> x / y;

    static UnaryOperator<Integer> pow = x -> x * x;
    static UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

    static Predicate<Integer> isPositive = x -> x > 0;

    static Consumer<Integer> println = System.out::println;
}
