import java.util.Scanner;
/*
문제링크
https://www.acmicpc.net/problem/1748
해결 : 자리수 별로 나누기 
 */
public class bf_3_6_수이어쓰기_1748 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int tempN = N;
		int idx = 0;
		while(tempN > 0) {
			tempN /= 10;
			idx++;
		}
		int result = 0;
		for (int i = 1; i <= idx; i++) {
			if (i != idx) {
				result += i*((Math.pow(10, i)-1) - Math.pow(10, i-1) + 1);
			}
			if (i == idx) {
				result += idx*(N - Math.pow(10, idx-1) + 1);
			}
		}
		System.out.println(result);
	}
}
