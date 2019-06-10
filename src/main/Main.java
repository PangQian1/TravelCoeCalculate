package main;

import dataProcess.DataPrePro;

public class Main {
	
	private static String dataOri = "/home/CQData";
	private static String dataRes = "/home/pq/ÏµÊý¼ÆËã/programData/preData";

	public static void main(String[] args) {
		DataPrePro.preData(dataOri, dataRes);
	}

}
