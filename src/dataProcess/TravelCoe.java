package dataProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.GetVehInfo;;

public class TravelCoe {
	
	private static String preDataPath = "I:\\programData\\chongqingCoeCal\\preData";
	//private static String travelCoePath = "I:\\programData\\chongqingCoeCal\\travelCoePerMonth";//���·�
	private static String travelCoePath = "I:\\programData\\chongqingCoeCal\\travelCoePerYear";//�����
	private static String oDInstancePath = "I:\\pangqian\\data\\cqϵ������\\�շ�վ��̾���20160623104640.csv";
	
	public static void checkExsistence(String path){
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static void writeData(String path, Map<String, ArrayList<String>> dataMap) {
		// д�ļ�
		System.out.println(path + "  writing !");
		try {
			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(path), "utf-8");
			BufferedWriter writer = new BufferedWriter(writerStream);
			writer.write("���ID,����ID,����,����,��ͨ����,��ʱ��,����,����ϵ��" + "\n");
			for (String key : dataMap.keySet()) {//��һ��ͨ�������ڶ�����ʱ�䣬���������
				ArrayList<String> list = dataMap.get(key);
				String info = list.get(0) + "," +list.get(1) + "," + list.get(2);
				double travelCoe = (Double.parseDouble(list.get(1))/Double.parseDouble(list.get(0)))/Double.parseDouble(list.get(2));
				writer.write(key + "," + info + "," + travelCoe);
				
				writer.write("\n");
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(path + "  writed !");
	}
	
	public static void writeProData(String path, Map<String, String> dataMap) {
		// д�ļ�
		System.out.println(path + "  writing !");
		try {
			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(path), "utf-8");
			BufferedWriter writer = new BufferedWriter(writerStream);

			for (String key : dataMap.keySet()) {//��һ��ͨ�������ڶ�����ʱ�䣬���������
				writer.write(key);
				
				writer.write("\n");
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(path + "  writed !");
	}
	
	public static Map<String, String> getODInstance(String path) {
		
		Map<String, String> oDInstance = new HashMap();//�洢��OD��OD������ӳ��
		
		InputStreamReader inStream;
		try {
			inStream = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader reader = new BufferedReader(inStream);
			
			String line = "";
			while((line = reader.readLine()) != null) {
				String lineArray[] = line.split(",");
				
				if(lineArray.length != 3) {
					continue;
				}
				String enId = lineArray[0];
				String exId = lineArray[1];
				String instance = lineArray[2];
				
				oDInstance.put(enId + "," + exId, instance);
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return oDInstance;
	}
	
	//���·ݼ���
	/*
	 * public static void travelCoeCal(String preDataPath, String travelCoePath,
	 * String oDInstancePath) { Map<String, ArrayList<String>> oDCoeMap = new
	 * HashMap<>(); Map<String, String> oDInstanceMap =
	 * getODInstance(oDInstancePath);
	 * 
	 * File file = new File(preDataPath); List<String> list =
	 * Arrays.asList(file.list());
	 * 
	 * try { for (int i = 0; i < list.size(); i++) { //���δ���ÿһ���ļ��� String path =
	 * preDataPath + "/" + list.get(i); File fileIn = new File(path); List<String>
	 * listIn = Arrays.asList(fileIn.list());
	 * 
	 * String year = ""; Map<String, String> proOD = new HashMap<>(); //int count =
	 * 0;//�������㹲�ж���������δ�������̾��� for(int j = 0;j < listIn.size(); j++){ //���δ���ÿһ���ļ�
	 * String pathIn = path+"/"+listIn.get(j);
	 * 
	 * InputStreamReader inStream = new InputStreamReader(new
	 * FileInputStream(pathIn), "UTF-8"); BufferedReader reader = new
	 * BufferedReader(inStream);
	 * 
	 * String line = reader.readLine(); String[] lineArray;
	 * 
	 * while ((line = reader.readLine()) != null) { lineArray = line.split(",");
	 * if(lineArray.length == 8) {
	 * 
	 * String exId = lineArray[0].trim();//���ڱ�� String enId =
	 * lineArray[1].trim();//��ڱ�� String date = lineArray[2].trim().substring(0,
	 * 10);//���� year = date.substring(0, 4); double travelTime =
	 * Double.parseDouble(lineArray[4].trim());//����ʱ�� String vehTypeCode =
	 * lineArray[5].trim();//���ʹ��� String vehFlag = lineArray[7].trim();//�ͻ�����ʶ int
	 * vehType = GetVehInfo.getVehicleType(vehFlag, vehTypeCode);//������� String
	 * instance = oDInstanceMap.get(enId + "," + exId);
	 * 
	 * String key = enId + "," + exId + "," + date + "," + vehType;
	 * if(oDCoeMap.containsKey(key)){//��һ��ͨ�������ڶ�����ʱ�䣬��������� ArrayList<String>
	 * infoList = oDCoeMap.get(key);
	 * 
	 * int txl = Integer.parseInt(infoList.get(0)); txl++; infoList.set(0, txl +
	 * "");
	 * 
	 * double tatalTime = Double.parseDouble(infoList.get(1)); tatalTime +=
	 * travelTime; infoList.set(1, tatalTime + "");
	 * 
	 * oDCoeMap.put(key, infoList); }else { ArrayList<String> infoList = new
	 * ArrayList<>(); infoList.add("1"); infoList.add(travelTime + "");
	 * infoList.add(instance);
	 * 
	 * if(instance == null) { proOD.put(enId + "," + exId, ""); }else {
	 * oDCoeMap.put(key, infoList); } }
	 * 
	 * }else{ System.out.println(line); }
	 * 
	 * } reader.close();
	 * 
	 * System.out.println(pathIn + " read finish!");
	 * 
	 * String out = travelCoePath + "/" + list.get(i); checkExsistence(out); String
	 * outPath = out + "/" + (listIn.get(j).replace("txt", "csv"));
	 * writeData(outPath, oDCoeMap); oDCoeMap = new HashMap<>(); }
	 * 
	 * writeProData(travelCoePath + "/" + year + "pro.csv", proOD);
	 * System.out.println(proOD.size()); } } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * System.out.println("******************16-19������Ԥ�������*************"); }
	 */

	//����ݼ���
	public static void travelCoeCal(String preDataPath, String travelCoePath, String oDInstancePath) {
		Map<String, ArrayList<String>> oDCoeMap = new HashMap<>();
		Map<String, String> oDInstanceMap = getODInstance(oDInstancePath);
		
		File file = new File(preDataPath);
		List<String> list = Arrays.asList(file.list());		
		
		try {
			for (int i = 0; i < list.size(); i++) {
				//���δ���ÿһ���ļ���
				String path = preDataPath + "/" + list.get(i);
				File fileIn = new File(path);
				List<String> listIn = Arrays.asList(fileIn.list());
				
				String year = "";
Map<String, String> proOD = new HashMap<>();
//int count = 0;//�������㹲�ж���������δ�������̾���
				for(int j = 0;j < listIn.size(); j++){
					//���δ���ÿһ���ļ�
					String pathIn = path+"/"+listIn.get(j);
		
					InputStreamReader inStream = new InputStreamReader(new FileInputStream(pathIn), "UTF-8");
					BufferedReader reader = new BufferedReader(inStream);

					String line = reader.readLine();
					String[] lineArray;
					
					while ((line = reader.readLine()) != null) {
						lineArray = line.split(",");
						if(lineArray.length == 8) {
													
							String exId = lineArray[0].trim();//���ڱ��
							String enId = lineArray[1].trim();//��ڱ��
							String date = lineArray[2].trim().substring(0, 10);//����
							year = date.substring(0, 4);
							double travelTime = Double.parseDouble(lineArray[4].trim());//����ʱ��
							String vehTypeCode = lineArray[5].trim();//���ʹ���
							String vehFlag = lineArray[7].trim();//�ͻ�����ʶ
							int vehType = GetVehInfo.getVehicleType(vehFlag, vehTypeCode);//�������
							String instance = oDInstanceMap.get(enId + "," + exId);
							
							String key = enId + "," + exId + "," + date + "," + vehType;
							if(oDCoeMap.containsKey(key)){//��һ��ͨ�������ڶ�����ʱ�䣬���������
								ArrayList<String> infoList = oDCoeMap.get(key);
								
								int txl = Integer.parseInt(infoList.get(0));
								txl++;
								infoList.set(0, txl + "");
								
								double tatalTime = Double.parseDouble(infoList.get(1));
								tatalTime += travelTime;
								infoList.set(1, tatalTime + "");
								
								oDCoeMap.put(key, infoList);
							}else {
								ArrayList<String> infoList = new ArrayList<>();
								infoList.add("1");
								infoList.add(travelTime + "");
								infoList.add(instance);
								
								if(instance == null) {
									proOD.put(enId + "," + exId, "");
								}else { 
									oDCoeMap.put(key, infoList);
								}
							}

						}else{
							System.out.println(line);
						}
				
					}
					reader.close();

					System.out.println(pathIn + " read finish!");
				}
				
				String outPath = travelCoePath + "/" + year + ".csv";
				writeData(outPath, oDCoeMap);
				oDCoeMap = new HashMap<>();
				
writeProData(travelCoePath + "/" + year + "pro.csv", proOD);
System.out.println(proOD.size());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("******************16-19������Ԥ�������*************");
	}
	
	
	public static void main(String[] args) {
		travelCoeCal(preDataPath, travelCoePath, oDInstancePath);
	}

}
