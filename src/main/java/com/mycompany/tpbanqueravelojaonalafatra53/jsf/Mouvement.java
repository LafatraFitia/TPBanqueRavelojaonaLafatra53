/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.jsf;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.jsf.util.Util;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author HP
 */
@Named(value = "mouvement")
@ViewScoped
public class Mouvement implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String typeMouvement;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.getCompteById(id);
    }

    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement");
        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement == null) {
            return;
        }
        if (valeurTypeMouvement.equals("retrait")) {
            int retrait = (int) valeur;
            
            if (compte.getSolde() < retrait) {
                FacesMessage message
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Le retrait doit être inférieur au solde du compte",
                                "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    public String enregistrerMouvement() {
        if (typeMouvement.equals("ajout")) {
            gestionnaireCompte.deposer(compte, montant);
        } else {
            gestionnaireCompte.retirer(compte, montant);
        }
        Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }

}
