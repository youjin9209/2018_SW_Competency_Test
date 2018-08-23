/*
문제링크 
https://www.acmicpc.net/problem/1182
해결 
go (index, sum)
index : 부분집합에 포함할지 말지 결정해야하는 index
sum : 현재까지 부분집합의 합   
*/
import java.util.Scanner;
public class bf_3_19_부분집합의합_1182 {
	static int N;
	static int S;
	static int[] a;
	static int cnt = 0;
	public static void go(int index, int sum) {
		if (index == N && sum == S) {
			cnt++;
			sum = 0;
			return;
		}
		if (index == N && sum != S) {
			sum = 0;
			return;
		}
		
		go(index + 1, sum + a[index]);
		go(index + 1, sum);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		go(0, 0);
		if (S == 0)
			cnt -= 1;
		System.out.println(cnt);
	}
}
