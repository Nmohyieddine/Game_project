using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Reponse : MonoBehaviour
{
    // Start is called before the first frame update
   public int idreponse;
   public int propositionId;
   public int questionId;

   public Reponse(int idreponse,int propositionId,int questionId){
       this.idreponse=idreponse;
       this.propositionId=propositionId;
       this.questionId=questionId;

   }
}
