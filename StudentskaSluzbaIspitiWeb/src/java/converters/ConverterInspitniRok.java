/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domen.Ispitnirok;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.ispitnirok.SBIspitniRokLocal;

/**
 *
 * @author user
 */
@FacesConverter(value = "convRok")
public class ConverterInspitniRok implements Converter{
    @EJB
    SBIspitniRokLocal sBIspitniRok;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Ispitnirok rok = null;
        if (value!=null && !value.isEmpty()){
            
            int id = Integer.parseInt(value);
             rok = sBIspitniRok.getIspitniRok(id);
        }
        System.out.println("IspitniRok "+rok.getNaziv());
        return rok;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Ispitnirok){
            
            Ispitnirok rok = (Ispitnirok) value;
            return String.valueOf(rok.getIspitniRokID());
        }
        return "";
    }
    
}
