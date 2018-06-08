package com.dbtw.firearms.supressor.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dbtw.firearms.supressor.Chamber;

class ChamberTest {

  @Test
  void testGetDiameter() {
    Chamber chamber = new Chamber(1.5, 3.5);
    assertEquals(3.5, chamber.getDiameter());
  }

  @Test
  void testGetLength() {
    Chamber chamber = new Chamber(1.5, 3.5);
    assertEquals(1.5, chamber.getLength());
  }

  @Test
  void testGetVolume() {
    Chamber chamber = new Chamber(1.0, 1.0);
    assertEquals(3.1415927, chamber.getVolume());
  }

  @Test
  void testToJSONString() {
    Chamber chamber = new Chamber(1.5, 3.5);
    assertEquals("{\"chamber\":[\"length\":\"1.5\",\"diameter\":\"3.5\"]}",chamber.toJSONString());
  }

}
