package com.example.pubsos.Task2;

import com.example.pubsos.Task1.PubSubBroker;
import com.example.pubsos.Task1.Subscriber;

public class Controller
{
    private int numberofS;
    private int numberOfO;
    private String curLetter;
    private  int p1score;
    private  int p2score;
    public Controller(String curLetter, int numberOfO, int numberofS, int p1score, int p2score)
    {
        this.curLetter = curLetter;
        this.numberOfO = numberOfO;
        this.numberofS = numberofS;
    }
    public void registerEvents()
    {
        // Get the broker.
        PubSubBroker broker = PubSubBroker.getInstance();

        // Some subscribers.
        Subscriber s01 = (publisher, topic, params)
                -> Checkletter(curLetter,numberofS,numberOfO);
        Subscriber s02 = (publisher, topic, params)
                -> countSos();
        Subscriber s03 = (publisher, topic, params)
                -> getNumberOfO(numberOfO);
        Subscriber s04 = (publisher, topic, params)
                -> getNumberOfO(numberofS);
        Subscriber s05 = (publisher, topic, params)
                -> scoreSum(p1score,p2score);

        // Subscribe
        broker.subscribe("Check Letter", s01);
        broker.subscribe("Number of O's", s03);
        broker.subscribe("goodbye", s02);
    }
    public void countSos()
    {
        //Counts the number of SOS completed.

    }
    public void Checkletter(String curLetter, int numberofS, int numberOfO)
    {

        if(curLetter=="S")
        {
            numberofS++;
        }
        if(curLetter=="O")
        {
            numberOfO++;
        }

    }

    public int getNumberOfO(int numberOfO)
    {
        return numberOfO;

    }
    public int getNumberofS(int numberofS)
    {
        return numberofS;

    }

    public int scoreSum(int p1ScoreVal, int p2ScoreVal)
    {
        return p1ScoreVal+p2ScoreVal;
    }
    public int getPlayer1Turns(int player1Turns)
    {
        return player1Turns;
    }
    public int getPlayer2Turns(int player2Turns)
    {
        return player2Turns;
    }

}
