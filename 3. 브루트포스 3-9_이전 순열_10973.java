/*
문제링크
https://www.acmicpc.net/problem/10973
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static boolean prev_permutation(int[] A) {
		int i = A.length - 1;
		while (i > 0 && A[i-1] <= A[i]) {
			i--;
		}
		if (i <= 0) {
			return false;
		}
		int j = A.length - 1;
		while (A[j] >= A[i-1]) {
			j--;
		}
		int temp = A[i-1];
		A[i-1] = A[j];
		A[j] = temp;
		int k = A.length - 1;
		while (i < k) {
			temp = A[i];
			A[i] = A[k];
			A[k] = temp;
			i++;
			k--;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		if (prev_permutation(A)) {
			for (int i = 0; i < n; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
		} else {
			System.out.println("-1");
		}
	}
}
