
using UnityEngine;
using UnityEngine.UI;
public class PlayerCollison : MonoBehaviour {

	// Use this for initialization
	public PlayerMouvement playerMouvement;
	public static int counter;

	public Text Scoretext;

	public Transform Player;



	void Update(){

		Scoretext.text="Score:" + counter.ToString();
	}

	void OnCollisionEnter(Collision infocollider)
	{


		
		if(infocollider.collider.tag == "taxi")
		{
			

			playerMouvement.enabled = false ;
			Debug.Log("taxi");


		}
		if(infocollider.collider.tag == "bus")
		{
			

			playerMouvement.enabled = false ;
			Debug.Log("bus");


		}
		
	}

	void OnTriggerEnter(Collider other )
	{
		
			if(other.gameObject.layer == 10 )
			{

				Destroy(other.gameObject);
			
				counter=counter+10;

				Debug.Log(counter);

			}


	}

	

}


 