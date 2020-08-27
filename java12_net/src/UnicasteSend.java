import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicasteSend {
	DatagramSocket ds;
	InetAddress ia;
	DatagramPacket dp;
	
	public UnicasteSend() {
	}
	
	public void start() {
		//UDP통신 -> Unicaste통신 1:1
		try {
			ia = InetAddress.getByName("192.168.0.214"); //보낼곳의 아이피
			ds = new DatagramSocket();
//			ds = new DatagramSocket(12000, ia); //이거 에러나네? 안쓰는게 나을듯하다고함
			
			String data = "Spring FrameWork, 스프링 프레임워크";
			
			//보낼 데이터를 Packget으로 생성한다.
			//							보낼 데이터		,  보낼데이터의 길이		  , InetAddress(ip), 포트번호
			dp = new DatagramPacket(data.getBytes(),data.getBytes().length, ia, 12000);
			
			//데이터 보내기
			ds.send(dp); //이시점에 데이터가 넘어간다.
			
			System.out.println("전송완료...");
		}catch(Exception e) {
			
		}
		
	}

	public static void main(String[] args) {
		new UnicasteSend().start();
	}

}
