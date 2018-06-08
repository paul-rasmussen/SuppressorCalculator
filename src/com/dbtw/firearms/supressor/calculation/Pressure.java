package com.dbtw.firearms.supressor.calculation;

import com.dbtw.firearms.supressor.SuppressorCalculator;
import com.dbtw.widgets.DebugWriter;

public class Pressure {
  
	public Pressure() {
	}
	
	 public static double pressureChange(double pressure, double volume, double changedValue, ParameterType changedParameter) throws Exception {
	    double newValue = 0.0;

	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write("Pressure");
	      DebugWriter.getInstance().write("pressureChange()");
	      DebugWriter.getInstance().write("    pressure: " + Double.toString(pressure));
        DebugWriter.getInstance().write("    volume: " + Double.toString(volume));
        DebugWriter.getInstance().write("    changedValue: " + Double.toString(changedValue));
        DebugWriter.getInstance().write("    changedParameter: " + changedParameter.name());
	    }

	    if ((changedParameter == ParameterType.VOLUME) || (changedParameter == ParameterType.PRESSURE)) {
	      if (SuppressorCalculator.debug) {
	        DebugWriter.getInstance().write("Calculating new pressure from:");
	        DebugWriter.getInstance().write("      Pressure: " + Double.toString(pressure));
	        DebugWriter.getInstance().write("      volume: " + Double.toString(volume));
	        DebugWriter.getInstance().write("      changedValue: " + Double.toString(changedValue));
	        DebugWriter.getInstance().write("      parameter: " + changedParameter.name());
	      }
	      newValue = (pressure * volume) / changedValue;
	    } 
	    else {
	      if (SuppressorCalculator.debug) {
	        DebugWriter.getInstance().write("    Received invalid parameter.");
	      }
	      throw new Exception("Invalid changed parameter");
	    }

	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write("    Returning: " + Double.toString(newValue));
	    }
	    return newValue;
	 }

}
