/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domen.Mesto;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.mesto.SBMestoLocal;

/**
 *
 * @author user
 */
@FacesConverter(value = "convMesto")
public class ConverterMesto implements Converter{
    @EJB
    SBMestoLocal sBMesto;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Mesto m = null;
        if (value!=null && !value.isEmpty()){
            
            int postanskiBroj = Integer.parseInt(value);
             m = sBMesto.getMesto(postanskiBroj);
        }
        System.out.println("Mesto "+m.getNaziv());
        return m;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Mesto){
            
            Mesto m = (Mesto) value;
            return String.valueOf(m.getPtt());
        }
        return "";
    }
}
