package com.example.android.slagalika;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    String kombinacija;
    String resenje;
    String resenje1;

    Okvir prviOkvir;
    Okvir drugiOkvir;
    Okvir treciOkvir;
    Okvir cetvrtiOkvir;

    ImageButton btnSkocko;
    ImageButton btnKordun;
    ImageButton btnLika;
    ImageButton btnBanija;
    Button btnAjOpet;

    int index1;
    int index2;
    int index3;
    int index4;

    RelativeLayout relativeLayout;
    PopupWindow popupWindow1;

    TextView textView123;
    ViewGroup view123;
    MediaPlayer mp;

    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i=0;

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String skocko = "@drawable/skocko";
        final String kordun = "@drawable/kordun";
        final String banija = "@drawable/banija";
        final String lika = "@drawable/lika";


        prviOkvir = new Okvir();
        drugiOkvir = new Okvir();
        treciOkvir = new Okvir();
        cetvrtiOkvir = new Okvir();

        btnSkocko = (ImageButton) findViewById(R.id.skocko);
        btnKordun = (ImageButton) findViewById(R.id.kordun);
        btnLika = (ImageButton) findViewById(R.id.lika);
        btnBanija = (ImageButton) findViewById(R.id.banija);
/*
        mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        mProgressBar.setProgress(i);
        mCountDownTimer=new CountDownTimer(15000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                mProgressBar.setProgress(i);

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                mProgressBar.setProgress(i);
            }
        };
        mCountDownTimer.start();
*/
        generateRandComb();

        btnSkocko.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                displayInformation(skocko);
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.skocko);
                mp.start();
            }


        });
        btnKordun.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                displayInformation(kordun);
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.kordun);
                mp.start();
            }
        });
        btnLika.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                displayInformation(lika);
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.lika);
                mp.start();
            }
        });
        btnBanija.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                displayInformation(banija);
                stopPlaying();
                mp = MediaPlayer.create(MainActivity.this, R.raw.banija);
                mp.start();
            }
        });


        view123 = (ViewGroup) getLayoutInflater().inflate(R.layout.test, null);

        popupWindow1 = new PopupWindow(view123, ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT, true);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative2);
        textView123 = (TextView) view123.findViewById(R.id.textView1);
        btnAjOpet = (Button) view123.findViewById(R.id.buttonAjOpet);
        btnAjOpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(i);
            }
        });

    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }


    int row = 0;
    int poz = 0;

    void createBtnRez(int ID5, final int ID6) {
        final ImageButton btnRez = (ImageButton) findViewById(ID5);
        btnRez.setImageResource(android.R.drawable.ic_menu_help);
        btnRez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                btnRez.setVisibility(View.INVISIBLE);
                TextView textView = (TextView) findViewById(ID6);
                generateComb();

                textView.setText("?????????:\n" + brojPogodjenih() + "\n" + "?? ??????:\n" + brojNaMjestu());
                if (brojNaMjestu() == 4 && brojPogodjenih() == 4) {
                    popupWindow1.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
                    mp = MediaPlayer.create(MainActivity.this, R.raw.odlicnostevice);
                    mp.start();
                }
                row++;
                prviOkvir.setPrazno(true);
                drugiOkvir.setPrazno(true);
                treciOkvir.setPrazno(true);
                cetvrtiOkvir.setPrazno(true);
                removeClickable(ID1, ID2, ID3, ID4);
                if (brojNaMjestu() == 4) {
                    textView123.setText(R.string.BravoNemaSta);
                    removeClickable(R.id.skocko, R.id.kordun, R.id.lika, R.id.banija);
                    mp = MediaPlayer.create(MainActivity.this, R.raw.odlicnostevice);
                    mp.start();
                }
                if (ID6 == R.id.rez66) {
                    createResejneSlike(index1 + 1, index2 + 1, index3 + 1, index4 + 1);
                    removeClickable(R.id.skocko, R.id.kordun, R.id.lika, R.id.banija);
                    popupWindow1.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
                    if(brojNaMjestu()!=4){
                        mp = MediaPlayer.create(MainActivity.this, R.raw.nijetacno);
                        mp.start();
                    }

                }
            }
        });
    }

    private void createResejneSlike(int a, int b, int c, int d) {
        if (a == 1) {
            btnSkocko.setImageResource(R.drawable.skocko);
        } else if (a == 2) {
            btnSkocko.setImageResource(R.drawable.kordun);
        } else if (a == 3) {
            btnSkocko.setImageResource(R.drawable.lika);
        } else if (a == 4) {
            btnSkocko.setImageResource(R.drawable.banija);
        }
        if (b == 1) {
            btnKordun.setImageResource(R.drawable.skocko);
        } else if (b == 2) {
            btnKordun.setImageResource(R.drawable.kordun);
        } else if (b == 3) {
            btnKordun.setImageResource(R.drawable.lika);
        } else if (b == 4) {
            btnKordun.setImageResource(R.drawable.banija);
        }
        if (c == 1) {
            btnLika.setImageResource(R.drawable.skocko);
        } else if (c == 2) {
            btnLika.setImageResource(R.drawable.kordun);
        } else if (c == 3) {
            btnLika.setImageResource(R.drawable.lika);
        } else if (c == 4) {
            btnLika.setImageResource(R.drawable.banija);
        }
        if (d == 1) {
            btnBanija.setImageResource(R.drawable.skocko);
        } else if (d == 2) {
            btnBanija.setImageResource(R.drawable.kordun);
        } else if (d == 3) {
            btnBanija.setImageResource(R.drawable.lika);
        } else if (d == 4) {
            btnBanija.setImageResource(R.drawable.banija);
        }
    }

    int ID1 = 0;
    int ID2 = 0;
    int ID3 = 0;
    int ID4 = 0;
    int ID5 = 0;
    int ID6 = 0;

    void displayInformation(String Res) {
        int imageResource = getResources().getIdentifier(Res, "drawable", getPackageName());
        poz = checkPosition();


        switch (row) {
            case 0:
                ID1 = R.id.prvi1;
                ID2 = R.id.prvi2;
                ID3 = R.id.prvi3;
                ID4 = R.id.prvi4;
                ID5 = R.id.rez1;
                ID6 = R.id.rez11;
                break;
            case 1:
                ID1 = R.id.drugi1;
                ID2 = R.id.drugi2;
                ID3 = R.id.drugi3;
                ID4 = R.id.drugi4;
                ID5 = R.id.rez2;
                ID6 = R.id.rez22;
                break;
            case 2:
                ID1 = R.id.treci1;
                ID2 = R.id.treci2;
                ID3 = R.id.treci3;
                ID4 = R.id.treci4;
                ID5 = R.id.rez3;
                ID6 = R.id.rez33;
                break;
            case 3:
                ID1 = R.id.cetvrti1;
                ID2 = R.id.cetvrti2;
                ID3 = R.id.cetvrti3;
                ID4 = R.id.cetvrti4;
                ID5 = R.id.rez4;
                ID6 = R.id.rez44;
                break;
            case 4:
                ID1 = R.id.peti1;
                ID2 = R.id.peti2;
                ID3 = R.id.peti3;
                ID4 = R.id.peti4;
                ID5 = R.id.rez5;
                ID6 = R.id.rez55;
                break;
            case 5:
                ID1 = R.id.sesti1;
                ID2 = R.id.sesti2;
                ID3 = R.id.sesti3;
                ID4 = R.id.sesti4;
                ID5 = R.id.rez6;
                ID6 = R.id.rez66;
                break;
        }
        if (poz == 0) {
            createButton(imageResource, Res, ID1, prviOkvir);
        } else if (poz == 1) {
            createButton(imageResource, Res, ID2, drugiOkvir);
        } else if (poz == 2) {
            createButton(imageResource, Res, ID3, treciOkvir);
        } else if (poz == 3) {
            createButton(imageResource, Res, ID4, cetvrtiOkvir);
            createBtnRez(ID5, ID6);
        }
    }

    private void createButton(int imageResource, String Res, int ID, final Okvir okvir) {
        final ImageButton newBtn = (ImageButton) findViewById(ID);
        newBtn.setImageResource(imageResource);

        okvir.setPrazno(false);
        okvir.setIme(Res);

        setID(okvir.getIme(), okvir);

        newBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                newBtn.setImageResource(R.drawable.boja);
                okvir.setPrazno(true);
            }
        });

    }

    private void setID(String ime, Okvir a) {
        switch (ime) {
            case "@drawable/skocko":
                a.setID(1);
                break;
            case "@drawable/kordun":
                a.setID(2);
                break;
            case "@drawable/lika":
                a.setID(3);
                break;
            default:
                a.setID(4);
                break;
        }
    }

    int checkPosition() {

        if (prviOkvir.getPrazno()) {
            return 0;
        } else if (drugiOkvir.getPrazno()) {
            return 1;
        } else if (treciOkvir.getPrazno()) {
            return 2;
        } else if (cetvrtiOkvir.getPrazno()) {
            return 3;
        }
        return 6;
    }

    public String generateComb() {

        kombinacija = prviOkvir.getID().toString() + drugiOkvir.getID().toString() +
                treciOkvir.getID().toString() + cetvrtiOkvir.getID().toString();
        if (kombinacija.equals("3333")) {
            mp = MediaPlayer.create(MainActivity.this, R.raw.like4);
            mp.start();
        }
        return kombinacija;
    }

    private int brojNaMjestu() {
        int naMjestu = 0;
        for (int i = 0; i < 4; i++) {
            if (kombinacija.charAt(i) == resenje1.charAt(i)) {
                naMjestu++;
            }
        }
        return naMjestu;
    }

    private int brojPogodjenih() {
        int brPogodjenih = 0;
        int naMjestu = brojNaMjestu();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kombinacija.charAt(i) == resenje.charAt(j)) {
                    brPogodjenih++;

                    char[] chars = resenje.toCharArray();
                    chars[j] = 'X';

                    resenje = new String(chars);

                    break;
                }
            }
        }
        resenje = resenje1;
        if (brPogodjenih < naMjestu) {
            return naMjestu;
        } else
            return brPogodjenih;
    }

    private void removeClickable(int ID1, int ID2, int ID3, int ID4) {

        findViewById(ID1).setClickable(false);
        findViewById(ID2).setClickable(false);
        findViewById(ID3).setClickable(false);
        findViewById(ID4).setClickable(false);
    }

    private void generateRandComb() {
        final String[] comb = {"1", "2", "3", "4"};
        Random random = new Random();

        index1 = random.nextInt(comb.length);
        index2 = random.nextInt(comb.length);
        index3 = random.nextInt(comb.length);
        index4 = random.nextInt(comb.length);
        System.out.println("INDEX - " + (index1 + 1) + (index2 + 1) + (index3 + 1) + (index4 + 1));

        resenje = comb[index1] + comb[index2] + comb[index3] + comb[index4];
        resenje1 = resenje;
    }

}
