using UnityEngine;

public class FlowPlayer : MonoBehaviour {

	// Use this for initialization
	public Transform Player;
	public Vector3 offset;
	
	// Update is called once per frame
	void Update () {

		transform.position = Player.position + offset ;
		
	}
}
