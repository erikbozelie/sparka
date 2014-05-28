import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class exampleJpanel extends JFrame {
	JButton jbutton;
	TCPClient client;
	exampleJpanel(TCPClient client){
		this.client = client;
		JPanel jpanel = new JPanel();
		
		jbutton = new JButton("send data");
		jpanel.add(jbutton);
		this.add(jpanel);
		this.setVisible(true);
		setSize(200, 100);
		jbutton.addActionListener(printListener);
	}
	
	ActionListener printListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("test verzonden");
			client.sendData("test");
		}
	    };
	
}
