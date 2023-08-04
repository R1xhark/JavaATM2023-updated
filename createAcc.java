import java.util.Set;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import Liberty.Sqlconnector;

public class NewClient {

      private string name;
      private int id;
      private int card_number;
      private int pin;
      private int age;
      
      Scanner read=new Scanner();
      System.Out.Println("Dobrý den. Vítejte v Liberty banks.")
      

      public void createAcc(){
      System.Out.Println("Zadejte vaše jméno");
      name=read.NextLine();
      System.Out.Println("Zadejte prosím svůj věk");
      age=read.NextLine();

      if(age > 18 && age < 99 && age > 0){
            Statement nameAdd=
            
