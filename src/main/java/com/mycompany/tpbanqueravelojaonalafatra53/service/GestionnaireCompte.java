/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.tpbanqueravelojaonalafatra53.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author HP
 */
@Named(value = "gestionnaireCompte")
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",             
    password="MySQL123",
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)
@RequestScoped
public class GestionnaireCompte {

    /**
     * Creates a new instance of GestionnaireCompte
     */
    public GestionnaireCompte() {
    }
    
}
