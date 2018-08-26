/*
문제 링크 
https://www.acmicpc.net/problem/9663

calc(row) : row행에 퀸을 어디에 놓을지 결정 
해당위치에 퀸을 놓는게 맞는지 check -> 통과하면 영역표시 -> 다음걸로 이동 
 
check(row, col) : col, / 대각선, \ 대각선 맞는지 체크 
*/
import java.util.Scanner;

public class bf_3_22_NQueen_9663 {
	static int N;
	static boolean[] check_col = new boolean[15]; 
	static boolean[] check_dig = new boolean[40]; // / 대각선
	static boolean[] check_dig2 = new boolean[40]; // \ 대각선
	static boolean check(int row, int col) {
		if (check_col[col]) // col
			return false;
		if (check_dig[row+col]) // / 대각선
			return false;
		if (check_dig2[row-col+N]) // \ 대각선 
			return false;
		return true;
	}
	static int calc(int row) {
		if (row == N) // 도달 
			return 1;
		int cnt = 0;
		for (int col = 0; col < N; col++) {
			if (check(row, col)) { // 체크해서 통과하면 영역표시 
				check_col[col] = true;
				check_dig[row+col] = true;
				check_dig2[row-col+N] = true;
				cnt += calc(row + 1); // 다음 row 로 넘어간다  
				// 반복문 다돌고 이전 row로 돌아와서 해당 col 초기화 
				check_col[col] = false;
				check_dig[row+col] = false;
				check_dig2[row-col+N] = false;
				// 이전 row 부터 다시 시작한다 
			}
		}
		return cnt;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		System.out.println(calc(0));
	}
}
