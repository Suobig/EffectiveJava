/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.Trash;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import java.io.*;

/**
 *
 * @author popov
 */
public class OutlookSender {
    public static void main(String[] args) {  
        ActiveXComponent oOutlook = new ActiveXComponent("Outlook.Application");  
        Dispatch.call(oOutlook ,"GetNamespace","MAPI").toDispatch();
        Dispatch email = Dispatch.invoke(oOutlook.getObject(),"CreateItem", 
                Dispatch.Get, new Object[] { "0" }, new int[0]).toDispatch();  
        Dispatch.put(email, "To", "popov@feib.ru");  
        Dispatch.put(email, "Subject", "hello, i'm java");  
        Dispatch.put(email, "Body", "Hello, Suobig, "
                + "I'm Java sending you email");
        Dispatch.put(email, "ReadReceiptRequested", "false"); 
        try {  
            Dispatch.call(email, "Display");  
        } catch (com.jacob.com.ComFailException e) {  
            e.printStackTrace();  
        }  
    }
}
