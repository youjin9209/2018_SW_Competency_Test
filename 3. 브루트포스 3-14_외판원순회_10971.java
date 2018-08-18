/*
문제링크 
https://www.acmicpc.net/problem/10971

모든 경우 다 해보기 ! 가능하다. 10! = 3628800 
시간복잡도 : O(N * N!)
- 순회를 도는 index를 다음 순열로 돌려준다 
1 -> 2 -> 3 -> 4
2 -> 3 -> 4 -> 1
3 -> 4 -> 1 -> 2
4 -> 1 -> 2 -> 3
위의 4가지는 다 같다. 어짜피 순회니까 ! 그래서 시작점을 1로 고정해도 ㅇㅋ 
*/
import java.util.Scanner;

public class bf_3_14_외판원순회_10971 {
	public static boolean next_permutation (int[] a) {
		// 7 2 3 6 5 4 1
		// 1) 가장 큰 감소수열: 시작점 i 찾기 
		int i = a.length - 1;
		while (i >= 0 && a[i-1] >= a[i]) {
			i -= 1;
		}
		if (i <= 0) {
			return false;
		}
		// 2) j >= i 이면서 a[j] > a[i-1] 만족하는 가장 큰 j 찾기 
		int j = a.length - 1;
		while (a[j] <= a[i-1]) {
			j -= 1;
		}
		// 3) swap(a[j], a[i-1]) 
		int temp = a[i-1];
		a[i-1] = a[j];
		a[j] = temp;
		// 4) a[i] 부터 뒤집기 
		int k = a.length - 1;
		while (i < k) {
			temp  = a[i];
			a[i] = a[k];
			a[k] = temp;
			i += 1;
			k -= 1;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] a = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		// 순회를 도는 index 배열 d[] : 다음 순열로 돌려줄거임 
		int[] d = new int[N];
		for (int i = 0; i < N; i++) {
			d[i] = i;
		}
		int ans = Integer.MAX_VALUE; // 최소구해줘야 하니까 
		do {
			if (d[0] != 0) break; // d[0] 이 다시되는순간 탈출 
			int sum = 0;
			boolean flag = true;
			// 0 -> 1 -> 2 -> 3 계산 
			for (int i = 0; i < N-1; i++) {
				if (a[d[i]][d[i+1]] == 0) {
					flag = false;
				} else {
					sum += a[d[i]][d[i+1]];
				}
			}
			// 3 -> 0 계산 
			if (flag && a[d[N-1]][d[0]] != 0) {
				sum += a[d[N-1]][d[0]];
				if (ans > sum) {
					ans = sum;
				}
			}
		} while(next_permutation(d));
		System.out.println(ans);
	}
}
