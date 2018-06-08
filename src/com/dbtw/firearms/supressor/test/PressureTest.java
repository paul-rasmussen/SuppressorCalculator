package com.dbtw.firearms.supressor.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dbtw.firearms.supressor.calculation.ParameterType;
import com.dbtw.firearms.supressor.calculation.Pressure;

class PressureTest {

  @Test
  void test() {
    try {
      assertEquals(10.0, Pressure.pressureChange(10.0, 1.0, 1.0, ParameterType.VOLUME));
    }
    catch (Exception e) {
      fail("Execption thrown: " + e.getMessage());
    }
  }

}
