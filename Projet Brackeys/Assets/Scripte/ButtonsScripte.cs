using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ButtonsScripte : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject PanelPopup;
    public GameObject Panelquiz;

    public void Yesbutton(){

        PanelPopup.SetActive(false);
        Panelquiz.SetActive(true);


    }
    public void Nonbutton(){
        PanelPopup.SetActive(false);
        Panelquiz.SetActive(false);
    }
}
