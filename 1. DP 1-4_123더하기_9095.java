import java.util.Scanner;
 
public class course_10_1 {
    static int A = 1000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] d = new int[N+1];
        d[1] = 1;
        d[2] = 2;
        d[3] = 4;
        for (int i = 4; i <= N; i++) {
            d[i] = d[i-1] + d[i-2] + d[i-3];
            d[i] %= A;
        }
        System.out.println(d[N]);
    }
}
