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
	private static String travelCoeFilePath = "I:\\programData\\chongqingCoeCal\\travelCoePerYear01\\16-19出行系数结果.csv";
	private static String txlIntegrationFilePath = "I:\\programData\\chongqingCoeCal\\通行量统计.csv";
	
	public static void writeData(String path, Map<String, String> dataMap) {
		// 写文件
		System.out.println(path + "  writing !");
		try {
			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(path), "utf-8");
			BufferedWriter writer = new BufferedWriter(writerStream);

			for (String key : dataMap.keySet()) {// 第一项通行量，第二项总时间，第三项距离
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

		Map<String, String> txlMap = new HashMap();// 存储了txl与对应次数的映射

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
