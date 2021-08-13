using System.Net.Mime;
using System.Xml.Linq;
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

    //private static string dbName=@"Data Source=C:\Users\zmiloudi\Desktop\Game_project\Projet_Gamification\Assets\Scripte\QuestionDB.db";
    public static string Val;
    public static int nbr_reponse;
    public static List<String> TablProposition;
    private static string dbName;


    



    
    // Start is called before the first frame update
    void Start()
    {
        dbName="URI=file:" + Application.persistentDataPath + "/QuestionDB.db";

        CreateDB();
        TestingQuestionExistance("hello");
        UnityEngine.Debug.Log(Application.persistentDataPath);
        
        
        
        
        
        

        
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

                command1.CommandText="CREATE TABLE IF NOT EXISTS Questions (idquestion INT , question TEXT );";
                command1.ExecuteNonQuery();
            }using(var command2 = connection.CreateCommand()){

                command2.CommandText="CREATE TABLE IF NOT EXISTS Reponses ( idreponse	INT , idquestion INT, idproposition INT  ,FOREIGN KEY (idquestion) REFERENCES Questions(idquestion),FOREIGN KEY (idproposition) REFERENCES Propositions(idproposition));";
                command2.ExecuteNonQuery();
            }using(var command3 = connection.CreateCommand()){

                command3.CommandText="CREATE TABLE IF NOT EXISTS Propositions ( idproposition	INT, proposition TEXT, idquestion INT, FOREIGN KEY (idquestion) REFERENCES Questions(idquestion));";
                command3.ExecuteNonQuery();
            }
            connection.Close();

        }
    }



    // fonctions de gestion des données pour la partie jeux 

 public static void AddQuestion(int idquestion,string question )

    {

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="INSERT INTO  Questions (idquestion , question ) VALUES ('" +idquestion + "' , '" + question + "'  ); ";
                command.ExecuteNonQuery();
            }
            connection.Close();

        }



    }

    public static bool TestingQuestionExistance(string question){

          using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Questions  ;" ;

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){



                        if(String.Equals(question,(string)reader["question"])){

                         return true;
                        };
                      



                    }
                    reader.Close();
                }
            }
            connection.Close();
            

        }
    
    return false;


    }

     public static bool TestingPropositionExistance(string proposition){

          using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Propositions  ;" ;

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){



                        if(String.Equals(proposition,(string)reader["proposition"])){

                         return true;
                        };
                      



                    }
                    reader.Close();
                }
            }
            connection.Close();
            

        }
    
    return false;


    }
    public static void AddReponse(int idreponse,int idquestion,int idproposition )

    {

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="INSERT INTO  Reponses (idreponse , idquestion , idproposition ) VALUES ('" +idreponse + "'  ,'" +idquestion+  "','" +idproposition+  "' ); ";
                command.ExecuteNonQuery();
            }
            connection.Close();

        }



    }

     public static void addproposition(int idproposition,string  proposition ,int  idquestion ){


         using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="INSERT INTO  Propositions (idproposition ,proposition, idquestion ) VALUES ('" +idproposition + "' , '" + proposition + "' ,'" +idquestion+  "' ); ";
                command.ExecuteNonQuery();
            }
            connection.Close();

        }

    }



     public static string Showquestion(int idquestion)
    {
        

        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Questions WHERE idquestion = "+idquestion+";";

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



     public static List<string> groupProposition(int idquestion){

        
    
        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Propositions WHERE idquestion =" +idquestion+ ";" ;

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){



                      TablProposition.Add((string)reader["proposition"]);
                      



                    }
                    reader.Close();
                }
            }
            connection.Close();
            

        }
    return TablProposition;


    }
    public static List<Reponse> groupReponses(){

        
        List<Reponse> ListReponses=new List<Reponse>();
        using(var connection = new SqliteConnection(dbName)){
            connection.Open();

            using(var command = connection.CreateCommand()){

                command.CommandText="SELECT * FROM Reponses ;" ;

                using(IDataReader reader = command.ExecuteReader()){

                    while(reader.Read()){


                        Reponse rp=new Reponse((int)reader["idreponse"],(int)reader["idproposition"],(int)reader["idquestion"]);
                        ListReponses.Add(rp);
                      



                    }
                    reader.Close();
                }
            }
            connection.Close();
            

        }
    return ListReponses;


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

    

    
}


   



   

   