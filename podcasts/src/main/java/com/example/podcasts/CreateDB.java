package com.example.podcasts;

import java.sql.*;
import java.util.Scanner;



public class CreateDB {
    public static void main(String[] args) {
    try {
        //aqui haremos la conexión y las consultas
        // 1-decirle el driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2- conectar con DB específica. Crear objeto de Conne
        Connection conexion1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Podcast","root","");
        System.out.println("***CORRECTOOO ***");


        // 3- crear objeto Statement, que es el que se encargará de hacer consultas
        Statement stat1 = conexion1.createStatement();

        // 4 - Ejecutar instrucciones stat1 y mediante métodos executeQuery() y executeUpdate()
        // primero lo leemos todo y lo guardamos en onjeto ResultSet
        ResultSet resultado1 = stat1.executeQuery("SELECT * FROM Podcast");


        // 5- recorrer el resultado mediante bucle
        System.out.println("--------------\nLISTADO PODCASTS\n--------------");
        while(resultado1.next()){
            System.out.println("- Id: " + resultado1.getString("idPodcast") + " | " + resultado1.getString("nombre") + ": " + resultado1.getString("descripcion") + " | Duración: " + resultado1.getString("duracion"));
        }
       
        String nombrePodcast;
        String descPodcast;
        String duracionPodcast;
        int idPodcast;

        Scanner teclado = new Scanner(System.in);
        
        boolean active = true;
        while (active) {
            System.out.println("--------------\n");
            System.out.println("¿Qué quieres hacer?");
            
        
            System.out.println("1. Añadir un podcast\n2. Modificar un podcast\n3. Eliminar un podcast\n4. Ver listado\n5. SALIR");
            int menuOption=teclado.nextInt();
            teclado.nextLine();
            System.out.println("----------------\n\n");
            switch (menuOption) {
                case 1:
                    // 6 - INSERTAR daros en la tabla con un método executeUpdate()
                        System.out.println("Indica el nombre");
                        nombrePodcast = teclado.nextLine();
                        System.out.println("\nIndica la descripción:");
                        descPodcast = teclado.nextLine();
                        System.out.println("\\n" + //
                                                        "Indica la duración:");
                        duracionPodcast = teclado.nextLine();
                        String insertar = "INSERT INTO Podcast (nombre, descripcion, duracion) values ('"+nombrePodcast+"','"+descPodcast+"','"+duracionPodcast+"')";
                        stat1.executeUpdate(insertar);
                        System.out.println("datos insertados!!");
                        System.out.println("----------------\n\n");

                    
                    break;
                case 2:
                    // 7 - Actualizar datos UPADTE
                        System.out.println("Introduce el ID:");
                        idPodcast = teclado.nextInt();
                        teclado.nextLine();
                        System.out.println("¿Qué datos quieres modificar?");
                        System.out.println("1. Título\n2. Descripción\n3. Duración\n4. Cancelar");
                        int valorModif=teclado.nextInt();
                        teclado.nextLine();
                        switch (valorModif) {
                            case 1:
                                System.out.println("\nIntroduce el nuevo título:");
                                nombrePodcast = teclado.nextLine();
                                String actualizarNombre = "UPDATE Podcast set nombre='"+nombrePodcast+"' where idPodcast='"+idPodcast+"'";
                                stat1.executeUpdate(actualizarNombre);
                                System.out.println("datos actualizados");
                                System.out.println("----------------\n\n");                                 
                                break;
                            case 2:
                                System.out.println("\nIntroduce la nueva descripción:");
                                descPodcast = teclado.nextLine();
                                String actualizarDesc = "UPDATE Podcast set descripcion='"+descPodcast+"' where idPodcast='"+idPodcast+"'";
                                stat1.executeUpdate(actualizarDesc);
                                System.out.println("datos actualizados");
                                System.out.println("----------------\n\n");                                
                                break;
                            case 3:
                                System.out.println("\nIntroduce la duración de podcast:");
                                duracionPodcast = teclado.nextLine();
                                String actualizarDur = "UPDATE Podcast set duracion='"+duracionPodcast+"' where idPodcast='"+idPodcast+"'";
                                stat1.executeUpdate(actualizarDur);
                                System.out.println("datos actualizados");
                                System.out.println("----------------\n\n");
                                break;
                            case 4:
                                
                                break;
                        
                            default:
                                break;
                        }
                        
                    break;
                case 3:
                    // 8 - Borrar registros DELELTE
                    System.out.println("¿Que id quieres eliminar?");
                        idPodcast = teclado.nextInt();
                        teclado.nextLine();
                        String eliminar = "DELETE from Podcast where idPodcast='"+idPodcast+"'";
                        stat1.executeUpdate(eliminar);
                        System.out.println("Elemento eliminado");
                        System.out.println("----------------\n\n");
                    break;
                
                case 4:
                        System.out.println("--------------\nLISTADO PODCASTS\n--------------");
                        ResultSet resultado2 = stat1.executeQuery("SELECT * FROM Podcast");
                        while(resultado2.next()){
                            System.out.println("- Id: " + resultado2.getString("idPodcast") + " | " + resultado2.getString("nombre") + ": " + resultado2.getString("descripcion") + " | Duración: " + resultado2.getString("duracion"));
                        }
                        System.out.println("--------------\n");
                    break;
                case 5:
                    System.out.println("Hasta pronto!");
                    active=false;
                    break;

                default:
                System.out.println("Error... vuelve a probar");
                    break;
            }
        }

        

        



        conexion1.close();
    }catch(Exception e){
        //mensaje de error
        System.out.println("***oohhhh ***");
    }

}
}