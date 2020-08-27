import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/*
  	224.0.0.0 ~ 239.255.255.255
 	
	230.1.1.11 - 내가 임의로 받은 아이피주소
 */
public class MulticasteSend {
	InetAddress ia;
	MulticastSocket ms;
	DatagramPacket dp;
	public MulticasteSend() {
	}
	public void start() {
		try {
			ia = InetAddress.getByName("230.1.1.11"); //내 아이피 주소
			ms = new MulticastSocket(); //이건 아이피 없이 그냥 만들어도 된다.
			
			//보낼데이터
			String data = "java 네트워크 테스트중....";
			dp = new DatagramPacket(data.getBytes(), data.getBytes().length, ia, 20000);
			
			ms.send(dp); //보내기
			ms.close();
			System.out.println("전송완료...됨");
		}catch(Exception e) {}
		
	}
	
	public static void main(String[] args) {
		new MulticasteSend().start();
	}

}
