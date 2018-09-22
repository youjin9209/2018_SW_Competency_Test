import java.util.Scanner;

public class Solution_4014_활주로건설 {
	static int N;
	static int X;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			X = sc.nextInt();
			map = new int[N][N];
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 1) row check : 높이 차이 1 && 낮은 곳이 동일하게 경사로 길이 만큼 만족 
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				for (int j = 0; j < N; j++) {
					// 내리막 
					if (j+1 < N && map[i][j] > map[i][j+1]) {
						flag = false;
						// 1 차이 
						if (map[i][j] == map[i][j+1] + 1) {
							
						}
					}
					// 오르막 
					if (j+1 < N && map[i][j] < map[i][j+1]) {
						flag = false;
						// 1 차이 
						if (map[i][j] + 1 == map[i][j+1]) {
							
						}
					}
					// 길이가 다 동일 
					if (flag) {
						result++;
					}
				}
			}
			// 2) col check 
			
			System.out.println("#"+test_case+" "+result);
		}
	}
}
