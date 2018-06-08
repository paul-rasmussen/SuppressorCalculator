package com.dbtw.firearms.supressor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import com.dbtw.firearms.supressor.calculation.ChamberVolume;
import com.dbtw.firearms.supressor.calculation.ParameterType;
import com.dbtw.firearms.supressor.calculation.Pressure;
import com.dbtw.widgets.DebugWriter;

public class Suppressor implements Serializable{
  private static final long serialVersionUID = 683930568L;
  
	private Caliber caliber;
	private double diameter = 0.0;
	private double wallThickness = 0.0;
	private double baffleThickness = 0.0;
	private Vector<Chamber> chambers = new Vector<Chamber>();

	public Suppressor(Caliber caliber) {
	  if (SuppressorCalculator.debug) {
	    DebugWriter.getInstance().write(this.getClass().getName());
	    DebugWriter.getInstance().write("    Constructor");
	    DebugWriter.getInstance().write("    Recieved:");
	    if (caliber != null) {
	      DebugWriter.getInstance().write("      caliber: " + caliber.toString());
	    }
	    else {
        DebugWriter.getInstance().write("      caliber: null");
	    }
	  }
		setCaliber(caliber);
	}

	public void setCaliber(Caliber cal) {
	  if (SuppressorCalculator.debug) {
	    DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    setCaliber");
      DebugWriter.getInstance().write("        recieved: " + cal.name());
	  }
		caliber = cal;
	}

	public void setDiameter(double dia) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    setDiameter");
      DebugWriter.getInstance().write("        recieved: " + Double.toString(dia));
    }
		diameter = dia;
		resetChambers();
	}

	public void setWallThickness(double thick) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    setWallThickness");
      DebugWriter.getInstance().write("        recieved: " + Double.toString(thick));
    }
		wallThickness = thick;
		resetChambers();
	}

	public void setBaffleThickness(double thick) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    setBaffleThickness");
      DebugWriter.getInstance().write("        thick: " + Double.toString(thick));
    }
	  baffleThickness = thick;
	}
	
	public void addChamber(double len) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    addChamber");
      DebugWriter.getInstance().write("        len: " + Double.toString(len));
    }
		chambers.addElement(new Chamber(len, diameter));
	}

	public Caliber getCaliber() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getCaliber");
      DebugWriter.getInstance().write("        returning: " + caliber.name());
    }
		return caliber;
	}

	public double getLength() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getLength");
      DebugWriter.getInstance().write("        returning: " + Double.toString(suppressorLength()));
    }
		return suppressorLength();
	}
	
	public double getChamberLength(int index) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getChamberLength");
      DebugWriter.getInstance().write("        received: " + Integer.toString(index));
      DebugWriter.getInstance().write("        returning: " + Double.toString(chambers.elementAt(index).getLength()));
    }
	  return chambers.elementAt(index).getLength();
	}
	
	public double getActualLength() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getActualLength");
      DebugWriter.getInstance().write("        returning: " + Double.toString(suppressorLength()));
    }
		return suppressorLength();
	}

	public double getDiameter() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getDiameter");
      DebugWriter.getInstance().write("        returning: " + Double.toString(diameter));
    }
		return diameter;
	}

	public double getWallThickness() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getWallThickness");
      DebugWriter.getInstance().write("        returning: " + Double.toString(wallThickness));
    }
		return wallThickness;
	}

	public double getBaffleThickness() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getBaffleThickness");
      DebugWriter.getInstance().write("        returning: " + Double.toString(baffleThickness));
    }
	  return baffleThickness;
	}

	public int getChamberCount() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getChamberCount");
      DebugWriter.getInstance().write("        returning: " + Double.toString(chambers.size()));
    }
		return chambers.size();
	}
	
	public void clearChambers() {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    clearChambers");
    }
	  chambers = new Vector<Chamber>();
	}
	
	public Chamber getChamber(int index) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getChamber");
      DebugWriter.getInstance().write("        received: " + Integer.toString(index));
      DebugWriter.getInstance().write("        returning: " + chambers.elementAt(index).toString());
    }
	  return chambers.elementAt(index);
	}

	public double getChamberVolume(int index) {
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getChamberVolume");
      DebugWriter.getInstance().write("        received: " + Integer.toString(index));
      DebugWriter.getInstance().write("        returning: " + Double.toString(chambers.elementAt(index).getVolume()));
    }
		return chambers.elementAt(index).getVolume();
	}

	public double getFinalPressure(double barrelLength) {
		double press = caliber.getChamberPressure();
		double vol = 0.0;
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getFinalPressure");
      DebugWriter.getInstance().write("        received: " + Double.toString(barrelLength));
    }
		try {
	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    getFinalPressure");
	      DebugWriter.getInstance().write("        Creating new ChamberVolume object");
	    }
			ChamberVolume barrel = new ChamberVolume(barrelLength, caliber.getDiameter());

	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    getFinalPressure");
	      DebugWriter.getInstance().write("        Determining new pressure");
	    }
			press = Pressure.pressureChange(press, vol, barrel.getVolume(), ParameterType.VOLUME);
			vol += barrel.getVolume();

	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    getFinalPressure");
	      DebugWriter.getInstance().write("        Entering chamber iteration");
	    }
			Iterator<Chamber> cells = chambers.iterator();
			while (cells.hasNext()) {
		    if (SuppressorCalculator.debug) {
		      DebugWriter.getInstance().write(this.getClass().getName());
		      DebugWriter.getInstance().write("    getFinalPressure");
		      DebugWriter.getInstance().write("        Calculating new pressure");
		    }
				Chamber c = cells.next();
				press = Pressure.pressureChange(press, vol, c.getVolume(), ParameterType.VOLUME);
				vol += c.getVolume();
			}
		} catch (Exception e) {
	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    getFinalPressure");
	      DebugWriter.getInstance().write("        Error:");
	      DebugWriter.getInstance().write(e.getMessage());
	    }
			e.printStackTrace(System.out);
		}

    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    getFinalPressure");
      DebugWriter.getInstance().write("        returning: " + Double.toString(press));
    }
		return press;
	}

	public String toJSONString() {
	  StringBuilder txt = new StringBuilder();
	  
	  txt.append("{\"suppressor\":[");
	  txt.append("\"caliber\":\"" + caliber.getDescription() + "\",");
    txt.append("\"diameter\":\"" + Double.toString(diameter) + "\",");
    txt.append("\"wallThickness\":\"" + Double.toString(wallThickness) + "\",");
    txt.append("\"baffleThickness\":\"" + Double.toString(baffleThickness) + "\",");
    txt.append("\"chambers\":[");
    Iterator<Chamber> cells = chambers.iterator();
    while (cells.hasNext()) {
      Chamber cell = cells.next();
      txt.append(cell.toJSONString());
    }
	  txt.append("]]}");
	  
    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    toJSONString");
      DebugWriter.getInstance().write("        returning: " + txt.toString());
    }
	  return txt.toString();
	}
	
	private void resetChambers() {
		Iterator<Chamber> cell = chambers.iterator();

    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    resetChambers");
      DebugWriter.getInstance().write("        Entering cell iterator");
    }
		while (cell.hasNext()) {
	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    resetChambers");
	      DebugWriter.getInstance().write("        Resetting diameter for cell");
	    }
			Chamber c = cell.next();
			c.setDiameter(diameter - (2 * wallThickness));
		}
	}

	private double suppressorLength() {
		double endCaps = 1.0;
		double totalLength = 0.0;

    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    suppressorLength");
      DebugWriter.getInstance().write("        Calculating endcaps and baffle contributions");
    }
		totalLength = endCaps + ((chambers.size() - 1) * baffleThickness);
		try {
	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    suppressorLength");
	      DebugWriter.getInstance().write("        Entering cell iteration");
	    }
			Iterator<Chamber> cell = chambers.iterator();

			while (cell.hasNext()) {
		    if (SuppressorCalculator.debug) {
		      DebugWriter.getInstance().write(this.getClass().getName());
		      DebugWriter.getInstance().write("    suppressorLength");
		      DebugWriter.getInstance().write("        Adding cell length");
		    }
				Chamber c = cell.next();
				totalLength += c.getLength();
			}
		} 
		catch (Exception e) {
	    if (SuppressorCalculator.debug) {
	      DebugWriter.getInstance().write(this.getClass().getName());
	      DebugWriter.getInstance().write("    suppressorLength");
	      DebugWriter.getInstance().write("        Error:");
        DebugWriter.getInstance().write(e.getMessage());
	    }
			e.printStackTrace(System.out);
		}

    if (SuppressorCalculator.debug) {
      DebugWriter.getInstance().write(this.getClass().getName());
      DebugWriter.getInstance().write("    suppressorLength");
      DebugWriter.getInstance().write("        returning: " + Double.toString(totalLength));
    }
		return totalLength;
	}
	
}
