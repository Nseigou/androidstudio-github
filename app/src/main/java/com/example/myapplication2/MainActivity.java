package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    public String process="";     //打ち込んだ数字（記号）列を格納
    public String preans = "";     //「ANS」ボタン用。１つ前の計算の答えを保存する。
    int numA=0;                    //特殊計算に切り替えるための変数
    public int change = 0;         //リスト作成用の変数
    double aA=0;                   //double型のただの変数
    double bA=0;                   //double型のただの変数
    int nA=0;                      //int型のただの変数
    int aI = 0;                     //int型のただの変数
    int bI = 0;                     //int型のただの変数
    int cI = 1985416576;            //int型の大きな変数
    String aS = "";                 //String型の変数
    List<Double> data = new ArrayList<>();        //特殊計算に用いるリストの用意
    List<String> data1 = new ArrayList<>();       //特殊計算に用いるリストの用意



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextViewの参照

        TextView textView3 = findViewById(R.id.textView3);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        TextView textView9 = findViewById(R.id.textView9);
        TextView textView10 = findViewById(R.id.textView10);


        //Buttonの参照とButtonを押したときのアクション
        //表(0~9,.,;,-,×,÷,=,Ans) 　裏(A~N)　　常に表示（CA,▶,切替,次へ,決定）

        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(v -> {
            process = process + "0";         //0をTextView3に表示
            textView3.setText(process);
        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> {
            process = process + "1";         //1をTextView3に表示
            textView3.setText(process);
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            process = process + "2";         //2をTextView3に表示
            textView3.setText(process);
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            process = process + "3";          //3をTextView3に表示
            textView3.setText(process);
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> {
            process = process + "4";         //4をTextView3に表示
            textView3.setText(process);
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> {
            process = process + "5";          //5をTextView3に表示
            textView3.setText(process);
        });
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> {
            process = process + "6";             //6をTextView3に表示
            textView3.setText(process);
        });
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            process = process + "7";            //7をTextView3に表示
            textView3.setText(process);
        });
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> {
            process = process + "8";                //8をTextView3に表示
            textView3.setText(process);
        });
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(v -> {
            process = process + "9";                //9をTextView3に表示
            textView3.setText(process);
        });
        Button buttondot = findViewById(R.id.button30);
        buttondot.setOnClickListener(v -> {
            process = process + ".";                  //.をTextView3に表示
            textView3.setText(process);
        });

        Button buttontasu = findViewById(R.id.button14);
        buttontasu.setOnClickListener(v -> {
            process = process + "+";
            textView3.setText(process);                 //+をTextView3に表示
        });
        Button buttonminus = findViewById(R.id.button15);
        buttonminus.setOnClickListener(v -> {
            process = process + "-";                    //-をTextView3に表示
            textView3.setText(process);
        });
        Button buttonkakeru = findViewById(R.id.button16);
        buttonkakeru.setOnClickListener(v -> {
            process = process + "×";                      //×をTextView3に表示
            textView3.setText(process);
        });
        Button buttonwaru = findViewById(R.id.button18);
        buttonwaru.setOnClickListener(v -> {
            process = process + "÷";                       //÷をTextView3に表示
            textView3.setText(process);
        });
        Button buttonCA = findViewById(R.id.button17);
        buttonCA.setOnClickListener(v -> {                  //「CA」を押したら初期状態にリセット
            process = "";
            preans = "";
            numA = 0;
            double aA=0;
            double bA=0;
            int nA=0;
            aI =0;
            bI=0;
            cI = 1985416576;
            textView3.setText(process);
            textView5.setText("");
            textView6.setText("情報電卓");
            textView6.setBackgroundColor(Color.WHITE);
            textView9.setText("式");
            textView10.setText("答え");
        });

        Button buttonbackspace = findViewById(R.id.button20);     //最後に入力した文字（記号）を消去
        buttonbackspace.setOnClickListener(v -> {
            if (process.length() > 0) {
                process = process.substring(0, process.length() - 1);
                textView3.setText(process);
            } else {
            }
        });

        Button buttonequal = findViewById(R.id.button13);        //入力されている（processが持っている）文字列で四則演算を行う
        buttonequal.setOnClickListener(v -> {
            double answer = Sisokuenzan(process.replace("preans", preans));
            if(answer==798751598651.71658756189579186995758279948542797){
                textView5.setText("0で割ることは出来ません");  //0除算のエラー
                process = "";
                preans = "";
                numA = 0;
                textView3.setText(process);
            }else {
                preans = String.valueOf(answer);
                textView5.setText(String.valueOf(answer));
                process = "";
            }
        });

        Button buttonans = findViewById(R.id.buttonans);     //一つ前の計算結果を保持しておく
        buttonans.setOnClickListener(v -> {
            process += "preans";
            textView3.setText(preans);
            textView5.setText("");
        });

        Button buttonchange = findViewById(R.id.button22);      //表のボタンと裏のボタンを切り替える
        buttonchange.setOnClickListener(v -> {
            change += 1;
            UpdateButtonVisibility();
        });

        Button buttonA = findViewById(R.id.buttonA);         //等差数列の和を求める
        buttonA.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView6.setText("等差数列の和：初項を入力して「決定」を押して下さい");  //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW);   //色づけ
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                numA = 5;
            }else{
            }
        });

        Button buttonB = findViewById(R.id.buttonB);              //等比数列の和を求める
        buttonB.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView6.setText("等比数列の和：初項を入力して「決定」を押して下さい");       //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW);
                numA = 1;                                                           //この変数を動かすことで行いたい特殊計算を実行させている
            }else{
            }
        });



        Button buttonC = findViewById(R.id.buttonC);       //階乗を求める
        buttonC.setOnClickListener(v -> {
            if(numA ==0){
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setBackgroundColor(Color.YELLOW);
                numA = 8;
                textView6.setText("階乗：14以下の自然数を入力して「決定」を押してください下さい");      //ここからは「決定」ボタンに委ねる
            }else{
            }
        });


        Button buttonD = findViewById(R.id.buttonD);       //コンビネーションを求める
        buttonD.setOnClickListener(v -> {
            if(numA ==0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setBackgroundColor(Color.YELLOW);
                numA = 9;
                textView6.setText("コンビネーション：aCbの「a」を１４以下の自然数で入力して「決定」を押してください下さい");      //ここからは「決定」ボタンに委ねる
            }

        });
        Button buttonE = findViewById(R.id.buttonE);       //平均を求める
        buttonE.setOnClickListener(v -> {
            if(numA==0){
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView6.setBackgroundColor(Color.YELLOW);
                numA = 11;
                data = new ArrayList<>();
                textView6.setText("平均：数値を１つ入力して「次へ」を押して下さい\n「決定」で平均を求めます。");      //ここからは「次へ」ボタンに委ねる
                textView10.setText("data");
            }
        });

        Button buttonF = findViewById(R.id.buttonF);     //分散を求める
        buttonF.setOnClickListener(v -> {
            if(numA==0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView6.setBackgroundColor(Color.YELLOW);
                numA = 12;
                data = new ArrayList<>();
                textView6.setText("分散：数値を１つずつ入力して「次へ」を押して下さい。「決定」で分散を求めます。");      //ここからは「次へ」ボタンに委ねる
                textView10.setText("data");
            }
        });
        Button buttonG = findViewById(R.id.buttonG);  //Mod計算
        buttonG.setOnClickListener(v -> {
            if(numA==0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setBackgroundColor(Color.YELLOW);
                numA = 13;
                data = new ArrayList<>();
                textView6.setText("mod；整数を入力して「決定」を押して下さい。");      //ここからは「決定」ボタンに委ねる
            }

        });
        Button buttonH = findViewById(R.id.buttonH);       //log計算
        buttonH.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("log：底を正の数で入力して「決定」を押して下さい");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 15;
            }

        });
        Button buttonI = findViewById(R.id.buttonI);     //平方根を計算
        buttonI.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("平方根：正の数を入力してください");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 17;
            };

        });
        Button buttonJ = findViewById(R.id.buttonJ);    //べき乗計算
        buttonJ.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("べき乗：底を入力して「決定」を押して下さい");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 18;
            }

        });
        Button buttonK = findViewById(R.id.buttonK);  //逆元を求める
        buttonK.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("逆元を求めたい値を正の数で入力して「決定」を押してくださいください");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 20;
            }

        });
        Button buttonL = findViewById(R.id.buttonL);    //原始元を求める
        buttonL.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("原始元を求めたい法を素数で入力してください");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 22;
                data1 = new ArrayList<>();
            }

        });
        Button buttonM = findViewById(R.id.buttonM);     //10進数変換
        buttonM.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("何進数を10進数に変換しますか？(2以上9以下の自然数)入力して「決定」を押して下さい");
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 26;
            }

        });
        Button buttonN = findViewById(R.id.buttonN);   //拡張ユークリッド
        buttonN.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("拡張ユークリッド：ax+by=dのaを自然数で入力して「決定」を押してください");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 24;
            }

        });

        Button buttonR = findViewById(R.id.buttonR);   //N進数変換
        buttonR.setOnClickListener(v -> {
            if (numA == 0) {
                process = "";
                textView3.setText("");
                textView5.setText("");
                textView9.setText("式");
                textView10.setText("答え");
                textView6.setText("10進数を何進数に変換しますか？(2~9の自然数)入力して「決定」を押して下さい");      //ここからは「決定」ボタンに委ねる
                textView6.setBackgroundColor(Color.YELLOW); //
                numA = 28;
            }

        });

        Button buttonP = findViewById(R.id.buttonP);     //「決定」ボタン。特殊計算を順序立てて進めていく
        buttonP.setOnClickListener(v -> {
            textView3.setText("");
            textView5.setText("");
            if (numA == 1) {                         //「等比」が押されたとき
                aA = Double.parseDouble(process);
                if(aA!=0){                         //aAに値が入ったら次に移る
                    process = "";
                    textView5.setText("");
                    numA = 3;                    //numA=3にし、次に「決定」が押されたらnumA==3のif文を実行
                    textView6.setText("公比を入力して「決定」を押して下さい");
                }
            } else if (numA == 3) {
                bA = Double.parseDouble(process);
                if(bA!=0){                         //bAに値が入ったら次に移る
                    process = "";
                    textView5.setText("");
                    numA = 4;                    //numA=4にし、次に「決定」が押されたらnumA==4のif文を実行
                    textView6.setText("項数を入力して「決定」を押して下さい");
                }
            }else if (numA == 4) {
                nA = Integer.parseInt(process);
                if(nA!=0) {
                    double ans = Touhisuuretu(aA, bA, nA);   //関数
                    textView6.setText("情報電卓");     //リセット
                    textView5.setText(String.valueOf(ans));     //特殊演算の答えの表示
                    textView3.setText("初項" + String.valueOf(aA) + "\n公比" + String.valueOf(bA) + "\n項数" + String.valueOf(nA)); //TextViewに表示
                    preans = String.valueOf(ans);   //答えを保存
                    numA = 0;         //リセット
                    process="";
                    double aA=0;
                    double bA=0;
                    int nA=0;
                    textView6.setBackgroundColor(Color.WHITE); //
                }
            }

            //numA=1~4が等差数列の和を求めるアルゴリズム




            else if (numA == 5) {                      //等比数列の和を求める
                aA = Double.parseDouble(process);         //要領は等差数列の和を求める過程と同じ
                if(aA!=0){
                    process = "";
                    textView5.setText("");
                    numA = 6;
                    textView6.setText("公比を入力して「決定」を押して下さい");
                }
            } else if (numA == 6) {
                bA = Double.parseDouble(process);
                if(bA!=0){
                    process = "";
                    textView5.setText("");
                    numA = 7;
                    textView6.setText("項数を入力して「決定」を押して下さい");
                }
            } else if (numA == 7) {
                nA = Integer.parseInt(process);
                if(nA!=0) {
                    double ans = Tousasuuretu(aA, bA, nA);                          //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText("初項" + String.valueOf(aA) + "\n公比" + String.valueOf(bA) + "\n項数" + String.valueOf(nA));
                    preans = String.valueOf(ans);
                    numA = 0;
                    process="";
                    double aA=0;
                    double bA=0;
                    int nA=0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            } else if (numA == 8) {                       //階乗の計算
                aI = Integer.parseInt(process);
                if(aI!=0) {                              //結果表示とリセット
                    double ans = Kaijou(aI);                   //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText( String.valueOf(aI) + "!");
                    preans = String.valueOf(ans);
                    numA = 0;
                    process="";
                    aI = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if (numA == 9) {                      //コンビネーションの計算
                aI = Integer.parseInt(process);         //必要な値を入れる
                if(aI!=0) {
                    process = "";
                    textView5.setText("");
                    numA = 10;
                    textView6.setText("aCbの「b」を１４以下の自然数で入力して「決定」を押して下さい");
                }
            }else if(numA==10){
                bI = Integer.parseInt(process);       //必要な値を入れる
                if(bI!=0) {                           //結果表示とリセット
                    double ans = Combination(aI,bI);                   //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText( String.valueOf(aI) + "C"+String.valueOf(bI));
                    preans = String.valueOf(ans);
                    numA = 0;
                    process="";
                    aI = 0;
                    bI=0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if(numA==11){                          //平均を求める
                double ans = Heikin(data);                   //関数で求める
                textView6.setText("情報電卓");
                textView5.setText(String.valueOf(ans));
                String datastring2 = data.toString();       //「次へ」で打ち込まれたdataを取ってくる
                textView3.setText(datastring2);
                textView10.setText("答え");
                textView9.setText("data");
                preans = String.valueOf(ans);
                numA = 0;
                process="";
                textView6.setBackgroundColor(Color.WHITE);
            }else if(numA==12){                          //分散
                double ans = Bunsan(data);                   //関数で求める
                textView6.setText("情報電卓");
                textView5.setText(String.valueOf(ans));
                String datastring2 = data.toString();
                textView3.setText(datastring2);
                textView10.setText("答え");
                textView9.setText("data");
                preans = String.valueOf(ans);
                numA = 0;
                process="";
                textView6.setBackgroundColor(Color.WHITE);
            }else if(numA==13) {                               //Mod計算
                cI = Integer.parseInt(process);
                if(cI != 1985416576){
                    process = "";
                    textView5.setText("");
                    numA = 14;                          //「決定」でnumA==14へ
                    textView6.setText("法を整数で入力して「決定」を押して下さい");
                }
            }else if(numA==14){                              //結果表示とリセット
                bI = Integer.parseInt(process);
                if (bI != 0) {
                    int ans = Mod(cI, bI);                   //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText(String.valueOf(cI) + "÷" + String.valueOf(bI) + "の余り");
                    preans = String.valueOf(ans);
                    numA = 0;
                    process = "";
                    cI = 0;
                    bI = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if (numA == 15) {                           //logの計算
                bA = Double.parseDouble(process);
                if(bA!=0) {
                    process = "";
                    textView5.setText("");
                    numA = 16;                          //「決定」でnumA==16へ
                    textView6.setText("真数を0より大きい値で入力して「決定」を押して下さい");
                }
            }else if (numA == 16) {                          //結果表示とリセット
                aA = Double.parseDouble(process);
                if(aA!=0) {
                    double ans = Log(aA, bA);                   //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText("log"+String.valueOf(bA) + "("+ String.valueOf(aA) + ")は");
                    preans = String.valueOf(ans);
                    numA = 0;
                    process = "";
                    double aA = 0;
                    double bA = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            } else if (numA == 17) {                          //平方根の計算
                aA = Double.parseDouble(process);
                if(aA!=0) {
                    double ans = Root(aA);                   //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText(String.valueOf(aA) + "の平方根は");
                    preans = String.valueOf(ans);
                    numA = 0;
                    process = "";
                    double aA = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if (numA == 18) {                   //べき乗計算
                aA = Double.parseDouble(process);
                if(aA!=0) {
                    process = "";
                    textView5.setText("");
                    numA = 19;
                    textView6.setText("べき指数を入力して「決定」を押して下さい");
                }
            } else if (numA == 19) {
                bA = Integer.parseInt(process);
                if (bA != 0) {
                    double ans = Bekijou(aA, bA);                   //関数で求める
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(ans));
                    textView3.setText(String.valueOf(aA) + "の" + String.valueOf(bA) + "乗は");
                    preans = String.valueOf(ans);
                    numA = 0;
                    process = "";
                    double aA = 0;
                    double bA = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if (numA == 20) {                   //逆元の計算
                aI = Integer.parseInt(process);
                if (aI != 0) {
                    process = "";
                    textView5.setText("");
                    numA = 21;
                    textView6.setText("法となる数を素数で入力して「決定」を押してください");
                }
            }else if (numA == 21) {
                bI = Integer.parseInt(process);
                if (bI != 0) {                   //最大公約数が１出なけれは逆元が存在しないのでエラーを返す
                    int gcd1 = Gcd(aI, bI);                   //関数で最大公約数を求める
                    if(gcd1!=1){
                        textView5.setText("逆元は存在しません");                   //エラー文
                    }else {
                        Answer anslist = Kakutyouyukuriddo(aI, bI);                   //拡張ユークリッドの関数を用いる
                        int ansx = anslist.a1;
                        int ansy = anslist.a2;
                        int ansgcd = anslist.a3;
                        int inverse = (anslist.a1 % bI + bI)%bI;
                        textView6.setText("情報電卓");
                        textView5.setText(String.valueOf(inverse));
                        textView3.setText(String.valueOf(bI) + "を法とした" + String.valueOf(aI)+"の逆元は");
                        preans = "";
                        numA = 0;
                        process = "";
                        double aI = 0;
                        double bI = 0;
                        textView6.setBackgroundColor(Color.WHITE);
                    }
                }
            } else if (numA == 22) {                   //原始元の計算
                aI = Integer.parseInt(process);
                if (IsPrimeNumber(aI)==false) {                   //法が素数でなければ原始元がないのでエラーを返す
                    textView5.setText("原始元は存在しません");
                }else {
                    for (int i=2; i<aI; i++){                   //原始元は複数ある場合もあるのでリストを作る
                        if(GensigenJugde(i,aI)){                   //関数を用いて原始元かどうかの判断をしていく
                            data1.add(Integer.toString(i));
                        }
                    }
                    textView6.setText("情報電卓");
                    textView5.setText(String.valueOf(data1));
                    textView3.setText(String.valueOf(aI) + "を法とした原始元は");
                    preans = "";
                    numA = 0;
                    process = "";
                    double aI = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if (numA == 24) {                   //拡張ユークリッド
                aI = Integer.parseInt(process);
                if (aI != 0) {
                    process = "";
                    textView5.setText("");
                    numA = 25;
                    textView6.setText("ax+by=dのbを自然数で入力して「決定」を押してください");
                }
            }else if (numA == 25) {
                bI = Integer.parseInt(process);
                if (bI != 0) {
                    Answer anslist = Kakutyouyukuriddo(aI, bI);                   //関数で求める
                    int ansx = anslist.a1;
                    int ansy = anslist.a2;
                    int ansgcd = anslist.a3;
                    textView6.setText("情報電卓");
                    textView5.setText("x=" + String.valueOf(ansx) + "    y=" + String.valueOf(ansy)+ "  最大公約数は" + String.valueOf(ansgcd));
                    textView3.setText(String.valueOf(aI) + "x + " + String.valueOf(bI) + "y = gcd");
                    preans = "";
                    numA = 0;
                    process = "";
                    double aI = 0;
                    double bI = 0;
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if (numA == 26) {         //10進数変化
                aI = Integer.parseInt(process);         //必要な値を入れる
                if(aI!=0) {
                    process = "";
                    textView5.setText("");
                    numA = 27;                     //numA==27へ
                    textView6.setText("変換したい値を"+Integer.toString(aI)+"進数で入力して「決定」を押して下さい");
                }
            }else if (numA == 27) {
                bI = Integer.parseInt(process);//必要な値を入れる
                aS = process;
                if(SinsuuBug(aS,aI)){                      //10進数に直すときに与えられた文字列にN(aI)以上の数字が入っていないかチェック
                    textView3.setText(Integer.toString(aI)+"以上の数字は入力できません。「CA」を押して下さい");   //エラー//やり直し
                }else{
                    int ans = Jussinsuu(Integer.toString(bI),aI);       //無事10進数に変換
                    textView5.setText(Integer.toString(ans));
                    textView3.setText(Integer.toString(bI)+"の10進数表現は");
                    preans = String.valueOf(ans);
                    numA = 0;
                    process="";
                    aI = 0;
                    bI = 0;
                    aS = "";
                    textView6.setText("情報電卓");
                    textView6.setBackgroundColor(Color.WHITE);
                }
            }else if(numA==28){           //N進数変換
                aI = Integer.parseInt(process);         //必要な値を入れる
                if(aI!=0) {
                    process = "";
                    textView5.setText("");
                    numA = 29;
                    textView6.setText("変換したい値を10進数で入力して「決定」を押して下さい");
                }

            }else if(numA==29){
                bI = Integer.parseInt(process);       //必要な値を入れる
                if(bI!=0) {                           //結果表示とリセット
                    String ans = Nsinsuu(bI,aI);        //関数で求める
                    textView6.setText("情報電卓");
                    textView6.setBackgroundColor(Color.WHITE);
                    textView5.setText(ans);
                    textView3.setText( String.valueOf(bI) + "の"+String.valueOf(aI)+"進数表現は");
                    preans = "";
                    numA = 0;
                    process="";
                    aI = 0;
                    bI=0;
                }
            }

        });



        Button buttonnext = findViewById(R.id.buttonnext);      //リストに値を格納していくためのボタン
        buttonnext.setOnClickListener(v -> {                   //numA=11は平均
            if(numA==11){
                aA = Double.parseDouble(process);
                data.add(aA);
                String datastring = data.toString();
                textView5.setText((datastring));
                textView3.setText("");
                process = "";
            }else if(numA==12){                              //numA==12は分散
                aA = Double.parseDouble(process);
                data.add(aA);
                String datastring = data.toString();
                textView5.setText((datastring));
                textView3.setText("");
                process = "";
            }
        });

        UpdateButtonVisibility();                   //表のボタンか裏のボタンのどちらを表示させるかをチェック
        }

        private void UpdateButtonVisibility() {                   //どれのボタンを表にし、どのボタンを裏にするかを決定している
            Button button1 = findViewById(R.id.button1);          //常時表示させるボタンはここには書かない
            Button button2 = findViewById(R.id.button2);
            Button button3 = findViewById(R.id.button3);
            Button button4 = findViewById(R.id.button4);
            Button button5 = findViewById(R.id.button5);
            Button button6 = findViewById(R.id.button6);
            Button button7 = findViewById(R.id.button7);
            Button button8 = findViewById(R.id.button8);
            Button button9 = findViewById(R.id.button9);
            Button button0 = findViewById(R.id.button0);
            Button buttondot = findViewById(R.id.button30);
            Button buttonequal = findViewById(R.id.button13);
            Button buttontasu = findViewById(R.id.button14);
            Button buttonminus = findViewById(R.id.button15);
            Button buttonkakeru = findViewById(R.id.button16);
            Button buttonwaru = findViewById(R.id.button18);
            Button buttonA = findViewById(R.id.buttonA);
            Button buttonB = findViewById(R.id.buttonB);
            Button buttonC = findViewById(R.id.buttonC);
            Button buttonD = findViewById(R.id.buttonD);
            Button buttonE = findViewById(R.id.buttonE);
            Button buttonF = findViewById(R.id.buttonF);
            Button buttonG = findViewById(R.id.buttonG);
            Button buttonH = findViewById(R.id.buttonH);
            Button buttonI = findViewById(R.id.buttonI);
            Button buttonJ = findViewById(R.id.buttonJ);
            Button buttonK = findViewById(R.id.buttonK);
            Button buttonL = findViewById(R.id.buttonL);
            Button buttonM = findViewById(R.id.buttonM);
            Button buttonN = findViewById(R.id.buttonN);
            Button buttonP = findViewById(R.id.buttonP);
            Button buttonR = findViewById(R.id.buttonR);
            Button buttonans = findViewById(R.id.buttonans);



            if (change % 2 == 0) {
                buttonA.setVisibility(View.GONE);
                buttonB.setVisibility(View.GONE);
                buttonC.setVisibility(View.GONE);
                buttonD.setVisibility(View.GONE);
                buttonE.setVisibility(View.GONE);
                buttonF.setVisibility(View.GONE);
                buttonG.setVisibility(View.GONE);
                buttonH.setVisibility(View.GONE);
                buttonI.setVisibility(View.GONE);
                buttonJ.setVisibility(View.GONE);
                buttonK.setVisibility(View.GONE);
                buttonL.setVisibility(View.GONE);
                buttonM.setVisibility(View.GONE);
                buttonN.setVisibility(View.GONE);
                buttonR.setVisibility(View.GONE);
                buttonP.setVisibility(View.VISIBLE);
                button0.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
                button5.setVisibility(View.VISIBLE);
                button6.setVisibility(View.VISIBLE);
                button7.setVisibility(View.VISIBLE);
                button8.setVisibility(View.VISIBLE);
                button9.setVisibility(View.VISIBLE);
                buttondot.setVisibility(View.VISIBLE);
                buttonequal.setVisibility(View.VISIBLE);
                buttontasu.setVisibility(View.VISIBLE);
                buttonminus.setVisibility(View.VISIBLE);
                buttonkakeru.setVisibility(View.VISIBLE);
                buttonwaru.setVisibility(View.VISIBLE);
                buttonans.setVisibility(View.VISIBLE);



            } else {
                buttonA.setVisibility(View.VISIBLE);
                buttonB.setVisibility(View.VISIBLE);
                buttonC.setVisibility(View.VISIBLE);
                buttonD.setVisibility(View.VISIBLE);
                buttonE.setVisibility(View.VISIBLE);
                buttonF.setVisibility(View.VISIBLE);
                buttonG.setVisibility(View.VISIBLE);
                buttonH.setVisibility(View.VISIBLE);
                buttonI.setVisibility(View.VISIBLE);
                buttonJ.setVisibility(View.VISIBLE);
                buttonK.setVisibility(View.VISIBLE);
                buttonL.setVisibility(View.VISIBLE);
                buttonM.setVisibility(View.VISIBLE);
                buttonN.setVisibility(View.VISIBLE);
                buttonR.setVisibility(View.VISIBLE);
                buttonP.setVisibility(View.VISIBLE);
                button0.setVisibility(View.GONE);
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);
                button5.setVisibility(View.GONE);
                button6.setVisibility(View.GONE);
                button7.setVisibility(View.GONE);
                button8.setVisibility(View.GONE);
                button9.setVisibility(View.GONE);
                buttondot.setVisibility(View.GONE);
                buttontasu.setVisibility(View.GONE);
                buttonminus.setVisibility(View.GONE);
                buttonkakeru.setVisibility(View.GONE);
                buttonwaru.setVisibility(View.GONE);
                buttonans.setVisibility(View.GONE);

            }

    }







///四則演算、特殊計算のための自作関数
    //①全ての記号、数字を一つに並べる
    //②"+"か"-"があれば区切る
    //③"×"か"÷"があれば区切る
    //こうして区切られた数字または小数点の一連の文字列は数字を成す。
    //④文字列を数字に直す。
    //⑤演算子の計算


    public static double Sisokuenzan(String s) {            //四則演算
        //初期化
        double error = 798751598651.71658756189579186995758279948542797;    //エラー用の返り値
        int pre = -1;
        double ans = 0;
        int preop = 1;
        s = s + '+';                //文字列の最後に＋をつけることでエラーを防ぐ
        int n = s.length();
        for (int i = 0; i < n; i++) {          //②
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {          //＋か－かあればその記号の間の文字列を計算する
                String t = "";
                for (int j = pre + 1; j <= i - 1; j++) t = t + s.charAt(j);   //+か－の間の文字を格納
                t = t + '×';          //文字列tの最後に「*」をつけて計算ミスをなくす
                int si = t.length();
                double ima = 1;
                int pre2 = -1;
                int preop2 = 1;
                for (int j = 0; j < si; j++) {           //③
                    if (t.charAt(j) == '×' || t.charAt(j) == '÷') {          //「*」か「÷」があればかけ算、または割り算を行う
                        int dot = j;
                        for (int k = pre2 + 1; k < j; k++)
                            if (t.charAt(k) == '.') dot = k;          //小数点位置を特定

                        double now = 0;
                        //④//電卓で数字が打ち込まれるように文字列を左から数字に変換していく
                        for (int k = pre2 + 1; k < dot; k++) now = now * 10 + (t.charAt(k) - '0');         //整数部分
                        double now2 = 0;
                        for (int k = j - 1; k > dot; k--) now2 = now2 / 10 + (t.charAt(k) - '0');          //小数部分
                        now2 /= 10;
                        now += now2;         //整数と小数を足す
                        if (preop2 == 0) {      //割り算
                            if(now==0){           //0除算があればerrorを返す
                                return error;
                            }else{
                            ima = ima / now;
                            }
                        } else{          //かけ算
                            ima = ima * now;
                        }

                        pre2 = j;
                        if (t.charAt(j) == '×') preop2 = 1;
                        else preop2 = 0;
                    }
                }
                pre = i;
                ans = ans + preop * ima;   // 足し算か引き算//数値の直前にある演算子が+ならpreop==1、-ならpreop==-1
                if (s.charAt(i) == '+') preop = 1;
                else preop = -1;
            }
        }
        return ans;
    }

    public static double Touhisuuretu(double a,double b,int n){    //等比数列の和を求める関数//（a,b,n）=(初期値、公比、項数)
        double ans1=0;         //初期値
        if(b==1){
            ans1 = a*n;
        } else{
            ans1 = Math.pow(b, n);         //    a(1-r**n)/1-r
            ans1 = a * (ans1-1);
            ans1 = ans1 / (b - 1);
        }
        return ans1;
    };

    public static double Tousasuuretu(double a,double b,int n){         //等差数列の和を求める関数
        double ans1=0;         //初期値
        double l = a+(n-1)*b;             //末項
        ans1 = 0.5*(a+l)*n;           //    1/2*n(a+l)
        return ans1;
    };

    public static double Heikin(List<Double> a){           //平均を求める関数
        double sum = 0;           //初期化
        for(int i=0; i<a.size(); i++){
        sum += a.get(i);
        }
        return (sum/a.size());
    };

    public static double Bunsan(List<Double> a){           //分散
        double sum =0;
        double hei=Heikin(a);           //平均を求める関数を用いる
        for(int i=0; i<a.size(); i++){
            sum += Math.pow((a.get(i)-hei),2);           //（xi-x)**2
        }
        return (sum/a.size());
    };

    public static double Root(double a){           //平方根
        double ans1=0;
        ans1 = Math.sqrt(a);
        return ans1;
    }

    public static String Nsinsuu(int a, int d) {         //N進数変換
        String base = "0123456789";
        String res = "";
        String res1 = "";
        int now = a;
        while (now > 0) {
            res+=base.charAt(now % d);   //baseと対応しており、余りの数字を取ってくる
            now /= d;
        }
        for(int i=0;i<res.length();i++){       //文字列を反転させる
            res1+=res.charAt(res.length()-1-i);
        }

        return res1;
    }


    public static int Jussinsuu(String a,int d){        //10進数変換
        int res = 0;
        for(int i=0; i<a.length(); i++){
            res += Math.pow(d,i)*(a.charAt(a.length()-1-i) - '0');
        }
        return res;
    }

    public static int Mod(int a,int b){           //Mod
        int ans1=0;
        ans1=a%b;
        return ans1;
    }

    public static double Kaijou(int a){           //階乗
        int ans1=1;
        for(int i=1; i<=a; i++){
            ans1 = ans1*i;
        }
        return ans1;
    }


    public static double Combination(int a, int b){           //コンビネーション
        double ans1=0;
        double c =1;  //分子
        double d =1;  //分母
        for(int i=0;i<b;i++) {
            c = c * (a - i);
        }
        d = Kaijou(b);           //階乗を求める関数を用いる
        ans1=c/d;
        return ans1;
    }


    public static double Log(double a,double b){           //log
        double ans1= Math.log(a) / Math.log(b);
        return ans1;
    }


    public static double Bekijou(double a,double b){           //べき乗
        double ans1=Math.pow(a,b);
        return ans1;
    }


    public static int Gcd(int a, int b) {           //最大公約数を求める関数
        while (b != 0) {
            int temp = b;           //更新されないようにいったん保持
            b = a % b;
            a = temp;
        }
        return a;
    }


    public class Answer {           //値を複数返したい時に使う関数
        public int a1;           //宣言
        public int a2;
        public int a3;
        public Answer(int a1, int a2,int a3) {
            this.a1 = a1;           //引数を入れていく
            this.a2 = a2;
            this.a3 = a3;
        }
    }



    public Answer Kakutyouyukuriddo(int a, int b) {           //拡張ユークリッド
        int gcd = Gcd(a, b);           //最大公約数を求める関数

        int q1 = a / b;           //今期の授業で学習した計算方法
        int r1 = a % b;           //x,yが3個目以降から自動更新できるのでそれまでは冗長だが真面目に書く
        int x1 = 1;
        int y1 = -q1;
        if (a%b==0) {
            return new Answer(x1,y1+1,gcd);
        }

        int q2 = b / r1;
        int r2 = b % r1;
        int x2 = -q2;
        int y2 = 1 + q1 * q2;
        if(b%r1==0){
            return new Answer(x2,y2,gcd);
        }

        int q3 = r1 / r2;
        int r3 = r1 % r2;
        int x3 = x1 - q3 * x2;
        int y3 = y1 - q3 * y2;
        //自動更新
        while(r3 !=0){        //最新のあまりが０になるまでループ
            q1 = q2;
            r1 = r2;
            x1 = x2;
            y1 = y2;
            q2 = q3;
            r2 = r3;
            x2 = x3;
            y2 = y3;
            q3 = r1 / r2;
            r3 = r1 % r2;
            x3 = x1 - q3 * x2;
            y3 = y1 - q3 * y2;
        }
            return new Answer(x2,y2,gcd);           //Answer型で３つの値を返す
    }

    public static boolean IsPrimeNumber(int a){           //素数かどうかを判定する関数
        if(a<=1){        //１以下は素数でない
            return false;
        }
        if(a==2){        //２は素数
            return true;
        }
        if(a%2==0){        //２以外の２の倍数は素数ではない
            return false;
        }

        for(int i=3; i<=(int)Math.sqrt(a);i++){        //３から√p　までの全ての数で割りきれないことが分かればpは素数である
            if(a%i==0){
                return false;
            }
        }
        return true;
    }


    public static boolean GensigenJugde(int g, int p) {        //原始元にふさわしいか判定する
        if (g <= 0 || g >= p) {        //法より大きかったり、０以下は原始元ではない
            return false;
        }
        if (Gcd(g, p) != 1) {        //互いに素でなければ原始元ではない
            return false;
        }
        int result = 1;
        for (int i = 1; i < p - 1; i++) {        //gを１から(p-2)乗までして１にならなかったら原始元である。
            result = (result * g) % p;
            if (result == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean SinsuuBug(String str, int d) {        //10進数に直すとき、与えられた文字列にd以上の数字が含まれていないか判定する関数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c-'0' >= d ) {
                return true;
            }
        }
        return false; // d以上の文字が含まれていない場合はfalseを返す
    }



}


