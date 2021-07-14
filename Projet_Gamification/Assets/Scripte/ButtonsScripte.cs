using System.Threading;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ButtonsScripte : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject PanelPopup;
    public GameObject Panelquiz;

    public GameObject PanelExplication;

    public GameObject PausePanel;


    public Text quizQuestion;

    int i=1;


    public void Yesbutton(){

        PanelPopup.SetActive(false);
        Panelquiz.SetActive(true);
        


    }
    public void Nonbutton(){
        PanelPopup.SetActive(false);
        Panelquiz.SetActive(false);
    }

    public void CestPartiebutton(){

        PanelExplication.SetActive(false);
        Time.timeScale = 1f;


    }
    public void pause(){

        PausePanel.SetActive(true);
        Time.timeScale = 0f;
    }

    public void resume(){

        PausePanel.SetActive(false);
        Time.timeScale =1f;


    }

    public void Xbutton(){

        Panelquiz.SetActive(true);
        Time.timeScale = 0f;
        quizQuestion.text=Scripte4DB.Showquestion(i);
        i++;



    }

    public void ouiXButton(){

        


    }
    public void nonXButton(){



    }

    public void passer(){

        Panelquiz.SetActive(false);
        Time.timeScale = 1f;


    }
}
