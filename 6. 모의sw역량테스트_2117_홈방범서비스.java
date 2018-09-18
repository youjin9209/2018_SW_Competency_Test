import java.util.Scanner;

public class Solution_2117_홈방범서비스 {
	static int N;
	static int M;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int max = 0;
			for (int K = 1; K <= N + 20; K++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int cnt = 0;
						// 별 영역에 "1" 있는 지 count 
						for (int k = 1; k <= K; k++) {
							// 빈칸 
							for (int p = 1; p <= K-k; p++) {
								System.out.print(" ");
							}
							//별 
							for (int p = 1; p <= 2*k - 1; p++) {
								System.out.print("*");
								if (map[i][j] == 1) {
									cnt++;
								}
							}
							System.out.println();
						}
						for (int k = K-1; k >= 1; k--) {
							// 빈칸 
							for (int p = 1; p <= K-k; p++) {
								System.out.print(" ");
							}
							//별 
							for (int p = 1; p <= 2*k - 1; p++) {
								System.out.print("*");
								if (map[i][j] == 1) {
									cnt++;
								}
							}
							System.out.println();
						}
						int benefit = M*cnt - (K*K + (K-1)*(K-1));
						if (benefit > 0) {
							if (max < cnt)
								max = cnt;
						}
					}
				}
			}
			System.out.println("#"+test_case+" "+max);
		}
	}
}
