import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Enter your expression (only valid options: \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b)");

        Scanner keyboard = new Scanner(System.in);
        String line = keyboard.nextLine();

        System.out.println("\"" + calc(line) + "\"");
    }

    public static String calc(String input) throws Exception {

        String output = "";                                                                                             // output - возвращаемая строка
        String a = "\"";                                                                                                // переменная для определения длин подстрок путем поиска кавычек

        int index1 = input.indexOf(a);                                                                                  // index1 - порядковый номер первых кавычек в строке
        int index2 = input.indexOf(a, (index1 + 1));                                                                    // index2 - порядковый номер вторых кавычек в строке

        if (!(index1 == 0))
            throw new Exception("First you must enter only *String*");                                                  // исключение для выражений, начинающихся не со строки

        String str1 = input.substring(index1 + 1, index2);                                                              // первая подстрока, без кавычек
        String operator = input.substring(index2 + 1, index2 + 4);                                                      // оператор, исходя из длины первой подстроки

        operator = operator.trim();

        if (str1.length() > 10)
            throw new Exception("Your *String* length must be 10 or less");                                             // исключение для строк длиной более 10 символов

        if (operator.equals("*") || operator.equals("/")) {                                                             // обработка умножения и деления отдельно, т. к. в этих случаях вводится строка + число
            int num2;
            try {
                num2 = Integer.parseInt(input.substring(index2 + 4));                                         // присвоение переменной num2 числа, находящегося после первой строки и оператора
            } catch (NumberFormatException e) {
                throw new Exception("You can * or / only *String* by *Integer*");                                       // исключение для выражений, не содержащих число после оператора
            }

            if (!((num2 > 0) && (num2 < 11)))
                throw new Exception("the numbers entered must be from 1 to 10");                                        // исключение для чисел < 1 и > 10

            int i = 0;
            switch (operator) {
                case "*":                                                                                               // поиск умножения
                    while (i < num2) {
                        output += str1;                                                                                 // конкатенация, повторяемая num2 раз
                        i++;
                    }
                    break;
                case "/":                                                                                               // поиск деления
                    output = str1.substring(0, str1.length() / (num2));                                                 // деление согласно длине строки str1
                    break;
                default:
                    throw new Exception("There must be a correct *operator* in your statement!");                       // исключение для некорректного/отсутствующего оператора
            }

        } else {
            String str2 = input.substring(index2 + 5, input.lastIndexOf(a));                                            // присвоение переменной str2 подстроки, находящейся после первой строки и оператора

            if (str2.length() > 10)
                throw new Exception("Your *String* length must be 10 or less");                                         // исключение для строк длиной более 10 символов

            switch (operator) {
                case "+":                                                                                               // поиск сложения
                    output = str1 + str2;                                                                               // конкатенация
                    break;
                case "-":                                                                                               // поиск вычитания
                    output = str1.replaceAll(str2, "");                                                      // замена отрезка подстроки str1 совпадающего с подстрокой str2 на *ничто*
                    break;
                default:
                    throw new Exception("There must be a correct *operator* in your statement!");                       // исключение для некорректного/отсутствующего оператора
            }
        }

        if (output.length() >= 40)
            output = output.substring(0, 40) + "...";                                                                   // изменение возвращаемой строки output при длине в 40 и более символов

        return output;
    }
}