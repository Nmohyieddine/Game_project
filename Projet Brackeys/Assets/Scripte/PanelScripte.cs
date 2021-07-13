using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PanelScripte : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject StartPanel;
    public GameObject popupPanel;

    public GameObject GameOverPanel;
    void Start()
    {
        //StartPanel.SetActive(false);
        popupPanel.SetActive(false);
        //GameOverPanel.SetActive(false);
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        ActivepopupPanel();
    }

    public void ActivepopupPanel(){

        if(PlayerCollison.counterdiamond != 0){

            popupPanel.SetActive(true);

            

        }
    }
}
