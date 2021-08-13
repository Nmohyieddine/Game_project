
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
    public int h=0;
 

    public static readonly string ApiURL ="http://localhost:8081/api/";
    public static string APIkey="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYyODc2NzE1NX0.mwbh3shn0_1AhGxtoRTQKo_BQYsrpzJa_kljspMPBa724LpXYpNIuZeOtpJ_eB3C6LdaQLZc2HkVlPypXwDWUw";
    

    void Start(){

        List<Reponse> ListNotExisting =new List<Reponse>();
        
        
        
         if(Application.internetReachability == NetworkReachability.NotReachable)
        {
            UnityEngine.Debug.Log("Error. Check internet connection!");

        }else{

            //StartCoroutine(MappingQuestions());
            //StartCoroutine(MappingPropositions());
            StartCoroutine(CheckReponseExixtance(ListNotExisting));
            
                StartCoroutine(InverseMappingReponse(ListNotExisting[1]));
                        


            

            
            

            
          

        }


    }

    
    


    IEnumerator MappingQuestions(){

        string ApiQuestionURL= ApiURL +"questions";

         UnityWebRequest QuestionsInfoRequest = UnityWebRequest.Get(ApiQuestionURL);

       
        //Authentification token
        QuestionsInfoRequest.SetRequestHeader("Authorization", "Bearer " + APIkey);
        QuestionsInfoRequest.SetRequestHeader("Content-Type", "application/json");



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


    IEnumerator MappingPropositions(){

        string ApiPropositionURL= ApiURL +"propositions";

         UnityWebRequest PropositionsInfoRequest = UnityWebRequest.Get(ApiPropositionURL);

        
        //Authentification token      
        PropositionsInfoRequest.SetRequestHeader("Authorization", "Bearer " + APIkey);
        PropositionsInfoRequest.SetRequestHeader("Content-Type", "application/json");



        yield return PropositionsInfoRequest.SendWebRequest();

        if(PropositionsInfoRequest.isNetworkError || PropositionsInfoRequest.isHttpError){

            UnityEngine.Debug.LogError(PropositionsInfoRequest);
            yield break;
        }

        JSONNode Propositioninfo = JSON.Parse(PropositionsInfoRequest.downloadHandler.text);

        

        for (int i = 0 ; i < Propositioninfo.Count  ; i++){
           

            Proposition prop = new Proposition(int.Parse(Propositioninfo[i]["idpropositions"]),Propositioninfo[i]["proposition"],Propositioninfo[i]["questionsIdquestion"]);
             if(!Scripte4DB.TestingPropositionExistance(prop.proposition)){

                Scripte4DB.addproposition(prop.idproposition,prop.proposition,prop.idquestion );               

            }else
            {
                UnityEngine.Debug.Log(prop.proposition+"dèjà éxiste");
            }



        }

       



    }

    


     IEnumerator InverseMappingReponse(Reponse reponse){

        string ApiReponseURL= ApiURL +"reponses";


        string jsonquestion=JsonUtility.ToJson(reponse);
        var request = new UnityWebRequest(ApiReponseURL, "POST");
        byte[] bodyRaw = Encoding.UTF8.GetBytes(jsonquestion);
        request.uploadHandler = (UploadHandler) new UploadHandlerRaw(bodyRaw);
        request.downloadHandler = (DownloadHandler) new DownloadHandlerBuffer();
        
        //Authentification token 
        request.SetRequestHeader("Authorization", "Bearer " + APIkey);
        request.SetRequestHeader("Content-Type", "application/json");

        yield return request.SendWebRequest();
        Debug.Log("Status Code: " + request.responseCode);

        UnityEngine.Debug.Log(jsonquestion);



    }



    //this fonction check if a Reponse exist in the Backoffice Data base

    IEnumerator CheckReponseExixtance(List<Reponse> ListReponseNotExist){

        string ApiPropositionURL= ApiURL +"reponses";

         UnityWebRequest ReponsesInfoRequest = UnityWebRequest.Get(ApiPropositionURL);

        /*  Authentification:

            ReponsesInfoRequest.SetRequestHeader('Authorization :','Bearer '+ APIkey );

            ou

             UnityWebRequest ReponsesInfoRequest = UnityWebRequest.Get(AVWXURL + "?token="+yourAPIKey);

        */ 
        
        
        ReponsesInfoRequest.SetRequestHeader("Authorization", "Bearer " + APIkey);
        ReponsesInfoRequest.SetRequestHeader("Content-Type", "application/json");
      
       
        yield return ReponsesInfoRequest.SendWebRequest();

        if(ReponsesInfoRequest.isNetworkError || ReponsesInfoRequest.isHttpError){

            UnityEngine.Debug.LogError(ReponsesInfoRequest);
            yield break;
        }

        JSONNode Reponseinfo = JSON.Parse(ReponsesInfoRequest.downloadHandler.text);
        
        // mapping object relationnel

        //List<Reponse> ListReponsesDb=Scripte4DB.groupReponses();
        List<Reponse> ListReponsesDb=new List<Reponse>();
        Reponse rp1=new Reponse(2222,2,5);
        Reponse rp2=new Reponse(2223,11,11);
        Reponse rp3=new Reponse(2224,2,5);
        Reponse rp4=new Reponse(2222,2,5);


        ListReponsesDb.Add(rp1);
        ListReponsesDb.Add(rp2);
        ListReponsesDb.Add(rp3);
        ListReponsesDb.Add(rp4);
        
        
        //

        UnityEngine.Debug.Log(ListReponsesDb.Count);
                    


        for (int i = 0 ; i <ListReponsesDb.Count  ; i++){
            
            for(int j=0;j<Reponseinfo.Count;j++){
                

                if(Reponseinfo[j]["idreponse"]==ListReponsesDb[i].idreponse){
                    
                    UnityEngine.Debug.Log("existe");
                    


                }else
                {

                    h++;
                    
                
                }
               
                
            }
             if(h==Reponseinfo.Count){

                ListReponseNotExist.Add(ListReponsesDb[i]);
                UnityEngine.Debug.Log("don't existe");
                


            }
            h=0;
            

            
        }

    }



  



}
