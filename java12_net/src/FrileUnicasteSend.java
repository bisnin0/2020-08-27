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
	JButton btn = new JButton("파일전송하기");
	
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
				//열기선택시 파일전송하기
				File file = fc.getSelectedFile();
				//파일명 얻어오기
				String fileName = file.getName(); //getPath는 경로와 파일명 다
//				System.out.println(fileName);
				
				InetAddress ia = InetAddress.getByName("192.168.0.214");
//				DatagramSocket ds = new DatagramSocket(15000, ia); //이렇게 하니까 안보내지네. 
				DatagramSocket ds = new DatagramSocket();
				
				// 1. 파일명 보내기 	
				String sendFilename = "[*filename*]"+fileName;
				DatagramPacket dp = new DatagramPacket(sendFilename.getBytes(), sendFilename.getBytes().length, ia, 15000);
				ds.send(dp); //이렇게하면 파일명이 넘어갔다 반대편에서 받을 준비를 해놨어야한다.
				
				// 2. 파일전송
				FileInputStream fis = new FileInputStream(file);
				while(true) {
					byte data[] = new byte[512]; //한번에 보낼 데이터 수 지정.. 받는쪽도 512
					int cnt = fis.read(data, 0, data.length);//배열의 0번째위치부터 배열의 크기만큼 담아라.
					//읽은 데이터의 갯수니까 더이상 읽을게 없으면 0이 돌아온다.
					if(cnt>0) { //0보다 클때만 데이터를 전송을 하고
						dp = new DatagramPacket(data, cnt, ia, 15000); //데이터를 패킷으로만듬
						ds.send(dp);
					}else {
						fis.close();
						break;
					}
					//이렇게만하면 파일 손실이 일어남.. 왜냐면 전송속도를 쓰기속도가 못따라가기때문에. 쓰기가 완료되기전에 전송이끝나버리기때문
					Thread.sleep(100); //한번 보내고 0.1초 쉬고 하는 식으로 텀을 줘야 안깨진다.
					//위 쓰레드는 이미 예외처리 되어있다. 예외처리 안에쓴거라서
				}
				
				// 3. 마지막을 알림
				String lastMsg = "[*lastdata*]end";
				dp = new DatagramPacket(lastMsg.getBytes(), lastMsg.getBytes().length, ia, 15000);
				ds.send(dp);
				System.out.println("보내기완료");
				
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
