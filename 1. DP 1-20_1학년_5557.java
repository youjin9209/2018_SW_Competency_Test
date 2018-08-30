/*
문제 링크 
https://www.acmicpc.net/problem/5557 
d[i][j] = i 까지 수를 사용해서 j를 만드는 방법의 수 
*/
import java.util.*;
public class Main {
    static long[][] d = new long[100][21];
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        n -= 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int goal = sc.nextInt();
        d[0][a[0]] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 20; j++) { // 20을 넘는 수는 모름 
                if (j-a[i] >= 0) {
                    d[i][j] += d[i-1][j-a[i]];
                }
                if (j+a[i] <= 20) {
                    d[i][j] += d[i-1][j+a[i]];
                }
            }
        }
        System.out.println(d[n-1][goal]);
    }
}
