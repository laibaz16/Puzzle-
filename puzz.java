import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*; 
import java.net.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;
import java.awt.Toolkit;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class puzz extends JFrame implements ActionListener,MouseListener{ 
  //Variable and GUIObject Declaration area 
  BufferedImage img,BG;
  Image second;
  MyDrawPanel draw1=new MyDrawPanel();
  int count=0,count2=0;
  int check[]=new int[16];
  int rand[]=new int[16];
  int x[]= new int[16];
  int y[]= new int[16];
  int mx,my,winc;
  int xx,yy,move=0,picN=1;
  boolean done=true,wind=true;
  public static void main(String[ ] args) 
  {
    new puzz();
  }  
  
  public puzz (){
    //Create object and your code goes here
    try {
      img = ImageIO.read(new File("org.png"));
////      BG = ImageIO.read(new File("gg.jpg"));
    } catch (IOException e) {
      //e.printStackTrace();
    }
//    second=Toolkit.getDefaultToolkit().createImage("2nd.gif");
    randi();
   
    this.add(draw1);
    addMouseListener(this);
    this.setSize(600,500);
    this.setVisible(true);
    //repaint();
  }
  public void randi(){
    count=0;
    count2=0;
    for(int mm=0;mm<16;mm++){
      do{
        rand[mm]=(int )(Math.random() * 16);
      }while(check[rand[mm]]==1);
      check[rand[mm]]=1;
      System.out.println(""+rand[mm]);
    }
    for(int ba=0;ba<4;ba++){
      for(int ab=0;ab<4;ab++){
        x[count]=ab*110;
        y[count]=ba*110;
        count++;
      }
    }
    repaint();
  }
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}
  public void mouseClicked(MouseEvent evt){
    mx=evt.getX();
    my=evt.getY();
    int weeboo=mx/110;
    int weebuu=my/110;
    int temp=weeboo+weebuu*4;
    //System.out.println(""+weeboo+" "+weebuu+"serial    "+temp);
    //rand[temp]=15;  
    if(mx<550&&mx>450&&my<435&&my>415){
      for(int ab=0;ab<16;ab++){
        rand[ab]=-1;
        check[ab]=0;
      }
      randi();
      move=0;
      mx=0;
      my=0;
      wind=false;
      if(picN==1){
        picN=2;
      }
      else if(picN==2){
        picN=1;
      }
      repaint();
    }
    System.out.println(""+mx+my);
    for(int ab=0;ab<16;ab++){
      if(rand[ab]==15){
        int temp2=ab;
        if (temp2-1==temp&&temp2!=0&&temp2!=4&&temp2!=8&&temp2!=12){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
        }
        else if (temp2+1==temp&&temp2!=3&&temp2!=7&&temp2!=11&&temp2!=15){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
        }
        else if (temp2-4==temp&&temp2!=0&&temp2!=1&&temp2!=2&&temp2!=3){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
        }
        else if (temp2+4==temp&&temp2!=12&&temp2!=13&&temp2!=14&&temp2!=15){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
        }
        //System.out.println(""+temp2);
      }
    }
    repaint();
  }
  public void mouseEntered(MouseEvent evt){}
  public void mouseExited(MouseEvent evt){}
  public void win(){
    for(int deez=0;deez<16;deez++){
      if(rand[deez]==deez){
        winc+=1;
      }
    }
    if(winc==16){
      wind=true;
    }
    else{
      winc=0;
    }
  }
  public void actionPerformed(ActionEvent e){}
  class MyDrawPanel extends JPanel
  {
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.white);
      g2.setFont(new Font("Impact",Font.BOLD,16));
      g2.drawImage(BG,0,0,600,500,null);
      if(picN==1){
        g2.drawImage(img,460,0,120,120,null);
      }
      else if(picN==2){
        g2.drawImage(second,460,0,120,120,null);
      }
      //g2.drawImage(img,460,0,120,120,null);
       g2.drawString("Block Missing:",440,180);
      g2.drawString("Right Bottom Corner",440,200);
      g2.drawString("Moves: "+move,440,230);
      if(wind==true){
        g2.drawString("You Win",440,250);
        g2.drawString("Press Reset to Reset",440,270);
      }
      g2.drawRect(440,385,100,20);
      g2.drawString("Reset",465,400);
      for(int bb=0;bb<4;bb++){
        for(int ba=0;ba<4;ba++){
          if(rand[count2]!=15){
            if(picN==1){
              g2.drawImage(img,x[count2],y[count2],x[count2]+110,y[count2]+110,x[rand[count2]],y[rand[count2]],x[rand[count2]]+110,y[rand[count2]]+110,null);
            }
            else if(picN==2){
              g2.drawImage(second,x[count2],y[count2],x[count2]+110,y[count2]+110,x[rand[count2]],y[rand[count2]],x[rand[count2]]+110,y[rand[count2]]+110,null);
            }
          }
          count2++;
        }
      }
      count2=0;
    }
  }
}