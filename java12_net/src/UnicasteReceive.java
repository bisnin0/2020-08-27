import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UnicasteReceive {
	DatagramSocket ds;
	DatagramPacket dp;
	public UnicasteReceive() {
	}
	
	public void start() {
		try {
			ds = new DatagramSocket(12000);
			//12000으로 대기중
			
			//받을 Packet.. 
			//보낼때는 보낼데이터가 있으니 그 데이터의 크기만큼 보내는데 받는쪽은 보내는쪽에서 얼만큼 보낼지 모른다.
			//그래서 데이터 없는 배열을 만들어서 데이터를 저장할 준비를 한다. 배열을 얼만한걸 만들어야하나? 권장하는건 512바이트 들어갈 공간.
			//보내는쪽과 받는쪽이 약속한다 보낼데이터크기 받을데이터크기
			byte data[] = new byte[512]; //받을 수 있는 최대 크기는 512로 하겠다.
			dp = new DatagramPacket(data, data.length); //공간 확보
			
			//받을 준비
			System.out.println("받기 대기중...");
			ds.receive(dp); //데이터를 여기에 받을거야 하고 dp객체를 알려줌 ..옆에서 데이터 보내면 이시점에 받는다.
			//////////////데이터가 올때까지 여기서 기다리고있는다.. 
			
			//전송받은 데이터 얻어오기
			byte receiveData[] = dp.getData();
			String text1 = new String(receiveData); //전송받은걸 문자로 바꾼것.
			///이렇게하면 문자열뿐 아니라 선언한 배열의 크기인 512바이트 크기만큼 전부 출력된다.  
			
			int txtCount = dp.getLength();//받은 문자수
			String text = new String(receiveData,0, txtCount); //글자열의 0번부터 글자열의 길이까지 출력한다.

			
			System.out.println("받은 문자"+ text1+"=====================================");
			//txt1의 길이가 너무 길어서 =========== 이 선이 오른쪽 끝에 가있는걸 확인할 수 있음
			System.out.println("받은 문자"+ text+"=====================================");
			System.out.println("받기완료..");
			
		}catch(Exception e) {
			
		}
	}
	
	
	public static void main(String[] args) {
		new UnicasteReceive().start();
	}
}
