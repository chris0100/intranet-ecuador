/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eurekaintranet.sessions;

import com.eurekaintranet.entities.T006ReporteSaldoVendedor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author luis carlos cabal
 */
@Stateless
public class T006ReporteSaldoVendedorFacade extends AbstractFacade<T006ReporteSaldoVendedor> {

    @PersistenceContext(unitName = "EurekaIntranetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T006ReporteSaldoVendedorFacade() {
        super(T006ReporteSaldoVendedor.class);
    }

}
