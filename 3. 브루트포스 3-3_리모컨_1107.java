/*
문제링크
https://www.acmicpc.net/problem/1107
핵심 : 최소를 만족하려면 ? : 숫자 -> +, - 버튼 
 */
import java.util.Scanner;

public class bf_3_3_리모컨_1107 {
	static boolean[] malfunc;
	public static int possible(int C) {
		if (C == 0) {
			if (malfunc[0]) // 0 이 고장났을 때 
				return 0;
			else 
				return 1;
		}
		int len = 0;
		while (C > 0) { // 1의 자리부터 시작해서 고장났는지 체크 
			if (malfunc[C % 10])
				return 0;
			C /= 10;
			len++;
		}
		return len;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		// 1. 고장난거 배열에 check
		malfunc = new boolean[10];
		for (int i = 1; i <= M; i++) {
			int idx = sc.nextInt();
			malfunc[idx] = true;
		}
		// 2. 결국 최소를 구할거니까 | N - 100 | 일단 구해놓기 
		int result = 100 - N;
		if (result < 0)
			result = -result;
		// 3. C : N 근처로 이동할 숫자 
		int C = 0;
		for (int i = 0; i < 1000000; i++) {
			C = i;
			// 4. Cbtn : C 로 이동하기 위해 눌러야하는 숫자 버튼의 횟수 
			int Cbtn = possible(C);
			if (Cbtn > 0) {
				// 5. 숫자버튼의 횟수가 0 보다 커야한다. 즉 무조건 숫자버튼 부터 먼 눌러야하니까 !!!!!
				// PMbtn : + or -를 누르는 횟수 
				int PMbtn = 0;
				PMbtn = C - N;
				if (PMbtn < 0)
					PMbtn = -PMbtn;
				if (result > Cbtn + PMbtn)
					result = Cbtn + PMbtn;
			}
		}
		System.out.println(result);
	}
}
