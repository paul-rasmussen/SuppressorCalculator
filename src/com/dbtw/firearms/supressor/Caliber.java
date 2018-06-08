package com.dbtw.firearms.supressor;

public enum Caliber {
	C22(21000, ".22", 0.22), 
	C223(55000, ".223", 0.223), 
	C556(55000, "5.56", 5.56), 
	C9(35000, "9mm", 9.0), 
	C762x39(45000, "7.62x39", 7.62), 
	C762x51(60000, ".308 (7.62x51)", 0.308), 
	C357(35000, ".357", 0.357), 
	C45(21000, ".45 ACP", 0.45);

	private final int chamberPressure;
	private final double diameter;
	private String description;

	Caliber(int cPressure, String desc, double cDiameter) {
		chamberPressure = cPressure;
		diameter = cDiameter;
		description = desc;
	}

	public int getChamberPressure() {
		return chamberPressure;
	}

	public double getDiameter() {
		return diameter;
	}
	
	public String getDescription() {
	  return description;
	}
	
}
