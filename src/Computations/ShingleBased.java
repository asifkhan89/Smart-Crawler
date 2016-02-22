/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Computations;

abstract class ShingleBased {

    protected final int k;

    public ShingleBased(int k) {
        this.k = k;
    }

    public ShingleBased() {
        this(3);
    }

    public int getK() {
        return k;
    }
}
