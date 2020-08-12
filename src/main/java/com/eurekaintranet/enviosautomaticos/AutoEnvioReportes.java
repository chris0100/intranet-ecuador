package com.eurekaintranet.enviosautomaticos;

import com.eurekaintranet.controller.ReporteInventarioController;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author lccabalr
 */
@Singleton
@LocalBean
@Startup
public class AutoEnvioReportes {
    
   
    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init()  {
//        TimerConfig timerConfig = new TimerConfig();
//        timerConfig.setInfo("Envio reporte Ecuador");
//        ScheduleExpression schedule = new ScheduleExpression();
//        schedule.hour("7").minute("30").second("10"); 
//        timerService.createCalendarTimer(schedule, timerConfig);
    } 
    

    @Timeout
    public void execute(Timer timer) throws SQLException, ClassNotFoundException, IOException, ParseException {
//        ReporteInventarioController reporteInventarioActual = new ReporteInventarioController();
//        reporteInventarioActual.enviarXlsInvDisponible();
    }
}
