import java.util.Scanner;

public class Calc
{
    public static String[] roman = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public static boolean choiceRoman (String romSymb)
    {
        return romSymb.matches("[XVI]");
    }

    public static boolean Arabian(String arabSymb)
    {
        try
        {
            Integer.parseInt(arabSymb);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static int romanToArabian(String romanString)
    {
        int xSymb = (int)romanString.chars().filter(ch -> ch == 'X').count();
        int vSymb = (int)romanString.chars().filter(ch -> ch == 'V').count();
        int iSymb = (int)romanString.chars().filter(ch -> ch == 'I').count();
        return xSymb * 10 + vSymb * 5 + iSymb - (romanString.endsWith("I") ? 0 : 2);
    }

    public static String arabianToRome(int arabian)
    {
        if (arabian == 100) return "C";
        int SymbL = arabian / 50;
        int SymbX = arabian % 50 / 10;
        int SymbI = arabian % 50 % 10;
        return "L".repeat(SymbL) + "X".repeat(SymbX) + roman [SymbI];
    }

    public static int getResult(int num1, int num2, String operator) throws Exception
    {
        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10))
        {
            throw new Exception("Числа не в диапазоне 1..10");
        }
        switch (operator)
        {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            default: return num1 / num2;
        }
    }

    public static void Result(String operand1, String operand2, String operator) throws Exception
    {
        if (!operator.matches("[+*/-]"))
        {
            throw new Exception("Ошибка операции");
        }

        if (Arabian(operand1) && Arabian(operand2))
        {
            int num1 = Integer.parseInt(operand1);
            int num2 = Integer.parseInt(operand2);

            System.out.println(getResult(num1, num2, operator));
        }
        else if (choiceRoman(operand1) && choiceRoman(operand2))
        {
            int num1 = romanToArabian(operand1);
            int num2 = romanToArabian(operand2);

            System.out.println(arabianToRome(getResult(num1, num2, operator)));
        }
        else
        {
            throw new Exception("Разные системы счисления");
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String example = scanner.nextLine();

        String[] signs = example.split(" ");
        String operand1 = signs[0];
        String operand2 = signs[2];
        String operator = signs[1];

        try
        {
            Result(operand1, operand2, operator);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}