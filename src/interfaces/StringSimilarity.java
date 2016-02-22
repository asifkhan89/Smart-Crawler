/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

public interface StringSimilarity extends Serializable {

    public double similarity(String s1, String s2);

}
