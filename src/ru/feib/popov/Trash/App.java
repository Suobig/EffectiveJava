/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;


/**
 *
 * @author popov
 */
public class App {
    public static void main(String args[]) {
        final int MATRIX_SIZE = 6;
        final int MAX_VALUE = 10;
        
        Matrix a = Matrix.square(MATRIX_SIZE);
        a.fillRandom(MAX_VALUE);
        System.out.println("Matrix A(" + 
                MATRIX_SIZE + "x" + MATRIX_SIZE + "):\n" + a);
        
        Matrix e = Matrix.identity(MATRIX_SIZE);
        
        Matrix v = Matrix.vectorColumn(MATRIX_SIZE);
        v.fillRandom(MAX_VALUE);
        System.out.println("Vector V(" + MATRIX_SIZE + "x1):\n" + v);
        
        Matrix b = Matrix.multiply(Matrix.subtract(a, e), v);
        System.out.println("(A - E) x V =\n" + b);
    }
}
