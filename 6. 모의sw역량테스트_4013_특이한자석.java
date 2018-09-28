import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1) initialize 
			int[][] arr = new int[5][8];
			int K = sc.nextInt();
			for (int i = 1; i <= 4; i++) {
				for (int j = 0; j <= 7; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			// 2) set flag : turn, direction 
			for (int k = 1; k <= K; k++) {
				boolean[] turn = new boolean[5];
				int[] dir = new int[5];
				int num = sc.nextInt();
				int d = sc.nextInt();
				if (num == 1) {
					turn[num] = true;
					dir[num]  = d;
					// 2-1) check adjacent node 
					if (arr[1][2] != arr[2][6]) {
						turn[2] = true;
						dir[2] = -d;
						if (arr[2][2] != arr[3][6]) { 
							turn[3] = true;
							dir[3] = d;
							if (arr[3][2] != arr[4][6]) {
								turn[4] = true;
								dir[4] = -d;
							}
						}
					}
				} else if (num == 2) {
					turn[num] = true;
					dir[num]  = d;
					if (arr[2][6] != arr[1][2]) {
						turn[1] = true;
						dir[1] = -d;
					}
					if (arr[2][2] != arr[3][6]) {
						turn[3] = true;
						dir[3] = -d;
						if (arr[3][2] != arr[4][6]) {
							turn[4] = true;
							dir[4] = d;
						}
					}
				} else if (num == 3) {
					turn[num] = true;
					dir[num]  = d;
					if (arr[3][2] != arr[4][6]) {
						turn[4] = true;
						dir[4] = -d;
					}
					if (arr[3][6] != arr[2][2]) {
						turn[2] = true;
						dir[2] = -d;
						if (arr[2][6] != arr[1][2]) {
							turn[1] = true;
							dir[1] = d;
						}
					}
				} else if (num == 4) {
					turn[num] = true;
					dir[num] = d;
					if (arr[4][6] != arr[3][2]) {
						turn[3] = true;
						dir[3] = -d;
						if (arr[3][6] != arr[2][2]) {
							turn[2] = true;
							dir[2] = d;
							if (arr[2][6] != arr[1][2]) {
								turn[1] = true;
								dir[1] = -d;
							}
						}
					}
				}
				// 3) process 
				int[][] temp = new int[5][8];
				for (int i = 1; i <= 4; i++) {
					if (turn[i]) {
						if (dir[i] == 1) { // clockwise 
							for (int j = 0; j <= 7; j++) {
								temp[i][(j+1)%8] = arr[i][j];
							}
						} else if (dir[i] == -1) { // counter-clockwise 
							for (int j = 0; j <= 7; j++) {
								temp[i][(j+7)%8] = arr[i][j];
							}
						}
						arr[i] = temp[i];
 					}
				}
			}
			// 4) get summation 
			int sum = 0;
			for (int i = 1; i <= 4; i++) {
				if (i == 1 && arr[i][0] == 1)
					sum += 1;
				if (i == 2 && arr[i][0] == 1)
					sum += 2;
				if (i == 3 && arr[i][0] == 1)
					sum += 4;
				if (i == 4 && arr[i][0] == 1)
					sum += 8;
			}
			System.out.println("#"+test_case+" "+sum);
		}
	}
}
