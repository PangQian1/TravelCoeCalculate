package dataProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 * ��ԭʼ�������ݽ���Ԥ����
 * ���޵����������ݼ�¼�����޵�����ʱ��С�����ʱ�����ݣ��ۼ���ÿ����¼������ʱ�䣻�ܽ�ÿ����¼�ĳ����ID������ʱ�䣬����ʱ��д�����ļ�
 * @author PQ
 *
 */

public class DataPrePro {
	
	private static String data18 = "I:\\pangqian\\data\\cqϵ������\\proData";
	private static String data18res = "I:\\pangqian\\data\\cqϵ������\\res";
	
	public static void checkExsistence(String path){
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static String stampToDate(String s){
		if(s.equals("") || s == null || s.length() != 29){
			return "0";
		}
		
		s = s.substring(0, 20);
		s = s.replace(".", "").replace("/", "-");

		return s;
	}
	
	public static void preData(String inPath, String outPath) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		File file = new File(inPath);
		List<String> list = Arrays.asList(file.list());		
		
		for (int i = 0; i < list.size(); i++) {
			//���δ���ÿһ���ļ���
			String path = inPath + "/" + list.get(i);
			File fileIn=new File(path);
			List<String> listIn=Arrays.asList(fileIn.list());
			
			for(int j = 0;j < listIn.size(); j++){
				//���δ���ÿһ���ļ�
				String pathIn=path+"/"+listIn.get(j);
				String pathOut = outPath + "/" + list.get(i);
				checkExsistence(pathOut);
				pathOut += "/" + listIn.get(j);
				
				// ��һ�м�¼дһ�м�¼
				try {
					InputStreamReader inStream = new InputStreamReader(new FileInputStream(pathIn), "UTF-8");
					BufferedReader reader = new BufferedReader(inStream);
					
					OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(pathOut), "utf-8");
					BufferedWriter writer = new BufferedWriter(writerStream);
					writer.write("���ڱ��,��ڱ��,����ʱ��,���ʱ��,����ʱ��,���ʹ���,�������,�ͻ�����ʶ\n");

					String line = "";
					String[] lineArray;
					
					ArrayList<String> records = new ArrayList<>();//��¼��������
					int count = 0;
					
					while ((line = reader.readLine()) != null) {
						count++;
						lineArray = line.split(";");
						if(lineArray.length >= 51) {
													
							String exId = lineArray[0].trim();//���ڱ��
							String exTime = stampToDate(lineArray[2].trim());//����ʱ��
							String enId = lineArray[9].trim();//��ڱ��
							String enTime = stampToDate(lineArray[10].trim());//���ʱ��	
							String vehTypeCode = lineArray[11].trim();//���ʹ���
							String vehClassCode = lineArray[13].trim();//�������
							String vehFlag = lineArray[28].trim();//�ͻ�����ʶ
							
							if(exTime.equals("0") || exTime.equals("0") || exId.equals("0") || enId.equals("0")){
								//System.out.println(line);
								continue;
							}
							
							Date exDate = sdf.parse(exTime);
							Date enDate = sdf.parse(enTime);
							long timeInterval = (exDate.getTime() - enDate.getTime());
							
							if(timeInterval < 0){
								continue;
							}
							
							double travelTime = timeInterval/(double)1000;
							
							String resRec = exId + "," + enId + "," + exTime + "," + enTime + "," + travelTime + ","
											+ vehTypeCode + "," + vehClassCode + "," + vehFlag + "\n";
							
							writer.write(resRec);
						}else{
							records.add("" + count);
							//System.out.println(line);
						}
				
					}
					reader.close();
					writer.flush();
					writer.close();
					System.out.println(records);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(pathIn + " read finish!");
				System.out.println(pathOut + " write finish!");
			}
		}
		
		System.out.println("******************16-19������Ԥ�������*************");
	}

	public static void main(String[] args) {
		preData(data18, data18res);
	}

}
