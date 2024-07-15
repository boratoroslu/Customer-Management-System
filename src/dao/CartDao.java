package dao;

import core.Database;
import entity.Cart;
import entity.Customer;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao {
    private Connection connection;

    public CartDao(){this.connection = Database.getInstance();}
    public ArrayList<Cart> findAll(){
        ArrayList<Cart> carts = new ArrayList<>();
        try{
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM cart");
            while (rs.next()){
                carts.add(this.match(rs));
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return carts;
    }
}
