package com.mycompany.banksim;

/**
 *
 * @author R1xhark
 */
import java.util.Scanner;

public class BankSim {

    public static void main(String[] args) {
        ATM atm = new ATM();
        NewClient klient=new NewClient();
        Scanner read=new Scanner(System.in);
        
        System.out.println("Co si prejes?");
        System.out.println("1.Vytvor clienta");
        System.out.println("2.Emuluj ATM");
        System.out.println("------------");
        System.out.println("Tvuj vyber?");
        
        int mainMenuInput=read.nextInt();
        
        if(mainMenuInput==1){
            klient.createAcc();
        }
        else if(mainMenuInput==2){
            atm.start(); // cannot find symbol ??????
        }
    }
}
