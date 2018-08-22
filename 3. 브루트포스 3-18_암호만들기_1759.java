import java.util.Arrays;
import java.util.Scanner;
/*
 1) 최소 한 개의 모음과 최소 두 개의 자음
 2) 증가수열 
 3) go (n, alpha, password, i)
n: 암호 길이 
alpha : 사용가능한 알파벳 
password : 현재까지 만든 암호 
i : 사용할지 말지 결정해야하는 인덱스 
 */
public class bf_3_18_암호만들기_1759 {
	public static boolean check (String password) {
		int ja = 0;
		int mo = 0;
		for (char x : password.toCharArray()) {
			if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u')
				mo += 1;
			else
				ja += 1;
		}
		return ja >= 2 && mo >= 1;
	}
	public static void go(int n, char[] alpha, String password, int i) {
		if (password.length() == n) {
			if (check(password)) {
				System.out.println(password);
			}
			return;
		}
		if (i >= alpha.length) 
			return;
		// i 번째 알파벳을 사용 할 때 
		go(n, alpha, password + alpha[i], i+1);
		// 안사용 할 때 
		go(n, alpha, password, i+1);
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int C = sc.nextInt();
		char[] alpha = new char[C];
		sc.nextLine();
		String[] line = sc.nextLine().split(" ");
		for (int i = 0; i < C; i++) {
			alpha[i] = line[i].charAt(0);
		}
		Arrays.sort(alpha); // 증가수열이니까 일단 정렬하고 시작
		go(L, alpha, "", 0);
	}
}
