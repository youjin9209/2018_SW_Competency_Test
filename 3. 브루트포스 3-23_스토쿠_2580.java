/*
문제 링크 
https://www.acmicpc.net/problem/2580
*/
import java.util.Scanner;

public class bf_3_23_스토쿠_2580 {
	static int n = 9;
	public static int square(int x, int y){
		return (x/3)*3 + (y/3);
	}
	public static boolean go(int[][] a, boolean[][][] c, int z) {
		if (z == 81) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(a[i][j] + " ");
				}
				System.out.println();
			}
			return true;
		}
		// 좌표 
		int x = z / n;
		int y = z % n;
		if (a[x][y] != 0) // 숫자일 경우 
			return go(a, c, z+1);
		else { //빈칸일 경우 
			for (int i = 1; i <= 9; i++) { // 숫자 집어 넣어줘야함 
				if (!c[0][x][i] && !c[1][y][i] && !c[2][square(x, y)][i]) {
					c[0][x][i] = c[1][y][i] = c[2][square(x, y)][i] = true;
					a[x][y] = i;
					if (go(a, c, z+1))
						return true;
					// 어긋나서 다시 돌아왔을 경우  초기화 
					c[0][x][i] = c[1][y][i] = c[2][square(x, y)][i] = false;
					a[x][y] = 0;
				}
				
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] a = new int[n][n];
		boolean[][][] c = new boolean[3][n][10]; // 3: 행 , 열 , 정사각형 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
				if (a[i][j] != 0) {
					c[0][i][a[i][j]] = true; // i 행에 숫자 a[i][j] 가 있을경우 
					c[1][j][a[i][j]] = true; // j 행에 숫자 a[i][j] 가 있을경우 
					c[2][square(i, j)][a[i][j]] = true; // square(i ,j) 에 숫자 a[i][j] 가 있을 경우 
				}
			}
		}
		go(a, c, 0);
	}
}
