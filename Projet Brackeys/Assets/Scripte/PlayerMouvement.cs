using System.Diagnostics;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMouvement : MonoBehaviour {

	// Use this for initialization
	public Rigidbody rb;
	public Transform Player;
	public float Forwardforce=2000f;
	public float Sideforce=500f;

	public bool RightAction=false;
	public bool LeftAction=false;


	void Start () {
		//Debug.Log("hello world");
		//rb.AddForce(200 , 0 , 500);

		


		
	}
	void Update(){

		if (Input.GetKey(KeyCode.RightArrow)) {

			RightAction=true;
		}

		if(Input.GetKey(KeyCode.LeftArrow)){

			LeftAction=true;

		}

		rb.AddForce(0 , 0 ,Forwardforce);




	}
	
	// Update is called once per frame
	void FixedUpdate () {


		
		
		if(RightAction == true){

			rb.AddForce(Sideforce, 0 ,0,ForceMode.VelocityChange);
			RightAction=false;

		}

		if(LeftAction == true){

			rb.AddForce(-Sideforce, 0 ,0,ForceMode.VelocityChange);
			LeftAction=false;

		}

		if(Player.position.x>7.3){

			rb.AddForce(-Sideforce/2,0,0,ForceMode.VelocityChange);
			rb.AddForce(0 , 0 ,-Forwardforce);

			

			
		}

		if(Player.position.x<-7.3){

			rb.AddForce(Sideforce/2,0,0,ForceMode.VelocityChange);
			rb.AddForce(0 , 0 ,-Forwardforce);

			

			
		}



		
	}
	

}
