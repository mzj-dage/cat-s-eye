package com.mzj.meetingfilm.testng.demo;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
public class HelloWorld {

    @Test
    public void test(){
        log.error("this is test!");
    }

    @Test
    public void test02(){
        log.error("this is test02");
    }

    @BeforeMethod
    public void beforemethod(){
        log.info("this is beforemethod test!");
    }

    @AfterMethod
    public void aftermethod(){
        log.info("this is aftermethod test!");
    }

    @BeforeClass
    public void beforeclass(){
        log.info("this is beforeclass test");
    }

    @AfterClass
    public void afterclass(){
        log.info("this is afterclass test");
    }

    @BeforeSuite
    public void beforesutie(){
        log.info("this is beforesutie test");
    }

    @AfterSuite
    public void aftersutie(){
        log.info("this is aftersutie test");
    }
}
