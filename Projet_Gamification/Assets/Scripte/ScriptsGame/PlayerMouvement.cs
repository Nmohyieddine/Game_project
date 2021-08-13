using System.Diagnostics;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class PlayerMouvement : MonoBehaviour {

	// Use this for initialization
	public Rigidbody Gbakarb;
	public Transform Gbaka;
	public static float Forwardforce=50f;
	public static float Sideforce=5f;


	public bool RightAction=false;
	public bool LeftAction=false;


	void Start () {
		//UnityEngine.Debug.Log("hello world");
		//rb.AddForce(200 , 0 , 500);
		

		
	}
	void Update(){

		if (Input.GetKey(KeyCode.RightArrow)) {

			RightAction=true;
		}

		if(Input.GetKey(KeyCode.LeftArrow)){

			LeftAction=true;

		}

		//Gbakarb.AddForce(0 , 0 ,Forwardforce);

		transform.Translate(Vector3.forward * Forwardforce * Time.deltaTime);






	}
	
		// Update is called once per frame
	void FixedUpdate () {


		
		
		if(RightAction == true){

			//Gbakarb.AddForce(Sideforce, 0 ,0,ForceMode.VelocityChange);
			transform.Translate(UnityEngine.Vector3.right * Forwardforce * Time.deltaTime);

			RightAction=false;

		}

		if(LeftAction == true){

			//Gbakarb.AddForce(-Sideforce, 0 ,0,ForceMode.VelocityChange);
			transform.Translate(UnityEngine.Vector3.left* Forwardforce * Time.deltaTime);

			LeftAction=false;

		}

		if(Gbaka.position.x>7.3){

			//Gbakarb.AddForce(-Sideforce/2,0,0,ForceMode.VelocityChange);
			//Gbakarb.AddForce(0 , 0 ,-Forwardforce);
			transform.Translate(UnityEngine.Vector3.left* Forwardforce * Time.deltaTime);


			

			
		}

		if(Gbaka.position.x<-7.3){

			//Gbakarb.AddForce(Sideforce/2,0,0,ForceMode.VelocityChange);
			//Gbakarb.AddForce(0 , 0 ,-Forwardforce);
			transform.Translate(UnityEngine.Vector3.right* Forwardforce * Time.deltaTime);


			

			
		}

	



		
	}
	

}
