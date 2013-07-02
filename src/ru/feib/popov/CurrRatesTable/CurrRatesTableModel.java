/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.CurrRatesTable;

import javax.swing.table.*;

/**
 *
 * @author popov
 */
public class CurrRatesTableModel extends AbstractTableModel {
    static final String COLUMN_NAMES[] = {"Код валюты", "Курс валюты"};
    static final int COLUMN_COUNT = 2;
    
    private CurrRateList mList;
    
    public CurrRatesTableModel(CurrRateList list) {
        mList = list;
    }
    
    @Override
    public String getColumnName(int column) 
            throws IllegalArgumentException {
        if (column > COLUMN_COUNT -1) {
            throw new IllegalArgumentException();
        } else {
            return COLUMN_NAMES[column];
        }
    }
    
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }
    
    @Override
    public int getRowCount() {
        return mList.getSize();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
            throws IllegalArgumentException {
        if (rowIndex > mList.getSize()) throw new IllegalArgumentException();
        if(columnIndex == 0) {
            return mList.getItem(rowIndex).getNumber();
        } else if (columnIndex == 1) {
            return mList.getItem(rowIndex).getRate();
        } else {
            throw new IllegalArgumentException();
        }        
    }
}
