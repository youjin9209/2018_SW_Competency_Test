/*
문제링크 
https://www.acmicpc.net/problem/1476 
해결 : 체크 및 출력 -> 범위초과시 초기화 !
*/
import java.util.Scanner;

public class bf_3_2_날짜계산_1476 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt(); // 15
		int S = sc.nextInt(); // 28
		int M = sc.nextInt(); // 19
		int e = 1;
		int s = 1;
		int m = 1;
		for (int i = 1; ; i++) {
			if (e == E && s == S && m == M) {
				System.out.println(i);
				System.exit(0);
			}
			e++;
			s++;
			m++;
			if (e > 15)
				e = 1;
			if (m > 19)
				m = 1;
			if (s > 28)
				s = 1;
		}
	}
}
