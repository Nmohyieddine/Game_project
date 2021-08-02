
using System.Threading;
using System.Net.Mime;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.UI;



public class PlayerCollison : MonoBehaviour {

	// Use this for initialization
	public PlayerMouvement playerMouvement;
	public static int counterdiamond=0;
	public static int counterstars=0;

	public GameObject popPanel;

	public GameObject quizPanel;

	public GameObject PanelExplication;

	public GameObject PausePanel;



	public Text Textetoile;

	public Text Textdiamond;

	public Text popuptext;

	


	
	void Start(){
		popPanel.SetActive(false);
		quizPanel.SetActive(false);
		PanelExplication.SetActive(false);
		PausePanel.SetActive(false);
		


		

	}


	void Update(){

		
	}

	void OnCollisionEnter(Collision infocollider)
	{


		
		if(infocollider.collider.tag == "taxi")
		{
			

			playerMouvement.enabled = false ;
			UnityEngine.Debug.Log("taxi");


		}
		if(infocollider.collider.tag == "bus")
		{
			

			playerMouvement.enabled = false ;
			UnityEngine.Debug.Log("bus");


		}
		
	}

	void OnTriggerEnter(Collider other )
	{
		
			if(other.gameObject.layer == 10 )
			{

				Destroy(other.gameObject);
			
				counterdiamond=counterdiamond+1;

				Textdiamond.text=counterdiamond.ToString();


				popuptext.text=counterdiamond.ToString()+" X";

				if(counterdiamond ==1 ){

					PanelExplication.SetActive(true);
					Time.timeScale = 0f;


				}

				//quizQuestion.text=Scripte4DB.Showquestion(1);


			

				//quizPanel.SetActive(true);


				





				

				

				

			}if(other.gameObject.layer == 11){

				Destroy(other.gameObject);
				counterstars=counterstars+10;
				Textetoile.text=counterstars.ToString();

			}


	}

	

}


 