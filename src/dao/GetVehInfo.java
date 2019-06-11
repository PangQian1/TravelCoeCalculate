package dao;

public class GetVehInfo {
	
	/**
	 * 返回车辆的类型
	 * @param khFlag 客货标志位
	 * @param vehType 车型代码
	 * @return 0（小客车）  1（大中型客车）  2（1型货车）  3（2型货车）  4（3型货车）  5（4型货车）  6（5型货车）
	 */
	public static int getVehicleType(String khFlag, String vehType) {
		
		if(  khFlag.equals("0")  && Integer.valueOf(vehType) <= 1)return 0;
		if(  khFlag.equals("0")  && Integer.valueOf(vehType) >  1)return 1;
		if((!khFlag.equals("0")) && Integer.valueOf(vehType) <= 1)return 2;
		if((!khFlag.equals("0")) && Integer.valueOf(vehType) == 2)return 3;
		if((!khFlag.equals("0")) && Integer.valueOf(vehType) == 3)return 4;
		if((!khFlag.equals("0")) && Integer.valueOf(vehType) == 4)return 5;
		if((!khFlag.equals("0")) && Integer.valueOf(vehType) == 5)return 6;
		
		return -1;
	}
	
}
