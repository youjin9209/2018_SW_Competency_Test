import java.util.Arrays;
import java.util.Scanner;
/*
 1) 최소 한 개의 모음과 최소 두 개의 자음
 2) 증가수열 
 3) go (password, i)
password : 현재까지 만든 암호 
i : 사용할지 말지 결정해야하는 인덱스 
 */
public class Main {
	static int L;
	static int C;
	static char[] a;
	public static boolean check(String str) {
		int ja = 0;
		int mo = 0;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char word = str.charAt(i);
			if (word == 'a' || word == 'e' || word == 'i' || word == 'o' || word == 'u') {
				mo++;
			} else {
				ja++;
			}
		}
		return ja >= 2 && mo >= 1;
	}
	public static void go(int index, String str) {
		if (str.length() == L) {
			if (check(str)) {
				System.out.println(str);
			}
			return;
		}
		if (index >= C) return;
		// index 상태에서 
		// 1) index + 1 의 단어를 추가 한다 
		go(index + 1, str+a[index]);
		// 2) index + 1 의 단어를 추가 안 한다 
		go(index + 1, str);
	}
    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();
        a = new char[C];
        String[] line = sc.nextLine().split(" ");
        for (int i = 0; i < C; i++) {
        	a[i] = line[i].charAt(0);
        }
        Arrays.sort(a);
        go(0, "");
    }
}
