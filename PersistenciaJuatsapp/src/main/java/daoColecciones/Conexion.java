/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoColecciones;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author LuisaM
 */
public class Conexion {
    private static MongoClient mongoClient=null;
    private static final String URI="mongodb://localhost:27017";
    private static final String DB_NAME="Juatsapp";
    
    private Conexion(){
    }
    
    public static MongoDatabase getDatabase(){
        if(mongoClient==null){
            CodecRegistry pojoCodecRegistry =CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            
            MongoClientSettings clientSettings= MongoClientSettings.builder()
                    .applyConnectionString(new com.mongodb.ConnectionString(URI))
                    .codecRegistry(pojoCodecRegistry).build();
            
            mongoClient= MongoClients.create(clientSettings);
            return mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
        }
        return mongoClient.getDatabase(DB_NAME);
    }
    
    public static void cerrarConexion(){
        if(mongoClient!=null)mongoClient.close();
    }
}
