/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacionrp;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import static java.lang.Thread.sleep;
import ve.gob.mercal.app.services.Servicio;
import ve.gob.mercal.app.services.cargasMAX;
import static ve.gob.mercal.app.services.detenerProceso.detenerProceso;
import ve.gob.mercal.app.services.hiloServicioCargaInicial;
import ve.gob.mercal.app.services.hiloServicioMediacion;
import ve.gob.mercal.app.services.procesosEjecutandose;

/**
 *
 * @author phd2014
 */
public class Automatizacionrp {

    /**
     * @param args the command line arguments
     */
    static Logger log = Logger.getLogger(Automatizacionrp.class.getName());
    
    public static void main(String[] args) {
        // TODO code application logic here
        int max = 0;
        int time = 5;      
        String resultado = "NULL";
        PropertyConfigurator.configure("/home/bigdata/mercal/automatizacionrp/conf/log4j.properties");
        log.info("Iniciando el proceso de Automatización");
        if(args[0].equals("start")){           
            while(true){
                cargasMAX.setcargasMAX(); //REVISO EN LA BD LA CANTIDAD MAXIMA DE PROCESOS
                max= cargasMAX.getcargasMAX();//OBTENGO LA CANTIDAD MAXIMA DE PROCESOS
                if (procesosEjecutandose.procesosEjecutandose() <= max){ //VERIFICO NUMERO DE PROCESOS EN EJECUCION
                    log.info("Existe disponibilidad para una nueva ejecución");
                    Parametros param;
                    param = obtenerParametros.obtenerParametros();//OBTENER PARAMETROS Y EJECUTAR EL JOB
                    log.info("el tipo de planificacion es: "+param.getTipo());
                    if(param.getTipo() == null){
                        param.setTipo("");
                    }
                    
                    switch (param.getTipo()) {
                        case "INICIAR_CARGA":
                            hiloServicioCargaInicial cargaInicial = new hiloServicioCargaInicial(param);
                            cargaInicial.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());              
                        break;
                        
                        case "INICIAR_CARGA_ETL_INVENTARIOS":
                            hiloServicioMediacion mediacionInventario = new hiloServicioMediacion(param);
                            mediacionInventario.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());
                        break;
                        case "INICIAR_CARGA_ETL_PROVEEDORES":
                            hiloServicioMediacion mediacionProveedores = new hiloServicioMediacion(param);
                            mediacionProveedores.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());
                        break;
                        case "INICIAR_CARGA_ETL_VENTAS":
                            hiloServicioMediacion mediacionVentas = new hiloServicioMediacion(param);
                            mediacionVentas.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());
                        break;
                        case "INICIAR_CARGA_ETL_USUARIOS":
                            hiloServicioMediacion mediacionUsuarios = new hiloServicioMediacion(param);
                            mediacionUsuarios.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());
                        break;
                        case "INICIAR_CARGA_ETL_PRODUCTOS":
                            hiloServicioMediacion mediacionProductos = new hiloServicioMediacion(param);
                            mediacionProductos.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());
                        break;
                        case "INICIAR_CARGA_ETL_TIENDAS":
                            hiloServicioMediacion mediacionTiendas = new hiloServicioMediacion(param);
                            mediacionTiendas.start();
                            try {
                                sleep(60000);
                            } catch (Exception e) {
                                log.error("Excepción durmiendo ejecución");
                                log.error(e);
                            }                           
                            log.info("Se actualizará el status de la planificación: "+param.getIdplanEjec());
                            try {
                                resultado = Servicio.queryapp("UPDATE public.pasos_plan_ejecucion \n" +
                                                    "SET status_plan='a ejecucion'\n" +
                                                    "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                    "AND id_plan_ejecucion in (SELECT id_plan_ejecucion \n" +
                                                                                "FROM public.pasos_ejecucion\n" +
                                                                                "WHERE id_plan_ejecucion='"+param.getIdplanEjec()+"'\n" +
                                                                                "AND status_ejecucion='en ejecucion'\n" +
                                                                                "AND activo=TRUE);");
                            } catch (Exception e) {
                            }                           
                            log.info("Se actualizó el status de la planificación: "+param.getIdplanEjec());
                        break;
                        
                    }
                    param.setTipo("");
                    try {
                        sleep(30000); //DUERMO LA EJECUCION DURANTE 'TIME' MINUTOS
                    } catch (Exception e) {
                        log.error("Excepción durmiendo ejecución");
                        log.error(e);
                    }
                }else{
                    if(!args[1].equals(null)){
                        log.info("El tiempo maximo de espera asignado es: " + args[1]+" minutos");
                        time = Integer.parseInt(args[1]);
                    }                  
                    time = time * 60000;
                    try {
                        sleep(time); //DUERMO LA EJECUCION DURANTE 'TIME' MINUTOS
                    } catch (Exception e) {
                        log.error("Excepción durmiendo ejecución");
                        log.error(e);
                    }      
                }      
            } 
        }else{
            if(args[0].equals("stop")){
                log.info("Detener Proceso ha sido Iniciado");
                detenerProceso();             
            }
        }
    }  
}
