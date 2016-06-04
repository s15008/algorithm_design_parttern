import static java.lang.System.out;
import java.util.Random;

public class TestSort {
    private static Sfmt sfmt = new Sfmt(0);
    private static final int LOOP_COUNT = 1_000_000;

    public static void main(String... args) {
        for (int i = 5; i < 50; i++) {
            int[] array = new int[i];
            long start, end;

            // 挿入ソート
            out.print("挿入ソート(" + i + ")");
            start = System.currentTimeMillis();
            for (int c = 0; c < LOOP_COUNT; c++) {
                generate(array);
                Sort.insertion(array);
            }
            end = System.currentTimeMillis();
            out.printf("経過時間: %.4f\t", (double)(end - start) / 1000);

            // クイックソート
            out.print("クイックソート(" + i + ")");
            start = System.currentTimeMillis();
            for (int c = 0; c < LOOP_COUNT; c++) {
                generate(array);
                Sort.quick(array, 0, array.length - 1);
            }
            end = System.currentTimeMillis();
            out.printf("経過時間: %.4f\t", (double)(end - start) / 1000);

            // ハイブリッドソート
            out.print("ハイブリッドソート(" + i + ")");
            start = System.currentTimeMillis();
            for (int c = 0; c < LOOP_COUNT; c++) {
                generate(array);
                Sort.hybrid(array, 0, array.length - 1);
            }
            end = System.currentTimeMillis();
            out.printf("経過時間: %.4f\t", (double)(end - start) / 1000);

            out.println();
        }
    }

    // 乱数の生成
    private static void generate(int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = sfmt.NextInt(10000);
        }
    }

    // ショータイム
    private static void ショータイム() {
        int[] aTest = new int[10];
        generate(aTest);

        out.print("SOURCE : ");
        for (int num : aTest) out.print(num + " ");
        out.println();

        new java.util.Scanner(System.in).nextLine();
        String[] ans = Sort.よぎソート(aTest);
        out.print("SORTED : ");
        for (String num : ans) out.print(num + " ");
        out.println();
    }
}

class Sort {

    // ハイブリッドソート
    public static void hybrid( int a[], int left, int right) {
        int mid = -1;
        int pivot = -1;

        // 配列の要素数が12個以下なら挿入ソートを行う
        if (a.length <= 12) {
            insertion(a);
            return;
        }

        // 配列の要素数が1個になるまで以下を繰り返す
        if (left < right) {
            mid = left + (right - left) / 2;

            // 左端、右端、中央の3つの値の真ん中を基準値とする
            if (a[left] < a[mid]) {
                if (a[right] < a[left]) pivot = a[left];
                else if (a[mid] < a[right]) pivot = a[mid];
                else pivot = a[right];
            }
            else {
                if (a[right] > a[left]) pivot = a[left];
                else if (a[mid] > a[right]) pivot = a[mid];
                else pivot = a[right];
            }

            // 基準値より小さい値と大きい値に配列の要素を分割
            int indexL = left;
            int indexR = right;
            while (true) {
                while (a[indexL] < pivot) indexL++;
                while (pivot < a[indexR]) indexR--;
                if (indexL >= indexR) break;
                int tmp = a[indexL];
                a[indexL] = a[indexR];
                a[indexR] = tmp;
                indexL++;
                indexR--;
            }

            quick(a, left, indexL - 1);
            quick(a, indexR + 1, right);
        }
    }

    // クイックソート
    public static void quick( int a[], int left, int right) {
        int mid = -1;
        int pivot = -1;

        // 配列の要素数が1個になるまで以下を繰り返す
        if (left < right) {
            mid = left + (right - left) / 2;

            // 左端、右端、中央の3つの値の真ん中を基準値とする
            if (a[left] < a[mid]) {
                if (a[right] < a[left]) pivot = a[left];
                else if (a[mid] < a[right]) pivot = a[mid];
                else pivot = a[right];
            }
            else {
                if (a[right] > a[left]) pivot = a[left];
                else if (a[mid] > a[right]) pivot = a[mid];
                else pivot = a[right];
            }

            // 基準値より小さい値と大きい値に配列の要素を分割
            int indexL = left;
            int indexR = right;
            while (true) {
                while (a[indexL] < pivot) indexL++;
                while (pivot < a[indexR]) indexR--;
                if (indexL >= indexR) break;
                int tmp = a[indexL];
                a[indexL] = a[indexR];
                a[indexR] = tmp;
                indexL++;
                indexR--;
            }

            quick(a, left, indexL - 1);
            quick(a, indexR + 1, right);
        }
    }

    // 挿入ソート
    public static void insertion(int a[]) {
        int idx;
        int pos;
        int tmp;

        for (idx = 1; idx < a.length; idx++) {
            pos = idx;
            while (pos > 0 && a[pos - 1] > a[pos]) {
                tmp = a[pos];
                a[pos] = a[pos - 1];
                a[pos - 1] = tmp;
                pos--;
            }
        }
    }

    /*
     * よぎソート
     * 全てをよぎにする
     */
    public static String[] よぎソート(int げんぶ[]) {
        String[] よぎ = new String[げんぶ.length];
        for (int よ = 0; よ < よぎ.length; よ++) {
            よぎ[よ] = "よぎ";
        }

        return よぎ;
    }
}

/*
 * Sfmtクラス
 * 擬似乱数生成器
 */
class Sfmt
{
    int index;
    int[]x=new int[624]; /* 状態テーブル */

    void gen_rand_all() {
        int a=0,b=488,c=616,d=620,y; int[]p=x;

        do {
            y=p[a+3]^(p[a+3]<<8)^(p[a+2]>>>24)^((p[b+3]>>>11)&0xbffffff6);
            p[a+3]=y^(p[c+3]>>>8)^(p[d+3]<<18);
            y=p[a+2]^(p[a+2]<<8)^(p[a+1]>>>24)^((p[b+2]>>>11)&0xbffaffff);
            p[a+2]=y^((p[c+2]>>>8)|(p[c+3]<<24))^(p[d+2]<<18);
            y=p[a+1]^(p[a+1]<<8)^(p[a]>>>24)^((p[b+1]>>>11)&0xddfecb7f);
            p[a+1]=y^((p[c+1]>>>8)|(p[c+2]<<24))^(p[d+1]<<18);
            y=p[a]^(p[a]<<8)^((p[b]>>>11)&0xdfffffef);
            p[a]=y^((p[c]>>>8)|(p[c+1]<<24))^(p[d]<<18);
            c=d; d=a; a+=4; b+=4;
            if (b==624) b=0;
        } while (a!=624);
    }

    void period_certification() {
        int work,inner=0,i,j;
        int[]parity={ 0x00000001,0x00000000,0x00000000,0x13c9e684 };

        index=624;
        for (i=0;i<4;i++) inner^=x[i]&parity[i];
        for (i=16;i>0;i>>>=1) inner^=inner>>>i;
        inner&=1;
        if (inner==1) return;
        for (i=0;i<4;i++) for (j=0,work=1;j<32;j++,work<<=1)
            if ((work&parity[i])!=0) { x[i]^=work; return; }
    }

    /* 整数の種 s による初期化 */
    public synchronized void InitMt(int s) {
        x[0]=s;
        for (int p=1;p<624;p++) x[p]=s=1812433253*(s^(s>>>30))+p;
        period_certification();
    }

    Sfmt(int s) { InitMt(s); }

    /* 32ビット符号あり整数の乱数 */
    public synchronized int NextMt() {
        if (index==624) { gen_rand_all(); index=0; }
        return x[index++];
    }

    /* 0以上 n 未満の整数乱数 */
    public int NextInt(int n) {
        double z=NextMt();
        if (z<0) z+=4294967296.0;
        return(int)(n*(1.0/4294967296.0)*z);
    }

    /* 0以上1未満の乱数(53bit精度) */
    public double NextUnif() {
        double z=NextMt()>>>11,y=NextMt();
        if (y<0) y+=4294967296.0;
        return(y*2097152.0+z)*(1.0/9007199254740992.0);
    }
}

