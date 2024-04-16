/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.jsf;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.jsf.util.Util;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author HP
 */
@Named(value = "listeComptes")
@Dependent
public class ListeComptes {

    private List<CompteBancaire> allComptes;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllComptes();
        }
        return allComptes;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        System.out.println("----------- com.mycompany.tpbanqueravelojaonalafatra53.jsf.ListeComptes.supprimerCompte()");
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }

}
