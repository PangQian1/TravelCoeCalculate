package main;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static Date stampToDate(String s) throws ParseException{
		s = s.substring(0, 20);
		s = s.replace(".", "").replace("/", "-");
		System.out.println(s);

		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf2.parse(s);

		return date;
	}
	
/*	public static String stampToDate(String s) throws ParseException{
		s = s.substring(0, 20);
		s = s.replace(".", "").replace("/", "-");
		
		String rs;
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf1.parse(s);
		rs=sdf2.format(date);
		return rs;
	}*/
	

	//��ȡʱ����    
    public static long getTime(Date currentTime,Date firstTime){
        long diff = currentTime.getTime() - firstTime.getTime();//�����õ��Ĳ�ֵ��΢�뼶��
        System.out.println(diff);
       
        return diff;
    }


	public static void main(String[] args) throws ParseException {
		String aString = "2018-06-02 10:41:27";
		
		System.out.println(aString.substring(0, 12));
	}

}




/*
 * if(oDCoeMap.containsKey(key)){ Map<String, ArrayList<String>> infoMap =
 * oDCoeMap.get(enId + "," + exId); if(infoMap.containsKey(date + "," +
 * vehFlag)) {//��һ��ͨ�������ڶ�����ʱ�䣬��������� ArrayList<String> infoList =
 * infoMap.get(date + "," + vehFlag);
 * 
 * int txl = Integer.parseInt(infoList.get(0)); txl ++; infoList.set(0, txl +
 * "");
 * 
 * double totalTime = Double.parseDouble(infoList.get(1)); totalTime +=
 * travelTime; infoList.set(1, totalTime + ""); }else { ArrayList<String>
 * infoList = new ArrayList<>(); infoList.set(0, "1"); infoList.set(1,
 * travelTime + ""); infoList.set(2, instance); infoMap.put(date + "," +
 * vehFlag, infoList); } }else { Map<String, ArrayList<String>> infoMap = new
 * HashMap<>();
 * 
 * ArrayList<String> infoList = new ArrayList<>(); infoList.set(0, "1");
 * infoList.set(1, travelTime + ""); infoList.set(2, instance);
 * 
 * infoMap.put(date + "," + vehFlag, infoList); oDCoeMap.put(enId + "," + exId,
 * infoMap); }
 */