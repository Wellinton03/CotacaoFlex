/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.wellinton.cotacao.config.Scope;

import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Map;

public class ViewScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            throw new IllegalStateException("FacesContext não encontrado.");
        }

        Map<String, Object> viewMap = facesContext.getViewRoot().getViewMap();
        return viewMap.computeIfAbsent(name, k -> objectFactory.getObject());
    }

    @Override
    public Object remove(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            throw new IllegalStateException("FacesContext não encontrado.");
        }

        return facesContext.getViewRoot().getViewMap().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext != null ? facesContext.getViewRoot().getViewId() : null;
    }
}
