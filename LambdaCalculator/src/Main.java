public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);

//      int c = calc.devide.apply(a, b); // В данном случае происходит деление на 0 что не допустимо в математике.
//      calc.println.accept(c); // Получаем ошибку ArithmeticException.

//      Используем try-catch блок для обработки исключения.
//      Таким образом если будет происходить деление на 0, то мы будем получать об этом уведомление.
//      В остальных случаях деление будет происходить нормально.
        try {
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: деление на ноль");
        }
    }
}
