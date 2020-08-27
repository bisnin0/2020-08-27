import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FrileUnicasteSend extends JFrame implements ActionListener{
	JButton btn = new JButton("���������ϱ�");
	
	public FrileUnicasteSend() {
		
		add(btn);
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		btn.addActionListener(this);
	}
	
	public void start() {
		try {
			JFileChooser fc = new JFileChooser();
			int state = fc.showOpenDialog(this);
			if(state == 0){ 
				//���⼱�ý� ���������ϱ�
				File file = fc.getSelectedFile();
				//���ϸ� ������
				String fileName = file.getName(); //getPath�� ��ο� ���ϸ� ��
//				System.out.println(fileName);
				
				InetAddress ia = InetAddress.getByName("192.168.0.214");
//				DatagramSocket ds = new DatagramSocket(15000, ia); //�̷��� �ϴϱ� �Ⱥ�������. 
				DatagramSocket ds = new DatagramSocket();
				
				// 1. ���ϸ� ������ 	
				String sendFilename = "[*filename*]"+fileName;
				DatagramPacket dp = new DatagramPacket(sendFilename.getBytes(), sendFilename.getBytes().length, ia, 15000);
				ds.send(dp); //�̷����ϸ� ���ϸ��� �Ѿ�� �ݴ����� ���� �غ� �س�����Ѵ�.
				
				// 2. ��������
				FileInputStream fis = new FileInputStream(file);
				while(true) {
					byte data[] = new byte[512]; //�ѹ��� ���� ������ �� ����.. �޴��ʵ� 512
					int cnt = fis.read(data, 0, data.length);//�迭�� 0��°��ġ���� �迭�� ũ�⸸ŭ ��ƶ�.
					//���� �������� �����ϱ� ���̻� ������ ������ 0�� ���ƿ´�.
					if(cnt>0) { //0���� Ŭ���� �����͸� ������ �ϰ�
						dp = new DatagramPacket(data, cnt, ia, 15000); //�����͸� ��Ŷ���θ���
						ds.send(dp);
					}else {
						fis.close();
						break;
					}
					//�̷��Ը��ϸ� ���� �ս��� �Ͼ.. �ֳĸ� ���ۼӵ��� ����ӵ��� �����󰡱⶧����. ���Ⱑ �Ϸ�Ǳ����� �����̳��������⶧��
					Thread.sleep(100); //�ѹ� ������ 0.1�� ���� �ϴ� ������ ���� ��� �ȱ�����.
					//�� ������� �̹� ����ó�� �Ǿ��ִ�. ����ó�� �ȿ����Ŷ�
				}
				
				// 3. �������� �˸�
				String lastMsg = "[*lastdata*]end";
				dp = new DatagramPacket(lastMsg.getBytes(), lastMsg.getBytes().length, ia, 15000);
				ds.send(dp);
				System.out.println("������Ϸ�");
				
			}
			
			
		}catch(Exception e) {}
	}
	
	public void actionPerformed(ActionEvent ae) {
		start();
	}
	public static void main(String[] args) {
		new FrileUnicasteSend();
	}

}
