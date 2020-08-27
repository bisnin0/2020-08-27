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
			//12000���� �����
			
			//���� Packet.. 
			//�������� ���������Ͱ� ������ �� �������� ũ�⸸ŭ �����µ� �޴����� �������ʿ��� ��ŭ ������ �𸥴�.
			//�׷��� ������ ���� �迭�� ���� �����͸� ������ �غ� �Ѵ�. �迭�� ���Ѱ� �������ϳ�? �����ϴ°� 512����Ʈ �� ����.
			//�������ʰ� �޴����� ����Ѵ� ����������ũ�� ����������ũ��
			byte data[] = new byte[512]; //���� �� �ִ� �ִ� ũ��� 512�� �ϰڴ�.
			dp = new DatagramPacket(data, data.length); //���� Ȯ��
			
			//���� �غ�
			System.out.println("�ޱ� �����...");
			ds.receive(dp); //�����͸� ���⿡ �����ž� �ϰ� dp��ü�� �˷��� ..������ ������ ������ �̽����� �޴´�.
			//////////////�����Ͱ� �ö����� ���⼭ ��ٸ����ִ´�.. 
			
			//���۹��� ������ ������
			byte receiveData[] = dp.getData();
			String text1 = new String(receiveData); //���۹����� ���ڷ� �ٲ۰�.
			///�̷����ϸ� ���ڿ��� �ƴ϶� ������ �迭�� ũ���� 512����Ʈ ũ�⸸ŭ ���� ��µȴ�.  
			
			int txtCount = dp.getLength();//���� ���ڼ�
			String text = new String(receiveData,0, txtCount); //���ڿ��� 0������ ���ڿ��� ���̱��� ����Ѵ�.

			
			System.out.println("���� ����"+ text1+"=====================================");
			//txt1�� ���̰� �ʹ� �� =========== �� ���� ������ ���� ���ִ°� Ȯ���� �� ����
			System.out.println("���� ����"+ text+"=====================================");
			System.out.println("�ޱ�Ϸ�..");
			
		}catch(Exception e) {
			
		}
	}
	
	
	public static void main(String[] args) {
		new UnicasteReceive().start();
	}
}
