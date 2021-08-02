using System.Runtime.CompilerServices;
using System.Diagnostics;
using System.Collections.ObjectModel;
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using SimpleJSON;
using UnityEngine.UI;





public class Questions_controler : MonoBehaviour
{
    public static List<Question> listquestions;
    public static int t=0;
    

    void Start(){

        QuestionContoler(listquestions);

        

       

        	

    }

    void Update(){


        //UnityEngine.Debug.Log(listquestions[0].idquestion.ToString());
        UnityEngine.Debug.Log(t);



    }


    
    public static readonly string ApiURL ="http://localhost:8081/api/";
   
   

    public static IEnumerator QuestionContoler(List<Question> listDesQuestions){

        string ApiQuestionURL= ApiURL +"questions";
        UnityWebRequest QuestionsInfoRequest = UnityWebRequest.Get(ApiQuestionURL);
        QuestionsInfoRequest.SetRequestHeader("Bearer","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYyNzkxNDI0MH0.R47aerBmQ4xd-2t_RThOJKwnVOkzAkOnSG5Rfm_2NmPUR2GXyCEw-OVxSwsUTC7KYhViNVLh9WQA4ir12uM9zg");
        
        yield return QuestionsInfoRequest.SendWebRequest();

        if(QuestionsInfoRequest.isNetworkError || QuestionsInfoRequest.isHttpError){
            UnityEngine.Debug.Log("error");
            UnityEngine.Debug.LogError(QuestionsInfoRequest);
            yield break;
        }

        JSONNode Questioninfo =JSON.Parse(QuestionsInfoRequest.downloadHandler.text);

        

        for(int i=0;i<Questioninfo.Count;i++){
            t++;

            Question qs = new Question(Questioninfo[i]["idquestion"],Questioninfo["question"]);
            listDesQuestions.Add(qs);


            
        }


    }
        
   
}
