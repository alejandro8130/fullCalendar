/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;

/**
 *
 * @author ficha1020611
 */
public class conectadb {

    private String usuario;
    private String clave;
    private String BaseDa;
    private String url;
    private String driverClassName;
    private Connection conn = null;
    private Statement estancia;
    
    public conectadb(String usuario, String clave, String BaseDa, String url, String driverClassName) {
        this.usuario = usuario;
        this.clave = clave;
        this.BaseDa = BaseDa;
        this.url = url;
        this.driverClassName = driverClassName;
    }

    public conectadb() {
        this.usuario = "alejo";
        this.clave = "";
        this.BaseDa = "fullCalendar";
        this.url = "jdbc:mysql://localhost:3306/fullCalendar";
        this.driverClassName = "com.mysql.jdbc.Driver";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getBaseDa() {
        return BaseDa;
    }

    public void setBaseDa(String BaseDa) {
        this.BaseDa = BaseDa;
    }


    
    public Connection conectar() throws SQLException {
        try {
            Class.forName(this.driverClassName).newInstance();
            this.conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
        } catch (Exception err) {
            System.out.println("Error " + err.getMessage());
        }
        return conn;
    }

    public void cierraConexion() throws SQLException {
        this.conn.close();
    }

//    public ResultSet consulta(String consulta) throws SQLException {
//        this.estancia = (Statement) conn.createStatement();
//        return this.estancia.executeQuery(consulta);
//    }
//
//    public void actualizar(String actualiza) throws SQLException {
//        this.estancia = (Statement) conn.createStatement();
//        estancia.executeUpdate(actualiza);
//    }
//
//    public ResultSet borrar(String borra) throws SQLException {
//        Statement st = (Statement) this.conn.createStatement();
//        return (ResultSet) st.executeQuery(borra);
//    }
//
//    public int insertar(String inserta) throws SQLException {
//        Statement st = (Statement) this.conn.createStatement();
//        return st.executeUpdate(inserta);
//    }
}
