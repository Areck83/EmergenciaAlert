package com.hiloxDevelop.EmergenciaAlert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_hiloxdevelop.emergencias.RegistrarContactoRequest;
import https.t4is_hiloxdevelop.emergencias.RegistrarContactoResponse;
import https.t4is_hiloxdevelop.emergencias.ObtenerContactosResponse.Contacto;
import https.t4is_hiloxdevelop.emergencias.BuscarContactoRequest;
import https.t4is_hiloxdevelop.emergencias.BuscarContactoResponse;
import https.t4is_hiloxdevelop.emergencias.EditarContactoRequest;
import https.t4is_hiloxdevelop.emergencias.EditarContactoResponse;
import https.t4is_hiloxdevelop.emergencias.EliminarContactoRequest;
import https.t4is_hiloxdevelop.emergencias.EliminarContactoResponse;
import https.t4is_hiloxdevelop.emergencias.ObtenerContactosResponse;

@Endpoint
public class EndPoint {
    @Autowired
    private IDirectorioEmergencias iDirectorioEmergencias;

    @PayloadRoot(localPart = "RegistrarContactoRequest", namespace = "https://t4is.hiloxDevelop/emergencias")

    @ResponsePayload
    public RegistrarContactoResponse RegistrarContacto(@RequestPayload RegistrarContactoRequest peticion){
        RegistrarContactoResponse i = new RegistrarContactoResponse();
        i.setRespuesta("Se ha registrado el contacto: "+peticion.getNombre());
        DirectorioEmergencias agenda = new DirectorioEmergencias();
        agenda.setNombre(peticion.getNombre());
        agenda.setTelefono(peticion.getTelefono());
        agenda.setDireccion(peticion.getDireccion());
        agenda.setAlias(peticion.getAlias());
        agenda.setLatitude(peticion.getLatitude());
        agenda.setLongitude(peticion.getLongitude());
        iDirectorioEmergencias.save(agenda);
        return i;
    }

    @PayloadRoot(localPart = "BuscarContactoRequest", namespace = "https://t4is.hiloxDevelop/emergencias")
    @ResponsePayload
    public BuscarContactoResponse BuscarContacto(@RequestPayload BuscarContactoRequest peticion){
        BuscarContactoResponse s = new BuscarContactoResponse();
        DirectorioEmergencias agenda = iDirectorioEmergencias.findById(peticion.getId()).get();
        s.setId(agenda.getId());
        s.setNombre(agenda.getNombre());
        s.setDireccion(agenda.getDireccion());
        s.setTelefono(agenda.getTelefono());
        s.setAlias(agenda.getAlias());
        s.setLatitude(agenda.getLatitude());
        s.setLongitude(agenda.getLongitude());
        return s;
    }

    @PayloadRoot(localPart = "EditarContactoRequest", namespace = "https://t4is.hiloxDevelop/emergencias")
    @ResponsePayload
    public EditarContactoResponse EditarContacto(@RequestPayload EditarContactoRequest peticion){
        EditarContactoResponse a = new EditarContactoResponse();
        DirectorioEmergencias agenda = new DirectorioEmergencias();
        agenda.setId(peticion.getId());
        agenda.setNombre(peticion.getNombre());
        agenda.setDireccion(peticion.getDireccion());
        agenda.setTelefono(peticion.getTelefono());
        agenda.setAlias(peticion.getAlias());
        agenda.setLatitude(peticion.getLatitude());
        agenda.setLongitude(peticion.getLongitude());
        iDirectorioEmergencias.save(agenda);
        a.setAviso("Se ha actualizado el contacto");
        return a;
    }

    @PayloadRoot (localPart = "EliminarContactoRequest", namespace = "https://t4is.hiloxDevelop/emergencias")
    @ResponsePayload
    public EliminarContactoResponse EliminarContacto(@RequestPayload EliminarContactoRequest peticion){
        EliminarContactoResponse c = new EliminarContactoResponse();
        iDirectorioEmergencias.deleteById(peticion.getId());
        c.setAviso("Se ha eliminado el contacto con el ID "+peticion.getId());
        return c;
    }

    @PayloadRoot(localPart= "ObtenerContactosRequest", namespace = "https://t4is.hiloxDevelop/emergencias")
    @ResponsePayload
    public ObtenerContactosResponse ObtenerContactosResponse(){
        ObtenerContactosResponse x = new ObtenerContactosResponse();
        Iterable<DirectorioEmergencias> contactosList = iDirectorioEmergencias.findAll();

        for (DirectorioEmergencias contacto : contactosList){
            ObtenerContactosResponse.Contacto n = new ObtenerContactosResponse.Contacto();
            n.setId(contacto.getId());
            n.setNombre(contacto.getNombre());
            n.setDireccion(contacto.getDireccion());
            n.setTelefono(contacto.getTelefono());
            n.setAlias(contacto.getAlias());
            n.setLatitude(contacto.getLatitude());
            n.setLongitude(contacto.getLongitude());
            x.getContacto().add(n);
        }
        return x;
    }

    /*@PayloadRoot(localPart= "ObtenerContactosRequest", namespace = "https://t4is.hiloxDevelop/emergencias")
    @ResponsePayload
    public Iterable<DirectorioEmergencias> contactos(){
        ObtenerContactosResponse x = new ObtenerContactosResponse();//Constructor
        Iterable<DirectorioEmergencias> contactosList = iDirectorioEmergencias.findAll(); //Ya tiene todo
        return contactosList;
    }*/
}
