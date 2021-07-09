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



	void Start () {
		//Debug.Log("hello world");
		//rb.AddForce(200 , 0 , 500);

		
	}
	
	// Update is called once per frame
	void FixedUpdate () {

		rb.AddForce(0 , 0 ,Forwardforce* Time.deltaTime);
		
		
		if(Input.GetKey(KeyCode.RightArrow)){

			rb.AddForce(Sideforce* Time.deltaTime, 0 ,0,ForceMode.VelocityChange);

		}

		if(Input.GetKey(KeyCode.LeftArrow)){

			rb.AddForce(-Sideforce* Time.deltaTime, 0 ,0,ForceMode.VelocityChange);

		}

		if(Player.position.x>7.3){

			rb.AddForce(-(Sideforce* Time.deltaTime+5),0,0,ForceMode.VelocityChange);

			

			
		}

		if(Player.position.x<-7.3){

			rb.AddForce((Sideforce* Time.deltaTime+5),0,0,ForceMode.VelocityChange);

			

			
		}



		
	}
	

}
