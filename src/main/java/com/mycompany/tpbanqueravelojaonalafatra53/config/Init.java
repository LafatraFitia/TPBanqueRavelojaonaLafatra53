/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.config;

import com.mycompany.tpbanqueravelojaonalafatra53.entity.CompteBancaire;
import com.mycompany.tpbanqueravelojaonalafatra53.service.GestionnaireCompte;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;

/**
 *
 * @author HP
 */
@Named(value = "init")
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of Init
     */
    public Init() {
    }

    @PostConstruct
    @Transactional
    public void init(@Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        // Vérifier si la table des comptes est vide
        if (gestionnaireCompte.nbComptes() == 0) {
            // Créer les comptes
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaireCompte.creerCompte(new CompteBancaire("George Harrison", 100000));
        }
    }

}
