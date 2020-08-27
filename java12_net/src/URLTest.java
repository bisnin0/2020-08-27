import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

	public URLTest() {
		start();
	}
	public void start() {
		try {
		URL url = new URL("https://www.naver.com");//������ ����.. ����ó��
		System.out.println("protocol="+url.getProtocol());//��� �Ծ�
		System.out.println("host="+url.getHost());
		System.out.println("path="+url.getPath());
		System.out.println("port="+url.getPort());
		System.out.println("filename="+url.getFile());

		//Page Header��ü ������ // �ѱ��ڵ� �� ���� �ִ��� ����.
		//1. URL��ü�� �̿��ؼ� URLConnection�����Ѵ�.
		URLConnection connection = url.openConnection(); 
		//2. ���ä�� Ȯ��.
		connection.connect(); //����� ��������
		//3. ��� ���� ������
		String contentType = connection.getContentType();
		System.out.println("contentType-->"+contentType);
		//text/html; charset=UTF-8�� ���´�. ���⼭ UTF-8 ���� charset=�� ��ġ�� ���ؼ� 
		
		//charset=��ġ ���ϱ�
		int idx=contentType.indexOf("charset=");// indexOf�� ����charset=����ġ �˾Ƴ����� c������ ��ġ�� ��������. �ܾ��� ������ġ
		String code = contentType.substring(idx+8); //������ ���� ������ �ִ°� �ʿ��ϱ⶧���� c������ ���� idx + charset=�� ���� 8 �ϸ� ����
		System.out.println("code="+code); 
		
		
		InputStream is = url.openStream(); //inputStream�� byte������. ���ڴ����� �о���� ó������
		InputStreamReader isr = new InputStreamReader(is, code);//�ѱ��ھ� �о���°�. ���۷� ���پ� �о���� ó������ char�� �о�����Ѵ�.
		////////////////////////////////////////////////////////
		//InputStreamReader�� �ѱ��� ó������ �о���°�  ������ ���� code��ü�� �־��ָ� ������ �ѱ��� ���������� ���δ�. ///
		BufferedReader br = new BufferedReader(isr); //���۷� ���پ� �а� ����
		
		while(true) {
			String inData = br.readLine();
			if(inData == null) break;
			System.out.println(inData);
			
		}
		
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new URLTest();
	}

}
