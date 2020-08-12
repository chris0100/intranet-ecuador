/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eurekaintranet.sessions;

import com.eurekaintranet.entities.T003ItemsMba;
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
public class T003ItemsMbaFacade extends AbstractFacade<T003ItemsMba> {

    @PersistenceContext(unitName = "EurekaIntranetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T003ItemsMbaFacade() {
        super(T003ItemsMba.class);
    }
    public List<T003ItemsMba> itemsCrear(short estado){
          Query q= getEntityManager().createNamedQuery("T003ItemsMba.findByEstado");
          q.setParameter("estado",estado);
          return q.getResultList();
    }
    
    public List<T003ItemsMba> itemsCrear(short estado1 , short estado2){
          Query q= getEntityManager().createNamedQuery("T003ItemsMba.findByEstadoPendienteCrear");
          q.setParameter("estado1",estado1);
          q.setParameter("estado2",estado2);
          q.setParameter("idTipoCreacion",1);
          return q.getResultList();
    }
    
    public List<T003ItemsMba> itemsCrearPortalWeb(short estado1 , short estado2){
          Query q= getEntityManager().createNamedQuery("T003ItemsMba.findByEstadoPendienteCrear");
          q.setParameter("estado1",estado1);
          q.setParameter("estado2",estado2);
          q.setParameter("idTipoCreacion",2);
          return q.getResultList();
    }
}
