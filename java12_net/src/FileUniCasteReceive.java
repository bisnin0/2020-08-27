import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FileUniCasteReceive {
	DatagramSocket ds;
	DatagramPacket dp;
	FileOutputStream os = null; //���Ϸ� ����ϴϱ�.. ���Ϸ� ����Ʈ������ ó���ϴ°�
	////�ٸ��޼ҵ忡�� os��ü�� �������� �������ؼ� �ɹ��� �ű�
	public FileUniCasteReceive() {
	}

	public void start() {
		try {
			DatagramSocket ds = new DatagramSocket(15000);
			
			byte data[] = new byte[512];
			DatagramPacket dp = new DatagramPacket(data, 0, data.length);
			
			
			
			while(true) { //�ѹ��� �ް� ���� �ƴϱ⶧���� �ݺ���.. ����ũ�⿡���� ������ ���ƾ��ϴϱ�
				System.out.println("���۹ޱ� �����..");
				
				ds.receive(dp);
				
				byte receiveData[] = dp.getData(); //send���� ���� �����Ͱ� ����ִ�.
				int length = dp.getLength(); //�����Ͱ� �󸶳� �ִ��� ������ length�� �������ִ�.
				
				String dataStr = new String(receiveData, 0, length); //�������ڼ� ��ŭ���� ���ڿ� ����
				if(length>12) { //send���� [*filename*]��� �����ϱ�. �̰� ���̰� 12
					//���ϸ�
					if(dataStr.substring(0,12).equals("[*filename*]")) { //������ ���ϸ������۵Ȱ�
						//FileoutputStram
						os = new FileOutputStream(new File("D://javaFolder", dataStr.substring(12)));
						
					}else if(dataStr.substring(0,12).equals("[*lastdata*]")) {
						//������ ..���� ������ �Ϸ�Ǹ� �̷�������Ѵ�
						os.close();
						System.out.println("�������� �Ϸ�..");
					}else {
					//���ϳ���
					fileWrite(receiveData, length);
					}
				}else {
					//���ϳ���
					fileWrite(receiveData, length);
				}
				
			}
		}catch(Exception e) {}
	}
	public void fileWrite(byte data[], int len) {
		try {
			os.write(data, 0, len); //�̷��Ը��ϸ� ���� �ս��� �Ͼ.. �ֳĸ� ���ۼӵ��� ����ӵ��� �����󰡱⶧����. ���Ⱑ �Ϸ�Ǳ����� �����̳��������⶧��
		}catch(Exception e) {}
	}
	
	public static void main(String[] args) {
		new FileUniCasteReceive().start();
	}

}
