/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.gob.mercal.app.services;

import automatizacionrp.Parametros;
import static ve.gob.mercal.app.services.cargasMAX.log;

/**
 *
 * @author pbonillo
 */
public class hiloServicioCargaInicial extends Thread {
    Parametros param;
    public hiloServicioCargaInicial(Parametros param2){
        log.info("Se inicio un proceso de Carga Inicial");
        param = param2;
    }

    @Override
    public void run(){
        int result = -999;
        try {        
            result = Servicio.cargainicial(param.getDirPDI(),param.getNombreJob(),
                    param.getDirEjecucion(),param.getRepositorio(),param.getUsuarioRepositorio(),
                    param.getPassusuarioRepo(),param.getHostbdOracle(),param.getUsuariobdOracle(),
                    param.getPassusuariobdOracle(),param.getBdOracle(),param.getHostbdCassandra(),
                    param.getColumnFamily(),param.getKeyspace(),param.getHostbdApp(),
                    param.getUsuariobdApp(),param.getPassusuariobdApp(),param.getBdApp(),
                    param.getIdplanEjec(),param.getJobModo(),param.getDirLogs(),param.getNivelLogs());
        } catch (Exception e) {
            log.error("Excepción en Carga Inicial");
            log.error(e);
        }
    }

}
