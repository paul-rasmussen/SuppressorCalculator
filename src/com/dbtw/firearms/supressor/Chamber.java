package com.dbtw.firearms.supressor;

import com.dbtw.firearms.supressor.calculation.ChamberVolume;
import com.dbtw.widgets.DebugWriter;

public class Chamber {
	private ChamberVolume volume = new ChamberVolume();

	public Chamber() {
	}

	public Chamber(double len, double dia) {
	  if (SuppressorCalculator.debug) {
	    DebugWriter.getInstance().write("Initiating with:");
	    DebugWriter.getInstance().write("      len: " + Double.toString(len));
	    DebugWriter.getInstance().write("      dia: " + Double.toString(dia));
	  }
	  volume.setLength(len);
	  volume.setDiameter(dia);
	}

	public void setLength(double len) {
		volume.setLength(len);
	}

	public void setDiameter(double dia) {
    volume.setDiameter(dia);;
	}
	
	public double getLength() {
		return volume.getLength();
	}

	public double getVolume() {
		return volume.getVolume();
	}
	
	public double getDiameter() {
	 return volume.getDiameter();
	}
	
	public String toJSONString() {
	  StringBuilder txt = new StringBuilder();
	  
	  txt.append("{\"chamber\":[");
	  txt.append("\"length\":\"" + Double.toString(volume.getLength()) + "\",");
    txt.append("\"diameter\":\"" + Double.toString(volume.getDiameter()) + "\"");
	  txt.append("]}");
	  
	  if (SuppressorCalculator.debug) {
	    DebugWriter.getInstance().write("JSON:");
	    DebugWriter.getInstance().write(txt.toString());
	  }
	  
	  return txt.toString();
	}
	
}
