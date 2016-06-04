import static java.lang.System.out;

public class TestBitCount {
    private static Sfmt rand = new Sfmt(256);
    private static int LOOP_COUNT = 1_000_000;

    public static void main(String... args) {
        long start;
        long end;

        //* mineA
        out.print("mineA");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_mineA(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*/

        //* mineB
        out.print("mineB");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_mineB(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*
 
        //* A
        out.print("A");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_A(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*/ 

        //* B
        out.print("B");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_B(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*/ 

        //* C
        out.print("C");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_C(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*/ 

        //* D
        out.print("D");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_D(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*/

        //* E
        out.print("E");
        start = System.currentTimeMillis();
        for (int i = 0; i < LOOP_COUNT; i++) {
            for (int j = 0; j < 256; j++) {
                int data = rand.NextInt(256);
                bitCount_E(data);
            }
        }
        end = System.currentTimeMillis();
        out.printf("経過時間: %.4f\n", (double)(end - start) / 1000);
        //*/
    }

    // ver.A
    // forで文字列回して要素単位で判別
    // ベンチウォーマー
    private static int bitCount_mineA(int data) {
        int count = 0;
        String sData = Integer.toBinaryString(data);
        for (int i = 0; i < sData.length(); i++) {
            if (sData.charAt(i) == '1') count++;
        }
        return count;
    }

    // ver.B
    // シフト演算で削っていって1ケタ目が1かどうかを判定
    // スタメン
    private static int bitCount_mineB(int data) {
        int count = 0;
        while (data != 0x0) {
            if((data & 0x1) == 0x1) count++;
            data = data >>> 1;
        }
        return count;
    }

    /// 参考書のサンプル
    // A 2で割った余りを集計する
    private static int bitCount_A(int data) {
        int count = 0;
        while (data != 0) {
            count += data % 2;
            data /= 2;
        }
        return count;
    }

    // B 1とAND演算した結果を集計する
    private static int bitCount_B(int data) {
        int count = 0;
        while (data != 0) {
            count += data & 1;
            data = data >>> 1;
        }
        return count;
    }

    // C 1を引いた値とAND演算した回数を求める
    private static int bitCount_C(int data) {
        int count = 0;
        while (data != 0) {
            data &= (data - 1);
            count++;
        }
        return count;
    }

    // D ループを使わずに1の個数を数える
    private static int bitCount_D(int data) {
        data = data - ((data >>> 1) & 0x55555555);
        data = (data & 0x33333333) + ((data >>> 2) & 0x33333333);
        data = (data + (data >>> 4)) & 0x0f0f0f0f;
        data = data + (data >>> 8);
        data = data + (data >>> 16);

        return data & 0x3f;
    }

    // E 最も効率的なアルゴリズムは
    private static int memoCount[] = new int[256];
    private static int bitCount_E(int data) {
        int i = 0;
        if (memoCount[1] == 0) {
            for (i = 0; i < 256; i++) {
                memoCount[i] = memoCount[i >> 1] + (i & 1);
            }
        }
        return memoCount[data];
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
