import java.util.Scanner;

public class Solution_2117_홈방범서비스 {
	static int K = 20;
	static int N;
	static int M;
	static int[][] map;
	static int[] price = new int[K+2]; // 운영 비용
	// 2) x, y 좌표에서 k 크기의 마름모 영역으로 방범 서비스를 손해보지 않는 집의 수  
	public static int check(int x, int y, int k) {
		int cnt = 0;
		int nexty;
		int endp;
		// 2-1) k 크기의 마름모 영역 위, 아래 확인 
		for (int i = 1; i <= k-1; i++) {
			int up_nextx = x - k + i;
			int down_nextx = x + k - i;
			
			endp = i*2 - 1;
			nexty = y - endp/2;
			for (int j = 0; j < endp; j++) {
				// 위 
				if (up_nextx >= 0 && up_nextx < N && nexty + j  >= 0 && nexty + j < N ) {
					if (map[up_nextx][nexty + j] == 1) {
						cnt++;
					}
				}
				// 아래 
				if (down_nextx >= 0 && down_nextx < N && nexty + j  >= 0 && nexty + j < N ) {
					if (map[down_nextx][nexty + j] == 1) {
						cnt++;
					}
				}
			}
		}
		// 2-2) k 크기의 마름모 영역 가운데 확인 
		endp = k*2 - 1;
		nexty = y - endp/2;
		for (int i = 0; i < endp; i++) {
			if (x >= 0 && x < N && nexty + i >= 0 && nexty + i < N) {
				if (map[x][nexty+i] == 1) {
					cnt++;
				}
			}
		}
		// 2-3) 손해를 보지 않을 때, 홈 방범 서비스를 제공받는 집의 수 리턴 
		if (M*cnt - price[k] >= 0)
			return cnt;
		return 0;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		// 1) 운영비용 계산 
		price[1] = 1;
		for (int i = 2; i < K + 1; i++)
			price[i] = i*i + (i-1)*(i-1);
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= N+1; k++) {
						ans = Math.max(ans, check(i, j, k));
					}
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
