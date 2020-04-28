package semi_project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import semi_project.Mp3Model;
import semi_project.Mp3;

public class Mp3View extends JPanel implements ActionListener{
	ImageIcon[] imgs; 
	JLabel[] la;
	JPanel imgjp;
	int num[];
	
	JTextField tfPLNO, tfMtitle, tfAtitle, tfSinger, tfRtime, tfRdate, tfMsearch;
	JComboBox comMgenre, comMsearch;
	JTextArea taAC;
	JButton bMI, bMM, bMD;

	JTable tableM;
	
	Mp3TableModel tbModelMp3;
	Mp3Model model;
	
	public Mp3View() {
		addLayout();
		evenProc();
		connectDB();	
	}
	
	public void connectDB() {
		try {
			model=new Mp3Model();
			System.out.println("mp3 DB����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void evenProc() {
		bMI.addActionListener(this);
		bMM.addActionListener(this);
		bMD.addActionListener(this);
		tfMsearch.addActionListener(this);
		
		tableM.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					num=new int[1];
					for (int i = 0; i < la.length; i++) {
						num[i]=Integer.parseInt(tfPLNO.getText());
						
						la[i].setIcon(imgs[num[i]]);
					}
				}
				
				int row=tableM.getSelectedRow();
				int col=0;
				String data=(String) tableM.getValueAt(row, col);
				int no=Integer.parseInt(data);
				
				try {
					Mp3 m=model.selectbyPk(no);
					//���� vo�� ���ڵ尡 ��� vo
					selectbyPk(m);
					
				} catch (Exception e1) {					
				}				
			}
		});
	}
	public void selectbyPk(Mp3 m) {
		tfPLNO.setText(String.valueOf(m.getPLNO()));
		tfMtitle.setText(m.getMtitle());
		tfAtitle.setText(m.getAtitle());
		tfSinger.setText(m.getSinger());
		tfRtime.setText(m.getRtime());
		tfRdate.setText(m.getRdate());
		taAC.setText(m.getAC());
		comMgenre.setSelectedItem(m.getGenre());
	}
	
	private void addLayout() {
		//VideoView ȭ�鱸��
		tfPLNO=new JTextField();
		tfMtitle=new JTextField();
		tfAtitle=new JTextField();
		tfSinger=new JTextField();
		tfRtime=new JTextField();
		tfRdate=new JTextField();
		
		//�帣�� ���� �迭�� ó��
		String [] cbGenreStr= {"Pop","SynthPop","EdmPop","Soul","Hiphop"};
		comMgenre=new JComboBox(cbGenreStr);
		
		taAC=new JTextArea();
		
		bMI=new JButton("�߰�");
		bMM=new JButton("����");
		bMD=new JButton("����");
		
		String[] cbMSearch= {"���","����"};
		comMsearch=new JComboBox(cbMSearch);
		tfMsearch=new JTextField(15);
		
		//���� ���̺� ����
		tbModelMp3=new Mp3TableModel();
		tableM=new JTable(tbModelMp3);
		tableM.setModel(tbModelMp3);
		
		//�ǳ�1 ȭ�� ����
		JPanel p_west=new JPanel();
		p_west.setLayout(new BorderLayout());
		
		//�ǳ�2 ȭ�� ����
		JPanel p_west_center=new JPanel();
		p_west_center.setLayout(new BorderLayout());
		
		//�ǳ�3 ȭ�� ����
		JPanel p_west_center_north=new JPanel();
		p_west_center_north.setLayout(new GridLayout(7,2));
		
		//�ǳ�3 �޺� ����
		p_west_center_north.add(new JLabel("PlayList ��ȣ"));
		p_west_center_north.add(tfPLNO);
		p_west_center_north.add(new JLabel("���"));
		p_west_center_north.add(tfMtitle);
		p_west_center_north.add(new JLabel("�ٹ���"));
		p_west_center_north.add(tfAtitle);
		p_west_center_north.add(new JLabel("����"));
		p_west_center_north.add(tfSinger);
		p_west_center_north.add(new JLabel("�帣"));
		p_west_center_north.add(comMgenre);
		p_west_center_north.add(new JLabel("����ð�"));
		p_west_center_north.add(tfRtime);		
		p_west_center_north.add(new JLabel("�߸���"));
		p_west_center_north.add(tfRdate);
		
		//�ǳ�4 ȭ�� ����
		JPanel p_west_center_center=new JPanel();
		imgs=new ImageIcon[12];

		imgjp=new JPanel();
		imgjp.setLayout(new BorderLayout());
		
		la=new JLabel[1];
		
		for (int i = 0; i < imgs.length; i++) {
			imgs[i]=new ImageIcon("img/"+i+".jpg");
		}for (int i = 0; i < la.length; i++) {
			la[i]=new JLabel();
			la[i].setIcon(imgs[0]);
			imgjp.add(la[i]);
		}
			
		p_west_center_center.add(new JLabel("�ٹ�Ŀ��"),BorderLayout.WEST);
		p_west_center_center.add(imgjp,BorderLayout.CENTER);
		
		//�ǳ�3,4�� �ǳ�2�� ����
		p_west_center.add(p_west_center_north,BorderLayout.NORTH);
		p_west_center.add(p_west_center_center,BorderLayout.CENTER);
		
		//border �����
		p_west_center.setBorder(new TitledBorder("���� ����"));
		
		//�ǳ�5 west_south
		JPanel p_west_south=new JPanel();
		p_west_south.setLayout(new GridLayout(1,3));
		p_west_south.add(bMI);
		p_west_south.add(bMM);
		p_west_south.add(bMD);
		
		p_west.add(p_west_center,BorderLayout.CENTER);
		p_west.add(p_west_south,BorderLayout.SOUTH);
		
		//���� �ǳ�
		JPanel p_east=new JPanel();
		p_east.setLayout(new BorderLayout());
		
		JPanel p_east_north=new JPanel();
		p_east_north.add(comMsearch);
		p_east_north.add(tfMsearch);
		p_east_north.setBorder(new TitledBorder("���� �˻�"));
		
		p_east.add(p_east_north,BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableM),BorderLayout.CENTER);
		
		setLayout(new GridLayout(1,2));
		add(p_west);
		add(p_east);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		
		if (evt==bMI) {
			InsertMp3();
		}else if (evt==bMM) {
			Modify();
		}else if (evt==bMD) {
			delete();
		}else if (evt==tfMsearch) {
			System.out.println("�˻�");
			int idx=comMsearch.getSelectedIndex();
			String str=tfMsearch.getText();
			
			try {
				ArrayList data=model.searchMp3(idx,str);
				
				tbModelMp3.data=data;
				tableM.setModel(tbModelMp3);
				tbModelMp3.fireTableDataChanged();
				//������ ���� �����͸� ���̺� �ݿ�
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
	}
	
	private void delete() {
		
			Mp3 m=new Mp3();
			//Video Ŭ������ ����(����)�ϱ� ����
			m.setPLNO(Integer.parseInt(tfPLNO.getText()));
			m.setMtitle(tfMtitle.getText());
			m.setAtitle(tfAtitle.getText());
			m.setSinger(tfSinger.getText());
			m.setRtime(tfRtime.getText());
			m.setRdate(tfRdate.getText());
			m.setAC(taAC.getText());
			m.setGenre((String)comMgenre.getSelectedItem());
			
			try {
				model.delete(m);
				JOptionPane.showMessageDialog(null, "���� ����");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	private void Modify() {
		Mp3 m=new Mp3();
		//Video Ŭ������ ����(����)�ϱ� ����
		m.setPLNO(Integer.parseInt(tfPLNO.getText()));
		m.setMtitle(tfMtitle.getText());
		m.setAtitle(tfAtitle.getText());
		m.setSinger(tfSinger.getText());
		m.setRtime(tfRtime.getText());
		m.setRdate(tfRdate.getText());
		m.setAC(taAC.getText());
		m.setGenre((String)comMgenre.getSelectedItem());
		
		try {
			model.modifyMp3(m);
			JOptionPane.showMessageDialog(null, "���� �Ϸ�");
			tfPLNO.setText(null);
			tfMtitle.setText(null);
			tfAtitle.setText(null);
			tfSinger.setText(null);
			tfRtime.setText(null);
			tfRdate.setText(null);
			taAC.setText(null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void InsertMp3() {
		
			System.out.println("insertbtn press");
			Mp3 m=new Mp3();
			m.setPLNO(Integer.parseInt(tfPLNO.getText()));
			m.setMtitle(tfMtitle.getText());
			m.setAtitle(tfAtitle.getText());
			m.setSinger(tfSinger.getText());
			m.setGenre((String) comMgenre.getSelectedItem());
			m.setRtime(tfRtime.getText());
			m.setRdate(tfRdate.getText());
//			m.setAC(taAC.getText());
	
			try {
				model.insertMp3(m);
				JOptionPane.showMessageDialog(null, "�߰� �Ϸ�");				
				tfMtitle.setText(null);
				tfAtitle.setText(null);
				tfSinger.setText(null);
				tfRtime.setText(null);
				tfRdate.setText(null);
				taAC.setText(null);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
		}
	
	class Mp3TableModel extends AbstractTableModel{

		ArrayList data=new ArrayList<>();
		String[] columnNames= {"PlayListNo.","���","�ٹ���","����","�帣","����ð�","�߸���"};
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp=(ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}				
	}
}

