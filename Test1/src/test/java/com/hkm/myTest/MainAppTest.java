package com.hkm.myTest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainAppTest {

	   @Test
	   public void testSayHello() {
	      MainApp app = new MainApp();
	      assertNotNull("Success", app.start());
	   }
	}
