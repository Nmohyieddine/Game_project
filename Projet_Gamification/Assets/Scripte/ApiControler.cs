
using System.Data;
using System.Security.AccessControl;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using SimpleJSON;
using UnityEngine.UI;
using System;
using System.Text;


public class ApiControler : MonoBehaviour
{
  
  
    
    //List avec les questions issue de la base de donné jhipster 
   public  List<Question> listquestions;
    
    public  List<Proposition> listpropositions;
    public  List<Reponse> listreponses;

    public bool ConnectionStatus=false;
 

    public static readonly string ApiURL ="http://localhost:8081/api/";
    public static string APIkey="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYyODE2Mjc3OH0.VX7EVwYLKEpYKQXkKRsD6AjRlIqbi3OyPRpKS-kT1JkjPpUi8AkaBmGgAlqXLdjQd7LwOe0gwoUOCajAVMma7A";
    

    void Start(){

          StartCoroutine(checkInternetConnection(ConnectionStatus));
          ConnectionStatus=false;
        if(!ConnectionStatus){

            StartCoroutine(MappingQuestions());

        }else{
            UnityEngine.Debug.Log("Connection faild");
        }

        
       
        //StartCoroutine(MappingPropositions(listpropositions));
        //Reponse rep=new Reponse(21165,11,11);
       
        //StartCoroutine( InverseMappingReponse(rep));


    }

    
    


    IEnumerator MappingQuestions(){

        string ApiQuestionURL= ApiURL +"questions";

         UnityWebRequest QuestionsInfoRequest = UnityWebRequest.Get(ApiQuestionURL);

        /*  Authentification:

            QuestionsInfoRequest.SetRequestHeader('Authorization :','Bearer '+ APIkey );

            ou

             UnityWebRequest QuestionsInfoRequest = UnityWebRequest.Get(AVWXURL + "?token="+yourAPIKey);

        */
      
       
        yield return QuestionsInfoRequest.SendWebRequest();

        if(QuestionsInfoRequest.isNetworkError || QuestionsInfoRequest.isHttpError){

            UnityEngine.Debug.LogError(QuestionsInfoRequest);
            yield break;
        }

        JSONNode Questioninfo = JSON.Parse(QuestionsInfoRequest.downloadHandler.text);

        

        for (int i = 0 ; i < Questioninfo.Count  ; i++){
           

            Question qs = new Question(int.Parse(Questioninfo[i]["idquestion"]),Questioninfo[i]["question"]);

            if(!Scripte4DB.TestingQuestionExistance(qs.question)){

                Scripte4DB.AddQuestion(qs.idquestion,qs.question );               

            }else
            {
                 UnityEngine.Debug.Log(qs.question+"dèjà éxiste");
            }

               
            


        }

       
        
       
        

    }


    IEnumerator MappingPropositions(List<Proposition> listDesPropositions){

        string ApiPropositionURL= ApiURL +"propositions";

         UnityWebRequest PropositionsInfoRequest = UnityWebRequest.Get(ApiPropositionURL);

        /*  Authentification:

            PropositionsInfoRequest.SetRequestHeader('Authorization :','Bearer '+ APIkey );

            ou

             UnityWebRequest PropositionsInfoRequest = UnityWebRequest.Get(AVWXURL + "?token="+yourAPIKey);

        */
      
       
        yield return PropositionsInfoRequest.SendWebRequest();

        if(PropositionsInfoRequest.isNetworkError || PropositionsInfoRequest.isHttpError){

            UnityEngine.Debug.LogError(PropositionsInfoRequest);
            yield break;
        }

        JSONNode Propositioninfo = JSON.Parse(PropositionsInfoRequest.downloadHandler.text);

        

        for (int i = 0 ; i < Propositioninfo.Count -1 ; i++){
           

            Proposition prop = new Proposition(int.Parse(Propositioninfo[i]["idpropositions"]),Propositioninfo[i]["proposition"],Propositioninfo[i]["questionsIdquestion"]);
            listDesPropositions.Add(prop);


        }

        UnityEngine.Debug.Log(listDesPropositions[0].idproposition);
        UnityEngine.Debug.Log(listDesPropositions[0].proposition);
        UnityEngine.Debug.Log(listDesPropositions[0].idquestion);



    }

    

     IEnumerator InverseMappingReponse(Reponse reponse){

          string ApiReponseURL= ApiURL +"reponses";


        string jsonquestion=JsonUtility.ToJson(reponse);
      
        var request = new UnityWebRequest(ApiReponseURL, "POST");
        byte[] bodyRaw = Encoding.UTF8.GetBytes(jsonquestion);
        request.uploadHandler = (UploadHandler) new UploadHandlerRaw(bodyRaw);
        request.downloadHandler = (DownloadHandler) new DownloadHandlerBuffer();
        request.SetRequestHeader("Content-Type", "application/json");
        yield return request.SendWebRequest();
        Debug.Log("Status Code: " + request.responseCode);

        UnityEngine.Debug.Log(jsonquestion);



    }

     IEnumerator checkInternetConnection(bool action)
    {
        WWW www = new WWW("http://google.com");
        yield return www;
        if (www.error != null) {
            action=false;
        } else {
            action=true;
        }
    }

  



}
