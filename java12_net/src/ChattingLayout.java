import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChattingLayout extends JFrame {
	Font font = new Font("����ü", Font.PLAIN, 13);
	int membercnt = 0;
	
	JPanel mainPane = new JPanel(new BorderLayout());
		JPanel topPane = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)); 
			JTextField topJf = new JTextField();
			JButton conBtn = new JButton("����");
			JLabel topLbl = new JLabel("������ ����Ʈ", JLabel.CENTER);
		
		JTextArea leftTa = new JTextArea();	
		JScrollPane leftJp = new JScrollPane(leftTa);//���� ��ũ��
		JList rightLt = new JList();
		JScrollPane rightJp = new JScrollPane(rightLt);//������ ��ũ��
		
		JPanel bottomPane = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)); 
			JTextField botJf = new JTextField();
			JButton outputBtn = new JButton("������");
			JLabel botLbl = new JLabel(" ����� : "+membercnt+"��");	
		
	
	public ChattingLayout() {
		setLayout(new FlowLayout());
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		topLbl.setFont(font); botLbl.setFont(font); conBtn.setFont(font); outputBtn.setFont(font);
		
		mainPane.setBorder(new EmptyBorder(15,15,15,15));

		mainPane.setOpaque(false);
		topPane.setOpaque(false);
		bottomPane.setOpaque(false);
		leftTa.setBackground(new Color(0,0,139));
		leftTa.setForeground(Color.WHITE);
		topJf.setBackground(new Color(135,206,250));
		botJf.setBackground(new Color(135,206,250));
		leftJp.setPreferredSize(new Dimension(270,150));
		rightLt.setPreferredSize(new Dimension(130,1000));
		rightJp.setPreferredSize(new Dimension(150,150));
		topLbl.setPreferredSize(new Dimension(150,30));
		botLbl.setPreferredSize(new Dimension(150,30));
		topJf.setPreferredSize(new Dimension(238,27));
		botJf.setPreferredSize(new Dimension(228,27));
		
		topPane.add(topJf); topPane.add(conBtn); topPane.add(topLbl);
		bottomPane.add(botJf); bottomPane.add(outputBtn); bottomPane.add(botLbl); 
		
		mainPane.add(BorderLayout.NORTH, topPane); 
		mainPane.add(BorderLayout.CENTER, leftJp); mainPane.add(BorderLayout.EAST, rightJp);
		mainPane.add(BorderLayout.SOUTH, bottomPane);
		
		add(mainPane);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new ChattingLayout();
	}

}
