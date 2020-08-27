import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FileUniCasteReceive {
	DatagramSocket ds;
	DatagramPacket dp;
	FileOutputStream os = null; //파일로 써야하니까.. 파일로 바이트단위로 처리하는것
	////다른메소드에서 os객체를 공동으로 쓰기위해서 맴버로 옮김
	public FileUniCasteReceive() {
	}

	public void start() {
		try {
			DatagramSocket ds = new DatagramSocket(15000);
			
			byte data[] = new byte[512];
			DatagramPacket dp = new DatagramPacket(data, 0, data.length);
			
			
			
			while(true) { //한번만 받고 말게 아니기때문에 반복문.. 파일크기에따라 여러번 돌아야하니까
				System.out.println("전송받기 대기중..");
				
				ds.receive(dp);
				
				byte receiveData[] = dp.getData(); //send에서 보낸 데이터가 담겨있다.
				int length = dp.getLength(); //데이터가 얼마나 있는지 정보는 length가 가지고있다.
				
				String dataStr = new String(receiveData, 0, length); //받은문자수 만큼으로 문자열 만듬
				if(length>12) { //send에서 [*filename*]라고 썼으니까. 이거 길이가 12
					//파일명
					if(dataStr.substring(0,12).equals("[*filename*]")) { //같으면 파일명이전송된것
						//FileoutputStram
						os = new FileOutputStream(new File("D://javaFolder", dataStr.substring(12)));
						
					}else if(dataStr.substring(0,12).equals("[*lastdata*]")) {
						//마지막 ..파일 전송이 완료되면 이루어져야한다
						os.close();
						System.out.println("파일전송 완료..");
					}else {
					//파일내용
					fileWrite(receiveData, length);
					}
				}else {
					//파일내용
					fileWrite(receiveData, length);
				}
				
			}
		}catch(Exception e) {}
	}
	public void fileWrite(byte data[], int len) {
		try {
			os.write(data, 0, len); //이렇게만하면 파일 손실이 일어남.. 왜냐면 전송속도를 쓰기속도가 못따라가기때문에. 쓰기가 완료되기전에 전송이끝나버리기때문
		}catch(Exception e) {}
	}
	
	public static void main(String[] args) {
		new FileUniCasteReceive().start();
	}

}
