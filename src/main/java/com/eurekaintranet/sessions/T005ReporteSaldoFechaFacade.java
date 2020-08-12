/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eurekaintranet.sessions;

import com.eurekaintranet.entities.T005ReporteSaldoFecha;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author luis carlos cabal
 */
@Stateless
public class T005ReporteSaldoFechaFacade extends AbstractFacade<T005ReporteSaldoFecha> {

    @PersistenceContext(unitName = "EurekaIntranetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T005ReporteSaldoFechaFacade() {
        super(T005ReporteSaldoFecha.class);
    }
   
}
