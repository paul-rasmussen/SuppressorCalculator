package com.dbtw.firearms.supressor.calculation;

import com.dbtw.firearms.supressor.SuppressorCalculator;
import com.dbtw.widgets.DebugWriter;

public class ChamberVolume {

	private double diameter = 0.0;
	private double length = 0.0;

	public static double PI = 3.1415927;

	public ChamberVolume() {
	}

	public ChamberVolume(double length, double diameter) {
	  if (SuppressorCalculator.debug) {
	    DebugWriter.getInstance().write(this.getClass().getName());
	    DebugWriter.getInstance().write("Alternative Constructor");
	    DebugWriter.getInstance().write("Setting values:]");
	    DebugWriter.getInstance().write("      diameter: " + Double.toString(diameter));
      DebugWriter.getInstance().write("      length: " + Double.toString(length));
	  }
		this.diameter = diameter;
		this.length = length;
	}

	public double getVolume() {
		double vol = 0.0;

		if (SuppressorCalculator.debug) {
		  DebugWriter.getInstance().write(this.getClass().getName());
		  DebugWriter.getInstance().write("getVolume()");
		}
		if ((diameter > 0.0) && (length > 0.0)) {
		  if (SuppressorCalculator.debug) {
		    DebugWriter.getInstance().write("Calculating volume from : " + Double.toString(diameter) + ", " + Double.toString(length));
		  }
			vol = (diameter * PI) * length;
		}

		return vol;
	}
	
	public void setDiameter(double dia) {
	  if (SuppressorCalculator.debug) {
	    DebugWriter.getInstance().write(this.getClass().getName());
	    DebugWriter.getInstance().write("setDiameter");
	    DebugWriter.getInstance().write("    recieved: " + Double.toString(dia));
	  }
	  diameter = dia;
	}
	
	public void setLength(double len) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("setLength");
      DebugWriter.getInstance().write("    recieved: " + Double.toString(len));
    }
	  length = len;
	}
	
	public double getDiameter() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("getDiameter");
      DebugWriter.getInstance().write("    returning: " + Double.toString(diameter));
    }
	  return diameter;
	}
	
	public double getLength() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("getLength");
      DebugWriter.getInstance().write("    returned: " + Double.toString(length));
    }
	  return length;
	}
	
}
