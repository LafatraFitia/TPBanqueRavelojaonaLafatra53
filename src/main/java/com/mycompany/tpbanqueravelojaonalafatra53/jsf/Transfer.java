/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.jsf;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.jsf.util.Util;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;

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

    public String transfer() {
        CompteBancaire source = gestionnaireCompte.getCompteById(idSource);
        CompteBancaire destination = gestionnaireCompte.getCompteById(idDestination);
        ArrayList<String> erreurs = new ArrayList<>();
        if (source == null) {
            //Util.messageErreur("Le compte source n'existe pas", "Le compte source n'existe pas");
            //return null;
            erreurs.add("Le compte source n'existe pas");
        }
        if (destination == null) {
            //Util.messageErreur("Le compte destination n'existe pas", "Le compte destination n'existe pas");
            //return null;
            erreurs.add("Le compte destination n'existe pas");

        }

        double montantSource = gestionnaireCompte.getMontantById(idSource);
        if (montantSource < montant) {
            //Util.messageErreur("Le compte source n'a pas assez d'argent", "Le compte source n'a pas assez d'argent");
            //return null;
            erreurs.add("Le compte source n'a pas assez d'argent");

        }

        if (!erreurs.isEmpty()) {
            for (String erreur : erreurs) {
                Util.messageErreur(erreur, erreur);
            }
            return null;
        }
        gestionnaireCompte.transferer(source, destination, montant);
        
        Util.messageErreur("transfert reussi", "transfert reussi");

        return "listeComptes";
    }

}
