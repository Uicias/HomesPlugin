package com.uicias.homeplugin.utils;

import com.uicias.homeplugin.HomePlugin;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
This file DbUtil is part of a project HomesPlugin.
It was created on 06/06/2020 at 19:08 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class DbUtil {

    private static DbUtil instance;
    private static Connection connection;

    private DbUtil(){
        connect();
    }

    public static DbUtil getInstance(){
        if(instance == null){
            new DbUtil();
        }
        return instance;
    }

    private void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println("Classe non trouvee");
            e.printStackTrace();
            Bukkit.shutdown();
        }

        File db = new File(HomePlugin.homePlugin.getDataFolder() + "homes.db");

        if(!db.exists()){
            createDb(db);
        }

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + db.getPath());
            instance = this;
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    private static void createDb(File db){

        try{
            Class.forName("org.sqlite.JDBC");
            db.createNewFile();
            connection = DriverManager.getConnection("jdbc:sqlite:" + db.getPath());

            HomePlugin.homePlugin.getLogger().info("Creation de la table homes.");

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS Homes (" +
                    "uuid TEXT NOT NULL," +
                    "name TEXT NOT NULL," +
                    "loc TEXT NOT NULL);").executeUpdate();


        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

}
