/*
문제링크 
https://www.acmicpc.net/problem/14501
go (day, sum)
: day일이 되었다. day일에 상담을 할지 말지 결정해야한다.  
: 지금까지 얻은 수익은 sum 
*/
import java.util.Scanner;
public class bf_3_20_퇴사_14501 {
	static int N; 
	static int[] T;
	static int[] P;
	static int result = 0;
	public static void go(int day, int sum) {
		// 정답 찾음 
		if (day == N) {
			if (result < sum)
				result = sum;
			return;
		}
		// 정답 아님 
		if (day > N) {
			return ;
		}
		// 상담을 한다 
		go(day + T[day], sum + P[day]);
		// 상담을 안한다 
		go(day + 1, sum);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T = new int[N];
		P = new int[N];
		for (int i = 0; i < N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		go(0, 0);
		System.out.println(result);
	}
}
