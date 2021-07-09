
using System.Diagnostics;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TileManager : MonoBehaviour
{
    public GameObject [] tilePrefabs;

    //public GameObject titlefirst;

    // Start is called before the first frame update

    public float zSpaw = 0;
    public float titleLentgth = 300 ;

    public Transform transformPlayer;


    int k=0;

 

    public bool accesframe ; 

    public List<GameObject> activeTiles = new List<GameObject>();

    public int posCreation =75;


    

    void Start()
    {
        
        
        tilePrefabs[0].transform.position=new Vector3(0,0,150);
        SpawnTile(0);
        

    }
 
    

    void Update()
    {

    
        if(transformPlayer.position.z > posCreation)
        {

            SpawnTile(UnityEngine.Random.Range(0,tilePrefabs.Length));
            posCreation+=300;
            k++;
            if(k>1){
                DelectTile();
            }
            
        }

    }


    public void SpawnTile(int tileIndex)
    {

        GameObject go=Instantiate(tilePrefabs[tileIndex],transform.forward*zSpaw,transform.rotation);
        
        zSpaw +=titleLentgth;

        activeTiles.Add(go);

    }
   

    public void DelectTile(){

        Destroy(activeTiles[0]);
        activeTiles.RemoveAt(0);

    }


}