/*
문제 링크 
https://www.acmicpc.net/problem/11058
화면에 A 를 출력하는 버튼 눌렀을 때 : d[i-1] + 1
A의 개수를 최대화 시켜야함 
마지막에 Ctrl+A, C 를 누르고 Ctrl+V를 j 번 누른 경우 : d[i - (j+2)]*(j+1) 
*/
import java.util.Scanner;

public class bf_1_17_크리보드_11058 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] d = new long[n+1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i-1] + 1;
            for (int j = 1; j <= i-3; j++) {
                if (d[i] < d[i-j-2]*(j+1)) {
                    d[i] = d[i-j-2]*(j+1);
                }
            }
        }
        System.out.println(d[n]);
	}
}
