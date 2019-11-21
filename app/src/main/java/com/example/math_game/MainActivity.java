package com.example.math_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String operater[]={"+","-","*","/"};
    int num1,num2,num3,num4,num5,result,ans;
    Boolean counter;
    int point=0;

    TextView levelT,quesT,question,score,timer;//,answerT;
    Button submit,start;
    EditText answer;
    AlertDialog.Builder dialog;
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //object Allocation
        question=(TextView)findViewById(R.id.question);
        quesT=(TextView)findViewById(R.id.qtitle);
        answer=(EditText)findViewById(R.id.answer);
        levelT=(TextView)findViewById(R.id.leveltitle);
        score=(TextView)findViewById(R.id.score);
      //  message=(TextView)findViewById(R.id.message);
        submit=(Button)findViewById(R.id.submit);
        start=(Button)findViewById(R.id.start);
        timer=(TextView)findViewById(R.id.timer);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                    }
                });
            }
    void startGame()
    {
        levelT.setVisibility(View.VISIBLE);
        quesT.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        answer.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
        //Game start
        watch watch_ob=new watch();
        counter=true;
        String str=timer.getText().toString();
        String time[]=str.split(":");
        int min=Integer.parseInt(time[0]);
        int sec=Integer.parseInt(time[1]);
        watch_ob.execute(min*60);
        level1();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = Integer.valueOf(String.valueOf(answer.getText()));
                point = checkResult(result, ans, point);
                if (point == 0) {
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Game over");
                    dialog.setMessage("Do you want to restart the game");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            counter=false;
                            RestartGame();
                        }
                    });
                    alert = dialog.create();
                    alert.show();
                }
                if(point <50)
                    level1();
                else if(point>=50 && point<=90)
                    level2();
                else if(point>=100 && point<=140)
                    level3();
                else if(point>=150 && point<=190)
                    level4();
                else if(point==200)
                {
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Congratulations");
                    dialog.setMessage("You have own the game");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            counter=false;
                            RestartGame();
                        }
                    });
                    alert = dialog.create();
                    alert.show();
                }
                score.setText("Score:" + point);
                answer.setText("");
            }});
    }
    void RestartGame()
    {
        levelT.setVisibility(View.INVISIBLE);
        quesT.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        answer.setVisibility(View.INVISIBLE);
        //   message.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        timer.setVisibility(View.INVISIBLE);
        point=0;
    }
    void level1()
    {
        Random rand=new Random();
        num1=rand.nextInt(20);
        num2=rand.nextInt(20);
        int op=rand.nextInt(4);
        levelT.setText("Level 1");
        switch (op)
        {
            case 0:
                result=num1+num2;
                question.setText(num1+"+"+num2);
                break;
            case 1:
                result=num1-num2;
                question.setText(num1+"-"+num2);
                break;
            case 2:
                result=num1*num2;
                question.setText(num1+"*"+num2);
                break;
            case 3:
                result=num1/num2;
                question.setText(num1+"/"+num2);
                break;

        }
    }
    void level2()
    {
        Random rand=new Random();
        num1=rand.nextInt(30);
        num2=rand.nextInt(30);
        num3=rand.nextInt(30);
        int op=rand.nextInt(4);
        int op2=rand.nextInt(4);
        String str=operater[op]+""+operater[op2];
        levelT.setText("Level 2");
        switch (str)
        {
            case "++":
                result=num1+num2+num3;
                question.setText(num1+"+"+num2+"+"+num3);
                break;
            case "--":
                result=num1-num2-num3;
                question.setText(num1+"-"+num2+"-"+num3);
                break;
            case "**":
                result=num1*num2*num3;
                question.setText(num1+"*"+num2+"*"+num3);
                break;
            case "//":
                result=num1/num2/num3;
                question.setText(num1+"/"+num2+"/"+num3);
                break;
            case "+-":
                result=num1+num2-num3;
                question.setText(num1+"+"+num2+"-"+num3);
                break;
            case "+*":
                result=num1+num2*num3;
                question.setText(num1+"+"+num2+"*"+num3);
                break;
            case "+/":
                result=num1+num2/num3;
                question.setText(num1+"+"+num2+"/"+num3);
                break;
            case "-+":
                result=num1-num2+num3;
                question.setText(num1+"-"+num2+"+"+num3);
                break;
            case "-*":
                result=num1-num2*num3;
                question.setText(num1+"-"+num2+"*"+num3);
                break;
            case "-/":
                result=num1-num2/num3;
                question.setText(num1+"-"+num2+"/"+num3);
                break;
            case "*+":
                result=num1*num2+num3;
                question.setText(num1+"*"+num2+"-"+num3);
                break;
            case "*-":
                result=num1*num2-num3;
                question.setText(num1+"*"+num2+"-"+num3);
                break;
            case "*/":
                result=num1*num2/num3;
                question.setText(num1+"*"+num2+"/"+num3);
                break;
            case "/-":
                result=num1/num2-num3;
                question.setText(num1+"/"+num2+"-"+num3);
                break;
            case "/*":
                result=num1/num2*num3;
                question.setText(num1+"/"+num2+"*"+num3);
                break;
            case "/+":
                result=num1/num2+num3;
                question.setText(num1+"/"+num2+"+"+num3);
                break;

        }
    }
    void level3()
    {
        Random rand=new Random();
        num1=rand.nextInt(50);
        num2=rand.nextInt(50);
        num3=rand.nextInt(50);
        num4=rand.nextInt(50);
        int op=rand.nextInt(4);
        int op2=rand.nextInt(4);
        int op3=rand.nextInt(4);
        levelT.setText("Level 3");
        String str=operater[op]+""+operater[op2]+""+operater[op3];
        //actual operation
                String problem=num1+operater[op]+num2+operater[op2]+num3+operater[op3]+num4;
                result=cal(problem);

                question.setText(num1+operater[op]+num2+operater[op2]+num3+operater[op3]+num4);
    }
    void level4()
    {
        Random rand=new Random();
        num1=rand.nextInt(50);
        num2=rand.nextInt(50);
        num3=rand.nextInt(50);
        num4=rand.nextInt(50);
        num5=rand.nextInt(50);
        int op=rand.nextInt(4);
        int op2=rand.nextInt(4);
        int op3=rand.nextInt(4);
        int op4=rand.nextInt(4);
        levelT.setText("Level 4");
        String str=operater[op]+""+operater[op2]+""+operater[op3]+""+operater[op4];
        //actual operation
        String problem=num1+operater[op]+num2+operater[op2]+num3+operater[op3]+num4+operater[op4]+num5;
        result=cal(problem);

        question.setText(num1+operater[op]+num2+operater[op2]+num3+operater[op3]+num4+operater[op4]+num5);
    }
    int checkResult(double result,double ans,int point)
    {

        if(result==ans)
        {
            point=point+10;
           // message.setText("Correct Answer");
        }
        else
        {
            return 0;
        }
        return point;
    }
    int generateExpression(int num1,int num2,int op)
    {
        switch (op)
        {
            case 0:
                result=num1+num2;
                break;
            case 1:
                result=num1-num2;
                break;
            case 2:
                result=num1*num2;
                break;
            case 3:
                result=num1/num2;
                break;
        }
        return result;
    }
    int cal(String problem)
    {
        ArrayList<String> al=new ArrayList<String>();
        String operator = "/*+-";
        String p=" ";
        int j;
        int counter = 0;
        Character c;
        for (int i = 0; i < problem.length(); i++) {
            c = problem.charAt(i);
            if (c == '/' ||c == '*'||c == '+'||c == '-') {
                p = p.substring(1, p.length());
                al.add(counter, p);
                counter++;
                al.add(counter,String.valueOf(c));
                counter++;
                p=" ";
            }
            else if(i==(problem.length()-1))
            {
                p=p+c;
                p = p.substring(1, p.length());
                al.add(counter,p);
            }
            else
                p=p+c;
        }
        double ans = 0.0;
        for (int i = 0; i < al.size(); i++) {
            p=al.get(i);
            String p1,p2;
            if(p.contains("/")) {
                double var1, var2;
                p1=al.get(i-1);
                p2=al.get(i+1);
                var1 = Double.valueOf(p1);
                var2 = Double.valueOf(p2);
                ans = var1 / var2;
                al.remove(i-1);
                al.add(i-1,String.valueOf(ans));
                for (j = i; j < (al.size() - 2); j++)
                {
                    p=al.get(j+2);
                    al.remove(j);
                    al.add(j,p);
                }
                al.remove(al.size()-1);
                al.remove(al.size()-1);
                i = 0;
            }
        }
        for (int i = 0; i < al.size(); i++) {
            p=al.get(i);
            String p1,p2;
            if(p.contains("*")) {
                double var1, var2;
                p1=al.get(i-1);
                p2=al.get(i+1);
                var1 = Double.valueOf(p1);
                var2 = Double.valueOf(p2);
                ans = var1 * var2;
                al.remove(i-1);
                al.add(i-1,String.valueOf(ans));
                for (j = i; j < (al.size() - 2); j++)
                {
                    p=al.get(j+2);
                    al.remove(j);
                    al.add(j,p);
                }
                al.remove(al.size()-1);
                al.remove(al.size()-1);
                i = 0;
            }
        }
        for (int i = 0; i < al.size(); i++) {
            p=al.get(i);
            String p1,p2;
            if(p.contains("+")) {
                double var1, var2;
                p1=al.get(i-1);
                p2=al.get(i+1);
                var1 = Double.valueOf(p1);
                var2 = Double.valueOf(p2);
                ans = var1 + var2;
                al.remove(i-1);
                al.add(i-1,String.valueOf(ans));
                for (j = i; j < (al.size() - 2); j++)
                {
                    p=al.get(j+2);
                    al.remove(j);
                    al.add(j,p);
                }
                al.remove(al.size()-1);
                al.remove(al.size()-1);
                i = 0;
            }
        }
        for (int i = 0; i < al.size(); i++) {
            p=al.get(i);
            String p1,p2;
            if(p.contains("-")) {
                double var1, var2;
                p1=al.get(i-1);
                p2=al.get(i+1);
                var1 = Double.valueOf(p1);
                var2 = Double.valueOf(p2);
                ans = var1 - var2;
                al.remove(i-1);
                al.add(i-1,String.valueOf(ans));
                for (j = i; j < (al.size() - 2); j++)
                {
                    p=al.get(j+2);
                    al.remove(j);
                    al.add(j,p);
                }
                al.remove(al.size()-1);
                al.remove(al.size()-1);
                i = 0;
            }
        }
        double val=Double.parseDouble(al.get(0));
        int val2= (int) Math.round(val);
        return val2;
    }

    class watch extends AsyncTask<Integer,Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        public Void doInBackground(Integer... integers)
        {
            try{
                for(int i=integers[0].intValue();i>0;i--)
                {
                    publishProgress(i);
                    Thread.sleep(1000);
                    if(counter==false)
                        break;
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(counter)
            {
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Time over");
                dialog.setMessage("Do you want to restart the game");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        counter=false;
                        RestartGame();
                    }
                });
                alert = dialog.create();
                alert.show();
            }

        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Integer timer1=values[0].intValue();
            String time;
            if(timer1/60!=0)
            {
                time=""+(timer1/60)+":"+(timer1%60);
            }
            else
            {
                time=""+0+":"+(timer1%60);
            }
            timer.setText(time);
        }
    }
}
