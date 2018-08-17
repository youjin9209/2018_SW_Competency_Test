/*
문제링크
https://www.acmicpc.net/problem/10972
*/
import java.util.Scanner;

public class bf_3_8_다음순열_10972 {
	public static boolean next_permutation(int[] A) {
		// 예) 7 2 3 6 5 4 1
		// 1. A[i-1] < A[i] 를 만족하는 i 찾기 : 6
		int i = A.length - 1;
		while (i > 0 && A[i-1] >= A[i]) {
			i--;
		}
		if (i <= 0) {
			return false;
		}
		// 2. j >= i 이면서 A[j] > A[i-1]를 만족하는 가장 큰 j 찾기  : 4
		int j = A.length - 1;
		while (A[j] <= A[i-1]) {
			j--;
		}
		// 3. swap(A[i-1] <-> A[j]) : swap (3, 4)
		int temp = A[i-1];
		A[i-1] = A[j];
		A[j] = temp;
		// 4. A[i] 부터 뒤집기 : 6 5 3 1 -> 1 3 5 6
		int k = A.length - 1;
		while (i < k) {
			temp = A[i];
			A[i] = A[k];
			A[k] = temp;
			k--;
			i++;
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		if (next_permutation(A)) {
			for (int i = 0 ; i < N; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
		} else {
			System.out.println("-1");
		}
	}
}
