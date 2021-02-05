
// Dana Im
// 4/9/20
// CSE 142
// Assignment #2
// TA: Kendra Specht 
public class DrawRocket {
    public static final int SIZE = 3;
    public static void main(final String[] args) {
        drawRocketHat();
        drawLine();
        drawBodyOne();
        drawBodyTwo();
        drawLine();
        drawBodyTwo();
        drawBodyOne();
        drawLine();
        drawRocketHat();
    }

    public static void drawRocketHat() {
    // It prints the top of the rocket
        for (int line = 1; line <= 2 * SIZE -1; line++) {
            for (int space = 1; space <= -1 * line + 2 * SIZE; space++) {
                System.out.print(" ");
            }
            for (int forward = 1 ; forward <= +1 * line + 0 ; forward ++) {
                System.out.print("/");
            }
            System.out.print("**");
            for (int backslash = 1 ; backslash <= +1 * line + 0 ; backslash ++) {
                System.out.print("\\");
            }
            System.out.println();
        }
    }

    public static void drawLine() {
    // It divides body and the rocket sections.
        System.out.print("+");
        for (int star =1; star <= 2 * SIZE; star++) {
            System.out.print("=*");
        }
        System.out.println("+");
    }

    public static void drawBodyOne() {
    // It prints out redundant body one figures.
        for (int line = 1; line <= SIZE; line++) {
            System.out.print("|");
            for (int dot = 1; dot <= -1 * line +1 * SIZE; dot++) {
                System.out.print(".");
            }
            for (int slashes = 1; slashes <= +1 * line + 0; slashes++) {
                System.out.print("/\\");
            }
            for (int dot2 = 1; dot2 <= -2 * line + 2 * SIZE; dot2++) {
                System.out.print(".");
            }
            for (int slashes = 1; slashes <= +1 * line + 0; slashes++) {
                System.out.print("/\\");
            }
            for (int dot = 1; dot <= -1 * line + 1 * SIZE; dot++) {
                System.out.print(".");
            }
            System.out.println("|");
        }
    }

    public static void drawBodyTwo() {
    // It prints out redundant body two figures.
        for (int line = 1; line <= 1 * SIZE; line++) {
            System.out.print("|");
            for (int dots = 1; dots <= line - 1; dots++) {
                System.out.print(".");
            }  
            for (int backwardSlashes = 1; backwardSlashes <= -line + SIZE +1; backwardSlashes++) {
                System.out.print("\\/"); 
            }
            for (int dots = 1; dots <= 2 * (line - 1); dots++) {
                System.out.print(".");
            }
            for (int backwardSlashes = 1; backwardSlashes <= -line + SIZE +1; backwardSlashes++) {
                System.out.print("\\/"); 
            }
            for (int dots = 1; dots <= line - 1; dots++) {
                System.out.print(".");
            }
            System.out.println("|");
        }
    }
}

















