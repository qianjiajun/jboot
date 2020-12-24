package org.jot.test;

/**
 * @Author qjj
 * @Date 2020-12-14 12:01
 * @Version 1.0
 * @Class ForTests.java
 * @Description 标签
 */
public class ForTests {
    public static void main(String[] args) {
        retry1:
        for (int j = 5; j >= 1; j--) {
            retry2:
            for (int i = 0; i <= 9; i++) {
                System.out.println(String.format("倒数第%d次第%d个数", j, i + 1));
                if (i == 4 && j >= 1) {
                    continue retry1;
                }
            }
        }
    }

}
