/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.util;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lucas
 */
@ManagedBean
@ApplicationScoped
public class IntervalosBean {
    public List<Integer> getIntevalo(int n) {
        List<Integer> lista = new LinkedList<>();
        for(int i=1;i<=n;i++) {
            lista.add(i);
        }
        return lista;
    }
}
