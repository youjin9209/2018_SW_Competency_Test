import java.util.Scanner;
class data_type {
	int status; // 0 : empty, 1 : deactivate, 2 : activate, 3 :dead 
	int LP; // Life Point 
	int HP; // 0 : dead, if status == deactivate -> HP++ , if status == activate ->  HP-- 
}
class Solution_5653_줄기세포배양 {
	static int MAXL = 352;
	static int N;
	static int M;
	static int K;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static data_type[][][] map = new data_type[2][MAXL][MAXL];
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			// 1) initialize 
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			for (int i = 0; i < N + K + 2; i++) {
				for (int j = 0; j < M + K + 2; j++) {
					map[0][i][j] = new data_type(); // current 
					map[1][i][j] = new data_type(); // next 
					map[0][i][j].status = 0;
					map[1][i][j].status = 0;
				}
			}
			for (int i = 0 + K/2 + 1; i < N + K/2 + 1; i++) {
				for (int j = 0 + K/2 + 1; j < M + K/2 + 1; j++) {
					map[0][i][j].LP = sc.nextInt();
					if (map[0][i][j].LP > 0) {
						map[0][i][j].status = 1;
						map[0][i][j].HP = 0;
					}
				}
			}
			// MAX RANGE within K hours 
			N = N + K + 2;
			M = M + K + 2;
			// 2) process 
			int CurMap = 0;
			for (int k = 0; k <= K; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						// 2-1) 죽은 세포 일 경우 
						if (map[CurMap][i][j].status == 3) {
							// 아무동작도 하지 않음 : 현재 상태를 다음턴에 넘김 
							map[1-CurMap][i][j].status = map[CurMap][i][j].status;
							continue;
						}
						// 2-2) 비활성 세포 일 경우 
						else if (map[CurMap][i][j].status == 1) {
							// HP++ 
							map[1-CurMap][i][j].HP = map[CurMap][i][j].HP + 1;
							// LP 는 그대로 
							map[1-CurMap][i][j].LP = map[CurMap][i][j].LP;
							// 비활성 시간이 LP 만큼 지났을 때 
							if (map[1-CurMap][i][j].HP == map[1-CurMap][i][j].LP) {
								// 활성 상태로 변경 
								map[1-CurMap][i][j].status = 2;
							} else {
								map[1-CurMap][i][j].status = 1;
							}
						}
						// 2-3) 활성 세포 일 경우 
						else if (map[CurMap][i][j].status == 2) {
							// 활성 상태가 된 후 첫시간일 경우 
							if (map[CurMap][i][j].HP == map[CurMap][i][j].LP) {
								// 현재 위치에서 4방향 탐색 
								for (int idx = 0; idx < 4; idx++) {
									int ni = i + dx[idx];
									int nj = j + dy[idx]; 
									// 줄기세포가 번식하는 방향이 비어있을 경우 
									if (map[CurMap][ni][nj].status == 0) {
										// 하나의 줄기세포가 번식하려 할 때 
										if (map[1-CurMap][ni][nj].status == 0) {
											// 해당 줄기 세포가 셀을 차지 
											map[1-CurMap][ni][nj].status = 1;
											map[1-CurMap][ni][nj].LP = map[CurMap][i][j].LP;
											map[1-CurMap][ni][nj].HP = 0;
										}
										// 두개 이상의 줄기세포가 번식하려 할 때 
										else if (map[1-CurMap][ni][nj].status == 1) {
											// 생명력이 높은 줄기세포가 셀을 차지 
											if (map[1-CurMap][ni][nj].LP < map[CurMap][ni][nj].LP) {
												map[1-CurMap][ni][nj].LP = map[CurMap][ni][nj].LP;
											}
										}
									}
								}
							}
							// 활성시간 감소 
							map[1-CurMap][i][j].HP = map[CurMap][i][j].HP - 1;
							// 활성시간이 LP 만큼 지났을 경우 
							if (map[1-CurMap][i][j].HP == 0)
								map[1-CurMap][i][j].status = 3; // dead 
							else 
								map[1-CurMap][i][j].status = 2;
						}
					}
				}
				// 다음 상태를 저장해놓은 map을 현재 map으로 변경 
				CurMap = 1 - CurMap;
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[CurMap][i][j].status == 1 || map[CurMap][i][j].status == 2) {
						ans++;
					}
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
