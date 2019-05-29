package com.thizgroup.promotion.util;

import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
public class VericationCodeGenerator {
  private static final String SOURCE = "1234567890";



  public static String generate() {
    StringBuilder code = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      code.append(SOURCE.charAt(random.nextInt(SOURCE.length())));
    }
    return code.toString();
  }

  public static void main(String[] args) {
    System.out.println(generate());
    System.out.println(generate());
  }


}
