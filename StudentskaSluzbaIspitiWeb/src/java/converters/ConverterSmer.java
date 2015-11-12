/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domen.Smer;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sb.smer.SBSmerLocal;

/**
 *
 * @author user
 */
@FacesConverter(value = "convSmer")
public class ConverterSmer implements Converter{
    
    @EJB
    SBSmerLocal sBSmer;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Smer s = null;
        if (value!=null && !value.isEmpty()){
            
            int smerID = Integer.parseInt(value);
             s = sBSmer.getSmer(smerID);
        }
        System.out.println("Mesto "+s.getNaziv());
        return s;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Smer){
            
            Smer s = (Smer) value;
            return String.valueOf(s.getSmerID());
        }
        return "";
    }
}
