import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Chaos {
	static public void main(String []args){
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("Empty");
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}