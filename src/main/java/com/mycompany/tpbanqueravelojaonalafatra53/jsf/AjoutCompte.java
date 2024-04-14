/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.jsf;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.jsf.util.Util;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 *
 * @author HP
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }

    public void setGestionnaireCompte(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
     private String nom;
    private int solde;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
    public String ajout() {
        if (solde < 0) {
            Util.messageErreur("Le solde ne peut pas être négatif", "Erreur d'ajout de compte");
            return "erreur"; // Retourner "erreur" pour indiquer une erreur
        }

        CompteBancaire nouveauCompte = new CompteBancaire(nom, solde);

        gestionnaireCompte.creerCompte(nouveauCompte);

        Util.messageInfo("Le compte a été ajouté avec succès", "Ajout de compte");

        nom = "";
        solde = 0;

        return "listeComptes";
    }
}