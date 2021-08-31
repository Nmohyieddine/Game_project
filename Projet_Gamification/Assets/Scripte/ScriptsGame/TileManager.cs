
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




    public int k=0;

    public bool accesframe ; 

    public List<GameObject> activeTiles = new List<GameObject>();

    public int posCreation =10;



    public GameObject WinPanel;
    public static int NombrTile ;
    public int NombreDeRoute=2; 



    public GameObject ServicePanel;



       
       
    


    

    void Start()
    {
        
        
       
         //winPanel.SetActive(false);
        SpawnTile(0,150);


        

    }
 
    

    void FixedUpdate()
    {

    
            if(transformPlayer.position.z > posCreation)
            {

                SpawnTile(UnityEngine.Random.Range(0,tilePrefabs.Length));
                

                posCreation+=300;
                k++;
                //inscrementation du nombre de tile
            

                if(k>1){

                    DelectTile();
                    NombrTile++;
                }
                
            }

            ActiveWinPanel();


    

         
      



    }  

    public void ActiveWinPanel(){
        if(NombrTile == NombreDeRoute){

            WinPanel.SetActive(true);
            Time.timeScale = 0f;
            NombrTile=0;





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

      public void choiseService(){

        WinPanel.SetActive(false);  
        ServicePanel.SetActive(true);





    }


}