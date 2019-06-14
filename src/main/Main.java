package main;

import dataProcess.DataPrePro;
import dataProcess.TravelCoe;

public class Main {
	
	//预处理
	private static String dataOri = "/home/CQData";
	private static String dataRes = "/home/pq/系数计算/programData/preData";
	
	//出行系数计算
	private static String travelCoePath = "/home/pq/系数计算/programData/travelCoePerYear";
	private static String oDInstancePath = "/home/pq/系数计算/相关文件/收费站最短距离20160623104640.csv";

	public static void main(String[] args) {
		//DataPrePro.preData(dataOri, dataRes);
		//DataPrePro.moveToOneFile(dataRes, dataRes);
		TravelCoe.travelCoeCal(dataRes, travelCoePath, oDInstancePath);
	}

}
