import java.io.*;
import java.util.*;

public class Calculator {
    private final MyStack stack;

    public Calculator()
    {
        stack = new MyStack();
    }

    public long compute(String exp) throws IOException
    {
        stack.clear();

        PushbackReader input = new PushbackReader(new StringReader(exp + ";"));

        int c;
        while ((c = input.read()) != ';') {
            char ch = (char)c;

            if (Character.isDigit(ch)) {
                long num = 0;
                while (Character.isDigit(ch)) {
                    num = 10 * num + (ch - '0');
                    c = input.read();
                    ch = (char)c;
                }
                input.unread(c);
                stack.push(num);
            } else {
                long a, b;
                switch (ch) {
                    case '+':
                        b = (Long)stack.pop();
                        a = (Long)stack.pop();
                        stack.push(a + b);
                        break;
                    case '-':
                        b = (Long)stack.pop();
                        a = (Long)stack.pop();
                        stack.push(a - b);
                        break;
                    case '*':
                        b = (Long)stack.pop();
                        a = (Long)stack.pop();
                        stack.push(a * b);
                        break;
                    case '/':
                        b = (Long)stack.pop();
                        a = (Long)stack.pop();
                        stack.push(a / b);
                        break;
                    case ' ':
                    case '\t':
                    case '\r':
                    default:
                        throw new IllegalArgumentException("不正な文字" + ch + "がありました。");
                }
            }
        }

        if (!stack.isEmpty()) {
            return (Long)stack.pop();
        } else {
            throw new IllegalArgumentException("式がありません。");
        }
    }

    public static void main(String args[]) throws IOException
    {
        BufferdReader input = new BufferdReader(new InputStreamReader(System.in));

        Calculator calculator = new Calculator();

        String line;
        while ((line = input.readLine()) != null) {
            try {
                long answer = calculator.compute(line);
                System.out.println("値は" + answer + "です。");
            } catch (EmptyStackException e) {
                System.out.println("式が正しくありません。");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
