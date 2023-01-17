package com.eomcs.oop.ex05;


class Solution {
  public int[] solution(int numer1, int denom1, int numer2, int denom2) {

    int ma = Math.max(denom1,denom2);
    int mi = Math.min(denom1,denom2);
    int div = ma / mi; //곱해야 될 값


    int qnswk1;
    if (denom1 == mi) {
      qnswk1 =  numer1 * div + numer2;
    } else {
      qnswk1 = numer2 * div + numer1;
    }


    if(ma % mi == 0) {
      int[] answer = {qnswk1, ma};
      return answer;
    } else {
      int[] answer = {numer1*denom2 + numer2*denom1, denom1 * denom2};
      return answer;
    }
  }
}