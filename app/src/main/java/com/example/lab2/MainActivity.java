package com.example.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private Button ROCK, PAPER, SCISSORS;
    public TextView playWeapDisp, compWeapDisp, playWinCntDis,compWinCntDis, winDisp;
    public int playWinCounter = 0;
    public int compWinCounter = 0;

    private Weapon player, computer;

    private boolean playerWinner = false;

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        public int weaponValues(){
            int ROCK = 0;
            int PAPER = 1;
            int SCISSORS = 2;
            return weaponValues();
        }

        private String message;

        private Weapon(String msg) { message = msg; }

        @Override
        public String toString() { return message; }

    };






    public Weapon getCompWeapon(){

        int compWeapon= ((int)(Math.random() * 3));
        Weapon choice;

        switch(compWeapon){
            case 0:
                choice = Weapon.ROCK;
                break;
            case 1:
                choice = Weapon.PAPER;
                break;
            default:
                choice = Weapon.SCISSORS;

        }

        return choice;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PAPER = (Button) findViewById(R.id.paperButton);
        ROCK = (Button) findViewById(R.id.rockButton);
        SCISSORS = (Button) findViewById(R.id.scissorsButton);

        playWeapDisp = (TextView) findViewById(R.id.playWeapDisp);
        compWeapDisp = (TextView) findViewById(R.id.compWeapDisp);
        playWinCntDis = (TextView) findViewById(R.id.playWinCntDis);
        compWinCntDis = (TextView) findViewById(R.id.compWinDis);
        winDisp = (TextView) findViewById(R.id.winDisp);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void buttonClick(View v) {

        // Display Weapon Choice

        Button b = (Button)v;
        String name = b.getText().toString();
        playWeapDisp.setText(name);

        // Set Player's Weapon

        switch(name){
            case "ROCK":
                player = Weapon.ROCK;
                break;
            case "PAPER":
                player = Weapon.PAPER;
                break;
            default:
                player = Weapon.SCISSORS;
        }

        // Set Computer's Weapon

        computer = getCompWeapon();

        // Compare and Get Winning/Losing Message

        String message = compare();

        // Display Computer's Weapon

        compWeapDisp.setText(computer.toString());

        // Display Winning/Losing Message
        winDisp.setText(message);

        // Increment Win Counter(s)
        if (playerWinner == true){

            playWinCounter ++;
            playWinCntDis.setText(Integer.toString(playWinCounter));
        }
        else if(playerWinner == false){

            compWinCounter ++;
            compWinCntDis.setText(Integer.toString(compWinCounter));
        }

    }

    private String compare() {

        String message = "";

        if (player == computer) {
            return "The game is a draw!";
        }

        else {

            if (computer == Weapon.ROCK) {

                if (player == Weapon.PAPER) {
                    message = "Paper wraps rock.";
                    playerWinner = true;
                }

                else if (player == Weapon.SCISSORS) {
                    message = "Rock blunts scissors.";
                    playerWinner = false;
                }

            }
            else if (computer == Weapon.PAPER){
                if (player == Weapon.ROCK){
                    message = "Paper shrouds rock,";
                    playerWinner = false;
                }
                else if (player == Weapon.SCISSORS){
                    message = "Paper gets shredded by scissors";
                    playerWinner = true;
                }

            }
            else if(computer == Weapon.SCISSORS){
                if (player == Weapon.PAPER){
                    message = "Paper get shredded by scissors";
                    playerWinner=false;
                }
                else if(player == Weapon.ROCK){
                    message = "Rock crushes scissors";
                    playerWinner=true;
                }
            }
        }

        return message;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
