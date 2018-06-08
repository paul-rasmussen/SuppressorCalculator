package com.dbtw.firearms.supressor.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dbtw.firearms.supressor.calculation.ChamberVolume;

class ChamberVolumeTest {
  
  @Test
  void testGetVolume() {
    ChamberVolume vol = new ChamberVolume(1.0, 1.0);
    assertEquals(3.1415927, vol.getVolume());
  }
  
  @Test
  void testSetDiameter() {
    ChamberVolume vol = new ChamberVolume(1.5, 3.5);
    assertEquals(1.5, vol.getLength());
  }

  @Test
  void testSetLength() {
    ChamberVolume vol = new ChamberVolume(1.5, 3.5);
    assertEquals(3.5, vol.getDiameter());
  }
  
}
