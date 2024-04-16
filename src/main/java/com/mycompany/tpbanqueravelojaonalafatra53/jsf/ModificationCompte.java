/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.jsf;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.jsf.util.Util;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author HP
 */
@Named(value = "modificationCompte")
@ViewScoped
public class ModificationCompte implements Serializable {
    private Long id;
    private CompteBancaire compte;
    private String nom;
    
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    private int montant;
    
    public void loadCompte() {
        compte = gestionnaireCompte.getCompteById(id);
    }

    public void loadName() {
        if(compte == null) {
            loadCompte();
        }
        nom = compte.getNom();
    }
    /**
     * Creates a new instance of ModificationCompte
     */
    public ModificationCompte() {
    }
    
    public String modifier(){
           
        String ancienNom = compte.getNom();
        compte.setNom(nom);
        gestionnaireCompte.update(compte);
        Util.messageInfo("Changement de nom de \"" + ancienNom + "\" en \""+compte.getNom()+"\" terminé avec succès.", "Changement de nom de \"" + ancienNom + "\" en \""+compte.getNom()+"\" terminé avec succès.");
        return "listeComptes";
    }
    
}
