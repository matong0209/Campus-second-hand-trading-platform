package com.bistu.demo;

public class Main {
    public int funcCoverage (int x, int y, int z)
    {
        if((x>5) && (z>20))
            x++;
        else
            x--;
        if((y==6) || (z<2))
            z++;
        else
            z--;
        return x+y+z;
    }
}