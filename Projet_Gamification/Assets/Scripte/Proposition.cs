using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Proposition : MonoBehaviour
{
    // Start is called before the first frame update
    public int idproposition;
    public string proposition;
    public string idquestion;

    public Proposition(int idproposition,string proposition,string idquestion){
        this.idproposition=idproposition;
        this.proposition=proposition;
        this.idquestion=idquestion;

    }
}
