import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
	Scanner scan = new Scanner(System.in);
	
	public SocketTest() {
		try {
		InetAddress ia = InetAddress.getByName("192.168.0.214");
		
		int port = 20000;
		Socket socket = new Socket(ia, port); //serverSocketTest�� 20000���� �����. ���⼭ ���ӳ���
		/////////////////�� �������� ������ �̷������.
		
		
		/////server�� �޽����� �ޱ�
		/////Server���� ���� socket�� output�� �޾Ƽ� is->isr->br->msg
		InputStream is = socket.getInputStream();//����Ʈ������ �о�´�. �ѱ� ����
		InputStreamReader isr = new InputStreamReader(is); //���ڴ����� �о����. �ѱ۾ȱ�����. �ѱ��ھ�
		BufferedReader br = new BufferedReader(isr); //�����͸� ���پ� �б�
		
		String msg = br.readLine();
		System.out.println("�����κ��� ���� ����->"+msg);
		
		///////////
		
		//Ŭ���̾�Ʈ�� ������ ���� ������
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(osw);
	
		pw.println("�� Ŭ���̾�Ʈ ���ڸ� ������ ����...");
		pw.flush();
		
		while(true) {
			System.out.println("���������Է�=");
			String data = scan.nextLine(); //���� �о���°�
			socket = new Socket(ia, port);//������ �Ҷ����� �����ϰ� �׷��� ������.. 
			//���⼱ socket�� ����Ǹ鼭 �������⶧���� �������� ���ο� socket�� ���� ������ ������ �ݺ� 
			os = socket.getOutputStream();
			osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			
			pw.println(data);
			pw.flush();
		}
		
		
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("Ŭ���̾�Ʈ ���α׷��� ����Ǿ����ϴ�.");
	}
	

	public static void main(String[] args) {
		new SocketTest();
	}

}
