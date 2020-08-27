import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticasteReceive {
	InetAddress ia;
	MulticastSocket ms;
	DatagramPacket dp;
	public MulticasteReceive() {
	}
	
	public void start() {
		try {
			ia = InetAddress.getByName("230.1.1.11"); //�������Ƿ� �ο����� �������ּ�
			ms = new MulticastSocket(20000);
			
			
			ms.joinGroup(ia); //������ ia�� �����Ѵ�.
			//////////////
			
			//�ޱ� ���
			byte[] data = new byte[512];
			dp = new DatagramPacket(data, data.length);
			System.out.println("�����");
			ms.receive(dp);
			
			//���۹��� ���
			byte rData[] = dp.getData();
			int len = dp.getLength();
			System.out.println("���� ����="+ new String(rData, 0, len));
			ms.close();
		}catch(Exception e) {}
	}
	public static void main(String[] args) {
		new MulticasteReceive().start();
	}

}