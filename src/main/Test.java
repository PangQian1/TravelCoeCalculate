package main;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	

	//获取时间差方法    
    public static long getTime(Date currentTime,Date firstTime){
        long diff = currentTime.getTime() - firstTime.getTime();//这样得到的差值是微秒级别
        System.out.println(diff);
       
        return diff;
    }


	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date t1 = stampToDate("2016/01/28 00:00:19.000000000");
		Date t2 = stampToDate("2016/01/29 00:00:29.000000000");
		String aString = "2016/01/27 00:00:20.000000000";
		System.out.println(aString.length());
		System.out.println(aString.substring(0, 19));
		/*
		 * System.out.println(sdf.parse(t1).getTime());
		 * System.out.println(sdf.parse(t2).getTime());
		 * System.out.println(sdf.parse(t1).getTime()-sdf.parse(t2).getTime()); long
		 * timeInterval = (sdf.parse(t1).getTime()-sdf.parse(t2).getTime())/1000/60/60;
		 */
		System.out.println(t1 + " " + t2);
		long timeInterval = t1.getTime()-t2.getTime();
		double res = timeInterval/(double)1000/60/60;

		System.out.println(timeInterval);
		System.out.println(res);
		//System.out.println(stampToDate(string));

	}

}
