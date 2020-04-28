package semi_project;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Mp3PlayList extends JFrame {
	
	Mp3View mp3;
	
	public Mp3PlayList() {
		mp3=new Mp3View();
		
		JTabbedPane pane=new JTabbedPane();
		pane.addTab("음악 정보 관리", mp3);
		
		pane.setSelectedIndex(0);
		add("Center",pane);
		
		setTitle("Mp3 Player");
		ImageIcon icon=new ImageIcon("img/music.jpg");
		setIconImage(icon.getImage());
		
		setSize(1000,700);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		new Mp3PlayList();
	}
}
