package game.view;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.vo.Diamond;
import game.vo.Hail;

public class GameView extends JFrame {
//	20개의 우박 객체 참조값이 저장되는 배열
	Hail[] hails = new Hail[20]; 							
	JLabel[] lblHails = new JLabel[hails.length];		
	Diamond[] diamonds = new Diamond[hails.length / 2];
	JLabel[] lblDiamonds = new JLabel[diamonds.length];
	
	public GameView(int si) {
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		Random random = new Random();
		HailThread hThread = null;
		DiamondThread dThread = null;
		
//		20개의 우박 객체를 생성해서 배열에 저장
		for (int i = 0; i < hails.length; i++) {
			hails[i] = new Hail();
			hails[i].setX(i * 50);
			hails[i].setY(i * random.nextInt(50));
			hails[i].setW(40);
			hails[i].setH(40);
			hails[i].setImgName("images/ddong.png");
			hails[i].setPoint(10);
			lblHails[i] = new JLabel(new ImageIcon(hails[i].getImgName()));
			lblHails[i].setBounds(hails[i].getX(), hails[i].getY(), hails[i].getW(), hails[si].getY());
			add(lblHails[i]);
			hThread = new HailThread(lblHails[i], hails[i]);
			hThread.start();
		}
		
//		10개의 다이아몬드 객체를 생성해서 배열에 저장
		
		for (int i = 0; i < diamonds.length; i++) {
			diamonds[i] = new Diamond();
			diamonds[i].setX(i * 50 + random.nextInt(500));
			diamonds[i].setY(i * random.nextInt(30));
			diamonds[i].setW(40);
			diamonds[i].setH(30);
			diamonds[i].setImgName("images/mushroom.png");
			diamonds[i].setPoint(20);
			lblDiamonds[i] = new JLabel(new ImageIcon(diamonds[i].getImgName()));
			lblDiamonds[i].setBounds(diamonds[i].getX(), diamonds[i].getY(), diamonds[i].getW(), diamonds[i].getY());
			add(lblDiamonds[i]);
			dThread = new DiamondThread(lblDiamonds[i], diamonds[i]);
			dThread.start();
		}
		
		setTitle("우박을 피해봐");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(10, 10, 1000, 600);
		setVisible(true);
//		setResizable(false);
	}
	
	public class HailThread extends Thread{
		JLabel hailLbl;
		Hail hail;
		
		public HailThread(JLabel hailLbl, Hail hail) {
			this.hailLbl = hailLbl;
			this.hail = hail;
		}
		
		
		@Override
		public void run() {
			while (true) {
				Random random = new Random();
				if( hailLbl.getY() <= 600)
					hailLbl.setLocation(hailLbl.getX(), hailLbl.getY() + 10);
				else {
					hailLbl.setLocation(hailLbl.getX(), random.nextInt(70));
				}
					
				
				try {
					sleep(20 * random.nextInt(50));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	
	public class DiamondThread extends Thread{
		JLabel diamondLbl;
		Diamond diamond;
		
		public DiamondThread(JLabel diamondLbl, Diamond diamond) {
			this.diamondLbl = diamondLbl;
			this.diamond = diamond;
		}
		
		
		@Override
		public void run() {
			while (true) {
				Random random = new Random();
				if( diamondLbl.getY() <= 600)
					diamondLbl.setLocation(diamondLbl.getX(), diamondLbl.getY() + 15);
				else {
					diamondLbl.setLocation(diamondLbl.getX(), random.nextInt(30));
				}
					
				
				try {
					sleep(10 * random.nextInt(40));
				} catch (InterruptedException e) {
	
				}
			}

		}
	}
	
}