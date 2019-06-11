package dao;

public class GetVehInfo {
	
	/**
	 * ���س���������
	 * @param khFlag �ͻ���־λ
	 * @param vehType ���ʹ���
	 * @return 0��С�ͳ���  1�������Ϳͳ���  2��1�ͻ�����  3��2�ͻ�����  4��3�ͻ�����  5��4�ͻ�����  6��5�ͻ�����
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
