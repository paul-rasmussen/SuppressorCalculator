package com.dbtw.firearms.supressor.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dbtw.firearms.supressor.Caliber;
import com.dbtw.firearms.supressor.Suppressor;
import com.dbtw.firearms.supressor.calculation.ChamberVolume;

class SuppressorTest {
  Suppressor suppressor = new Suppressor(Caliber.C223);
  
  public SuppressorTest() {
    suppressor.setBaffleThickness(0.125);
    suppressor.setDiameter(4.0);
    suppressor.addChamber(2.5);
    suppressor.addChamber(2.5);
    suppressor.addChamber(1.5);
    suppressor.addChamber(1.0);
    suppressor.addChamber(1.0);
    suppressor.addChamber(1.0);
    suppressor.addChamber(0.5);
    suppressor.setWallThickness(0.125);
  }
  
  @Test
  void testAddChamber() {
    suppressor.addChamber(1.25);
    assertEquals(13.125, suppressor.getActualLength());
  }

  @Test
  void testClearChambers() {
    suppressor.clearChambers();
    assertEquals(0, suppressor.getChamberCount());
  }

  @Test
  void testGetActualLength() {
    assertEquals(11.75, suppressor.getActualLength());
  }

  @Test
  void testGetBaffleThickness() {
    assertEquals(0.125, suppressor.getBaffleThickness());
  }

  @Test
  void testGetCaliber() {
    assertEquals(Caliber.C223, suppressor.getCaliber());
  }

  @Test
  void testGetChamber() {
    assertEquals(1.0, suppressor.getChamber(3).getLength());
  }

  @Test
  void testGetChamberCount() {
    assertEquals(7, suppressor.getChamberCount());
  }

  @Test
  void testGetChamberLength() {
    assertEquals(1.5, suppressor.getChamberLength(2));
  }

  @Test
  void testGetChamberVolume() {
    assertEquals((1.0*ChamberVolume.PI*3.75), suppressor.getChamberVolume(5));
  }

  @Test
  void testGetDiameter() {
    assertEquals(4.0, suppressor.getDiameter());
  }

  @Test
  void testGetFinalPressure() {
    assertEquals(0, suppressor.getFinalPressure(18.0));
  }

  @Test
  void testGetLength() {
    assertEquals(11.75, suppressor.getLength());
  }

  @Test
  void testGetWallThickness() {
    assertEquals(0.125, suppressor.getWallThickness());
  }

  @Test
  void testToJSONString() {
    String val = "{\"suppressor\":[\"caliber\":\".223\",\"diameter\":\"4.0\",\"wallThickness\":\"0.125\",\"baffleThickness\":\"0.125\",\"chambers\":[{\"chamber\":[\"length\":\"2.5\",\"diameter\":\"3.75\"]}{\"chamber\":[\"length\":\"2.5\",\"diameter\":\"3.75\"]}{\"chamber\":[\"length\":\"1.5\",\"diameter\":\"3.75\"]}{\"chamber\":[\"length\":\"1.0\",\"diameter\":\"3.75\"]}{\"chamber\":[\"length\":\"1.0\",\"diameter\":\"3.75\"]}{\"chamber\":[\"length\":\"1.0\",\"diameter\":\"3.75\"]}{\"chamber\":[\"length\":\"0.5\",\"diameter\":\"3.75\"]}]]}";
    assertEquals(val, suppressor.toJSONString());
  }

}
