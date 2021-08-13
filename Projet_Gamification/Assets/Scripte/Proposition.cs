using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Proposition : MonoBehaviour
{
    // Start is called before the first frame update
    public int idproposition;
    public string proposition;
    public int idquestion;

    public Proposition(int idproposition,string proposition,int idquestion){
        this.idproposition=idproposition;
        this.proposition=proposition;
        this.idquestion=idquestion;

    }
}
