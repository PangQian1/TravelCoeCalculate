package dataEvaluation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class TXLEvaluation {
	private static String travelCoeFilePath = "I:\\programData\\chongqingCoeCal\\travelCoePerYear01\\16-19����ϵ�����.csv";
	private static String txlIntegrationFilePath = "I:\\programData\\chongqingCoeCal\\ͨ����ͳ��.csv";
	
	public static void writeData(String path, Map<String, String> dataMap) {
		// д�ļ�
		System.out.println(path + "  writing !");
		try {
			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(path), "utf-8");
			BufferedWriter writer = new BufferedWriter(writerStream);

			for (String key : dataMap.keySet()) {// ��һ��ͨ�������ڶ�����ʱ�䣬���������
				writer.write(key + "," + dataMap.get(key));

				writer.write("\n");
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(path + "  writed !");
	}
	
	public static void txlIntegration(String travelCoeFilePath, String txlIntegrationFilePath) {

		Map<String, String> txlMap = new HashMap();// �洢��txl���Ӧ������ӳ��

		InputStreamReader inStream;
		try {
			inStream = new InputStreamReader(new FileInputStream(travelCoeFilePath), "UTF-8");
			BufferedReader reader = new BufferedReader(inStream);

			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String lineArray[] = line.split(",");

				if (lineArray.length != 8) {
					continue;
				}
				String txl = lineArray[4];
				
				if(txlMap.containsKey(txl)) {
					int num = Integer.parseInt(txlMap.get(txl));
					num++;
					txlMap.put(txl, "" + num);
				}else {
					txlMap.put(txl, "1");
				}

			}

			reader.close();
			
			writeData(txlIntegrationFilePath, txlMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		txlIntegration(travelCoeFilePath, txlIntegrationFilePath);
	}

}
