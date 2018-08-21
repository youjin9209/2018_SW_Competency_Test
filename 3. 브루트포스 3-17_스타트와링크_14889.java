/*
문제 링크 
https://www.acmicpc.net/problem/14889

해결
N 을 N/2로 2팀으로 나누려면 
-> 0 을 N/2개, 1을 N/2개 넣어서 모든 순열 돌리기 ! 
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class bf_3_17_스타트와링크_14889 {
	public static boolean next_permutation (int[] team) {
		// 7 2 3 6 5 4 1
		// 1) 가장 긴 감소수열의 시작 index 찾기 
		int i = team.length - 1;
		while (i > 0 && team[i-1] >= team[i]) {
			i--;
		}
		if (i <= 0)
			return false;
		// 2) a[i-1] < a[j] 인 가장 큰 j 찾기 
		int j = team.length - 1;
		while (team[i-1] >= team[j]) {
			j--;
		}
		// 3) swap (team[i-1], team[j])
		int temp = team[i-1];
		team[i-1] = team[j];
		team[j] = temp;
		
		// 4) 6531 -> 1356 
		int k = team.length - 1;
		while (i < k) {
			temp = team[i];
			team[i] = team[k];
			team[k] = temp;
			k--;
			i++;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] a = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		// 1) 0, 1 로 두 팀나누기 
		int[] team = new int[N];
		for (int i = 0; i < N/2; i++) {
			team[i] = 1;
		}
		Arrays.sort(team);
		int min = Integer.MAX_VALUE;
		do {
			// 2) 팀 나누기 
			ArrayList<Integer> teamA = new ArrayList<Integer>();
			ArrayList<Integer> teamB = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				if (team[i] == 0) {
					teamA.add(i);
				} else {
					teamB.add(i);
				}
			}
			// 3) 팀 쪼갠걸로 능력치 구하기  
			int aSum = 0;
			int bSum = 0;
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					if (i != j) {
						aSum += a[teamA.get(i)][teamA.get(j)];
						bSum += a[teamB.get(i)][teamB.get(j)];
					}
				}
			}
			// 4) 능력치 차 최소값 구하기 
			int dif = aSum - bSum;
			if (dif < 0)
				dif = -dif;
			if (min > dif)
				min = dif;
		} while (next_permutation(team));
		System.out.println(min);
	}
}
