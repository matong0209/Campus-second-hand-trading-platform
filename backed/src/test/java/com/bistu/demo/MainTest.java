package com.bistu.demo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    // 判定覆盖
    @ParameterizedTest
    @CsvSource({
            "6, 6, 21, 35",   // if((x>5) && (z>20)) 为 true 且 if((y==6) || (z<2)) 为 true
            "4, 6, 21, 31",   // if((x>5) && (z>20)) 为 false 且 if((y==6) || (z<2)) 为 true
            "6, 7, 21, 34",   // if((x>5) && (z>20)) 为 true 且 if((y==6)  || (z<2)) 为 false
            "4, 7, 21, 30",   // if((x>5) && (z>20)) 为 false 且 if((y==6) || (z<2)) 为 false
            "6, 7, 1, 14"     // if((x>5) && (z>20)) 为 true 且 if((y==6)  || (z<2)) 为 true
    })
    void testFuncCoverageDecision(int x, int y, int z, int expected) {
        Main main = new Main();
        assertEquals(expected, main.funcCoverage(x, y, z));
    }


    // 条件覆盖
    @ParameterizedTest
    @CsvSource({
            "6, 6, 21, 35",  // x > 5 为 true，z > 20 为 true
            "4, 6, 21, 31",  // x > 5 为 false，z > 20 为 true
            "6, 7, 1, 14",   // x > 5 为 true，z > 20 为 false
            "4, 7, 1, 12",    // x > 5 为 false，z > 20 为 false
            "6, 7, 21, 34",  // y == 6 为 false，z < 2 为 false
            "6, 6, 1, 13"    // y == 6 为 true，z < 2 为 true
    })
    void testFuncCoverageCondition(int x, int y, int z, int expected) {
        Main main = new Main();
        assertEquals(expected, main.funcCoverage(x, y, z));
    }


    // 判定-条件覆盖
    @ParameterizedTest
    @CsvSource({
            "6, 6, 21, 35",  // 第一个 if 语句的两个条件均为 true
            "4, 6, 21, 31",  // 第一个 if 语句的第一个条件为 false，第二个条件为 true
            "6, 6, 1, 13",   // 第一个 if 语句的第一个条件为 true，第二个条件为 false
            "4, 7, 21, 30",  // 第一个 if 语句的两个条件均为 false
            "6, 6, 1, 13",   // 第二个 if 语句的两个条件均为 true
            "6, 7, 1, 14"    // 第二个 if 语句的第一个条件为 false，第二个条件为 true
    })
    void testFuncCoverageDecisionCondition(int x, int y, int z, int expected) {
        Main main = new Main();
        assertEquals(expected, main.funcCoverage(x, y, z));
    }


    // 条件组合覆盖
    @ParameterizedTest
    @CsvSource({
            "6, 6, 21, 35",  // x > 5 为 true，z > 20 为 true，y == 6 为 true，z < 2 为 false
            "4, 6, 21, 31",  // x > 5 为 false，z > 20 为 true，y == 6 为 true，z < 2 为 false
            "6, 7, 21, 34",  // x > 5 为 true，z > 20 为 true，y == 6 为 false，z < 2 为 false
            "4, 7, 21, 30",  // x > 5 为 false，z > 20 为 true，y == 6 为 false，z < 2 为 false
            "6, 6, 1, 13",   // x > 5 为 true，z > 20 为 false，y == 6 为 true，z < 2 为 true
            "4, 6, 1, 11",    // x > 5 为 false，z > 20 为 false，y == 6 为 true，z < 2 为 true
            "6, 7, 1, 14",   // x > 5 为 true，z > 20 为 false，y == 6 为 false，z < 2 为 true
            "4, 7, 1, 12"     // x > 5 为 false，z > 20 为 false，y == 6 为 false，z < 2 为 true
    })
    void testFuncCoverageConditionCombination(int x, int y, int z, int expected) {
        Main main = new Main();
        assertEquals(expected, main.funcCoverage(x, y, z));
    }

}

