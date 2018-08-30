/*
문제 링크 
https://www.acmicpc.net/problem/11066
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int fileSummation(int[] a, int[] s, int[][] d, int i, int j) {
		int sum = -1;
		if(i == j) {
			return 0;
		}
		if (d[i][j] != -1) {
			return d[i][j];
		}
		
		for (int p = i; p <= j-1; p++) {
			int temp = fileSummation(a, s, d, i, p) + fileSummation(a, s, d, p+1, j) + s[j] - s[i-1];
			if (sum > temp || sum == -1) {
				sum = temp;
			}
		}
		d[i][j] = sum;
		return d[i][j];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(bf.readLine());
		while (T-- > 0) {
			int K = Integer.valueOf(bf.readLine());
			int[] a = new int[K+1];
			int[] s = new int[K+1];
			int[][] d = new int[K+1][K+1];
			String[] pages = bf.readLine().split(" ");
			
			for (int i = 1; i <= K; i++) {
				a[i] = Integer.valueOf(pages[i-1]);
				s[i] = s[i-1] + a[i];
				Arrays.fill(d[i], -1);
			}
			System.out.println(fileSummation(a, s, d, 1, K));
		}
	}
}
