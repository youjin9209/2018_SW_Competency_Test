import java.util.Scanner;

public class Solution_4013_특이한자석 {
	static int[] a1 = new int[8];
	static int[] a2 = new int[8];
	static int[] a3 = new int[8];
	static int[] a4 = new int[8];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int K = sc.nextInt();
			for (int i = 0; i < 8; i++) 
				a1[i] = sc.nextInt();
			for (int i = 0; i < 8; i++)
				a2[i] = sc.nextInt();
			for (int i = 0; i < 8; i++)
				a3[i] = sc.nextInt();
			for (int i = 0; i < 8; i++)
				a4[i] = sc.nextInt();
			for (int k = 1; k <= K; k++) {
				int num = sc.nextInt();
				int c = sc.nextInt();
				boolean[] turn = new boolean[5]; // 돌릴 것인지 
				int[] dir = new int[5]; // 어느방향인지 
				// 1) 돌리는 거 기점으로 인접한것끼리 N-S인지 boolean[] 에 check 
				if (num == 1) {
					turn[1] = true;
					dir[1] = c;
					// 1 <-> 2 
					if (a1[2] != a2[6]) {
						turn[1] = true;
						turn[2] = true;
						dir[1] = c;
						dir[2] = -c;
						// 2 <-> 3 
						if (a2[2] != a3[6]) {
							turn[3] = true;
							dir[3] = c;
							// 3 <-> 4 
							if (a3[2] != a4[6]) {
								turn[4] = true;
								dir[4] = -c;
							}
						}
					}
				} else if (num == 2) {
					turn[2] = true;
					dir[2] = c;
					// 2 <-> 1
					if (a2[6] != a1[2]) {
						turn[2] = true;
						turn[1] = true;
						dir[2] = c;
						dir[1] = -c;
					}
					// 2 <-> 3
					if (a2[2] != a3[6]) {
						turn[2] = true;
						turn[3] = true;
						dir[2] = c;
						dir[3] = -c;
						if (a3[2] != a4[6]) {
							turn[4] = true;
							dir[4] = c;
						}
					}
				} else if (num == 3) {
					turn[3] = true;
					dir[3] = c;
					// 3 <-> 2 
					if (a3[6] != a2[2]) {
						turn[3] = true;
						turn[2] = true;
						dir[3] = c;
						dir[2] = -c;
						if (a2[6] != a1[2]) {
							turn[1] = true;
							dir[1] = c;
						}
					}
					// 3 <-> 4 
					if (a3[2] != a4[6]) {
						turn[3] = true;
						turn[4] = true;
						dir[3] = c;
						dir[4] = -c;
					}
				} else if (num == 4) {
					turn[4] = true;
					dir[4] = c;
					// 4 <-> 3 
					if (a4[6] != a3[2]) {
						turn[4] = true;
						turn[3] = true;
						dir[4] = c;
						dir[3] = -c;
						// 3 <-> 2 
						if (a3[6] != a2[2]) {
							turn[2] = true;
							dir[2] = c;
							// 2 <-> 1 
							if (a2[6] != a1[2]) {
								turn[1] = true;
								dir[1] = -c;
							}
						}
					}
				}
				// 2) 시계 : 1 , 반시계 : -1 방향 돌리기 
				for (int p = 1; p <= 4; p++) {
					if (turn[p]) {
						int[] temp = new int[8];
						if (dir[p] == 1) { // 시계 
							if (p == 1) {
								for (int i = 0; i < 8; i++) {
									temp[(i+1)%8] = a1[i];
								}
							} else if (p == 2) {
								for (int i = 0; i < 8; i++) {
									temp[(i+1)%8] = a2[i];
								}
							} else if (p == 3) {
								for (int i = 0; i < 8; i++) {
									temp[(i+1)%8] = a3[i];
								}
							} else if (p == 4) {
								for (int i = 0; i < 8; i++) {
									temp[(i+1)%8] = a4[i];
								}
							}
						} else if (dir[p] == -1) { // 반시계 
							if (p == 1) {
								for (int i = 0; i < 8; i++) {
									temp[(i+7)%8] = a1[i];
								}
							} else if (p == 2) {
								for (int i = 0; i < 8; i++) {
									temp[(i+7)%8] = a2[i];
								}
							} else if (p == 3) {
								for (int i = 0; i < 8; i++) {
									temp[(i+7)%8] = a3[i];
								}
							} else if (p == 4) {
								for (int i = 0; i < 8; i++) {
									temp[(i+7)%8] = a4[i];
								}
							}
						}
						if (p == 1) a1 = temp;
						else if (p == 2) a2 = temp;
						else if (p == 3) a3 = temp;
						else if (p == 4) a4 = temp;
					}
				}
			}
			int result = 0;
			if (a1[0] == 0) result += 0;
			else if (a1[0] == 1) result += 1;
			if (a2[0] == 0) result += 0;
			else if (a2[0] == 1) result += 2;
			if (a3[0] == 0) result += 0;
			else if (a3[0] == 1) result += 4;
			if (a4[0] == 0) result += 0;
			else if (a4[0] == 1) result += 8;
			System.out.println("#"+test_case+" "+result);
		}
	}
}
