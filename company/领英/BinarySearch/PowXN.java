class PowXN {
  
  //想想  2^9(product2) = 2*4^4(product4) = 2*8^2(product8) = 2*16^1(product16)
  // n 小于0的情况
  public double myPow(double x, int n) {    
    Long N = (long) n;
    
    if (N < 0) {
      x = 1 / x;
      N = -N;
    }

    double result = 1.0;
    double product = x;

    while (N > 0) {
      if (N % 2 == 1) {    
        result *= product;
      }
      product *= product;
      N /= 2;
    }

    return result;
  }
}
