/*
문제 링크 
https://www.acmicpc.net/problem/14891
*/
import java.util.Scanner;

public class bf_5_1_톱니바퀴_14891 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] a1 = new int[8];
		int[] a2 = new int[8];
		int[] a3 = new int[8];
		int[] a4 = new int[8];
		String[] line1 = sc.nextLine().split("");
		for (int i = 0; i < 8; i++)
			a1[i] = Integer.parseInt(line1[i]);
		String[] line2 = sc.nextLine().split("");
		for (int i = 0; i < 8; i++)
			a2[i] = Integer.parseInt(line2[i]);
		String[] line3 = sc.nextLine().split("");
		for (int i = 0; i < 8; i++)
			a3[i] = Integer.parseInt(line3[i]);
		String[] line4 = sc.nextLine().split("");
		for (int i = 0; i < 8; i++)
			a4[i] = Integer.parseInt(line4[i]);
		int K = sc.nextInt();
		for (int k = 1; k <= K; k++) {
			int num = sc.nextInt();
			int d = sc.nextInt();
			boolean[] turn = new boolean[5];
			int[] dir = new int[5];
			if (num == 1) {
				turn[1] = true;
				dir[1] = d;
				if (a1[2] != a2[6]) {
					turn[2] = true;
					dir[2] = -d;
					if (a2[2] != a3[6]) {
						turn[3] = true;
						dir[3] = d;
						if (a3[2] != a4[6]) {
							turn[4] = true;
							dir[4] = -d;
						}
					}
				}
			} else if (num == 2) {
				turn[2] = true;
				dir[2] = d;
				if (a2[6] != a1[2]) {
					turn[1] = true;
					dir[1] = -d;
				}
				if (a2[2] != a3[6]) {
					turn[3] = true;
					dir[3] = -d;
					if (a3[2] != a4[6]) {
						turn[4] = true;
						dir[4] = d;
					}
				}
			} else if (num == 3) {
				turn[3] = true;
				dir[3] = d;
				if (a3[6] != a2[2]) {
					turn[2] = true;
					dir[2] = -d;
					if (a2[6] != a1[2]) {
						turn[1] = true;
						dir[1] = d;
					}
				}
				if (a3[2] != a4[6]) {
					turn[4] = true;
					dir[4] = -d;
				}
			} else if (num == 4) {
				turn[4] = true;
				dir[4] = d;
				if (a4[6] != a3[2]) {
					turn[3] = true;
					dir[3] = -d;
					if (a3[6] != a2[2]) {
						turn[2] = true;
						dir[2] = d;
						if (a2[6] != a1[2]) {
							turn[1] = true;
							dir[1] = -d;
						}
					}
				}
			}
			for (int p = 1; p <= 4; p++) {
				int[] temp = new int[8];
				if (p == 1 && turn[p]) {
					if (dir[1] == 1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+1)%8] = a1[idx];
						}
					} else if (dir[1] == -1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+7)%8] = a1[idx];
						}
					}
					a1 = temp;
				} else if (p == 2 && turn[p]) {
					if (dir[2] == 1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+1)%8] = a2[idx];
						}
					} else if (dir[2] == -1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+7)%8] = a2[idx];
						}
					}
					a2 = temp;
				} else if (p == 3 && turn[p]) {
					if (dir[3] == 1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+1)%8] = a3[idx];
						}
					} else if (dir[3] == -1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+7)%8] = a3[idx];
						}
					}
					a3 = temp;
				} else if (p == 4 && turn[p]) {
					if (dir[4] == 1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+1)%8] = a4[idx];
						}
					} else if (dir[4] == -1) {
						for (int idx = 0; idx < 8; idx++) {
							temp[(idx+7)%8] = a4[idx];
						}
					}
					a4 = temp;
				}
			}
 		}
		int result = 0;
		if (a1[0] == 1) result += 1;
		if (a2[0] == 1) result += 2;
		if (a3[0] == 1) result += 4;
		if (a4[0] == 1) result += 8;
		System.out.println(result);
	}
}
