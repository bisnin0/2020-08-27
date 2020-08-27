import java.net.InetAddress;

public class InetAddressTest {

	public InetAddressTest() {
	}
	
	public void start() {
		try {
		//ip, domain�� �̿��Ͽ� inetAdress��ü�� �����Ͽ� ����Ѵ�. ���������� �����Ѵ�.
		
		//�� ��ǻ���� ������ ������
		InetAddress ia1 = InetAddress.getLocalHost(); //���ܹ߻�
		//domain ���ϱ�
		String hostName = ia1.getHostName(); //domain, domain�� ������� ��ǻ�� �̸��� ���Ѵ�.
		//ip���ϱ�
		String address = ia1.getHostAddress();
		
		System.out.println(hostName + ", "+ address);
		
		//url�ּҸ� �̿��� InetAddress��ü ���ϱ�
		InetAddress ia2 = InetAddress.getByName("www.naver.com");
		System.out.println("ia2.address="+ia2.getHostAddress()); //ip //�ּ�â�� ip�ּ�ġ�� ���̹� ���Ӱ���
		System.out.println("ia2.name="+ia2.getHostName());//domain
		
		//210.89.160.88 ==��� ���� ���̹� ip
		//ip�� �̿��� InetAddress��ü����
		InetAddress ia3 = InetAddress.getByName("210.89.160.88"); //ia3�� �̿��� �� ������ �ּҿ� ��� ����
		System.out.println("ia3.address=" + ia3.getHostAddress()); 
		System.out.println("ia3.name="+ ia3.getHostName());//ip�� �������� domain�� �������� �ʴ´�.
		
		
		//ip�� �������϶�
		InetAddress ia4[] = InetAddress.getAllByName("www.naver.com");
		System.out.println("-------------------------");
		for(InetAddress ia: ia4) { //�����ʿ� �迭�̸� ���ʿ� �� �迭�� ������ Ÿ��
			System.out.println("ia.name="+ia.getHostName());
			System.out.println("ua.address="+ia.getHostAddress());
		}
		InetAddress ia5[] = InetAddress.getAllByName("www.joara.com");
		System.out.println("-------------------------");
		for(InetAddress ia: ia5) { //�����ʿ� �迭�̸� ���ʿ� �� �迭�� ������ Ÿ��
			System.out.println("ia.name="+ia.getHostName());
			System.out.println("ua.address="+ia.getHostAddress());
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new InetAddressTest().start(); 
	}

}
