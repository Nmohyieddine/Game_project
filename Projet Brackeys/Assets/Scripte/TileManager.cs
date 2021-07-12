
using System.Diagnostics;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TileManager : MonoBehaviour
{
    public GameObject [] tilePrefabs;

    public Transform Trantile1;

    //public GameObject titlefirst;

    // Start is called before the first frame update

    public float zSpaw = 0;
    public float titleLentgth = 300 ;

    public Transform transformPlayer;


    int k=0;

 

    public bool accesframe ; 

    public List<GameObject> activeTiles = new List<GameObject>();

    public int posCreation =10;


    

    void Start()
    {
        
        
       

        SpawnTile(0,150);
        

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

        GameObject go=Instantiate(tilePrefabs[tileIndex],transform.forward*(zSpaw+150),transform.rotation);
        
        zSpaw +=titleLentgth;

        activeTiles.Add(go);

    }
    public void SpawnTile(int tileIndex,int possition){

         GameObject go=Instantiate(tilePrefabs[tileIndex],transform.forward*possition,transform.rotation);
        
        zSpaw +=titleLentgth;

        activeTiles.Add(go);

    }
   

    public void DelectTile(){

        Destroy(activeTiles[0]);
        activeTiles.RemoveAt(0);

    }


}