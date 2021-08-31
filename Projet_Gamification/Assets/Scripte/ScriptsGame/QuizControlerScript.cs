using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class QuizControlerScript : MonoBehaviour
{
    // Start is called before the first frame update
    public Button[] button;
    public GameObject Panelquiz;

    public Text quizQuestion;

    public Text[] TextButtons;
    


    int i=1;
    public static int idreponse=0;

    void Start()
    {

       
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void iniButton(){
        List<string> answer=Scripte4DB.groupProposition(i);
       

       

        for(int i=0;i<button.Length;i++){

            if(i>=Scripte4DB.counterproposition(i)){
                    button[i].gameObject.SetActive(false);  
                    continue;
            }

            button[i].gameObject.SetActive(true);
            button[i].GetComponentInChildren<Text>().text=answer[i];

                        

        }
    }

    public void Xbutton(){

        
        List<string> Ques=Scripte4DB.groupQuestion();

        Panelquiz.SetActive(true);
        //iniButton();
        Time.timeScale = 0f;
        quizQuestion.text=Ques[i];

        i++;



    }

    public void OuiButton(){

        List<int> IdQues=Scripte4DB.groupIdQuestion();

        Scripte4DB.AddReponse(idreponse,IdQues[i],1);
        idreponse++;
        Panelquiz.SetActive(false);
        Time.timeScale = 1f;


    }

    public void NonButton(){
        
        List<int> IdQues=Scripte4DB.groupIdQuestion();

        Scripte4DB.AddReponse(idreponse,IdQues[i],0);

        idreponse++;
        Panelquiz.SetActive(false);
        Time.timeScale = 1f;

        
    }
}
