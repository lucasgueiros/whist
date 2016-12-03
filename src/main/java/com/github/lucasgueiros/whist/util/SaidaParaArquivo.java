/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author lucas
 */
public class SaidaParaArquivo {
    
    static {
        try {
            file = new PrintStream(new File("/home/lucas/logs/whist.log"));
        } catch (FileNotFoundException ex) {
            System.out.println("erro asdajs dkk dglkd vljangaljndvasdncjas ddi addv as viasjd asd ks dvsd v");
        }
    }
    
    public static PrintStream file;
    
}
