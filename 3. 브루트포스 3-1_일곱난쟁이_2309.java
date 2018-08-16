/*
문제 link
https://www.acmicpc.net/problem/2309
핵심 : 찾자마자 끝내버려야함 !
*/
import java.util.Arrays;
import java.util.Scanner;

public class bf_3_1_일곱난쟁이_2309 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[9];
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
		}
		for (int i = 0; i <= 7; i++) {
			for (int j = i+1; j < 9; j++) {
				if (sum - arr[i] - arr[j] == 100) {
					arr[i] = 0;
					arr[j] = 0;
					Arrays.sort(arr);
					for (int k = 0; k < 9; k++) {
						if (arr[k] != 0)
							System.out.println(arr[k]);
					}
					System.exit(0); // 찾자마자 끝내버려야함 
				}
			}
		}
	}
}
