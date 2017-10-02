package ru;
import ru.numberPI;
public class blowfish {
		 class blf_ctx {
		    int S[][] = new int[4][256];
		    int P[] = new int[18];
		  }
		 private blf_ctx ctx;
		 public blowfish(byte[] key) {
		    this.ctx = new blf_ctx();
		   System.arraycopy(numberPI.ps, 0, this.ctx.P, 0, 18);
		   System.arraycopy(numberPI.ks0, 0, this.ctx.S[0], 0, 256);
		   System.arraycopy(numberPI.ks1, 0, this.ctx.S[1], 0, 256);
		   System.arraycopy(numberPI.ks2, 0, this.ctx.S[2], 0, 256);
		   System.arraycopy(numberPI.ks3, 0, this.ctx.S[3], 0, 256);
		   int data;
		    int j = 0, i;
		    for (i = 0; i < 18; ++i) {
		      data = 0x00000000;
		      for (int k = 0; k < 4; ++k) {
		        data = (data << 8) | (key[j] & 0xFF);
		        j++;
		        if (j >= key.length) j = 0;
		      }
		      this.ctx.P[i] ^= data;
		    }
		    byte[] b = new byte[8];
		    for (i = 0; i < 18; i += 2) {
		      encipher(b);
		      this.ctx.P[i] = b2d(b, 0);
		      this.ctx.P[i + 1] = b2d(b, 4);
		    }
		    for (i = 0; i < 4; ++i) {
		      for (j = 0; j < 256; j += 2) {
		        encipher(b);
		        ctx.S[i][j] = b2d(b, 0);
		        ctx.S[i][j + 1] = b2d(b, 4);
		      }
		    }
		  }
		 private int F(int x) {
		    int a, b, c, d;
		    d = x & 0xFF;
		    x >>= 8;
		    c = x & 0xFF;
		    x >>= 8;
		    b = x & 0xFF;
		    x >>= 8;
		    a = x & 0xFF;
		    int y = this.ctx.S[0][a] + this.ctx.S[1][b];
		    y ^= this.ctx.S[2][c];
		    y += this.ctx.S[3][d];
		    return y;
		  }
		 private int b2d(byte[] b, int p) {
		    int r = 0;
		    r |= b[p + 3] & 0xFF;
		    r <<= 8;
		    r |= b[p + 2] & 0xFF;
		    r <<= 8;
		    r |= b[p + 1] & 0xFF;
		    r <<= 8;
		    r |= b[p] & 0xFF;
		    return r;
		  }
		 private void d2b(int a, byte[] b, int p) {
		    b[p] = (byte) (a & 0xFF);
		    a >>= 8;
		    b[p + 1] = (byte) (a & 0xFF);
		    a >>= 8;
		    b[p + 2] = (byte) (a & 0xFF);
		    a >>= 8;
		    b[p + 3] = (byte) (a & 0xFF);
		  }
		 public void encipher(byte[] data) {
		    int blocks = data.length >> 3;
		    for (int k = 0, p; k < blocks; k++) {
		      p = k << 3;
		      int Xl = b2d(data, p);
		      int Xr = b2d(data, p + 4);
		      int tmp;
		      for (int i = 0; i < 16; i++) {
		        Xl = Xl ^ this.ctx.P[i];
		        Xr = F(Xl) ^ Xr;
		        tmp = Xl;
		        Xl = Xr;
		        Xr = tmp;
		      }
		      tmp = Xl;
		      Xl = Xr;
		      Xr = tmp;
		      Xr ^= this.ctx.P[16];
		      Xl ^= this.ctx.P[17];
		      d2b(Xl, data, p);
		      d2b(Xr, data, p + 4);
		    }
		  }
		 public void decipher(byte[] data) {
		    int blocks = data.length >> 3;
		    for (int k = 0, p; k < blocks; k++) {
		      p = k << 3;
		      int Xl = b2d(data, p);
		      int Xr = b2d(data, p + 4);
		      int tmp;
		      for (int i = 17; i > 1; i--) {
		        Xl = Xl ^ this.ctx.P[i];
		        Xr = F(Xl) ^ Xr;
		        tmp = Xl;
		        Xl = Xr;
		        Xr = tmp;
		      }
		      tmp = Xl;
		      Xl = Xr;
		      Xr = tmp;
		      Xr ^= this.ctx.P[1];
		      Xl ^= this.ctx.P[0];
		      d2b(Xl, data, p);
		      d2b(Xr, data, p + 4);
		    }
		  }
		 public byte[] padding(byte[] a, int p) {
		    int l = (a.length | 7) + 1;
		    byte[] b = new byte[l];
		    for (int i = 0; i < b.length; i++) b[i] = (byte) p;
		    System.arraycopy(a, 0, b, 0, a.length);
		    return b;
		  }
}