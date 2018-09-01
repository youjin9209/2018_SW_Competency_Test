/*
문제 링크 
https://www.acmicpc.net/problem/10942
*/
import java.util.Arrays;
import java.util.Scanner;

public class bf_1_14_팰린드롬_10942 {
	public static int palindrome(int[] a, int[][] d, int s, int e) {
        // 1) 종료조건 
	if(s == e) // 문자열 길이 : 1 
            return 1;
        if (s+1 == e) { // 문자열 길이 : 2
            if (a[s] == a[e])
                return 1;
            else
                return 0;
        }
        // 2) memo 
        if(d[s][e] != -1)  
            return d[s][e];
        // 3) 재귀 불러 
        if (a[s] != a[e])
            return 0;
        else 
            return d[s][e] = palindrome(a, d, s+1, e-1); // 범위 좁혀가며 
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int [N];
        int[][] d = new int[N][N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
            Arrays.fill(d[i], -1);
        }
        StringBuilder sb = new StringBuilder();
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            sb.append(palindrome(a, d, s-1, e-1));
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
