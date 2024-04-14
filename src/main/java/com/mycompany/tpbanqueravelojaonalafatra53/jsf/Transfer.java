/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.jsf;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author HP
 */
@Named(value = "transfer")
@RequestScoped
public class Transfer implements Serializable {
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private Long idSource;
    private Long idDestination;
    private int montant;

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }

    public void setGestionnaireCompte(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

   
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of Transfer
     */
    public Transfer() {
    }
    
    public String transfer(){
        //gestionnaireCompte.transferer(idSource, idDestinataire, montant);
        CompteBancaire source = gestionnaireCompte.getCompteById(idSource);
        CompteBancaire destination = gestionnaireCompte.getCompteById(idDestination);
        gestionnaireCompte.transferer(source, destination, montant);
        return "listeComptes";
    }
    
}
