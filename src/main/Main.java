package main;

import dataProcess.DataPrePro;
import dataProcess.TravelCoe;

public class Main {
	
	//Ԥ����
	private static String dataOri = "/home/CQData";
	private static String dataRes = "/home/pq/ϵ������/programData/preData";
	
	//����ϵ������
	private static String travelCoePath = "/home/pq/ϵ������/programData/travelCoePerYear";
	private static String oDInstancePath = "/home/pq/ϵ������/����ļ�/�շ�վ��̾���20160623104640.csv";

	public static void main(String[] args) {
		//DataPrePro.preData(dataOri, dataRes);
		//DataPrePro.moveToOneFile(dataRes, dataRes);
		TravelCoe.travelCoeCal(dataRes, travelCoePath, oDInstancePath);
	}

}
