using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class carsMouvement : MonoBehaviour {

	// Use this for initialization
	public Transform TranCar;
	public Rigidbody rbCar;
	public float Forwardforce=2000f;
	void Start () {
		
	}
	
	// Update is called once per frame
	void FixedUpdate () {

		//rbCar.AddForce(0,0,Forwardforce,ForceMode.VelocityChange);
		transform.Translate(-Vector3.forward * Forwardforce * Time.deltaTime);

		//*Time.deltaTime
	}
}
