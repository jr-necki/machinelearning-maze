
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class Reinforcement {

   ArrayList<Integer> newPoint = new ArrayList<Integer>();//새 포인트
   ArrayList<Integer> points = new ArrayList<Integer>();//지금까지의 포인트 저장
   
   int reward = 50;
   String[] moveType = { "상", "하", "좌", "우" };
   int temp1 = 0;
   int temp2 = 0;
   int size = 0;
   

   public ArrayList move(JLabel[][] grid, int r, int c) {
      newPoint.clear();
      String move = moveType[(int) (Math.random() * 4)];
      
      //현재 포인트 저장
      temp1 = r;
      temp2 = c;
      


      if (c == 0) {
         if (r == size) {
            // 우
            if (move.equals("우")) {
               c++;
            }

         } else {
            // 우하
            if (move.equals("우")) {
               c++;
            }else if (move.equals("하")) {
               r++;
            }
         }
      } else if (c == size) {
         if (r == 0) {
            // 우하
            if (move.equals("우")) {
               c++;
            }else if (move.equals("하")) {
               r++;
            }
         } else {
            // 하
            if (move.equals("하")) {
               r++;
            }
         }
      } else {
         if (r == size) {
            // 우
            if (move.equals("우")) {
               c++;
            }
         } else {
            // 우하
            if (move.equals("우")) {
               c++;
            }else if (move.equals("하")) {
               r++;
            }
         }
      }
      if(r>size||c>size) {//선밖으로 나갈때
         r=temp1;
         c=temp2;
      }
      if (grid[r][c].getBackground().equals(Color.black)||
            grid[r][c].getBackground().equals(Color.gray)) {//장애물 있을시
         reward-=5;
         if(reward==0) {//빠져나가기 불가 전 포인트로 가야함
            System.out.println("리워드 0");
            reward=50;
            newPoint.add(points.get(points.size()-4));
            newPoint.add(points.get(points.size()-3));
            
         }else {//현재포인트로..
            newPoint.add(temp1);
            newPoint.add(temp2);
         }
         
      }else {
         //없다면 새로운 포인트
         newPoint.add(r);
         newPoint.add(c);
      }


      return newPoint;

   }

   public void setSize(int SIZE) {
      this.size = SIZE - 1;

   }

   public void giveList(ArrayList<Integer> pointList) {
      this.points=pointList;
      
   }

}