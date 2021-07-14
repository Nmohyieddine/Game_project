using System;
using System.Diagnostics;
using System.Data;
using UnityEngine;
using Mono.Data.Sqlite;

public class Scripte4DB : MonoBehaviour
{

    private static string dbName =@"Data Source=C:\Users\zmiloudi\Desktop\Game_project\Projet_Gamification\Assets\Scripte\QuesionsDB.db,Version=3";
    public static string Val;
    // Start is called before the first frame update
    void Start()
    {
        CreateDB();
        //Addanswer(1,"oui");
       
        
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    public static void CreateDB(){

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command1 = connection.CreateCommand()){

                command1.CommandText="CREATE TABLE IF NOT EXISTS Questions (ID INT , question TEXT );";
                command1.ExecuteNonQuery();
            }using(var command2 = connection.CreateCommand()){

                command2.CommandText="CREATE TABLE IF NOT EXISTS Reponses (ID INT ,reponse TEXT );";
                command2.ExecuteNonQuery();
            }
            connection.Close();

        }
    }
    public static void Addanswer(int IDquestion,string answer )
    {

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="INSERT INTO  Reponses (ID ,reponse ) VALUES ('" +IDquestion + "' , '" + answer + "' ); ";
                command.ExecuteNonQuery();
            }
            connection.Close();

        }


    }

     public static string Showquestion(int IDquestion)
    {
        

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Questions WHERE ID = "+IDquestion+";";

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){

                      return (string)reader["question"];

                    }
                    reader.Close();
                }
            }
            connection.Close();

        }
    return Val;

    }
  


}
