/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;
import java.util.Random;

public class Matrix {
    private static Random randomEngine  = new Random();
    
    private int[][] matrix;
    private int rows;
    private int columns;
    
        

    /**
     * Public constructor
     * @param rows
     * @param columns 
     */
    public Matrix(int rows, int columns) {
        matrix = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }
    
    //######################################################################
    //Static factories
    //######################################################################
    
    /**
     * Creates matrix of the standard form. Equivalent of public constructor
     * @param rows
     * @param columns
     * @return 
     */
    public static Matrix standard(int rows, int columns) {
        Matrix instance = new Matrix(rows, columns);
        return instance;
    }
    
    /**
     * Creates a vector-row
     * @param size
     * @return 
     */    
    public static Matrix vectorRow(int size) {
        Matrix instance = new Matrix(1, size);
        return instance;
    }
    
    /**
     * Creates a vector-column
     * @param size
     * @return 
     */
    public static Matrix vectorColumn(int size) {
        Matrix instance = new Matrix(size, 1);
        return instance;
    }
    
    /**
     * Creates a square matrix
     * @param size
     * @return 
     */
    public static Matrix square(int size) {
        Matrix instance = new Matrix(size, size);
        return instance;
    }
    
    /**
     * Creates an identity matrix
     * @param size
     * @return 
     */
    public static Matrix identity(int size) {
        Matrix instance = new Matrix(size, size);
        
        for (int i = 1; i <= size; i++)
            instance.setValue(i, i, 1);
        return instance;
    }
    
    //######################################################################
    //Getters&setters
    //######################################################################
    
    /**
     * 
     * @return number of rows
     */
    public int getRows() {
        return this.rows;
    }
    
    /**
     * 
     * @return number of columns
     */
    public int getColumns() {
        return this.columns;
    }
    
    /**
     * Set into certain cell certain value
     * @param row cell row
     * @param column cell column
     * @param value input value
     * @throws IllegalArgumentException if row or column is out of range
     */    
    public void setValue(int row, int column, int value) 
            throws IllegalArgumentException {
        if (this.rows < row - 1 || row <= 0) 
            throw new IllegalArgumentException("Illegal row");
        if (this.columns < column - 1 || column <= 0) 
            throw new IllegalArgumentException("Illegal column");
        
        matrix[row - 1][column - 1] = value;
    }
    
    public int getValue(int row, int column) 
            throws IllegalArgumentException {
        if (this.rows < row - 1 || row <= 0) 
            throw new IllegalArgumentException("Illegal row");
        if (this.columns < column - 1 || column <= 0) 
            throw new IllegalArgumentException("Illegal column");
        
        return matrix[row - 1][column - 1];
    }
    
    //######################################################################
    //Operations with matrixes
    //######################################################################
    
    /**
     * 
     * @param m1
     * @param m2
     * @return new matrix, representing sum of two matrixes
     * @throws IllegalArgumentException if matrixes have different size
     */
    public Matrix add(Matrix m1, Matrix m2)
            throws IllegalArgumentException {
        if(m1.getRows() != m2.getRows() || 
                m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException(
                    "Matrixes should have same size");
        }
        
        Matrix instance = new Matrix (m1.getRows(), m1.getColumns());
        for (int i = 1; i <= m1.getRows(); i++) {
            for (int j = 1; j <= m1.getColumns(); j++) {
                instance.setValue(i, j, 
                        m1.getValue(i, j) + m2.getValue(i, j));
            }            
        }
        return instance;
    }    
    
    /**
     * 
     * @param m1
     * @param m2
     * @return new matrix representing result of subtraction m2 from m1
     * @throws IllegalArgumentException  if matrixes have different size
     */
    public static Matrix subtract(Matrix m1, Matrix m2)
            throws IllegalArgumentException {
        if(m1.getRows() != m2.getRows() || 
                m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException(
                    "Matrixes should have same size");
        }
        
        Matrix instance = new Matrix (m1.getRows(), m1.getColumns());
        for (int i = 1; i <= m1.getRows(); i++) {
            for (int j = 1; j <= m1.getColumns(); j++) {
                instance.setValue(i, j, 
                        m1.getValue(i, j) - m2.getValue(i, j));
            }            
        }
        return instance;
    }
    
    /**
     * 
     * @param m1
     * @param m2
     * @return representing result of multiplication of m1 on m2
     * @throws IllegalArgumentException if m1.column != m2.row
     */
    public static Matrix multiply(Matrix m1, Matrix m2) 
            throws IllegalArgumentException {
        if(m1.getColumns() != m2.getRows()) {
            throw new IllegalArgumentException(
                    "Number of columns in first matrix should equal "
                    + "number of rows in second matrix");
        }
        
        Matrix instance = new Matrix(m1.getRows(), m2.getColumns());
        for (int row1 = 1; row1 <= m1.getRows(); row1++) {
            for (int column2 = 1; column2 <= m2.getColumns(); column2++) {
                int sum = 0;
                for (int i = 1; i <= m1.getColumns(); i++) {
                    sum += m1.getValue(row1, i) * 
                            m2.getValue(i, column2);
                }
                instance.setValue(row1, column2, sum);
            }
        }
        return instance;
    }
    
    //######################################################################
    //Other methods
    //######################################################################
    
    /**
     * fills matrix with random values up to certain maximum value
     * @param maxValue 
     */
    public void fillRandom(int maxValue) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrix[i][j] = randomEngine.nextInt(maxValue);
            }
        }
    }
    
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                str += this.matrix[i][j] + 
                        ((j == this.columns - 1) ?  "" : " "); 
            }
            str += (i == this.rows - 1) ?  "" : "\n";
        }
        return str;
    }
}