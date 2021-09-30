package com.example.nachos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class AboutAnActivity extends AppCompatActivity{
    private TextView tv_count;
    private int counter;
    private Button hash_up, hash_ve, hash_ft, hash_do, hash_aw, hash_pf;
    private String score;

    @Override
    protected void onStop() {//5
        //Toast.makeText(getApplicationContext(),"onStop 호출됨",Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {//6
        //Toast.makeText(getApplicationContext(),"onDestroy 호출됨",Toast.LENGTH_SHORT).show();
        savescore();
        super.onDestroy();
    }

    @Override
    protected void onPause() {//4
        //Toast.makeText(getApplicationContext(),"onPause 호출됨",Toast.LENGTH_SHORT).show();
        savescore(); //앱이 도중에 잘못되어도 저장됨
        super.onPause();
    }

    //데이터 저장
//SharedPreferences는 해당 프로세스(어플리케이션)내에 File 형태로 Data를 저장해
//해당 어플리케이션이 삭제되기 전까지 Data를 보관해 주는 기능
//SharedPreferences 사용한 어플리케이션을 지우면 내용이 모두 삭제 됩니다. File이 삭제되는 것이지요.
    private void savescore(){
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE); //"gostop"은 SharedPreferences 이름. 여러개가 있을 수 있음
        SharedPreferences.Editor edit = pref.edit(); //만들어서 저장
        edit.putString("score", String.valueOf(counter)); //종료해도 저장됨
        edit.commit(); //저장은 필수
    }

    @Override
    protected void onResume() {//3
        //Toast.makeText(getApplicationContext(),"onResume 호출됨",Toast.LENGTH_SHORT).show();
        loadscore();
        super.onResume();
    }

    //데이터 호출
    private void loadscore(){
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE); //불러올때 설정한 이름을 불러와야해
        score = pref.getString("score","0"); //값이 없을 때 0을 기본값으로 넣겠다.(기본값)
        Toast.makeText(getApplicationContext(),"점수 : "+score ,Toast.LENGTH_SHORT).show();
        tv_count.setText(String.valueOf(score) +"도");
        savescore();
    }

    @Override
    protected void onStart() {//2
        //Toast.makeText(getApplicationContext(),"onStart 호출됨",Toast.LENGTH_SHORT).show();
        savescore();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_animal);



        SharedPreferences prefs = getSharedPreferences("counter_alone", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        counter = prefs.getInt("counter", 0);



        tv_count = findViewById(R.id.tv_count);

        Button title_back, title_prof; // 상단 타이틀
        Button home, cate, prod, stor; // 상단 탑뷰
         // hashtag들

        title_back = findViewById(R.id.btn_Back);
        title_prof = findViewById(R.id.btn_Profile);
        home = findViewById(R.id.button_home);
        cate = findViewById(R.id.buttom_cate);
        prod = findViewById(R.id.button_prod);
        stor = findViewById(R.id.button_stor);
        hash_up = findViewById(R.id.hash1);
        hash_ve = findViewById(R.id.hash2);
        hash_ft = findViewById(R.id.hash3);
        hash_do = findViewById(R.id.hash4);
        hash_aw = findViewById(R.id.hash5);
        hash_pf = findViewById(R.id.hash6);

        // 상단 타이틀 버튼 클릭 시 이벤트
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(0, 0);
            }
        });

        title_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "프로필", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AboutAnActivity.this, CountActivity.class);
                int score1 =  Integer.valueOf(score);
                System.out.println("score1"+score1);
                intent.putExtra("counter",score1);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        // 상단 탑뷰 클릭 시 이벤트
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAnActivity.this, HomeActivity.class);
                int score1 =  Integer.valueOf(score);
                System.out.println("score1"+score1);
                intent.putExtra("counter",score1);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAnActivity.this, CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAnActivity.this, ProductActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        stor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAnActivity.this, SiteActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //HashTag 키워드 클릭 시 이벤트
        hash_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                //tv_count.setText(counter);

                editor.putInt("counter", counter);
                editor.apply();

                Intent intent = new Intent(AboutAnActivity.this, AboutUpActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        hash_ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                //tv_count.setText(counter);

                editor.putInt("counter", counter);
                editor.apply();
                Intent intent = new Intent(AboutAnActivity.this, AboutVeActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        hash_ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                //tv_count.setText(counter);

                editor.putInt("counter", counter);
                editor.apply();
                Intent intent = new Intent(AboutAnActivity.this, AboutFtActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        hash_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                //tv_count.setText(counter);

                editor.putInt("counter", counter);
                editor.apply();
                Intent intent = new Intent(AboutAnActivity.this, AboutDoActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        hash_aw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                //tv_count.setText(counter);

                editor.putInt("counter", counter);
                editor.apply();
                Intent intent = new Intent(AboutAnActivity.this, AboutAnActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        hash_pf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAnActivity.this, AboutPfActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        Button ansite;
        ansite = findViewById(R.id.button_site);
        ansite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAnActivity.this, SiteActivity.class);
                intent.putExtra("froman","froman");
                startActivity(intent);
            }
        });

        savescore();
    }

}
