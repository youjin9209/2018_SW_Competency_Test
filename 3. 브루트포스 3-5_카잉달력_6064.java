/*
x 를 이용해서 모든 해를 고려하지 않고, i X M + x 형태만 조사하면 된다.
*/
import java.util.Scanner;
import java.util.*;
import java.io.*;
public class bf_3_5_카잉달력_6064 {
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(bf.readLine());
        while (t-- > 0) {
            String[] line = bf.readLine().split(" ");
            int M = Integer.valueOf(line[0]);
            int N = Integer.valueOf(line[1]);
            int x = Integer.valueOf(line[2])-1;
            int y = Integer.valueOf(line[3])-1;
            boolean ok = false;
            for (int k = x; k < (M*N); k += M) {
                if (k%N == y) {
                    System.out.println(k+1);
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.println(-1);
            }
        }
    }
}
