/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import net.daw.dao.ProductoDao;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author a044531896d
 */
public class LineaBean {

    @Expose
    private int id;
    @Expose
    private int cantidad;
    @Expose(serialize = false)
    private int id_producto;
    @Expose
    private int id_factura;
    @Expose(deserialize = false)
    private ProductoBean obj_Producto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public ProductoBean getObj_Producto() {
        return obj_Producto;
    }

    public void setObj_Producto(ProductoBean obj_Producto) {
        this.obj_Producto = obj_Producto;
    }

    public LineaBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {

        this.setId(oResultSet.getInt("id"));
        this.setCantidad(oResultSet.getInt("cantidad"));
        this.setId_factura(oResultSet.getInt("id_factura"));
        this.setId_producto(oResultSet.getInt("id_producto"));
        if (expand > 0) {
            ProductoDao oproductoDao = new ProductoDao(oConnection, "producto");
            this.setObj_Producto(oproductoDao.get(oResultSet.getInt("id_producto"), expand - 1));
        } else {
            this.setId_producto(oResultSet.getInt("id_producto"));
        }
        return this;
    }
    public String getPairs(String ob) {
		String strPairs="";
		strPairs += "id=" + id + ",";
		strPairs += "cantidad=" + cantidad + ",";
		strPairs += "id_factura=" +id_factura + ",";
		strPairs += "id_producto=" + id_producto+ ",";
                strPairs += " WHERE id=" + id ;
		return strPairs;
		
	}

}
