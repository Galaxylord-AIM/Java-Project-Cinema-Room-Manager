import java.util.*;

public class Main {
    public static void main(String[] args) {
        // accepting values
        Scanner scanner = new Scanner(System.in);
        double M = scanner.nextInt();
        double P = scanner.nextInt();
        double K = scanner.nextInt();
        int years = 0;
        while (M<K){
            ++years;
            M = M + (M * (P/100));
        }
        System.out.println(years);
    }
}