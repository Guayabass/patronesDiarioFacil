/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import java.util.List;

/**
 *
 * @author Alejandro
 */
public interface InterfaceDAO {
    public List<Object> selectAll();
    public void insert(Object obj);
    public void update(Object obj);
    public void delete(Object obj);
    
}
