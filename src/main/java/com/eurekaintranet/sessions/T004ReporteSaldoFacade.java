/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eurekaintranet.sessions;

import com.eurekaintranet.entities.T004ReporteSaldo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author luis carlos cabal
 */
@Stateless
public class T004ReporteSaldoFacade extends AbstractFacade<T004ReporteSaldo> {

    @PersistenceContext(unitName = "EurekaIntranetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T004ReporteSaldoFacade() {
        super(T004ReporteSaldo.class);
    }
    public List<T004ReporteSaldo> ordenarReporte(){
        Query q= getEntityManager().createNamedQuery("T004ReporteSaldo.findAll");
        return q.getResultList();
    }
    public List<T004ReporteSaldo> ordenarReporteConsolidado(){
        Query q= getEntityManager().createNamedQuery("T004ReporteSaldo.findAllConsolidado");
        return q.getResultList();
    }
}
