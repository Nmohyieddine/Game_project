using System.Collections.Generic;
using System.Collections;
using System;
using System.Diagnostics;
using System.Data;
using UnityEngine;
using Mono.Data.Sqlite;
using UnityEngine.UI;
public class Scripte4DB : MonoBehaviour
{

    private static string dbName =@"Data Source=C:\Users\zmiloudi\Desktop\Game_project\Projet_Gamification\Assets\Scripte\QuesionsDB.db,Version=3";
    public static string Val;
    public static int nbr_reponse;
    public static List<String> TablProposition;



    
    // Start is called before the first frame update
    void Start()
    {
        CreateDB();
        
        

        
    }


    // Update is called once per frame
    void Update()
    {

        //QuestionContoler(listeQuestion);

        //UnityEngine.Debug.Log(listeQuestion[0].idquestion);
        
    }



    public static void CreateDB(){

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command1 = connection.CreateCommand()){

                command1.CommandText="CREATE TABLE IF NOT EXISTS Questions (ID INT , question TEXT );";
                command1.ExecuteNonQuery();
            }using(var command2 = connection.CreateCommand()){

                command2.CommandText="CREATE TABLE IF NOT EXISTS Reponses ( ID	INT, fk_question	TEXT , reponse TEXT, FOREIGN KEY (fk_question) REFERENCES Questions(ID)  );";
                command2.ExecuteNonQuery();
            }using(var command3 = connection.CreateCommand()){

                command3.CommandText="CREATE TABLE IF NOT EXISTS Propositions ( ID	INT, fk_question	TEXT, proposition TEXT, FOREIGN KEY (fk_question) REFERENCES Questions(ID)  );";
                command3.ExecuteNonQuery();
            }
            connection.Close();

        }
    }


    public static void Addanswer(int IDreponse,int fk_question, string answer )

    {

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="INSERT INTO  Reponses (ID , fk_question, reponse ) VALUES ('" +IDreponse + "' , '" + fk_question + "' ,'" +answer+  "' ); ";
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



    public static List<string> groupProposition(int IDquestion){

        
    
        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Propositions WHERE fk_question =" +IDquestion+ ";" ;

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){



                      //TablProposition.Add((string)reader["proposition"]);
                      



                    }
                    reader.Close();
                }
            }
            connection.Close();
            

        }
    return TablProposition;


    }




    public static int counterproposition(int IDquestion){

          using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Propositions WHERE fk_question =" +IDquestion+ ";" ;

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){

                      nbr_reponse++;

                    }
                    reader.Close();
                }
            }

            connection.Close();

        }

    return nbr_reponse;




    }

    public static void addproposition(int IDquestion,int fk_question,string proposition){


         using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="INSERT INTO  Propositions (ID ,fk_question, proposition ) VALUES ('" +IDquestion + "' , '" + fk_question + "' ,'" +proposition+  "' ); ";
                command.ExecuteNonQuery();
            }
            connection.Close();

        }

    }
   

}
