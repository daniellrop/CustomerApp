package com.version1.CustomerAppTest;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CountryTest.class, CustomerControllerTest.class, CustomerNameTest.class })
public class AllTests {

}

