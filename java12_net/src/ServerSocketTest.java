import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public ServerSocketTest() {
		try {
		//������ ����� �� �����ϸ� Socket ��ü�� ���� �� �ִ�.
		ServerSocket ss  =new ServerSocket(20000); //��Ʈ��ȣ�� 65500�����ϱ� �� ���̿� �ִ°� ����
		//�̰� ��ü�� ����Ű� ������ ��ٸ��� �ʰ��ִ°�.
		//������ ����ϴ� �޼ҵ尡 ���� �ִ�.. ������ �����ϱ� ��ٸ��±��, 
		//�����������ϸ� �����ѻ���� inet��巹���� �����ؼ� ������ ��������
		//���Ӿ��ϸ� ���⼭ ��� �����
		
		while(true) { //���Ӹ��ϸ� ���ڸ� ������ ���ѷ��� �������� ����ϵ���
			//���Ӵ�� : �������� ip�� �������. 
			System.out.println("server start....");
			System.out.println("���Ӵ����....");
			Socket socket = ss.accept();
			////////////
			//���ڿ����� �ַܼ� �ΰ��� ���ÿ� �����Ҽ��������� dos�� ���� Ȯ���Ұ�
			
			//�������� InetAddress��ü
			InetAddress ia = socket.getInetAddress();
			System.out.println(ia.getHostAddress()+"���� �����Ͽ����ϴ�..");
			
			//�����ڿ��� ���ں����� �غ�
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os); //�ѱ۷ν���ϴϱ�
			PrintWriter pw = new PrintWriter(osw); // 
			
			//���ں�����. ps->osw->os->socket
			pw.println("������ ���� �Ǿ����ϴ�.. ȯ���մϴ�."); //���� ���� ������. ���� �����ϰ� �ִ� ��ǻ�Ϳ���
			pw.flush();
			//flush()�޼���� ���ۿ��ִ� �����͸� ��� ���Ͽ� ���� ���� ���۸� ���� �޼���
			
			
			//Ŭ���̾�Ʈ�� �������� �ޱ�
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String msg = br.readLine();
			System.out.println("Ŭ���̾�Ʈ�κ��� ���� ����-->"+ msg);
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}

	public static void main(String[] args) {
		new ServerSocketTest();
	}

}
