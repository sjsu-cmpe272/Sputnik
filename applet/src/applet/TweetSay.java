package applet;

import javax.swing.JFrame;


public class TweetSay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame j= new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(1400,400);
		Applets a=new Applets();
		j.setContentPane(a);
		j.setVisible(true);
	}

}
