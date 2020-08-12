/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.sessions;

import com.eurekaintranet.entities.T008ReporteSaldoItemCodBarraMba;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lccabalr
 */
@Stateless
public class T008ReporteSaldoItemCodBarraMbaFacade extends AbstractFacade<T008ReporteSaldoItemCodBarraMba> {

    @PersistenceContext(unitName = "EurekaIntranetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T008ReporteSaldoItemCodBarraMbaFacade() {
        super(T008ReporteSaldoItemCodBarraMba.class);
    }
    
}
